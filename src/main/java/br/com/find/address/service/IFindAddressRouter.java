package br.com.find.address.service;

import br.com.find.address.dto.AddressDTO;

public interface IFindAddressRouter {

	AddressDTO routeFindAddressByPostalCode(String postalCode);
}
