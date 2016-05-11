package br.com.find.address.service.abs;

import br.com.find.address.dto.AddressDTO;
import br.com.find.address.hazelcast.constants.HazelcastConstants;
import br.com.find.address.hazelcast.utils.HazelcastFindAddressUtils;
import br.com.find.address.service.IFindAddressService;

/**
 * Abstract class of the find address service.
 * 
 * @author Eduardo
 *
 */
public abstract class AbstractFindAddressService implements IFindAddressService {

	/**
	 * Historic of the response time.
	 */
	private Long integrationCallTime = 0L;

	@Override
	public abstract AddressDTO findAddressByPostalCodeIntegration(String postalCode);
	
	@Override
	public abstract String getWebServiceName();

	@Override
	public AddressDTO findAdressByPostalCode(String postalCode) {

		AddressDTO addressDTO = HazelcastFindAddressUtils.getInstance().findAddressDTO(this.getCacheMapName(),
				postalCode);

		if (addressDTO == null) {
			addressDTO = findAddressByPostalCodeIntegration(postalCode);

			if (addressDTO != null) {
				HazelcastFindAddressUtils.getInstance().insertAddressDTO(this.getCacheMapName(), postalCode,
						addressDTO);
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

	@Override
	public String getCacheMapName() {
		return HazelcastConstants.GENERAL_CACHE_MAP_NAME;
	}
}
