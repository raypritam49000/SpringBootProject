package com.rest.api.services;

import java.util.List;

import com.rest.api.entity.Person;

public interface PersonService {
	public abstract boolean addPerson(Person person);

	public abstract List<Person> getAllPersons();

	public abstract Person getPerson(Long id);

	public abstract boolean deletePerson(Long id);

	public abstract boolean updatePerson(Long id, Person person);

}
