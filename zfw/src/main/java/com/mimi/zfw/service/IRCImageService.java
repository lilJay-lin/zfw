package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.RCImage;
import com.mimi.zfw.mybatis.pojo.RCImageExample;

public interface IRCImageService extends
		IBaseService<RCImage, RCImageExample, String> {
	public List<RCImage> getImagesByRCId(String rcId, Integer targetPage,
			Integer pageSize);
}
