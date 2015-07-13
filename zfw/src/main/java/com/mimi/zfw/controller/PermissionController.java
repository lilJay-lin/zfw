package com.mimi.zfw.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.Permission;
import com.mimi.zfw.mybatis.pojo.PermissionExample;
import com.mimi.zfw.service.IPermissionService;

@Controller
@RequestMapping(value="/mi")
public class PermissionController {

    @Resource
    private IPermissionService permissionService ;
    private static final Logger LOG = LoggerFactory
	    .getLogger(UserController.class);
    
    @RequestMapping(value = "permissions/page/{curPage}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getPermissionByPage(HttpServletRequest request, @PathVariable int curPage) {

	Object res = null;

	int page = curPage - 1 > 0 ? curPage - 1 : 0;

	String roleid = request.getParameter("roleid") == null ? null
		: (String) request.getParameter("roleid");

	String name = request.getParameter("name") == null ? null
		: (String) request.getParameter("name");
	
	String code = request.getParameter("code") == null ? null
		: (String) request.getParameter("code");
	
	PermissionExample example = new PermissionExample() ;
	PermissionExample.Criteria crt = example.createCriteria();

	try {
		if(!StringUtils.isBlank(name)){
		    crt.andNameLike("%"+URLDecoder.decode(name,"utf-8")+"%");
		}

		if(!StringUtils.isBlank(code)){
		    crt.andCodeEqualTo(URLDecoder.decode(code,"utf-8"));
		}
		
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		JSONObject jo = new JSONObject();
		jo.put("success", false);
		jo.put("msg", "查询条件解码出错");
		LOG.error("查询角色分页，查询条件解码出错！",e);
		
		return jo.toString();
	}
	crt.andDelFlagEqualTo(false);
	Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
		: Integer.valueOf((String) request.getParameter("pagesize"));
	
	int rows = 0;
	try {
	    //有userid则查询关联的role，无则查询所有role
	    rows =roleid==null ? permissionService.countPermissionByExample(example) : permissionService.countPermissionById(roleid);
	    List<Permission> items = roleid==null ? permissionService.findPermissionByExample(example, page, pageSize):permissionService.findPermissionByRoleId(roleid, page, pageSize);
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
	    int pageSize, List<Permission> items, boolean rescode, String msg) {
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
