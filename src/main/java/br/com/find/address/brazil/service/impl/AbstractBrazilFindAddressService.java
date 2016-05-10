package br.com.find.address.brazil.service.impl;

import br.com.find.address.hazelcast.constants.HazelcastConstants;
import br.com.find.address.service.abs.AbstractFindAddressService;

/**
 * Abstract class for the Find Address Service implementation for Brazilian
 * addresses.
 * 
 * @author Eduardo
 *
 */
public abstract class AbstractBrazilFindAddressService extends AbstractFindAddressService {

	@Override
	public String getCacheMapName() {
		return HazelcastConstants.BRAZIL_CACHE_MAP_NAME;
	}
}
