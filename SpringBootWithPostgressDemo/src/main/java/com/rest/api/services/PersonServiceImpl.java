package com.rest.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.entity.Person;
import com.rest.api.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public boolean addPerson(Person person) {
		this.personRepository.save(person);
		return true;
	}

	@Override
	public List<Person> getAllPersons() {
		return this.personRepository.findAll();
	}

	@Override
	public Person getPerson(Long id) {
		return this.personRepository.findById(id).get();
	}

	@Override
	public boolean deletePerson(Long id) {
		Person person = this.personRepository.findById(id).get();
		if (person != null) {
			this.personRepository.delete(person);
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePerson(Long id, Person person) {
		Person existingPerson = this.personRepository.findById(id).get();
		if (person != null) {
			existingPerson.setCity(person.getCity());
			existingPerson.setName(person.getName());
			existingPerson.setSalary(person.getSalary());
			this.personRepository.save(existingPerson);
			return true;
		}
		return false;
	}

}
