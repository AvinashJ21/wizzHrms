package com.wizzhrms.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wizzhrms.service.FileUploadService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

	FileOutputStream fstream = null;
	InputStream finstream = null;

	@Override
	public String saveFile(MultipartFile file, String filePath) {

		try {

			fstream = new FileOutputStream(new File(filePath + File.separator + file.getOriginalFilename()));
			finstream = file.getInputStream();
			fstream.write(finstream.readAllBytes());
			finstream.close();
			fstream.close();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return "SUCCESS";
	}

}
