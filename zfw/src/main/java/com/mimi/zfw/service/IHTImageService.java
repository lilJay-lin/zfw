package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.HTImage;
import com.mimi.zfw.mybatis.pojo.HTImageExample;

public interface IHTImageService extends
		IBaseService<HTImage, HTImageExample, String> {

	public List<HTImage> getImagesByHTId(String id);

	public List<HTImage> getImagesByParams(String id, int targetPage,
			int pageSize);

	public List<HTImage> findByParams(String htId, String name,
			Integer targetPage, Integer pageSize);

	public int countByParams(String htId, String name);

	public Map<String, String> add(HTImage image);

	public Map<String, String> modify(HTImage image);

	public Map<String, String> batchDel(String imageIds);

}
