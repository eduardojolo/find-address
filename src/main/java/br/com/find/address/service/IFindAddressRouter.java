package br.com.find.address.service;

import java.util.Queue;

import br.com.find.address.dto.AddressDTO;

/**
 * Interface of the find address routers.
 * 
 * @author Eduardo
 *
 */
public interface IFindAddressRouter {
	
	/**
	 * Route the find address by postal code choosing the currently best option to receive the request.
	 * 
	 * @param postalCode Postal code
	 * @return AddressDTO
	 */
	AddressDTO routeFindAddressByPostalCode(String postalCode);
		
	/**
	 * Get the queue of services the router is considering when redirecting the request.
	 * 
	 * @return Queue<IFindAddressService>
	 */
	Queue<IFindAddressService> getFindAddressServices();
	
	/**
	 * Set the find address services queue to be considered in the performace analysis and redirection of the requests.
	 * 
	 * @param findAddressServices Queue of find address services
	 */
	void setFindAddressServices(Queue<IFindAddressService> findAddressServices);
	
	/**
	 * Get the name of the integration web service currently being used to handle unknown postal codes.
	 * 
	 * @return String
	 */
	String getCurrentWebServiceName();
	
	/**
	 * Get the response time of the last analysis of the web service being currently being used.
	 * 
	 * @return Long
	 */
	Long getCurrentResponseTime();
	
	/**
	 * Get the number of elements in the specific cache.
	 * 
	 * @return Integer
	 */
	Integer getCurrentNumberCachedElements();
	
	/**
	 * Get the country name of the router implementation.
	 * 
	 * @return String
	 */
	String getCountryName();
	
	/**
	 * Get the country flag image file name.
	 * 
	 * @return String
	 */
	String getCountryImageName();
	
	/**
	 * Get the current call count for the specific router.
	 * 
	 * @return Integer
	 */
	Integer getCallsCount();
	
	/**
	 * Set the current call count for the specific router.
	 * 
	 * @param callsCount Call count
	 */
	void setCallsCount(Integer callsCount);
}
