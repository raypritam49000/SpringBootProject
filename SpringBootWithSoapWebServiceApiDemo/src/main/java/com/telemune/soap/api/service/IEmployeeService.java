package com.telemune.soap.api.service;

import com.telemune.soap.api.entity.Employee;

public interface IEmployeeService {

	Employee AddEmployee(Employee employee);

	Employee getEmployeeById(long employeeId);

	void updateEmployee(Employee employee);

	void deleteEmployee(long employeeId);
}
