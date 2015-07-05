package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.AssessmentParameter;
import com.mimi.zfw.mybatis.pojo.AssessmentParameterExample;

public interface IAssessmentParameterService extends
		IBaseService<AssessmentParameter, AssessmentParameterExample, String> {

	List<AssessmentParameter> getAll();
}
