package com.mimi.zfw.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.HTRing;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IHTRingService;
import com.mimi.zfw.util.JsonDateValueProcessor;

@Controller
public class HXRingController {
	private static final Logger LOG = LoggerFactory
			.getLogger(HXRingController.class);

	@Resource
	private IHTRingService htrService;

	@Resource
	IAliyunOSSService aossService;

    @RequiresPermissions("rep:view")
	@RequestMapping(value = "/mi/{htId}/hxring/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getHTRingByPage(HttpServletRequest request,
			@PathVariable int curPage,Integer pageSize, @PathVariable String htId, String name) {
		Object res = null;
		int page = curPage - 1 > 0 ? curPage - 1 : 0;
		try {
			List<HTRing> items = htrService.findByParams(htId, name,
					page, pageSize);
			if (items != null && !items.isEmpty()) {
				for (HTRing item : items) {
					item.setPreImageUrl(aossService.addImgParams(
							item.getPreImageUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			int rows = htrService.countByParams(htId, name);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("户型三维查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false,
					"户型三维查询失败");
		}
		return res;
	}

    @RequiresPermissions("rep:view")
	@RequestMapping(value = "/mi/{htId}/hxring/{ringId}/detail", method = { RequestMethod.GET })
	public String toHTRingDetail(HttpServletRequest request,
			@PathVariable String htId,@PathVariable String ringId) {
		return "/mi/hxring/detail";
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/{htId}/hxring/add", method = { RequestMethod.GET })
	public String toAddHTRing(HttpServletRequest request, @PathVariable String htId) {
		return "mi/hxring/add";
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/hxring/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addHTRing(HttpServletRequest request, HTRing ring) {
		JSONObject jo = new JSONObject();
		try {
			ring.setPreImageUrl(aossService.clearImgParams(ring.getPreImageUrl()));
			Map<String, String> res = htrService.add(ring);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "户型三维保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("户型保存失败",e);
			jo.put("success", false);
			jo.put("msg", "户型三维保存失败!");
		}
		return jo.toString();
	}


    @RequiresPermissions("rep:view")
	@RequestMapping(value = "/mi/hxring/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getHTRing(@PathVariable String id, HttpServletRequest request) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jo = new JSONObject();
		try {
			HTRing ring = htrService.get(id);
			if (ring != null) {
				ring.setPreImageUrl(aossService.addImgParams(
						ring.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				jo.put("ring", JSONObject.fromObject(ring, jsonConfig));
			}
		} catch (Exception e) {
			LOG.error("获取户型三维失败", e);
			jo.put("ring", null);
		}
		return jo.toString();
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/{htId}/hxring/{ringId}/edit", method = { RequestMethod.GET })
	public String toUpdateHTRing(HttpServletRequest request,
			@PathVariable String htId,@PathVariable String ringId) {
		return "/mi/hxring/edit";
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/hxring/{ringId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateHTRing(HttpServletRequest request, HTRing ring) {
		JSONObject jo = new JSONObject();
		try {
			ring.setPreImageUrl(aossService.clearImgParams(ring.getPreImageUrl()));
			Map<String, String> res = htrService.modify(ring);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新户型三维成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新户型三维失败", e);
			jo.put("success", false);
			jo.put("msg", "更新户型三维失败!");
		}
		return jo.toString();
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/hxring/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelHTRing(HttpServletRequest request, String ringIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = htrService.batchDel(ringIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "户型三维删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "户型三维删除失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/hxring/uploadImg", method = { RequestMethod.POST })
	public @ResponseBody
	Object upload(HttpServletRequest request,
			@RequestParam("theFile") MultipartFile theFile) {
		JSONObject jo = new JSONObject();
		try {
			String path = aossService.saveFileToServer(theFile);
			path = aossService.addImgParams(path,
					Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG);
			jo.put("imgPath", path);
			jo.put("success", true);
		} catch (IOException e) {
			LOG.error("上传三维缩放图出错！", e);
			jo.put("success", false);
			jo.put("msg", "保存三维缩放图失败");
		}
		return jo.toString();
	}

	public Object getJsonObject(int rows, int totalpage, int curPage,
			int pageSize, List<HTRing> items, boolean rescode, String msg) {
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
