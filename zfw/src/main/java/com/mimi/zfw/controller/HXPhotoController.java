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
import com.mimi.zfw.mybatis.pojo.HTImage;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IHTImageService;
import com.mimi.zfw.util.JsonDateValueProcessor;

@Controller
public class HXPhotoController {
	private static final Logger LOG = LoggerFactory
			.getLogger(HXPhotoController.class);

	@Resource
	private IHTImageService htiService;

	@Resource
	IAliyunOSSService aossService;

	@RequestMapping(value = "/mi/{htId}/hxphoto/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getHTImageByPage(HttpServletRequest request,
			@PathVariable int curPage,Integer pageSize, @PathVariable String htId, String name) {
		Object res = null;
		int page = curPage - 1 > 0 ? curPage - 1 : 0;
		try {
			List<HTImage> items = htiService.findByParams(htId, name,
					page, pageSize);
			if (items != null && !items.isEmpty()) {
				for (HTImage item : items) {
					item.setContentUrl(aossService.addImgParams(
							item.getContentUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			int rows = htiService.countByParams(htId, name);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("户型图片查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false,
					"户型图片查询失败");
		}
		return res;
	}

	@RequestMapping(value = "/mi/{htId}/hxphoto/{imageId}/detail", method = { RequestMethod.GET })
	public String toHTImageDetail(HttpServletRequest request,
			@PathVariable String htId,@PathVariable String imageId) {
		return "/mi/hxphoto/detail";
	}

	@RequestMapping(value = "/mi/{htId}/hxphoto/add", method = { RequestMethod.GET })
	public String toAddHTImage(HttpServletRequest request, @PathVariable String htId) {
		return "mi/hxphoto/add";
	}

	@RequestMapping(value = "/mi/hxphoto/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addHTImage(HttpServletRequest request, HTImage image) {
		JSONObject jo = new JSONObject();
		try {
			image.setContentUrl(aossService.clearImgParams(image.getContentUrl()));
			Map<String, String> res = htiService.add(image);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "户型图片保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("户型保存失败",e);
			jo.put("success", false);
			jo.put("msg", "户型图片保存失败!");
		}
		return jo.toString();
	}


	@RequestMapping(value = "/mi/hxphoto/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getHTImage(@PathVariable String id, HttpServletRequest request) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jo = new JSONObject();
		try {
			HTImage image = htiService.get(id);
			if (image != null) {
				image.setContentUrl(aossService.addImgParams(
						image.getContentUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				jo.put("image", JSONObject.fromObject(image, jsonConfig));
			}
		} catch (Exception e) {
			LOG.error("获取户型图片失败", e);
			jo.put("image", null);
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/{htId}/hxphoto/{imageId}/edit", method = { RequestMethod.GET })
	public String toUpdateHTImage(HttpServletRequest request,
			@PathVariable String htId,@PathVariable String imageId) {
		return "/mi/hxphoto/edit";
	}

	@RequestMapping(value = "/mi/hxphoto/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateHTImage(HttpServletRequest request, HTImage image) {
		JSONObject jo = new JSONObject();
		try {
			image.setContentUrl(aossService.clearImgParams(image.getContentUrl()));
			Map<String, String> res = htiService.modify(image);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新户型图片成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新户型图片失败", e);
			jo.put("success", false);
			jo.put("msg", "更新户型图片失败!");
		}
		return jo.toString();
	}
	
	@RequestMapping(value = "/mi/hxphoto/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelHTImage(HttpServletRequest request, String imageIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = htiService.batchDel(imageIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "户型图片删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "户型图片删除失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/hxphoto/uploadImg", method = { RequestMethod.POST })
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
			LOG.error("上传图片缩放图出错！", e);
			jo.put("success", false);
			jo.put("msg", "保存图片缩放图失败");
		}
		return jo.toString();
	}

	public Object getJsonObject(int rows, int totalpage, int curPage,
			int pageSize, List<HTImage> items, boolean rescode, String msg) {
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
