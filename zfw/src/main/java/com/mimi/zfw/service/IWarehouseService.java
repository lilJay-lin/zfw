package com.mimi.zfw.service;

import com.mimi.zfw.mybatis.pojo.Warehouse;
import com.mimi.zfw.mybatis.pojo.WarehouseExample;

public interface IWarehouseService extends IBaseService<Warehouse, WarehouseExample, String> {
	public void initWarehouse();
}
