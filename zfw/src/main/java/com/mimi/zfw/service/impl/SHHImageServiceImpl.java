package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.SHHImageMapper;
import com.mimi.zfw.mybatis.pojo.SHHImage;
import com.mimi.zfw.mybatis.pojo.SHHImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.ISHHImageService;

@Service
public class SHHImageServiceImpl extends
		BaseService<SHHImage, SHHImageExample, String>
		implements ISHHImageService {

	@Resource
	private SHHImageMapper shhim;

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<SHHImage, SHHImageExample, String> baseDao) {
		this.baseDao = baseDao;
		this.shhim = (SHHImageMapper) baseDao;
	}

	@Override
	public List<SHHImage> getImagesByParams(String id, int targetPage,
			int pageSize) {
		SHHImageExample ie = new SHHImageExample();
		ie.or().andSecondHandHouseIdEqualTo(id).andDelFlagEqualTo(false);
		ie.setLimitStart(targetPage*pageSize);
		ie.setLimitSize(pageSize);
		return shhim.selectByExample(ie);
	}

}
