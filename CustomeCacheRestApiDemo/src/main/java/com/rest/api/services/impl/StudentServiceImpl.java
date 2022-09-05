package com.rest.api.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rest.api.config.SimpleCacheManager;
import com.rest.api.dto.StudentDto;
import com.rest.api.entity.Student;
import com.rest.api.repository.StudentRepository;
import com.rest.api.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private final ModelMapper mapper;
	private final StudentRepository studentRepository;

	@Autowired
	@Qualifier("cache1")
	private SimpleCacheManager simpleCacheManager;

	public StudentServiceImpl(ModelMapper mapper, StudentRepository studentRepository) {
		super();
		this.mapper = mapper;
		this.studentRepository = studentRepository;
	}

	@Override
	public StudentDto createStudent(StudentDto studentDto) {
		Student student = this.mapper.map(studentDto, Student.class);
		Student createStudent = this.studentRepository.save(student);
		return this.mapper.map(createStudent, StudentDto.class);
	}

	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> students = this.studentRepository.findAll();
		List<StudentDto> studentList = students.stream().map(student -> this.mapper.map(student, StudentDto.class))
				.toList();
		return studentList;
	}

	@Override
	public StudentDto getStudentById(int id) throws Exception {
		Student student = this.studentRepository.findById(id)
				.orElseThrow(() -> new Exception("Student Not Found With Id : " + id));
		return this.mapper.map(student, StudentDto.class);
	}

	@Override
	public Boolean deleteStudentById(int id) throws Exception {
		Student student = this.studentRepository.findById(id)
				.orElseThrow(() -> new Exception("Student Not Found With Id : " + id));
		this.studentRepository.delete(student);
		return true;
	}

	@Override
	public StudentDto updateStudentById(int id, StudentDto studentDto) throws Exception {
		Student student = this.studentRepository.findById(id)
				.orElseThrow(() -> new Exception("Student Not Found With Id : " + id));
		Student updateStudent = null;
		if (student != null) {
			student.setCity(studentDto.getCity());
			student.setName(studentDto.getName());
			student.setSalary(studentDto.getSalary());
			updateStudent = this.studentRepository.save(student);
		}
		return this.mapper.map(updateStudent, StudentDto.class);
	}

	// @Scheduled(cron= "*/1 * * * * *")
	@Override
	public void chacheReload() {
		simpleCacheManager.clear();
		for (StudentDto student : this.getAllStudents()) {
			simpleCacheManager.put("" + student.getId(), student);
		}

	}

}
