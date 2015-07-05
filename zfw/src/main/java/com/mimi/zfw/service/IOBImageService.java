package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.OBImage;
import com.mimi.zfw.mybatis.pojo.OBImageExample;

public interface IOBImageService extends
		IBaseService<OBImage, OBImageExample, String> {

	List<OBImage> getImagesByParams(String id, int targetPage, int pageSize);
}
