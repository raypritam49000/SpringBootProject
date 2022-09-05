package com.rest.api;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.rest.api.entity.Person;
import com.rest.api.repository.PersonRepository;

@SpringBootApplication
public class SpringBootUploadImageAndDisplayDemoApplication {
	
	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUploadImageAndDisplayDemoApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		
		try 
		{
			
			Person user = new Person();
			user.setUserName("mike5");
			user.setPassword("password");
			user.setCreationTime(new Date());
			user.setUpdatedTime(new Date());
			user.setDateofBirth(new Date());
			File file = new File("D:\\user-photos\\profile.jpg");
			byte[] picInBytes = new byte[(int) file.length()];
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(picInBytes);
			fileInputStream.close();
			user.setProfilePic(picInBytes);
			
			this.personRepository.save(user);
			    
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	   
		
	}

}
