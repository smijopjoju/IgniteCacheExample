package my.apache.ignite.examples.config;

import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

import my.apache.ignite.examples.model.Organization;
import my.apache.ignite.examples.model.Person;

public class IgniteCacheConfig {

	
	
	public static IgniteConfiguration getIgniteCacheConfig() throws Exception {
		
		IgniteConfiguration igniteConfig = new IgniteConfiguration();
    	
    	CacheConfiguration<Long, Organization> orgCacheCfg = new CacheConfiguration<>(CacheConstant.ORG_CACHE);

        orgCacheCfg.setCacheMode(CacheMode.PARTITIONED); // Default.
        orgCacheCfg.setIndexedTypes(Long.class, Organization.class);

        CacheConfiguration<AffinityKey<Long>, Person> personCacheCfg =
            new CacheConfiguration<>(CacheConstant.PERSON_CACHE);

        personCacheCfg.setCacheMode(CacheMode.PARTITIONED); // Default.
        personCacheCfg.setIndexedTypes(AffinityKey.class, Person.class);
    	
        igniteConfig.setCacheConfiguration(orgCacheCfg,personCacheCfg);
		
        return igniteConfig;
	}
	
	
}
