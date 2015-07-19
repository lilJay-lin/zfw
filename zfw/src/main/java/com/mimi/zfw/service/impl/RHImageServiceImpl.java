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
import com.mimi.zfw.mybatis.dao.RHImageMapper;
import com.mimi.zfw.mybatis.pojo.RHImage;
import com.mimi.zfw.mybatis.pojo.RHImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IRHImageService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.FormatUtil;

@Service
public class RHImageServiceImpl extends
		BaseService<RHImage, RHImageExample, String> implements IRHImageService {

	@Resource
	private RHImageMapper rhim;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<RHImage, RHImageExample, String> baseDao) {
		this.baseDao = baseDao;
		this.rhim = (RHImageMapper) baseDao;
	}

	@Override
	public List<RHImage> getImagesByParams(String id, int targetPage,
			int pageSize) {
		RHImageExample ie = new RHImageExample();
		ie.or().andRentalHousingIdEqualTo(id).andDelFlagEqualTo(false);
		ie.setLimitStart(targetPage * pageSize);
		ie.setLimitSize(pageSize);
		ie.setOrderByClause("update_date asc");
		return rhim.selectByExample(ie);
	}

	@Override
	public List<RHImage> findByParams(String rhId, String name,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(rhId)) {
			return null;
		}
		RHImageExample ie = bindRHImageParams(rhId, name);
		if (targetPage != null && pageSize != null) {
			ie.setLimitStart(targetPage * pageSize);
			ie.setLimitSize(pageSize);
		}
		ie.setOrderByClause("update_date desc");
		return rhim.selectByExample(ie);
	}

	@Override
	public int countByParams(String rhId, String name) {
		if (StringUtils.isBlank(rhId)) {
			return 0;
		}
		RHImageExample pe = bindRHImageParams(rhId, name);
		return rhim.countByExample(pe);
	}

	private RHImageExample bindRHImageParams(String rhId, String name) {
		RHImageExample pe = new RHImageExample();
		RHImageExample.Criteria cri = pe.createCriteria();
		cri.andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(rhId)) {
			cri.andRentalHousingIdEqualTo(rhId);
		}
		if (StringUtils.isNotBlank(name)) {
			cri.andNameLike("%" + name + "%");
		}
		return pe;
	}

	@Override
	public Map<String, String> add(RHImage image) {
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
		rhim.insertSelective(image);
		return resMap;
	}

	@Override
	public Map<String, String> modify(RHImage image) {
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
		rhim.updateByPrimaryKeySelective(image);
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
			RHImageExample pe = new RHImageExample();
			pe.or().andIdIn(idList).andDelFlagEqualTo(false);
			RHImage image = new RHImage();
			image.setDelFlag(true);
			image.setLastEditor(curUserId);
			rhim.updateByExampleSelective(image, pe);
		}
		return resMap;
	}

	private Map<String, String> checkInfo(RHImage image) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (image == null) {
			resMap.put("msg", "图片内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(image.getRentalHousingId())) {
			resMap.put("msg", "图片所属租房不能为空");
			return resMap;
		}
		String name = image.getName();
		String errStr = FormatUtil.checkFormate(name,true, FormatUtil.MAX_LENGTH_COMMON_SHORT_L2, "图片名称");
		if(StringUtils.isNotBlank(errStr)){
		    resMap.put("field","name");
		    resMap.put("msg", errStr);
		    return resMap;
		}
		
		if(StringUtils.isBlank(image.getContentUrl())){
		    resMap.put("msg","图片不能为空");
		    return resMap;
		}

		String description = image.getDescription();
		errStr = FormatUtil.checkFormate(description,false,FormatUtil.MAX_LENGTH_COMMON_NORMAL_L2, "描述");
		if(StringUtils.isNotBlank(errStr)){
			resMap.put("field","description");
			resMap.put("msg", errStr);
			return resMap;
		}
		
		return resMap;
	}
}
