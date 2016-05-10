package br.com.find.address.brazil.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.find.address.dto.AddressDTO;

/**
 * Republica Virtual address DTO mapping.
 * 
 * @author Eduardo
 *
 */
public class RepublicaVirtualAddressDTO extends AddressDTO implements Serializable  {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -3078380776191192161L;

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
	 * Map logradouro to address.
	 */
	@JsonProperty("logradouro")
	@Override
	public void setAddress(String address) {
		super.setAddress(address);
	}

	/**
	 * Map uf to region.
	 */
	@JsonProperty("uf")
	@Override
	public void setRegion(String region) {
		super.setRegion(region);
	}

}
