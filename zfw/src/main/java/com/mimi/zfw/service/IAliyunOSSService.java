package com.mimi.zfw.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;

public interface IAliyunOSSService {

	public OSSClient getOSSClient();

	public String saveFileToServer(MultipartFile theFile) throws IOException;

//	public String addImgStyle(String path, String style);
//	
//	public String clearImgStyle(String path);
	
	public String addImgParams(String path, String params);
	
	public String batchAddImgParams(String paths,String params);
	
	public String clearImgParams(String path);
	
	public String batchClearImgParams(String paths);
	
}
