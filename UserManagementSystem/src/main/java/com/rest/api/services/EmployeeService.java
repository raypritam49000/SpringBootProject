package com.rest.api.services;

import java.util.List;

import com.rest.api.entity.Employee;



public interface EmployeeService {
	public Employee createEmployee(Employee employeeDto);
	public List<Employee> getAllEmployees();
	public Employee getEmployee(Long employeeId);
	public void deleteEmployee(Long employeeId);
	public Employee updateEmployee(Long employeeId,Employee employeeDto);
	public Long getCountEmployees();


}
