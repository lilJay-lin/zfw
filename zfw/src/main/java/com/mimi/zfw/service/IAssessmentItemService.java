package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.AssessmentItem;
import com.mimi.zfw.mybatis.pojo.AssessmentItemExample;

public interface IAssessmentItemService extends
		IBaseService<AssessmentItem, AssessmentItemExample, String> {
	public void initAssessItem();

	public List<AssessmentItem> getAll();

	public List<AssessmentItem> findByParams(String name, Integer targetPage,
			Integer pageSize);

	public int countByParams(String name);

	public Map<String, String> addAI(AssessmentItem ai);

	public Map<String, String> updateAI(AssessmentItem ai);

	public Map<String, String> batchDel(String aiIds);
}
