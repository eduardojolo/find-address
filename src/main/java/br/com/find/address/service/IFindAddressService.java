package br.com.find.address.service;

import br.com.find.address.dto.AddressDTO;

public interface IFindAddressService {

	AddressDTO findAdressByPostalCode(String postalCode);
	
	AddressDTO findAddressByPostalCodeIntegration(String postalCode);

	String getCacheMapName();
	
	Long getIntegrationCallTime();
	
	void setIntegrationCallTime(Long integrationCallTime);
}
