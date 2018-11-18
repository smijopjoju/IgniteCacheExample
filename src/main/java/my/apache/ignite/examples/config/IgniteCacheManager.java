package my.apache.ignite.examples.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.affinity.AffinityKey;

import my.apache.ignite.examples.model.Organization;
import my.apache.ignite.examples.model.Person;

public enum IgniteCacheManager {

	INSTANCE;
	
	private Ignite ignite;
	
	private IgniteCache<Long, Organization> orgCache;
	
	private IgniteCache<AffinityKey<Long>, Person> perCache;
	
	public void init() throws Exception {
		ignite = Ignition.start(IgniteCacheConfig.getIgniteCacheConfig());
		
		orgCache = ignite.getOrCreateCache(CacheConstant.ORG_CACHE);
		perCache = ignite.getOrCreateCache(CacheConstant.PERSON_CACHE);
		
		
	}



	public IgniteCache<Long, Organization> getOrgCache() {
		return orgCache;
	}



	public IgniteCache<AffinityKey<Long>, Person> getPerCache() {
		return perCache;
	}
	
	public void initRecords() {

        // Organizations.
        Organization org1 = new Organization("ApacheIgnite");
        Organization org2 = new Organization("Other");
		
		if( orgCache != null ) {
			 // Clear cache before running the example.
	        orgCache.clear();



	        orgCache.put(org1.id(), org1);
	        orgCache.put(org2.id(), org2);
		}

       

        if( perCache != null ) {
        	 // Clear caches before running the example.
        	perCache.clear();

            // People.
            Person p1 = new Person(org1, "John", "Doe", 2000, "John Doe has Master Degree.");
            Person p2 = new Person(org1, "Jane", "Doe", 1000, "Jane Doe has Bachelor Degree.");
            Person p3 = new Person(org2, "John", "Smith", 1000, "John Smith has Bachelor Degree.");
            Person p4 = new Person(org2, "Jane", "Smith", 2000, "Jane Smith has Master Degree.");

            // Note that in this example we use custom affinity key for Person objects
            // to ensure that all persons are collocated with their organizations.
            perCache.put(p1.key(), p1);
            perCache.put(p2.key(), p2);
            perCache.put(p3.key(), p3);
            perCache.put(p4.key(), p4);
        }

	}
	
}
