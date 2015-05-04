package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mimi.zfw.dao.RoleMapper;
import com.mimi.zfw.pojo.Role;
import com.mimi.zfw.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {
	@Resource
	private RoleMapper rm;
	
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
	    return rm.selectAll();
//	    return null;
	}

}
