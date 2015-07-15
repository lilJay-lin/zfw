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
import com.mimi.zfw.mybatis.dao.HTImageMapper;
import com.mimi.zfw.mybatis.pojo.HTImage;
import com.mimi.zfw.mybatis.pojo.HTImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IHTImageService;
import com.mimi.zfw.service.IUserService;

@Service
public class HTImageServiceImpl extends
		BaseService<HTImage, HTImageExample, String> implements IHTImageService {

	@Resource
	private HTImageMapper htim;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<HTImage, HTImageExample, String> baseDao) {
		this.baseDao = baseDao;
		this.htim = (HTImageMapper) baseDao;
	}

	@Override
	public List<HTImage> getImagesByHTId(String id) {
		HTImageExample hie = new HTImageExample();
		hie.or().andHouseTypeIdEqualTo(id).andDelFlagEqualTo(false);
		return htim.selectByExample(hie);
	}

	@Override
	public List<HTImage> getImagesByParams(String id, int targetPage,
			int pageSize) {
		HTImageExample hie = new HTImageExample();
		hie.or().andHouseTypeIdEqualTo(id).andDelFlagEqualTo(false);
		hie.setLimitStart(targetPage * pageSize);
		hie.setLimitSize(pageSize);
		hie.setOrderByClause("update_date asc");
		return htim.selectByExample(hie);
	}

	@Override
	public List<HTImage> findByParams(String htId, String name,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(htId)) {
			return null;
		}
		HTImageExample pe = bindHTImageParams(htId, name);
		if (targetPage != null && pageSize != null) {
			pe.setLimitStart(targetPage * pageSize);
			pe.setLimitSize(pageSize);
		}
		pe.setOrderByClause("update_date desc");
		return htim.selectByExample(pe);
	}

	@Override
	public int countByParams(String htId, String name) {
		if (StringUtils.isBlank(htId)) {
			return 0;
		}
		HTImageExample pe = bindHTImageParams(htId, name);
		return htim.countByExample(pe);
	}

	private HTImageExample bindHTImageParams(String htId, String name) {
		HTImageExample pe = new HTImageExample();
		HTImageExample.Criteria cri = pe.createCriteria();
		cri.andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(htId)) {
			cri.andHouseTypeIdEqualTo(htId);
		}
		if (StringUtils.isNotBlank(name)) {
			cri.andNameLike("%" + name + "%");
		}
		return pe;
	}

	@Override
	public Map<String, String> add(HTImage image) {
		Map<String, String> resMap = new HashMap<String, String>();
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(image);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		image.setCreater(curUserId);
		image.setLastEditor(curUserId);
		image.setId(UUID.randomUUID().toString());
		htim.insertSelective(image);
		return resMap;
	}

	@Override
	public Map<String, String> modify(HTImage image) {
		Map<String, String> resMap = new HashMap<String, String>();
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(image);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		image.setLastEditor(curUserId);
		htim.updateByPrimaryKeySelective(image);
		return resMap;
	}

	@Override
	public Map<String, String> batchDel(String imageIds) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isBlank(imageIds)) {
			resMap.put("msg", "删除内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		String[] ids = imageIds.split(Constants.MI_IDS_SPLIT_STRING);
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotBlank(ids[i])) {
				idList.add(ids[i]);
			}
		}
		if (!idList.isEmpty()) {
			HTImageExample pe = new HTImageExample();
			pe.or().andIdIn(idList).andDelFlagEqualTo(false);
			HTImage image = new HTImage();
			image.setDelFlag(true);
			image.setLastEditor(curUserId);
			htim.updateByExampleSelective(image, pe);
		}
		return resMap;
	}

	private Map<String, String> checkInfo(HTImage image) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (image == null) {
			resMap.put("msg", "图片内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(image.getHouseTypeId())) {
			resMap.put("msg", "图片所属户型不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(image.getContentUrl())) {
			resMap.put("msg", "图片内容路径不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(image.getName())) {
			resMap.put("msg", "图片名称不能为空");
			return resMap;
		}
		return resMap;
	}
}
