package com.rest.api.services;

import java.util.List;

import com.rest.api.model.Employee;

public interface EmployeeService {
	public boolean createEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public Employee getEmployee(Long id);
	public Boolean deleteEmployee(Long id);
	public Boolean updateEmployee(Long id,Employee employee);
}
