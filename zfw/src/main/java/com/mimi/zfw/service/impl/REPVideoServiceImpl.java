package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.REPVideoMapper;
import com.mimi.zfw.mybatis.pojo.REPVideo;
import com.mimi.zfw.mybatis.pojo.REPVideoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IREPVideoService;

@Service
public class REPVideoServiceImpl extends
		BaseService<REPVideo, REPVideoExample, String> implements
		IREPVideoService {

	@Resource
	private REPVideoMapper repvm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<REPVideo, REPVideoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.repvm = (REPVideoMapper) baseDao;
	}

	@Override
	public List<REPVideo> getVideosByHTId(String id) {
		REPVideoExample ve = new REPVideoExample();
		ve.or().andRealEstateProjectIdEqualTo(id).andDelFlagEqualTo(false);
		return repvm.selectByExample(ve);
	}

	@Override
	public List<REPVideo> getVideosByParams(String id, int targetPage,
			int pageSize) {
		REPVideoExample ve = new REPVideoExample();
		ve.or().andRealEstateProjectIdEqualTo(id).andDelFlagEqualTo(false);
		ve.setLimitStart(targetPage*pageSize);
		ve.setLimitSize(pageSize);
		return repvm.selectByExample(ve);
	}

}
