package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
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
import com.mimi.zfw.mybatis.pojo.UserExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IRoleService;

@Service
public class RoleServiceImpl extends BaseService<Role, RoleExample, String>
	implements IRoleService {
    @Resource
    private RoleMapper rm;

    @Resource
    private PermissionMapper pm;

    @Resource
    private RelationRoleAndPermissionMapper rrpm;

    @Resource
    private RelationUserAndRoleMapper rurm;

    @Resource
    @Override
    public void setBaseDao(IBaseDao<Role, RoleExample, String> baseDao) {
	this.baseDao = baseDao;
	this.rm = (RoleMapper) baseDao;
    }

    public int k = 1;

    public int getK() {
	return k++;
    }

    @Override
    public void initRole() {
	int count = rm.countByExample(null);
	if (count < 1) {
	    Date nowDate = new Date(System.currentTimeMillis());
	    Role role = new Role();
	    role.setId(UUID.randomUUID().toString());
	    role.setName(Constants.ROLE_NAME_ADMIN_DEFAULT);
	    role.setDescription("拥有最高权限");
	    role.setCreateDate(nowDate);
	    rm.insertSelective(role);
	    // rm.insert(role);
	    List<Permission> permissions = pm.selectByExample(null);
	    for (int i = 0; i < permissions.size(); i++) {
		RelationRoleAndPermission rrm = new RelationRoleAndPermission();
		rrm.setId(UUID.randomUUID().toString());
		rrm.setRoleId(role.getId());
		rrm.setPermissionId(permissions.get(i).getId());
		rrm.setCreateDate(nowDate);
		rrpm.insertSelective(rrm);
	    }
	    Role normal = new Role();
	    normal.setId(UUID.randomUUID().toString());
	    normal.setName(Constants.ROLE_NAME_NORMAL_DEFAULT);
	    normal.setDescription("注册用户默认权限");
	    normal.setCreateDate(nowDate);
	    rm.insertSelective(normal);
	    // rm.insert(normal);
	    List<String> npns = new ArrayList<String>();
	    npns.add("自管理");
	    for (int i = 0; i < permissions.size(); i++) {
		RelationRoleAndPermission rrm = new RelationRoleAndPermission();
		if (npns.contains(permissions.get(i).getName())) {
		    rrm.setId(UUID.randomUUID().toString());
		    rrm.setRoleId(normal.getId());
		    rrm.setPermissionId(permissions.get(i).getId());
		    rrm.setCreateDate(nowDate);
		    rrpm.insertSelective(rrm);
		}
	    }
	}
    }

    @Override
    public List<Role> getRolesByUserId(String id) {
	if (id == null) {
	    return new ArrayList<Role>();
	}
	RelationUserAndRoleExample rure = new RelationUserAndRoleExample();
	rure.or().andUserIdEqualTo(id).andDelFlagEqualTo(false);
	List<RelationUserAndRole> relations = rurm.selectByExample(rure);
	List<String> roleIds = new ArrayList<String>();
	for (int i = 0; i < relations.size(); i++) {
	    String roleId = relations.get(i).getRoleId();
	    if (!roleIds.contains(roleId)) {
		roleIds.add(roleId);
	    }
	}
	RoleExample re = new RoleExample();
	if (!roleIds.isEmpty()) {
	    re.or().andIdIn(roleIds).andDelFlagEqualTo(false);
	    return rm.selectByExample(re);
	} else {
	    return null;
	}
    }

    @Override
    public List<Role> findRoleByExample(RoleExample example, Integer curPage,
	    Integer pageSize) {
	// TODO Auto-generated method stub
	if(example == null){
	    example = new RoleExample();
	    example.or().andDelFlagEqualTo(false);
	}

	example.setLimitStart(curPage* pageSize);
	example.setLimitSize(pageSize);
	
	List<Role> roles = rm.selectByExample(example);
	
	return roles;
    }

    @Override
    public int countRoleByExample(RoleExample example) {
	// TODO Auto-generated method stub
	if(example == null){
	    example = new RoleExample();
	    example.or().andDelFlagEqualTo(false);
	}
	List<Role> roles = rm.selectByExample(example);
	
	return roles.size();
    }

    @Override
    public List<Role> findRolesByUserId(String id, Integer curPage,
	    Integer pageSize) {
	// TODO Auto-generated method stub
	if (id == null) {
	    return null;
	}
	RelationUserAndRoleExample rure = new RelationUserAndRoleExample();
	rure.or().andUserIdEqualTo(id).andDelFlagEqualTo(false);
	rure.setLimitStart(curPage* pageSize);
	rure.setLimitSize(pageSize);
	List<RelationUserAndRole> relations = rurm.selectByExample(rure);
	List<String> roleIds = new ArrayList<String>();
	for (int i = 0; i < relations.size(); i++) {
	    String roleId = relations.get(i).getRoleId();
	    if (!roleIds.contains(roleId)) {
		roleIds.add(roleId);
	    }
	}
	RoleExample re = new RoleExample();
	if (!roleIds.isEmpty()) {
	    re.or().andIdIn(roleIds).andDelFlagEqualTo(false);
	    return rm.selectByExample(re);
	} else {
	    return null;
	}
    }

    @Override
    public int countRolesByUserId(String id) {
	// TODO Auto-generated method stub
	return this.getRolesByUserId(id).size();
    }

}
