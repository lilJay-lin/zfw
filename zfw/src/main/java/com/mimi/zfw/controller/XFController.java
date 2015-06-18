package com.mimi.zfw.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.HouseType;
import com.mimi.zfw.mybatis.pojo.RealEstateProject;
import com.mimi.zfw.service.IHouseTypeService;
import com.mimi.zfw.service.IRealEstateProjectService;

@Controller
public class XFController {

	@Resource
	private IRealEstateProjectService repService;

	@Resource
	private IHouseTypeService htService;

	@RequestMapping(value = "/xf", method = { RequestMethod.GET })
	public String xf(HttpServletRequest request) {
		List<RealEstateProject> list = repService
				.findRealEstateProjectByParams(null, null, null, null, null,
						null, null, null, 0, Constants.DEFAULT_PAGE_SIZE);
		int totalNum = repService.countRealEstateProjectByParams(null, null,
				null, null, null, null, null);
		request.getSession().setAttribute("results", list);
		request.getSession().setAttribute("total", totalNum);
		request.setAttribute("resultType", "楼盘");
		return "ui/xf/index";
	}

	@RequestMapping(value = "/xf/{keyWord}-{region}-{averagePrice}-{roomNum}-{grossFloorArea}-{saleStatus}-{resultType}-{orderBy}/search", method = { RequestMethod.GET })
	public String search(HttpServletRequest request,
			@PathVariable String keyWord, @PathVariable String region,
			@PathVariable String averagePrice, @PathVariable Integer roomNum,
			@PathVariable String grossFloorArea,
			@PathVariable String saleStatus, @PathVariable String resultType,
			@PathVariable String orderBy) {
		if ("楼盘".equals(resultType)) {
			List<RealEstateProject> list = repService
					.findRealEstateProjectByParams(keyWord, region,
							averagePrice, roomNum, grossFloorArea, saleStatus,
							null, orderBy, 0, Constants.DEFAULT_PAGE_SIZE);
			int totalNum = repService.countRealEstateProjectByParams(keyWord,
					region, averagePrice, roomNum, grossFloorArea, saleStatus,
					null);
			request.getSession().setAttribute("results", list);
			request.getSession().setAttribute("total", totalNum);
		} else {
			List<HouseType> list = htService.findHouseTypeByParams(keyWord,
					region, averagePrice, roomNum, grossFloorArea, saleStatus,
					orderBy, 0, Constants.DEFAULT_PAGE_SIZE);
			int totalNum = htService.countHouseTypeByParams(keyWord, region,
					averagePrice, roomNum, grossFloorArea, saleStatus);
			request.getSession().setAttribute("results", list);
			request.getSession().setAttribute("total", totalNum);
		}
		return "ui/xf/index";
	}

	@RequestMapping(value = "/xf/json/{keyWord}-{region}-{averagePrice}-{roomNum}-{grossFloorArea}-{saleStatus}-{resultType}-{bound}-{orderBy}-{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
	public @ResponseBody
	Object ajaxSearch(HttpServletRequest request, @PathVariable String keyWord,
			@PathVariable String region, @PathVariable String averagePrice,
			@PathVariable Integer roomNum, @PathVariable String grossFloorArea,
			@PathVariable String saleStatus, @PathVariable String resultType,
			@PathVariable String bound, @PathVariable String orderBy,
			@PathVariable Integer targetPage, @PathVariable Integer pageSize) {
		if (targetPage == null) {
			targetPage = 0;
		}
		if (pageSize == null) {
			pageSize = Constants.DEFAULT_PAGE_SIZE;
		}
		JSONObject jo = new JSONObject();
		try {
			if ("楼盘".equals(resultType)) {
				List<RealEstateProject> list = repService
						.findRealEstateProjectByParams(keyWord, region,
								averagePrice, roomNum, grossFloorArea,
								saleStatus, bound, orderBy, targetPage,
								pageSize);
				jo.put("results", list);
			} else {
				List<HouseType> list = htService.findHouseTypeByParams(
						keyWord, region, averagePrice, roomNum, grossFloorArea,
						saleStatus, orderBy, targetPage, pageSize);
				jo.put("results", list);
			}
			jo.put("success", true);
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/xf/map", method = { RequestMethod.GET })
	public String map(HttpServletRequest request) {
		return "ui/xf/map";
	}

	@RequestMapping(value = "/xf/{id}/detail", method = { RequestMethod.GET })
	public String toDetail(Model model, @PathVariable String id) {
		return "ui/xf/detail";
	}

	@RequestMapping(value = "/xf/{id}/photo", method = { RequestMethod.GET })
	public String toPhoto(Model model, @PathVariable String id) {
		return "ui/photo/photoList";
	}

}
