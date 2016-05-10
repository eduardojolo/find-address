package br.com.find.address.hazelcast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.config.MaxSizeConfig.MaxSizePolicy;

import br.com.find.address.hazelcast.constants.HazelcastConstants;

/**
 * Embedded hazelcast configuration.
 * 
 * @author Eduardo
 *
 */
@Configuration
public class HazelcastConfiguration {

	/**
	 * Configuration method.
	 * 
	 * @return Config
	 */
	@Bean
	public Config config() {
		
		Config config =  new Config(HazelcastConstants.LOCAL_INSTANCE_NAME).addMapConfig(
				new MapConfig()
					.setName(HazelcastConstants.BRAZIL_CACHE_MAP_NAME)
					.setEvictionPolicy(EvictionPolicy.LFU)
					.setMaxSizeConfig(new MaxSizeConfig().setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_PERCENTAGE).setSize(15))); 
		
		return config;
	}
}
