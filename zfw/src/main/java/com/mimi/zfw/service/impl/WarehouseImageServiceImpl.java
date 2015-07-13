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
import com.mimi.zfw.mybatis.dao.WarehouseImageMapper;
import com.mimi.zfw.mybatis.pojo.WarehouseImage;
import com.mimi.zfw.mybatis.pojo.WarehouseImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.service.IWarehouseImageService;

@Service
public class WarehouseImageServiceImpl extends
	BaseService<WarehouseImage, WarehouseImageExample, String> implements
	IWarehouseImageService {

    @Resource
    private WarehouseImageMapper wim;
    @Resource
    private IUserService userService;

    @Resource
    @Override
    public void setBaseDao(
	    IBaseDao<WarehouseImage, WarehouseImageExample, String> baseDao) {
	this.baseDao = baseDao;
	this.wim = (WarehouseImageMapper) baseDao;
    }

    @Override
    public List<WarehouseImage> getImagesByParams(String id, int targetPage,
	    int pageSize) {
	WarehouseImageExample ie = new WarehouseImageExample();
	ie.or().andWarehouseIdEqualTo(id).andDelFlagEqualTo(false);
	ie.setLimitStart(targetPage * pageSize);
	ie.setLimitSize(pageSize);
	ie.setOrderByClause("update_date asc");
	return wim.selectByExample(ie);
    }

    @Override
    public int updateBatchWarehouseImage(String warehouseImageIds,
	    WarehouseImage warehouseImage) {

	if (warehouseImage == null) {
	    return 0;
	}

	String[] ids = warehouseImageIds.split(Constants.MI_IDS_SPLIT_STRING);
	List<String> list = new ArrayList<String>();
	for (String id : ids) {
	    list.add(id);
	}

	WarehouseImageExample example = new WarehouseImageExample();
	example.or().andIdIn(list).andDelFlagEqualTo(false);
	warehouseImage.setLastEditor(userService.getCurUserId());

	int i = wim.updateByExampleSelective(warehouseImage, example);

	return i;
    }

    @Override
    public List<WarehouseImage> findWarehouseImageByExample(
	    WarehouseImageExample example, Integer curPage, Integer pageSize) {

	if (example == null) {
	    example = new WarehouseImageExample();
	    example.or().andDelFlagEqualTo(false);
	}

	example.setLimitStart(curPage * pageSize);
	example.setLimitSize(pageSize);

	List<WarehouseImage> list = wim.selectByExample(example);

	return list;
    }

    @Override
    public int countWarehouseImageByExample(WarehouseImageExample example) {

	if (example == null) {
	    example = new WarehouseImageExample();
	    example.or().andDelFlagEqualTo(false);
	}
	List<WarehouseImage> list = wim.selectByExample(example);

	return list == null ? 0 : list.size();
    }

    @Override
    public Map<String, String> addWarehouseImage(WarehouseImage warehouseImage) {
	// TODO Auto-generated method stub
	Map<String, String> resMap = new HashMap<String, String>();
	if (warehouseImage == null) {
	    resMap.put("msg", "厂房/仓库图片内容不能为空");
	    return resMap;
	}
	resMap = checkWarehouseImage(warehouseImage);
	if (!resMap.isEmpty()) {
	    return resMap;
	}
	String curId = userService.getCurUserId();
	warehouseImage.setCreater(curId);
	warehouseImage.setLastEditor(curId);
	warehouseImage.setDelFlag(false);
	warehouseImage.setId(UUID.randomUUID().toString());
	wim.insertSelective(warehouseImage);

	return resMap;
    }

    @Override
    public Map<String, String> updateWarehouseImage(
	    WarehouseImage warehouseImage) {
	Map<String, String> resMap = new HashMap<String, String>();
	if (warehouseImage == null) {
	    resMap.put("msg", "厂房/仓库图片内容不能为空");
	    return resMap;
	}
	resMap = checkWarehouseImage(warehouseImage);
	if (!resMap.isEmpty()) {
	    return resMap;
	}

	warehouseImage.setLastEditor(userService.getCurUserId());
	wim.updateByPrimaryKeySelective(warehouseImage);

	return resMap;
    }

    @Override
    public Map<String, String> checkWarehouseImage(WarehouseImage warehouseImage) {
	Map<String, String> resMap = new HashMap<String, String>();

	if (StringUtils.isBlank(warehouseImage.getName())) {
	    resMap.put("field", "name");
	    resMap.put("msg", "厂房/仓库名称不能为空");
	}
	if (StringUtils.isBlank(warehouseImage.getContentUrl())) {
	    resMap.put("msg", "厂房/仓库图片不能为空");
	}

	return resMap;
    }

}
