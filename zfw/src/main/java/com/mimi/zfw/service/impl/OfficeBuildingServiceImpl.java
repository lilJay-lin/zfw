package com.mimi.zfw.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.OfficeBuildingMapper;
import com.mimi.zfw.mybatis.pojo.OfficeBuilding;
import com.mimi.zfw.mybatis.pojo.OfficeBuildingExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IOfficeBuildingService;

@Service
public class OfficeBuildingServiceImpl extends
		BaseService<OfficeBuilding, OfficeBuildingExample, String> implements
		IOfficeBuildingService {

	@Resource
	private OfficeBuildingMapper obm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<OfficeBuilding, OfficeBuildingExample, String> baseDao) {
		this.baseDao = baseDao;
		this.obm = (OfficeBuildingMapper) baseDao;
	}

	@Override
	public void initOfficeBuilding() {
		// TODO Auto-generated method stub
		
	}

}
