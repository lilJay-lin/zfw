package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.REPImage;
import com.mimi.zfw.mybatis.pojo.REPImageExample;

public interface IREPImageService extends
		IBaseService<REPImage, REPImageExample, String> {

	public List<REPImage> getImagesByREPId(String id);

	public List<REPImage> getImagesByParams(String id, String type,
			int targetPage, int pageSize);

}
