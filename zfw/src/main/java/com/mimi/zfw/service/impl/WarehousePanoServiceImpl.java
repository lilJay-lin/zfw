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
import com.mimi.zfw.mybatis.dao.WarehousePanoMapper;
import com.mimi.zfw.mybatis.pojo.WarehousePano;
import com.mimi.zfw.mybatis.pojo.WarehousePanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.service.IWarehousePanoService;

@Service
public class WarehousePanoServiceImpl extends
	BaseService<WarehousePano, WarehousePanoExample, String> implements
	IWarehousePanoService {

    @Resource
    private WarehousePanoMapper wpm;
    @Resource
    private IUserService userService;

    @Resource
    @Override
    public void setBaseDao(
	    IBaseDao<WarehousePano, WarehousePanoExample, String> baseDao) {
	this.baseDao = baseDao;
	this.wpm = (WarehousePanoMapper) baseDao;
    }

    @Override
    public List<WarehousePano> getPanosByWarehouseId(String id) {
	WarehousePanoExample pe = new WarehousePanoExample();
	pe.or().andWarehouseIdEqualTo(id).andDelFlagEqualTo(false);
	pe.setOrderByClause("update_date desc");
	return wpm.selectByExample(pe);
    }

    public int updateBatchWarehousePano(String warehousePanoIds,
	    WarehousePano warehousePano) {

	if (warehousePano == null) {
	    return 0;
	}

	String[] ids = warehousePanoIds.split(Constants.MI_IDS_SPLIT_STRING);
	List<String> list = new ArrayList<String>();
	for (String id : ids) {
	    list.add(id);
	}

	WarehousePanoExample example = new WarehousePanoExample();
	example.or().andIdIn(list).andDelFlagEqualTo(false);
	warehousePano.setLastEditor(userService.getCurUserId());

	int i = wpm.updateByExampleSelective(warehousePano, example);

	return i;
    }

    @Override
    public List<WarehousePano> findWarehousePanoByExample(
	    WarehousePanoExample example, Integer curPage, Integer pageSize) {

	if (example == null) {
	    example = new WarehousePanoExample();
	    example.or().andDelFlagEqualTo(false);
	}

	example.setLimitStart(curPage * pageSize);
	example.setLimitSize(pageSize);

	example.setOrderByClause("update_date desc");
	List<WarehousePano> list = wpm.selectByExample(example);

	return list;
    }

    @Override
    public int countWarehousePanoByExample(WarehousePanoExample example) {

	if (example == null) {
	    example = new WarehousePanoExample();
	    example.or().andDelFlagEqualTo(false);
	}
	return wpm.countByExample(example);
    }

    @Override
    public Map<String, String> addWarehousePano(WarehousePano warehousePano) {
	// TODO Auto-generated method stub
	Map<String, String> resMap = new HashMap<String, String>();
	if (warehousePano == null) {
	    resMap.put("msg", "厂房/仓库全景内容不能为空");
	    return resMap;
	}
	resMap = checkWarehousePano(warehousePano);
	if (!resMap.isEmpty()) {
	    return resMap;
	}
	String curId = userService.getCurUserId();
	warehousePano.setCreater(curId);
	warehousePano.setLastEditor(curId);
	warehousePano.setDelFlag(false);
	warehousePano.setId(UUID.randomUUID().toString());
	wpm.insertSelective(warehousePano);

	return resMap;
    }

    @Override
    public Map<String, String> updateWarehousePano(WarehousePano warehousePano) {
	Map<String, String> resMap = new HashMap<String, String>();
	if (warehousePano == null) {
	    resMap.put("msg", "厂房/仓库全景内容不能为空");
	    return resMap;
	}
	resMap = checkWarehousePano(warehousePano);
	if (!resMap.isEmpty()) {
	    return resMap;
	}

	warehousePano.setLastEditor(userService.getCurUserId());
	wpm.updateByPrimaryKeySelective(warehousePano);

	return resMap;
    }

    @Override
    public Map<String, String> checkWarehousePano(WarehousePano warehousePano) {
	Map<String, String> resMap = new HashMap<String, String>();

	if (StringUtils.isBlank(warehousePano.getName())) {
	    resMap.put("field", "name");
	    resMap.put("msg", "厂房/仓库名称不能为空");
	}
	if (StringUtils.isBlank(warehousePano.getPreImageUrl())) {
	    resMap.put("msg", "厂房/仓库图片不能为空");
	}

	return resMap;
    }
}
