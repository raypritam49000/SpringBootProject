package com.rest.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.entity.Person;
import com.rest.api.services.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@PostMapping
	public ResponseEntity<?> createPerson(@RequestBody Person person){
		try 
		{
			Boolean isSaved = this.personService.addPerson(person);
			
			if(isSaved) {
				return new ResponseEntity<>(Map.of("message","Add Person Successfully","status",HttpStatus.OK,"statusCode",201),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(Map.of("message","All fields are required !! ","status",HttpStatus.BAD_REQUEST,"statusCode",400),HttpStatus.BAD_REQUEST);
			}
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(Map.of("message",e.getMessage(),"status",HttpStatus.INTERNAL_SERVER_ERROR,"statusCode",501),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@GetMapping
	public ResponseEntity<?> getAllPersons(){
		try 
		{
			List<Person> persons= this.personService.getAllPersons();
			
			if(persons!=null && persons.size()>0) {
				return new ResponseEntity<>(Map.of("message","Persons Lists","body",persons,"status",HttpStatus.OK,"statusCode",200),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(Map.of("message","Data Not Found !! ","status",HttpStatus.NOT_FOUND,"statusCode",404),HttpStatus.NOT_FOUND);
			}
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(Map.of("message",e.getMessage(),"status",HttpStatus.INTERNAL_SERVER_ERROR,"statusCode",501),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPerson(@PathVariable("id") Long id){
		try 
		{
			Person person= this.personService.getPerson(id);
			
			if(person!=null && !person.getName().equals("")) {
				return new ResponseEntity<>(Map.of("message","Persons Lists","body",person,"status",HttpStatus.OK,"statusCode",200),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(Map.of("message","Data Not Found !! ","status",HttpStatus.NOT_FOUND,"statusCode",404),HttpStatus.NOT_FOUND);
			}
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(Map.of("message",e.getMessage(),"status",HttpStatus.INTERNAL_SERVER_ERROR,"statusCode",501),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable("id") Long id){
		try 
		{
			Boolean isDeleted = this.personService.deletePerson(id);
			
			if(isDeleted) {
				return new ResponseEntity<>(Map.of("message","Person Deleted","status",HttpStatus.OK,"statusCode",204),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(Map.of("message","Error Occur while Person Deletd !! ","status",HttpStatus.BAD_REQUEST,"statusCode",400),HttpStatus.BAD_REQUEST);
			}
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(Map.of("message",e.getMessage(),"status",HttpStatus.INTERNAL_SERVER_ERROR,"statusCode",501),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePerson(@PathVariable("id") Long id,@RequestBody Person person){
		try 
		{
			Boolean isDeleted = this.personService.updatePerson(id, person);
			
			if(isDeleted) {
				return new ResponseEntity<>(Map.of("message","Person Updated","status",HttpStatus.OK,"statusCode",202),HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(Map.of("message","Error Occur while Person Updated !! ","status",HttpStatus.BAD_REQUEST,"statusCode",400),HttpStatus.BAD_REQUEST);
			}
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(Map.of("message",e.getMessage(),"status",HttpStatus.INTERNAL_SERVER_ERROR,"statusCode",501),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
