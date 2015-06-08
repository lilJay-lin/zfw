package com.mimi.zfw.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.RelationUserAndRoleMapper;
import com.mimi.zfw.mybatis.dao.RoleMapper;
import com.mimi.zfw.mybatis.dao.UserMapper;
import com.mimi.zfw.mybatis.pojo.RelationUserAndRole;
import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.mybatis.pojo.RoleExample;
import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.mybatis.pojo.UserExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.MD5Util;
import com.mimi.zfw.web.shiro.authc.UniqueidUsernamePasswordToken;

@Service
public class UserServiceImpl extends BaseService<User, UserExample, String>
	implements IUserService {
    @Resource
    private UserMapper um;
    @Resource
    private RoleMapper rm;
    @Resource
    private RelationUserAndRoleMapper rurm;

    @Resource
    @Override
    public void setBaseDao(IBaseDao<User, UserExample, String> baseDao) {
	this.baseDao = baseDao;
	this.um = (UserMapper) baseDao;
    }

    @Override
    public User findByName(String name) {
	UserExample ue = new UserExample();
	ue.or().andNameEqualTo(name).andDelFlagEqualTo(false);
	List<User> users = um.selectByExample(ue);
	if (users == null || users.isEmpty()) {
	    return null;
	}
	return users.get(0);
    }

    @Override
    public void initUser() {
	UserExample ue = new UserExample();
	ue.or().andDelFlagEqualTo(false);
	int count = um.countByExample(null);
	if (count < 1) {
	    Date nowDate = new Date(System.currentTimeMillis());
	    User user = new User();
	    user.setId(UUID.randomUUID().toString());
	    user.setName(Constants.USER_DEFAULT_ADMIN_NAME);
	    user.setCreateDate(nowDate);
	    String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
	    user.setPassword(getFinalPwd(user.getId(), salt,
		    MD5Util.MD5(Constants.USER_DEFAULT_ADMIN_PASSWORD)));
	    user.setSalt(salt);
	    um.insertSelective(user);

	    RoleExample re = new RoleExample();
	    re.or().andNameEqualTo(Constants.ROLE_NAME_ADMIN_DEFAULT)
		    .andDelFlagEqualTo(false);
	    List<Role> roles = rm.selectByExample(re);
	    for (int i = 0; i < roles.size(); i++) {
		RelationUserAndRole rur = new RelationUserAndRole();
		rur.setId(UUID.randomUUID().toString());
		rur.setUserId(user.getId());
		rur.setRoleId(roles.get(i).getId());
		rur.setCreateDate(nowDate);
		rurm.insertSelective(rur);
	    }
	    testDataInit();
	}
    }

    private void testDataInit() {
	String[] types = { Constants.ROLE_NAME_NORMAL_DEFAULT,
		Constants.ROLE_NAME_ADMIN_DEFAULT };
	String[][] names = {
		{ "15017528974", "18718326027", "12345678910", "11111111111",
			"15918402615" }, { "mimi", "linxj", "deleted_admin" } };
	String[][] pwds = {
		{ "123123", "123123", "123123", "123123", "123123" },
		{ "123132", "123123", "123123" } };
	Date nowDate = new Date(System.currentTimeMillis());
	for (int j = 0; j < types.length; j++) {
	    RoleExample re = new RoleExample();
	    re.or().andNameEqualTo(types[j]).andDelFlagEqualTo(false);
	    List<Role> roles = rm.selectByExample(re);
	    if (roles != null && !roles.isEmpty()) {
		String roleId = roles.get(0).getId();
		for (int i = 0; i < names[j].length; i++) {
		    User user = new User();
		    user.setId(UUID.randomUUID().toString());
		    if (Constants.ROLE_NAME_NORMAL_DEFAULT.equals(types[j])) {
			user.setPhoneNum(names[j][i]);
		    } else {
			user.setName(names[j][i]);
		    }
		    user.setCreateDate(nowDate);
		    String salt = new SecureRandomNumberGenerator().nextBytes()
			    .toHex();
		    user.setPassword(getFinalPwd(user.getId(), salt,
			    MD5Util.MD5(pwds[j][i])));
		    user.setSalt(salt);

		    RelationUserAndRole rur = new RelationUserAndRole();
		    rur.setId(UUID.randomUUID().toString());
		    rur.setUserId(user.getId());
		    rur.setRoleId(roleId);
		    rur.setCreateDate(nowDate);

		    if ("11111111111".equals(names[j][i])
			    || "deleted_admin".equals(names[j][i])) {
			user.setDelFlag(true);
			rur.setDelFlag(true);
		    }
		    if("12345678910".equals(names[j][i])){
			user.setLocked(true);
		    }
		    
		    um.insertSelective(user);
		    rurm.insertSelective(rur);
		}
	    }
	}
    }

    @Override
    public User saveOriginUser(User user) {
	Date nowDate = new Date(System.currentTimeMillis());
	user.setId(UUID.randomUUID().toString());
	user.setCreateDate(nowDate);
	String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
	user.setPassword(getFinalPwd(user.getId(), salt, user.getPassword()));
	user.setSalt(salt);
	um.insertSelective(user);

	RoleExample re = new RoleExample();
	re.or().andNameEqualTo(Constants.ROLE_NAME_NORMAL_DEFAULT)
		.andDelFlagEqualTo(false);
	List<Role> roles = rm.selectByExample(re);
	for (int i = 0; i < roles.size(); i++) {
	    RelationUserAndRole rur = new RelationUserAndRole();
	    rur.setId(UUID.randomUUID().toString());
	    rur.setUserId(user.getId());
	    rur.setRoleId(roles.get(i).getId());
	    rur.setCreateDate(nowDate);
	    rurm.insertSelective(rur);
	}
	return user;
    }

    @Override
    public void login(String name, String password) {
	UniqueidUsernamePasswordToken token = new UniqueidUsernamePasswordToken();
	token.setUsername(name);
	token.setPassword(password.toCharArray());
	token.setLoginType(Constants.LOGIN_TYPE_PWD);
	SecurityUtils.getSubject().login(token);
    }

    @Override
    public boolean checkNameFormat(String name) {
	String regex = "^[a-z]([a-z0-9_]){4,32}";
	return checkValueFormat(name, regex);
    }

    @Override
    public boolean checkPhoneNumFormat(String phoneNum) {
	String regex = "^1[0-9]{10}$";
	return checkValueFormat(phoneNum, regex);
    }

    @Override
    public boolean checkEamilFormat(String eamil) {
	String regex = "([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+";
	return checkValueFormat(eamil, regex);
    }

    private boolean checkValueFormat(String value, String regex) {
	if (value != null && !"".equals(value.trim())) {
	    return value.matches(regex);
	}
	return false;
    }

    @Override
    public User findByEmail(String email) {
	UserExample ue = new UserExample();
	ue.or().andEmailEqualTo(email).andDelFlagEqualTo(false);
	List<User> users = um.selectByExample(ue);
	if (users == null || users.isEmpty()) {
	    return null;
	}
	return users.get(0);
    }

    @Override
    public User findByPhoneNum(String phoneNum) {
	UserExample ue = new UserExample();
	ue.or().andPhoneNumEqualTo(phoneNum).andDelFlagEqualTo(false);
	List<User> users = um.selectByExample(ue);
	if (users == null || users.isEmpty()) {
	    return null;
	}
	return users.get(0);
    }

    @Override
    public User findByLoginName(String loginName) {
	if (checkPhoneNumFormat(loginName)) {
	    return findByPhoneNum(loginName);
	} else if (checkNameFormat(loginName)) {
	    return findByName(loginName);
	} else if (checkEamilFormat(loginName)) {
	    return findByEmail(loginName);
	}
	return null;
    }

    @Override
    public void login(String loginName) {
	UniqueidUsernamePasswordToken token = new UniqueidUsernamePasswordToken();
	token.setUsername(loginName);
	token.setLoginType(Constants.LOGIN_TYPE_CAPTCHA);
	SecurityUtils.getSubject().login(token);
    }

    @Override
    public String updatePassword(User user) {
	UserExample ue = new UserExample();
	if (StringUtils.isNotBlank(user.getId())) {
	    ue.or().andIdEqualTo(user.getId()).andDelFlagEqualTo(false);
	} else if (StringUtils.isNotBlank(user.getPhoneNum())) {
	    ue.or().andPhoneNumEqualTo(user.getPhoneNum())
		    .andDelFlagEqualTo(false);
	} else if (StringUtils.isNotBlank(user.getName())) {
	    ue.or().andNameEqualTo(user.getName()).andDelFlagEqualTo(false);
	} else if (StringUtils.isNotBlank(user.getEmail())) {
	    ue.or().andEmailEqualTo(user.getEmail()).andDelFlagEqualTo(false);
	} else {
	    return "登录名不合法";
	}
	if (StringUtils.isBlank(user.getPassword())) {
	    return "密码不合法";
	}
	List<User> userList = um.selectByExample(ue);
	if (userList == null || userList.isEmpty()) {
	    return "用户不存在";
	}
	User oldUser = userList.get(0);
	oldUser.setPassword(getFinalPwd(oldUser.getId(), oldUser.getSalt(),
		user.getPassword()));

	String idStr = getCurUserId();
	if (StringUtils.isBlank(idStr)) {
	    idStr = oldUser.getId();
	}
	Date nowDate = new Date(System.currentTimeMillis());
	oldUser.setUpdateDate(nowDate);
	oldUser.setLastEditor(idStr);

	um.updateByExampleSelective(oldUser, ue);

	return "";
    }

    private String getFinalPwd(String id, String salt, String pwd) {
	SimpleHash sh = new SimpleHash(Constants.SHIRO_HASH_ALGORITHM_NAME,
		pwd, id + salt, Constants.SHIRO_HASH_ITERATIONS);
	return sh.toString();
    }

    private String getCurUserId() {
	String idStr = "";
	try {
	    SecurityUtils.getSubject().getPrincipals().asSet();
	    Set<String> set = SecurityUtils.getSubject().getPrincipals()
		    .asSet();
	    Iterator<String> it = set.iterator();
	    int i = 0;
	    while (it.hasNext()) {
		i++;
		String tempStr = it.next();
		if (i == 2) {
		    idStr = tempStr;
		}
	    }
	} catch (Exception e) {

	}
	return idStr;
    }

    @Override
    public String updateCurUserHeadImgUrl(String headImgUrl) {
	String idStr = getCurUserId();
	if (StringUtils.isBlank(idStr)) {
	    return "未登录";
	}
	if (StringUtils.isBlank(headImgUrl)) {
	    return "头像路径错误";
	}
	UserExample ue = new UserExample();
	ue.or().andIdEqualTo(idStr).andDelFlagEqualTo(false);
	User user = new User();
	user.setHeadImgUrl(headImgUrl);
	um.updateByExampleSelective(user, ue);
	return "";
    }

    @Override
    public User getCurUser() {
	String idStr = getCurUserId();
	if (StringUtils.isBlank(idStr)) {
	    return null;
	}
	UserExample ue = new UserExample();
	ue.or().andIdEqualTo(idStr).andDelFlagEqualTo(false);
	List<User> users = um.selectByExample(ue);
	if (users == null || users.isEmpty()) {
	    return null;
	}
	return users.get(0);
    }

    @Override
    public boolean isLogined() {
	return SecurityUtils.getSubject().isAuthenticated();
    }

}
