package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.mybatis.pojo.RoleExample;
import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.mybatis.pojo.UserExample;

public interface IRoleService extends IBaseService<Role, RoleExample, String>{
	public int getK();
	
	public void initRole();
	
	public List<Role> getRolesByUserId(String id);
	    
	public List<Role> findRoleByExample(RoleExample example ,Integer curPage,Integer pageSize);
	    
	public int countRoleByExample(RoleExample example);
	
	public List<Role> findRolesByUserId(String id,Integer curPage,Integer pageSize);
	
	public int countRolesByUserId(String id);
}
