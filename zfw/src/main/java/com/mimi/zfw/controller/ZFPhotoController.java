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
import com.mimi.zfw.mybatis.pojo.RHImage;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IRHImageService;
import com.mimi.zfw.util.JsonDateValueProcessor;

@Controller
public class ZFPhotoController {
	private static final Logger LOG = LoggerFactory
			.getLogger(ZFPhotoController.class);

	@Resource
	private IRHImageService rhiService;

	@Resource
	IAliyunOSSService aossService;

    @RequiresPermissions("rc:view")
	@RequestMapping(value = "/mi/{rhId}/zfphoto/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getRHImageByPage(HttpServletRequest request,
			@PathVariable int curPage,Integer pageSize, @PathVariable String rhId, String name) {
		Object res = null;
		int page = curPage - 1 > 0 ? curPage - 1 : 0;
		try {
			List<RHImage> items = rhiService.findByParams(rhId, name,
					page, pageSize);
			if (items != null && !items.isEmpty()) {
				for (RHImage item : items) {
					item.setContentUrl(aossService.addImgParams(
							item.getContentUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			int rows = rhiService.countByParams(rhId, name);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("租房图片查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false,
					"租房图片查询失败");
		}
		return res;
	}

    @RequiresPermissions("rc:view")
	@RequestMapping(value = "/mi/{rhId}/zfphoto/{imageId}/detail", method = { RequestMethod.GET })
	public String toRHImageDetail(HttpServletRequest request,
			@PathVariable String rhId,@PathVariable String imageId) {
		return "/mi/zfphoto/detail";
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/{rhId}/zfphoto/add", method = { RequestMethod.GET })
	public String toAddRHImage(HttpServletRequest request, @PathVariable String rhId) {
		return "mi/zfphoto/add";
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/zfphoto/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addRHImage(HttpServletRequest request, RHImage image) {
		JSONObject jo = new JSONObject();
		try {
			image.setContentUrl(aossService.clearImgParams(image.getContentUrl()));
			Map<String, String> res = rhiService.add(image);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "租房图片保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("租房保存失败",e);
			jo.put("success", false);
			jo.put("msg", "租房图片保存失败!");
		}
		return jo.toString();
	}


    @RequiresPermissions("rc:view")
	@RequestMapping(value = "/mi/zfphoto/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getRHImage(@PathVariable String id, HttpServletRequest request) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jo = new JSONObject();
		try {
			RHImage image = rhiService.get(id);
			if (image != null) {
				image.setContentUrl(aossService.addImgParams(
						image.getContentUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				jo.put("image", JSONObject.fromObject(image, jsonConfig));
			}
		} catch (Exception e) {
			LOG.error("获取租房图片失败", e);
			jo.put("image", null);
		}
		return jo.toString();
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/{rhId}/zfphoto/{imageId}/edit", method = { RequestMethod.GET })
	public String toUpdateRHImage(HttpServletRequest request,
			@PathVariable String rhId,@PathVariable String imageId) {
		return "/mi/zfphoto/edit";
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/zfphoto/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateRHImage(HttpServletRequest request, RHImage image) {
		JSONObject jo = new JSONObject();
		try {
			image.setContentUrl(aossService.clearImgParams(image.getContentUrl()));
			Map<String, String> res = rhiService.modify(image);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新租房图片成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新租房图片失败", e);
			jo.put("success", false);
			jo.put("msg", "更新租房图片失败!");
		}
		return jo.toString();
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/zfphoto/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelRHImage(HttpServletRequest request, String imageIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = rhiService.batchDel(imageIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "租房图片删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "租房图片删除失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/zfphoto/uploadImg", method = { RequestMethod.POST })
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
			int pageSize, List<RHImage> items, boolean rescode, String msg) {
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
