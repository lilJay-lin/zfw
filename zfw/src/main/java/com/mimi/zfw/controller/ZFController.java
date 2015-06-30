package com.mimi.zfw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.RHImage;
import com.mimi.zfw.mybatis.pojo.RHPano;
import com.mimi.zfw.mybatis.pojo.RentalHousing;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.service.IRHImageService;
import com.mimi.zfw.service.IRHPanoService;
import com.mimi.zfw.service.IRentalHousingService;
import com.mimi.zfw.service.IResidenceCommunityService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.DateUtil;
import com.mimi.zfw.util.FileUtil;

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
	@Resource
	private IUserService userService;

	@RequestMapping(value = "/zf", method = { RequestMethod.GET })
	public String zf(HttpServletRequest request) {
		List<RentalHousing> list = rhService.findRentalHousingsByParams(null,
				null, null, null, null, null, null, 0,
				Constants.DEFAULT_PAGE_SIZE);
		int totalNum = rhService.countRentalHousingByParams(null, null, null,
				null, null, null);
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
			dateDesc = DateUtil.getUpdateTimeStr(rh.getUpdateDate());
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
				grossFloorArea);
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

	@RequestMapping(value = "user/zf", method = { RequestMethod.GET })
	public String toCurUserEsf(HttpServletRequest request) {
		String userId = userService.getCurUserId();
		List<RentalHousing> list = rhService.getByUserId(userId, 0,
				Constants.DEFAULT_PAGE_SIZE);
		int total = rhService.countByUserId(userId);
		request.setAttribute("results", list);
		request.setAttribute("total", total);
		return "ui/user/zf/index";
	}

	@RequestMapping(value = "user/zf/add", method = { RequestMethod.GET })
	public String toAdd(HttpServletRequest request) {
		return "ui/user/zf/add";
	}

	@RequestMapping(value = "user/zf/json/add", method = { RequestMethod.POST })
	public @ResponseBody
	Object add(HttpServletRequest request, RentalHousing rh, String imgUrls) {
		JSONObject jo = new JSONObject();
		try {
			String errorStr = rhService.saveCascading(rh, imgUrls);
			if (StringUtils.isBlank(errorStr)) {
				jo.put("success", true);
			} else {
				jo.put("success", false);
				jo.put("msg", errorStr);
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "发布出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/zf/json/del", method = { RequestMethod.POST })
	public @ResponseBody
	Object del(HttpServletRequest request, String id) {
		JSONObject jo = new JSONObject();
		try {
			String errorStr = rhService.deleteUserRHByFlag(id);
			if (StringUtils.isBlank(errorStr)) {
				jo.put("success", true);
			} else {
				jo.put("success", false);
				jo.put("msg", errorStr);
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "删除出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/zf/json/refresh", method = { RequestMethod.POST })
	public @ResponseBody
	Object refresh(HttpServletRequest request, String id) {
		JSONObject jo = new JSONObject();
		try {
			String errorStr = rhService.refreshUserRH(id);
			if (StringUtils.isBlank(errorStr)) {
				jo.put("success", true);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_YEAR, Constants.ACTIVE_TIME);
				jo.put("time",
						cal.get(Calendar.YEAR) + "-"
								+ (cal.get(Calendar.MONTH) + 1) + "-"
								+ cal.get(Calendar.DAY_OF_MONTH));
			} else {
				jo.put("success", false);
				jo.put("msg", errorStr);
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "刷新出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/zf/json/update", method = { RequestMethod.POST })
	public @ResponseBody
	Object update(HttpServletRequest request, RentalHousing rh, String imgUrls) {
		JSONObject jo = new JSONObject();
		try {
			String errorStr = rhService.updateCascading(rh, imgUrls);
			if (StringUtils.isBlank(errorStr)) {
				jo.put("success", true);
			} else {
				jo.put("success", false);
				jo.put("msg", errorStr);
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "修改出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/zf/{id}/manage", method = { RequestMethod.GET })
	public String toManager(HttpServletRequest request, @PathVariable String id) {
		RentalHousing rh = rhService.get(id);
		List<RHImage> images = rhiService.getImagesByParams(id, 0,
				Integer.MAX_VALUE);
		Calendar cal = Calendar.getInstance();
		cal.setTime(rh.getUpdateDate());
		cal.add(Calendar.DAY_OF_YEAR, Constants.ACTIVE_TIME);
		request.setAttribute("deadline",
				cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
						+ "-" + cal.get(Calendar.DAY_OF_MONTH));
		request.setAttribute("zf", rh);
		request.setAttribute("images", images);
		return "ui/user/zf/manage";
	}

	@RequestMapping(value = "user/zf/json/{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
	public @ResponseBody
	Object ajaxCurUserEsfSearch(HttpServletRequest request,
			@PathVariable Integer targetPage, @PathVariable Integer pageSize) {
		if (targetPage == null) {
			targetPage = 0;
		}
		if (pageSize == null) {
			pageSize = Constants.DEFAULT_PAGE_SIZE;
		}
		JSONObject jo = new JSONObject();
		try {
			String userId = userService.getCurUserId();
			jo.put("results",
					rhService.getByUserId(userId, targetPage, pageSize));
			jo.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/user/zf/uploadImg", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	Object upload(HttpServletRequest request,
			@RequestParam("theFile") MultipartFile theFile) {

		JSONObject jo = new JSONObject();
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.getTimeInMillis();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			String path = "/assets/upload/" + year + "/" + month + "/" + day
					+ "/" + hour + "/";
			path = request.getContextPath()
					+ path
					+ FileUtil.saveFileToServer(theFile, request.getSession()
							.getServletContext().getRealPath("/")
							+ path);
			jo.put("imgPath", path);
			jo.put("success", true);
		} catch (IOException e) {
			jo.put("success", false);
			jo.put("msg", "保存图片失败");
		}
		return jo.toString();
	}

}
