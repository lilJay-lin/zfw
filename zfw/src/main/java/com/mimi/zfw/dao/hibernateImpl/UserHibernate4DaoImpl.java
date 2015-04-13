package com.mimi.zfw.dao.hibernateImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mimi.zfw.dao.IUserDao;
import com.mimi.zfw.model.UserModel;
import com.mimi.zfw.model.UserQueryModel;

@Repository("IUserDao")
public class UserHibernate4DaoImpl extends BaseHibernateDao<UserModel, String>
		implements IUserDao {

	private static final String HQL_LIST = "from UserModel ";
	private static final String HQL_COUNT = "select count(*) from UserModel ";

	private static final String HQL_LIST_ORDER = "order by id desc";
	private static final String HQL_LIST_QUERY_CONDITION = " where username like ?";
	private static final String HQL_LIST_STRICT_QUERY_CONDITION = " where username = ? and password = ?";
	private static final String HQL_LIST_QUERY_ALL = HQL_LIST
			+ HQL_LIST_QUERY_CONDITION + HQL_LIST_ORDER;
	private static final String HQL_COUNT_QUERY_ALL = HQL_COUNT
			+ HQL_LIST_QUERY_CONDITION;

	public List<UserModel> query(int pn, int pageSize, UserQueryModel command) {
		return list(HQL_LIST_QUERY_ALL, pn, pageSize, getQueryParam(command));
	}

	public int countQuery(UserQueryModel command) {
		return this.<Number> aggregate(HQL_COUNT_QUERY_ALL,
				getQueryParam(command)).intValue();
	}

	private Object[] getQueryParam(UserQueryModel command) {
		// TODO 改成全文索引
		String usernameLikeStr = "%" + command.getUserName() + "%";
		return new Object[] { usernameLikeStr };
	}

	public List<UserModel> strictQuery(int pn, int pageSize,
			UserQueryModel command) {
		return list(
				HQL_LIST + HQL_LIST_STRICT_QUERY_CONDITION + HQL_LIST_ORDER,
				pn, pageSize, getStrictQueryParam(command));
	}

	public int countStrictQuery(UserQueryModel command) {
		return this.<Number> aggregate(
				HQL_COUNT + HQL_LIST_STRICT_QUERY_CONDITION,
				getStrictQueryParam(command)).intValue();
	}

	private Object[] getStrictQueryParam(UserQueryModel command) {
		return new Object[] { command.getUserName(), command.getPassword() };
	}

}
