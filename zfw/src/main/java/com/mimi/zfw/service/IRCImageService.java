package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.RCImage;
import com.mimi.zfw.mybatis.pojo.RCImageExample;

public interface IRCImageService extends
		IBaseService<RCImage, RCImageExample, String> {
	public List<RCImage> getImagesByRCId(String rcId, Integer targetPage,
			Integer pageSize);

	public List<RCImage> findByParams(String rcId, String name,
			Integer targetPage, Integer pageSize);

	public int countByParams(String rcId, String name);

	public Map<String, String> add(RCImage image);

	public Map<String, String> modify(RCImage image);

	public Map<String, String> batchDel(String imageIds);

}
