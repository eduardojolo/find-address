package br.com.find.address.canada.service.impl;

import org.springframework.web.client.RestTemplate;

import br.com.find.address.canada.dto.ZippopotamAddressDTO;
import br.com.find.address.dto.AddressDTO;

/**
 * Implementation of the integration to find Canadian addresses using the Zippopotam rest api.
 * 
 * @author Eduardo
 *
 */
public class ZippopotamFindAddressServiceImpl extends AbstractCanadaFindAddressService {

	/**
	 * Service URL.
	 */
	private static final String SERVICE_URL = "http://api.zippopotam.us/CA/";

	@Override
	public AddressDTO findAddressByPostalCodeIntegration(String postalCode) {
		AddressDTO addressDTO;

		RestTemplate restTemplate = new RestTemplate();
		addressDTO = restTemplate.getForObject(SERVICE_URL + postalCode, ZippopotamAddressDTO.class);

		return addressDTO;
	}

}
