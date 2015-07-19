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
import com.mimi.zfw.mybatis.dao.AssessmentParameterMapper;
import com.mimi.zfw.mybatis.pojo.AssessmentParameter;
import com.mimi.zfw.mybatis.pojo.AssessmentParameterExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IAssessmentParameterService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.FormatUtil;

@Service
public class AssessmentParameterServiceImpl extends
		BaseService<AssessmentParameter, AssessmentParameterExample, String>
		implements IAssessmentParameterService {

	@Resource
	private AssessmentParameterMapper apm;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<AssessmentParameter, AssessmentParameterExample, String> baseDao) {
		this.baseDao = baseDao;
		this.apm = (AssessmentParameterMapper) baseDao;
	}

	@Override
	public List<AssessmentParameter> getAll() {
		AssessmentParameterExample ape = new AssessmentParameterExample();
		ape.or().andDelFlagEqualTo(false);
		ape.setOrderByClause("update_date desc");
		return apm.selectByExample(ape);
	}

	@Override
	public List<AssessmentParameter> findByParams(String aiId, String name,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(aiId)) {
			return null;
		}
		AssessmentParameterExample ape = bindAssessmentParameterParams(aiId,
				name);
		if (targetPage != null && pageSize != null) {
			ape.setLimitStart(targetPage * pageSize);
			ape.setLimitSize(pageSize);
		}
		ape.setOrderByClause("update_date desc");
		return apm.selectByExample(ape);
	}

	@Override
	public int countByParams(String aiId, String name) {
		if (StringUtils.isBlank(aiId)) {
			return 0;
		}
		AssessmentParameterExample ape = bindAssessmentParameterParams(aiId,
				name);
		return apm.countByExample(ape);
	}

	private AssessmentParameterExample bindAssessmentParameterParams(
			String aiId, String name) {
		AssessmentParameterExample ape = new AssessmentParameterExample();
		AssessmentParameterExample.Criteria cri = ape.createCriteria();
		cri.andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(aiId)) {
			cri.andAssessmentItemIdEqualTo(aiId);
		}
		if (StringUtils.isNotBlank(name)) {
			cri.andNameLike("%" + name + "%");
		}
		return ape;
	}

	@Override
	public Map<String, String> addAP(AssessmentParameter ap) {
		Map<String, String> resMap = new HashMap<String, String>();
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(ap);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		ap.setCreater(curUserId);
		ap.setLastEditor(curUserId);
		ap.setId(UUID.randomUUID().toString());
		apm.insertSelective(ap);
		return resMap;
	}

	@Override
	public Map<String, String> updateAP(AssessmentParameter ap) {
		Map<String, String> resMap = new HashMap<String, String>();
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(ap);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		ap.setLastEditor(curUserId);
		apm.updateByPrimaryKeySelective(ap);
		return resMap;
	}

	@Override
	public Map<String, String> batchDel(String aiId, String apIds) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isBlank(apIds)) {
			resMap.put("msg", "删除内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		String[] ids = apIds.split(Constants.MI_IDS_SPLIT_STRING);
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotBlank(ids[i])) {
				idList.add(ids[i]);
			}
		}
		if (!idList.isEmpty()) {
			AssessmentParameterExample ape = new AssessmentParameterExample();
			ape.or().andIdIn(idList).andDelFlagEqualTo(false);
			AssessmentParameter ap = new AssessmentParameter();
			ap.setDelFlag(true);
			ap.setLastEditor(curUserId);
			apm.updateByExampleSelective(ap, ape);
		}
		return resMap;
	}

	private Map<String, String> checkInfo(AssessmentParameter ap) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (ap == null) {
			resMap.put("msg", "评估参数内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(ap.getAssessmentItemId())) {
			resMap.put("msg", "评估参数所属评估项不能为空");
			return resMap;
		}
		
		String name = ap.getName();
		String errStr = FormatUtil.checkFormate(name,true, FormatUtil.MAX_LENGTH_COMMON_SHORT_L2, "评估参数名称");
		if(StringUtils.isNotBlank(errStr)){
		    resMap.put("field","name");
		    resMap.put("msg", errStr);
		    return resMap;
		}
		
		Integer value = ap.getValue();
		errStr = FormatUtil.checkFormat(value, FormatUtil.REGEX_COMMON_PGPARAMETERVALUE, true, "评估参数值");
		if(StringUtils.isNotBlank(errStr)){
			resMap.put("field","value");
			resMap.put("msg", errStr);
			return resMap;
		}
		
		String description = ap.getDescription();
		errStr = FormatUtil.checkFormate(description,false,FormatUtil.MAX_LENGTH_COMMON_NORMAL_L2, "描述");
		if(StringUtils.isNotBlank(errStr)){
			resMap.put("field","description");
			resMap.put("msg", errStr);
			return resMap;
		}
		
		return resMap;
	}
}
