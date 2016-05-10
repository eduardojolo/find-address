package br.com.find.address.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Common address dto.
 * 
 * @author Eduardo
 *
 */
public class AddressDTO implements Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 2200797667724538017L;

	private String district;
	
	private String city;
	
	private String postalCode;
	
	private String address;
	
	private String region;

	@JsonProperty("district")
	public String getDistrict() {
		return district;
	}

	@JsonProperty("district")
	public void setDistrict(String district) {
		this.district = district;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("postalCode")
	public String getPostalCode() {
		return postalCode;
	}

	@JsonProperty("postalCode")
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@JsonProperty("region")
	public String getRegion() {
		return region;
	}

	@JsonProperty("region")
	public void setRegion(String region) {
		this.region = region;
	}

	@JsonProperty("address")
	public String getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(String address) {
		this.address = address;
	}
	
}
