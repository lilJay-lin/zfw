package com.mimi.zfw.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	public static String saveFileToServer(MultipartFile multifile, String path)
			throws IOException {
		// 创建目录
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String fileName = multifile.getOriginalFilename();
		fileName = UUID.randomUUID().toString()
				+ fileName.substring(fileName.lastIndexOf("."));
		// String fileName = UUID.randomUUID().toString();
		// 读取文件流并保持在指定路径
		InputStream inputStream = multifile.getInputStream();
		OutputStream outputStream = new FileOutputStream(path + fileName);
		byte[] buffer = multifile.getBytes();
//		int bytesum = 0;
		int byteread = 0;
		while ((byteread = inputStream.read(buffer)) != -1) {
//			bytesum += byteread;
			outputStream.write(buffer, 0, byteread);
			outputStream.flush();
		}
		outputStream.close();
		inputStream.close();

		return fileName;
	}
}
