package com.mimi.zfw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	

}
