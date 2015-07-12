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
import com.mimi.zfw.mybatis.pojo.Shop;
import com.mimi.zfw.mybatis.pojo.ShopImage;
import com.mimi.zfw.mybatis.pojo.ShopPano;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IShopImageService;
import com.mimi.zfw.service.IShopPanoService;
import com.mimi.zfw.service.IShopService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.DateUtil;

@Controller
public class SPController {
    private static final Logger LOG = LoggerFactory
	    .getLogger(SPController.class);
    @Resource
    private IShopService shopService;
    @Resource
    private IShopImageService siService;
    @Resource
    private IShopPanoService spService;
    @Resource
    private IUserService userService;
    @Resource
    private IAliyunOSSService aossService;

    @RequestMapping(value = "/sp", method = { RequestMethod.GET })
    public String sp(HttpServletRequest request) {
	List<Shop> list = shopService.findShopsByParams(null, null, null, null,
		Constants.ROS_RENT_ONLY, null, null, null, 0,
		Constants.DEFAULT_PAGE_SIZE);
	int totalNum = shopService.countShopByParams(null, null, null, null,
		Constants.ROS_RENT_ONLY, null, null);
	if (list != null && !list.isEmpty()) {
	    for (int i = 0; i < list.size(); i++) {
		Shop shop = list.get(i);
		shop.setPreImageUrl(aossService.addImgParams(
			shop.getPreImageUrl(),
			Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
	    }
	}
	request.setAttribute("results", list);
	request.setAttribute("total", totalNum);
	return "ui/sp/index";
    }

    @RequestMapping(value = "/sp/{id}/detail", method = { RequestMethod.GET })
    public String toDetail(HttpServletRequest request, @PathVariable String id) {
	int targetPage = 0;
	int pageSize = 2;
	List<ShopImage> images = siService.getImagesByParams(id, targetPage,
		pageSize);
	List<ShopPano> panos = spService.getPanosByShopId(id);
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
		ShopPano pano = panos.get(i);
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

	Shop shop = shopService.get(id);

	String dateDesc = "";
	if (shop != null) {
	    dateDesc = DateUtil.getUpdateTimeStr(shop.getUpdateDate());
	}

	request.setAttribute("panos", panos);
	request.setAttribute("sp", shop);
	request.setAttribute("topImgs", topImgs);
	request.setAttribute("dateDesc", dateDesc);
	return "ui/sp/detail";
    }

    @RequestMapping(value = "/sp/{id}/photos", method = { RequestMethod.GET })
    public String toPhoto(HttpServletRequest request, @PathVariable String id) {
	List<ShopImage> images = siService.getImagesByParams(id, 0,
		Integer.MAX_VALUE);
	List<ShopPano> panos = spService.getPanosByShopId(id);
	List<Map<String, Object>> photos = new ArrayList<Map<String, Object>>();
	if (panos != null && !panos.isEmpty()) {
	    Map<String, Object> listMap = new HashMap<String, Object>();
	    listMap.put("name", Constants.PHOTO_DATA_TITLE_PANO);
	    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	    for (int i = 0; i < panos.size(); i++) {
		ShopPano pano = panos.get(i);
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
		ShopImage image = images.get(i);
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

    @RequestMapping(value = "/sp/{keyWord}-{region}-{grossFloorArea}-{type}-{rentOrSale}-{rental}-{totalPrice}-{orderBy}/search", method = { RequestMethod.GET })
    public String search(HttpServletRequest request,
	    @PathVariable String keyWord, @PathVariable String region,
	    @PathVariable String grossFloorArea, @PathVariable String type,
	    @PathVariable String rentOrSale, @PathVariable String rental,
	    @PathVariable String totalPrice, @PathVariable String orderBy) {
	List<Shop> list = shopService.findShopsByParams(keyWord, region,
		grossFloorArea, type, rentOrSale, rental, totalPrice, orderBy,
		0, Constants.DEFAULT_PAGE_SIZE);
	int totalNum = shopService.countShopByParams(keyWord, region,
		grossFloorArea, type, rentOrSale, rental, totalPrice);
	if (list != null && !list.isEmpty()) {
	    for (int i = 0; i < list.size(); i++) {
		Shop shop = list.get(i);
		shop.setPreImageUrl(aossService.addImgParams(
			shop.getPreImageUrl(),
			Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
	    }
	}
	request.setAttribute("results", list);
	request.setAttribute("total", totalNum);
	return "ui/sp/index";
    }

    @RequestMapping(value = "/sp/json/{keyWord}-{region}-{grossFloorArea}-{type}-{rentOrSale}-{rental}-{totalPrice}-{orderBy}-{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
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
	    List<Shop> list = shopService.findShopsByParams(keyWord, region,
		    grossFloorArea, type, rentOrSale, rental, totalPrice,
		    orderBy, targetPage, pageSize);
	    if (list != null && !list.isEmpty()) {
		for (int i = 0; i < list.size(); i++) {
		    Shop shop = list.get(i);
		    shop.setPreImageUrl(aossService.addImgParams(
			    shop.getPreImageUrl(),
			    Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
		}
	    }
	    jo.put("results", list);
	    jo.put("success", true);
	} catch (Exception e) {
	    LOG.error("查询商铺出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "查询出错!");
	}
	return jo.toString();
    }

    @RequestMapping(value = "user/sp", method = { RequestMethod.GET })
    public String toCurUserEsf(HttpServletRequest request) {
	String userId = userService.getCurUserId();
	List<Shop> list = shopService.getByUserId(userId, 0,
		Constants.DEFAULT_PAGE_SIZE);
	int total = shopService.countByUserId(userId);
	if (list != null && !list.isEmpty()) {
	    for (int i = 0; i < list.size(); i++) {
		Shop shop = list.get(i);
		shop.setPreImageUrl(aossService.addImgParams(
			shop.getPreImageUrl(),
			Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
	    }
	}
	request.setAttribute("results", list);
	request.setAttribute("total", total);
	return "ui/user/sp/index";
    }

    @RequestMapping(value = "user/sp/add", method = { RequestMethod.GET })
    public String toAdd(HttpServletRequest request) {
	return "ui/user/sp/add";
    }

    @RequestMapping(value = "user/sp/json/add", method = { RequestMethod.POST })
    public @ResponseBody Object add(HttpServletRequest request, Shop shop,
	    String imgUrls) {
	JSONObject jo = new JSONObject();
	try {
	    imgUrls = aossService.batchClearImgParams(imgUrls);
	    String errorStr = shopService.saveCascading(shop, imgUrls);
	    if (StringUtils.isBlank(errorStr)) {
		jo.put("success", true);
	    } else {
		jo.put("success", false);
		jo.put("msg", errorStr);
	    }
	} catch (Exception e) {
	    LOG.error("保存商铺出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "发布出错!");
	}
	return jo.toString();
    }

    @RequestMapping(value = "user/sp/json/del", method = { RequestMethod.POST })
    public @ResponseBody Object del(HttpServletRequest request, String id) {
	JSONObject jo = new JSONObject();
	try {
	    String errorStr = shopService.deleteUserShopByFlag(id);
	    if (StringUtils.isBlank(errorStr)) {
		jo.put("success", true);
	    } else {
		jo.put("success", false);
		jo.put("msg", errorStr);
	    }
	} catch (Exception e) {
	    LOG.error("删除商铺出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "删除出错!");
	}
	return jo.toString();
    }

    @RequestMapping(value = "user/sp/json/refresh", method = { RequestMethod.POST })
    public @ResponseBody Object refresh(HttpServletRequest request, String id) {
	JSONObject jo = new JSONObject();
	try {
	    String errorStr = shopService.refreshUserShop(id);
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
	    LOG.error("更新商铺有效期出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "刷新出错!");
	}
	return jo.toString();
    }

    @RequestMapping(value = "user/sp/json/update", method = { RequestMethod.POST })
    public @ResponseBody Object update(HttpServletRequest request, Shop sp,
	    String imgUrls) {
	JSONObject jo = new JSONObject();
	try {
	    imgUrls = aossService.batchClearImgParams(imgUrls);
	    String errorStr = shopService.updateCascading(sp, imgUrls);
	    if (StringUtils.isBlank(errorStr)) {
		jo.put("success", true);
	    } else {
		jo.put("success", false);
		jo.put("msg", errorStr);
	    }
	} catch (Exception e) {
	    LOG.error("更新商铺出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "修改出错!");
	}
	return jo.toString();
    }

    @RequestMapping(value = "user/sp/{id}/manage", method = { RequestMethod.GET })
    public String toManager(HttpServletRequest request, @PathVariable String id) {
	Shop sp = shopService.get(id);
	List<ShopImage> images = siService.getImagesByParams(id, 0,
		Integer.MAX_VALUE);
	Calendar cal = Calendar.getInstance();
	cal.setTime(sp.getUpdateDate());
	cal.add(Calendar.DAY_OF_YEAR, Constants.ACTIVE_TIME);
	request.setAttribute("deadline",
		cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1)
			+ "-" + cal.get(Calendar.DAY_OF_MONTH));
	if (images != null && !images.isEmpty()) {
	    for (int i = 0; i < images.size(); i++) {
		ShopImage si = images.get(i);
		si.setContentUrl(aossService.addImgParams(si.getContentUrl(),
			Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_HEAD_IMG));
	    }
	}
	request.setAttribute("sp", sp);
	request.setAttribute("images", images);
	return "ui/user/sp/manage";
    }

    @RequestMapping(value = "user/sp/json/{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
    public @ResponseBody Object ajaxCurUserSpSearch(HttpServletRequest request,
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
	    List<Shop> list = shopService.getByUserId(userId, targetPage,
		    pageSize);
	    if (list != null && !list.isEmpty()) {
		for (int i = 0; i < list.size(); i++) {
		    Shop shop = list.get(i);
		    shop.setPreImageUrl(aossService.addImgParams(
			    shop.getPreImageUrl(),
			    Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
		}
	    }
	    jo.put("results", list);
	    jo.put("success", true);
	} catch (Exception e) {
	    LOG.error("查询商铺出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "查询出错!");
	}
	return jo.toString();
    }

    @RequestMapping(value = { "/user/sp/uploadImg", "/mi/shop/uploadImg" }, method = {
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
	    LOG.error("上传商铺图片出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "保存图片失败");
	}
	return jo.toString();
    }

    @RequestMapping(value = "/mi/shop", method = { RequestMethod.GET })
    public String index(HttpServletRequest request) {

	return "/mi/shop/index";
    }

    @RequestMapping(value = "/mi/shop/page/{curPage}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getShopByPage(HttpServletRequest request,
	    @PathVariable int curPage) {

	Object res = null;

	int page = curPage - 1 > 0 ? curPage - 1 : 0;

	String name = request.getParameter("name") == null ? null
		: (String) request.getParameter("name");

	if (!StringUtils.isBlank(name)) {
	    try {
		name = URLDecoder.decode(name, "utf-8");
	    } catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		JSONObject jo = new JSONObject();
		jo.put("success", false);
		jo.put("msg", "查询条件解码出错");
		LOG.error("查询商铺分页，查询条件解码出错！", e);

		return jo.toString();
	    }
	}

	Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
		: Integer.valueOf((String) request.getParameter("pagesize"));

	int rows = 0;
	try {
	    // 有userid则查询关联的role，无则查询所有role
	    List<Shop> items = shopService.findShopsByParams(name, null, null,
		    null, null, null, null, null, page, pageSize);
	    rows = shopService.countShopByParams(name, null, null, null, null,
		    null, null);
	    int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
		    / pageSize + 1);
	    res = getJsonObject(rows, totalpage, curPage, pageSize, items,
		    true, "");
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    res = getJsonObject(rows, 0, curPage, pageSize, null, false, "");
	    LOG.error("查询商铺分页信息报错！", e);
	}
	return res;
    }

    public Object getJsonObject(int rows, int totalpage, int curPage,
	    int pageSize, List<Shop> items, boolean rescode, String msg) {
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

    @RequestMapping(value = "/mi/shop/{id}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getShop(@PathVariable String id, HttpServletRequest request) {

	JSONObject jo = new JSONObject();

	try {
	    Shop shop = (Shop) shopService.get(id);
	    if (shop != null) {
		jo.put("shop", shop);
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    jo.put("shop", null);
	    LOG.error("查询商铺信息出错！", e);
	}

	return jo.toString();
    }

    @RequestMapping(value = "/mi/shop/add", method = { RequestMethod.GET })
    public String toAddShop(Model model, HttpServletRequest request) {

	// return new ModelAndView("mi/users/add","user",new User());

	return "mi/shop/add";
    }

    @RequestMapping(value = "/mi/shop", method = { RequestMethod.POST })
    @ResponseBody
    public Object addShop(HttpServletRequest request, Shop shop) {

	JSONObject jo = new JSONObject();

	if (shop == null) {
	    jo.put("success", false);
	    jo.put("msg", "新增商铺信息不能为空!");
	} else {

	    try {

		Map<String, String> res = shopService.addShop(shop);

		if (StringUtils.isEmpty(res.get("msg"))) {
		    jo.put("success", true);
		    jo.put("msg", "新增商铺保存成功!");

		} else {
		    jo.put("success", false);
		    jo.put("msg", res.get("msg"));
		    jo.put("field", res.get("field"));
		}

	    } catch (Exception e) {
		jo.put("success", false);
		jo.put("msg", "新增商铺保存失败!");
		LOG.error("新增商铺保存失败！", e);
	    }

	}

	return jo.toString();
    }

    @RequestMapping(value = "/mi/shop/{id}/edit", method = { RequestMethod.GET })
    public String toUpdateShop(HttpServletRequest request, Model model,
	    @PathVariable String id) {
	model.addAttribute("shopId", id);
	return "/mi/shop/edit";
    }

    @RequestMapping(value = "/mi/shop/{id}/detail", method = { RequestMethod.GET })
    public String toViewShop(Model model, @PathVariable String id) {

	model.addAttribute("shopId", id);

	return "/mi/shop/detail";
    }

    @RequestMapping(value = "/mi/shop/{id}", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateShop(HttpServletRequest request, Shop shop) {

	JSONObject jo = new JSONObject();
	try {

	    Map<String, String> res = shopService.updateShop(shop);

	    if (StringUtils.isEmpty(res.get("msg"))) {
		jo.put("success", true);
		jo.put("msg", "更新商铺成功!");

	    } else {
		jo.put("success", false);
		jo.put("msg", res.get("msg"));
		jo.put("field", res.get("field"));
	    }

	} catch (Exception e) {
	    jo.put("success", false);
	    jo.put("msg", "更新商铺失败!");
	    LOG.error("更新商铺信息失败！", e);
	}

	return jo.toString();
    }

    @RequestMapping(value = "/mi/shops", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateBatchShop(HttpServletRequest request, Shop shop,
	    String shopids) {
	JSONObject jo = new JSONObject();

	try {
	    shopService.updateBatchShop(shopids, shop);

	    jo.put("success", true);
	    jo.put("msg", "商铺更新成功");
	} catch (Exception e) {
	    // TODO Auto-generated catch block

	    jo.put("success", false);
	    jo.put("msg", "商铺更新失败");
	    LOG.error("更新商铺信息失败！", e);
	}

	return jo.toString();
    }

}
