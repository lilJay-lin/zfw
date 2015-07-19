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
import com.mimi.zfw.mybatis.dao.SHHPanoMapper;
import com.mimi.zfw.mybatis.pojo.SHHPano;
import com.mimi.zfw.mybatis.pojo.SHHPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.ISHHPanoService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.FormatUtil;

@Service
public class SHHPanoServiceImpl extends
		BaseService<SHHPano, SHHPanoExample, String> implements ISHHPanoService {

	@Resource
	private SHHPanoMapper shhpm;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<SHHPano, SHHPanoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.shhpm = (SHHPanoMapper) baseDao;
	}

	@Override
	public List<SHHPano> getPanosBySHHId(String id) {
		SHHPanoExample pe = new SHHPanoExample();
		pe.or().andSecondHandHouseIdEqualTo(id).andDelFlagEqualTo(false);
		pe.setOrderByClause("update_date desc");
		return shhpm.selectByExample(pe);
	}

	@Override
	public List<SHHPano> findByParams(String shhId, String name,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(shhId)) {
			return null;
		}
		SHHPanoExample pe = bindSHHPanoParams(shhId, name);
		if (targetPage != null && pageSize != null) {
			pe.setLimitStart(targetPage * pageSize);
			pe.setLimitSize(pageSize);
		}
		pe.setOrderByClause("update_date desc");
		return shhpm.selectByExample(pe);
	}

	@Override
	public int countByParams(String shhId, String name) {
		if (StringUtils.isBlank(shhId)) {
			return 0;
		}
		SHHPanoExample pe = bindSHHPanoParams(shhId, name);
		return shhpm.countByExample(pe);
	}

	private SHHPanoExample bindSHHPanoParams(String shhId, String name) {
		SHHPanoExample pe = new SHHPanoExample();
		SHHPanoExample.Criteria cri = pe.createCriteria();
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
	public Map<String, String> add(SHHPano pano) {
		Map<String, String> resMap = new HashMap<String, String>();
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(pano);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		pano.setCreater(curUserId);
		pano.setLastEditor(curUserId);
		pano.setId(UUID.randomUUID().toString());
		shhpm.insertSelective(pano);
		return resMap;
	}

	@Override
	public Map<String, String> modify(SHHPano pano) {
		Map<String, String> resMap = new HashMap<String, String>();
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(pano);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		pano.setLastEditor(curUserId);
		shhpm.updateByPrimaryKeySelective(pano);
		return resMap;
	}

	@Override
	public Map<String, String> batchDel(String panoIds) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isBlank(panoIds)) {
			resMap.put("msg", "删除内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		String[] ids = panoIds.split(Constants.MI_IDS_SPLIT_STRING);
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotBlank(ids[i])) {
				idList.add(ids[i]);
			}
		}
		if (!idList.isEmpty()) {
			SHHPanoExample pe = new SHHPanoExample();
			pe.or().andIdIn(idList).andDelFlagEqualTo(false);
			SHHPano pano = new SHHPano();
			pano.setDelFlag(true);
			pano.setLastEditor(curUserId);
			shhpm.updateByExampleSelective(pano, pe);
		}
		return resMap;
	}

	private Map<String, String> checkInfo(SHHPano pano) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (pano == null) {
			resMap.put("msg", "全景内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(pano.getSecondHandHouseId())) {
			resMap.put("msg", "全景所属二手房不能为空");
			return resMap;
		}
		

		String name = pano.getName();
		String errStr = FormatUtil.checkFormate(name,true, FormatUtil.MAX_LENGTH_COMMON_SHORT_L2, "全景名");
		if(StringUtils.isNotBlank(errStr)){
		    resMap.put("field","name");
		    resMap.put("msg", errStr);
		    return resMap;
		}

		
		if(StringUtils.isBlank(pano.getPreImageUrl())){
		    resMap.put("msg","全景缩略图不能为空");
		    return resMap;
		}
		
		String contentUrl = pano.getContentUrl();
		errStr = FormatUtil.checkFormate(contentUrl, true, FormatUtil.MAX_LENGTH_COMMON_NORMAL_L2, "全景内容路径");
		if(StringUtils.isNotBlank(errStr)){
			resMap.put("field","contentUrl");
			resMap.put("msg", errStr);
			return resMap;
		}
		
		String description = pano.getDescription();
		errStr = FormatUtil.checkFormate(description,false,FormatUtil.MAX_LENGTH_COMMON_NORMAL_L2, "描述");
		if(StringUtils.isNotBlank(errStr)){
			resMap.put("field","description");
			resMap.put("msg", errStr);
			return resMap;
		}

		
		return resMap;
	}

}
