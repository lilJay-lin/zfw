package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.OBPanoMapper;
import com.mimi.zfw.mybatis.pojo.OBPano;
import com.mimi.zfw.mybatis.pojo.OBPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IOBPanoService;

@Service
public class OBPanoServiceImpl extends
		BaseService<OBPano, OBPanoExample, String> implements
		IOBPanoService {

	@Resource
	private OBPanoMapper obpm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<OBPano, OBPanoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.obpm = (OBPanoMapper) baseDao;
	}

	@Override
	public List<OBPano> getPanosByOBId(String id) {
		OBPanoExample pe = new OBPanoExample();
		pe.or().andOfficeBuildingIdEqualTo(id).andDelFlagEqualTo(false);
		return obpm.selectByExample(pe);
	}

}
