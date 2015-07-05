package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.OBImageMapper;
import com.mimi.zfw.mybatis.pojo.OBImage;
import com.mimi.zfw.mybatis.pojo.OBImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IOBImageService;

@Service
public class OBImageServiceImpl extends
		BaseService<OBImage, OBImageExample, String>
		implements IOBImageService {

	@Resource
	private OBImageMapper obim;

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<OBImage, OBImageExample, String> baseDao) {
		this.baseDao = baseDao;
		this.obim = (OBImageMapper) baseDao;
	}

	@Override
	public List<OBImage> getImagesByParams(String id, int targetPage,
			int pageSize) {
		OBImageExample ie = new OBImageExample();
		ie.or().andOfficeBuildingIdEqualTo(id).andDelFlagEqualTo(false);
		ie.setLimitStart(targetPage*pageSize);
		ie.setLimitSize(pageSize);
		ie.setOrderByClause("update_date asc");
		return obim.selectByExample(ie);
	}

}
