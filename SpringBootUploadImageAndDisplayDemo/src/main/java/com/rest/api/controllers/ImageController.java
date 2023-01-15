package com.rest.api.controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rest.api.entity.User;
import com.rest.api.repository.UserRepository;
import com.rest.api.utils.FileUploadUtil;

@RestController
public class ImageController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/upload-image")
	public ResponseEntity<?> saveImage(@RequestParam("file") MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		System.out.println(fileName);

		User user = new User();
		user.setName("Pritam Ray");
		user.setCity("Ropar");
		user.setSalary(45000D);
		user.setPhotos(fileName);

		User savedUser = userRepository.save(user);

		String uploadDir = "D:\\user-photos\\";

		try {
			FileUploadUtil.saveFile(uploadDir, fileName, file);
		} catch (Exception e) {
			return ResponseEntity.ok(Map.of("message", e.getMessage(), "success", false));
		}

		return ResponseEntity.ok(Map.of("message", "File is uploaded Successfully", "success", true));
	}

	@GetMapping("/image/{userId}")
	public ResponseEntity<?> getPhoto(@PathVariable("userId") Integer userId) {
		try {
			User user = this.userRepository.findById(userId).get();
			File file = new File("D:\\user-photos\\" + user.getPhotos());
			byte[] image = FileUtils.readFileToByteArray(file);
			// return ResponseEntity.ok(Map.of("message", image, "success", true));
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
		} catch (Exception e) {
			return ResponseEntity.ok(Map.of("message", e.getMessage(), "success", false));
		}
	}

}
