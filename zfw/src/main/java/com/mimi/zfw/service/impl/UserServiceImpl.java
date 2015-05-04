package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.dao.UserMapper;
import com.mimi.zfw.pojo.User;
import com.mimi.zfw.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserMapper um;
	@Override
	public User getUserById(String id) {
		// TODO Auto-generated method stub
		return this.um.selectByPrimaryKey(id);
	}
	@Override
	public void batchAddUsers(List<User> users) {
	    // TODO Auto-generated method stub
	    for (User user : users) {
		um.insert(user);
	    }
	}
	@Override
	public User findByUsername(String name) {
	    // TODO Auto-generated method stub
	    
	    return um.selectByName(name);
	}
	@Override
	public void save(User user) {
	    // TODO Auto-generated method stub
	    um.insert(user);
	}
	@Override
	public List<User> listAll() {
	    // TODO Auto-generated method stub
	    return um.selectAll();
	}

}
