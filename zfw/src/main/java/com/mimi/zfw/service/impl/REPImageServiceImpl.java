package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.REPImageMapper;
import com.mimi.zfw.mybatis.pojo.REPImage;
import com.mimi.zfw.mybatis.pojo.REPImageExample;
import com.mimi.zfw.mybatis.pojo.REPImageExample.Criteria;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IREPImageService;

@Service
public class REPImageServiceImpl extends
		BaseService<REPImage, REPImageExample, String> implements
		IREPImageService {

	@Resource
	private REPImageMapper repim;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<REPImage, REPImageExample, String> baseDao) {
		this.baseDao = baseDao;
		this.repim = (REPImageMapper) baseDao;
	}

	@Override
	public List<REPImage> getImagesByREPId(String id) {
		REPImageExample ie = new REPImageExample();
		ie.or().andRealEstateProjectIdEqualTo(id).andDelFlagEqualTo(false);
		return repim.selectByExample(ie);
	}

	@Override
	public List<REPImage> getImagesByParams(String id, String type,
			int targetPage, int pageSize) {
		REPImageExample ie = new REPImageExample();
		Criteria cri = ie.createCriteria();
		cri.andRealEstateProjectIdEqualTo(id).andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(type)) {
			cri.andTypeEqualTo(type);
		}
		ie.setLimitStart(targetPage*pageSize);
		ie.setLimitSize(pageSize);
		return repim.selectByExample(ie);
	}

}
