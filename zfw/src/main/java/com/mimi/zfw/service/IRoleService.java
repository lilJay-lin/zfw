package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.mybatis.pojo.RoleExample;

public interface IRoleService extends IBaseService<Role, RoleExample, String>{
	public int getK();
	
	public void initRole();
	
	public List<Role> getRolesByUserId(String id);
}
