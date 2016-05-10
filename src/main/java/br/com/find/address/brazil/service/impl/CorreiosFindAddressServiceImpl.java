package br.com.find.address.brazil.service.impl;

import org.springframework.web.client.RestTemplate;

import br.com.find.address.brazil.dto.CorreiosapiAddressDTO;
import br.com.find.address.dto.AddressDTO;

/**
 * Implementation of the integration to find Brazilian addresses using the correios rest api.
 * 
 * @author Eduardo
 *
 */
public class CorreiosFindAddressServiceImpl extends AbstractBrazilFindAddressService {

	/**
	 * Service URL.
	 */
	private static final String SERVICE_URL = "http://correiosapi.apphb.com/cep/";

	@Override
	public AddressDTO findAddressByPostalCodeIntegration(String postalCode) {
		AddressDTO addressDTO;

		RestTemplate restTemplate = new RestTemplate();
		addressDTO = restTemplate.getForObject(SERVICE_URL + postalCode, CorreiosapiAddressDTO.class);

		return addressDTO;
	}

}
