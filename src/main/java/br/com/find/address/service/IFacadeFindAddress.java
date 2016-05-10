package br.com.find.address.service;

import br.com.find.address.dto.AddressDTO;

public interface IFacadeFindAddress {

	AddressDTO findAddressByPostalCodeBrazil(String postalCode);
	
	AddressDTO findAddressByPostalCodeCanada(String postalCode);
}
