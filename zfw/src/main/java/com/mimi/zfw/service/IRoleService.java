package com.mimi.zfw.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mimi.zfw.pojo.Role;

public interface IRoleService{
	public Role getRoleById(String id);

	public void batchAddRoles(List<Role> roles);
	
	public List<Role> listAll();
	
	public int getK();
}
