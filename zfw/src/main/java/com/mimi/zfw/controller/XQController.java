package com.mimi.zfw.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.RCImage;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.service.IRCImageService;
import com.mimi.zfw.service.IResidenceCommunityService;

@Controller
public class XQController {
	@Resource
	private IResidenceCommunityService rcService;
	@Resource
	private IRCImageService rciService;

	@RequestMapping(value = "/xq/{id}/detail", method = { RequestMethod.GET })
	public String toDetail(HttpServletRequest request, @PathVariable String id) {

		ResidenceCommunity rc = rcService.get(id);
		List<RCImage> topImgs = rciService.getImagesByRCId(id, 0,
				Constants.DEFAULT_PAGE_SIZE);

		request.setAttribute("topImgs", topImgs);
		request.setAttribute("rc", rc);
		return "ui/xq/detail";
	}

	@RequestMapping(value = "/xq/{id}/photos", method = { RequestMethod.GET })
	public String toPhoto(HttpServletRequest request, @PathVariable String id) {
		List<RCImage> images = rciService.getImagesByRCId(id, 0,5);
		request.setAttribute("images", images);
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
			jo.put("results", rcService.findResidenceCommunitiesByParams(bound,
					region, totalPrice, roomNum, grossFloorArea, null, null,
					null));
			jo.put("success", true);
		} catch (Exception e) {
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
			jo.put("results", rcService.findResidenceCommunitiesByParams(bound,
					region, null, null, null, rental, roomNum, grossFloorArea));
			jo.put("success", true);
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}

}
