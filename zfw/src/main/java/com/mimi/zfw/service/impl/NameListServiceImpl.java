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
import com.mimi.zfw.mybatis.dao.NameListMapper;
import com.mimi.zfw.mybatis.pojo.NameList;
import com.mimi.zfw.mybatis.pojo.NameListExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.INameListService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.FormatUtil;

@Service
public class NameListServiceImpl extends
	BaseService<NameList, NameListExample, String> implements
	INameListService {

    @Resource
    private NameListMapper nlm;

    private boolean showSignUpForm = true;

    private String singUpFormTitle = Constants.SIGN_UP_FORM_TITLE_DEFAULT;

    @Resource
    private IUserService userService;

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

    @Override
    public List<NameList> findNameListByExample(NameListExample example,
	    Integer curPage, Integer pageSize) {

	if (example == null) {
	    example = new NameListExample();
	    example.or().andDelFlagEqualTo(false);
	}

	example.setLimitStart(curPage * pageSize);
	example.setLimitSize(pageSize);

	example.setOrderByClause("update_date desc");
	List<NameList> nls = nlm.selectByExample(example);

	return nls;
    }

    @Override
    public int countNameListByExample(NameListExample example) {

	if (example == null) {
	    example = new NameListExample();
	    example.or().andDelFlagEqualTo(false);
	}
	return nlm.countByExample(example);
    }

    @Override
    public Map<String, String> addNameList(NameList nl) {

	Map<String, String> resMap = new HashMap<String, String>();
	resMap.put("msg", "");

	if (nl == null) {
	    resMap.put("msg", "名单信息不能为空");
	}

	resMap = this.checkNameList(nl);

	if (StringUtils.isNotBlank(resMap.get("msg"))) {
	    return resMap;
	}
	
	NameListExample nle = new NameListExample();
	nle.or().andNameEqualTo(nl.getName()).andPhoneNumEqualTo(nl.getPhoneNum())
		.andDelFlagEqualTo(false);
	if (nlm.countByExample(nle) > 0) {
	    resMap.put("msg", "您已经报过名");
	    return resMap;
	}
	
	nl.setDelFlag(false);
	nl.setCreater(userService.getCurUserId());
	nl.setLastEditor(userService.getCurUserId());

	this.save(nl);

	return resMap;
    }

    @Override
    public Map<String, String> updateNameList(NameList nl) {

	Map<String, String> resMap = new HashMap<String, String>();
	resMap.put("msg", "");

	String nlid = nl.getId();

	if (StringUtils.isBlank(nlid) || nlm.selectByPrimaryKey(nlid) == null) {
	    resMap.put("msg", "名单信息不存在!");
	}

	resMap = this.checkNameList(nl);

	if (StringUtils.isNotBlank(resMap.get("msg"))) {
	    return resMap;
	}
	NameListExample nle = new NameListExample();
	nle.or().andNameEqualTo(nl.getName()).andPhoneNumEqualTo(nl.getPhoneNum())
		.andDelFlagEqualTo(false);
	if (nlm.countByExample(nle) > 0) {
	    resMap.put("msg", "您已经报过名");
	    return resMap;
	}
	
	nl.setLastEditor(userService.getCurUserId());

	nlm.updateByPrimaryKeySelective(nl);

	return resMap;

    }

    @Override
    public Map<String, String> checkNameList(NameList nl) {
	Map<String, String> resMap = new HashMap<String, String>();
	resMap.put("msg", "");
	String name = nl.getName();
	String errStr = FormatUtil.checkFormate(name,true, FormatUtil.MAX_LENGTH_COMMON_SHORT_L2, "姓名");
	if(StringUtils.isNotBlank(errStr)){
	    resMap.put("field","name");
	    resMap.put("msg", errStr);
	    return resMap;
	}
	String phoneNum = nl.getPhoneNum();
	errStr = FormatUtil.checkFormat(phoneNum, FormatUtil.REGEX_COMMON_PHONENUM,true, "电话");
	if(StringUtils.isNotBlank(errStr)){
	    resMap.put("field","phoneNum");
	    resMap.put("msg", errStr);
	    return resMap;
	}
	
	NameListExample example = new NameListExample();
	example.or().andNameEqualTo(name).andPhoneNumEqualTo(phoneNum).andDelFlagEqualTo(false);
	List<NameList> list = nlm.selectByExample(example);
	if(list != null && list.size()>0){
	    for(NameList n : list){
		if(!StringUtils.equals(n.getId(), nl.getId())){
		    resMap.put("msg", "指定名称和电话的记录已存在");
		    return resMap;
		}
	    }
	}
	
	
	return resMap;
    }

    @Override
    public int updateBatchNameList(String nlids, NameList nl) {

	if (nl == null) {
	    return 0;
	}

	String[] ids = nlids.split(Constants.MI_IDS_SPLIT_STRING);
	List<String> rList = new ArrayList<String>();
	for (String id : ids) {
	    rList.add(id);
	}

	NameListExample ue = new NameListExample();
	ue.or().andIdIn(rList).andDelFlagEqualTo(false);

	nl.setLastEditor(userService.getCurUserId());

	int row = nlm.updateByExampleSelective(nl, ue);

	return row;
    }
    

}
