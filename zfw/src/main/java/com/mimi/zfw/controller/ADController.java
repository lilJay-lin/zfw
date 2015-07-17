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
import com.mimi.zfw.mybatis.pojo.Advertisement;
import com.mimi.zfw.service.IAdvertisementService;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.util.JsonDateValueProcessor;

@Controller
public class ADController {
	private static final Logger LOG = LoggerFactory
			.getLogger(ADController.class);

	@Resource
	private IAdvertisementService adService;

	@Resource
	IAliyunOSSService aossService;

    @RequiresPermissions("ad:query")
	@RequestMapping(value = "/mi/ad", method = { RequestMethod.GET })
	public String toMIIndex(HttpServletRequest request) {
		return "/mi/ad/index";
	}

    @RequiresPermissions("ad:query")
	@RequestMapping(value = "/mi/ad/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAdvertisementByPage(HttpServletRequest request,
			@PathVariable int curPage, Integer pageSize, String name,
			String location) {
		Object res = null;
		int page = curPage - 1 > 0 ? curPage - 1 : 0;
		try {
			pageSize = pageSize == null ? Constants.DEFAULT_PAGE_SIZE
					: pageSize;
			List<Advertisement> items = adService.findByParams(name, location,
					page, pageSize);
			if (items != null && !items.isEmpty()) {
				for (Advertisement item : items) {
					item.setPreImageUrl(aossService.addImgParams(
							item.getPreImageUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			int rows = adService.countByParams(name, location);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("广告查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false, "广告查询失败");
		}
		return res;
	}

    @RequiresPermissions("ad:view")
	@RequestMapping(value = "/mi/ad/{adId}/detail", method = { RequestMethod.GET })
	public String toAdvertisementDetail(HttpServletRequest request,
			@PathVariable String adId) {
		return "/mi/ad/detail";
	}

    @RequiresPermissions("ad:view")
	@RequestMapping(value = "/mi/ad/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getAdvertisement(@PathVariable String id,
			HttpServletRequest request) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		JSONObject jo = new JSONObject();
		try {
			Advertisement ad = adService.get(id);
			if (ad != null) {
				ad.setPreImageUrl(aossService.addImgParams(ad.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				jo.put("ad", JSONObject.fromObject(ad, jsonConfig));
			}
		} catch (Exception e) {
			LOG.error("获取广告失败", e);
			jo.put("ad", null);
		}
		return jo.toString();
	}

    @RequiresPermissions("ad:update")
	@RequestMapping(value = "/mi/ad/{adId}/edit", method = { RequestMethod.GET })
	public String toUpdateAdvertisement(HttpServletRequest request,
			@PathVariable String adId) {
		return "/mi/ad/edit";
	}

    @RequiresPermissions("ad:update")
	@RequestMapping(value = "/mi/ad/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateAdvertisement(HttpServletRequest request,
			Advertisement ad) {
		JSONObject jo = new JSONObject();
		try {
			ad.setPreImageUrl(aossService.clearImgParams(ad.getPreImageUrl()));
			Map<String, String> res = adService.modify(ad);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新广告成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新广告失败", e);
			jo.put("success", false);
			jo.put("msg", "更新广告失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/ad/uploadImg", method = { RequestMethod.POST })
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
			LOG.error("上传广告缩放图出错！", e);
			jo.put("success", false);
			jo.put("msg", "保存广告缩放图失败");
		}
		return jo.toString();
	}

	public Object getJsonObject(int rows, int totalpage, int curPage,
			int pageSize, List<Advertisement> items, boolean rescode, String msg) {
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
