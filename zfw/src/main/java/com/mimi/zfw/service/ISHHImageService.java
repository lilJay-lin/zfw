package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.SHHImage;
import com.mimi.zfw.mybatis.pojo.SHHImageExample;

public interface ISHHImageService extends
		IBaseService<SHHImage, SHHImageExample, String> {

	List<SHHImage> getImagesByParams(String id, int targetPage, int pageSize);

	public List<SHHImage> findByParams(String shhId, String name,
			Integer targetPage, Integer pageSize);

	public int countByParams(String shhId, String name);

	public Map<String, String> add(SHHImage image);

	public Map<String, String> modify(SHHImage image);

	public Map<String, String> batchDel(String imageIds);
}
