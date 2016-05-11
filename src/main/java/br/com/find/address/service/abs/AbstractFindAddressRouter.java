package br.com.find.address.service.abs;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.find.address.dto.AddressDTO;
import br.com.find.address.hazelcast.utils.HazelcastFindAddressUtils;
import br.com.find.address.service.IIntegrationAnalyzerService;
import br.com.find.address.service.IFindAddressRouter;
import br.com.find.address.service.IFindAddressService;
import rx.schedulers.Schedulers;

/**
 * Abstract class of the find address router.
 * 
 * @author Eduardo
 *
 */
@Service
public abstract class AbstractFindAddressRouter implements IFindAddressRouter {

	/**
	 * Logger instance.
	 */
	private static final Logger LOGGER = Logger.getLogger(AbstractFindAddressRouter.class);

	/**
	 * Performance analyzer service.
	 */
	@Autowired
	private IIntegrationAnalyzerService performanceAnalyzerService;

	/**
	 * Queue of the available services to find addresses.
	 */
	private Queue<IFindAddressService> findAddressServices;

	/**
	 * Default constructor.
	 */
	public AbstractFindAddressRouter() {
		fillFindAddressServices();
	}
	
	/**
	 * Fill the queue of find address services.
	 */
	protected abstract void fillFindAddressServices();

	@Override
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

		if (orderChanged) {
			this.setFindAddressServices(copyFindAddressServices);
		}

		return addressDTO;
	}

	/**
	 * Check the current call count and starts a new thread for the performance analyzer.
	 */
	private synchronized void checkCallCount() {
		Integer callsCount = this.getCallsCount();
		if (callsCount == -1 || callsCount > 100) {
			callsCount = 0;

			performanceAnalyzerService.observeOrderAddressProviders(getFindAddressServices())
					.subscribeOn(Schedulers.newThread()).subscribe(queue -> {
						setFindAddressServices(queue);
					});
		}
		this.setCallsCount(callsCount + 1);
	}

	@Override
	public Queue<IFindAddressService> getFindAddressServices() {
		return findAddressServices;
	}

	@Override
	public synchronized void setFindAddressServices(Queue<IFindAddressService> findAddressServices) {
		this.findAddressServices = findAddressServices;
	}

	@Override
	public String getCurrentWebServiceName() {
		return this.getFindAddressServices().peek().getWebServiceName();
	}

	@Override
	public Long getCurrentResponseTime() {
		return this.getFindAddressServices().peek().getIntegrationCallTime();
	}

	@Override
	public Integer getCurrentNumberCachedElements() {
		return HazelcastFindAddressUtils.getInstance()
				.getNumberCachedElements(this.getFindAddressServices().peek().getCacheMapName());
	}
}
