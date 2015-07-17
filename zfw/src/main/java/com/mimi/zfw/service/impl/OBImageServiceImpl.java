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
import com.mimi.zfw.mybatis.dao.OBImageMapper;
import com.mimi.zfw.mybatis.pojo.OBImage;
import com.mimi.zfw.mybatis.pojo.OBImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IOBImageService;
import com.mimi.zfw.service.IUserService;

@Service
public class OBImageServiceImpl extends
	BaseService<OBImage, OBImageExample, String> implements IOBImageService {

    @Resource
    private OBImageMapper obim;
    @Resource
    private IUserService userService;

    @Resource
    @Override
    public void setBaseDao(IBaseDao<OBImage, OBImageExample, String> baseDao) {
	this.baseDao = baseDao;
	this.obim = (OBImageMapper) baseDao;
    }

    @Override
    public List<OBImage> getImagesByParams(String id, int targetPage,
	    int pageSize) {
	OBImageExample ie = new OBImageExample();
	ie.or().andOfficeBuildingIdEqualTo(id).andDelFlagEqualTo(false);
	ie.setLimitStart(targetPage * pageSize);
	ie.setLimitSize(pageSize);
	ie.setOrderByClause("update_date desc");
	return obim.selectByExample(ie);
    }

    @Override
    public int updateBatchOBImage(String oBImageIds, OBImage oBImage) {

	if (oBImage == null) {
	    return 0;
	}

	String[] ids = oBImageIds.split(Constants.MI_IDS_SPLIT_STRING);
	List<String> list = new ArrayList<String>();
	for (String id : ids) {
	    list.add(id);
	}

	OBImageExample example = new OBImageExample();
	example.or().andIdIn(list).andDelFlagEqualTo(false);
	oBImage.setLastEditor(userService.getCurUserId());

	int i = obim.updateByExampleSelective(oBImage, example);

	return i;
    }

    @Override
    public List<OBImage> findOBImageByExample(OBImageExample example,
	    Integer curPage, Integer pageSize) {

	if (example == null) {
	    example = new OBImageExample();
	    example.or().andDelFlagEqualTo(false);
	}

	example.setLimitStart(curPage * pageSize);
	example.setLimitSize(pageSize);

	example.setOrderByClause("update_date desc");
	List<OBImage> list = obim.selectByExample(example);

	return list;
    }

    @Override
    public int countOBImageByExample(OBImageExample example) {

	if (example == null) {
	    example = new OBImageExample();
	    example.or().andDelFlagEqualTo(false);
	}
	return obim.countByExample(example);
    }

    @Override
    public Map<String, String> addOBImage(OBImage oBImage) {
	// TODO Auto-generated method stub
	Map<String, String> resMap = new HashMap<String, String>();
	if (oBImage == null) {
	    resMap.put("msg", "写字楼图片内容不能为空");
	    return resMap;
	}
	resMap = checkOBImage(oBImage);
	if (!resMap.isEmpty()) {
	    return resMap;
	}
	String curId = userService.getCurUserId();
	oBImage.setCreater(curId);
	oBImage.setLastEditor(curId);
	oBImage.setDelFlag(false);
	oBImage.setId(UUID.randomUUID().toString());
	obim.insertSelective(oBImage);

	return resMap;
    }

    @Override
    public Map<String, String> updateOBImage(OBImage oBImage) {
	Map<String, String> resMap = new HashMap<String, String>();
	if (oBImage == null) {
	    resMap.put("msg", "写字楼图片内容不能为空");
	    return resMap;
	}
	resMap = checkOBImage(oBImage);
	if (!resMap.isEmpty()) {
	    return resMap;
	}

	oBImage.setLastEditor(userService.getCurUserId());
	obim.updateByPrimaryKeySelective(oBImage);

	return resMap;
    }

    @Override
    public Map<String, String> checkOBImage(OBImage oBImage) {
	Map<String, String> resMap = new HashMap<String, String>();

	if (StringUtils.isBlank(oBImage.getName())) {
	    resMap.put("field", "name");
	    resMap.put("msg", "写字楼名称不能为空");
	}
	if (StringUtils.isBlank(oBImage.getContentUrl())) {
	    resMap.put("msg", "写字楼图片不能为空");
	}

	return resMap;
    }

}
