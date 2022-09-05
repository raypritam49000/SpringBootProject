package com.telemune.soap.api.endpoint;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.telemune.soap.api.entity.Employee;
import com.telemune.soap.api.interfaces.AddEmployeeRequest;
import com.telemune.soap.api.interfaces.AddEmployeeResponse;
import com.telemune.soap.api.interfaces.DeleteEmployeeRequest;
import com.telemune.soap.api.interfaces.DeleteEmployeeResponse;
import com.telemune.soap.api.interfaces.EmployeeInfo;
import com.telemune.soap.api.interfaces.GetEmployeeByIdRequest;
import com.telemune.soap.api.interfaces.GetEmployeeResponse;
import com.telemune.soap.api.interfaces.ServiceStatus;
import com.telemune.soap.api.interfaces.UpdateEmployeeRequest;
import com.telemune.soap.api.interfaces.UpdateEmployeeResponse;
import com.telemune.soap.api.service.impl.EmployeeService;

@Endpoint
public class EmployeeEndpoint {

	private static final String NAMESPACE_URI = "http://interfaces.soap.springboot.vkakarla.com";

	@Autowired
	private EmployeeService employeeService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addEmployeeRequest")
	@ResponsePayload
	public AddEmployeeResponse addEmployee(@RequestPayload AddEmployeeRequest request) {
		AddEmployeeResponse response = new AddEmployeeResponse();
		ServiceStatus serviceStatus = new ServiceStatus();

		Employee employee = new Employee();
		BeanUtils.copyProperties(request.getEmployeeInfo(), employee);
		Employee createEmployee = employeeService.AddEmployee(employee);

		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Content Added Successfully");
		response.setServiceStatus(serviceStatus);
		response.setEmployeeInfo(new EmployeeInfo(createEmployee.getEmployeeId(), createEmployee.getName(),
				createEmployee.getDepartment(), createEmployee.getPhone(), createEmployee.getAddress()));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEmployeeByIdRequest")
	@ResponsePayload
	public GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeByIdRequest request) {
		GetEmployeeResponse response = new GetEmployeeResponse();
		EmployeeInfo employeeInfo = new EmployeeInfo();
		BeanUtils.copyProperties(employeeService.getEmployeeById(request.getEmployeeId()), employeeInfo);
		response.setEmployeeInfo(employeeInfo);
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateEmployeeRequest")
	@ResponsePayload
	public UpdateEmployeeResponse updateEmployee(@RequestPayload UpdateEmployeeRequest request) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(request.getEmployeeInfo(), employee);

	    employeeService.updateEmployee(employee);

		ServiceStatus serviceStatus = new ServiceStatus();
		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Content Updated Successfully");

		UpdateEmployeeResponse response = new UpdateEmployeeResponse();
		response.setServiceStatus(serviceStatus);
	
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteEmployeeRequest")
	@ResponsePayload
	public DeleteEmployeeResponse deleteEmployee(@RequestPayload DeleteEmployeeRequest request) {
		employeeService.deleteEmployee(request.getEmployeeId());
		ServiceStatus serviceStatus = new ServiceStatus();

		serviceStatus.setStatus("SUCCESS");
		serviceStatus.setMessage("Content Deleted Successfully");
		DeleteEmployeeResponse response = new DeleteEmployeeResponse();
		response.setServiceStatus(serviceStatus);
		return response;
	}

}
