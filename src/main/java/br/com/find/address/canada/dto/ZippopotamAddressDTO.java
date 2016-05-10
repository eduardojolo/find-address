package br.com.find.address.canada.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.find.address.dto.AddressDTO;

/**
 * Zippopotam api address DTO custom mapping.
 * 
 * @author Eduardo
 *
 */
public class ZippopotamAddressDTO extends AddressDTO implements Serializable {

	/**
	 * UID.
	 */
	private static final long serialVersionUID = 4566928120602614439L;

	/**
	 * Map cep to postalCode.
	 */
	@JsonProperty("post code")
	@Override
	public void setPostalCode(String postalCode) {
		super.setPostalCode(postalCode);
	}

	/**
	 * Map estado to region.
	 */
	@JsonProperty("places")
	public void setPlaces(JsonNode[] nodes) {
		if(nodes != null && nodes.length > 0) {
			JsonNode node = nodes[0];
			
			this.setCity(node.findValue("place name").textValue());
			this.setRegion(node.findValue("state").textValue());
		}
	}
}
