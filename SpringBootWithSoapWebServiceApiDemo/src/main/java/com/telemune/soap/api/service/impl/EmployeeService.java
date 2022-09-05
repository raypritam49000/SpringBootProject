package com.telemune.soap.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telemune.soap.api.entity.Employee;
import com.telemune.soap.api.repository.EmployeeRepository;
import com.telemune.soap.api.service.IEmployeeService;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Employee getEmployeeById(long employeeId) {
		Employee obj = employeeRepository.findByEmployeeId(employeeId);
		return obj;

	}

	@Override
	public Employee AddEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		Employee existingemoloyee = employeeRepository.findById(employee.getEmployeeId()).get();
		if(existingemoloyee!=null) {
			existingemoloyee.setName(employee.getName());
			existingemoloyee.setAddress(employee.getAddress());
			existingemoloyee.setDepartment(employee.getDepartment());
			existingemoloyee.setPhone(employee.getPhone());
			this.employeeRepository.save(existingemoloyee);
		}
		
	}

	@Override
	public void deleteEmployee(long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

}
