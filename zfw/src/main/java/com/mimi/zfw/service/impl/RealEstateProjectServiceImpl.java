package com.mimi.zfw.service.impl;

import javax.annotation.Resource;

import com.mimi.zfw.mybatis.dao.RealEstateProjectMapper;
import com.mimi.zfw.mybatis.pojo.RealEstateProject;
import com.mimi.zfw.mybatis.pojo.RealEstateProjectExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IRealEstateProjectService;

public class RealEstateProjectServiceImpl  extends
BaseService<RealEstateProject, RealEstateProjectExample, String> implements
IRealEstateProjectService {

	@Resource
	private RealEstateProjectMapper repm;
	
    @Resource
    @Override
    public void setBaseDao(
	    IBaseDao<RealEstateProject, RealEstateProjectExample, String> baseDao) {
	this.baseDao = baseDao;
	this.repm = (RealEstateProjectMapper) baseDao;
    }

}
