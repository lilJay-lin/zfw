package com.mimi.zfw.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mimi.zfw.dao.IBaseDao;
import com.mimi.zfw.dao.IUserDao;
import com.mimi.zfw.model.UserModel;
import com.mimi.zfw.model.UserQueryModel;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.pageUtil.CommonPageObject;
import com.mimi.zfw.util.pageUtil.PageUtil;

@Service("IUserService")
public class UserServiceImpl extends BaseService<UserModel, String> implements
		IUserService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	private IUserDao userDao;

	@Autowired
	@Qualifier("IUserDao")
	@Override
	public void setBaseDao(IBaseDao<UserModel, String> userDao) {
		this.baseDao = userDao;
		this.userDao = (IUserDao) userDao;
	}

	public CommonPageObject<UserModel> query(int pn, int pageSize,
			UserQueryModel command) {
		LOGGER.info("UserServiceImpl");
		return PageUtil.getPage(userDao.countQuery(command), pn,
				userDao.query(pn, pageSize, command), pageSize);
	}

	public CommonPageObject<UserModel> strictQuery(int pn, int pageSize,
			UserQueryModel command) {
		return PageUtil.getPage(userDao.countStrictQuery(command), pn,
				userDao.strictQuery(pn, pageSize, command), pageSize);
	}

}