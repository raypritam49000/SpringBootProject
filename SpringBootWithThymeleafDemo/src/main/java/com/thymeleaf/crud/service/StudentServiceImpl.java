package com.thymeleaf.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymeleaf.crud.dto.StudentDto;
import com.thymeleaf.crud.entity.Student;
import com.thymeleaf.crud.repository.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepo studentRepo;

	@Override
	public boolean createStudent(StudentDto studentDto) {
		Student student = new Student();
		BeanUtils.copyProperties(studentDto, student);
		System.out.println(student);
		this.studentRepo.save(student);
		return true;
	}

	@Override
	public List<StudentDto> getAllStudents() {
		StudentDto studentDto = null;		
		List<Student> students = studentRepo.findAll();
		List<StudentDto> studentDtos = new ArrayList<StudentDto>();
		 
		for(Student student : students) {
			studentDto = new StudentDto();
			BeanUtils.copyProperties(student, studentDto);
			studentDtos.add(studentDto);
		}
		return studentDtos;
	}

	@Override
	public Student getStudent(Long id) {
		return this.studentRepo.findById(id).get();
	}

	@Override
	public Boolean deleteStudent(Long id) {
		this.studentRepo.deleteById(id);
		return true;
	}

	@Override
	public Boolean updateStudent(Long id, StudentDto studentDto) {
	    Student student = new Student();
	    BeanUtils.copyProperties(studentDto, student);
	    Student existingStudent =  this.studentRepo.findById(id).get();
	    if(existingStudent!=null) {
	    	existingStudent.setCity(student.getCity());
	    	existingStudent.setSalary(student.getSalary());
	    	existingStudent.setName(student.getName());
	    	this.studentRepo.save(existingStudent);
	    	return true;
	    }
		return false;
	}

}
