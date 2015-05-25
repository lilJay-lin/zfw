package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.PermissionMapper;
import com.mimi.zfw.mybatis.dao.RelationRoleAndPermissionMapper;
import com.mimi.zfw.mybatis.dao.RelationUserAndRoleMapper;
import com.mimi.zfw.mybatis.dao.RoleMapper;
import com.mimi.zfw.mybatis.pojo.Permission;
import com.mimi.zfw.mybatis.pojo.RelationRoleAndPermission;
import com.mimi.zfw.mybatis.pojo.RelationUserAndRole;
import com.mimi.zfw.mybatis.pojo.RelationUserAndRoleExample;
import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.mybatis.pojo.RoleExample;
import com.mimi.zfw.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {
	@Resource
	private RoleMapper rm;

	@Resource
	private PermissionMapper pm;

	@Resource
	private RelationRoleAndPermissionMapper rrpm;
	
	@Resource
	private RelationUserAndRoleMapper rurm;
	
	public int k = 1;
	public int getK(){
	    return k++;
	}
	
	@Override
//	@Transactional
	public void batchAddRoles(List<Role> roles) {
	    // TODO Auto-generated method stub
	    System.out.println(roles.size());
	    for(int i=0;i<roles.size();i++){
		rm.insert(roles.get(i));
		int k = 1/(i-10);
		System.out.print(k);
	    }
//	    for (Role role : roles) {
//		rm.insert(role);
//	    }
	}
	@Override
	public Role getRoleById(String id) {
	    // TODO Auto-generated method stub
//		return this.rm.selectByPrimaryKey(id);
	    return null;
	}
	@Override
	public List<Role> listAll() {
	    // TODO Auto-generated method stub
		return rm.selectByExample(null);
//	    return rm.selectAll();
//	    return null;
	}

	@Override
	public void initRole() {
		int count = rm.countByExample(null);
		if(count<1){
			Date nowDate = new Date(System.currentTimeMillis());
			Role role = new Role();
			role.setId(UUID.randomUUID().toString());
			role.setName("超级管理员");
			role.setDescription("拥有最高权限");
			role.setCreateDate(nowDate);
			rm.insert(role);
			List<Permission> permissions = pm.selectByExample(null);
			for(int i=0;i<permissions.size();i++){
				RelationRoleAndPermission rrm = new RelationRoleAndPermission();
				rrm.setId(UUID.randomUUID().toString());
				rrm.setRoleId(role.getId());
				rrm.setPermissionId(permissions.get(i).getId());
				rrm.setCreateDate(nowDate);
				rrpm.insert(rrm);
			}
		}
	}

	@Override
	public List<Role> getRolesByUserId(String id) {
		RelationUserAndRoleExample rure = new RelationUserAndRoleExample();
		rure.or().andUserIdEqualTo(id);
		List<RelationUserAndRole> relations = rurm.selectByExample(rure);
		List<String> roleIds = new ArrayList<String>();
		for(int i=0;i<relations.size();i++){
			String roleId = relations.get(i).getRoleId();
			if(!roleIds.contains(roleId)){
				roleIds.add(roleId);
			}
		}
		RoleExample re = new RoleExample();
		re.or().andIdIn(roleIds);
		return rm.selectByExample(re);
	}

}
