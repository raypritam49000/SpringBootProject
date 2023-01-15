package com.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.api.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
