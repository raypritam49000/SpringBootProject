package com.rest.api.services;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendSimpleEmail(String toEmail, String body, String subject) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("raypritam8483@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		
		mailSender.send(message);
		
		System.out.println("Mail Send...");
	}
	
	public void sendEmailWithAttachment(String toEmail, String body, String subject,String attachment)  {
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
			
			mimeMessageHelper.setFrom("raypritam8483@gmail.com");
			mimeMessageHelper.setTo(toEmail);
			mimeMessageHelper.setText(body);
			mimeMessageHelper.setSubject(subject);
			
			File file = new File(attachment);
			FileSystemResource fileSystem = new FileSystemResource(file);
			
			mimeMessageHelper.addAttachment(fileSystem.getFilename(), fileSystem);
			
			mailSender.send(mimeMessage);
			
			System.out.println("Mail Send ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
