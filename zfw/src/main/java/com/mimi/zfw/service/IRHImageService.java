package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.RHImage;
import com.mimi.zfw.mybatis.pojo.RHImageExample;

public interface IRHImageService extends
		IBaseService<RHImage, RHImageExample, String> {

	List<RHImage> getImagesByParams(String id, int targetPage, int pageSize);

	public List<RHImage> findByParams(String rhId, String name,
			Integer targetPage, Integer pageSize);

	public int countByParams(String rhId, String name);

	public Map<String, String> add(RHImage image);

	public Map<String, String> modify(RHImage image);

	public Map<String, String> batchDel(String imageIds);
}
