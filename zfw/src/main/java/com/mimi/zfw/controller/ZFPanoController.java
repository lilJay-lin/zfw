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
import com.mimi.zfw.mybatis.pojo.RHPano;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IRHPanoService;
import com.mimi.zfw.util.JsonDateValueProcessor;

@Controller
public class ZFPanoController {
	private static final Logger LOG = LoggerFactory
			.getLogger(ZFPanoController.class);

	@Resource
	private IRHPanoService rhpService;

	@Resource
	IAliyunOSSService aossService;

    @RequiresPermissions("rc:view")
	@RequestMapping(value = "/mi/{rhId}/zfpano/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getRHPanoByPage(HttpServletRequest request,
			@PathVariable int curPage,Integer pageSize, @PathVariable String rhId, String name) {
		Object res = null;
		int page = curPage - 1 > 0 ? curPage - 1 : 0;
		try {
			List<RHPano> items = rhpService.findByParams(rhId, name,
					page, pageSize);
			if (items != null && !items.isEmpty()) {
				for (RHPano item : items) {
					item.setPreImageUrl(aossService.addImgParams(
							item.getPreImageUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			int rows = rhpService.countByParams(rhId, name);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("租房全景查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false,
					"租房全景查询失败");
		}
		return res;
	}

    @RequiresPermissions("rc:view")
	@RequestMapping(value = "/mi/{rhId}/zfpano/{panoId}/detail", method = { RequestMethod.GET })
	public String toRHPanoDetail(HttpServletRequest request,
			@PathVariable String rhId,@PathVariable String panoId) {
		return "/mi/zfpano/detail";
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/{rhId}/zfpano/add", method = { RequestMethod.GET })
	public String toAddRHPano(HttpServletRequest request, @PathVariable String rhId) {
		return "mi/zfpano/add";
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/zfpano/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addRHPano(HttpServletRequest request, RHPano pano) {
		JSONObject jo = new JSONObject();
		try {
			pano.setPreImageUrl(aossService.clearImgParams(pano.getPreImageUrl()));
			Map<String, String> res = rhpService.add(pano);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "租房全景保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("租房保存失败",e);
			jo.put("success", false);
			jo.put("msg", "租房全景保存失败!");
		}
		return jo.toString();
	}


    @RequiresPermissions("rc:view")
	@RequestMapping(value = "/mi/zfpano/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getRHPano(@PathVariable String id, HttpServletRequest request) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jo = new JSONObject();
		try {
			RHPano pano = rhpService.get(id);
			if (pano != null) {
				pano.setPreImageUrl(aossService.addImgParams(
						pano.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				jo.put("pano", JSONObject.fromObject(pano, jsonConfig));
			}
		} catch (Exception e) {
			LOG.error("获取租房全景失败", e);
			jo.put("pano", null);
		}
		return jo.toString();
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/{rhId}/zfpano/{panoId}/edit", method = { RequestMethod.GET })
	public String toUpdateRHPano(HttpServletRequest request,
			@PathVariable String rhId,@PathVariable String panoId) {
		return "/mi/zfpano/edit";
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/zfpano/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateRHPano(HttpServletRequest request, RHPano pano) {
		JSONObject jo = new JSONObject();
		try {
			pano.setPreImageUrl(aossService.clearImgParams(pano.getPreImageUrl()));
			Map<String, String> res = rhpService.modify(pano);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新租房全景成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新租房全景失败", e);
			jo.put("success", false);
			jo.put("msg", "更新租房全景失败!");
		}
		return jo.toString();
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/zfpano/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelRHPano(HttpServletRequest request, String panoIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = rhpService.batchDel(panoIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "租房删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "租房删除失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/zfpano/uploadImg", method = { RequestMethod.POST })
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
			int pageSize, List<RHPano> items, boolean rescode, String msg) {
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
