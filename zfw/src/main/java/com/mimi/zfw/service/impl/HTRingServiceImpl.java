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
import com.mimi.zfw.mybatis.dao.HTRingMapper;
import com.mimi.zfw.mybatis.pojo.HTRing;
import com.mimi.zfw.mybatis.pojo.HTRingExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IHTRingService;
import com.mimi.zfw.service.IUserService;

@Service
public class HTRingServiceImpl extends
		BaseService<HTRing, HTRingExample, String> implements IHTRingService {

	@Resource
	private HTRingMapper htrm;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<HTRing, HTRingExample, String> baseDao) {
		this.baseDao = baseDao;
		this.htrm = (HTRingMapper) baseDao;
	}

	@Override
	public List<HTRing> getRingsByHTId(String id) {
		HTRingExample hre = new HTRingExample();
		hre.or().andHouseTypeIdEqualTo(id).andDelFlagEqualTo(false);
		hre.setOrderByClause("update_date desc");
		return htrm.selectByExample(hre);
	}

	@Override
	public List<HTRing> getRingsByParams(String id, int targetPage, int pageSize) {
		HTRingExample hre = new HTRingExample();
		hre.or().andHouseTypeIdEqualTo(id).andDelFlagEqualTo(false);
		hre.setLimitStart(targetPage * pageSize);
		hre.setLimitSize(pageSize);
		hre.setOrderByClause("update_date desc");
		return htrm.selectByExample(hre);
	}

	@Override
	public List<HTRing> findByParams(String htId, String name,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(htId)) {
			return null;
		}
		HTRingExample pe = bindHTRingParams(htId, name);
		if (targetPage != null && pageSize != null) {
			pe.setLimitStart(targetPage * pageSize);
			pe.setLimitSize(pageSize);
		}
		pe.setOrderByClause("update_date desc");
		return htrm.selectByExample(pe);
	}

	@Override
	public int countByParams(String htId, String name) {
		if (StringUtils.isBlank(htId)) {
			return 0;
		}
		HTRingExample pe = bindHTRingParams(htId, name);
		return htrm.countByExample(pe);
	}

	private HTRingExample bindHTRingParams(String htId, String name) {
		HTRingExample pe = new HTRingExample();
		HTRingExample.Criteria cri = pe.createCriteria();
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
	public Map<String, String> add(HTRing ring) {
		Map<String, String> resMap = new HashMap<String, String>();
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(ring);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		ring.setCreater(curUserId);
		ring.setLastEditor(curUserId);
		ring.setId(UUID.randomUUID().toString());
		htrm.insertSelective(ring);
		return resMap;
	}

	@Override
	public Map<String, String> modify(HTRing ring) {
		Map<String, String> resMap = new HashMap<String, String>();
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(ring);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		ring.setLastEditor(curUserId);
		htrm.updateByPrimaryKeySelective(ring);
		return resMap;
	}

	@Override
	public Map<String, String> batchDel(String ringIds) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isBlank(ringIds)) {
			resMap.put("msg", "删除内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		String[] ids = ringIds.split(Constants.MI_IDS_SPLIT_STRING);
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotBlank(ids[i])) {
				idList.add(ids[i]);
			}
		}
		if (!idList.isEmpty()) {
			HTRingExample pe = new HTRingExample();
			pe.or().andIdIn(idList).andDelFlagEqualTo(false);
			HTRing ring = new HTRing();
			ring.setDelFlag(true);
			ring.setLastEditor(curUserId);
			htrm.updateByExampleSelective(ring, pe);
		}
		return resMap;
	}

	private Map<String, String> checkInfo(HTRing ring) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (ring == null) {
			resMap.put("msg", "三维内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(ring.getHouseTypeId())) {
			resMap.put("msg", "三维所属户型不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(ring.getPreImageUrl())) {
			resMap.put("msg", "三维缩略图不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(ring.getContentUrl())) {
			resMap.put("msg", "三维内容路径不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(ring.getName())) {
			resMap.put("msg", "三维名称不能为空");
			return resMap;
		}
		return resMap;
	}
}
