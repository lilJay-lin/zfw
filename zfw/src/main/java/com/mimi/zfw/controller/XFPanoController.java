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
import com.mimi.zfw.mybatis.pojo.REPPano;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IREPPanoService;
import com.mimi.zfw.util.JsonDateValueProcessor;

@Controller
public class XFPanoController {
	private static final Logger LOG = LoggerFactory
			.getLogger(XFPanoController.class);

	@Resource
	private IREPPanoService reppService;

	@Resource
	IAliyunOSSService aossService;

	@RequestMapping(value = "/mi/{repId}/xfpano/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getREPPanoByPage(HttpServletRequest request,
			@PathVariable int curPage,Integer pageSize, @PathVariable String repId, String name) {
		Object res = null;
		int page = curPage - 1 > 0 ? curPage - 1 : 0;
		try {
			List<REPPano> items = reppService.findByParams(repId, name,
					page, pageSize);
			if (items != null && !items.isEmpty()) {
				for (REPPano item : items) {
					item.setPreImageUrl(aossService.addImgParams(
							item.getPreImageUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			int rows = reppService.countByParams(repId, name);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("楼盘全景查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false,
					"楼盘全景查询失败");
		}
		return res;
	}

	@RequestMapping(value = "/mi/{repId}/xfpano/{panoId}/detail", method = { RequestMethod.GET })
	public String toREPPanoDetail(HttpServletRequest request,
			@PathVariable String repId,@PathVariable String panoId) {
		return "/mi/xfpano/detail";
	}

	@RequestMapping(value = "/mi/{repId}/xfpano/add", method = { RequestMethod.GET })
	public String toAddREPPano(HttpServletRequest request, @PathVariable String repId) {
		return "mi/xfpano/add";
	}

	@RequestMapping(value = "/mi/xfpano/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addREPPano(HttpServletRequest request, REPPano pano) {
		JSONObject jo = new JSONObject();
		try {
			pano.setPreImageUrl(aossService.clearImgParams(pano.getPreImageUrl()));
			Map<String, String> res = reppService.add(pano);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "楼盘全景保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("楼盘保存失败",e);
			jo.put("success", false);
			jo.put("msg", "楼盘全景保存失败!");
		}
		return jo.toString();
	}


	@RequestMapping(value = "/mi/xfpano/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getREPPano(@PathVariable String id, HttpServletRequest request) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jo = new JSONObject();
		try {
			REPPano pano = reppService.get(id);
			if (pano != null) {
				pano.setPreImageUrl(aossService.addImgParams(
						pano.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				jo.put("pano", JSONObject.fromObject(pano, jsonConfig));
			}
		} catch (Exception e) {
			LOG.error("获取楼盘全景失败", e);
			jo.put("pano", null);
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/{repId}/xfpano/{panoId}/edit", method = { RequestMethod.GET })
	public String toUpdateREPPano(HttpServletRequest request,
			@PathVariable String repId,@PathVariable String panoId) {
		return "/mi/xfpano/edit";
	}

	@RequestMapping(value = "/mi/xfpano/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateREPPano(HttpServletRequest request, REPPano pano) {
		JSONObject jo = new JSONObject();
		try {
			pano.setPreImageUrl(aossService.clearImgParams(pano.getPreImageUrl()));
			Map<String, String> res = reppService.modify(pano);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新楼盘全景成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新楼盘全景失败", e);
			jo.put("success", false);
			jo.put("msg", "更新楼盘全景失败!");
		}
		return jo.toString();
	}
	
	@RequestMapping(value = "/mi/xfpano/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelREPPano(HttpServletRequest request, String panoIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = reppService.batchDel(panoIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "楼盘全景删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "楼盘全景删除失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/xfpano/uploadImg", method = { RequestMethod.POST })
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
			int pageSize, List<REPPano> items, boolean rescode, String msg) {
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
