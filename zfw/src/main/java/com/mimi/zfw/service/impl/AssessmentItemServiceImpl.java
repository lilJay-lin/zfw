package com.mimi.zfw.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.AssessmentItemMapper;
import com.mimi.zfw.mybatis.dao.AssessmentParameterMapper;
import com.mimi.zfw.mybatis.pojo.AssessmentItem;
import com.mimi.zfw.mybatis.pojo.AssessmentItemExample;
import com.mimi.zfw.mybatis.pojo.AssessmentParameter;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IAssessmentItemService;

@Service
public class AssessmentItemServiceImpl extends
		BaseService<AssessmentItem, AssessmentItemExample, String> implements IAssessmentItemService {

	@Resource
	private AssessmentItemMapper aim;
	@Resource
	private AssessmentParameterMapper apm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<AssessmentItem, AssessmentItemExample, String> baseDao) {
		this.baseDao = baseDao;
		this.aim = (AssessmentItemMapper) baseDao;
	}

	@Override
	public void initAssessItem() {
		AssessmentItemExample ie = new AssessmentItemExample();
		ie.or().andDelFlagEqualTo(false);
		int count = aim.countByExample(ie);
		if(count<1){
			initTestData();
		}
	}
	
	private void initTestData(){
		String[] aiNames = {"景观情况","采光通风","噪音影响","厌恶因素"};
		String[] aiTypes = {Constants.ASSESSMENT_ITEM_TYPE_RADIO,Constants.ASSESSMENT_ITEM_TYPE_RADIO,Constants.ASSESSMENT_ITEM_TYPE_RADIO,Constants.ASSESSMENT_ITEM_TYPE_MULTI};
		String[][] apNames = {{"景观房","侧景观房","一般","有遮挡"},{"无暗房/南北通风","南北通风","一般","差"},{"无噪音","噪音较小","噪音较大"},{"变电站","垃圾场","发射塔"}};
		int[][] apValues = {{2,1,0,-1},{2,1,0,-1},{0,-1,-2},{-2,-2,-2}};
		for(int i=0;i<aiNames.length;i++){
			AssessmentItem ai = new AssessmentItem();
			ai.setId(UUID.randomUUID().toString());
			ai.setName(aiNames[i]);
			ai.setType(aiTypes[i]);
			for(int j=0;j<apNames[i].length;j++){
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


}
