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
import com.mimi.zfw.mybatis.dao.RHPanoMapper;
import com.mimi.zfw.mybatis.pojo.RHPano;
import com.mimi.zfw.mybatis.pojo.RHPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IRHPanoService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.FormatUtil;

@Service
public class RHPanoServiceImpl extends
		BaseService<RHPano, RHPanoExample, String> implements IRHPanoService {

	@Resource
	private RHPanoMapper rhpm;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<RHPano, RHPanoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.rhpm = (RHPanoMapper) baseDao;
	}

	@Override
	public List<RHPano> getPanosByRHId(String id) {
		RHPanoExample pe = new RHPanoExample();
		pe.or().andRentalHousingIdEqualTo(id).andDelFlagEqualTo(false);
		pe.setOrderByClause("update_date desc");
		return rhpm.selectByExample(pe);
	}

	@Override
	public List<RHPano> findByParams(String rhId, String name,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(rhId)) {
			return null;
		}
		RHPanoExample pe = bindRHPanoParams(rhId, name);
		if (targetPage != null && pageSize != null) {
			pe.setLimitStart(targetPage * pageSize);
			pe.setLimitSize(pageSize);
		}
		pe.setOrderByClause("update_date desc");
		return rhpm.selectByExample(pe);
	}

	@Override
	public int countByParams(String rhId, String name) {
		if (StringUtils.isBlank(rhId)) {
			return 0;
		}
		RHPanoExample pe = bindRHPanoParams(rhId, name);
		return rhpm.countByExample(pe);
	}

	private RHPanoExample bindRHPanoParams(String rhId, String name) {
		RHPanoExample pe = new RHPanoExample();
		RHPanoExample.Criteria cri = pe.createCriteria();
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
	public Map<String, String> add(RHPano pano) {
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
		rhpm.insertSelective(pano);
		return resMap;
	}

	@Override
	public Map<String, String> modify(RHPano pano) {
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
		rhpm.updateByPrimaryKeySelective(pano);
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
			RHPanoExample pe = new RHPanoExample();
			pe.or().andIdIn(idList).andDelFlagEqualTo(false);
			RHPano pano = new RHPano();
			pano.setDelFlag(true);
			pano.setLastEditor(curUserId);
			rhpm.updateByExampleSelective(pano, pe);
		}
		return resMap;
	}

	private Map<String, String> checkInfo(RHPano pano) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (pano == null) {
			resMap.put("msg", "全景内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(pano.getRentalHousingId())) {
			resMap.put("msg", "全景所属租房不能为空");
			return resMap;
		}

		String name = pano.getName();
		String errStr = FormatUtil.checkFormate(name,true, FormatUtil.MAX_LENGTH_COMMON_SHORT_L2, "全景名称");
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
