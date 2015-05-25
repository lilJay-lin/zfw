package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.Role;

public interface IRoleService{
	public Role getRoleById(String id);

	public void batchAddRoles(List<Role> roles);
	
	public List<Role> listAll();
	
	public int getK();
	
	public void initRole();
	
	public List<Role> getRolesByUserId(String id);
}
