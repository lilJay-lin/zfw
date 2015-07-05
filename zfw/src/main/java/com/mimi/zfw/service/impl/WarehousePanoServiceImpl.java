package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.WarehousePanoMapper;
import com.mimi.zfw.mybatis.pojo.WarehousePano;
import com.mimi.zfw.mybatis.pojo.WarehousePanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IWarehousePanoService;

@Service
public class WarehousePanoServiceImpl extends
		BaseService<WarehousePano, WarehousePanoExample, String> implements
		IWarehousePanoService {

	@Resource
	private WarehousePanoMapper wpm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<WarehousePano, WarehousePanoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.wpm = (WarehousePanoMapper) baseDao;
	}

	@Override
	public List<WarehousePano> getPanosByWarehouseId(String id) {
		WarehousePanoExample pe = new WarehousePanoExample();
		pe.or().andWarehouseIdEqualTo(id).andDelFlagEqualTo(false);
		return wpm.selectByExample(pe);
	}

}
