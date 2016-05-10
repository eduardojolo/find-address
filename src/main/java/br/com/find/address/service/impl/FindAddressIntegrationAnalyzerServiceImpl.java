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
import br.com.find.address.service.IFindAddressIntegrationAnalyzerService;
import rx.Observable;
import rx.Subscriber;

@Service
public class FindAddressIntegrationAnalyzerServiceImpl implements IFindAddressIntegrationAnalyzerService {

	private final static String POSTAL_CODE_PERFORMANCE_ANALYZER = "13098426";

	@Override
	public Observable<Queue<IFindAddressService>> observeOrderAddressProviders(
			Queue<IFindAddressService> postalCodeServices) {

		return Observable.create((Subscriber<? super Queue<IFindAddressService>> subscriber) -> {
			subscriber.onNext(orderAddressProvidersByPerformance(postalCodeServices));
			subscriber.onCompleted();
		});
	}

	private Queue<IFindAddressService> orderAddressProvidersByPerformance(Queue<IFindAddressService> postalCodeServices) {

		Queue<IFindAddressService> copyPostalCodeServices = new LinkedList<IFindAddressService>(postalCodeServices);

		List<IFindAddressService> successCallList = new ArrayList<IFindAddressService>();
		List<IFindAddressService> errosCallList = new ArrayList<IFindAddressService>();

		IFindAddressService iPostalCodeService = null;
		int size = copyPostalCodeServices.size();

		for (int i = 0; i < size; i++) {
			try {
				iPostalCodeService = copyPostalCodeServices.poll();
				iPostalCodeService.setIntegrationCallTime(this.getIntegrationCallTime(iPostalCodeService));

				successCallList.add(iPostalCodeService);
			} catch (Exception ex) {
				errosCallList.add(iPostalCodeService);
			}
		}

		return createNewPostalCodeServiceQueue(successCallList, errosCallList);
	}

	private Long getIntegrationCallTime(IFindAddressService iPostalCodeService) {
		AddressDTO addressDTO = null;
		Long initTime;
		Long finalTime;

		initTime = Calendar.getInstance().getTimeInMillis();
		addressDTO = iPostalCodeService.findAddressByPostalCodeIntegration(POSTAL_CODE_PERFORMANCE_ANALYZER);
		finalTime = Calendar.getInstance().getTimeInMillis();

		if (addressDTO != null) {
			return finalTime - initTime;
		}

		throw new AddressNotFoundException(POSTAL_CODE_PERFORMANCE_ANALYZER);
	}

	private Queue<IFindAddressService> createNewPostalCodeServiceQueue(List<IFindAddressService> successCallList,
			List<IFindAddressService> errosCallList) {

		Queue<IFindAddressService> newPostalCodeServices = new LinkedList<IFindAddressService>();
		Collections.sort(successCallList, new Comparator<IFindAddressService>() {
			@Override
			public int compare(IFindAddressService o1, IFindAddressService o2) {
				return o1.getIntegrationCallTime().compareTo(o2.getIntegrationCallTime());
			}
		});
		newPostalCodeServices.addAll(successCallList);
		newPostalCodeServices.addAll(errosCallList);

		return newPostalCodeServices;
	}

}
