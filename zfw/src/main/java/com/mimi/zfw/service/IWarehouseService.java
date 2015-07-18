package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.Warehouse;
import com.mimi.zfw.mybatis.pojo.WarehouseExample;

public interface IWarehouseService extends
		IBaseService<Warehouse, WarehouseExample, String> {
	public void initWarehouse();

	public List<Warehouse> findWarehousesByParams(String keyWord,
			String region, String grossFloorArea, String type,
			String rentOrSale, String rental, String totalPrice,
			Boolean outOfDate, String orderBy, Integer targetPage, Integer pageSize);

	public int countWarehouseByParams(String keyWord, String region,
			String grossFloorArea, String type, String rentOrSale,
			String rental, String totalPrice, Boolean outOfDate);

	public int countByUserId(String userId);

	public List<Warehouse> getByUserId(String userId, Integer targetPage,
			Integer pageSize);

	public String saveCascading(Warehouse warehouse, String imgUrls);

	public String deleteUserWarehouseByFlag(String id);

	public String refreshUserWarehouse(String id);

	public String updateCascading(Warehouse warehouse, String imgUrls);
	
	public Map<String, String> addWarehouse(Warehouse warehouse);

	public Map<String,String> updateWarehouse(Warehouse warehouse);
	    
	public int updateBatchWarehouse(String warehouseids,Warehouse warehouse);

	public String cleanDeadWarehouse();

}
