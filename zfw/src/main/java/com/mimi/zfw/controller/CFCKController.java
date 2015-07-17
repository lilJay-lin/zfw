package com.mimi.zfw.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.mimi.zfw.mybatis.pojo.Warehouse;
import com.mimi.zfw.mybatis.pojo.WarehouseImage;
import com.mimi.zfw.mybatis.pojo.WarehousePano;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.service.IWarehouseImageService;
import com.mimi.zfw.service.IWarehousePanoService;
import com.mimi.zfw.service.IWarehouseService;
import com.mimi.zfw.util.DateUtil;

@Controller
public class CFCKController {
    private static final Logger LOG = LoggerFactory
	    .getLogger(CFCKController.class);
    @Resource
    private IWarehouseService wService;
    @Resource
    private IWarehouseImageService wiService;
    @Resource
    private IWarehousePanoService wpService;
    @Resource
    private IUserService userService;
    @Resource
    private IAliyunOSSService aossService;

    @RequestMapping(value = "/cfck", method = { RequestMethod.GET })
    public String cfck(HttpServletRequest request) {
	List<Warehouse> list = wService.findWarehousesByParams(null, null,
		null, null, Constants.ROS_RENT_ONLY, null, null, null, 0,
		Constants.DEFAULT_PAGE_SIZE);
	int totalNum = wService.countWarehouseByParams(null, null, null, null,
		Constants.ROS_RENT_ONLY, null, null);
	if (list != null && !list.isEmpty()) {
	    for (int i = 0; i < list.size(); i++) {
		Warehouse warehouse = list.get(i);
		warehouse.setPreImageUrl(aossService.addImgParams(
			warehouse.getPreImageUrl(),
			Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
	    }
	}
	request.setAttribute("results", list);
	request.setAttribute("total", totalNum);
	return "ui/cfck/index";
    }

    @RequestMapping(value = "/cfck/{id}/detail", method = { RequestMethod.GET })
    public String toDetail(HttpServletRequest request, @PathVariable String id) {
	int targetPage = 0;
	int pageSize = 2;
	List<WarehouseImage> images = wiService.getImagesByParams(id,
		targetPage, pageSize);
	List<WarehousePano> panos = wpService.getPanosByWarehouseId(id);
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
	if (panos != null && !panos.isEmpty()) {
	    for (int i = 0; i < panos.size(); i++) {
		WarehousePano pano = panos.get(i);
		pano.setPreImageUrl(aossService.addImgParams(
			pano.getPreImageUrl(),
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

	Warehouse warehouse = wService.get(id);

	String dateDesc = "";
	if (warehouse != null) {
	    dateDesc = DateUtil.getUpdateTimeStr(warehouse.getUpdateDate());
	}

	request.setAttribute("panos", panos);
	request.setAttribute("warehouse", warehouse);
	request.setAttribute("topImgs", topImgs);
	request.setAttribute("dateDesc", dateDesc);
	return "ui/cfck/detail";
    }

    @RequestMapping(value = "/cfck/{id}/photos", method = { RequestMethod.GET })
    public String toPhoto(HttpServletRequest request, @PathVariable String id) {
	List<WarehouseImage> images = wiService.getImagesByParams(id, 0,
		Integer.MAX_VALUE);
	List<WarehousePano> panos = wpService.getPanosByWarehouseId(id);
	List<Map<String, Object>> photos = new ArrayList<Map<String, Object>>();
	if (panos != null && !panos.isEmpty()) {
	    Map<String, Object> listMap = new HashMap<String, Object>();
	    listMap.put("name", Constants.PHOTO_DATA_TITLE_PANO);
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    for (int i = 0; i < panos.size(); i++) {
		WarehousePano pano = panos.get(i);
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

	if (images != null && !images.isEmpty()) {
	    Map<String, Object> listMap = new HashMap<String, Object>();
	    listMap.put("name", Constants.PHOTO_DATA_TITLE_IMAGE);
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    for (int i = 0; i < images.size(); i++) {
		WarehouseImage image = images.get(i);
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", image.getName());
		map.put("contentUrl", aossService.addImgParams(
			image.getContentUrl(),
			Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_IMG));
		map.put("preImageUrl", aossService.addImgParams(
			image.getContentUrl(),
			Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
		map.put("dataType", Constants.PHOTO_DATA_TYPE_IMAGE);
		list.add(map);
	    }
	    listMap.put("list", list);
	    photos.add(listMap);
	}
	request.setAttribute("photos", photos);
	return "ui/photo/photoList";
    }

    @RequestMapping(value = "/cfck/{keyWord}-{region}-{grossFloorArea}-{type}-{rentOrSale}-{rental}-{totalPrice}-{orderBy}/search", method = { RequestMethod.GET })
    public String search(HttpServletRequest request,
	    @PathVariable String keyWord, @PathVariable String region,
	    @PathVariable String grossFloorArea, @PathVariable String type,
	    @PathVariable String rentOrSale, @PathVariable String rental,
	    @PathVariable String totalPrice, @PathVariable String orderBy) {
	List<Warehouse> list = wService.findWarehousesByParams(keyWord, region,
		grossFloorArea, type, rentOrSale, rental, totalPrice, orderBy,
		0, Constants.DEFAULT_PAGE_SIZE);
	int totalNum = wService.countWarehouseByParams(keyWord, region,
		grossFloorArea, type, rentOrSale, rental, totalPrice);
	if (list != null && !list.isEmpty()) {
	    for (int i = 0; i < list.size(); i++) {
		Warehouse warehouse = list.get(i);
		warehouse.setPreImageUrl(aossService.addImgParams(
			warehouse.getPreImageUrl(),
			Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
	    }
	}
	request.setAttribute("results", list);
	request.setAttribute("total", totalNum);
	return "ui/cfck/index";
    }

    @RequestMapping(value = "/cfck/json/{keyWord}-{region}-{grossFloorArea}-{type}-{rentOrSale}-{rental}-{totalPrice}-{orderBy}-{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
    public @ResponseBody Object ajaxSearch(HttpServletRequest request,
	    @PathVariable String keyWord, @PathVariable String region,
	    @PathVariable String grossFloorArea, @PathVariable String type,
	    @PathVariable String rentOrSale, @PathVariable String rental,
	    @PathVariable String totalPrice, @PathVariable String orderBy,
	    @PathVariable Integer targetPage, @PathVariable Integer pageSize) {
	if (targetPage == null) {
	    targetPage = 0;
	}
	if (pageSize == null) {
	    pageSize = Constants.DEFAULT_PAGE_SIZE;
	}
	JSONObject jo = new JSONObject();
	try {
	    List<Warehouse> list = wService.findWarehousesByParams(keyWord,
		    region, grossFloorArea, type, rentOrSale, rental,
		    totalPrice, orderBy, targetPage, pageSize);
	    if (list != null && !list.isEmpty()) {
		for (int i = 0; i < list.size(); i++) {
		    Warehouse warehouse = list.get(i);
		    warehouse
			    .setPreImageUrl(aossService.addImgParams(
				    warehouse.getPreImageUrl(),
				    Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
		}
	    }
	    jo.put("results", list);
	    jo.put("success", true);
	} catch (Exception e) {
	    LOG.error("查询厂房仓库出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "查询出错!");
	}
	return jo.toString();
    }

    @RequestMapping(value = "user/cfck", method = { RequestMethod.GET })
    public String toCurUserEsf(HttpServletRequest request) {
	String userId = userService.getCurUserId();
	List<Warehouse> list = wService.getByUserId(userId, 0,
		Constants.DEFAULT_PAGE_SIZE);
	int total = wService.countByUserId(userId);
	if (list != null && !list.isEmpty()) {
	    for (int i = 0; i < list.size(); i++) {
		Warehouse warehouse = list.get(i);
		warehouse.setPreImageUrl(aossService.addImgParams(
			warehouse.getPreImageUrl(),
			Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
	    }
	}
	request.setAttribute("results", list);
	request.setAttribute("total", total);
	return "ui/user/cfck/index";
    }

    @RequestMapping(value = "user/cfck/add", method = { RequestMethod.GET })
    public String toAdd(HttpServletRequest request) {
	return "ui/user/cfck/add";
    }

    @RequestMapping(value = "user/cfck/json/add", method = { RequestMethod.POST })
    public @ResponseBody Object add(HttpServletRequest request,
	    Warehouse warehouse, String imgUrls) {
	JSONObject jo = new JSONObject();
	try {
	    imgUrls = aossService.batchClearImgParams(imgUrls);
	    String errorStr = wService.saveCascading(warehouse, imgUrls);
	    if (StringUtils.isBlank(errorStr)) {
		jo.put("success", true);
	    } else {
		jo.put("success", false);
		jo.put("msg", errorStr);
	    }
	} catch (Exception e) {
	    LOG.error("保存厂房仓库出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "发布出错!");
	}
	return jo.toString();
    }

    @RequestMapping(value = "user/cfck/json/del", method = { RequestMethod.POST })
    public @ResponseBody Object del(HttpServletRequest request, String id) {
	JSONObject jo = new JSONObject();
	try {
	    String errorStr = wService.deleteUserWarehouseByFlag(id);
	    if (StringUtils.isBlank(errorStr)) {
		jo.put("success", true);
	    } else {
		jo.put("success", false);
		jo.put("msg", errorStr);
	    }
	} catch (Exception e) {
	    LOG.error("删除厂房仓库出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "删除出错!");
	}
	return jo.toString();
    }

    @RequestMapping(value = "user/cfck/json/refresh", method = { RequestMethod.POST })
    public @ResponseBody Object refresh(HttpServletRequest request, String id) {
	JSONObject jo = new JSONObject();
	try {
	    String errorStr = wService.refreshUserWarehouse(id);
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
	    LOG.error("更新厂房仓库有效期出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "刷新出错!");
	}
	return jo.toString();
    }

    @RequestMapping(value = "user/cfck/json/update", method = { RequestMethod.POST })
    public @ResponseBody Object update(HttpServletRequest request,
	    Warehouse warehouse, String imgUrls) {
	JSONObject jo = new JSONObject();
	try {
	    imgUrls = aossService.batchClearImgParams(imgUrls);
	    String errorStr = wService.updateCascading(warehouse, imgUrls);
	    if (StringUtils.isBlank(errorStr)) {
		jo.put("success", true);
	    } else {
		jo.put("success", false);
		jo.put("msg", errorStr);
	    }
	} catch (Exception e) {
	    LOG.error("更新厂房仓库出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "修改出错!");
	}
	return jo.toString();
    }

    @RequestMapping(value = "user/cfck/{id}/manage", method = { RequestMethod.GET })
    public String toManager(HttpServletRequest request, @PathVariable String id) {
	Warehouse warehouse = wService.get(id);
	List<WarehouseImage> images = wiService.getImagesByParams(id, 0,
		Integer.MAX_VALUE);
	Calendar cal = Calendar.getInstance();
	cal.setTime(warehouse.getUpdateDate());
	cal.add(Calendar.DAY_OF_YEAR, Constants.ACTIVE_TIME);
	request.setAttribute("deadline",
		cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
			+ "-" + cal.get(Calendar.DAY_OF_MONTH));
	if (images != null && !images.isEmpty()) {
	    for (int i = 0; i < images.size(); i++) {
		WarehouseImage obi = images.get(i);
		obi.setContentUrl(aossService.addImgParams(obi.getContentUrl(),
			Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_HEAD_IMG));
	    }
	}
	request.setAttribute("warehouse", warehouse);
	request.setAttribute("images", images);
	return "ui/user/cfck/manage";
    }

    @RequestMapping(value = "user/cfck/json/{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
    public @ResponseBody Object ajaxCurUserWarehouseSearch(
	    HttpServletRequest request, @PathVariable Integer targetPage,
	    @PathVariable Integer pageSize) {
	if (targetPage == null) {
	    targetPage = 0;
	}
	if (pageSize == null) {
	    pageSize = Constants.DEFAULT_PAGE_SIZE;
	}
	JSONObject jo = new JSONObject();
	try {
	    String userId = userService.getCurUserId();
	    List<Warehouse> list = wService.getByUserId(userId, targetPage,
		    pageSize);
	    if (list != null && !list.isEmpty()) {
		for (int i = 0; i < list.size(); i++) {
		    Warehouse warehouse = list.get(i);
		    warehouse
			    .setPreImageUrl(aossService.addImgParams(
				    warehouse.getPreImageUrl(),
				    Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
		}
	    }
	    jo.put("results", list);
	    jo.put("success", true);
	} catch (Exception e) {
	    LOG.error("获取用户厂房仓库出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "查询出错!");
	}
	return jo.toString();
    }

    @RequestMapping(value = "/user/cfck/uploadImg", method = {
	    RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody Object upload(HttpServletRequest request,
	    @RequestParam("theFile") MultipartFile theFile) {
	JSONObject jo = new JSONObject();
	try {
	    String path = aossService.saveFileToServer(theFile);
	    path = aossService.addImgParams(path,
		    Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_HEAD_IMG);
	    jo.put("imgPath", path);
	    jo.put("success", true);
	} catch (IOException e) {
	    LOG.error("上传厂房仓库图片出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "保存图片失败");
	}
	return jo.toString();
    }

    @RequestMapping(value = "/mi/cfck/uploadImg", method = { RequestMethod.POST })
    public @ResponseBody Object miUpload(HttpServletRequest request,
	    @RequestParam("theFile") MultipartFile theFile) {
	JSONObject jo = new JSONObject();
	try {
	    String path = aossService.saveFileToServer(theFile);
	    path = aossService.addImgParams(path,
		    Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_HEAD_IMG);
	    jo.put("imgPath", path);
	    jo.put("success", true);
	} catch (IOException e) {
	    LOG.error("上传厂房/仓库图片出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "保存图片失败");
	}
	return jo.toString();
    }

    @RequiresPermissions("warehouse:query")
    @RequestMapping(value = "/mi/cfck", method = { RequestMethod.GET })
    public String index(HttpServletRequest request) {

	return "/mi/cfck/index";
    }

    @RequiresPermissions("warehouse:query")
    @RequestMapping(value = "/mi/cfck/page/{curPage}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getWarehouseByPage(HttpServletRequest request,
	    @PathVariable int curPage) {

	Object res = null;

	int page = curPage - 1 > 0 ? curPage - 1 : 0;

	String name = request.getParameter("name") == null ? null
		: (String) request.getParameter("name");

	String type = request.getParameter("type") == null ? null
		: (String) request.getParameter("type");

	try {
	    if (StringUtils.isNotBlank(name)) {
	    	name = URLDecoder.decode(name, "utf-8");
	    }

	    if (StringUtils.isNotBlank(type)) {
	    	type = URLDecoder.decode(type, "utf-8");
	    }
	} catch (UnsupportedEncodingException e) {

	    JSONObject jo = new JSONObject();
	    jo.put("success", false);
	    jo.put("msg", "查询条件解码出错");
	    LOG.error("查询厂房/仓库分页，查询条件解码出错！", e);

	    return jo.toString();
	}
	Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
		: Integer.valueOf((String) request.getParameter("pagesize"));

	int rows = 0;
	try {
	    // 有userid则查询关联的role，无则查询所有role
	    List<Warehouse> items = wService.findWarehousesByParams(name, null,
		    null, type, null, null, null, "onUpdateFromNear", page, pageSize);
	    rows = wService.countWarehouseByParams(name, null, null, type,
		    null, null, null);
	    int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
		    / pageSize + 1);
	    res = getJsonObject(rows, totalpage, curPage, pageSize, items,
		    true, "");
	} catch (Exception e) {

	    res = getJsonObject(rows, 0, curPage, pageSize, null, false, "");
	    LOG.error("查询厂房/仓库分页信息报错！", e);
	}
	return res;
    }

    public Object getJsonObject(int rows, int totalpage, int curPage,
	    int pageSize, List<Warehouse> items, boolean rescode, String msg) {
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

    @RequiresPermissions("warehouse:view")
    @RequestMapping(value = "/mi/cfck/{id}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getWarehouse(@PathVariable String id,
	    HttpServletRequest request) {

	JSONObject jo = new JSONObject();

	try {
	    Warehouse warehouse = (Warehouse) wService.get(id);
	    if (warehouse != null) {
		jo.put("warehouse", warehouse);
	    }
	} catch (Exception e) {

	    jo.put("warehouse", null);
	    LOG.error("查询厂房/仓库信息出错！", e);
	}

	return jo.toString();
    }

    @RequiresPermissions("warehouse:add")
    @RequestMapping(value = "/mi/cfck/add", method = { RequestMethod.GET })
    public String toAddWarehouse(HttpServletRequest request) {

	// return new ModelAndView("mi/users/add","user",new User());

	return "mi/cfck/add";
    }

    @RequiresPermissions("warehouse:add")
    @RequestMapping(value = "/mi/cfck", method = { RequestMethod.POST })
    @ResponseBody
    public Object addWarehouse(HttpServletRequest request, Warehouse warehouse) {

	JSONObject jo = new JSONObject();

	if (warehouse == null) {
	    jo.put("success", false);
	    jo.put("msg", "新增厂房/仓库信息不能为空!");
	} else {

	    try {

		Map<String, String> res = wService.addWarehouse(warehouse);

		if (StringUtils.isEmpty(res.get("msg"))) {
		    jo.put("success", true);
		    jo.put("msg", "新增厂房/仓库保存成功!");

		} else {
		    jo.put("success", false);
		    jo.put("msg", res.get("msg"));
		    jo.put("field", res.get("field"));
		}

	    } catch (Exception e) {
		jo.put("success", false);
		jo.put("msg", "新增厂房/仓库保存失败!");
		LOG.error("新增厂房/仓库保存失败！", e);
	    }

	}

	return jo.toString();
    }

    @RequiresPermissions("warehouse:update")
    @RequestMapping(value = "/mi/cfck/{id}/edit", method = { RequestMethod.GET })
    public String toUpdateWarehouse(HttpServletRequest request, Model model,
	    @PathVariable String id) {
	model.addAttribute("warehouseId", id);
	return "/mi/cfck/edit";
    }

    @RequiresPermissions("warehouse:view")
    @RequestMapping(value = "/mi/cfck/{id}/detail", method = { RequestMethod.GET })
    public String toViewWarehouse(Model model, @PathVariable String id) {

	model.addAttribute("warehouseId", id);

	return "/mi/cfck/detail";
    }

    @RequiresPermissions("warehouse:update")
    @RequestMapping(value = "/mi/cfck/{id}", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateWarehouse(HttpServletRequest request,
	    Warehouse warehouse) {

	JSONObject jo = new JSONObject();
	try {

	    Map<String, String> res = wService.updateWarehouse(warehouse);

	    if (StringUtils.isEmpty(res.get("msg"))) {
		jo.put("success", true);
		jo.put("msg", "更新厂房/仓库成功!");

	    } else {
		jo.put("success", false);
		jo.put("msg", res.get("msg"));
		jo.put("field", res.get("field"));
	    }

	} catch (Exception e) {
	    jo.put("success", false);
	    jo.put("msg", "更新厂房/仓库失败!");
	    LOG.error("更新厂房/仓库信息失败！", e);
	}

	return jo.toString();
    }

    @RequiresPermissions("warehouse:del")
    @RequestMapping(value = "/mi/cfcks", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateBatchWarehouse(HttpServletRequest request,
	    Warehouse warehouse, String warehouseids) {
	JSONObject jo = new JSONObject();

	try {
	    wService.updateBatchWarehouse(warehouseids, warehouse);

	    jo.put("success", true);
	    jo.put("msg", "厂房/仓库删除成功");
	} catch (Exception e) {

	    jo.put("success", false);
	    jo.put("msg", "厂房/仓库删除失败");
	    LOG.error("删除厂房/仓库信息失败！", e);
	}

	return jo.toString();
    }

}
