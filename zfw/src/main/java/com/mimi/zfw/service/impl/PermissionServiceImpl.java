package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.PermissionMapper;
import com.mimi.zfw.mybatis.dao.RelationRoleAndPermissionMapper;
import com.mimi.zfw.mybatis.pojo.Permission;
import com.mimi.zfw.mybatis.pojo.PermissionExample;
import com.mimi.zfw.mybatis.pojo.RelationRoleAndPermission;
import com.mimi.zfw.mybatis.pojo.RelationRoleAndPermissionExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IPermissionService;

@Service
public class PermissionServiceImpl extends
	BaseService<Permission, PermissionExample, String> implements
	IPermissionService {

    @Resource
    private PermissionMapper pm;

    @Resource
    private RelationRoleAndPermissionMapper rrpm;

    @Resource
    @Override
    public void setBaseDao(
	    IBaseDao<Permission, PermissionExample, String> baseDao) {
	this.baseDao = baseDao;
	this.pm = (PermissionMapper) baseDao;
    }

    @Override
    public void initPermission() {
	int count = pm.countByExample(null);
	if (count < 1) {
	    Date nowDate = new Date(System.currentTimeMillis());
	    String[] names = { "管理用户", "查询用户", "浏览用户", "添加用户", "删除用户", "修改用户",
		    "自管理", "进入后台", "管理角色", "查询角色", "浏览角色", "添加角色", "删除角色",
		    "修改角色" };
	    String[] codes = { "user", "user:query", "user:view", "user:add",
		    "user:del", "user:update", "user:self", "user:mi", "role",
		    "role:query", "role:view", "role:add", "role:del",
		    "role:update" };
	    String[] descs = { "", "", "", "", "", "", "", "", "", "", "", "",
		    "", "" };
	    for (int i = 0; i < names.length; i++) {
		Permission p = new Permission();
		p.setId(UUID.randomUUID().toString());
		p.setName(names[i]);
		p.setCode(codes[i]);
		p.setDescription(descs[i]);
		p.setCreateDate(nowDate);
		pm.insertSelective(p);
	    }
	}
    }

    @Override
    public List<Permission> getPermissionsByRoleIds(List<String> ids) {
	if (ids == null || ids.isEmpty()) {
	    return null;
	}
	RelationRoleAndPermissionExample rrpe = new RelationRoleAndPermissionExample();
	rrpe.or().andRoleIdIn(ids);
	List<RelationRoleAndPermission> relations = rrpm.selectByExample(rrpe);
	List<String> permissionIds = new ArrayList<String>();
	for (int i = 0; i < relations.size(); i++) {
	    String permissionId = relations.get(i).getPermissionId();
	    if (!permissionIds.contains(permissionId)) {
		permissionIds.add(permissionId);
	    }
	}
	if (permissionIds.isEmpty()) {
	    return null;
	}
	PermissionExample pe = new PermissionExample();
	pe.or().andIdIn(permissionIds);
	return pm.selectByExample(pe);
    }

}
