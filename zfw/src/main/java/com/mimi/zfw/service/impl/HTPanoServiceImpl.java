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
import com.mimi.zfw.mybatis.dao.HTPanoMapper;
import com.mimi.zfw.mybatis.pojo.HTPano;
import com.mimi.zfw.mybatis.pojo.HTPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IHTPanoService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.FormatUtil;

@Service
public class HTPanoServiceImpl extends
		BaseService<HTPano, HTPanoExample, String> implements IHTPanoService {

	@Resource
	private HTPanoMapper htpm;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<HTPano, HTPanoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.htpm = (HTPanoMapper) baseDao;
	}

	@Override
	public List<HTPano> getPanosByHTId(String id) {
		HTPanoExample hpe = new HTPanoExample();
		hpe.or().andHouseTypeIdEqualTo(id).andDelFlagEqualTo(false);
		hpe.setOrderByClause("update_date desc");
		return htpm.selectByExample(hpe);
	}

	@Override
	public List<HTPano> getPanosByParams(String id, int targetPage, int pageSize) {
		HTPanoExample hpe = new HTPanoExample();
		hpe.or().andHouseTypeIdEqualTo(id).andDelFlagEqualTo(false);
		hpe.setLimitStart(targetPage * pageSize);
		hpe.setLimitSize(pageSize);
		hpe.setOrderByClause("update_date desc");
		return htpm.selectByExample(hpe);
	}

	@Override
	public List<HTPano> findByParams(String htId, String name,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(htId)) {
			return null;
		}
		HTPanoExample pe = bindHTPanoParams(htId, name);
		if (targetPage != null && pageSize != null) {
			pe.setLimitStart(targetPage * pageSize);
			pe.setLimitSize(pageSize);
		}
		pe.setOrderByClause("update_date desc");
		return htpm.selectByExample(pe);
	}

	@Override
	public int countByParams(String htId, String name) {
		if (StringUtils.isBlank(htId)) {
			return 0;
		}
		HTPanoExample pe = bindHTPanoParams(htId, name);
		return htpm.countByExample(pe);
	}

	private HTPanoExample bindHTPanoParams(String htId, String name) {
		HTPanoExample pe = new HTPanoExample();
		HTPanoExample.Criteria cri = pe.createCriteria();
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
	public Map<String, String> add(HTPano pano) {
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
		htpm.insertSelective(pano);
		return resMap;
	}

	@Override
	public Map<String, String> modify(HTPano pano) {
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
		htpm.updateByPrimaryKeySelective(pano);
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
			HTPanoExample pe = new HTPanoExample();
			pe.or().andIdIn(idList).andDelFlagEqualTo(false);
			HTPano pano = new HTPano();
			pano.setDelFlag(true);
			pano.setLastEditor(curUserId);
			htpm.updateByExampleSelective(pano, pe);
		}
		return resMap;
	}

	private Map<String, String> checkInfo(HTPano pano) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (pano == null) {
			resMap.put("msg", "全景内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(pano.getHouseTypeId())) {
			resMap.put("msg", "全景所属户型不能为空");
			return resMap;
		}

		String name = pano.getName();
		String errStr = FormatUtil.checkFormate(name,true, FormatUtil.MAX_LENGTH_COMMON_SHORT_L2, "全景名");
		if(StringUtils.isNotBlank(errStr)){
		    resMap.put("field","name");
		    resMap.put("msg", errStr);
		    return resMap;
		}

		
//		if(StringUtils.isBlank(pano.getPreImageUrl())){
//		    resMap.put("msg","全景缩略图不能为空");
//		    return resMap;
//		}
		
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
