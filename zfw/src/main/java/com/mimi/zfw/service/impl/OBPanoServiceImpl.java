package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.OBPanoMapper;
import com.mimi.zfw.mybatis.pojo.OBPano;
import com.mimi.zfw.mybatis.pojo.OBPanoExample;
import com.mimi.zfw.mybatis.pojo.OBPano;
import com.mimi.zfw.mybatis.pojo.OBPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IOBPanoService;
import com.mimi.zfw.service.IUserService;

@Service
public class OBPanoServiceImpl extends
	BaseService<OBPano, OBPanoExample, String> implements IOBPanoService {

    @Resource
    private OBPanoMapper obpm;
    @Resource
    private IUserService userService;

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

    @Override
    public int updateBatchOBPano(String oBPanoIds, OBPano oBPano) {

	if (oBPano == null) {
	    return 0;
	}

	String[] ids = oBPanoIds.split(Constants.MI_IDS_SPLIT_STRING);
	List<String> list = new ArrayList<String>();
	for (String id : ids) {
	    list.add(id);
	}

	OBPanoExample example = new OBPanoExample();
	example.or().andIdIn(list).andDelFlagEqualTo(false);
	oBPano.setLastEditor(userService.getCurUserId());

	int i = obpm.updateByExampleSelective(oBPano, example);

	return i;
    }

    @Override
    public List<OBPano> findOBPanoByExample(OBPanoExample example,
	    Integer curPage, Integer pageSize) {

	if (example == null) {
	    example = new OBPanoExample();
	    example.or().andDelFlagEqualTo(false);
	}

	example.setLimitStart(curPage * pageSize);
	example.setLimitSize(pageSize);

	List<OBPano> list = obpm.selectByExample(example);

	return list;
    }

    @Override
    public int countOBPanoByExample(OBPanoExample example) {

	if (example == null) {
	    example = new OBPanoExample();
	    example.or().andDelFlagEqualTo(false);
	}
	List<OBPano> list = obpm.selectByExample(example);

	return list == null ? 0 : list.size();
    }

    @Override
    public Map<String, String> addOBPano(OBPano oBPano) {
	// TODO Auto-generated method stub
	Map<String, String> resMap = new HashMap<String, String>();
	if (oBPano == null) {
	    resMap.put("msg", "写字楼全景内容不能为空");
	    return resMap;
	}
	resMap = checkOBPano(oBPano);
	if (!resMap.isEmpty()) {
	    return resMap;
	}
	String curId = userService.getCurUserId();
	oBPano.setCreater(curId);
	oBPano.setLastEditor(curId);
	oBPano.setDelFlag(false);
	oBPano.setId(UUID.randomUUID().toString());
	obpm.insertSelective(oBPano);

	return resMap;
    }

    @Override
    public Map<String, String> updateOBPano(OBPano oBPano) {
	Map<String, String> resMap = new HashMap<String, String>();
	if (oBPano == null) {
	    resMap.put("msg", "写字楼全景内容不能为空");
	    return resMap;
	}
	resMap = checkOBPano(oBPano);
	if (!resMap.isEmpty()) {
	    return resMap;
	}

	oBPano.setLastEditor(userService.getCurUserId());
	obpm.updateByPrimaryKeySelective(oBPano);

	return resMap;
    }

    @Override
    public Map<String, String> checkOBPano(OBPano oBPano) {
	Map<String, String> resMap = new HashMap<String, String>();

	if (StringUtils.isBlank(oBPano.getName())) {
	    resMap.put("field", "name");
	    resMap.put("msg", "写字楼名称不能为空");
	}
	if (StringUtils.isBlank(oBPano.getPreImageUrl())) {
	    resMap.put("msg", "写字楼图片不能为空");
	}

	return resMap;
    }
}
