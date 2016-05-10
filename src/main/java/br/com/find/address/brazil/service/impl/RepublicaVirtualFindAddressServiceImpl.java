package br.com.find.address.brazil.service.impl;

import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.find.address.brazil.dto.RepublicaVirtualAddressDTO;
import br.com.find.address.dto.AddressDTO;

/**
 * Implementation of the integration to find Brazilian addresses using the republica virtual rest api.
 * 
 * @author Eduardo
 *
 */
public class RepublicaVirtualFindAddressServiceImpl extends AbstractBrazilFindAddressService {

	/**
	 * Service URL.
	 */
	private static final String SERVICE_URL = "http://cep.republicavirtual.com.br/web_cep.php";

	/**
	 * Postal code param.
	 */
	private static final String POSTAL_CODE_PARAM = "cep";

	/**
	 * Format param.
	 */
	private static final String FORMAT_PARAM = "formato";

	/**
	 * Json param option.
	 */
	private static final String FORMAT_JSON = "json";

	@Override
	public AddressDTO findAddressByPostalCodeIntegration(String postalCode) {
		AddressDTO addressDTO;

		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SERVICE_URL)
				.queryParam(POSTAL_CODE_PARAM, postalCode).queryParam(FORMAT_PARAM, FORMAT_JSON);

		addressDTO = restTemplate.getForObject(builder.toUriString(), RepublicaVirtualAddressDTO.class);

		if (addressDTO != null && StringUtils.isEmpty(addressDTO.getCity())) {
			addressDTO = null;
		} else {
			addressDTO.setPostalCode(postalCode);
		}

		return addressDTO;
	}

}
