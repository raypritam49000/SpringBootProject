package com.rest.api.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.api.config.SimpleCacheManager;
import com.rest.api.dto.StudentDto;
import com.rest.api.response.Response;
import com.rest.api.services.StudentService;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin(origins = "*")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	@Qualifier("cache1")
	private SimpleCacheManager cacheManager;

	@GetMapping("/reloadCache")
	public ResponseEntity<Response> reloadCache() {
		try {

			 cacheManager.clear();
			for (StudentDto student : this.studentService.getAllStudents()) {
				cacheManager.put("" + student.getId(), student);
			}

			return new ResponseEntity<Response>(
					new Response("Cache Reload Successfully", "SUCCESS", 200, true, Arrays.asList()), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response("Internal Server Error", "INTERNAL_SERVER_ERROR", 500, false,  Arrays.asList()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<Response> createStudent(@RequestBody StudentDto studentDto) {
		try {

			if (studentDto != null && !studentDto.getName().equals("") && !studentDto.getCity().equals("")
					&& !studentDto.getSalary().equals("")) {
				StudentDto createStudentDto = studentService.createStudent(studentDto);
				return new ResponseEntity<Response>(
						new Response("Student Created", "CREATED", 201, true, Arrays.asList(createStudentDto)),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Response>(
						new Response("All Parameter are required", "BAD_REQUEST", 401, true, Arrays.asList()),
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response("Internal Server Error", "INTERNAL_SERVER_ERROR", 500, false, Arrays.asList()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<Response> getAllStudents() {
		try {
			List<StudentDto> studentDtos = new ArrayList<>();
			if (cacheManager.size() > 0 && !cacheManager.isEmpty()) {
				for (Map.Entry<String, Object> entry : cacheManager.entrySet()) {
					StudentDto studentDto = (StudentDto) entry.getValue();
					studentDtos.add(studentDto);
				}

				if (studentDtos != null && !studentDtos.isEmpty() && studentDtos.size() > 0) {
					return new ResponseEntity<Response>(new Response("Student List", "SUCCESS", 200, true, studentDtos),
							HttpStatus.OK);
				} else {
					return new ResponseEntity<Response>(
							new Response("Student Data Not Found", "NOT_FOUND", 404, true, Arrays.asList()),
							HttpStatus.NOT_FOUND);
				}

			} else {
				List<StudentDto> studentList = studentService.getAllStudents();

				if (studentList != null && !studentList.isEmpty() && studentList.size() > 0) {
					return new ResponseEntity<Response>(new Response("Student List", "SUCCESS", 200, true, studentList),
							HttpStatus.OK);
				} else {
					return new ResponseEntity<Response>(
							new Response("Student Data Not Found", "NOT_FOUND", 404, true, Arrays.asList()),
							HttpStatus.NOT_FOUND);
				}

			}

		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response("Internal Server Error", "INTERNAL_SERVER_ERROR", 500, false, Arrays.asList()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Response> getStudentById(@PathVariable("id") int id) {
		try {

			if (cacheManager.size() > 0 && !cacheManager.isEmpty()) {

				if (cacheManager.containsKey("" + id)) {
					StudentDto studentDto = (StudentDto) cacheManager.get("" + id);

					if (studentDto != null && !studentDto.getName().equals("") && !studentDto.getCity().equals("")
							&& !studentDto.getSalary().equals("")) {
						return new ResponseEntity<Response>(
								new Response("Student List", "SUCCESS", 200, true, Arrays.asList(studentDto)),
								HttpStatus.OK);
					} else {
						return new ResponseEntity<Response>(
								new Response("Student Data Not Found", "NOT_FOUND", 404, true, Arrays.asList()),
								HttpStatus.NOT_FOUND);
					}
				} else {
					return new ResponseEntity<Response>(
							new Response("Student Data Not Found", "NOT_FOUND", 404, true, Arrays.asList()),
							HttpStatus.NOT_FOUND);
				}

			} else {
				StudentDto studentDto = studentService.getStudentById(id);

				if (studentDto != null && !studentDto.getName().equals("") && !studentDto.getCity().equals("")
						&& !studentDto.getSalary().equals("")) {
					return new ResponseEntity<Response>(
							new Response("Student List", "SUCCESS", 200, true, Arrays.asList(studentDto)),
							HttpStatus.OK);
				} else {
					return new ResponseEntity<Response>(
							new Response("Student Data Not Found", "NOT_FOUND", 404, true, Arrays.asList()),
							HttpStatus.NOT_FOUND);
				}

			}

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Response>(
					new Response("Internal Server Error", "INTERNAL_SERVER_ERROR", 500, false, Arrays.asList()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Response> deleteStudent(@PathVariable("id") int id) {
		try {

			if (id < 0) {
				return new ResponseEntity<Response>(
						new Response("Please enter id is greater than 0", "BAD_REQUEST", 401, true, Arrays.asList()),
						HttpStatus.BAD_REQUEST);
			}

			Boolean isDeleted = studentService.deleteStudentById(id);

			if (isDeleted) {
				return new ResponseEntity<Response>(
						new Response("Student Deleted", "SUCCESS", 200, true, Arrays.asList()), HttpStatus.OK);
			} else {
				return new ResponseEntity<Response>(
						new Response("Error occure when student deleted", "BAD_GATEWAY", 500, false, Arrays.asList()),
						HttpStatus.BAD_GATEWAY);
			}

		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response("Internal Server Error", "INTERNAL_SERVER_ERROR", 500, false, Arrays.asList()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Response> updateStudent(@PathVariable("id") int id,@RequestBody StudentDto studentDto) {
		try {

			if (id < 0) {
				return new ResponseEntity<Response>(
						new Response("Please enter id is greater than 0", "BAD_REQUEST", 401, true, Arrays.asList()),
						HttpStatus.BAD_REQUEST);
			}
			
			if (studentDto != null && !studentDto.getName().equals("") && !studentDto.getCity().equals("")
					&& !studentDto.getSalary().equals("")) {
				
				StudentDto updateStudentDto = studentService.updateStudentById(id, studentDto);
				
				return new ResponseEntity<Response>(
						new Response("Student Updated", "CREATED", 201, true, Arrays.asList(updateStudentDto)),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Response>(
						new Response("All Parameter are required", "BAD_REQUEST", 401, true, Arrays.asList()),
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<Response>(
					new Response("Internal Server Error", "INTERNAL_SERVER_ERROR", 500, false, Arrays.asList()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
