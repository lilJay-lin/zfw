package com.mimi.zfw.dao;

import java.util.List;

import com.mimi.zfw.model.UserModel;
import com.mimi.zfw.model.UserQueryModel;

public interface IUserDao extends IBaseDao<UserModel, String> {

	List<UserModel> query(int pn, int pageSize, UserQueryModel command);

	int countQuery(UserQueryModel command);

	List<UserModel> strictQuery(int pn, int pageSize, UserQueryModel command);

	int countStrictQuery(UserQueryModel command);

}
