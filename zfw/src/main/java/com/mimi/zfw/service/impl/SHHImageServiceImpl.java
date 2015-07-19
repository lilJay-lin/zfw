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
import com.mimi.zfw.mybatis.dao.SHHImageMapper;
import com.mimi.zfw.mybatis.pojo.SHHImage;
import com.mimi.zfw.mybatis.pojo.SHHImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.ISHHImageService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.FormatUtil;

@Service
public class SHHImageServiceImpl extends
		BaseService<SHHImage, SHHImageExample, String> implements
		ISHHImageService {

	@Resource
	private SHHImageMapper shhim;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<SHHImage, SHHImageExample, String> baseDao) {
		this.baseDao = baseDao;
		this.shhim = (SHHImageMapper) baseDao;
	}

	@Override
	public List<SHHImage> getImagesByParams(String id, int targetPage,
			int pageSize) {
		SHHImageExample ie = new SHHImageExample();
		ie.or().andSecondHandHouseIdEqualTo(id).andDelFlagEqualTo(false);
		ie.setLimitStart(targetPage * pageSize);
		ie.setLimitSize(pageSize);
		ie.setOrderByClause("update_date asc");
		return shhim.selectByExample(ie);
	}

	@Override
	public List<SHHImage> findByParams(String shhId, String name,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(shhId)) {
			return null;
		}
		SHHImageExample ie = bindSHHImageParams(shhId, name);
		if (targetPage != null && pageSize != null) {
			ie.setLimitStart(targetPage * pageSize);
			ie.setLimitSize(pageSize);
		}
		ie.setOrderByClause("update_date desc");
		return shhim.selectByExample(ie);
	}

	@Override
	public int countByParams(String shhId, String name) {
		if (StringUtils.isBlank(shhId)) {
			return 0;
		}
		SHHImageExample pe = bindSHHImageParams(shhId, name);
		return shhim.countByExample(pe);
	}

	private SHHImageExample bindSHHImageParams(String shhId, String name) {
		SHHImageExample pe = new SHHImageExample();
		SHHImageExample.Criteria cri = pe.createCriteria();
		cri.andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(shhId)) {
			cri.andSecondHandHouseIdEqualTo(shhId);
		}
		if (StringUtils.isNotBlank(name)) {
			cri.andNameLike("%" + name + "%");
		}
		return pe;
	}

	@Override
	public Map<String, String> add(SHHImage image) {
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
		shhim.insertSelective(image);
		return resMap;
	}

	@Override
	public Map<String, String> modify(SHHImage image) {
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
		shhim.updateByPrimaryKeySelective(image);
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
			SHHImageExample pe = new SHHImageExample();
			pe.or().andIdIn(idList).andDelFlagEqualTo(false);
			SHHImage image = new SHHImage();
			image.setDelFlag(true);
			image.setLastEditor(curUserId);
			shhim.updateByExampleSelective(image, pe);
		}
		return resMap;
	}

	private Map<String, String> checkInfo(SHHImage image) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (image == null) {
			resMap.put("msg", "图片内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(image.getSecondHandHouseId())) {
			resMap.put("msg", "图片所属二手房不能为空");
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
