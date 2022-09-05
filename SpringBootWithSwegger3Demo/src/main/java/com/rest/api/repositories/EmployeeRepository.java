package com.rest.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.api.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
