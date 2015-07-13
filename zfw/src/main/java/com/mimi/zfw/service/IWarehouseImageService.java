package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.WarehouseImage;
import com.mimi.zfw.mybatis.pojo.WarehouseImageExample;

public interface IWarehouseImageService extends
	IBaseService<WarehouseImage, WarehouseImageExample, String> {

    List<WarehouseImage> getImagesByParams(String id, int targetPage,
	    int pageSize);
    
    public int updateBatchWarehouseImage(String warehouseImageIds,WarehouseImage warehouseImage);

    public List<WarehouseImage> findWarehouseImageByExample(WarehouseImageExample example,
	    Integer curPage, Integer pageSize);

    public int countWarehouseImageByExample(WarehouseImageExample example);

    public Map<String, String> addWarehouseImage(WarehouseImage warehouseImage);

    public Map<String, String> updateWarehouseImage(WarehouseImage warehouseImage);

    public Map<String, String> checkWarehouseImage(WarehouseImage warehouseImage);

}
