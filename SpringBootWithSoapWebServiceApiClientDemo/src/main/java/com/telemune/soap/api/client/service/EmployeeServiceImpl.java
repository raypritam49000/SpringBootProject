package com.telemune.soap.api.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.telemune.soap.api.client.interfaces.AddEmployeeRequest;
import com.telemune.soap.api.client.interfaces.AddEmployeeResponse;
import com.telemune.soap.api.client.interfaces.DeleteEmployeeRequest;
import com.telemune.soap.api.client.interfaces.DeleteEmployeeResponse;
import com.telemune.soap.api.client.interfaces.GetEmployeeByIdRequest;
import com.telemune.soap.api.client.interfaces.GetEmployeeResponse;
import com.telemune.soap.api.client.interfaces.UpdateEmployeeRequest;
import com.telemune.soap.api.client.interfaces.UpdateEmployeeResponse;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Override
	public AddEmployeeResponse addEmployee(AddEmployeeRequest request) {
		AddEmployeeResponse addEmployeeResponse = (AddEmployeeResponse) webServiceTemplate
				.marshalSendAndReceive("http://localhost:9999/vkakarlaService", request);
		return addEmployeeResponse;
	}

	@Override
	public GetEmployeeResponse findEmployeeById(GetEmployeeByIdRequest employeeByIdRequest) {
		GetEmployeeResponse getEmployeeResponse = (GetEmployeeResponse) webServiceTemplate
				.marshalSendAndReceive("http://localhost:9999/vkakarlaService", employeeByIdRequest);
		return getEmployeeResponse;
	}

	@Override
	public DeleteEmployeeResponse deleteEmployeeById(DeleteEmployeeRequest deleteEmployeeRequest) {
		DeleteEmployeeResponse deleteEmployeeResponse = (DeleteEmployeeResponse) webServiceTemplate
				.marshalSendAndReceive("http://localhost:9999/vkakarlaService", deleteEmployeeRequest);
		return deleteEmployeeResponse;
	}

	@Override
	public UpdateEmployeeResponse updateEmployeeById(UpdateEmployeeRequest updateEmployeeRequest) {
		UpdateEmployeeResponse UpdateEmployeeResponse = (UpdateEmployeeResponse) webServiceTemplate
				.marshalSendAndReceive("http://localhost:9999/vkakarlaService", updateEmployeeRequest);
		return UpdateEmployeeResponse;
	}

}
