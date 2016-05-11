package br.com.find.address.service;

import java.util.Queue;

import rx.Observable;

/**
 * Interface of the web service integration analyzer.
 * 
 * @author Eduardo
 *
 */
public interface IIntegrationAnalyzerService {

	/**
	 * Registers a new observable instance of the performance analyzer.
	 *  
	 * @param postalCodeServices Postal code services to be analized
	 * @return Observable<Queue<IFindAddressService>>
	 */
	Observable<Queue<IFindAddressService>> observeOrderAddressProviders(Queue<IFindAddressService> postalCodeServices);
}
