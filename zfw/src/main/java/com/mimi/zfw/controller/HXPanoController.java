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
import com.mimi.zfw.mybatis.pojo.HTPano;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IHTPanoService;
import com.mimi.zfw.util.JsonDateValueProcessor;

@Controller
public class HXPanoController {
	private static final Logger LOG = LoggerFactory
			.getLogger(HXPanoController.class);

	@Resource
	private IHTPanoService htpService;

	@Resource
	IAliyunOSSService aossService;

    @RequiresPermissions("rep:view")
	@RequestMapping(value = "/mi/{htId}/hxpano/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getHTPanoByPage(HttpServletRequest request,
			@PathVariable int curPage,Integer pageSize, @PathVariable String htId, String name) {
		Object res = null;
		int page = curPage - 1 > 0 ? curPage - 1 : 0;
		try {
			List<HTPano> items = htpService.findByParams(htId, name,
					page, pageSize);
			if (items != null && !items.isEmpty()) {
				for (HTPano item : items) {
					item.setPreImageUrl(aossService.addImgParams(
							item.getPreImageUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			int rows = htpService.countByParams(htId, name);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("户型全景查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false,
					"户型全景查询失败");
		}
		return res;
	}

    @RequiresPermissions("rep:view")
	@RequestMapping(value = "/mi/{htId}/hxpano/{panoId}/detail", method = { RequestMethod.GET })
	public String toHTPanoDetail(HttpServletRequest request,
			@PathVariable String htId,@PathVariable String panoId) {
		return "/mi/hxpano/detail";
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/{htId}/hxpano/add", method = { RequestMethod.GET })
	public String toAddHTPano(HttpServletRequest request, @PathVariable String htId) {
		return "mi/hxpano/add";
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/hxpano/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addHTPano(HttpServletRequest request, HTPano pano) {
		JSONObject jo = new JSONObject();
		try {
			pano.setPreImageUrl(aossService.clearImgParams(pano.getPreImageUrl()));
			Map<String, String> res = htpService.add(pano);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "户型全景保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("户型保存失败",e);
			jo.put("success", false);
			jo.put("msg", "户型全景保存失败!");
		}
		return jo.toString();
	}


    @RequiresPermissions("rep:view")
	@RequestMapping(value = "/mi/hxpano/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getHTPano(@PathVariable String id, HttpServletRequest request) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jo = new JSONObject();
		try {
			HTPano pano = htpService.get(id);
			if (pano != null) {
				pano.setPreImageUrl(aossService.addImgParams(
						pano.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				jo.put("pano", JSONObject.fromObject(pano, jsonConfig));
			}
		} catch (Exception e) {
			LOG.error("获取户型全景失败", e);
			jo.put("pano", null);
		}
		return jo.toString();
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/{htId}/hxpano/{panoId}/edit", method = { RequestMethod.GET })
	public String toUpdateHTPano(HttpServletRequest request,
			@PathVariable String htId,@PathVariable String panoId) {
		return "/mi/hxpano/edit";
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/hxpano/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateHTPano(HttpServletRequest request, HTPano pano) {
		JSONObject jo = new JSONObject();
		try {
			pano.setPreImageUrl(aossService.clearImgParams(pano.getPreImageUrl()));
			Map<String, String> res = htpService.modify(pano);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新户型全景成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新户型全景失败", e);
			jo.put("success", false);
			jo.put("msg", "更新户型全景失败!");
		}
		return jo.toString();
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/hxpano/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelHTPano(HttpServletRequest request, String panoIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = htpService.batchDel(panoIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "户型删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "户型删除失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/hxpano/uploadImg", method = { RequestMethod.POST })
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
			LOG.error("上传全景缩放图出错！", e);
			jo.put("success", false);
			jo.put("msg", "保存全景缩放图失败");
		}
		return jo.toString();
	}

	public Object getJsonObject(int rows, int totalpage, int curPage,
			int pageSize, List<HTPano> items, boolean rescode, String msg) {
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
