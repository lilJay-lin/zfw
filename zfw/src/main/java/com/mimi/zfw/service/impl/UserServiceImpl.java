package com.mimi.zfw.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

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

@Service
public class UserServiceImpl extends BaseService<User, UserExample, String> implements IUserService {
	@Resource
	private UserMapper um;
	@Resource
	private RoleMapper rm;
	@Resource
	private RelationUserAndRoleMapper rurm;

	@Resource
	@Override
	public void setBaseDao(
		IBaseDao<User, UserExample, String> baseDao) {
	    this.baseDao = baseDao;
	    this.um = (UserMapper) baseDao;
	}
	
	@Override
	public User findByUsername(String name) {
	    UserExample ue = new UserExample();
	    ue.or().andNameEqualTo(name);
	    List<User> users = um.selectByExample(ue);
	    if(users==null || users.isEmpty()){
		return null;
	    }
	    return users.get(0);
	}
	@Override
	public void initUser() {

		int count = um.countByExample(null);
		if(count<1){
			Date nowDate = new Date(System.currentTimeMillis());
			User user = new User();
			user.setId(UUID.randomUUID().toString());
			user.setName("admin");
			user.setCreateDate(nowDate);
			String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
			SimpleHash sh = new SimpleHash(Constants.SHIRO_HASH_ALGORITHM_NAME, MD5Util.MD5("123456"), user.getId()+salt, Constants.SHIRO_HASH_ITERATIONS);
//			SimpleHash sh = new SimpleHash(Constants.SHIRO_HASH_ALGORITHM_NAME, "123456", user.getId()+salt, Constants.SHIRO_HASH_ITERATIONS);
//			SimpleHash sh = new SimpleHash(Constants.SHIRO_HASH_ALGORITHM_NAME, "e10adc3949ba59abbe56e057f20f883e", user.getId()+salt, Constants.SHIRO_HASH_ITERATIONS);
			user.setPassword(sh.toString());
			user.setSalt(salt);
			um.insert(user);
			
			RoleExample re = new RoleExample();
			re.or().andNameEqualTo("超级管理员");
			List<Role> roles = rm.selectByExample(re);
			for(int i=0;i<roles.size();i++){
				RelationUserAndRole rur = new RelationUserAndRole();
				rur.setId(UUID.randomUUID().toString());
				rur.setUserId(user.getId());
				rur.setRoleId(roles.get(i).getId());
				rur.setCreateDate(nowDate);
				rurm.insert(rur);
			}
		}
	}
	

}
