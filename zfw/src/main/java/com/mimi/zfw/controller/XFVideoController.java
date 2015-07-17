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
import com.mimi.zfw.mybatis.pojo.REPVideo;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IREPVideoService;
import com.mimi.zfw.util.JsonDateValueProcessor;

@Controller
public class XFVideoController {
	private static final Logger LOG = LoggerFactory
			.getLogger(XFVideoController.class);

	@Resource
	private IREPVideoService repvService;

	@Resource
	IAliyunOSSService aossService;

    @RequiresPermissions("rep:view")
	@RequestMapping(value = "/mi/{repId}/xfvideo/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getREPVideoByPage(HttpServletRequest request,
			@PathVariable int curPage,Integer pageSize, @PathVariable String repId, String name) {
		Object res = null;
		int page = curPage - 1 > 0 ? curPage - 1 : 0;
		try {
			List<REPVideo> items = repvService.findByParams(repId, name,
					page, pageSize);
			if (items != null && !items.isEmpty()) {
				for (REPVideo item : items) {
					item.setPreImageUrl(aossService.addImgParams(
							item.getPreImageUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			int rows = repvService.countByParams(repId, name);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("楼盘视频查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false,
					"楼盘视频查询失败");
		}
		return res;
	}

    @RequiresPermissions("rep:view")
	@RequestMapping(value = "/mi/{repId}/xfvideo/{videoId}/detail", method = { RequestMethod.GET })
	public String toREPVideoDetail(HttpServletRequest request,
			@PathVariable String repId,@PathVariable String videoId) {
		return "/mi/xfvideo/detail";
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/{repId}/xfvideo/add", method = { RequestMethod.GET })
	public String toAddREPVideo(HttpServletRequest request, @PathVariable String repId) {
		return "mi/xfvideo/add";
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/xfvideo/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addREPVideo(HttpServletRequest request, REPVideo video) {
		JSONObject jo = new JSONObject();
		try {
			video.setPreImageUrl(aossService.clearImgParams(video.getPreImageUrl()));
			Map<String, String> res = repvService.add(video);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "楼盘视频保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("楼盘保存失败",e);
			jo.put("success", false);
			jo.put("msg", "楼盘视频保存失败!");
		}
		return jo.toString();
	}


    @RequiresPermissions("rep:view")
	@RequestMapping(value = "/mi/xfvideo/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getREPVideo(@PathVariable String id, HttpServletRequest request) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jo = new JSONObject();
		try {
			REPVideo video = repvService.get(id);
			if (video != null) {
				video.setPreImageUrl(aossService.addImgParams(
						video.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				jo.put("video", JSONObject.fromObject(video, jsonConfig));
			}
		} catch (Exception e) {
			LOG.error("获取楼盘视频失败", e);
			jo.put("video", null);
		}
		return jo.toString();
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/{repId}/xfvideo/{videoId}/edit", method = { RequestMethod.GET })
	public String toUpdateREPVideo(HttpServletRequest request,
			@PathVariable String repId,@PathVariable String videoId) {
		return "/mi/xfvideo/edit";
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/xfvideo/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateREPVideo(HttpServletRequest request, REPVideo video) {
		JSONObject jo = new JSONObject();
		try {
			video.setPreImageUrl(aossService.clearImgParams(video.getPreImageUrl()));
			Map<String, String> res = repvService.modify(video);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新楼盘视频成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新楼盘视频失败", e);
			jo.put("success", false);
			jo.put("msg", "更新楼盘视频失败!");
		}
		return jo.toString();
	}

    @RequiresPermissions("rep:update")
	@RequestMapping(value = "/mi/xfvideo/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelREPVideo(HttpServletRequest request, String videoIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = repvService.batchDel(videoIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "楼盘视频删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "楼盘视频删除失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/xfvideo/uploadImg", method = { RequestMethod.POST })
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
			LOG.error("上传视频缩放图出错！", e);
			jo.put("success", false);
			jo.put("msg", "保存视频缩放图失败");
		}
		return jo.toString();
	}

	public Object getJsonObject(int rows, int totalpage, int curPage,
			int pageSize, List<REPVideo> items, boolean rescode, String msg) {
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
