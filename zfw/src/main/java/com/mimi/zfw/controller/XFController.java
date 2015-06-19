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
import com.mimi.zfw.mybatis.pojo.HouseType;
import com.mimi.zfw.mybatis.pojo.Information;
import com.mimi.zfw.mybatis.pojo.REPImage;
import com.mimi.zfw.mybatis.pojo.REPPano;
import com.mimi.zfw.mybatis.pojo.REPVideo;
import com.mimi.zfw.mybatis.pojo.RealEstateProject;
import com.mimi.zfw.service.IHouseTypeService;
import com.mimi.zfw.service.IInformationService;
import com.mimi.zfw.service.IREPImageService;
import com.mimi.zfw.service.IREPPanoService;
import com.mimi.zfw.service.IREPVideoService;
import com.mimi.zfw.service.IRealEstateProjectService;

@Controller
public class XFController {

	@Resource
	private IRealEstateProjectService repService;

	@Resource
	private IREPImageService repiService;

	@Resource
	private IREPPanoService reppService;

	@Resource
	private IREPVideoService repvService;

	@Resource
	private IHouseTypeService htService;
	
	@Resource IInformationService infoService;

	@RequestMapping(value = "/xf", method = { RequestMethod.GET })
	public String xf(HttpServletRequest request) {
		List<RealEstateProject> list = repService
				.findRealEstateProjectByParams(null, null, null, null, null,
						null, null, null, 0, Constants.DEFAULT_PAGE_SIZE);
		int totalNum = repService.countRealEstateProjectByParams(null, null,
				null, null, null, null, null);
		request.setAttribute("results", list);
		request.setAttribute("total", totalNum);
		// request.getSession().setAttribute("results", list);
		// request.getSession().setAttribute("total", totalNum);
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
			request.setAttribute("results", list);
			request.setAttribute("total", totalNum);
			// request.getSession().setAttribute("results", list);
			// request.getSession().setAttribute("total", totalNum);
		} else {
			List<HouseType> list = htService.findHouseTypeByParams(keyWord,
					region, averagePrice, roomNum, grossFloorArea, saleStatus,
					orderBy, 0, Constants.DEFAULT_PAGE_SIZE);
			int totalNum = htService.countHouseTypeByParams(keyWord, region,
					averagePrice, roomNum, grossFloorArea, saleStatus);
			// request.getSession().setAttribute("results", list);
			// request.getSession().setAttribute("total", totalNum);
			request.setAttribute("results", list);
			request.setAttribute("total", totalNum);
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
				List<HouseType> list = htService.findHouseTypeByParams(keyWord,
						region, averagePrice, roomNum, grossFloorArea,
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
	public String toMap(HttpServletRequest request) {
		return "ui/xf/map";
	}

	@RequestMapping(value = "/xf/map/{id}", method = { RequestMethod.GET })
	public String toREPMap(HttpServletRequest request, @PathVariable String id) {
		RealEstateProject rep = repService.get(id);
		request.setAttribute("rep", rep);
		return "ui/xf/map";
	}

	@RequestMapping(value = "/xf/{id}/detail", method = { RequestMethod.GET })
	public String toDetail(HttpServletRequest request, @PathVariable String id) {
		int targetPage = 0;
		int pageSize = 2;
		List<REPImage> jtt = repiService.getImagesByParams(id,
				Constants.REP_IMAGE_TYPE_JTT, targetPage, pageSize);
		List<REPImage> ptt = repiService.getImagesByParams(id,
				Constants.REP_IMAGE_TYPE_PTT, targetPage, pageSize);
		List<REPImage> sjt = repiService.getImagesByParams(id,
				Constants.REP_IMAGE_TYPE_SJT, targetPage, pageSize);
		List<REPImage> xgt = repiService.getImagesByParams(id,
				Constants.REP_IMAGE_TYPE_XGT, targetPage, pageSize);

		List<REPPano> panos = reppService.getPanosByHTId(id);
		List<REPVideo> videos = repvService.getVideosByHTId(id);

		List<Map<String, String>> topImgs = new ArrayList<Map<String, String>>();
		for (int i = 0; i < jtt.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "image");
			map.put("imgUrl", jtt.get(i).getContentUrl());
			topImgs.add(map);
		}
		for (int i = 0; i < ptt.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "image");
			map.put("imgUrl", ptt.get(i).getContentUrl());
			topImgs.add(map);
		}
		for (int i = 0; i < sjt.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "image");
			map.put("imgUrl", sjt.get(i).getContentUrl());
			topImgs.add(map);
		}
		for (int i = 0; i < xgt.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "image");
			map.put("imgUrl", xgt.get(i).getContentUrl());
			topImgs.add(map);
		}
		for (int i = 0; i < panos.size() && i < pageSize; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "pano");
			map.put("imgUrl", panos.get(i).getPreImageUrl());
			topImgs.add(map);
		}
		for (int i = 0; i < videos.size() && i < pageSize; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "video");
			map.put("imgUrl", videos.get(i).getPreImageUrl());
			topImgs.add(map);
		}

		List<HouseType> hts = htService.getHouseTypeByREPId(id);

		List<HouseType> oneRoomHTs = new ArrayList<HouseType>();
		List<HouseType> twoRoomHTs = new ArrayList<HouseType>();
		List<HouseType> threeRoomHTs = new ArrayList<HouseType>();
		List<HouseType> fourRoomHTs = new ArrayList<HouseType>();
		List<HouseType> fiveRoomHTs = new ArrayList<HouseType>();
		List<HouseType> overFiveRoomHTs = new ArrayList<HouseType>();
		for (int i = 0; i < hts.size(); i++) {
			HouseType ht = hts.get(i);
			switch (ht.getRoomNum()) {
			case 1:
				oneRoomHTs.add(ht);
				break;
			case 2:
				twoRoomHTs.add(ht);
				break;
			case 3:
				threeRoomHTs.add(ht);
				break;
			case 4:
				fourRoomHTs.add(ht);
				break;
			case 5:
				fiveRoomHTs.add(ht);
				break;
			default:
				overFiveRoomHTs.add(ht);
				break;
			}
		}
		
		List<Information> infoList = infoService.findByREPId(id,0,Constants.DEFAULT_PAGE_SIZE);
		int totalInfo = infoService.countByREPId(id);

		RealEstateProject rep = repService.get(id);

		request.setAttribute("topImgs", topImgs);
		request.setAttribute("panos", panos);
		request.setAttribute("videos", videos);
		request.setAttribute("rep", rep);
		request.setAttribute("hxs", hts);
		request.setAttribute("oneRoomHTs", oneRoomHTs);
		request.setAttribute("twoRoomHTs", twoRoomHTs);
		request.setAttribute("threeRoomHTs", threeRoomHTs);
		request.setAttribute("fourRoomHTs", fourRoomHTs);
		request.setAttribute("fiveRoomHTs", fiveRoomHTs);
		request.setAttribute("overFiveRoomHTs", overFiveRoomHTs);
		request.setAttribute("infoList", infoList);
		request.setAttribute("totalInfo", totalInfo);
		return "ui/xf/detail";
	}

	@RequestMapping(value = "/xf/{id}/photos", method = { RequestMethod.GET })
	public String toPhoto(HttpServletRequest request, @PathVariable String id) {
		List<REPImage> images = repiService.getImagesByREPId(id);
		List<REPImage> jtt = new ArrayList<REPImage>();
		List<REPImage> ptt = new ArrayList<REPImage>();
		List<REPImage> sjt = new ArrayList<REPImage>();
		List<REPImage> xgt = new ArrayList<REPImage>();

		for (int i = 0; i < images.size(); i++) {
			REPImage ri = images.get(i);
			switch (ri.getType()) {
			case Constants.REP_IMAGE_TYPE_JTT:
				jtt.add(ri);
				break;
			case Constants.REP_IMAGE_TYPE_PTT:
				ptt.add(ri);
				break;
			case Constants.REP_IMAGE_TYPE_SJT:
				sjt.add(ri);
				break;
			case Constants.REP_IMAGE_TYPE_XGT:
				xgt.add(ri);
				break;
			default:
				break;
			}
		}

		List<REPPano> panos = reppService.getPanosByHTId(id);
		List<REPVideo> videos = repvService.getVideosByHTId(id);
		request.setAttribute("panos", panos);
		request.setAttribute("videos", videos);
		request.setAttribute("jtt", jtt);
		request.setAttribute("ptt", ptt);
		request.setAttribute("sjt", sjt);
		request.setAttribute("xgt", xgt);
		return "ui/photo/photoList";
	}

}
