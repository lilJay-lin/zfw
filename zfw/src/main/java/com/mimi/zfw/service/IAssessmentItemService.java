package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.AssessmentItem;
import com.mimi.zfw.mybatis.pojo.AssessmentItemExample;

public interface IAssessmentItemService extends
		IBaseService<AssessmentItem, AssessmentItemExample, String> {
	public void initAssessItem();

	public List<AssessmentItem> getAll();
}
