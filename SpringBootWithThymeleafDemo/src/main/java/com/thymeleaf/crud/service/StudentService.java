package com.thymeleaf.crud.service;

import java.util.List;

import com.thymeleaf.crud.dto.StudentDto;
import com.thymeleaf.crud.entity.Student;

public interface StudentService {
	public boolean createStudent(StudentDto studentDto);
	public List<StudentDto> getAllStudents();
	public Student getStudent(Long id);
	public Boolean deleteStudent(Long id);
	public Boolean updateStudent(Long id,StudentDto student);
}
