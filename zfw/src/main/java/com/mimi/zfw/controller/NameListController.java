package com.mimi.zfw.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.NameList;
import com.mimi.zfw.mybatis.pojo.NameListExample;
import com.mimi.zfw.service.INameListService;
import com.mimi.zfw.service.IPermissionService;

@Controller
@RequestMapping("/mi")
public class NameListController {
    @Resource
    private INameListService nlService;
    @Resource
    private IPermissionService permissionService;
    private static final Logger LOG = LoggerFactory
	    .getLogger(UserController.class);

    @RequiresPermissions("nl:query")
    @RequestMapping(value = "/nl", method = { RequestMethod.GET })
    public String index(HttpServletRequest request) {

	return "/mi/nl/index";
    }

    @RequiresPermissions("nl:query")
    @RequestMapping(value = "/nl/page/{curPage}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getNameListsByPage(HttpServletRequest request, @PathVariable int curPage ,String name) {

	Object res = null;

	int page = curPage - 1 > 0 ? curPage - 1 : 0;

//	String name = request.getParameter("name") == null ? null
//		: (String) request.getParameter("name");

	// String phoneNum = request.getParameter("type") == null ? null
	// : (String) request.getParameter("type");

	NameListExample example = new NameListExample();
	NameListExample.Criteria cr = example.createCriteria();
	if (StringUtils.isNotBlank(name)) {
	    try {
		cr.andNameLike("%" + URLDecoder.decode(name, "utf-8") + "%");
	    } catch (UnsupportedEncodingException e) {

		JSONObject jo = new JSONObject();
		jo.put("success", false);
		jo.put("msg", "查询条件解码出错");
		LOG.error("查询名单信息分页，查询条件解码出错！", e);

		return jo.toString();
	    }
	}
	cr.andDelFlagEqualTo(false);
	Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
		: Integer.valueOf((String) request.getParameter("pagesize"));

	int rows = 0;
	try {
	    rows = nlService.countNameListByExample(example);
	    List<NameList> items = nlService.findNameListByExample(example,
		    page, pageSize);
	    int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
		    / pageSize + 1);
	    res = getJsonObject(rows, totalpage, curPage, pageSize, items,
		    true, "");
	} catch (Exception e) {

	    res = getJsonObject(rows, 0, curPage, pageSize, null, false, "");
	    LOG.error("查询名单信息分页信息报错！", e);
	}
	return res;
    }

    public Object getJsonObject(int rows, int totalpage, int curPage,
	    int pageSize, List<NameList> items, boolean rescode, String msg) {
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

    @RequiresPermissions("nl:view")
    @RequestMapping(value = "/nl/{id}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getNameList(@PathVariable String id,
	    HttpServletRequest request) {

	JSONObject jo = new JSONObject();

	try {
	    NameList nl = (NameList) nlService.get(id);
	    if (nl != null) {
		jo.put("nl", nl);
	    }
	} catch (Exception e) {

	    jo.put("user", null);
	    jo.put("relations", null);
	    LOG.error("查询名单信息出错！", e);
	}

	return jo.toString();
    }

    @RequiresPermissions("nl:add")
    @RequestMapping(value = "/nl/add", method = { RequestMethod.GET })
    public String toAddUser(Model model, HttpServletRequest request) {

	// return new ModelAndView("mi/users/add","user",new User());

	return "mi/nl/add";
    }

    @RequiresPermissions("nl:add")
    @RequestMapping(value = "/nl", method = { RequestMethod.POST })
    @ResponseBody
    public Object addNameList(HttpServletRequest request, NameList nl) {

	JSONObject jo = new JSONObject();

	if (nl == null) {
	    jo.put("success", false);
	    jo.put("msg", "新增名单信息不能为空!");
	} else {

	    try {
		nl.setId(UUID.randomUUID().toString());

		Map<String, String> res = nlService.addNameList(nl);

		if (StringUtils.isEmpty(res.get("msg"))) {
		    jo.put("success", true);
		    jo.put("msg", "新增名单信息保存成功!");

		} else {
		    jo.put("success", false);
		    jo.put("msg", res.get("msg"));
		    jo.put("field", res.get("field"));
		}

	    } catch (Exception e) {
		jo.put("success", false);
		jo.put("msg", "新增名单信息保存失败!");
		LOG.error("新增名单信息保存失败！", e);
	    }

	}

	return jo.toString();
    }

    @RequiresPermissions("nl:update")
    @RequestMapping(value = "/nl/{id}/edit", method = { RequestMethod.GET })
    public String toUpdateUser(HttpServletRequest request, Model model,
	    @PathVariable String id) {
	model.addAttribute("id", id);
	return "/mi/nl/edit";
    }

    @RequiresPermissions("nl:view")
    @RequestMapping(value = "/nl/{id}/detail", method = { RequestMethod.GET })
    public String toViewNameList(Model model, @PathVariable String id) {

	model.addAttribute("id", id);

	return "/mi/nl/detail";
    }

    @RequiresPermissions("nl:update")
    @RequestMapping(value = "/nl/{id}", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateNameList(HttpServletRequest request, NameList nl,
	    @PathVariable String id) {

	JSONObject jo = new JSONObject();
	try {

	    Map<String, String> res = nlService.updateNameList(nl);

	    if (StringUtils.isEmpty(res.get("msg"))) {
		jo.put("success", true);
		jo.put("msg", "更新名单信息成功!");

	    } else {
		jo.put("success", false);
		jo.put("msg", res.get("msg"));
		jo.put("field", res.get("field"));
	    }

	} catch (Exception e) {
	    jo.put("success", false);
	    jo.put("msg", "更新名单信息失败!");
	    LOG.error("更新名单信息失败！", e);
	}

	return jo.toString();
    }

    @RequiresPermissions("nl:del")
    @RequestMapping(value = "/nls", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateBatchUser(HttpServletRequest request, NameList nl,
	    String nlids) {
	JSONObject jo = new JSONObject();

	try {
	    nlService.updateBatchNameList(nlids, nl);

	    jo.put("success", true);
	    jo.put("msg", "名单信息删除成功");
	} catch (Exception e) {

	    jo.put("success", false);
	    jo.put("msg", "名单信息删除失败");
	    LOG.error("删除名单信息失败！", e);
	}

	return jo.toString();
    }

}
