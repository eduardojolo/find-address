package br.com.find.address.brazil.service.impl;

import org.springframework.web.client.RestTemplate;

import br.com.find.address.brazil.dto.PostmonAddressDTO;
import br.com.find.address.dto.AddressDTO;

/**
 * Implementation of the integration to find Brazilian addresses using the postmon rest api.
 * 
 * @author Eduardo
 *
 */
public class PostmonFindAddressServiceImpl extends AbstractBrazilFindAddressService {
	
	/**
	 * Service URL.
	 */
	private static final String SERVICE_URL = "http://api.postmon.com.br/v1/cep/";

	@Override
	public AddressDTO findAddressByPostalCodeIntegration(String postalCode) {
		AddressDTO addressDTO;

		RestTemplate restTemplate = new RestTemplate();
		addressDTO = restTemplate.getForObject(SERVICE_URL + postalCode, PostmonAddressDTO.class);

		return addressDTO;
	}

}
