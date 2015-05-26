package com.mimi.zfw.service;

import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.mybatis.pojo.UserExample;

public interface IUserService extends IBaseService<User, UserExample, String>{
	
	public User findByUsername(String user);
	
	public void initUser();
	

}
