package com.mimi.zfw.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.HouseType;
import com.mimi.zfw.mybatis.pojo.Information;
import com.mimi.zfw.mybatis.pojo.REPImage;
import com.mimi.zfw.mybatis.pojo.REPPano;
import com.mimi.zfw.mybatis.pojo.REPVideo;
import com.mimi.zfw.mybatis.pojo.RealEstateProject;
import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IHouseTypeService;
import com.mimi.zfw.service.IInformationService;
import com.mimi.zfw.service.IREPImageService;
import com.mimi.zfw.service.IREPPanoService;
import com.mimi.zfw.service.IREPVideoService;
import com.mimi.zfw.service.IRealEstateProjectService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.JsonDateValueProcessor;

@Controller
public class XFController {
	private static final Logger LOG = LoggerFactory
			.getLogger(XFController.class);

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

	@Resource
	IInformationService infoService;

	@Resource
	IUserService userService;

	@Resource
	IAliyunOSSService aossService;

	@RequestMapping(value = "/xf", method = { RequestMethod.GET })
	public String xf(HttpServletRequest request) {
		List<RealEstateProject> list = repService
				.findRealEstateProjectByParams(null, null, null, null, null,
						null, null, null, 0, Constants.DEFAULT_PAGE_SIZE);
		int totalNum = repService.countRealEstateProjectByParams(null, null,
				null, null, null, null, null);
		if (list != null && !list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				RealEstateProject rep = list.get(i);
				rep.setPreImageUrl(aossService.addImgParams(
						rep.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
			}
		}
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
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					RealEstateProject rep = list.get(i);
					rep.setPreImageUrl(aossService.addImgParams(
							rep.getPreImageUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
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

			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					HouseType ht = list.get(i);
					ht.setPreImageUrl(aossService.addImgParams(
							ht.getPreImageUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
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
				if (list != null && !list.isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						RealEstateProject rep = list.get(i);
						rep.setPreImageUrl(aossService.addImgParams(
								rep.getPreImageUrl(),
								Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
					}
				}
				jo.put("results", list);
			} else {
				List<HouseType> list = htService.findHouseTypeByParams(keyWord,
						region, averagePrice, roomNum, grossFloorArea,
						saleStatus, orderBy, targetPage, pageSize);
				if (list != null && !list.isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						HouseType ht = list.get(i);
						ht.setPreImageUrl(aossService.addImgParams(
								ht.getPreImageUrl(),
								Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
					}
				}
				jo.put("results", list);
			}
			jo.put("success", true);
		} catch (Exception e) {
			LOG.error("查询新房出错！", e);
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/xf/map", method = { RequestMethod.GET })
	public String toMap(HttpServletRequest request) {
		return "ui/xf/map";
	}

	@RequestMapping(value = "/xf/{id}/map", method = { RequestMethod.GET })
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
		if (panos != null && !panos.isEmpty()) {
			for (int i = 0; i < panos.size(); i++) {
				REPPano pano = panos.get(i);
				pano.setPreImageUrl(aossService.addImgParams(
						pano.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_BANNER));
			}
		}
		if (videos != null && !videos.isEmpty()) {
			for (int i = 0; i < videos.size(); i++) {
				REPVideo video = videos.get(i);
				video.setPreImageUrl(aossService.addImgParams(
						video.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_BANNER));
			}
		}
		if (topImgs != null && !topImgs.isEmpty()) {
			for (int i = 0; i < topImgs.size(); i++) {
				Map<String, String> map = topImgs.get(i);
				map.put("imgUrl", aossService.addImgParams(map.get("imgUrl"),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_BANNER));
			}
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

		List<Information> infoList = infoService.findByREPId(id, 0,
				Constants.DEFAULT_PAGE_SIZE);
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/xf/{id}/photos", method = { RequestMethod.GET })
	public String toPhoto(HttpServletRequest request, @PathVariable String id) {
		List<REPImage> images = repiService.getImagesByREPId(id);
		List<REPPano> panos = reppService.getPanosByHTId(id);
		List<REPVideo> videos = repvService.getVideosByHTId(id);

		List<Map<String, Object>> photos = new ArrayList<Map<String, Object>>();
		if (panos != null && !panos.isEmpty()) {
			Map<String, Object> listMap = new HashMap<String, Object>();
			listMap.put("name", Constants.PHOTO_DATA_TITLE_PANO);
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (int i = 0; i < panos.size(); i++) {
				REPPano pano = panos.get(i);
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", pano.getName());
				map.put("contentUrl", aossService.addImgParams(
						pano.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_IMG));
				map.put("preImageUrl", aossService.addImgParams(
						pano.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				map.put("dataType", Constants.PHOTO_DATA_TYPE_PANO);
				list.add(map);
			}
			listMap.put("list", list);
			photos.add(listMap);
		}

		if (videos != null && !videos.isEmpty()) {
			Map<String, Object> listMap = new HashMap<String, Object>();
			listMap.put("name", Constants.PHOTO_DATA_TITLE_VIDEO);
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			for (int i = 0; i < videos.size(); i++) {
				REPVideo video = videos.get(i);
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", video.getName());
				map.put("contentUrl", aossService.addImgParams(
						video.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_IMG));
				map.put("preImageUrl", aossService.addImgParams(
						video.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				map.put("dataType", Constants.PHOTO_DATA_TYPE_VIDEO);
				list.add(map);
			}
			listMap.put("list", list);
			photos.add(listMap);
		}

		if (images != null && !images.isEmpty()) {
			String[] types = { Constants.REP_IMAGE_TYPE_SJT,
					Constants.REP_IMAGE_TYPE_XGT, Constants.REP_IMAGE_TYPE_JTT,
					Constants.REP_IMAGE_TYPE_PTT };
			List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
			for (int j = 0; j < types.length; j++) {
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("name", types[j]);
				List<Map<String, String>> tempMapList = new ArrayList<Map<String, String>>();
				tempMap.put("list", tempMapList);
				tempList.add(tempMap);
			}
			for (int i = 0; i < images.size(); i++) {
				REPImage image = images.get(i);
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", image.getName());
				map.put("contentUrl", aossService.addImgParams(
						image.getContentUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_IMG));
				map.put("preImageUrl", aossService.addImgParams(
						image.getContentUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				map.put("dataType", Constants.PHOTO_DATA_TYPE_IMAGE);
				for (int j = 0; j < types.length; j++) {
					if (types[j].equals(image.getType())) {
						((List<Map<String, String>>) tempList.get(j)
								.get("list")).add(map);
					}
				}
			}
			photos.addAll(tempList);
		}
		request.setAttribute("photos", photos);
		return "ui/photo/photoList";
	}

	@RequestMapping(value = "/mi/xf", method = { RequestMethod.GET })
	public String toMIREP(HttpServletRequest request) {
		return "mi/xf/index";
	}

	@RequestMapping(value = "/mi/xf/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getREPByPage(HttpServletRequest request,
			@PathVariable int curPage) {

		Object res = null;

		int page = curPage - 1 > 0 ? curPage - 1 : 0;

		String name = request.getParameter("name") == null ? null
				: (String) request.getParameter("name");

		Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
				: Integer.valueOf((String) request.getParameter("pagesize"));

		try {
			List<RealEstateProject> items = repService
					.findRealEstateProjectByParams(name, null, null, null,
							null, null, null, null, page, pageSize);
			int rows = repService.countRealEstateProjectByParams(name, null,
					null, null, null, null, null);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("楼盘查询失败",e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false, "楼盘查询失败");
		}
		return res;
	}

	@RequestMapping(value = "/mi/xf/add", method = { RequestMethod.GET })
	public String toAddREP(Model model, HttpServletRequest request) {
		return "mi/xf/add";
	}

	@RequestMapping(value = "/mi/xf/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addREP(HttpServletRequest request, RealEstateProject rep,
			String userIds, String infoIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = repService.addREP(rep, userIds, infoIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "楼盘保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("楼盘保存失败",e);
			jo.put("success", false);
			jo.put("msg", "楼盘保存失败!");
		}
		return jo.toString();
	}


	@RequestMapping(value = "/mi/xf/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getREP(@PathVariable String id, HttpServletRequest request) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject jo = new JSONObject();
		
		try {
			RealEstateProject rep = repService.get(id);
			if (rep != null) {
				rep.setPreImageUrl(aossService.addImgParams(
						rep.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				jo.put("rep", JSONObject.fromObject(rep, jsonConfig));
				List<User> relationUserList = userService.getUsersByREPId(id);
				List<Information> relationInfoList = infoService.findByREPId(id, 0, Integer.MAX_VALUE);
				jo.put("relationUserList", relationUserList);
				jo.put("relationInfoList", relationInfoList);
			}
		} catch (Exception e) {
			LOG.error("获取楼盘失败", e);
			jo.put("rep", null);
			jo.put("relationUserList", null);
			jo.put("relationInfoList", null);
		}
		return jo.toString();
//		return JSONObject.fromObject(jo, jsonConfig).toString();
	}

	@RequestMapping(value = "/mi/xf/{repId}/edit", method = { RequestMethod.GET })
	public String toUpdateInfo(HttpServletRequest request,
			@PathVariable String repId) {
//		model.addAttribute("repId", repId);
		return "/mi/xf/edit";
	}

	@RequestMapping(value = "/mi/xf/{repId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateREP(HttpServletRequest request, RealEstateProject rep,
			@PathVariable String repId, String addUserRelations, String delUserRelations, String addInfoRelations, String delInfoRelations) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = repService.updateREP(rep, addUserRelations, delUserRelations, addInfoRelations, delInfoRelations);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新楼盘成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新楼盘失败", e);
			jo.put("success", false);
			jo.put("msg", "更新楼盘失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/xf/uploadImg", method = { RequestMethod.POST })
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
			LOG.error("上传楼盘缩略图出错！", e);
			jo.put("success", false);
			jo.put("msg", "保存图片失败");
		}
		return jo.toString();
	}

	public Object getJsonObject(int rows, int totalpage, int curPage,
			int pageSize, List<RealEstateProject> items, boolean rescode,
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
