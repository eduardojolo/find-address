package br.com.find.address.service;

import br.com.find.address.dto.AddressDTO;

/**
 * Interface for the facade of the multiple find address services.
 * 
 * @author Eduardo
 *
 */
public interface IFacadeFindAddress {

	/**
	 * Find address by a Brazilian postal code.
	 * 
	 * @param postalCode Postal code
	 * @return AddressDTO
	 */
	AddressDTO findAddressByPostalCodeBrazil(String postalCode);
	
	/**
	 * Find address by a Canadian postal code.
	 * 
	 * @param postalCode Postal code
	 * @return AddressDTO
	 */
	AddressDTO findAddressByPostalCodeCanada(String postalCode);
}
