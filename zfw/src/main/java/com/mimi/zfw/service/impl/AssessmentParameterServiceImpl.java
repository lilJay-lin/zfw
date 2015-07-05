package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.AssessmentParameterMapper;
import com.mimi.zfw.mybatis.pojo.AssessmentParameter;
import com.mimi.zfw.mybatis.pojo.AssessmentParameterExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IAssessmentParameterService;

@Service
public class AssessmentParameterServiceImpl extends
		BaseService<AssessmentParameter, AssessmentParameterExample, String>
		implements IAssessmentParameterService {

	@Resource
	private AssessmentParameterMapper apm;

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
		return apm.selectByExample(ape);
	}

}
