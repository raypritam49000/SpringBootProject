package com.telemune.soap.api.client.service;

import com.telemune.soap.api.client.interfaces.AddEmployeeRequest;
import com.telemune.soap.api.client.interfaces.AddEmployeeResponse;
import com.telemune.soap.api.client.interfaces.DeleteEmployeeRequest;
import com.telemune.soap.api.client.interfaces.DeleteEmployeeResponse;
import com.telemune.soap.api.client.interfaces.GetEmployeeByIdRequest;
import com.telemune.soap.api.client.interfaces.GetEmployeeResponse;
import com.telemune.soap.api.client.interfaces.UpdateEmployeeRequest;
import com.telemune.soap.api.client.interfaces.UpdateEmployeeResponse;

public interface EmployeeService {
	public AddEmployeeResponse addEmployee(AddEmployeeRequest request);
	public GetEmployeeResponse findEmployeeById(GetEmployeeByIdRequest employeeByIdRequest);
	public DeleteEmployeeResponse deleteEmployeeById(DeleteEmployeeRequest deleteEmployeeRequest);
	public UpdateEmployeeResponse updateEmployeeById(UpdateEmployeeRequest updateEmployeeRequest);
}
