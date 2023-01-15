package com.rest.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.model.Employee;
import com.rest.api.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public boolean createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.employeeRepository.findAll();
	}

	@Override
	public Employee getEmployee(Long id) {
		return this.employeeRepository.findById(id).get();
	}

	@Override
	public Boolean deleteEmployee(Long id) {
		Employee employee = this.employeeRepository.findById(id).get();
		if (employee != null && !employee.getName().equals("")) {
			this.employeeRepository.delete(employee);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateEmployee(Long id, Employee employee) {
		Employee existingEmployee = this.employeeRepository.findById(id).get();
		if (employee != null && !employee.getName().equals("")) {
			existingEmployee.setName(employee.getName());
			existingEmployee.setSalary(employee.getSalary());
			existingEmployee.setDesignation(employee.getDesignation());
			this.employeeRepository.save(existingEmployee);
			return true;
		}
		return false;
	}

}
