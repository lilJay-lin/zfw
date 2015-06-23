package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.RHImageMapper;
import com.mimi.zfw.mybatis.pojo.RHImage;
import com.mimi.zfw.mybatis.pojo.RHImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IRHImageService;

@Service
public class RHImageServiceImpl extends
		BaseService<RHImage, RHImageExample, String> implements IRHImageService {

	@Resource
	private RHImageMapper rhim;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<RHImage, RHImageExample, String> baseDao) {
		this.baseDao = baseDao;
		this.rhim = (RHImageMapper) baseDao;
	}

	@Override
	public List<RHImage> getImagesByParams(String id, int targetPage,
			int pageSize) {
		RHImageExample ie = new RHImageExample();
		ie.or().andRentalHousingIdEqualTo(id).andDelFlagEqualTo(false);
		ie.setLimitStart(targetPage * pageSize);
		ie.setLimitSize(pageSize);
		return rhim.selectByExample(ie);
	}

}
