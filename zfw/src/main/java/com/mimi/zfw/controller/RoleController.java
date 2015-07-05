package com.mimi.zfw.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Base64.Decoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.mybatis.pojo.RoleExample;
import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.service.IRoleService;

@Controller
//@RequestMapping("/role")
public class RoleController {
    @Resource
    private IRoleService roleService;

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
	if(StringUtils.isNotEmpty(name)){
	    try {
		example.or().andNameEqualTo(URLDecoder.decode(name,"utf-8"));
	    } catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		JSONObject jo = new JSONObject();
		jo.put("success", false);
		jo.put("msg", "查询条件解码错误");
		
		return jo.toString();
	    }
	}
	
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

}
