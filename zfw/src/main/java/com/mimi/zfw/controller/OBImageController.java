package com.mimi.zfw.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.mimi.zfw.mybatis.pojo.OBImage;
import com.mimi.zfw.mybatis.pojo.OBImageExample;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IOBImageService;

@Controller
@RequestMapping(value="/mi")
public class OBImageController {

    private static final Logger LOG = LoggerFactory
	    .getLogger(SPController.class);
    
    @Resource
    private IOBImageService obiService;
    @Resource
    private IAliyunOSSService aossService;

    @RequestMapping(value = "/xzlphoto/uploadImg", method = {
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
	    LOG.error("上传写字楼图片图片出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "保存图片失败");
	}
	return jo.toString();
    }

//    @RequestMapping(value = "/mi/shop", method = { RequestMethod.GET })
//    public String index(HttpServletRequest request) {
//
//	return "/mi/{officeBuildingId}/xzlphoto/index";
//    }

    @RequestMapping(value = "/{officeBuildingId}/xzlphoto/page/{curPage}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getShopByPage(HttpServletRequest request,@PathVariable String officeBuildingId,
	    @PathVariable int curPage) {

	Object res = null;

	int page = curPage - 1 > 0 ? curPage - 1 : 0;

	String name = request.getParameter("name") == null ? null
		: (String) request.getParameter("name");

	OBImageExample example = new OBImageExample();
	OBImageExample.Criteria cr = example.createCriteria();
	if (StringUtils.isNotBlank(name)) {
	    try {
		cr.andNameLike("%"+URLDecoder.decode(name, "utf-8")+"%");
	    } catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		JSONObject jo = new JSONObject();
		jo.put("success", false);
		jo.put("msg", "查询条件解码出错");
		LOG.error("查询写字楼图片分页，查询条件解码出错！", e);

		return jo.toString();
	    }
	}
	cr.andOfficeBuildingIdEqualTo(officeBuildingId).andDelFlagEqualTo(false);
	
	Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
		: Integer.valueOf((String) request.getParameter("pagesize"));

	int rows = 0;
	try {
	    // 有userid则查询关联的role，无则查询所有role
	    rows = obiService.countOBImageByExample(example);
	    List<OBImage> items = obiService.findOBImageByExample(example, page, pageSize);
	    
	    int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
		    / pageSize + 1);
	    res = getJsonObject(rows, totalpage, curPage, pageSize, items,
		    true, "");
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    res = getJsonObject(rows, 0, curPage, pageSize, null, false, "");
	    LOG.error("查询写字楼图片分页信息报错！", e);
	}
	return res;
    }

    public Object getJsonObject(int rows, int totalpage, int curPage,
	    int pageSize, List<OBImage> items, boolean rescode, String msg) {
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

    @RequestMapping(value = "/xzlphoto/{id}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getOBImage( @PathVariable String id, HttpServletRequest request) {

	JSONObject jo = new JSONObject();

	try {
	    OBImage image = (OBImage) obiService.get(id);
	    if (image != null) {
		jo.put("image", image);
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    jo.put("shop", null);
	    LOG.error("查询写字楼图片信息出错！", e);
	}

	return jo.toString();
    }

    @RequestMapping(value = "/{officeBuildingId}/xzlphoto/add", method = { RequestMethod.GET })
    public String toAddOBImage(@PathVariable String officeBuildingId,Model model, HttpServletRequest request) {
	model.addAttribute("officeBuildingId", officeBuildingId);
	return "/mi/xzlphoto/add";
    }

    @RequestMapping(value = "/xzlphoto", method = { RequestMethod.POST })
    @ResponseBody
    public Object addOBImage(HttpServletRequest request, OBImage obImage) {

	JSONObject jo = new JSONObject();

	if (obImage == null) {
	    jo.put("success", false);
	    jo.put("msg", "新增写字楼图片信息不能为空!");
	} else {

	    try {
		Map<String, String> res = obiService.addOBImage(obImage);

		if (StringUtils.isEmpty(res.get("msg"))) {
		    jo.put("success", true);
		    jo.put("msg", "新增写字楼图片信息保存成功!");

		} else {
		    jo.put("success", false);
		    jo.put("msg", res.get("msg"));
		    jo.put("field", res.get("field"));
		}

	    } catch (Exception e) {
		jo.put("success", false);
		jo.put("msg", "新增写字楼图片信息保存失败!");
		LOG.error("新增写字楼图片信息保存失败！", e);
	    }

	}

	return jo.toString();
    }

    @RequestMapping(value = "/{officeBuildingId}/xzlphoto/{id}/edit", method = { RequestMethod.GET })
    public String toUpdateOBImage(@PathVariable String officeBuildingId,HttpServletRequest request, Model model,
	    @PathVariable String id) {
	model.addAttribute("officeBuildingId", officeBuildingId);
	model.addAttribute("imageId", id);
	return "/mi/xzlphoto/edit";
    }

    @RequestMapping(value = "/{officeBuildingId}/xzlphoto/{id}/detail", method = { RequestMethod.GET })
    public String toViewOBImage(@PathVariable String officeBuildingId, HttpServletRequest request, Model model,@PathVariable String id) {

	model.addAttribute("officeBuildingId", officeBuildingId);
	model.addAttribute("imageId", id);
	return "/mi/xzlphoto/detail";
    }

    @RequestMapping(value = "/xzlphoto/{id}", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateOBImage(HttpServletRequest request, OBImage obImage ,@PathVariable String id) {

	JSONObject jo = new JSONObject();
	try {

		Map<String, String> res = obiService.updateOBImage(obImage);

		if (StringUtils.isEmpty(res.get("msg"))) {
		    jo.put("success", true);
		    jo.put("msg", "更新写字楼图片信息保存成功!");

		} else {
		    jo.put("success", false);
		    jo.put("msg", res.get("msg"));
		    jo.put("field", res.get("field"));
		}

	} catch (Exception e) {
	    jo.put("success", false);
	    jo.put("msg", "更新写字楼图片信息失败!");
	    LOG.error("更新写字楼图片信息失败！", e);
	}

	return jo.toString();
    }

    @RequestMapping(value = "/xzlphotos", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateBatchOBImage(HttpServletRequest request, OBImage obImage,
	    String imageIds) {
	JSONObject jo = new JSONObject();

	try {
	    obiService.updateBatchOBImage(imageIds, obImage);
	    jo.put("success", true);
	    jo.put("msg", "写字楼图片删除成功");
	} catch (Exception e) {
	    // TODO Auto-generated catch block

	    jo.put("success", false);
	    jo.put("msg", "写字楼图片删除失败");
	    LOG.error("删除写字楼图片信息失败！", e);
	}

	return jo.toString();
    }

}
