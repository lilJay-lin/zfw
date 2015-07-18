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
import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.mybatis.pojo.SHHImage;
import com.mimi.zfw.mybatis.pojo.SHHPano;
import com.mimi.zfw.mybatis.pojo.SecondHandHouse;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IResidenceCommunityService;
import com.mimi.zfw.service.ISHHImageService;
import com.mimi.zfw.service.ISHHPanoService;
import com.mimi.zfw.service.ISecondHandHouseService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.DateUtil;

@Controller
public class ESFController {
	private static final Logger LOG = LoggerFactory.getLogger(ESFController.class);  
	@Resource
	private IResidenceCommunityService rcService;
	@Resource
	private ISecondHandHouseService shhService;
	@Resource
	private ISHHImageService shhiService;
	@Resource
	private ISHHPanoService shhpService;
	@Resource
	private IUserService userService;
	@Resource
	private IAliyunOSSService aossService;

	@RequestMapping(value = "/esf", method = { RequestMethod.GET })
	public String esf(HttpServletRequest request) {
		boolean outOfDate = false;
		List<SecondHandHouse> list = shhService.findSecondHandHousesByParams(
				null, null, null, null, null, null, outOfDate,null, 0,
				Constants.DEFAULT_PAGE_SIZE);
		int totalNum = shhService.countSecondHandHouseByParams(null, null,
				null, null, null, null,outOfDate);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				SecondHandHouse shh = list.get(i);
				shh.setPreImageUrl(aossService.addImgParams(shh.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
			}
		}
		request.setAttribute("results", list);
		request.setAttribute("total", totalNum);
		return "ui/esf/index";
	}

	@RequestMapping(value = "/esf/{id}/detail", method = { RequestMethod.GET })
	public String toDetail(HttpServletRequest request, @PathVariable String id) {
		int targetPage = 0;
		int pageSize = 2;
		List<SHHImage> images = shhiService.getImagesByParams(id, targetPage,
				pageSize);
		List<SHHPano> panos = shhpService.getPanosBySHHId(id);
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
				SHHPano pano = panos.get(i);
				pano.setPreImageUrl(aossService.addImgParams(pano.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_BANNER));
			}
		}
		if(topImgs!=null && !topImgs.isEmpty()){
			for(int i=0;i<topImgs.size();i++){
				Map<String, String> map = topImgs.get(i);
				map.put("imgUrl",aossService.addImgParams(map.get("imgUrl"), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_BANNER));
			}
		}

		SecondHandHouse shh = shhService.get(id);
		ResidenceCommunity rc = rcService.get(shh.getResidenceCommunityId());

		String dateDesc = "";
		if (shh != null) {
			dateDesc = DateUtil.getUpdateTimeStr(shh.getUpdateDate());
		}
		
		request.setAttribute("panos", panos);
		request.setAttribute("esf", shh);
		request.setAttribute("rc", rc);
		request.setAttribute("topImgs", topImgs);
		request.setAttribute("dateDesc", dateDesc);
		return "ui/esf/detail";
	}

	@RequestMapping(value = "/esf/{id}/photos", method = { RequestMethod.GET })
	public String toPhoto(HttpServletRequest request, @PathVariable String id) {
		List<SHHImage> images = shhiService.getImagesByParams(id, 0,
				Integer.MAX_VALUE);
		List<SHHPano> panos = shhpService.getPanosBySHHId(id);

		List<Map<String,Object>> photos = new ArrayList<Map<String,Object>>();
		if(panos!=null && !panos.isEmpty()){
			Map<String,Object> listMap = new HashMap<String,Object>();
			listMap.put("name", Constants.PHOTO_DATA_TITLE_PANO);
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for(int i=0;i<panos.size();i++){
				SHHPano pano = panos.get(i);
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
				SHHImage image = images.get(i);
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

	@RequestMapping(value = "/esf/{residenceCommunityId}-{keyWord}-{region}-{totalPrice}-{roomNum}-{grossFloorArea}-{orderBy}/search", method = { RequestMethod.GET })
	public String search(HttpServletRequest request,
			@PathVariable String residenceCommunityId,
			@PathVariable String keyWord, @PathVariable String region,
			@PathVariable String totalPrice, @PathVariable Integer roomNum,
			@PathVariable String grossFloorArea, @PathVariable String orderBy) {
		boolean outOfDate = false;
		List<SecondHandHouse> list = shhService.findSecondHandHousesByParams(
				residenceCommunityId, keyWord, region, totalPrice, roomNum,
				grossFloorArea, outOfDate,orderBy, 0, Constants.DEFAULT_PAGE_SIZE);
		int totalNum = shhService.countSecondHandHouseByParams(
				residenceCommunityId, keyWord, region, totalPrice, roomNum,
				grossFloorArea,outOfDate);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				SecondHandHouse shh = list.get(i);
				shh.setPreImageUrl(aossService.addImgParams(shh.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
			}
		}
		request.setAttribute("results", list);
		request.setAttribute("total", totalNum);
		return "ui/esf/index";
	}

	@RequestMapping(value = "/esf/json/{residenceCommunityId}-{keyWord}-{region}-{totalPrice}-{roomNum}-{grossFloorArea}-{orderBy}-{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
	public @ResponseBody
	Object ajaxSearch(HttpServletRequest request, @PathVariable String keyWord,
			@PathVariable String residenceCommunityId,
			@PathVariable String region, @PathVariable String totalPrice,
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
			boolean outOfDate = false;
			List<SecondHandHouse> list = shhService.findSecondHandHousesByParams(
					residenceCommunityId, keyWord, region, totalPrice, roomNum,
					grossFloorArea,outOfDate, orderBy, targetPage, pageSize);
			if(list!=null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					SecondHandHouse shh = list.get(i);
					shh.setPreImageUrl(aossService.addImgParams(shh.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			jo.put("results", list);
			jo.put("success", true);
		} catch (Exception e) {
			LOG.error("查询二手房出错！",e);
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/esf", method = { RequestMethod.GET })
	public String toCurUserEsf(HttpServletRequest request) {
		String userId = userService.getCurUserId();
		List<SecondHandHouse> list = shhService.getByUserId(userId, 0,
				Constants.DEFAULT_PAGE_SIZE);
		int total = shhService.countByUserId(userId);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				SecondHandHouse shh = list.get(i);
				shh.setPreImageUrl(aossService.addImgParams(shh.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
			}
		}
		request.setAttribute("results", list);
		request.setAttribute("total", total);
		return "ui/user/esf/index";
	}

	@RequestMapping(value = "user/esf/add", method = { RequestMethod.GET })
	public String toAdd(HttpServletRequest request) {
		return "ui/user/esf/add";
	}

	@RequestMapping(value = "user/esf/json/add", method = { RequestMethod.POST })
	public @ResponseBody
	Object add(HttpServletRequest request, SecondHandHouse shh, String imgUrls) {
		JSONObject jo = new JSONObject();
		try {
			imgUrls = aossService.batchClearImgParams(imgUrls);
			String errorStr = shhService.saveCascading(shh, imgUrls);
			if (StringUtils.isBlank(errorStr)) {
				jo.put("success", true);
			} else {
				jo.put("success", false);
				jo.put("msg", errorStr);
			}
		} catch (Exception e) {
			LOG.error("保存二手房出错！",e);
			jo.put("success", false);
			jo.put("msg", "发布出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/esf/json/del", method = { RequestMethod.POST })
	public @ResponseBody
	Object del(HttpServletRequest request, String id) {
		JSONObject jo = new JSONObject();
		try {
			String errorStr = shhService.deleteUserSHHByFlag(id);
			if (StringUtils.isBlank(errorStr)) {
				jo.put("success", true);
			} else {
				jo.put("success", false);
				jo.put("msg", errorStr);
			}
		} catch (Exception e) {
			LOG.error("删除二手房出错！",e);
			jo.put("success", false);
			jo.put("msg", "删除出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/esf/json/refresh", method = { RequestMethod.POST })
	public @ResponseBody
	Object refresh(HttpServletRequest request, String id) {
		JSONObject jo = new JSONObject();
		try {
			String errorStr = shhService.refreshUserSHH(id);
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
			LOG.error("更新二手房有效期出错！",e);
			jo.put("success", false);
			jo.put("msg", "刷新出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/esf/json/update", method = { RequestMethod.POST })
	public @ResponseBody
	Object update(HttpServletRequest request, SecondHandHouse shh,
			String imgUrls) {
		JSONObject jo = new JSONObject();
		try {
			imgUrls = aossService.batchClearImgParams(imgUrls);
			String errorStr = shhService.updateCascading(shh, imgUrls);
			if (StringUtils.isBlank(errorStr)) {
				jo.put("success", true);
			} else {
				jo.put("success", false);
				jo.put("msg", errorStr);
			}
		} catch (Exception e) {
			LOG.error("更新二手房出错！",e);
			jo.put("success", false);
			jo.put("msg", "修改出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "user/esf/{id}/manage", method = { RequestMethod.GET })
	public String toManager(HttpServletRequest request, @PathVariable String id) {
		SecondHandHouse shh = shhService.get(id);
		List<SHHImage> images = shhiService.getImagesByParams(id, 0,
				Integer.MAX_VALUE);
		String deadline = "已过期";
		if(shh.getOutOfDate()!=null && !shh.getOutOfDate()){
			Calendar cal = Calendar.getInstance();
			cal.setTime(shh.getUpdateDate());
			cal.add(Calendar.DAY_OF_YEAR, Constants.ACTIVE_TIME);
			deadline = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
					+ "-" + cal.get(Calendar.DAY_OF_MONTH);
		}
		request.setAttribute("deadline",deadline);

		if(images!=null && !images.isEmpty()){
			for(int i=0;i<images.size();i++){
				SHHImage si = images.get(i);
				si.setContentUrl(aossService.addImgParams(si.getContentUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_HEAD_IMG));
			}
		}
		request.setAttribute("esf", shh);
		request.setAttribute("images", images);
		return "ui/user/esf/manage";
	}

	@RequestMapping(value = "user/esf/json/{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
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
			List<SecondHandHouse> list = shhService.getByUserId(userId, targetPage, pageSize);
			if(list!=null && !list.isEmpty()){
				for(int i=0;i<list.size();i++){
					SecondHandHouse shh = list.get(i);
					shh.setPreImageUrl(aossService.addImgParams(shh.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			jo.put("results",list);
			jo.put("success", true);
		} catch (Exception e) {
			LOG.error("查询用户二手房出错！",e);
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/user/esf/uploadImg", method = {
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
			LOG.error("上传二手房图片出错！",e);
			jo.put("success", false);
			jo.put("msg", "保存图片失败");
		}
		return jo.toString();
	}


    @RequiresPermissions("rc:view")
	@RequestMapping(value = "/mi/{rcId}/esf/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getSHHByPage(HttpServletRequest request,
			@PathVariable int curPage ,@PathVariable String rcId) {

		Object res = null;

		int page = curPage - 1 > 0 ? curPage - 1 : 0;

		String name = request.getParameter("name") == null ? null
				: (String) request.getParameter("name");

		Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
				: Integer.valueOf((String) request.getParameter("pagesize"));

		try {
			List<SecondHandHouse> items = shhService.findByParams(name,rcId, page, pageSize);
			int rows = shhService.countByParams(name, rcId);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("二手房查询失败",e);
			res = getJsonObject(0, 0, curPage, pageSize, null, false, "二手房查询失败");
		}
		return res;
	}
    @RequiresPermissions("rc:view")
	@RequestMapping(value = "/mi/{rcId}/esf/{shhId}/detail", method = { RequestMethod.GET })
	public String toSHHDetail(HttpServletRequest request,
			@PathVariable String rcId,@PathVariable String shhId) {
		return "/mi/esf/detail";
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/{rcId}/esf/add", method = { RequestMethod.GET })
	public String toAddSHH(Model model, HttpServletRequest request,@PathVariable String rcId) {
		ResidenceCommunity rc = rcService.get(rcId);
		request.setAttribute("rcName", rc.getName());
		return "mi/esf/add";
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/esf/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addSHH(HttpServletRequest request, SecondHandHouse shh) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = shhService.addSHH(shh);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "二手房保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			LOG.error("二手房保存失败",e);
			jo.put("success", false);
			jo.put("msg", "二手房保存失败!");
		}
		return jo.toString();
	}


    @RequiresPermissions("rc:view")
	@RequestMapping(value = "/mi/esf/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getSHH(@PathVariable String id, HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		try {
			SecondHandHouse shh = shhService.get(id);
			if (shh != null) {
				shh.setPreImageUrl(aossService.addImgParams(
						shh.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				jo.put("shh", shh);
			}
		} catch (Exception e) {
			LOG.error("获取二手房失败", e);
			jo.put("shh", null);
		}
		return jo.toString();
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/{rcId}/esf/{shhId}/edit", method = { RequestMethod.GET })
	public String toUpdateSHH(HttpServletRequest request,
			@PathVariable String rcId,@PathVariable String shhId) {
		return "/mi/esf/edit";
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/esf/{shhId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateSHH(HttpServletRequest request, SecondHandHouse shh,
			@PathVariable String shhId) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = shhService.updateSHH(shh);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新二手房成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新二手房失败", e);
			jo.put("success", false);
			jo.put("msg", "更新二手房失败!");
		}
		return jo.toString();
	}

    @RequiresPermissions("rc:update")
	@RequestMapping(value = "/mi/esf/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelSHHPano(HttpServletRequest request, String rcId, String shhIds) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = shhService.batchDel(rcId,shhIds);
			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "二手房删除成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "二手房删除失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/esf/uploadImg", method = { RequestMethod.POST })
	public @ResponseBody
	Object miUpload(HttpServletRequest request,
			@RequestParam("theFile") MultipartFile theFile) {
		JSONObject jo = new JSONObject();
		try {
			String path = aossService.saveFileToServer(theFile);
			path = aossService.addImgParams(path,
					Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG);
			jo.put("imgPath", path);
			jo.put("success", true);
		} catch (IOException e) {
			LOG.error("上传二手房缩略图出错！", e);
			jo.put("success", false);
			jo.put("msg", "保存图片失败");
		}
		return jo.toString();
	}

	public Object getJsonObject(int rows, int totalpage, int curPage,
			int pageSize, List<SecondHandHouse> items, boolean rescode,
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
