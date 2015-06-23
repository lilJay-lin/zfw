package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.RHPanoMapper;
import com.mimi.zfw.mybatis.pojo.RHPano;
import com.mimi.zfw.mybatis.pojo.RHPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IRHPanoService;

@Service
public class RHPanoServiceImpl extends
		BaseService<RHPano, RHPanoExample, String> implements IRHPanoService {

	@Resource
	private RHPanoMapper rhpm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<RHPano, RHPanoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.rhpm = (RHPanoMapper) baseDao;
	}

	@Override
	public List<RHPano> getPanosByRHId(String id) {
		RHPanoExample pe = new RHPanoExample();
		pe.or().andRentalHousingIdEqualTo(id).andDelFlagEqualTo(false);
		return rhpm.selectByExample(pe);
	}

}
