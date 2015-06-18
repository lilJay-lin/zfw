package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.HTPanoMapper;
import com.mimi.zfw.mybatis.pojo.HTPano;
import com.mimi.zfw.mybatis.pojo.HTPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IHTPanoService;

@Service
public class HTPanoServiceImpl extends
		BaseService<HTPano, HTPanoExample, String> implements IHTPanoService {

	@Resource
	private HTPanoMapper hpm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<HTPano, HTPanoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.hpm = (HTPanoMapper) baseDao;
	}

	@Override
	public List<HTPano> getPanosByHTId(String id) {
		HTPanoExample hpe = new HTPanoExample();
		hpe.or().andHouseTypeIdEqualTo(id).andDelFlagEqualTo(false);
		return hpm.selectByExample(hpe);
	}

	@Override
	public List<HTPano> getPanosByParams(String id, int targetPage, int pageSize) {
		HTPanoExample hpe = new HTPanoExample();
		hpe.or().andHouseTypeIdEqualTo(id).andDelFlagEqualTo(false);
		hpe.setLimitStart(targetPage);
		hpe.setLimitSize(pageSize);
		return hpm.selectByExample(hpe);
	}

}
