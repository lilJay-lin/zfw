package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.RCImageMapper;
import com.mimi.zfw.mybatis.pojo.RCImage;
import com.mimi.zfw.mybatis.pojo.RCImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IRCImageService;

@Service
public class RCImageServiceImpl extends
		BaseService<RCImage, RCImageExample, String>
		implements IRCImageService {

	@Resource
	private RCImageMapper rcim;

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<RCImage, RCImageExample, String> baseDao) {
		this.baseDao = baseDao;
		this.rcim = (RCImageMapper) baseDao;
	}

	@Override
	public List<RCImage> getImagesByRCId(String rcId, Integer targetPage,
			Integer pageSize) {
		RCImageExample ie = new RCImageExample();
		RCImageExample.Criteria cri = ie.createCriteria();
		cri.andResidenceCommunityIdEqualTo(rcId).andDelFlagEqualTo(false);
		ie.setLimitStart(targetPage*pageSize);
		ie.setLimitSize(pageSize);
		return rcim.selectByExample(ie);
	}


}
