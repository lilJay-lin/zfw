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
import com.mimi.zfw.mybatis.pojo.Advertisement;
import com.mimi.zfw.mybatis.pojo.Information;
import com.mimi.zfw.service.IAdvertisementService;
import com.mimi.zfw.service.IInformationService;

@Controller
public class InformationController {

	@Resource
	private IAdvertisementService adService;

	@Resource
	private IInformationService infoService;

	@RequestMapping(value = "/info", method = { RequestMethod.GET })
	public String toFCInfo(HttpServletRequest request) {
		List<Advertisement> ads = adService
				.getActiveByLocation(Constants.AD_LOCATION_INFO_TOP);
		List<Information> infoList = infoService.findByParams(
				Constants.INFORMATION_TYPE_FC, 0, Constants.DEFAULT_PAGE_SIZE);
		int total = infoService.countByParams(Constants.INFORMATION_TYPE_FC);
		request.setAttribute("ads", ads);
		request.setAttribute("results", infoList);
		request.setAttribute("total", total);
		return "ui/zx/index";
	}

	@RequestMapping(value = "/info/{type}/type", method = { RequestMethod.GET })
	public String toZHInfo(HttpServletRequest request, @PathVariable String type) {
		List<Advertisement> ads = adService
				.getActiveByLocation(Constants.AD_LOCATION_INFO_TOP);
		List<Information> infoList = infoService.findByParams(type, 0,
				Constants.DEFAULT_PAGE_SIZE);
		int total = infoService.countByParams(type);
		request.setAttribute("ads", ads);
		request.setAttribute("results", infoList);
		request.setAttribute("total", total);
		return "ui/zx/index";
	}

	@RequestMapping(value = "/info/{id}/detail", method = { RequestMethod.GET })
	public String toDetail(HttpServletRequest request, @PathVariable String id) {
		List<Advertisement> ads = adService
				.getActiveByLocation(Constants.AD_LOCATION_INFO_TOP);
		Information info = infoService.get(id);
		request.setAttribute("ads", ads);
		request.setAttribute("info", info);
		return "ui/zx/detail";
	}

	@RequestMapping(value = "/info/json/{type}-{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
	public @ResponseBody
	Object ajaxSearch(HttpServletRequest request, @PathVariable String type,
			@PathVariable Integer targetPage, @PathVariable Integer pageSize) {
		if (targetPage == null) {
			targetPage = 0;
		}
		if (pageSize == null) {
			pageSize = Constants.DEFAULT_PAGE_SIZE;
		}
		JSONObject jo = new JSONObject();
		try {
			List<Information> list = infoService.findByParams(type, targetPage,
					pageSize);
			jo.put("results", list);
			jo.put("success", true);
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}


	@RequestMapping(value = "/info/json/{realEstateProjectId}/{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
	public @ResponseBody
	Object ajaxIdSearch(HttpServletRequest request, @PathVariable String realEstateProjectId,
			@PathVariable Integer targetPage, @PathVariable Integer pageSize) {
		if (targetPage == null) {
			targetPage = 0;
		}
		if (pageSize == null) {
			pageSize = Constants.DEFAULT_PAGE_SIZE;
		}
		JSONObject jo = new JSONObject();
		try {
			List<Information> list = infoService.findByREPId(realEstateProjectId, targetPage, pageSize);
			jo.put("results", list);
			jo.put("success", true);
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}

}
