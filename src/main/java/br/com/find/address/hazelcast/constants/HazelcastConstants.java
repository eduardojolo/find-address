package br.com.find.address.hazelcast.constants;

/**
 * Constant class for the embedded hazelcast instance manipulation.
 * 
 * @author Eduardo
 *
 */
public final class HazelcastConstants {
	
	/**
	 * Local hazelcast instance.
	 */
	public static final String LOCAL_INSTANCE_NAME = "local-cache";
	
	/**
	 * Brazil cache map access key name.
	 */
	public static final String BRAZIL_CACHE_MAP_NAME = "brazil-map";
	
	/**
	 * Canada cache map access key name.
	 */
	public static final String CANADA_CACHE_MAP_NAME = "canada-map";

	/**
	 * Private constructor for final class.
	 */
	private HazelcastConstants() {
	}
}
