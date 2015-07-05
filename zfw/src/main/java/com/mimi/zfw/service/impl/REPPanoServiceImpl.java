package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.REPPanoMapper;
import com.mimi.zfw.mybatis.pojo.REPPano;
import com.mimi.zfw.mybatis.pojo.REPPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IREPPanoService;

@Service
public class REPPanoServiceImpl extends
		BaseService<REPPano, REPPanoExample, String> implements IREPPanoService {

	@Resource
	private REPPanoMapper reppm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<REPPano, REPPanoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.reppm = (REPPanoMapper) baseDao;
	}

	@Override
	public List<REPPano> getPanosByHTId(String id) {
		REPPanoExample pe = new REPPanoExample();
		pe.or().andRealEstateProjectIdEqualTo(id).andDelFlagEqualTo(false);
		return reppm.selectByExample(pe);
	}

	@Override
	public List<REPPano> getPanosByParams(String id, int targetPage, int pageSize) {
		REPPanoExample pe = new REPPanoExample();
		pe.or().andRealEstateProjectIdEqualTo(id).andDelFlagEqualTo(false);
		pe.setLimitStart(targetPage*pageSize);
		pe.setLimitSize(pageSize);
		return reppm.selectByExample(pe);
	}

}
