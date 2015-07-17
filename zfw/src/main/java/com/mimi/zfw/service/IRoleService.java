package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.mybatis.pojo.RoleExample;

public interface IRoleService extends IBaseService<Role, RoleExample, String> {
	public void initRole();

	public List<Role> getRolesByUserId(String id);

	public List<Role> findRolesByUserId(String id, Integer curPage,
			Integer pageSize);

	public int countRolesByUserId(String id);

	public int saveRelationRoleAndPermission(String roleid, String permissions);

	public int deleteRelationRoleAndPermission(String roleid, String permissions);

	public Map<String, String> addRole(Role role, String permissions);

	public Map<String, String> updateRole(Role role, String adds, String dels);

	public Map<String, String> checkRole(Role role);

	public int updateBatchRole(String roleids, Role role);

	public List<Role> findByParams(String name, Boolean all,
			Integer targetPage, Integer pageSize);

	public int countByParams(String name, Boolean all);
}
