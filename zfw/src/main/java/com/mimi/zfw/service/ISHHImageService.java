package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.SHHImage;
import com.mimi.zfw.mybatis.pojo.SHHImageExample;

public interface ISHHImageService extends
		IBaseService<SHHImage, SHHImageExample, String> {

	List<SHHImage> getImagesByParams(String id, int targetPage, int pageSize);
}
