package com.user.management.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.user.management.system.entity.Student;
import com.user.management.system.repository.StudentRepository;

@SpringBootApplication
public class SpringBootWithCustomeRepositoryDemoApplication implements CommandLineRunner{
	
	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithCustomeRepositoryDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		studentRepository.persist(new Student("Pritam Ray", "Ropar","50000"));
		System.out.println(studentRepository.getAllStudents());
	}

}
