package br.com.find.address.service;

import br.com.find.address.dto.AddressDTO;

/**
 * Interface for the find address services.
 * 
 * @author Eduardo
 *
 */
public interface IFindAddressService {

	/**
	 * Finds an address by its postal code.
	 * 
	 * @param postalCode Postal code
	 * @return AddressDTO
	 */
	AddressDTO findAdressByPostalCode(String postalCode);
	
	/**
	 * Finds an address by its postal code utilizing the best web service available.
	 * 
	 * @param postalCode Postal code
	 * @return AddressDTO
	 */
	AddressDTO findAddressByPostalCodeIntegration(String postalCode);

	/**
	 * Get the cache map name for the service group.
	 * 
	 * @return String
	 */
	String getCacheMapName();
	
	/**
	 * Get the web service call time from the last analisis.
	 * 
	 * @return Long
	 */
	Long getIntegrationCallTime();
	
	/**
	 * Set integration call time, to be used by the performance analyzer.
	 * 
	 * @param integrationCallTime Integration call time
	 */
	void setIntegrationCallTime(Long integrationCallTime);
	
	/**
	 * Get the web service name to be displayed in the board.
	 * 
	 * @return String
	 */
	String getWebServiceName();
	
	/**
	 * Get the postal code to be utilized by the performance analyzer.
	 * 
	 * @return String
	 */
	String getPostalCodeForPerformanceAnalyzer();
}
