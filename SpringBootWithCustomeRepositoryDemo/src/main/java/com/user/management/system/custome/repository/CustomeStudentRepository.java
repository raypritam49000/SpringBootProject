package com.user.management.system.custome.repository;

import java.util.List;

import com.user.management.system.entity.Student;

public interface CustomeStudentRepository {
	public List<Student> getAllStudents();
	public void persist(Student student);
}
