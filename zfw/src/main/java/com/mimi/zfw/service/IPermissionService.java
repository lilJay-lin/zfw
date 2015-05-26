package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.Permission;
import com.mimi.zfw.mybatis.pojo.PermissionExample;

public interface IPermissionService extends IBaseService<Permission, PermissionExample, String>{
	public void initPermission();
	
	public List<Permission> getPermissionsByRoleIds(List<String> ids);
}
