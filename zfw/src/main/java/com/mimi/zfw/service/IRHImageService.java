package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.RHImage;
import com.mimi.zfw.mybatis.pojo.RHImageExample;

public interface IRHImageService extends
		IBaseService<RHImage, RHImageExample, String> {

	List<RHImage> getImagesByParams(String id, int targetPage, int pageSize);
}
