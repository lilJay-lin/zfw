package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.User;

public interface IUserService {
	public User getUserById(String id);
	
	public void batchAddUsers(List<User> users);
	
	public User findByUsername(String user);
	
	public void save(User user);
	
	public List<User> listAll();
	
	public void initUser();
	

}
