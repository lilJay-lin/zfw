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
import com.mimi.zfw.mybatis.pojo.REPImage;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IREPImageService;
import com.mimi.zfw.util.JsonDateValueProcessor;

@Controller
public class XFPhotoController {
	private static final Logger LOG = LoggerFactory
			.getLogger(XFPhotoController.class);

	@Resource
	private IREPImageService repiService;

	@Resource
	IAliyunOSSService aossService;

    @RequiresPermissions("rep:view")
	@RequestMapping(value = "/mi/{repId}/xfphoto/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getREPImageByPage(HttpServletRequest request,
			@PathVariable int curPage,Integer pageSize, @PathVariable String repId, String name,
			String type) {
		Object res = null;
		int page = curPage - 1 > 0 ? curPage - 1 : 0;
		try {
			List<REPImage> items = repiService.findByParams(repId, name, type,
					page, pageSize);
			if (items != null && !items.isEmpty()) {
				for (REPImage item : items) {
					item.setContentUrl(aossService.addImgParams(
							item.getContentUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			int rows = repiService.countByParams(repId, name, type);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("楼盘图片查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false,
					"楼盘图片查询失败");
		}
		return res;
	}

    @RequiresPermissions("rep:view")
	@RequestMapping(value = "/mi/{repId}/xfphoto/{imageId}/detail", method = { RequestMethod.GET })
	public String toREPImageDetail(HttpServletRequest request,
			@PathVariable String repId,@PathVariable String imageId) {
		return "/mi/xfphoto/detail";
	}


    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/{repId}/xfphoto/add", method = { RequestMethod.GET })
	public String toAddREPImage(HttpServletRequest request, @PathVariable String repId) {
		return "mi/xfphoto/add";
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/xfphoto/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addREPImage(HttpServletRequest request, REPImage image) {
		JSONObject jo = new JSONObject();
		try {
			image.setContentUrl(aossService.clearImgParams(image.getContentUrl()));
			Map<String, String> res = repiService.add(image);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "楼盘图片保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("楼盘保存失败",e);
			jo.put("success", false);
			jo.put("msg", "楼盘图片保存失败!");
		}
		return jo.toString();
	}


    @RequiresPermissions("rep:view")
	@RequestMapping(value = "/mi/xfphoto/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getREPImage(@PathVariable String id, HttpServletRequest request) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jo = new JSONObject();
		try {
			REPImage image = repiService.get(id);
			if (image != null) {
				image.setContentUrl(aossService.addImgParams(
						image.getContentUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				jo.put("image", JSONObject.fromObject(image, jsonConfig));
			}
		} catch (Exception e) {
			LOG.error("获取楼盘图片失败", e);
			jo.put("image", null);
		}
		return jo.toString();
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/{repId}/xfphoto/{imageId}/edit", method = { RequestMethod.GET })
	public String toUpdateREPImage(HttpServletRequest request,
			@PathVariable String repId,@PathVariable String imageId) {
		return "/mi/xfphoto/edit";
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/xfphoto/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateREPImage(HttpServletRequest request, REPImage image) {
		JSONObject jo = new JSONObject();
		try {
			image.setContentUrl(aossService.clearImgParams(image.getContentUrl()));
			Map<String, String> res = repiService.modify(image);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新楼盘图片成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新楼盘图片失败", e);
			jo.put("success", false);
			jo.put("msg", "更新楼盘图片失败!");
		}
		return jo.toString();
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/xfphoto/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelREPImage(HttpServletRequest request, String imageIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = repiService.batchDel(imageIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "楼盘图片删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "楼盘图片删除失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/xfphoto/uploadImg", method = { RequestMethod.POST })
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
			LOG.error("上传楼盘图片出错！", e);
			jo.put("success", false);
			jo.put("msg", "保存图片失败");
		}
		return jo.toString();
	}

	public Object getJsonObject(int rows, int totalpage, int curPage,
			int pageSize, List<REPImage> items, boolean rescode, String msg) {
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
