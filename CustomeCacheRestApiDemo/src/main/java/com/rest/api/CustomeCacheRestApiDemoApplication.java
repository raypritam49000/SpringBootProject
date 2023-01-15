package com.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.rest.api.config.SimpleCacheManager;
import com.rest.api.dto.StudentDto;
import com.rest.api.services.StudentService;

@EnableScheduling
@SpringBootApplication
public class CustomeCacheRestApiDemoApplication implements ApplicationRunner {

	@Autowired
	private StudentService studentService;

	@Autowired
	@Qualifier("cache1")
	private SimpleCacheManager simpleCacheManager;

	@Autowired
	@Qualifier("cache2")
	private SimpleCacheManager simpleCacheManager1;

	@Autowired
	@Qualifier("cache3")
	private SimpleCacheManager simpleCacheManager2;

	public static void main(String[] args) {
		SpringApplication.run(CustomeCacheRestApiDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		for (StudentDto student : this.studentService.getAllStudents()) {
			simpleCacheManager.put("" + student.getId(), student);
		}

	}

}
