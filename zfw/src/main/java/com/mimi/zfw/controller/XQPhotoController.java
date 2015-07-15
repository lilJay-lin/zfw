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
import com.mimi.zfw.mybatis.pojo.RCImage;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IRCImageService;
import com.mimi.zfw.util.JsonDateValueProcessor;

@Controller
public class XQPhotoController {
	private static final Logger LOG = LoggerFactory
			.getLogger(XQPhotoController.class);

	@Resource
	private IRCImageService rciService;

	@Resource
	IAliyunOSSService aossService;

	@RequestMapping(value = "/mi/{rcId}/xqphoto/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getRCImageByPage(HttpServletRequest request,
			@PathVariable int curPage,Integer pageSize, @PathVariable String rcId, String name) {
		Object res = null;
		int page = curPage - 1 > 0 ? curPage - 1 : 0;
		try {
			List<RCImage> items = rciService.findByParams(rcId, name,
					page, pageSize);
			if (items != null && !items.isEmpty()) {
				for (RCImage item : items) {
					item.setContentUrl(aossService.addImgParams(
							item.getContentUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			int rows = rciService.countByParams(rcId, name);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("小区图片查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false,
					"小区图片查询失败");
		}
		return res;
	}

	@RequestMapping(value = "/mi/{rcId}/xqphoto/{imageId}/detail", method = { RequestMethod.GET })
	public String toRCImageDetail(HttpServletRequest request,
			@PathVariable String rcId,@PathVariable String imageId) {
		return "/mi/xqphoto/detail";
	}

	@RequestMapping(value = "/mi/{rcId}/xqphoto/add", method = { RequestMethod.GET })
	public String toAddRCImage(HttpServletRequest request, @PathVariable String rcId) {
		return "mi/xqphoto/add";
	}

	@RequestMapping(value = "/mi/xqphoto/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addRCImage(HttpServletRequest request, RCImage image) {
		JSONObject jo = new JSONObject();
		try {
			image.setContentUrl(aossService.clearImgParams(image.getContentUrl()));
			Map<String, String> res = rciService.add(image);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "小区图片保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("小区保存失败",e);
			jo.put("success", false);
			jo.put("msg", "小区图片保存失败!");
		}
		return jo.toString();
	}


	@RequestMapping(value = "/mi/xqphoto/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getRCImage(@PathVariable String id, HttpServletRequest request) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jo = new JSONObject();
		try {
			RCImage image = rciService.get(id);
			if (image != null) {
				image.setContentUrl(aossService.addImgParams(
						image.getContentUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				jo.put("image", JSONObject.fromObject(image, jsonConfig));
			}
		} catch (Exception e) {
			LOG.error("获取小区图片失败", e);
			jo.put("image", null);
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/{rcId}/xqphoto/{imageId}/edit", method = { RequestMethod.GET })
	public String toUpdateRCImage(HttpServletRequest request,
			@PathVariable String rcId,@PathVariable String imageId) {
		return "/mi/xqphoto/edit";
	}

	@RequestMapping(value = "/mi/xqphoto/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateRCImage(HttpServletRequest request, RCImage image) {
		JSONObject jo = new JSONObject();
		try {
			image.setContentUrl(aossService.clearImgParams(image.getContentUrl()));
			Map<String, String> res = rciService.modify(image);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新小区图片成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新小区图片失败", e);
			jo.put("success", false);
			jo.put("msg", "更新小区图片失败!");
		}
		return jo.toString();
	}
	
	@RequestMapping(value = "/mi/xqphoto/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelRCImage(HttpServletRequest request, String imageIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = rciService.batchDel(imageIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "小区图片删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "小区图片删除失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/xqphoto/uploadImg", method = { RequestMethod.POST })
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
			int pageSize, List<RCImage> items, boolean rescode, String msg) {
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
