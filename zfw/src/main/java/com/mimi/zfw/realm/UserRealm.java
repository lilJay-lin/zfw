package com.mimi.zfw.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.mimi.zfw.pojo.User;
import com.mimi.zfw.service.IUserService;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public class UserRealm extends AuthorizingRealm {

    	@Autowired
	private IUserService userService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> roles = new HashSet<String>();
		roles.add("normal");
		roles.add("admin");
		
		Set<String> permissions = new HashSet<String>();
		permissions.add("aa");
		permissions.add("bb");
		if(username.equals("mimitest")){
			permissions.add("user");
		}else{
			permissions.add("user:view");
		}
		authorizationInfo.setRoles(roles);
		authorizationInfo.setStringPermissions(permissions);
//		authorizationInfo.setRoles(userService.findRoles(username));
//		authorizationInfo.setStringPermissions(userService
//				.findPermissions(username));
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal();

//		UserModel user = userService.findByUsername(username);
		
		User user = userService.findByUsername(username);
//		UserQueryModel command = new UserQueryModel();
//		command.setUserName(username);
//		CommonPageObject<UserModel> pages = userService.query(0, Constants.DEFAULT_PAGE_SIZE, command);
//		if(pages.getItemTotalNum()==0){
//			throw new UnknownAccountException();// 没找到帐号
//		}
//		UserModel user = pages.getItems().get(0);
		
//		throw new UnknownAccountException();// 没找到帐号
		
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}

		// if(Boolean.TRUE.equals(user.getLocked())) {
		// throw new LockedAccountException(); //帐号锁定
		// }

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getName(), // 用户名
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
				getName() // realm name
		);
		return authenticationInfo;
	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}
