package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.WarehouseImage;
import com.mimi.zfw.mybatis.pojo.WarehouseImageExample;

public interface IWarehouseImageService extends
		IBaseService<WarehouseImage, WarehouseImageExample, String> {

	List<WarehouseImage> getImagesByParams(String id, int targetPage, int pageSize);
}
