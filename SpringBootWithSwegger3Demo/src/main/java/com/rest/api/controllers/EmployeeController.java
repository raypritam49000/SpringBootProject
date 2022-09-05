package com.rest.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.model.Employee;
import com.rest.api.services.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/rest/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// http://localhost:9999/rest/api/employees/getEmployee/{id}
	@Operation(summary = "Get a Employee by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Employee", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) })
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<?> getEmployee(@Parameter(description = "id of book to be searched") @PathVariable Long id) {
		try {
			Employee employee = null;

			if (id != null) {
				employee = this.employeeService.getEmployee(id);

				if (employee != null && !employee.getName().equals("")) {
					return ResponseEntity.ok(Map.of("status", HttpStatus.OK, "data", employee, "success", true));
				} else {
					return ResponseEntity.ok(Map.of("status", HttpStatus.NOT_FOUND, "data", employee, "success", true));

				}
			} else {
				return ResponseEntity.ok(Map.of("status", HttpStatus.BAD_REQUEST, "data", employee, "success", false));
			}
		} catch (Exception e) {
			return ResponseEntity
					.ok(Map.of("status", HttpStatus.BAD_REQUEST, "message", e.getMessage(), "success", false));
		}
	}

	// http://localhost:9999/rest/api/employees/getAllEmployees
	@Operation(summary = "Get All Employees")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Employee", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) })
	@GetMapping("/getAllEmployees")
	public ResponseEntity<?> getAllEmployees() {
		try {
			List<Employee> employees = null;

			employees = this.employeeService.getAllEmployees();

			if (employees != null && !employees.isEmpty() && employees.size() > 0) {
				return ResponseEntity.ok(Map.of("status", HttpStatus.OK, "data", employees, "success", true));
			} else {
				return ResponseEntity.ok(Map.of("status", HttpStatus.NOT_FOUND, "data", employees, "success", true));

			}
		} catch (Exception e) {
			return ResponseEntity
					.ok(Map.of("status", HttpStatus.BAD_REQUEST, "message", e.getMessage(), "success", false));
		}
	}

	@Operation(summary = "Create New Employee")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Employee", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) })
	@PostMapping("/createEmployee")
	public ResponseEntity<?> createEmployee(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody Employee employee) {
		try {
			if (employee != null && !employee.getName().equals("")) {
				Boolean result = this.employeeService.createEmployee(employee);

				if (result) {
					return ResponseEntity
							.ok(Map.of("status", HttpStatus.OK, "message", "Employee are created", "success", true));
				} else {
					return ResponseEntity.ok(Map.of("status", HttpStatus.INTERNAL_SERVER_ERROR, "message",
							"Error Occur while Employee are created", "success", false));
				}
			} else {
				return ResponseEntity.ok(Map.of("status", HttpStatus.BAD_REQUEST, "data", employee, "success", false));
			}
		} catch (Exception e) {
			return ResponseEntity
					.ok(Map.of("status", HttpStatus.BAD_REQUEST, "message", e.getMessage(), "success", false));
		}

	}

	@Operation(summary = "Delete Employee by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Employee", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) })
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<?> deleteEmployee(
			@Parameter(description = "id of book to be searched") @PathVariable Long id) {
		try {
			Boolean result = null;

			if (id != null) {
				result = this.employeeService.deleteEmployee(id);

				if (result) {
					return ResponseEntity
							.ok(Map.of("status", HttpStatus.OK, "message", "Employee Deleted", "success", true));
				} else {
					return ResponseEntity.ok(Map.of("status", HttpStatus.INTERNAL_SERVER_ERROR, "message",
							"Error can occure While Employee Deleted", "success", false));

				}
			} else {
				return ResponseEntity.ok(Map.of("status", HttpStatus.BAD_REQUEST, "success", false));
			}
		} catch (Exception e) {
			return ResponseEntity
					.ok(Map.of("status", HttpStatus.BAD_REQUEST, "message", e.getMessage(), "success", false));
		}
	}

	@Operation(summary = "Update Employee By Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Employee", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Employee not found", content = @Content) })
	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<?> updateEmployee(@Parameter(description = "id of book to be searched") @PathVariable Long id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody Employee employee) {

		try {
			if (id != null && employee != null && !employee.getName().equals("")) {
				Boolean result = this.employeeService.updateEmployee(id, employee);

				if (result) {
					return ResponseEntity.ok(Map.of("status", HttpStatus.OK, "message", "Employee are updated", "success", true));
				} else {
					return ResponseEntity.ok(Map.of("status", HttpStatus.INTERNAL_SERVER_ERROR, "message","Error Occur while Employee are updated", "success", false));
				}
			} else {
				return ResponseEntity.ok(Map.of("status", HttpStatus.BAD_REQUEST, "data", employee, "success", false));
			}
		} catch (Exception e) {
			return ResponseEntity
					.ok(Map.of("status", HttpStatus.BAD_REQUEST, "message", e.getMessage(), "success", false));
		}

	}

}