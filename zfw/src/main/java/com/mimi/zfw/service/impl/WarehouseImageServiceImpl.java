package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.WarehouseImageMapper;
import com.mimi.zfw.mybatis.pojo.WarehouseImage;
import com.mimi.zfw.mybatis.pojo.WarehouseImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IWarehouseImageService;

@Service
public class WarehouseImageServiceImpl extends
		BaseService<WarehouseImage, WarehouseImageExample, String>
		implements IWarehouseImageService {

	@Resource
	private WarehouseImageMapper wim;

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<WarehouseImage, WarehouseImageExample, String> baseDao) {
		this.baseDao = baseDao;
		this.wim = (WarehouseImageMapper) baseDao;
	}

	@Override
	public List<WarehouseImage> getImagesByParams(String id, int targetPage,
			int pageSize) {
		WarehouseImageExample ie = new WarehouseImageExample();
		ie.or().andWarehouseIdEqualTo(id).andDelFlagEqualTo(false);
		ie.setLimitStart(targetPage*pageSize);
		ie.setLimitSize(pageSize);
		ie.setOrderByClause("update_date asc");
		return wim.selectByExample(ie);
	}

}
