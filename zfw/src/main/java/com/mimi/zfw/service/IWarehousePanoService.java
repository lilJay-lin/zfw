package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.WarehousePano;
import com.mimi.zfw.mybatis.pojo.WarehousePanoExample;

public interface IWarehousePanoService extends
		IBaseService<WarehousePano, WarehousePanoExample, String> {

	List<WarehousePano> getPanosByWarehouseId(String id);
}
