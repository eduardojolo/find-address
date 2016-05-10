package br.com.find.address.service.abs;

import br.com.find.address.dto.AddressDTO;
import br.com.find.address.hazelcast.utils.HazelcastFindAddressUtils;
import br.com.find.address.service.IFindAddressService;

public abstract class AbstractFindAddressService implements IFindAddressService {

	/**
	 * Historic of the response time.
	 */
	private Long integrationCallTime = 0L;
	
	@Override
	public abstract AddressDTO findAddressByPostalCodeIntegration(String postalCode);

	@Override
	public abstract String getCacheMapName();
	
	@Override
	public AddressDTO findAdressByPostalCode(String postalCode) {
		
		AddressDTO addressDTO = HazelcastFindAddressUtils.getInstance().findAddressDTO(this.getCacheMapName(), postalCode);

		if (addressDTO == null) {
			addressDTO = findAddressByPostalCodeIntegration(postalCode);

			if (addressDTO != null) {
				HazelcastFindAddressUtils.getInstance().insertAddressDTO(this.getCacheMapName(), postalCode, addressDTO);
			}
		}

		return addressDTO;
	}
	
	@Override
	public Long getIntegrationCallTime() {
		return integrationCallTime;
	}

	@Override
	public void setIntegrationCallTime(Long integrationCallTime) {
		this.integrationCallTime = integrationCallTime;
	}

}
