package com.mimi.zfw.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.mimi.zfw.web.shiro.authc.UniqueidFormAuthenticationFilter;
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
	ue.or().andNameEqualTo(name);
	List<User> users = um.selectByExample(ue);
	if (users == null || users.isEmpty()) {
	    return null;
	}
	return users.get(0);
    }

    @Override
    public void initUser() {

	int count = um.countByExample(null);
	if (count < 1) {
	    Date nowDate = new Date(System.currentTimeMillis());
	    User user = new User();
	    user.setId(UUID.randomUUID().toString());
	    user.setName(Constants.USER_DEFAULT_ADMIN_NAME);
	    user.setCreateDate(nowDate);
	    String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
	    SimpleHash sh = new SimpleHash(Constants.SHIRO_HASH_ALGORITHM_NAME,
		    MD5Util.MD5(Constants.USER_DEFAULT_ADMIN_PASSWORD),
		    user.getId() + salt, Constants.SHIRO_HASH_ITERATIONS);
	    user.setPassword(sh.toString());
	    user.setSalt(salt);
	    um.insert(user);

	    RoleExample re = new RoleExample();
	    re.or().andNameEqualTo(Constants.ROLE_NAME_ADMIN_DEFAULT);
	    List<Role> roles = rm.selectByExample(re);
	    for (int i = 0; i < roles.size(); i++) {
		RelationUserAndRole rur = new RelationUserAndRole();
		rur.setId(UUID.randomUUID().toString());
		rur.setUserId(user.getId());
		rur.setRoleId(roles.get(i).getId());
		rur.setCreateDate(nowDate);
		rurm.insert(rur);
	    }
	}
    }

    @Override
    public User saveOriginUser(User user) {
	Date nowDate = new Date(System.currentTimeMillis());
	user.setId(UUID.randomUUID().toString());
	user.setCreateDate(nowDate);
	String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
	SimpleHash sh = new SimpleHash(Constants.SHIRO_HASH_ALGORITHM_NAME,
		user.getPassword(), user.getId() + salt,
		Constants.SHIRO_HASH_ITERATIONS);
	user.setPassword(sh.toString());
	user.setSalt(salt);
	um.insert(user);

	RoleExample re = new RoleExample();
	re.or().andNameEqualTo(Constants.ROLE_NAME_NORMAL_DEFAULT);
	List<Role> roles = rm.selectByExample(re);
	for (int i = 0; i < roles.size(); i++) {
	    RelationUserAndRole rur = new RelationUserAndRole();
	    rur.setId(UUID.randomUUID().toString());
	    rur.setUserId(user.getId());
	    rur.setRoleId(roles.get(i).getId());
	    rur.setCreateDate(nowDate);
	    rurm.insert(rur);
	}
	return user;
    }

    @Override
    public void login(String name, String password) {
	UniqueidUsernamePasswordToken token = new UniqueidUsernamePasswordToken();  
	    token.setUsername(name);  
	    token.setPassword(password.toCharArray());  
	    SecurityUtils.getSubject().login(token);  
    }

    @Override
    public boolean checkNameFormat(String name) {
	String regex = "^[a-z]([a-z0-9_]){6,32}";
	return checkValueFormat(name,regex);
    }

    @Override
    public boolean checkPhoneNumFormat(String phoneNum) {
	String regex = "^1[0-9]{10}$";
	return checkValueFormat(phoneNum,regex);
    }

    @Override
    public boolean checkEamilFormat(String eamil) {
	String regex = "([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+";
	return checkValueFormat(eamil,regex);
    }
    
    private boolean checkValueFormat(String value,String regex){
	if(value!=null && !"".equals(value.trim())){
	   return value.matches(regex);
	}
	return false;
    }

    @Override
    public User findByEmail(String email) {
	UserExample ue = new UserExample();
	ue.or().andEmailEqualTo(email);
	List<User> users = um.selectByExample(ue);
	if (users == null || users.isEmpty()) {
	    return null;
	}
	return users.get(0);
    }

    @Override
    public User findByPhoneNum(String phoneNum) {
	UserExample ue = new UserExample();
	ue.or().andPhoneNumEqualTo(phoneNum);
	List<User> users = um.selectByExample(ue);
	if (users == null || users.isEmpty()) {
	    return null;
	}
	return users.get(0);
    }

}
