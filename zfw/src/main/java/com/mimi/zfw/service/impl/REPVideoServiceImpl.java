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
import com.mimi.zfw.mybatis.dao.REPVideoMapper;
import com.mimi.zfw.mybatis.pojo.REPVideo;
import com.mimi.zfw.mybatis.pojo.REPVideoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IREPVideoService;
import com.mimi.zfw.service.IUserService;

@Service
public class REPVideoServiceImpl extends
		BaseService<REPVideo, REPVideoExample, String> implements
		IREPVideoService {

	@Resource
	private REPVideoMapper repvm;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<REPVideo, REPVideoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.repvm = (REPVideoMapper) baseDao;
	}

	@Override
	public List<REPVideo> getVideosByHTId(String id) {
		REPVideoExample ve = new REPVideoExample();
		ve.or().andRealEstateProjectIdEqualTo(id).andDelFlagEqualTo(false);
		ve.setOrderByClause("update_date desc");
		return repvm.selectByExample(ve);
	}

	@Override
	public List<REPVideo> getVideosByParams(String id, int targetPage,
			int pageSize) {
		REPVideoExample ve = new REPVideoExample();
		ve.or().andRealEstateProjectIdEqualTo(id).andDelFlagEqualTo(false);
		ve.setLimitStart(targetPage*pageSize);
		ve.setLimitSize(pageSize);
		ve.setOrderByClause("update_date desc");
		return repvm.selectByExample(ve);
	}
	@Override
	public List<REPVideo> findByParams(String repId, String name,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(repId)) {
			return null;
		}
		REPVideoExample ve = bindREPVideoParams(repId, name);
		if (targetPage != null && pageSize != null) {
			ve.setLimitStart(targetPage * pageSize);
			ve.setLimitSize(pageSize);
		}
		ve.setOrderByClause("update_date desc");
		return repvm.selectByExample(ve);
	}

	@Override
	public int countByParams(String repId, String name) {
		if (StringUtils.isBlank(repId)) {
			return 0;
		}
		REPVideoExample ve = bindREPVideoParams(repId, name);
		return repvm.countByExample(ve);
	}

	private REPVideoExample bindREPVideoParams(String repId, String name) {
		REPVideoExample ve = new REPVideoExample();
		REPVideoExample.Criteria cri = ve.createCriteria();
		cri.andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(repId)) {
			cri.andRealEstateProjectIdEqualTo(repId);
		}
		if (StringUtils.isNotBlank(name)) {
			cri.andNameLike("%" + name + "%");
		}
		return ve;
	}

	@Override
	public Map<String, String> add(REPVideo video) {
		Map<String, String> resMap = new HashMap<String, String>();
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(video);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		video.setCreater(curUserId);
		video.setLastEditor(curUserId);
		video.setId(UUID.randomUUID().toString());
		repvm.insertSelective(video);
		return resMap;
	}

	@Override
	public Map<String, String> modify(REPVideo video) {
		Map<String, String> resMap = new HashMap<String, String>();
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(video);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		video.setLastEditor(curUserId);
		repvm.updateByPrimaryKeySelective(video);
		return resMap;
	}

	@Override
	public Map<String, String> batchDel(String videoIds) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isBlank(videoIds)) {
			resMap.put("msg", "删除内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		String[] ids = videoIds.split(Constants.MI_IDS_SPLIT_STRING);
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotBlank(ids[i])) {
				idList.add(ids[i]);
			}
		}
		if (!idList.isEmpty()) {
			REPVideoExample ve = new REPVideoExample();
			ve.or().andIdIn(idList).andDelFlagEqualTo(false);
			REPVideo video = new REPVideo();
			video.setDelFlag(true);
			video.setLastEditor(curUserId);
			repvm.updateByExampleSelective(video, ve);
		}
		return resMap;
	}

	private Map<String, String> checkInfo(REPVideo video) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (video == null) {
			resMap.put("msg", "视频内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(video.getRealEstateProjectId())) {
			resMap.put("msg", "视频所属楼盘不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(video.getPreImageUrl())) {
			resMap.put("msg", "视频缩略图不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(video.getContentUrl())) {
			resMap.put("msg", "视频内容路径不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(video.getName())) {
			resMap.put("msg", "视频名称不能为空");
			return resMap;
		}
		return resMap;
	}

}
