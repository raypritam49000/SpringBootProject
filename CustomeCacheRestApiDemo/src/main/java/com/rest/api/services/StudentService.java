package com.rest.api.services;

import java.util.List;

import com.rest.api.dto.StudentDto;

public interface StudentService {
	public StudentDto createStudent(StudentDto studentDto);
	public List<StudentDto> getAllStudents();
	public StudentDto getStudentById(int id) throws Exception;
	public Boolean deleteStudentById(int id) throws Exception;
	public StudentDto updateStudentById(int id,StudentDto studentDto) throws Exception;
	public void chacheReload();
}
