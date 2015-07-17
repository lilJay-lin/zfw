package com.mimi.zfw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.mimi.zfw.mybatis.pojo.AssessmentParameter;
import com.mimi.zfw.service.IAssessmentParameterService;

@Controller
public class PGParamController {
	private static final Logger LOG = LoggerFactory
			.getLogger(PGParamController.class);
	@Resource
	private IAssessmentParameterService apService;

    @RequiresPermissions("ai:view")
	@RequestMapping(value = "/mi/{aiId}/pgparam/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAPByPage(HttpServletRequest request,
			@PathVariable int curPage, @PathVariable String aiId) {
		Object res = null;
		int page = curPage - 1 > 0 ? curPage - 1 : 0;
		String name = request.getParameter("name") == null ? null
				: (String) request.getParameter("name");
		Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
				: Integer.valueOf((String) request.getParameter("pagesize"));
		try {
			List<AssessmentParameter> items = apService.findByParams(aiId,
					name, page, pageSize);
			int rows = apService.countByParams(aiId, name);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("评估参数查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false,
					"评估参数查询失败");
		}
		return res;
	}

    @RequiresPermissions("ai:view")
	@RequestMapping(value = "/mi/{aiId}/pgparam/{apId}/detail", method = { RequestMethod.GET })
	public String toAPDetail(HttpServletRequest request,
			@PathVariable String aiId, @PathVariable String apId) {
		return "/mi/pgparam/detail";
	}

    @RequiresPermissions("ai:update")
	@RequestMapping(value = "/mi/{aiId}/pgparam/add", method = { RequestMethod.GET })
	public String toAddAP(Model model, HttpServletRequest request,
			@PathVariable String aiId) {
		return "mi/pgparam/add";
	}

    @RequiresPermissions("ai:update")
	@RequestMapping(value = "/mi/pgparam/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addAP(HttpServletRequest request, AssessmentParameter ap) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = apService.addAP(ap);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "评估参数保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("评估参数保存失败", e);
			jo.put("success", false);
			jo.put("msg", "评估参数保存失败!");
		}
		return jo.toString();
	}

    @RequiresPermissions("ai:view")
	@RequestMapping(value = "/mi/pgparam/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAP(@PathVariable String id, HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		try {
			AssessmentParameter ap = apService.get(id);
			if (ap != null) {
				jo.put("ap", ap);
			}
		} catch (Exception e) {
			LOG.error("获取评估参数失败", e);
			jo.put("ap", null);
		}
		return jo.toString();
	}

    @RequiresPermissions("ai:update")
	@RequestMapping(value = "/mi/{aiId}/pgparam/{apId}/edit", method = { RequestMethod.GET })
	public String toUpdateAP(HttpServletRequest request,
			@PathVariable String aiId, @PathVariable String apId) {
		return "/mi/pgparam/edit";
	}

    @RequiresPermissions("ai:update")
	@RequestMapping(value = "/mi/pgparam/{apId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateAP(HttpServletRequest request, AssessmentParameter ap,
			@PathVariable String apId) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = apService.updateAP(ap);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新评估参数成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新评估参数失败", e);
			jo.put("success", false);
			jo.put("msg", "更新评估参数失败!");
		}
		return jo.toString();
	}

    @RequiresPermissions("ai:update")
	@RequestMapping(value = "/mi/pgparam/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelAP(HttpServletRequest request, String aiId,
			String apIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = apService.batchDel(aiId, apIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "评估参数删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "评估参数删除失败!");
		}
		return jo.toString();
	}

	public Object getJsonObject(int rows, int totalpage, int curPage,
			int pageSize, List<AssessmentParameter> items, boolean rescode,
			String msg) {
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
