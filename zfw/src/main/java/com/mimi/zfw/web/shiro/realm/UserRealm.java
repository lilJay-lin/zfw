package com.mimi.zfw.web.shiro.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.mimi.zfw.mybatis.pojo.Permission;
import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.service.IPermissionService;
import com.mimi.zfw.service.IRoleService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.web.shiro.authc.UniqueidUsernamePasswordToken;

/**
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 14-1-28
 * <p>
 * Version: 1.0
 */
public class UserRealm extends AuthorizingRealm {

//    	@Autowired
	@Resource
	private IUserService userService;
    	
	@Resource
	private IRoleService roleService;
	
	@Resource
	private IPermissionService permissionService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
//		String username = (String) principals.getPrimaryPrincipal();
		Set<String> set = principals.asSet();
		Iterator<String> it = set.iterator();
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
		List<Role> roles = null;
		for(int i=0;it.hasNext();i++){
			String value = it.next();
			if(i==1){
				roles = roleService.getRolesByUserId(value);
			}
		}
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		Set<String> roleNames = new HashSet<String>();
		Set<String> permissionCodes = new HashSet<String>();
		if(roles != null && !roles.isEmpty()){
			List<String> roleIds = new ArrayList<String>();
			for(int i=0;i<roles.size();i++){
				roleNames.add(roles.get(i).getName());
				roleIds.add(roles.get(i).getId());
			}
			List<Permission> permissions = permissionService.getPermissionsByRoleIds(roleIds);
			if(permissions!=null && !permissions.isEmpty()){
				for(int i=0;i<permissions.size();i++){
					permissionCodes.add(permissions.get(i).getCode());
				}
			}
		}
		authorizationInfo.setRoles(roleNames);
		authorizationInfo.setStringPermissions(permissionCodes);
		
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		String username = (String) token.getPrincipal();
		
		User user = userService.findByLoginName(username);
		
		if (user == null) {
			throw new UnknownAccountException();// 没找到帐号
		}
		if(user.getLocked()){
		    throw new LockedAccountException();
		}
		List<String> l = new ArrayList<String>();
		l.add(username);
		l.add(user.getId());
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				l, // 标识
				user.getPassword(), // 密码
				ByteSource.Util.bytes(user.getId()+user.getSalt()),//salt=username+salt
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
