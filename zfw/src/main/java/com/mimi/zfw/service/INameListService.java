package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.NameList;
import com.mimi.zfw.mybatis.pojo.NameListExample;

public interface INameListService extends
	IBaseService<NameList, NameListExample, String> {

    String signUp(String name, String tel);

    public boolean isShowSignUpForm();

    public void setShowSignUpForm(boolean showSignUpForm);

    public String getSingUpFormTitle();

    public void setSingUpFormTitle(String singUpFormTitle);

    public List<NameList> findNameListByExample(NameListExample example,
	    Integer curPage, Integer pageSize);

    public int countNameListByExample(NameListExample example);

    public Map<String, String> addNameList(NameList nl);

    public Map<String, String> updateNameList(NameList nl);

    public Map<String, String> checkNameList(NameList nl);

    public int updateBatchNameList(String nlids, NameList nl);

}
