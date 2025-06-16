package com.pradeepdemo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	Repository repo;
	@GetMapping("/find")
	public List<tablecreation> findll(){
		List<tablecreation>find=repo.findAll();
		return find;
	}
	@GetMapping("/byid/{id}")
	public ResponseEntity<tablecreation> byid(@PathVariable int id) {
		return repo.findById(id)
				.map(ResponseEntity::ok)
	               .orElse(ResponseEntity.notFound().build());
	
		
	}
	@PutMapping("/updat/{id}")
	public tablecreation update(@PathVariable int id,@RequestBody tablecreation creation) {
		return repo.findById(id)
				.map(emp ->{

					emp.setName(creation.getName());
					return repo.save(emp);
					
				}
				)
				.orElseGet(()->{
					creation.setId(id);
					return repo.save(creation);
					
					
				}
				);
		
	}
	@PostMapping("/create")
	public tablecreation create(@RequestBody tablecreation creation) {
		return repo.save(creation);
	}
	

}
