package com.mimi.zfw.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.mimi.zfw.Constants;
import com.mimi.zfw.service.IAliyunOSSService;

@Service
public class AliyunOSSServiceImpl implements IAliyunOSSService {
//	private String accessKeyId = "8yCPOEyqRL87gGa3";
//	private String accessKeySecret = "oTYhHWx8jTQVV03MiCucmLAI396PUS";
//    // 以杭州为例
//	private String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
	
	private OSSClient oc = null;
	@Override
	public OSSClient getOSSClient(){
		if(oc==null){
			oc = new OSSClient(Constants.ALIYUN_OSS_ENDPOINT,Constants.ALIYUN_OSS_ACCESS_KEY_ID, Constants.ALIYUN_OSS_ACCESS_KEY_SECRET);
		}
		return oc;
	}
	
	public void putObject(String bucketName, String key, String filePath) throws FileNotFoundException {

	    // 初始化OSSClient
	    OSSClient client = getOSSClient();

	    // 获取指定文件的输入流
	    File file = new File(filePath);
	    InputStream content = new FileInputStream(file);

	    // 创建上传Object的Metadata
	    ObjectMetadata meta = new ObjectMetadata();

	    // 必须设置ContentLength
	    meta.setContentLength(file.length());

	    // 上传Object.
	    PutObjectResult result = client.putObject(bucketName, key, content, meta);

	    // 打印ETag
	    System.out.println(result.getETag());
	}

	public void listObjects(String bucketName) {

	    // 初始化OSSClient
	    OSSClient client = getOSSClient();

	    // 获取指定bucket下的所有Object信息
	    ObjectListing listing = client.listObjects(bucketName);

	    // 遍历所有Object
	    for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
	        System.out.println(objectSummary.getKey());
	    }
	}

	@Override
	public String saveFileToServer(MultipartFile theFile) throws IOException {
		 // 初始化OSSClient
	    OSSClient client = getOSSClient();

	    // 获取指定文件的输入流
	    InputStream content = theFile.getInputStream();

	    // 创建上传Object的Metadata
	    ObjectMetadata meta = new ObjectMetadata();

	    // 必须设置ContentLength
	    meta.setContentLength(theFile.getSize());

		String fileName = theFile.getOriginalFilename();
		fileName = UUID.randomUUID().toString()
				+ fileName.substring(fileName.lastIndexOf("."));
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.getTimeInMillis();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		String path = "upload/images/" + year + "/" + month + "/" + day
				+ "/" + hour + "/"+fileName;
		client.putObject(Constants.ALIYUN_OSS_IMAGE_BUCKET, path, content, meta);
	    path = Constants.ALIYUN_OSS_CDN_URL+path;
		return path;
	}
	
	public static void main(String args[]){
		AliyunOSSServiceImpl ai = new AliyunOSSServiceImpl();
//		ai.getOSSClient();
//		System.out.println(ai.accessKeyId);
//		ai.listObjects("bluemimi4common");
		try {
			ai.putObject("bluemimi4common", "test/waw.gif", "D:\\material\\images\\gif\\houseLoading.gif");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	@Override
//	public String addImgStyle(String path, String style) {
//		if(StringUtils.isNotBlank(path) && StringUtils.isNotBlank(style)){
//			int index = path.lastIndexOf(Constants.ALIYUN_OSS_IMAGE_STYLE_SPLIT);
//			if(index==-1){
//				path = path+Constants.ALIYUN_OSS_IMAGE_STYLE_SPLIT+style;
//			}else{
//				path = path.substring(0, index)+Constants.ALIYUN_OSS_IMAGE_STYLE_SPLIT+style;
//			}
//		}
//		return path;
//	}
//
//	@Override
//	public String clearImgStyle(String path) {
//		if(StringUtils.isNotBlank(path)){
//			int index = path.lastIndexOf(Constants.ALIYUN_OSS_IMAGE_STYLE_SPLIT);
//			if(index!=-1){
//				path = path.substring(0, index);
//			}
//		}
//		return path;
//	}

	@Override
	public String addImgParams(String path, String params) {
		if(StringUtils.isNotBlank(path) && StringUtils.isNotBlank(params)){
			path = clearImgParams(path);
			String suffix = path.substring(path.lastIndexOf(".")+1);
			for(int i=0;i<Constants.ALIYUN_OSS_IMAGE_ALLOWED_SUFFIX.length;i++){
				if(Constants.ALIYUN_OSS_IMAGE_ALLOWED_SUFFIX[i].equalsIgnoreCase(suffix)){
					params = params.replace("{suffix}", Constants.ALIYUN_OSS_IMAGE_ALLOWED_SUFFIX[i]);
					path = path+Constants.ALIYUN_OSS_IMAGE_PARAMS_SPLIT+params;
					break;
				}
			}
		}
		return path;
	}

	@Override
	public String clearImgParams(String path) {
		if(StringUtils.isNotBlank(path)){
			int index = path.lastIndexOf(Constants.ALIYUN_OSS_IMAGE_PARAMS_SPLIT);
			if(index!=-1){
				path = path.substring(0, index);
			}
		}
		return path;
	}

	@Override
	public String batchAddImgParams(String paths, String params) {
		if(StringUtils.isNotBlank(paths) && StringUtils.isNotBlank(params)){
			String[] pathList = paths.split(Constants.IMAGE_URLS_SPLIT_STRING);
			String newPaths = "";
			for(int j=0;j<pathList.length;j++){
				String path = addImgParams(pathList[j], params);
				newPaths = newPaths+path;
				if(j<pathList.length-1){
					newPaths = newPaths+Constants.IMAGE_URLS_SPLIT_STRING;
				}
			}
			paths = newPaths;
		}
		return paths;
	}

	@Override
	public String batchClearImgParams(String paths) {
		if(StringUtils.isNotBlank(paths)){
			String[] pathList = paths.split(Constants.IMAGE_URLS_SPLIT_STRING);
			String newPaths = "";
			for(int j=0;j<pathList.length;j++){
				String path = clearImgParams(pathList[j]);
				newPaths = newPaths+path;
				if(j<pathList.length-1){
					newPaths = newPaths+Constants.IMAGE_URLS_SPLIT_STRING;
				}
			}
			paths = newPaths;
		}
		return paths;
	}
}
