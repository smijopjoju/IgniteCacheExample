package my.apache.ignite.examples.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import my.apache.ignite.examples.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService service;
	
	@GetMapping("/scanQuery/{salary}")
	public List scanQuery(@PathVariable Double salary) {
		return service.scanQuery(salary);
	}
	
	@GetMapping("/textQuery/{searchStr}")
	public List textQuery(@PathVariable String searchStr ) {
		return service.textQuery(searchStr);
	}
	
	@GetMapping("/sqlQuery/{salary}")
	public List sqlQuery(@PathVariable Double salary) {
		return service.sqlQuery(salary);
	}
	
	/*public boolean savePerson() {
		
	}*/
	
}
