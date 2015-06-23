package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.SHHPanoMapper;
import com.mimi.zfw.mybatis.pojo.SHHPano;
import com.mimi.zfw.mybatis.pojo.SHHPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.ISHHPanoService;

@Service
public class SHHPanoServiceImpl extends
		BaseService<SHHPano, SHHPanoExample, String> implements ISHHPanoService {

	@Resource
	private SHHPanoMapper shhpm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<SHHPano, SHHPanoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.shhpm = (SHHPanoMapper) baseDao;
	}

	@Override
	public List<SHHPano> getPanosBySHHId(String id) {
		SHHPanoExample pe = new SHHPanoExample();
		pe.or().andSecondHandHouseIdEqualTo(id).andDelFlagEqualTo(false);
		return shhpm.selectByExample(pe);
	}

}
