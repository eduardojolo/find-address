package br.com.find.address.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.find.address.dto.AddressDTO;
import br.com.find.address.exception.AddressNotFoundException;
import br.com.find.address.service.IFacadeFindAddress;

/**
 * Find address controller.
 * Process the requests for addresses by postal code for different countries.
 * 
 * @author Eduardo
 *
 */
@RestController
public class FindAddressController {

	/**
	 * Find address services facade.
	 */
	@Autowired
	private IFacadeFindAddress facadeFindAddress;
	
	/**
	 * Get a Brazilian address by its postal code.
	 * 
	 * @param postalCode Postal code
	 * @return AddressDTO
	 */
	@RequestMapping(value="/address/br/{postalCode}", method= RequestMethod.GET)
	public AddressDTO getBrazilianAddressByCountryPostalCode(@PathVariable String postalCode) {
		
		String formatedCode = postalCode.replaceAll("[^\\d]", "");
		
		AddressDTO addressDTO = facadeFindAddress.findAddressByPostalCodeBrazil(formatedCode);
		
		if(addressDTO == null) {
			throw new AddressNotFoundException(formatedCode);
		}
		
		return addressDTO;
	}
	
	/**
	 * Get a Canadian address by its postal code.
	 * 
	 * @param postalCode Postal code
	 * @return AddressDTO
	 */
	@RequestMapping(value="/address/ca/{postalCode}", method= RequestMethod.GET)
	public AddressDTO getCanadianAddressByCountryPostalCode(@PathVariable String postalCode) {
				
		AddressDTO addressDTO = facadeFindAddress.findAddressByPostalCodeCanada(postalCode);
		
		if(addressDTO == null) {
			throw new AddressNotFoundException(postalCode);
		}
		
		return addressDTO;
	}
}
