package com.mimi.zfw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.WarehouseMapper;
import com.mimi.zfw.mybatis.pojo.Warehouse;
import com.mimi.zfw.mybatis.pojo.WarehouseExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IWarehouseService;

@Service
public class WarehouseServiceImpl extends
		BaseService<Warehouse, WarehouseExample, String> implements
		IWarehouseService {

	@Resource
	private WarehouseMapper wm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<Warehouse, WarehouseExample, String> baseDao) {
		this.baseDao = baseDao;
		this.wm = (WarehouseMapper) baseDao;
	}

	@Override
	public void initWarehouse() {
		// TODO Auto-generated method stub
		
	}

}
