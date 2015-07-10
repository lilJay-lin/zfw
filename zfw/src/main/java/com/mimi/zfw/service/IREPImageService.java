package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.REPImage;
import com.mimi.zfw.mybatis.pojo.REPImageExample;

public interface IREPImageService extends
		IBaseService<REPImage, REPImageExample, String> {

	public List<REPImage> getImagesByREPId(String id);

	public List<REPImage> getImagesByParams(String id, String type,
			int targetPage, int pageSize);

	public List<REPImage> findByParams(String repId, String name, String type,
			Integer targetPage, Integer pageSize);

	public int countByParams(String repId, String name, String type);

	public Map<String, String> add(REPImage image);

	public Map<String, String> modify(REPImage image);

	public Map<String, String> batchDel(String imageIds);

}
