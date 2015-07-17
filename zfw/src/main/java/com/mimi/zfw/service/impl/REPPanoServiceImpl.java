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
import com.mimi.zfw.mybatis.dao.REPPanoMapper;
import com.mimi.zfw.mybatis.pojo.REPPano;
import com.mimi.zfw.mybatis.pojo.REPPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IREPPanoService;
import com.mimi.zfw.service.IUserService;

@Service
public class REPPanoServiceImpl extends
		BaseService<REPPano, REPPanoExample, String> implements IREPPanoService {

	@Resource
	private REPPanoMapper reppm;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<REPPano, REPPanoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.reppm = (REPPanoMapper) baseDao;
	}

	@Override
	public List<REPPano> getPanosByHTId(String id) {
		REPPanoExample pe = new REPPanoExample();
		pe.or().andRealEstateProjectIdEqualTo(id).andDelFlagEqualTo(false);
		pe.setOrderByClause("update_date desc");
		return reppm.selectByExample(pe);
	}

	@Override
	public List<REPPano> getPanosByParams(String id, int targetPage,
			int pageSize) {
		REPPanoExample pe = new REPPanoExample();
		pe.or().andRealEstateProjectIdEqualTo(id).andDelFlagEqualTo(false);
		pe.setLimitStart(targetPage * pageSize);
		pe.setLimitSize(pageSize);
		pe.setOrderByClause("update_date desc");
		return reppm.selectByExample(pe);
	}

	@Override
	public List<REPPano> findByParams(String repId, String name,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(repId)) {
			return null;
		}
		REPPanoExample pe = bindREPPanoParams(repId, name);
		if (targetPage != null && pageSize != null) {
			pe.setLimitStart(targetPage * pageSize);
			pe.setLimitSize(pageSize);
		}
		pe.setOrderByClause("update_date desc");
		return reppm.selectByExample(pe);
	}

	@Override
	public int countByParams(String repId, String name) {
		if (StringUtils.isBlank(repId)) {
			return 0;
		}
		REPPanoExample pe = bindREPPanoParams(repId, name);
		return reppm.countByExample(pe);
	}

	private REPPanoExample bindREPPanoParams(String repId, String name) {
		REPPanoExample pe = new REPPanoExample();
		REPPanoExample.Criteria cri = pe.createCriteria();
		cri.andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(repId)) {
			cri.andRealEstateProjectIdEqualTo(repId);
		}
		if (StringUtils.isNotBlank(name)) {
			cri.andNameLike("%" + name + "%");
		}
		return pe;
	}

	@Override
	public Map<String, String> add(REPPano pano) {
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
		reppm.insertSelective(pano);
		return resMap;
	}

	@Override
	public Map<String, String> modify(REPPano pano) {
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
		reppm.updateByPrimaryKeySelective(pano);
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
			REPPanoExample pe = new REPPanoExample();
			pe.or().andIdIn(idList).andDelFlagEqualTo(false);
			REPPano pano = new REPPano();
			pano.setDelFlag(true);
			pano.setLastEditor(curUserId);
			reppm.updateByExampleSelective(pano, pe);
		}
		return resMap;
	}

	private Map<String, String> checkInfo(REPPano pano) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (pano == null) {
			resMap.put("msg", "全景内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(pano.getRealEstateProjectId())) {
			resMap.put("msg", "全景所属楼盘不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(pano.getPreImageUrl())) {
			resMap.put("msg", "全景缩略图不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(pano.getContentUrl())) {
			resMap.put("msg", "全景内容路径不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(pano.getName())) {
			resMap.put("msg", "全景名称不能为空");
			return resMap;
		}
		return resMap;
	}

}
