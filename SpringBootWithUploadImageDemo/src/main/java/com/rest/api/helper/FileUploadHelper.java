package com.rest.api.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

	public final String UPLOAD_DIR = "F:\\MyAllPorgrams\\Java Framework\\SpringBootProject\\SpringBootWithUploadImageDemo\\src\\main\\resources\\static\\image";

	public boolean uploadFile(MultipartFile file) {
		boolean f = false;
		try {
			// 1st way
			// Read Image Data
			InputStream is = file.getInputStream();
			byte data[] = new byte[is.available()];
			is.read(data);

			// Write Image Data
			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR + "\\" + file.getOriginalFilename());
			fos.write(data);
			fos.flush();
			fos.close();

			// 2 Way
			Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);

			f = true;
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
