package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.PermissionMapper;
import com.mimi.zfw.mybatis.dao.RelationRoleAndPermissionMapper;
import com.mimi.zfw.mybatis.dao.RelationUserAndRoleMapper;
import com.mimi.zfw.mybatis.dao.RoleMapper;
import com.mimi.zfw.mybatis.pojo.Permission;
import com.mimi.zfw.mybatis.pojo.RelationRoleAndPermission;
import com.mimi.zfw.mybatis.pojo.RelationRoleAndPermissionExample;
import com.mimi.zfw.mybatis.pojo.RelationUserAndRole;
import com.mimi.zfw.mybatis.pojo.RelationUserAndRoleExample;
import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.mybatis.pojo.RoleExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IRoleService;
import com.mimi.zfw.service.IUserService;

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
    private IUserService userService ;

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
	if (StringUtils.isBlank(id)) {
	    return new ArrayList<Role>();
	}
	RelationUserAndRoleExample rure = new RelationUserAndRoleExample();
	rure.or().andUserIdEqualTo(id).andDelFlagEqualTo(false);
	List<RelationUserAndRole> relations = rurm.selectByExample(rure);
	List<String> roleids = new ArrayList<String>();
	for (int i = 0; i < relations.size(); i++) {
	    String roleid = relations.get(i).getRoleId();
	    if (!roleids.contains(roleid)) {
		roleids.add(roleid);
	    }
	}
	RoleExample re = new RoleExample();
	if (!roleids.isEmpty()) {
	    re.or().andIdIn(roleids).andDelFlagEqualTo(false);
	    return rm.selectByExample(re);
	} else {
	    return null;
	}
    }

    @Override
    public List<Role> findRoleByExample(RoleExample example, Integer curPage,
	    Integer pageSize) {
	
	if(example == null){
	    example = new RoleExample();
	    RoleExample.Criteria cr = example.createCriteria();
	    cr.andDelFlagEqualTo(false);
	    List<String> names = new ArrayList<String>();
	    names.add(Constants.ROLE_NAME_ADMIN_DEFAULT);
	    names.add(Constants.ROLE_NAME_NORMAL_DEFAULT);
	    cr.andNameNotIn(names);
	}
	example.setLimitStart(curPage* pageSize);
	example.setLimitSize(pageSize);
	
	List<Role> roles = rm.selectByExample(example);
	
	return roles;
    }

    @Override
    public int countRoleByExample(RoleExample example) {
	
	if(example == null){
	    example = new RoleExample();
	    RoleExample.Criteria cr = example.createCriteria();
	    cr.andDelFlagEqualTo(false);
	    List<String> names = new ArrayList<String>();
	    names.add(Constants.ROLE_NAME_ADMIN_DEFAULT);
	    names.add(Constants.ROLE_NAME_NORMAL_DEFAULT);
	    cr.andNameNotIn(names);
	}
	
	List<Role> roles = rm.selectByExample(example);
	
	return roles==null?0: roles.size();
    }

    @Override
    public List<Role> findRolesByUserId(String id, Integer curPage,
	    Integer pageSize) {
	
	if (StringUtils.isBlank(id)) {
	    return null;
	}
	RelationUserAndRoleExample rure = new RelationUserAndRoleExample();
	rure.or().andUserIdEqualTo(id).andDelFlagEqualTo(false);
	rure.setLimitStart(curPage* pageSize);
	rure.setLimitSize(pageSize);
	List<RelationUserAndRole> relations = rurm.selectByExample(rure);
	List<String> roleids = new ArrayList<String>();
	for (int i = 0; i < relations.size(); i++) {
	    String roleid = relations.get(i).getRoleId();
	    if (!roleids.contains(roleid)) {
		roleids.add(roleid);
	    }
	}
	RoleExample re = new RoleExample();
	if (!roleids.isEmpty()) {
	    re.or().andIdIn(roleids).andDelFlagEqualTo(false);
	    return rm.selectByExample(re);
	} else {
	    return null;
	}
    }

    @Override
    public int countRolesByUserId(String id) {
	
	return this.getRolesByUserId(id).size();
    }

    @Override
    public int saveRelationRoleAndPermission(String roldid, String permissions) {
	
	int res = 0;

	Role role = this.get(roldid);
	if (role == null || StringUtils.isEmpty(permissions)) {
	    res = 0;
	} else {
	    
	    String[] ids = permissions.split(Constants.MI_IDS_SPLIT_STRING);

	    for (String id : ids) {
		RelationRoleAndPermission record = new RelationRoleAndPermission();
		record.setRoleId(roldid);
		record.setPermissionId(id);
		record.setDelFlag(false);
		record.setCreater(userService.getCurUserId());
		record.setLastEditor(userService.getCurUserId());
		record.setId(UUID.randomUUID().toString());
		rrpm.insert(record);
		res++;
	    }

	}
	return res;
    }

    @Override
    public int deleteRelationRoleAndPermission(String roleid, String permissions) {
	
	int res = 0;

	Role role = this.get(roleid);
	if (role == null || StringUtils.isEmpty(permissions)) {
	    res = 0;
	} else {

	    String[] ids = permissions.split(Constants.MI_IDS_SPLIT_STRING);
	    List<String> pList = new ArrayList<String>();
	    for (String id : ids) {
		pList.add(id);
	    }

	    RelationRoleAndPermissionExample rolePermissinExample = new RelationRoleAndPermissionExample();
	    rolePermissinExample.or().andRoleIdEqualTo(roleid).andPermissionIdIn(pList).andDelFlagEqualTo(false);;

	    RelationRoleAndPermission record = new RelationRoleAndPermission();
	    record.setDelFlag(true);
	    record.setLastEditor(userService.getCurUserId());

	    res = rrpm.updateByExampleSelective(record,
		    rolePermissinExample);

	}
	return res;
    }

    @Override
    public Map<String, String> addRole(Role role, String permissions) {
	
	Map<String, String> resMap = new HashMap<String, String>();
	resMap.put("msg", "");

	if (role == null) {
	    resMap.put("msg", "角色信息不能为空");
	}

	resMap = this.checkRole(role);
	
	if(StringUtils.isNotBlank(resMap.get("msg"))){
	    return resMap;
	}
	role.setDelFlag(false);
	role.setCreater(userService.getCurUserId());
	role.setLastEditor(userService.getCurUserId());
	
	this.save(role);

	if (StringUtils.isNotBlank(permissions)) {
	    String roleid = role.getId();
	    this.saveRelationRoleAndPermission(roleid, permissions);

	}

	return resMap;
    }

    @Override
    public Map<String, String> updateRole(Role role, String adds, String dels) {
	
	Map<String, String> resMap = new HashMap<String, String>();
	resMap.put("msg", "");

	String roleid = role.getId();

	if (StringUtils.isBlank(roleid)
		|| rm.selectByPrimaryKey(roleid) == null) {
	    resMap.put("msg", "角色不存在!");
	}

	resMap = this.checkRole(role);

	if(StringUtils.isNotBlank(resMap.get("msg"))){
	    return resMap;
	}
	role.setLastEditor(userService.getCurUserId());
	
	rm.updateByPrimaryKeySelective(role);
	
	if (!StringUtils.isEmpty(adds)) {

	    this.saveRelationRoleAndPermission(roleid, adds);
	}
	if (!StringUtils.isEmpty(dels)) {
	    this.deleteRelationRoleAndPermission(roleid, dels);
	}

	return resMap;

    }

    @Override
    public Map<String, String> checkRole(Role role) {
	Map<String,String> resMap = new HashMap<String, String>();
	resMap.put("msg", "");
	String name = role.getName();
	if(StringUtils.isBlank(name)){
	    resMap.put("field","name");
	    resMap.put("msg", "角色名称不能为空");
	}else if(!name.matches("^[0-9a-zA-Z_\u4E00-\u9FA5]+$")){
	    resMap.put("field","name");
	    resMap.put("msg", "角色名称只能由中文、阿拉伯数据、英文字母和下划线组成");
	}
	
	
	return resMap;
    }

    @Override
    public int updateBatchRole(String roleids, Role role) {
	

	if (role == null) {
	    return 0;
	}

	String[] ids = roleids.split(Constants.MI_IDS_SPLIT_STRING);
	List<String> rList = new ArrayList<String>();
	for (String id : ids) {
	    rList.add(id);
	}

	RoleExample ue = new RoleExample();
	ue.or().andIdIn(rList).andDelFlagEqualTo(false);
	
	role.setLastEditor(userService.getCurUserId());
	
	int row = rm.updateByExampleSelective(role, ue);
	
	Boolean delFlag = role.getDelFlag();
	
	if(delFlag!=null && delFlag == true){
	    RelationRoleAndPermissionExample rolePermissionExample = new RelationRoleAndPermissionExample();
	    rolePermissionExample.or().andRoleIdIn(rList).andDelFlagEqualTo(false);
	    RelationRoleAndPermission record = new RelationRoleAndPermission();
	    record.setDelFlag(true);
	    record.setLastEditor(userService.getCurUserId());
	    rrpm.updateByExampleSelective(record, rolePermissionExample);
	}
	
	return row;
    }

}
