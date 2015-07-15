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
import com.mimi.zfw.mybatis.pojo.OBPano;
import com.mimi.zfw.mybatis.pojo.OBPanoExample;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IOBPanoService;

@Controller
@RequestMapping(value="/mi")
public class OBPanoController {

    private static final Logger LOG = LoggerFactory
	    .getLogger(SPController.class);
    
    @Resource
    private IOBPanoService obpService;
    @Resource
    private IAliyunOSSService aossService;

    @RequestMapping(value = "/xzlpano/uploadImg", method = {
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
	    LOG.error("上传写字楼全景图片出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "保存图片失败");
	}
	return jo.toString();
    }

//    @RequestMapping(value = "/mi/shop", method = { RequestMethod.GET })
//    public String index(HttpServletRequest request) {
//
//	return "/mi/{officeBuildingId}/xzlpano/index";
//    }

    @RequestMapping(value = "/{officeBuildingId}/xzlpano/page/{curPage}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getShopByPage(HttpServletRequest request,@PathVariable String officeBuildingId,
	    @PathVariable int curPage) {

	Object res = null;

	int page = curPage - 1 > 0 ? curPage - 1 : 0;

	String name = request.getParameter("name") == null ? null
		: (String) request.getParameter("name");

	OBPanoExample example = new OBPanoExample();
	OBPanoExample.Criteria cr = example.createCriteria();
	if (StringUtils.isNotBlank(name)) {
	    try {
		cr.andNameLike("%"+URLDecoder.decode(name, "utf-8")+"%");
	    } catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		JSONObject jo = new JSONObject();
		jo.put("success", false);
		jo.put("msg", "查询条件解码出错");
		LOG.error("查询写字楼全景分页，查询条件解码出错！", e);

		return jo.toString();
	    }
	}
	cr.andOfficeBuildingIdEqualTo(officeBuildingId).andDelFlagEqualTo(false);
	
	Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
		: Integer.valueOf((String) request.getParameter("pagesize"));

	int rows = 0;
	try {
	    // 有userid则查询关联的role，无则查询所有role
	    rows = obpService.countOBPanoByExample(example);
	    List<OBPano> items = obpService.findOBPanoByExample(example, page, pageSize);
	    
	    int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
		    / pageSize + 1);
	    res = getJsonObject(rows, totalpage, curPage, pageSize, items,
		    true, "");
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    res = getJsonObject(rows, 0, curPage, pageSize, null, false, "");
	    LOG.error("查询写字楼全景分页信息报错！", e);
	}
	return res;
    }

    public Object getJsonObject(int rows, int totalpage, int curPage,
	    int pageSize, List<OBPano> items, boolean rescode, String msg) {
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

    @RequestMapping(value = "/xzlpano/{id}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getOBPano( @PathVariable String id, HttpServletRequest request) {

	JSONObject jo = new JSONObject();

	try {
	    OBPano image = (OBPano) obpService.get(id);
	    if (image != null) {
		jo.put("image", image);
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    jo.put("shop", null);
	    LOG.error("查询写字楼全景信息出错！", e);
	}

	return jo.toString();
    }

    @RequestMapping(value = "/{officeBuildingId}/xzlpano/add", method = { RequestMethod.GET })
    public String toAddOBPano(@PathVariable String officeBuildingId,Model model, HttpServletRequest request) {
	model.addAttribute("officeBuildingId", officeBuildingId);
	return "/mi/xzlpano/add";
    }

    @RequestMapping(value = "/xzlpano", method = { RequestMethod.POST })
    @ResponseBody
    public Object addOBPano(HttpServletRequest request, OBPano obPano) {

	JSONObject jo = new JSONObject();

	if (obPano == null) {
	    jo.put("success", false);
	    jo.put("msg", "新增写字楼全景信息不能为空!");
	} else {

	    try {
		Map<String, String> res = obpService.addOBPano(obPano);

		if (StringUtils.isEmpty(res.get("msg"))) {
		    jo.put("success", true);
		    jo.put("msg", "新增写字楼全景信息保存成功!");

		} else {
		    jo.put("success", false);
		    jo.put("msg", res.get("msg"));
		    jo.put("field", res.get("field"));
		}

	    } catch (Exception e) {
		jo.put("success", false);
		jo.put("msg", "新增写字楼全景信息保存失败!");
		LOG.error("新增写字楼全景信息保存失败！", e);
	    }

	}

	return jo.toString();
    }

    @RequestMapping(value = "/{officeBuildingId}/xzlpano/{id}/edit", method = { RequestMethod.GET })
    public String toUpdateOBPano(@PathVariable String officeBuildingId,HttpServletRequest request, Model model,
	    @PathVariable String id) {
	model.addAttribute("officeBuildingId", officeBuildingId);
	model.addAttribute("panoId", id);
	return "/mi/xzlpano/edit";
    }

    @RequestMapping(value = "/{officeBuildingId}/xzlpano/{id}/detail", method = { RequestMethod.GET })
    public String toViewOBPano(@PathVariable String officeBuildingId, HttpServletRequest request, Model model,@PathVariable String id) {

	model.addAttribute("officeBuildingId", officeBuildingId);
	model.addAttribute("panoId", id);
	return "/mi/xzlpano/detail";
    }

    @RequestMapping(value = "/xzlpano/{id}", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateOBPano(HttpServletRequest request, OBPano obPano ,@PathVariable String id) {

	JSONObject jo = new JSONObject();
	try {

		Map<String, String> res = obpService.updateOBPano(obPano);

		if (StringUtils.isEmpty(res.get("msg"))) {
		    jo.put("success", true);
		    jo.put("msg", "更新写字楼全景信息保存成功!");

		} else {
		    jo.put("success", false);
		    jo.put("msg", res.get("msg"));
		    jo.put("field", res.get("field"));
		}

	} catch (Exception e) {
	    jo.put("success", false);
	    jo.put("msg", "更新写字楼全景信息失败!");
	    LOG.error("更新写字楼全景信息失败！", e);
	}

	return jo.toString();
    }

    @RequestMapping(value = "/xzlpanos", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateBatchOBPano(HttpServletRequest request, OBPano obPano,
	    String panoIds) {
	JSONObject jo = new JSONObject();

	try {
	    obpService.updateBatchOBPano(panoIds, obPano);
	    jo.put("success", true);
	    jo.put("msg", "写字楼全景删除成功");
	} catch (Exception e) {
	    // TODO Auto-generated catch block

	    jo.put("success", false);
	    jo.put("msg", "写字楼全景删除失败");
	    LOG.error("删除写字楼全景信息失败！", e);
	}

	return jo.toString();
    }

}
