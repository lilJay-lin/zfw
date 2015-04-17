package com.mimi.zfw.service;

import java.util.Set;

import com.mimi.zfw.model.UserModel;
import com.mimi.zfw.model.UserQueryModel;
import com.mimi.zfw.util.pageUtil.CommonPageObject;

public interface IUserService extends IBaseService<UserModel, String> {

	CommonPageObject<UserModel> query(int pn, int pageSize,
			UserQueryModel command);

	CommonPageObject<UserModel> strictQuery(int pn, int pageSize,
			UserQueryModel command);
	public void changePassword(Long userId, String newPassword); //修改密码
	public UserModel findByUsername(String username); //根据用户名查找用户
	public Set<String> findRoles(String username);// 根据用户名查找其角色
	public Set<String> findPermissions(String username);// 根据用户名查找其权限
}
