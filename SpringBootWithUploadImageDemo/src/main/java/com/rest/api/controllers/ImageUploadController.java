package com.rest.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rest.api.helper.FileUploadHelper;

@RestController
public class ImageUploadController {
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		 System.out.println(file.getOriginalFilename());
		 System.out.println(file.getSize());
		 System.out.println(file.getContentType());
		 System.out.println(file.getName());
		 
		 try 
		 {
			
			 if(file.isEmpty()) {
				 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
			 }
			 
			 if(file.getContentType().equals("image/jpg")) {
				 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only Jpg content type allow");
			 }
			 
			 Boolean isUploaded = fileUploadHelper.uploadFile(file);
			 
			 if(isUploaded) {
				 return ResponseEntity.ok("File is Successfully Uploaded");
			 }
			 
		 } 
		 catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		 
		 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong try again !!!");
	}

}
