package br.com.find.address.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.find.address.brazil.service.impl.FindAddressRouterBrazilImpl;
import br.com.find.address.canada.service.impl.FindAddressRouterCanadaImpl;
import br.com.find.address.dto.AddressDTO;
import br.com.find.address.service.IFacadeFindAddress;

/**
 * Implementation of the find address facade.
 * 
 * @author Eduardo
 *
 */
@Service
public class FacadeFindAddressImpl implements IFacadeFindAddress{

	@Autowired
	private FindAddressRouterBrazilImpl findAddressRouterBrazilImpl;
	
	@Autowired
	private FindAddressRouterCanadaImpl findAddressRouterCanadaImpl;
	
	/**
	 * Find an address by its Brazilian postal code.
	 */
	public AddressDTO findAddressByPostalCodeBrazil(String postalCode) {
		return findAddressRouterBrazilImpl.routeFindAddressByPostalCode(postalCode);
	}

	/**
	 * Find an address by its Canadian postal code.
	 */
	@Override
	public AddressDTO findAddressByPostalCodeCanada(String postalCode) {
		return findAddressRouterCanadaImpl.routeFindAddressByPostalCode(postalCode);
	}
	
}
