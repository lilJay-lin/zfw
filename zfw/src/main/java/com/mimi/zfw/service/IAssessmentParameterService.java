package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.AssessmentParameter;
import com.mimi.zfw.mybatis.pojo.AssessmentParameterExample;

public interface IAssessmentParameterService extends
		IBaseService<AssessmentParameter, AssessmentParameterExample, String> {

	List<AssessmentParameter> getAll();

	List<AssessmentParameter> findByParams(String aiId, String name,
			Integer targetPage, Integer pageSize);

	int countByParams(String aiId, String name);

	Map<String, String> addAP(AssessmentParameter ap);

	Map<String, String> updateAP(AssessmentParameter ap);

	Map<String, String> batchDel(String aiId, String apIds);
}
