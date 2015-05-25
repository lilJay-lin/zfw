package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.Permission;

public interface IPermissionService {
	public void initPermission();
	
	public List<Permission> getPermissionsByRoleIds(List<String> ids);
}
