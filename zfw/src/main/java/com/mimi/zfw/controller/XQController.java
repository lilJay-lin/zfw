package com.mimi.zfw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.RCImage;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IRCImageService;
import com.mimi.zfw.service.IResidenceCommunityService;

@Controller
public class XQController {
	private static final Logger LOG = LoggerFactory.getLogger(XQController.class);  
	@Resource
	private IResidenceCommunityService rcService;
	@Resource
	private IRCImageService rciService;
	@Resource
	private IAliyunOSSService aossService;

	@RequestMapping(value = "/xq/{id}/detail", method = { RequestMethod.GET })
	public String toDetail(HttpServletRequest request, @PathVariable String id) {

		ResidenceCommunity rc = rcService.get(id);
		List<RCImage> topImgs = rciService.getImagesByRCId(id, 0,
				Constants.DEFAULT_PAGE_SIZE);
		if(topImgs!=null && !topImgs.isEmpty()){
			for(int i=0;i<topImgs.size();i++){
				RCImage ri = topImgs.get(i);
				ri.setContentUrl(aossService.addImgParams(ri.getContentUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_BANNER));
			}
		}
		request.setAttribute("topImgs", topImgs);
		request.setAttribute("rc", rc);
		return "ui/xq/detail";
	}

	@RequestMapping(value = "/xq/{id}/photos", method = { RequestMethod.GET })
	public String toPhoto(HttpServletRequest request, @PathVariable String id) {
		List<RCImage> images = rciService.getImagesByRCId(id, 0,5);
		List<Map<String,Object>> photos = new ArrayList<Map<String,Object>>();

		if(images!=null && !images.isEmpty()){
			Map<String,Object> listMap = new HashMap<String,Object>();
			listMap.put("name", Constants.PHOTO_DATA_TITLE_IMAGE);
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for(int i=0;i<images.size();i++){
				RCImage image = images.get(i);
				Map<String,String> map = new HashMap<String,String>();
				map.put("name", image.getName());
				map.put("contentUrl", aossService.addImgParams(image.getContentUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_IMG));
				map.put("preImageUrl", aossService.addImgParams(image.getContentUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				map.put("dataType", Constants.PHOTO_DATA_TYPE_IMAGE);
				list.add(map);
			}
			listMap.put("list", list);
			photos.add(listMap);
		}
		request.setAttribute("photos", photos);
		return "ui/photo/photoList";
	}

	@RequestMapping(value = "/xq/{type}/map", method = { RequestMethod.GET })
	public String toMap(HttpServletRequest request, @PathVariable String type) {
		if ("zf".equals(type)) {
			return "ui/xq/zfMap";
		}
		return "ui/xq/esfMap";
	}

	@RequestMapping(value = "/xq/{type}/{id}/map", method = { RequestMethod.GET })
	public String toMap(HttpServletRequest request, @PathVariable String type,
			@PathVariable String id) {
		ResidenceCommunity rc = rcService.get(id);
		request.setAttribute("rc", rc);
		if ("zf".equals(type)) {
			return "ui/xq/zfMap";
		}
		return "ui/xq/esfMap";
	}

	@RequestMapping(value = "/xq/esf/json/{region}-{totalPrice}-{roomNum}-{grossFloorArea}-{bound}/search", method = { RequestMethod.GET })
	public @ResponseBody
	Object esfAjaxSearch(HttpServletRequest request,
			@PathVariable String region, @PathVariable String totalPrice,
			@PathVariable Integer roomNum, @PathVariable String grossFloorArea,
			@PathVariable String bound) {
		JSONObject jo = new JSONObject();
		try {
			List<ResidenceCommunity> list = rcService.findResidenceCommunitiesByParams(bound,
					region, totalPrice, roomNum, grossFloorArea, null, null,
					null);
			if(list!=null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					ResidenceCommunity rc = list.get(i);
					rc.setPreImageUrl(aossService.addImgParams(rc.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			jo.put("results", list);
			jo.put("success", true);
		} catch (Exception e) {
			LOG.error("查询二手房小区出错！",e);
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/xq/zf/json/{region}-{rental}-{roomNum}-{grossFloorArea}-{bound}/search", method = { RequestMethod.GET })
	public @ResponseBody
	Object zfAjaxSearch(HttpServletRequest request,
			@PathVariable String region, @PathVariable String rental,
			@PathVariable Integer roomNum, @PathVariable String grossFloorArea,
			@PathVariable String bound) {
		JSONObject jo = new JSONObject();
		try {
			List<ResidenceCommunity> list =  rcService.findResidenceCommunitiesByParams(bound,
					region, null, null, null, rental, roomNum, grossFloorArea);
			if(list!=null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					ResidenceCommunity rc = list.get(i);
					rc.setPreImageUrl(aossService.addImgParams(rc.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			jo.put("results", list);
			jo.put("success", true);
		} catch (Exception e) {
			LOG.error("查询租房小区出错！",e);
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}
	
	@RequestMapping(value="/xq/json/{name}/search",method={RequestMethod.GET})
	public @ResponseBody Object ajaxSearchByName(HttpServletRequest request,@PathVariable String name){
		JSONObject jo = new JSONObject();
		try {
			List<ResidenceCommunity> list =  rcService.findByName(name);
			if(list!=null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					ResidenceCommunity rc = list.get(i);
					rc.setPreImageUrl(aossService.addImgParams(rc.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			jo.put("results", list);
			jo.put("success", true);
		} catch (Exception e) {
			LOG.error("查询小区出错！",e);
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}
	

    @RequiresPermissions("rc:query")
	@RequestMapping(value = "/mi/xq", method = { RequestMethod.GET })
	public String toMIRC(HttpServletRequest request) {
		return "mi/xq/index";
	}

    @RequiresPermissions("rc:query")
	@RequestMapping(value = "/mi/xq/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getRCByPage(HttpServletRequest request,String name,Boolean active,
			@PathVariable int curPage) {

		Object res = null;

		int page = curPage - 1 > 0 ? curPage - 1 : 0;

		Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
				: Integer.valueOf((String) request.getParameter("pagesize"));

		try {
			List<ResidenceCommunity> items = rcService.findByParams(name,active,page,pageSize);
			int rows = rcService.countByParams(name,active);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("小区查询失败", e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false, "小区查询失败");
		}
		return res;
	}

    @RequiresPermissions("rc:view")
	@RequestMapping(value = "/mi/xq/{rcId}/detail", method = { RequestMethod.GET })
	public String toRCDetail(HttpServletRequest request,
			@PathVariable String rcId) {
		return "/mi/xq/detail";
	}

    @RequiresPermissions("rc:add")
	@RequestMapping(value = "/mi/xq/add", method = { RequestMethod.GET })
	public String toAddRC(Model model, HttpServletRequest request) {
		return "mi/xq/add";
	}

    @RequiresPermissions("rc:add")
	@RequestMapping(value = "/mi/xq/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addRC(HttpServletRequest request, ResidenceCommunity rc) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = rcService.addRC(rc);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "小区保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("小区保存失败", e);
			jo.put("success", false);
			jo.put("msg", "小区保存失败!");
		}
		return jo.toString();
	}

    @RequiresPermissions("rc:view")
	@RequestMapping(value = "/mi/xq/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getRC(@PathVariable String id, HttpServletRequest request) {
		JSONObject jo = new JSONObject();

		try {
			ResidenceCommunity rc = rcService.get(id);
			if (rc != null) {
				rc.setPreImageUrl(aossService.addImgParams(
						rc.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				jo.put("rc", rc);
			}
		} catch (Exception e) {
			LOG.error("获取小区失败", e);
			jo.put("rc", null);
		}
		return jo.toString();
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/xq/{rcId}/edit", method = { RequestMethod.GET })
	public String toUpdateRC(HttpServletRequest request,
			@PathVariable String rcId) {
		return "/mi/xq/edit";
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/xq/{rcId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateRC(HttpServletRequest request, ResidenceCommunity rc,
			@PathVariable String rcId) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = rcService.updateRC(rc);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新小区成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新小区失败", e);
			jo.put("success", false);
			jo.put("msg", "更新小区失败!");
		}
		return jo.toString();
	}

    @RequiresPermissions("rc:del")
	@RequestMapping(value = "/mi/xq/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelHTPano(HttpServletRequest request, String rcIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = rcService.batchDel(rcIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "小区删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "小区删除失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/xq/uploadImg", method = { RequestMethod.POST })
	public @ResponseBody
	Object upload(HttpServletRequest request,
			@RequestParam("theFile") MultipartFile theFile) {
		JSONObject jo = new JSONObject();
		try {
			String path = aossService.saveFileToServer(theFile);
			path = aossService.addImgParams(path,
					Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG);
			jo.put("imgPath", path);
			jo.put("success", true);
		} catch (IOException e) {
			LOG.error("上传小区缩略图出错！", e);
			jo.put("success", false);
			jo.put("msg", "保存图片失败");
		}
		return jo.toString();
	}

	public Object getJsonObject(int rows, int totalpage, int curPage,
			int pageSize, List<ResidenceCommunity> items, boolean rescode,
			String msg) {
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
