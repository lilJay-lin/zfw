package com.mimi.zfw.service;

import com.mimi.zfw.model.UserModel;
import com.mimi.zfw.model.UserQueryModel;
import com.mimi.zfw.util.pageUtil.CommonPageObject;

public interface IRoleService  extends IBaseService<UserModel, String> {

	CommonPageObject<UserModel> query(int pn, int pageSize,
			UserQueryModel command);

	CommonPageObject<UserModel> strictQuery(int pn, int pageSize,
			UserQueryModel command);
}
