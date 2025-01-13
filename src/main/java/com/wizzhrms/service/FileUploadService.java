package com.wizzhrms.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	String saveFile(MultipartFile file, String filePath);
	
}
