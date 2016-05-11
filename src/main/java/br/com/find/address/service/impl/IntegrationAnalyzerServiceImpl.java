package br.com.find.address.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;

import br.com.find.address.dto.AddressDTO;
import br.com.find.address.exception.AddressNotFoundException;
import br.com.find.address.service.IFindAddressService;
import br.com.find.address.service.IIntegrationAnalyzerService;
import rx.Observable;
import rx.Subscriber;

/**
 * Implementation of the performance analyzer.
 * 
 * @author Eduardo
 *
 */
@Service
public class IntegrationAnalyzerServiceImpl implements IIntegrationAnalyzerService {

	@Override
	public Observable<Queue<IFindAddressService>> observeOrderAddressProviders(
			Queue<IFindAddressService> postalCodeServices) {

		return Observable.create((Subscriber<? super Queue<IFindAddressService>> subscriber) -> {
			subscriber.onNext(orderAddressProvidersByPerformance(postalCodeServices));
			subscriber.onCompleted();
		});
	}

	/**
	 * Orders the services by its reponse time. 
	 * If an error occurs the service goes to the end of the queue.
	 * Updates the integration call time of the services.
	 * 
	 * @param postalCodeServices Postal code services
	 * @return Queue<IFindAddressService>
	 */
	private Queue<IFindAddressService> orderAddressProvidersByPerformance(
			Queue<IFindAddressService> postalCodeServices) {

		Queue<IFindAddressService> copyPostalCodeServices = new LinkedList<IFindAddressService>(postalCodeServices);

		List<IFindAddressService> successCallList = new ArrayList<IFindAddressService>();
		List<IFindAddressService> errorCallList = new ArrayList<IFindAddressService>();

		IFindAddressService iPostalCodeService = null;
		int size = copyPostalCodeServices.size();

		for (int i = 0; i < size; i++) {
			try {
				iPostalCodeService = copyPostalCodeServices.poll();
				iPostalCodeService.setIntegrationCallTime(this.getIntegrationCallTime(iPostalCodeService));

				successCallList.add(iPostalCodeService);
			} catch (Exception ex) {
				errorCallList.add(iPostalCodeService);
			}
		}

		return createNewPostalCodeServiceQueue(successCallList, errorCallList);
	}

	/**
	 * Gets the response time for an web service call.
	 * 
	 * @param iPostalCodeService Postal code service
	 * @return Long
	 */
	private Long getIntegrationCallTime(IFindAddressService iPostalCodeService) {
		AddressDTO addressDTO = null;
		Long initTime;
		Long finalTime;

		initTime = Calendar.getInstance().getTimeInMillis();
		addressDTO = iPostalCodeService.findAddressByPostalCodeIntegration(iPostalCodeService.getPostalCodeForPerformanceAnalyzer());
		finalTime = Calendar.getInstance().getTimeInMillis();

		if (addressDTO != null) {
			return finalTime - initTime;
		}

		throw new AddressNotFoundException(iPostalCodeService.getPostalCodeForPerformanceAnalyzer());
	}

	/**
	 * Unifies the success and error lists, ordering before returning.
	 * 
	 * @param successCallList List with the services that returned with success
	 * @param errorCallList List with the services that returned with error
	 * @return Queue<IFindAddressService>
	 */
	private Queue<IFindAddressService> createNewPostalCodeServiceQueue(List<IFindAddressService> successCallList,
			List<IFindAddressService> errorCallList) {

		Queue<IFindAddressService> newPostalCodeServices = new LinkedList<IFindAddressService>();
		Collections.sort(successCallList, new Comparator<IFindAddressService>() {
			@Override
			public int compare(IFindAddressService o1, IFindAddressService o2) {
				return o1.getIntegrationCallTime().compareTo(o2.getIntegrationCallTime());
			}
		});
		newPostalCodeServices.addAll(successCallList);
		newPostalCodeServices.addAll(errorCallList);

		return newPostalCodeServices;
	}

}
