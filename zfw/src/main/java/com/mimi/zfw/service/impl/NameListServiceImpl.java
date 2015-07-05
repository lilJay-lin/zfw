package com.mimi.zfw.service.impl;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.NameListMapper;
import com.mimi.zfw.mybatis.pojo.NameList;
import com.mimi.zfw.mybatis.pojo.NameListExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.INameListService;

@Service
public class NameListServiceImpl extends
		BaseService<NameList, NameListExample, String> implements
		INameListService {

	@Resource
	private NameListMapper nlm;
	
	private boolean showSignUpForm = true;
	
	private String singUpFormTitle = Constants.SIGN_UP_FORM_TITLE_DEFAULT;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<NameList, NameListExample, String> baseDao) {
		this.baseDao = baseDao;
		this.nlm = (NameListMapper) baseDao;
	}

	@Override
	public boolean isShowSignUpForm() {
		return showSignUpForm;
	}

	@Override
	public void setShowSignUpForm(boolean showSignUpForm) {
		this.showSignUpForm = showSignUpForm;
	}

	@Override
	public String getSingUpFormTitle() {
		return singUpFormTitle;
	}

	@Override
	public void setSingUpFormTitle(String singUpFormTitle) {
		this.singUpFormTitle = singUpFormTitle;
	}

	@Override
	public String signUp(String name, String tel) {
		if (!checkNameFormat(name)) {
			return "名称格式有误";
		}
		if (!checkTelFormat(tel)) {
			return "电话格式有误";
		}
		NameListExample nle = new NameListExample();
		nle.or().andNameEqualTo(name).andPhoneNumEqualTo(tel)
				.andDelFlagEqualTo(false);
		if (nlm.countByExample(nle) > 0) {
			return "您已经报过名";
		}
		NameList nl = new NameList();
		nl.setId(UUID.randomUUID().toString());
		nl.setName(name);
		nl.setPhoneNum(tel);
		nlm.insertSelective(nl);
		return null;
	}

	private boolean checkNameFormat(String name) {
		return StringUtils.isNotBlank(name);
	}

	private boolean checkTelFormat(String tel) {
		String regex = "^1[0-9]{10}$";
		return checkValueFormat(tel, regex);
	}

	private boolean checkValueFormat(String value, String regex) {
		if (value != null && !"".equals(value.trim())) {
			return value.matches(regex);
		}
		return false;
	}
}
