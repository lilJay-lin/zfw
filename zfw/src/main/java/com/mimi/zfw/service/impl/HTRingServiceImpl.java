package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.HTRingMapper;
import com.mimi.zfw.mybatis.pojo.HTRing;
import com.mimi.zfw.mybatis.pojo.HTRingExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IHTRingService;

@Service
public class HTRingServiceImpl extends
		BaseService<HTRing, HTRingExample, String> implements IHTRingService {

	@Resource
	private HTRingMapper hrm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<HTRing, HTRingExample, String> baseDao) {
		this.baseDao = baseDao;
		this.hrm = (HTRingMapper) baseDao;
	}

	@Override
	public List<HTRing> getRingsByHTId(String id) {
		HTRingExample hre = new HTRingExample();
		hre.or().andHouseTypeIdEqualTo(id).andDelFlagEqualTo(false);
		return hrm.selectByExample(hre);
	}

	@Override
	public List<HTRing> getRingsByParams(String id, int targetPage, int pageSize) {
		HTRingExample hre = new HTRingExample();
		hre.or().andHouseTypeIdEqualTo(id).andDelFlagEqualTo(false);
		hre.setLimitStart(targetPage*pageSize);
		hre.setLimitSize(pageSize);
		return hrm.selectByExample(hre);
	}

}
