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
import com.mimi.zfw.mybatis.dao.REPImageMapper;
import com.mimi.zfw.mybatis.pojo.REPImage;
import com.mimi.zfw.mybatis.pojo.REPImageExample;
import com.mimi.zfw.mybatis.pojo.REPImageExample.Criteria;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IREPImageService;
import com.mimi.zfw.service.IUserService;

@Service
public class REPImageServiceImpl extends
		BaseService<REPImage, REPImageExample, String> implements
		IREPImageService {

	@Resource
	private REPImageMapper repim;

	@Resource
	private IUserService userService;

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
		ie.setLimitStart(targetPage * pageSize);
		ie.setLimitSize(pageSize);
		ie.setOrderByClause("update_date asc");
		return repim.selectByExample(ie);
	}

	@Override
	public List<REPImage> findByParams(String repId, String name, String type,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(repId)) {
			return null;
		}
		REPImageExample ie = bindREPImageParams(repId, name, type);
		if (targetPage != null && pageSize != null) {
			ie.setLimitStart(targetPage * pageSize);
			ie.setLimitSize(pageSize);
		}
		return repim.selectByExample(ie);
	}

	@Override
	public int countByParams(String repId, String name, String type) {
		if (StringUtils.isBlank(repId)) {
			return 0;
		}
		REPImageExample ie = bindREPImageParams(repId, name, type);
		return repim.countByExample(ie);
	}

	private REPImageExample bindREPImageParams(String repId, String name,
			String type) {
		REPImageExample ie = new REPImageExample();
		REPImageExample.Criteria cri = ie.createCriteria();
		cri.andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(repId)) {
			cri.andRealEstateProjectIdEqualTo(repId);
		}
		if (StringUtils.isNotBlank(name)) {
			cri.andNameLike("%" + name + "%");
		}
		if (StringUtils.isNotBlank(type)) {
			cri.andTypeEqualTo(type);
		}
		return ie;
	}

	@Override
	public Map<String, String> add(REPImage image) {
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
		repim.insertSelective(image);
		return resMap;
	}

	@Override
	public Map<String, String> modify(REPImage image) {
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
		repim.updateByPrimaryKeySelective(image);
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
			REPImageExample ie = new REPImageExample();
			ie.or().andIdIn(idList).andDelFlagEqualTo(false);
			REPImage image = new REPImage();
			image.setDelFlag(true);
			image.setLastEditor(curUserId);
			repim.updateByExampleSelective(image, ie);
		}
		return resMap;
	}

	private Map<String, String> checkInfo(REPImage image) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (image == null) {
			resMap.put("msg", "图片内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(image.getRealEstateProjectId())) {
			resMap.put("msg", "图片所属楼盘不能为空");
			return resMap;
		}
		if(StringUtils.isBlank(image.getContentUrl())){
			resMap.put("msg", "图片路径不能为空");
			return resMap;
		}
		if(StringUtils.isBlank(image.getType())){
			resMap.put("msg", "图片类型不能为空");
			return resMap;
		}
		if(StringUtils.isBlank(image.getName())){
			resMap.put("msg", "图片名称不能为空");
			return resMap;
		}
		return resMap;
	}

}
