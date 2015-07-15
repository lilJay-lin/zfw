package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
	    String[][] nc = {
	    		{"管理用户","user"},{"查询用户","user:query"},{"浏览用户","user:view"},{"添加用户","user:add"},{"删除用户","user:del"},{"修改用户","user:update"},
	    		{"自管理", "user:self"},{"进入后台","user:mi"},
	    		{"管理角色","role"},{"查询角色","role:query"},{"浏览角色", "role:view"},{"添加角色","role:add"},{ "删除角色","role:del"},{"修改角色","role:update" },
	    		
	    		{"查询关联楼盘","rep:queryru"},{"查询楼盘","rep:query"},{"浏览楼盘","rep:view"},{"添加楼盘","rep:add"},{"删除楼盘","rep:del"},{"修改楼盘","rep:update"},
	    		{"查询小区","rc:query"},{"浏览小区","rc:view"},{"添加小区","rc:add"},{"删除小区","rc:del"},{"修改小区","rc:update"},
	    		
	    		{"查询资讯","info:query"},{"浏览资讯","info:view"},{"添加资讯","info:add"},{"删除资讯","info:del"},{"修改资讯","info:update"},
	    		{"查询广告","ad:query"},{"浏览广告","ad:view"},{"删除广告","ad:del"},{"修改广告","ad:update"},
	    		
	    		{"查询商铺","shop:query"},{"浏览商铺","shop:view"},{"添加商铺","shop:add"},{"删除商铺","shop:del"},{"修改商铺","shop:update"},
	    		{"查询写字楼","ob:query"},{"浏览写字楼","ob:view"},{"添加写字楼","ob:add"},{"删除写字楼","ob:del"},{"修改写字楼","ob:update"},
	    		{"查询厂房仓库","warehouse:query"},{"浏览厂房仓库","warehouse:view"},{"添加厂房仓库","warehouse:add"},{"删除厂房仓库","warehouse:del"},{"修改厂房仓库","warehouse:update"},
	    		
	    		{"查询报名名单","nl:query"},{"浏览报名名单","nl:view"},{"添加报名名单","nl:add"},{"删除报名名单","nl:del"},{"修改报名名单","nl:update"},
	    		{"浏览报名信息","su:view"},{"修改报名信息","su:update"},
	    		
	    		{"浏览评估信息","am:view"},{"计算评估方程","am:compute"},
	    		{"查询评估项","ai:query"},{"浏览评估项","ai:view"},{"添加评估项","ai:add"},{"删除评估项","ai:del"},{"修改评估项","ai:update"},
	    		};
	    for (int i = 0; i < nc.length; i++) {
		Permission p = new Permission();
		p.setId(UUID.randomUUID().toString());
		p.setName(nc[i][0]);
		p.setCode(nc[i][1]);
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
	rrpe.or().andRoleIdIn(ids).andDelFlagEqualTo(false);
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
	pe.setOrderByClause("code asc");
	return pm.selectByExample(pe);
    }

    @Override
    public List<Permission> getPermissionsByRoleId(String id) {
	if (StringUtils.isBlank(id)) {
	    return new ArrayList<Permission>();
	}
	RelationRoleAndPermissionExample rure = new RelationRoleAndPermissionExample();
	rure.or().andRoleIdEqualTo(id).andDelFlagEqualTo(false);
	List<RelationRoleAndPermission> relations = rrpm.selectByExample(rure);
	List<String> permissionIds = new ArrayList<String>();
	for (int i = 0; i < relations.size(); i++) {
	    String permissionId = relations.get(i).getPermissionId();
	    if (!permissionIds.contains(permissionId)) {
		permissionIds.add(permissionId);
	    }
	}
	PermissionExample pe = new PermissionExample();
	if (!permissionIds.isEmpty()) {
		pe.or().andIdIn(permissionIds).andDelFlagEqualTo(false);
		pe.setOrderByClause("code asc");
	    return pm.selectByExample(pe);
	} else {
	    return null;
	}
    }

    @Override
    public List<Permission> findPermissionByExample(PermissionExample example,
	    Integer curPage, Integer pageSize) {
	if(example == null){
	    example = new PermissionExample();
	    example.or().andDelFlagEqualTo(false);
	}
	
	example.setLimitStart(curPage* pageSize);
	example.setLimitSize(pageSize);
	example.setOrderByClause("code asc");
	
	List<Permission> permissions = pm.selectByExample(example);
	
	return permissions;
	
    }

    @Override
    public int countPermissionByExample(PermissionExample example) {
	
	if(example == null){
	    example = new PermissionExample();
	    example.or().andDelFlagEqualTo(false);
	}
	example.setOrderByClause("code asc");
	List<Permission> permissions = pm.selectByExample(example);
	
	return permissions==null?0: permissions.size();
    }

    @Override
    public List<Permission> findPermissionByRoleId(String id, Integer curPage,
	    Integer pageSize) {
	
	if (StringUtils.isBlank(id)) {
	    return new ArrayList<Permission>();
	}
	RelationRoleAndPermissionExample rure = new RelationRoleAndPermissionExample();
	rure.or().andRoleIdEqualTo(id).andDelFlagEqualTo(false);
	rure.setLimitStart(curPage* pageSize);
	rure.setLimitSize(pageSize);
	List<RelationRoleAndPermission> relations = rrpm.selectByExample(rure);
	List<String> permissionIds = new ArrayList<String>();
	for (int i = 0; i < relations.size(); i++) {
	    String permissionId = relations.get(i).getPermissionId();
	    if (!permissionIds.contains(permissionId)) {
		permissionIds.add(permissionId);
	    }
	}
	PermissionExample pe = new PermissionExample();
	if (!permissionIds.isEmpty()) {
		pe.or().andIdIn(permissionIds).andDelFlagEqualTo(false);
		pe.setOrderByClause("code asc");
	    return pm.selectByExample(pe);
	} else {
	    return null;
	}
    }

    @Override
    public int countPermissionById(String id) {
	
	return this.getPermissionsByRoleId(id).size();
    }


}
