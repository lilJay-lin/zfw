package com.mimi.zfw.service;

import com.mimi.zfw.mybatis.pojo.NameList;
import com.mimi.zfw.mybatis.pojo.NameListExample;

public interface INameListService extends
		IBaseService<NameList, NameListExample, String> {

	String signUp(String name, String tel);

	public boolean isShowSignUpForm();

	public void setShowSignUpForm(boolean showSignUpForm);

	public String getSingUpFormTitle();

	public void setSingUpFormTitle(String singUpFormTitle);
}
