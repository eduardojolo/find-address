package br.com.find.address.canada.service.impl;

import br.com.find.address.hazelcast.constants.HazelcastConstants;
import br.com.find.address.service.abs.AbstractFindAddressService;

/**
 * Abstract class for the Find Address Service implementation for Canadian
 * addresses.
 * 
 * @author Eduardo
 *
 */
public abstract class AbstractCanadaFindAddressService extends AbstractFindAddressService {

	@Override
	public String getCacheMapName() {
		return HazelcastConstants.CANADA_CACHE_MAP_NAME;
	}

}
