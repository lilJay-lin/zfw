package com.mimi.zfw.controller;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.AssessmentItem;
import com.mimi.zfw.service.IAssessmentItemService;

@Controller
public class PGItemController {
	private static final Logger LOG = LoggerFactory
			.getLogger(PGItemController.class);
	@Resource
	private IAssessmentItemService aiService;


	@RequestMapping(value = "/mi/pgitem", method = { RequestMethod.GET })
	public String toMIIndex(Model model, HttpServletRequest request) {
		return "mi/pgitem/index";
	}
	
	@RequestMapping(value = "/mi/pgitem/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAIByPage(HttpServletRequest request,
			@PathVariable int curPage, String name, Integer pageSize) {
		Object res = null;
		int page = curPage - 1 > 0 ? curPage - 1 : 0;
		pageSize = pageSize == null ? Constants.DEFAULT_PAGE_SIZE : pageSize;
		try {
			List<AssessmentItem> items = aiService.findByParams(name, page,
					pageSize);
			int rows = aiService.countByParams(name);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("评估项查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false, "查询失败");
		}
		return res;
	}

	@RequestMapping(value = "/mi/pgitem/{aiId}/detail", method = { RequestMethod.GET })
	public String toAIDetail(HttpServletRequest request,
			@PathVariable String aiId) {
		return "/mi/pgitem/detail";
	}

	@RequestMapping(value = "/mi/pgitem/add", method = { RequestMethod.GET })
	public String toAddAI(Model model, HttpServletRequest request) {
		return "mi/pgitem/add";
	}

	@RequestMapping(value = "/mi/pgitem/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addAI(HttpServletRequest request, AssessmentItem ai) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = aiService.addAI(ai);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "评估项保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("评估项保存失败", e);
			jo.put("success", false);
			jo.put("msg", "评估项保存失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/pgitem/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAI(@PathVariable String id, HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		try {
			AssessmentItem ai = aiService.get(id);
			if (ai != null) {
				jo.put("ai", ai);
			}
		} catch (Exception e) {
			LOG.error("获取失败", e);
			jo.put("ai", null);
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/pgitem/{aiId}/edit", method = { RequestMethod.GET })
	public String toUpdateAI(HttpServletRequest request,
			@PathVariable String aiId) {
		return "/mi/pgitem/edit";
	}

	@RequestMapping(value = "/mi/pgitem/{aiId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateAI(HttpServletRequest request, AssessmentItem ai,
			@PathVariable String aiId) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = aiService.updateAI(ai);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "评估项更新成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("评估项更新失败", e);
			jo.put("success", false);
			jo.put("msg", "评估项更新失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/pgitem/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelAI(HttpServletRequest request, String aiIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = aiService.batchDel(aiIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "评估项删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "评估项删除失败!");
		}
		return jo.toString();
	}

	public Object getJsonObject(int rows, int totalpage, int curPage,
			int pageSize, List<AssessmentItem> items, boolean rescode,
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
