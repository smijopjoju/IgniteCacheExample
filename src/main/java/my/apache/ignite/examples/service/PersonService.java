package my.apache.ignite.examples.service;

import java.util.ArrayList;
import java.util.List;

import javax.cache.Cache;
import javax.cache.Cache.Entry;

import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.ScanQuery;
import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.cache.query.TextQuery;
import org.apache.ignite.lang.IgniteBiPredicate;
import org.springframework.stereotype.Service;

import my.apache.ignite.examples.config.IgniteCacheManager;
import my.apache.ignite.examples.model.Person;

@Service
public class PersonService {

	public List scanQuery(Double salary) {		

		ScanQuery<AffinityKey<Long>, Person> scan = new ScanQuery<>(
	            new IgniteBiPredicate<AffinityKey<Long>, Person>() {
	                @Override public boolean apply(AffinityKey<Long> key, Person person) {
	                    return person.salary <= salary;
	                }
	            }
	        );
		
		Iterable<Entry<AffinityKey<Long>, Person>> persons = IgniteCacheManager.INSTANCE.getPerCache().query(scan).getAll();
        List personsList = new ArrayList();
        for(Entry personsEntry : persons) {
        	personsList.add(personsEntry.getValue());
        }
		
        return personsList;
	}
	
	public List textQuery(String searchStr) {
		 //  Query for all people with "Master Degree" in their resumes.
        QueryCursor<Cache.Entry<Long, Person>> searchContent =
            IgniteCacheManager.INSTANCE.getPerCache().query(new TextQuery<Long, Person>(Person.class, searchStr));
        
        Iterable<Entry<Long, Person>> persons = searchContent.getAll();
        List personsList = new ArrayList();
        for(Entry personsEntry : persons) {
        	personsList.add(personsEntry.getValue());
        }
        
        return personsList;
	}
	
	public List sqlQuery(Double salary) {
		

		SqlQuery sql = new SqlQuery(Person.class, "salary > ?");
		List personsList = new ArrayList();
		// Find only persons earning more than 1,000.
		try (QueryCursor<Entry<Long, Person>> cursor = IgniteCacheManager.INSTANCE.getPerCache().query(sql.setArgs(salary))) {
		  for (Entry<Long, Person> e : cursor) {
			  personsList.add(e.getValue());
		  }		    		 
		}
		
		return personsList;
	}
	
}
