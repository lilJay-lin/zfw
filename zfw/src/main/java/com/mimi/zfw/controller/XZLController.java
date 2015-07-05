package com.mimi.zfw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
import com.mimi.zfw.mybatis.pojo.OBImage;
import com.mimi.zfw.mybatis.pojo.OBPano;
import com.mimi.zfw.mybatis.pojo.OfficeBuilding;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IOBImageService;
import com.mimi.zfw.service.IOBPanoService;
import com.mimi.zfw.service.IOfficeBuildingService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.DateUtil;

@Controller
public class XZLController {
	private static final Logger LOG = LoggerFactory.getLogger(XZLController.class);  
	@Resource
	private IOfficeBuildingService obService;
	@Resource
	private IOBImageService obiService;
	@Resource
	private IOBPanoService obpService;
    @Resource
    private IUserService userService;
	@Resource
	private IAliyunOSSService aossService;

    @RequestMapping(value = "/xzl", method = { RequestMethod.GET })
	public String ob(HttpServletRequest request) {
		List<OfficeBuilding> list = obService.findOfficeBuildingsByParams(null, null, null, null,
				Constants.ROS_RENT_ONLY, null, null, null, 0,
				Constants.DEFAULT_PAGE_SIZE);
		int totalNum = obService.countOfficeBuildingByParams(null, null, null, null,
				Constants.ROS_RENT_ONLY, null, null);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				OfficeBuilding ob = list.get(i);
				ob.setPreImageUrl(aossService.addImgParams(ob.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
			}
		}
		request.setAttribute("results", list);
		request.setAttribute("total", totalNum);
		return "ui/xzl/index";
	}

	@RequestMapping(value = "/xzl/{id}/detail", method = { RequestMethod.GET })
	public String toDetail(HttpServletRequest request, @PathVariable String id) {
		int targetPage = 0;
		int pageSize = 2;
		List<OBImage> images = obiService.getImagesByParams(id, targetPage,
				pageSize);
		List<OBPano> panos = obpService.getPanosByOBId(id);
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
		if(panos!=null && !panos.isEmpty()){
			for(int i=0;i<panos.size();i++){
				OBPano pano = panos.get(i);
				pano.setPreImageUrl(aossService.addImgParams(pano.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_BANNER));
			}
		}
		if(topImgs!=null && !topImgs.isEmpty()){
			for(int i=0;i<topImgs.size();i++){
				Map<String, String> map = topImgs.get(i);
				map.put("imgUrl",aossService.addImgParams(map.get("imgUrl"), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_BANNER));
			}
		}

		OfficeBuilding ob = obService.get(id);

		String dateDesc = "";
		if (ob != null) {
			dateDesc = DateUtil.getUpdateTimeStr(ob.getUpdateDate());
		}
		
		request.setAttribute("panos", panos);
		request.setAttribute("ob", ob);
		request.setAttribute("topImgs", topImgs);
		request.setAttribute("dateDesc", dateDesc);
		return "ui/xzl/detail";
	}

	@RequestMapping(value = "/xzl/{id}/photos", method = { RequestMethod.GET })
	public String toPhoto(HttpServletRequest request, @PathVariable String id) {
		List<OBImage> images = obiService.getImagesByParams(id, 0,
				Integer.MAX_VALUE);
		List<OBPano> panos = obpService.getPanosByOBId(id);
		List<Map<String,Object>> photos = new ArrayList<Map<String,Object>>();
		if(panos!=null && !panos.isEmpty()){
			Map<String,Object> listMap = new HashMap<String,Object>();
			listMap.put("name", Constants.PHOTO_DATA_TITLE_PANO);
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for(int i=0;i<panos.size();i++){
				OBPano pano = panos.get(i);
				Map<String,String> map = new HashMap<String,String>();
				map.put("name", pano.getName());
				map.put("contentUrl", aossService.addImgParams(pano.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_IMG));
				map.put("preImageUrl", aossService.addImgParams(pano.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				map.put("dataType", Constants.PHOTO_DATA_TYPE_PANO);
				list.add(map);
			}
			listMap.put("list", list);
			photos.add(listMap);
		}

		if(images!=null && !images.isEmpty()){
			Map<String,Object> listMap = new HashMap<String,Object>();
			listMap.put("name", Constants.PHOTO_DATA_TITLE_IMAGE);
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for(int i=0;i<images.size();i++){
				OBImage image = images.get(i);
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

	@RequestMapping(value = "/xzl/{keyWord}-{region}-{grossFloorArea}-{type}-{rentOrSale}-{rental}-{totalPrice}-{orderBy}/search", method = { RequestMethod.GET })
	public String search(HttpServletRequest request,
			@PathVariable String keyWord, @PathVariable String region,
			@PathVariable String grossFloorArea, @PathVariable String type,
			@PathVariable String rentOrSale, @PathVariable String rental,
			@PathVariable String totalPrice, @PathVariable String orderBy) {
		List<OfficeBuilding> list = obService.findOfficeBuildingsByParams(keyWord, region,
				grossFloorArea, type, rentOrSale, rental, totalPrice, orderBy,
				0, Constants.DEFAULT_PAGE_SIZE);
		int totalNum = obService.countOfficeBuildingByParams(keyWord, region,
				grossFloorArea, type, rentOrSale, rental, totalPrice);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				OfficeBuilding ob = list.get(i);
				ob.setPreImageUrl(aossService.addImgParams(ob.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
			}
		}
		request.setAttribute("results", list);
		request.setAttribute("total", totalNum);
		return "ui/xzl/index";
	}

	@RequestMapping(value = "/xzl/json/{keyWord}-{region}-{grossFloorArea}-{type}-{rentOrSale}-{rental}-{totalPrice}-{orderBy}-{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
	public @ResponseBody
	Object ajaxSearch(HttpServletRequest request, @PathVariable String keyWord,
			@PathVariable String region, @PathVariable String grossFloorArea,
			@PathVariable String type, @PathVariable String rentOrSale,
			@PathVariable String rental, @PathVariable String totalPrice,
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
			List<OfficeBuilding> list = obService.findOfficeBuildingsByParams(keyWord, region,
					grossFloorArea, type, rentOrSale, rental, totalPrice,
					orderBy, targetPage, pageSize);
			if(list!=null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					OfficeBuilding ob = list.get(i);
					ob.setPreImageUrl(aossService.addImgParams(ob.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			jo.put("results", list);
			jo.put("success", true);
		} catch (Exception e) {
			LOG.error("查询写字楼出错！",e);
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}
	
	@RequestMapping(value = "user/xzl", method = { RequestMethod.GET })
	public String toCurUserEsf(HttpServletRequest request) {
		String userId = userService.getCurUserId();
		List<OfficeBuilding> list = obService.getByUserId(userId, 0,
				Constants.DEFAULT_PAGE_SIZE);
		int total = obService.countByUserId(userId);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				OfficeBuilding ob = list.get(i);
				ob.setPreImageUrl(aossService.addImgParams(ob.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
			}
		}
		request.setAttribute("results", list);
		request.setAttribute("total", total);
		return "ui/user/xzl/index";
	}

	@RequestMapping(value = "user/xzl/add", method = { RequestMethod.GET })
	public String toAdd(HttpServletRequest request) {
		return "ui/user/xzl/add";
	}

	@RequestMapping(value = "user/xzl/json/add", method = { RequestMethod.POST })
	public @ResponseBody
	Object add(HttpServletRequest request, OfficeBuilding ob, String imgUrls) {
		JSONObject jo = new JSONObject();
		try {
			imgUrls = aossService.batchClearImgParams(imgUrls);
			String errorStr = obService.saveCascading(ob, imgUrls);
			if (StringUtils.isBlank(errorStr)) {
				jo.put("success", true);
			} else {
				jo.put("success", false);
				jo.put("msg", errorStr);
			}
		} catch (Exception e) {
			LOG.error("保存写字楼出错！",e);
			jo.put("success", false);
			jo.put("msg", "发布出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/xzl/json/del", method = { RequestMethod.POST })
	public @ResponseBody
	Object del(HttpServletRequest request, String id) {
		JSONObject jo = new JSONObject();
		try {
			String errorStr = obService.deleteUserOBByFlag(id);
			if (StringUtils.isBlank(errorStr)) {
				jo.put("success", true);
			} else {
				jo.put("success", false);
				jo.put("msg", errorStr);
			}
		} catch (Exception e) {
			LOG.error("删除写字楼出错！",e);
			jo.put("success", false);
			jo.put("msg", "删除出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/xzl/json/refresh", method = { RequestMethod.POST })
	public @ResponseBody
	Object refresh(HttpServletRequest request, String id) {
		JSONObject jo = new JSONObject();
		try {
			String errorStr = obService.refreshUserOB(id);
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
			LOG.error("更新写字楼有效期出错！",e);
			jo.put("success", false);
			jo.put("msg", "刷新出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/xzl/json/update", method = { RequestMethod.POST })
	public @ResponseBody
	Object update(HttpServletRequest request, OfficeBuilding ob, String imgUrls) {
		JSONObject jo = new JSONObject();
		try {
			imgUrls = aossService.batchClearImgParams(imgUrls);
			String errorStr = obService.updateCascading(ob, imgUrls);
			if (StringUtils.isBlank(errorStr)) {
				jo.put("success", true);
			} else {
				jo.put("success", false);
				jo.put("msg", errorStr);
			}
		} catch (Exception e) {
			LOG.error("更新写字楼出错！",e);
			jo.put("success", false);
			jo.put("msg", "修改出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/xzl/{id}/manage", method = { RequestMethod.GET })
	public String toManager(HttpServletRequest request, @PathVariable String id) {
		OfficeBuilding ob = obService.get(id);
		List<OBImage> images = obiService.getImagesByParams(id, 0,
				Integer.MAX_VALUE);
		Calendar cal = Calendar.getInstance();
		cal.setTime(ob.getUpdateDate());
		cal.add(Calendar.DAY_OF_YEAR, Constants.ACTIVE_TIME);
		request.setAttribute("deadline",
				cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
						+ "-" + cal.get(Calendar.DAY_OF_MONTH));
		if(images!=null && !images.isEmpty()){
			for(int i=0;i<images.size();i++){
				OBImage obi = images.get(i);
				obi.setContentUrl(aossService.addImgParams(obi.getContentUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_HEAD_IMG));
			}
		}
		request.setAttribute("ob", ob);
		request.setAttribute("images", images);
		return "ui/user/xzl/manage";
	}

	@RequestMapping(value = "user/xzl/json/{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
	public @ResponseBody
	Object ajaxCurUserOBSearch(HttpServletRequest request,
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
			List<OfficeBuilding> list = obService.getByUserId(userId, targetPage, pageSize);
			if(list!=null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					OfficeBuilding ob = list.get(i);
					ob.setPreImageUrl(aossService.addImgParams(ob.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			jo.put("results",list);
			jo.put("success", true);
		} catch (Exception e) {
			LOG.error("查询用户写字楼出错！",e);
			e.printStackTrace();
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/user/xzl/uploadImg", method = {
			RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody
	Object upload(HttpServletRequest request,
			@RequestParam("theFile") MultipartFile theFile) {
		JSONObject jo = new JSONObject();
		try {
			String path = aossService.saveFileToServer(theFile);
			path = aossService.addImgParams(path,Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_HEAD_IMG);
			jo.put("imgPath", path);
			jo.put("success", true);
		} catch (IOException e) {
			LOG.error("保存写字楼图片出错！",e);
			jo.put("success", false);
			jo.put("msg", "保存图片失败");
		}
		return jo.toString();
	}
    
}
