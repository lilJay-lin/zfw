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
import com.mimi.zfw.mybatis.dao.AssessmentItemMapper;
import com.mimi.zfw.mybatis.dao.AssessmentParameterMapper;
import com.mimi.zfw.mybatis.pojo.AssessmentItem;
import com.mimi.zfw.mybatis.pojo.AssessmentItemExample;
import com.mimi.zfw.mybatis.pojo.AssessmentParameter;
import com.mimi.zfw.mybatis.pojo.AssessmentParameterExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IAssessmentItemService;
import com.mimi.zfw.service.IUserService;

@Service
public class AssessmentItemServiceImpl extends
		BaseService<AssessmentItem, AssessmentItemExample, String> implements
		IAssessmentItemService {

	@Resource
	private AssessmentItemMapper aim;
	@Resource
	private AssessmentParameterMapper apm;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<AssessmentItem, AssessmentItemExample, String> baseDao) {
		this.baseDao = baseDao;
		this.aim = (AssessmentItemMapper) baseDao;
	}

	@Override
	public void initAssessItem() {
		AssessmentItemExample ie = new AssessmentItemExample();
		ie.or().andDelFlagEqualTo(false);
		int count = aim.countByExample(ie);
		if (count < 1) {
			initTestData();
		}
	}

	private void initTestData() {
		String[] aiNames = { "景观情况", "采光通风", "噪音影响", "厌恶因素" };
		String[] aiTypes = { Constants.ASSESSMENT_ITEM_TYPE_RADIO,
				Constants.ASSESSMENT_ITEM_TYPE_RADIO,
				Constants.ASSESSMENT_ITEM_TYPE_RADIO,
				Constants.ASSESSMENT_ITEM_TYPE_MULTI };
		String[][] apNames = { { "景观房", "侧景观房", "一般", "有遮挡" },
				{ "无暗房/南北通风", "南北通风", "一般", "差" }, { "无噪音", "噪音较小", "噪音较大" },
				{ "变电站", "垃圾场", "发射塔" } };
		int[][] apValues = { { 2, 1, 0, -1 }, { 2, 1, 0, -1 }, { 0, -1, -2 },
				{ -2, -2, -2 } };
		for (int i = 0; i < aiNames.length; i++) {
			AssessmentItem ai = new AssessmentItem();
			ai.setId(UUID.randomUUID().toString());
			ai.setName(aiNames[i]);
			ai.setType(aiTypes[i]);
			for (int j = 0; j < apNames[i].length; j++) {
				AssessmentParameter ap = new AssessmentParameter();
				ap.setId(UUID.randomUUID().toString());
				ap.setAssessmentItemId(ai.getId());
				ap.setName(apNames[i][j]);
				ap.setValue(apValues[i][j]);
				apm.insertSelective(ap);
			}
			aim.insertSelective(ai);
		}
	}

	@Override
	public List<AssessmentItem> getAll() {
		AssessmentItemExample aie = new AssessmentItemExample();
		aie.or().andDelFlagEqualTo(false);
		return aim.selectByExample(aie);
	}

	@Override
	public List<AssessmentItem> findByParams(String name, Integer targetPage,
			Integer pageSize) {
		AssessmentItemExample aie = bindAIParams(name);
		if (targetPage != null && pageSize != null) {
			aie.setLimitStart(targetPage * pageSize);
			aie.setLimitSize(pageSize);
		}
		return aim.selectByExample(aie);
	}

	@Override
	public int countByParams(String name) {
		AssessmentItemExample aie = bindAIParams(name);
		return aim.countByExample(aie);
	}

	@Override
	public Map<String, String> addAI(AssessmentItem ai) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (ai == null) {
			resMap.put("msg", "评估项内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(ai);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		ai.setId(UUID.randomUUID().toString());
		ai.setCreater(curUserId);
		ai.setLastEditor(curUserId);
		aim.insertSelective(ai);
		return resMap;
	}

	@Override
	public Map<String, String> updateAI(AssessmentItem ai) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (ai == null) {
			resMap.put("msg", "评估项内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(ai);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		ai.setLastEditor(curUserId);
		aim.updateByPrimaryKeySelective(ai);
		return resMap;
	}

	@Override
	public Map<String, String> batchDel(String aiIds) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isBlank(aiIds)) {
			resMap.put("msg", "删除内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		String[] ids = aiIds.split(Constants.MI_IDS_SPLIT_STRING);
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotBlank(ids[i])) {
				idList.add(ids[i]);
			}
		}
		if (!idList.isEmpty()) {
			AssessmentItemExample aie = new AssessmentItemExample();
			aie.or().andIdIn(idList).andDelFlagEqualTo(false);
			AssessmentItem ai = new AssessmentItem();
			ai.setDelFlag(true);
			ai.setLastEditor(curUserId);
			aim.updateByExampleSelective(ai, aie);

			AssessmentParameterExample ape = new AssessmentParameterExample();
			ape.or().andAssessmentItemIdIn(idList).andDelFlagEqualTo(false);
			AssessmentParameter ap = new AssessmentParameter();
			ap.setDelFlag(true);
			ap.setLastEditor(curUserId);
			apm.updateByExampleSelective(ap, ape);
		}
		return resMap;
	}

	private AssessmentItemExample bindAIParams(String name) {
		AssessmentItemExample aie = new AssessmentItemExample();
		AssessmentItemExample.Criteria cri = aie.createCriteria();
		cri.andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(name)) {
			cri.andNameLike("%" + name + "%");
		}
		return aie;
	}

	private Map<String, String> checkInfo(AssessmentItem ai) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (ai == null) {
			resMap.put("msg", "评估项内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(ai.getType())) {
			resMap.put("msg", "评估项类型不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(ai.getName())) {
			resMap.put("msg", "评估项名称不能为空");
			return resMap;
		}
		return resMap;
	}

}
