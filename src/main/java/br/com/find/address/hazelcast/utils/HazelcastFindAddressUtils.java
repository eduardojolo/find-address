package br.com.find.address.hazelcast.utils;

import java.util.HashMap;
import java.util.Map;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import br.com.find.address.dto.AddressDTO;
import br.com.find.address.hazelcast.constants.HazelcastConstants;

/**
 * Hazelcast utils for find address caches.
 * 
 * @author Eduardo
 *
 */
public class HazelcastFindAddressUtils {

	/**
	 * Local instance.
	 */
	private static final HazelcastFindAddressUtils utilsInstance;

	/**
	 * Hazelcast instance.
	 */
	private static HazelcastInstance hazelcastInstance;

	/**
	 * Map with all the accessed hazelcast maps names.
	 */
	private static Map<String, IMap<String, AddressDTO>> cacheMaps;

	static {
		utilsInstance = new HazelcastFindAddressUtils();

		hazelcastInstance = Hazelcast.getHazelcastInstanceByName(HazelcastConstants.LOCAL_INSTANCE_NAME);

		cacheMaps = new HashMap<String, IMap<String, AddressDTO>>();
	}

	/**
	 * Get the hazelcast utils instance.
	 * 
	 * @return HazelcastFindAddressUtils
	 */
	public static synchronized HazelcastFindAddressUtils getInstance() {
		return utilsInstance;
	}

	/**
	 * Find address dto in the given cache map for the given postal code.
	 * 
	 * @param cacheMapName Cache map name
	 * @param postalCode Postal code
	 * @return AddressDTO
	 */
	public AddressDTO findAddressDTO(String cacheMapName, String postalCode) {
		IMap<String, AddressDTO> map = getCacheIMap(cacheMapName);
		return map.get(postalCode);
	}

	/**
	 * Insert a new address for a postal code in the specific cache.
	 * 
	 * @param cacheMapName Cache map name
	 * @param postalCode Postal code
	 * @param addressDTO Address
	 */
	public void insertAddressDTO(String cacheMapName, String postalCode, AddressDTO addressDTO) {
		IMap<String, AddressDTO> map = getCacheIMap(cacheMapName);
		map.put(postalCode, addressDTO);
	}

	/**
	 * Get the cache map in the hazelcast instance.
	 * 
	 * @param cacheMapName Cache map name
	 * @return IMap
	 */
	private IMap<String, AddressDTO> getCacheIMap(String cacheMapName) {
		IMap<String, AddressDTO> map = cacheMaps.get(cacheMapName);

		if (map == null) {
			map = hazelcastInstance.getMap(cacheMapName);

			cacheMaps.put(cacheMapName, map);
		}

		return map;
	}
}
