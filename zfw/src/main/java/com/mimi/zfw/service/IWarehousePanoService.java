package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.WarehousePano;
import com.mimi.zfw.mybatis.pojo.WarehousePanoExample;

public interface IWarehousePanoService extends
	IBaseService<WarehousePano, WarehousePanoExample, String> {

    List<WarehousePano> getPanosByWarehouseId(String id);

    public int updateBatchWarehousePano(String warehousePanoIds, WarehousePano warehousePano);

    public List<WarehousePano> findWarehousePanoByExample(WarehousePanoExample example,
	    Integer curPage, Integer pageSize);

    public int countWarehousePanoByExample(WarehousePanoExample example);

    public Map<String, String> addWarehousePano(WarehousePano warehousePano);

    public Map<String, String> updateWarehousePano(WarehousePano warehousePano);

    public Map<String, String> checkWarehousePano(WarehousePano warehousePano);

}
