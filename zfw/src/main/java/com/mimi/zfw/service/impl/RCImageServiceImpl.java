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
import com.mimi.zfw.mybatis.dao.RCImageMapper;
import com.mimi.zfw.mybatis.pojo.RCImage;
import com.mimi.zfw.mybatis.pojo.RCImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IRCImageService;
import com.mimi.zfw.service.IUserService;

@Service
public class RCImageServiceImpl extends
		BaseService<RCImage, RCImageExample, String> implements IRCImageService {

	@Resource
	private RCImageMapper rcim;
	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<RCImage, RCImageExample, String> baseDao) {
		this.baseDao = baseDao;
		this.rcim = (RCImageMapper) baseDao;
	}

	@Override
	public List<RCImage> getImagesByRCId(String rcId, Integer targetPage,
			Integer pageSize) {
		RCImageExample ie = new RCImageExample();
		RCImageExample.Criteria cri = ie.createCriteria();
		cri.andResidenceCommunityIdEqualTo(rcId).andDelFlagEqualTo(false);
		ie.setLimitStart(targetPage * pageSize);
		ie.setLimitSize(pageSize);
		return rcim.selectByExample(ie);
	}

	@Override
	public List<RCImage> findByParams(String rcId, String name,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(rcId)) {
			return null;
		}
		RCImageExample pe = bindRCImageParams(rcId, name);
		if (targetPage != null && pageSize != null) {
			pe.setLimitStart(targetPage * pageSize);
			pe.setLimitSize(pageSize);
		}
		pe.setOrderByClause("update_date desc");
		return rcim.selectByExample(pe);
	}

	@Override
	public int countByParams(String rcId, String name) {
		if (StringUtils.isBlank(rcId)) {
			return 0;
		}
		RCImageExample pe = bindRCImageParams(rcId, name);
		return rcim.countByExample(pe);
	}

	private RCImageExample bindRCImageParams(String rcId, String name) {
		RCImageExample pe = new RCImageExample();
		RCImageExample.Criteria cri = pe.createCriteria();
		cri.andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(rcId)) {
			cri.andResidenceCommunityIdEqualTo(rcId);
		}
		if (StringUtils.isNotBlank(name)) {
			cri.andNameLike("%" + name + "%");
		}
		return pe;
	}

	@Override
	public Map<String, String> add(RCImage image) {
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
		rcim.insertSelective(image);
		return resMap;
	}

	@Override
	public Map<String, String> modify(RCImage image) {
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
		rcim.updateByPrimaryKeySelective(image);
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
			RCImageExample pe = new RCImageExample();
			pe.or().andIdIn(idList).andDelFlagEqualTo(false);
			RCImage image = new RCImage();
			image.setDelFlag(true);
			image.setLastEditor(curUserId);
			rcim.updateByExampleSelective(image, pe);
		}
		return resMap;
	}

	private Map<String, String> checkInfo(RCImage image) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (image == null) {
			resMap.put("msg", "图片内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(image.getResidenceCommunityId())) {
			resMap.put("msg", "图片所属小区不能为空");
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
