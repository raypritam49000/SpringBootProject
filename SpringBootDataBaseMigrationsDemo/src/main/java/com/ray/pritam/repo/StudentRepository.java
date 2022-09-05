package com.ray.pritam.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ray.pritam.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	Optional<Student> findStudentByEmail(String email);
	void deleteStudentByEmail(String email);
}