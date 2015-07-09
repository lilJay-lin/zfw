package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.Permission;
import com.mimi.zfw.mybatis.pojo.PermissionExample;
import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.mybatis.pojo.RoleExample;

public interface IPermissionService extends IBaseService<Permission, PermissionExample, String>{
	public void initPermission();
	
	public List<Permission> getPermissionsByRoleIds(List<String> ids);
	
	public List<Permission> getPermissionsByRoleId(String id);
	    
	public List<Permission> findPermissionByExample(PermissionExample example ,Integer curPage,Integer pageSize);
	    
	public int countPermissionByExample(PermissionExample example);
	
	public List<Permission> findPermissionByRoleId(String id,Integer curPage,Integer pageSize);
	
	public int countPermissionById(String id);
}
