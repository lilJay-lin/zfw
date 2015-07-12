package com.mimi.zfw.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.Permission;
import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.mybatis.pojo.RoleExample;
import com.mimi.zfw.mybatis.pojo.ShopImageExample;
import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.service.IPermissionService;
import com.mimi.zfw.service.IRoleService;
import com.mimi.zfw.util.RSAUtil;

@Controller
//@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;
    @Resource
    private IPermissionService permissionService;
    private static final Logger LOG = LoggerFactory
	    .getLogger(UserController.class);

    @RequestMapping("/role")//@RequestMapping("")
    public String toIndex(HttpServletRequest request, Model model) {
	long b = new Date().getTime();
	model.addAttribute("roles", roleService.listAll());
	long e = new Date().getTime();
	model.addAttribute("time", (e - b) * 0.001);
	return "list";
    }

    @RequestMapping("/role/batchSave")//@RequestMapping("batchSave")
    public String batchSave(HttpServletRequest request, Model model) {
	long b = new Date().getTime();
	List<Role> roles = new ArrayList<Role>();
	for (int i = 0; i < 20; i++) {
	    String id = UUID.randomUUID().toString();
	    String name = "name_" + i + id;
	    String description = "description_" + i;
	    Role role = new Role();
	    role.setDescription(description);
	    role.setId(id);
	    role.setName(name);
	    roles.add(role);
	}
	roleService.saveBatch(roles);
	model.addAttribute("roles", roleService.listAll());
	long e = new Date().getTime();
	model.addAttribute("time", (e - b) * 0.001);
	return "list";
    }

    @RequestMapping("/role/test")//@RequestMapping("test")
    public String toTest(HttpServletRequest request, Model model) {
	return "index";
    }
    
    @RequestMapping(value = "/mi/roles" , method ={RequestMethod.GET})
    public String index(HttpServletRequest request){
	
	return "/mi/role/index";
    }
    
    @RequestMapping(value = "/mi/roles/page/{curPage}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getRolesByPage(HttpServletRequest request, @PathVariable int curPage) {

	Object res = null;

	int page = curPage - 1 > 0 ? curPage - 1 : 0;

	String userid = request.getParameter("userid") == null ? null
		: (String) request.getParameter("userid");

	String name = request.getParameter("name") == null ? null
		: (String) request.getParameter("name");
	
	RoleExample example = new RoleExample() ;
	RoleExample.Criteria cr = example.createCriteria();
	if(!StringUtils.isBlank(name)){
	    try {
		cr.andNameLike("%"+URLDecoder.decode(name,"utf-8")+"%");
	    } catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		JSONObject jo = new JSONObject();
		jo.put("success", false);
		jo.put("msg", "查询条件解码出错");
		LOG.error("查询角色分页，查询条件解码出错！",e);
		
		return jo.toString();
	    }
	}
	cr.andDelFlagEqualTo(false);
	Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
		: Integer.valueOf((String) request.getParameter("pagesize"));
	
	int rows = 0;
	try {
	    //有userid则查询关联的role，无则查询所有role
	    rows =userid==null ? roleService.countRoleByExample(example) : roleService.countRolesByUserId(userid);
	    List<Role> items = userid==null ? roleService.findRoleByExample(example, page, pageSize):roleService.findRolesByUserId(userid, page, pageSize);
	    int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows / pageSize + 1);
	    res = getJsonObject(rows, totalpage, curPage, pageSize, items, true, "");
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    res = getJsonObject(rows, 0, curPage, pageSize, null, false, "");
	    LOG.error("查询角色分页信息报错！",e);
	}
	return res;
    }

    public Object getJsonObject(int rows, int totalpage, int curPage,
	    int pageSize, List<Role> items, boolean rescode, String msg) {
	JSONObject jo = new JSONObject();

	Map<String, Integer> map = new HashMap<String, Integer>();
	map.put("totalrows", rows);
	map.put("curpage", curPage);
	map.put("totalpage", totalpage);
	map.put("pagesize", pageSize);

	jo.put("pageinfo", map);
	jo.put("items", items);

	jo.put("success", rescode);
	jo.put("msg", msg);

	return jo.toString();
    }

    
    @RequestMapping(value = "/mi/role/{id}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getRole(@PathVariable String id, HttpServletRequest request) {

	JSONObject jo = new JSONObject();

	try {
	    Role role = (Role) roleService.get(id);
	    if (role != null) {
		jo.put("role", role);
		List<Permission> relations = permissionService.getPermissionsByRoleId(id);
		jo.put("relations", relations);
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    jo.put("user", null);
	    jo.put("relations", null);
	    LOG.error("查询角色信息出错！",e);
	}

	return jo.toString();
    }

    @RequestMapping(value = "/mi/role/add", method = { RequestMethod.GET })
    public String toAddUser(Model model, HttpServletRequest request) {

	// return new ModelAndView("mi/users/add","user",new User());

	return "mi/role/add";
    }

    @RequestMapping(value = "/mi/role", method = { RequestMethod.POST })
    @ResponseBody
    public Object addRole(HttpServletRequest request, Role role, String permissions) {

	JSONObject jo = new JSONObject();

	if (role == null) {
	    jo.put("success", false);
	    jo.put("msg", "新增角色信息不能为空!");
	} else {
	    
	    try {
		role.setId(UUID.randomUUID().toString());

		Map<String, String> res = roleService.addRole(role, permissions);

		if (StringUtils.isEmpty(res.get("msg"))) {
		    jo.put("success", true);
		    jo.put("msg", "新增角色保存成功!");

		} else {
		    jo.put("success", false);
		    jo.put("msg", res.get("msg"));
		    jo.put("field", res.get("field"));
		}

	    } catch (Exception e) {
		jo.put("success", false);
		jo.put("msg", "新增角色保存失败!");
		LOG.error("新增角色保存失败！",e);
	    }

	}

	return jo.toString();
    }

    @RequestMapping(value = "/mi/role/{id}/edit", method = { RequestMethod.GET })
    public String toUpdateUser(HttpServletRequest request,Model model, @PathVariable String id) {
	model.addAttribute("id", id);
	return "/mi/role/edit";
    }
    
    @RequestMapping(value = "/mi/role/{id}/detail", method = { RequestMethod.GET })
    public String toViewRole(Model model, @PathVariable String id) {

	model.addAttribute("id", id);

	return "/mi/role/detail";
    }

    @RequestMapping(value = "/mi/role/{id}", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateRole(HttpServletRequest request, Role role,
	    @PathVariable String id, String adds, String dels) {

	JSONObject jo = new JSONObject();
	try {

	    Map<String, String> res = roleService.updateRole(role, adds, dels);

	    if (StringUtils.isEmpty(res.get("msg"))) {
		jo.put("success", true);
		jo.put("msg", "更新角色成功!");

	    } else {
		jo.put("success", false);
		jo.put("msg", res.get("msg"));
		jo.put("field", res.get("field"));
	    }

	} catch (Exception e) {
	    jo.put("success", false);
	    jo.put("msg", "更新角色失败!");
	    LOG.error("更新角色信息失败！",e);
	}

	return jo.toString();
    }

    @RequestMapping(value = "/mi/roles", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateBatchUser(HttpServletRequest request, Role role,
	    String roleids) {
	JSONObject jo = new JSONObject();

	try {
	    roleService.updateBatchRole(roleids, role);

	    jo.put("success", true);
	    jo.put("msg", "角色更新成功");
	} catch (Exception e) {
	    // TODO Auto-generated catch block

	    jo.put("success", false);
	    jo.put("msg", "角色更新失败");
	    LOG.error("更新角色信息失败！",e);
	}

	return jo.toString();
    }

}
