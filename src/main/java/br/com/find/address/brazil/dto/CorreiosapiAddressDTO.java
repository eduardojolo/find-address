package br.com.find.address.brazil.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.find.address.dto.AddressDTO;

/**
 * Correios api address DTO custom mapping.
 * 
 * @author Eduardo
 *
 */
public class CorreiosapiAddressDTO extends AddressDTO implements Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5818541667052051574L;

	/**
	 * Map bairro to district.
	 */
	@JsonProperty("bairro")
	@Override
	public void setDistrict(String district) {
		super.setDistrict(district);
	}

	/**
	 * Map cidade to city.
	 */
	@JsonProperty("cidade")
	@Override
	public void setCity(String city) {
		super.setCity(city);
	}

	/**
	 * Map cep to postalCode.
	 */
	@JsonProperty("cep")
	@Override
	public void setPostalCode(String postalCode) {
		super.setPostalCode(postalCode);
	}

	/**
	 * Map logradouro to address.
	 */
	@JsonProperty("logradouro")
	@Override
	public void setAddress(String address) {
		super.setAddress(address);
	}

	/**
	 * Map estado to region.
	 */
	@JsonProperty("estado")
	@Override
	public void setRegion(String region) {
		super.setRegion(region);
	}
}
