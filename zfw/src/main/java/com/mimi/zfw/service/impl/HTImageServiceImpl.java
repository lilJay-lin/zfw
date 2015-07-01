package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.HTImageMapper;
import com.mimi.zfw.mybatis.pojo.HTImage;
import com.mimi.zfw.mybatis.pojo.HTImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IHTImageService;

@Service
public class HTImageServiceImpl extends
	BaseService<HTImage, HTImageExample, String> implements
	IHTImageService {

    @Resource
    private HTImageMapper him;


    @Resource
    @Override
    public void setBaseDao(
	    IBaseDao<HTImage, HTImageExample, String> baseDao) {
	this.baseDao = baseDao;
	this.him = (HTImageMapper) baseDao;
    }


	@Override
	public List<HTImage> getImagesByHTId(String id) {
		HTImageExample hie = new HTImageExample();
		hie.or().andHouseTypeIdEqualTo(id).andDelFlagEqualTo(false);
		return him.selectByExample(hie);
	}


	@Override
	public List<HTImage> getImagesByParams(String id, int targetPage,
			int pageSize) {
		HTImageExample hie = new HTImageExample();
		hie.or().andHouseTypeIdEqualTo(id).andDelFlagEqualTo(false);
		hie.setLimitStart(targetPage*pageSize);
		hie.setLimitSize(pageSize);
		hie.setOrderByClause("update_date asc");
		return him.selectByExample(hie);
	}

}
