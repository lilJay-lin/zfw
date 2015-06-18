package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.HTImage;
import com.mimi.zfw.mybatis.pojo.HTImageExample;

public interface IHTImageService extends
		IBaseService<HTImage, HTImageExample, String> {

	public List<HTImage> getImagesByHTId(String id);

	public List<HTImage> getImageByParams(String id, int targetPage,
			int pageSize);

}
