package com.rest.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.entity.Employee;
import com.rest.api.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employeeDto) {
		return employeeRepository.save(employeeDto);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.employeeRepository.findAll();
	}

	@Override
	public Employee getEmployee(Long employeeId) {
		return this.employeeRepository.findById(employeeId).get();
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		this.employeeRepository.deleteById(employeeId);
	}

	@Override
	public Employee updateEmployee(Long employeeId, Employee employeeDto) {
		Employee existingEmployee = this.employeeRepository.findById(employeeId).get();
		Employee updateEmp = null;
		if (existingEmployee != null) {
			existingEmployee.setName(employeeDto.getName());
			existingEmployee.setCity(employeeDto.getCity());
			existingEmployee.setEmail(employeeDto.getEmail());
			existingEmployee.setSalary(employeeDto.getSalary());
			updateEmp = this.employeeRepository.save(existingEmployee);
		}
		return updateEmp;
	}

	@Override
	public Long getCountEmployees() {
		return this.employeeRepository.count();
	}

}
