package br.com.find.address.service.abs;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.find.address.dto.AddressDTO;
import br.com.find.address.service.IFindAddressIntegrationAnalyzerService;
import br.com.find.address.service.IFindAddressRouter;
import br.com.find.address.service.IFindAddressService;
import rx.schedulers.Schedulers;

@Service
public abstract class AbstractFindAddressRouter implements IFindAddressRouter {

	private static final Logger LOGGER = Logger.getLogger(AbstractFindAddressRouter.class);

	@Autowired
	private IFindAddressIntegrationAnalyzerService postalCodePerformanceAnalyzerService;
	
	private Queue<IFindAddressService> findAddressServices;

	private static Integer callCount = -1;
	
	public AbstractFindAddressRouter() {
		fillFindAddressServices();
	}
	
	protected abstract void fillFindAddressServices();

	public AddressDTO routeFindAddressByPostalCode(String postalCode) {
		AddressDTO addressDTO = null;
		int attempts = 0;
		
		Queue<IFindAddressService> copyFindAddressServices = new LinkedList<>(getFindAddressServices());

		this.checkCallCount();

		boolean orderChanged = false;
		while (attempts < copyFindAddressServices.size() && addressDTO == null) {
			try {
				addressDTO = copyFindAddressServices.peek().findAdressByPostalCode(postalCode);

				if (addressDTO != null) {
					break;
				}

			} catch (Exception ex) {
				LOGGER.info("Exception in the " + copyFindAddressServices.peek().getClass().getName()
						+ ".findAdressByPostalCode. Message: " + ex.getMessage());
			}

			attempts++;
			copyFindAddressServices.add(copyFindAddressServices.poll());
			orderChanged = true;
		}
		
		if(orderChanged) {
			this.setFindAddressServices(copyFindAddressServices);
		}

		return addressDTO;
	}

	private synchronized void checkCallCount() {
		if (callCount == -1 || callCount > 100) {
			callCount = 0;

			postalCodePerformanceAnalyzerService.observeOrderAddressProviders(getFindAddressServices())
					.subscribeOn(Schedulers.newThread()).subscribe(queue -> {
						setFindAddressServices(queue);
					});
		}
		callCount++;
	}
	
	public Queue<IFindAddressService> getFindAddressServices() {
		return findAddressServices;
	}

	public synchronized void setFindAddressServices(Queue<IFindAddressService> findAddressServices) {
		this.findAddressServices = findAddressServices;
	}

}
