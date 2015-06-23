package com.mimi.zfw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.RHImage;
import com.mimi.zfw.mybatis.pojo.RHPano;
import com.mimi.zfw.mybatis.pojo.RentalHousing;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.service.IRHImageService;
import com.mimi.zfw.service.IRHPanoService;
import com.mimi.zfw.service.IRentalHousingService;
import com.mimi.zfw.service.IResidenceCommunityService;

@Controller
public class ZFController {
	@Resource
	private IResidenceCommunityService rcService;
	@Resource
	private IRentalHousingService rhService;
	@Resource
	private IRHImageService rhiService;
	@Resource
	private IRHPanoService rhpService;

	@RequestMapping(value = "/zf", method = { RequestMethod.GET })
	public String zf(HttpServletRequest request) {
		List<RentalHousing> list = rhService.findRentalHousingsByParams(null,
				null, null, null, null, null, null, 0,
				Constants.DEFAULT_PAGE_SIZE);
		int totalNum = rhService.countRentalHousingByParams(null, null, null,
				null, null, null, null);
		request.setAttribute("results", list);
		request.setAttribute("total", totalNum);
		return "ui/zf/index";
	}

	@RequestMapping(value = "/zf/{id}/detail", method = { RequestMethod.GET })
	public String toDetail(HttpServletRequest request, @PathVariable String id) {
		int targetPage = 0;
		int pageSize = 2;
		List<RHImage> images = rhiService.getImagesByParams(id, targetPage,
				pageSize);
		List<RHPano> panos = rhpService.getPanosByRHId(id);
		List<Map<String, String>> topImgs = new ArrayList<Map<String, String>>();
		for (int i = 0; i < images.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "image");
			map.put("imgUrl", images.get(i).getContentUrl());
			topImgs.add(map);
		}
		for (int i = 0; i < panos.size() && i < pageSize; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "pano");
			map.put("imgUrl", panos.get(i).getPreImageUrl());
			topImgs.add(map);
		}

		RentalHousing rh = rhService.get(id);
		ResidenceCommunity rc = rcService.get(rh.getResidenceCommunityId());

		String dateDesc = "";
		if (rh != null) {
			long tempTime = (System.currentTimeMillis() - rh.getUpdateDate()
					.getTime()) / (1000);
			if (tempTime >= 60) {
				tempTime = tempTime / 60;
				dateDesc = "分钟前更新";
			}
			if (tempTime >= 60) {
				tempTime = tempTime / 60;
				dateDesc = "小时前更新";
			}
			if (tempTime >= 24) {
				tempTime = tempTime / 24;
				dateDesc = "天前更新";
			}
			if (tempTime >= 30) {
				tempTime = tempTime / 30;
				dateDesc = "个月前更新";
			}
			if (tempTime >= 12) {
				tempTime = tempTime / 12;
				dateDesc = "年前更新";
			}
			if(tempTime>0){
				dateDesc = "(" + tempTime + dateDesc + ")";
			}
		}
		request.setAttribute("panos", panos);

		request.setAttribute("zf", rh);
		request.setAttribute("rc", rc);
		request.setAttribute("topImgs", topImgs);
		request.setAttribute("dateDesc", dateDesc);
		return "ui/zf/detail";
	}

	@RequestMapping(value = "/zf/{id}/photos", method = { RequestMethod.GET })
	public String toPhoto(HttpServletRequest request, @PathVariable String id) {
		List<RHImage> images = rhiService.getImagesByParams(id, 0,
				Integer.MAX_VALUE);
		List<RHPano> panos = rhpService.getPanosByRHId(id);
		request.setAttribute("panos", panos);
		request.setAttribute("images", images);
		return "ui/photo/photoList";
	}

	@RequestMapping(value = "/zf/{residenceCommunityId}-{keyWord}-{region}-{rental}-{roomNum}-{grossFloorArea}-{orderBy}/search", method = { RequestMethod.GET })
	public String search(HttpServletRequest request,
			@PathVariable String residenceCommunityId,
			@PathVariable String keyWord, @PathVariable String region,
			@PathVariable String rental, @PathVariable Integer roomNum,
			@PathVariable String grossFloorArea, @PathVariable String orderBy) {
		List<RentalHousing> list = rhService.findRentalHousingsByParams(
				residenceCommunityId, keyWord, region, rental, roomNum,
				grossFloorArea, orderBy, 0, Constants.DEFAULT_PAGE_SIZE);
		int totalNum = rhService.countRentalHousingByParams(
				residenceCommunityId, keyWord, region, rental, roomNum,
				grossFloorArea, orderBy);
		request.setAttribute("results", list);
		request.setAttribute("total", totalNum);
		return "ui/zf/index";
	}

	@RequestMapping(value = "/zf/json/{residenceCommunityId}-{keyWord}-{region}-{rental}-{roomNum}-{grossFloorArea}-{orderBy}-{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
	public @ResponseBody
	Object ajaxSearch(HttpServletRequest request, @PathVariable String keyWord,
			@PathVariable String residenceCommunityId,
			@PathVariable String region, @PathVariable String rental,
			@PathVariable Integer roomNum, @PathVariable String grossFloorArea,
			@PathVariable String orderBy, @PathVariable Integer targetPage,
			@PathVariable Integer pageSize) {
		if (targetPage == null) {
			targetPage = 0;
		}
		if (pageSize == null) {
			pageSize = Constants.DEFAULT_PAGE_SIZE;
		}
		JSONObject jo = new JSONObject();
		try {
			jo.put("results", rhService.findRentalHousingsByParams(
					residenceCommunityId, keyWord, region, rental, roomNum,
					grossFloorArea, orderBy, targetPage, pageSize));
			jo.put("success", true);
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}
}
