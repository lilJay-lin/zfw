package com.mimi.zfw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.Advertisement;
import com.mimi.zfw.mybatis.pojo.Information;
import com.mimi.zfw.mybatis.pojo.RealEstateProject;
import com.mimi.zfw.service.IAdvertisementService;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IInformationService;
import com.mimi.zfw.service.IRealEstateProjectService;

@Controller
public class InformationController {
	private static final Logger LOG = LoggerFactory
			.getLogger(InformationController.class);

	@Resource
	private IAdvertisementService adService;

	@Resource
	private IInformationService infoService;
	@Resource
	private IAliyunOSSService aossService;
	@Resource
	private IRealEstateProjectService repService;

	@RequestMapping(value = "/info", method = { RequestMethod.GET })
	public String toFCInfo(HttpServletRequest request) {
		List<Advertisement> ads = adService
				.getActiveByLocation(Constants.AD_LOCATION_INFO_TOP);
		List<Information> infoList = infoService.findByParams(
				Constants.INFORMATION_TYPE_FC, 0, Constants.DEFAULT_PAGE_SIZE);
		int total = infoService.countByParams(Constants.INFORMATION_TYPE_FC);
		if (infoList != null && !infoList.isEmpty()) {
			for (int i = 0; i < infoList.size(); i++) {
				Information info = infoList.get(i);
				info.setPreImageUrl(aossService.addImgParams(
						info.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
			}
		}
		if (ads != null && !ads.isEmpty()) {
			for (int i = 0; i < ads.size(); i++) {
				Advertisement ad = ads.get(i);
				ad.setPreImageUrl(aossService.addImgParams(ad.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_AD));
			}
		}
		request.setAttribute("ads", ads);
		request.setAttribute("results", infoList);
		request.setAttribute("total", total);
		return "ui/zx/index";
	}

	@RequestMapping(value = "/info/{type}/type", method = { RequestMethod.GET })
	public String toZHInfo(HttpServletRequest request, @PathVariable String type) {
		List<Advertisement> ads = adService
				.getActiveByLocation(Constants.AD_LOCATION_INFO_TOP);
		List<Information> infoList = infoService.findByParams(type, 0,
				Constants.DEFAULT_PAGE_SIZE);
		int total = infoService.countByParams(type);
		if (infoList != null && !infoList.isEmpty()) {
			for (int i = 0; i < infoList.size(); i++) {
				Information info = infoList.get(i);
				info.setPreImageUrl(aossService.addImgParams(
						info.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
			}
		}
		if (ads != null && !ads.isEmpty()) {
			for (int i = 0; i < ads.size(); i++) {
				Advertisement ad = ads.get(i);
				ad.setPreImageUrl(aossService.addImgParams(ad.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_AD));
			}
		}
		request.setAttribute("ads", ads);
		request.setAttribute("results", infoList);
		request.setAttribute("total", total);
		return "ui/zx/index";
	}

	@RequestMapping(value = "/info/{id}/detail", method = { RequestMethod.GET })
	public String toDetail(HttpServletRequest request, @PathVariable String id) {
		List<Advertisement> ads = adService
				.getActiveByLocation(Constants.AD_LOCATION_INFO_TOP);
		Information info = infoService.get(id);
		if (ads != null && !ads.isEmpty()) {
			for (int i = 0; i < ads.size(); i++) {
				Advertisement ad = ads.get(i);
				ad.setPreImageUrl(aossService.addImgParams(ad.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_AD));
			}
		}
		request.setAttribute("ads", ads);
		request.setAttribute("info", info);
		return "ui/zx/detail";
	}

	@RequestMapping(value = "/info/json/{type}-{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
	public @ResponseBody
	Object ajaxSearch(HttpServletRequest request, @PathVariable String type,
			@PathVariable Integer targetPage, @PathVariable Integer pageSize) {
		if (targetPage == null) {
			targetPage = 0;
		}
		if (pageSize == null) {
			pageSize = Constants.DEFAULT_PAGE_SIZE;
		}
		JSONObject jo = new JSONObject();
		try {
			List<Information> list = infoService.findByParams(type, targetPage,
					pageSize);
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Information info = list.get(i);
					info.setPreImageUrl(aossService.addImgParams(
							info.getPreImageUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			jo.put("results", list);
			jo.put("success", true);
		} catch (Exception e) {
			LOG.error("查询咨询出错！", e);
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/info/json/{realEstateProjectId}/{targetPage}-{pageSize}/search", method = { RequestMethod.GET })
	public @ResponseBody
	Object ajaxIdSearch(HttpServletRequest request,
			@PathVariable String realEstateProjectId,
			@PathVariable Integer targetPage, @PathVariable Integer pageSize) {
		if (targetPage == null) {
			targetPage = 0;
		}
		if (pageSize == null) {
			pageSize = Constants.DEFAULT_PAGE_SIZE;
		}
		JSONObject jo = new JSONObject();
		try {
			List<Information> list = infoService.findByREPId(
					realEstateProjectId, targetPage, pageSize);
			if (list != null && !list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Information info = list.get(i);
					info.setPreImageUrl(aossService.addImgParams(
							info.getPreImageUrl(),
							Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				}
			}
			jo.put("results", list);
			jo.put("success", true);
		} catch (Exception e) {
			LOG.error("查询楼盘资讯出错！", e);
			jo.put("success", false);
			jo.put("msg", "查询出错!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/info", method = { RequestMethod.GET })
	public String toMIInfo(HttpServletRequest request) {
		return "mi/zx/index";
	}

	@RequestMapping(value = "/mi/info/{id}", method = { RequestMethod.GET })
	@ResponseBody
	public Object getInfo(@PathVariable String id, HttpServletRequest request) {
		JSONObject jo = new JSONObject();
		try {
			Information info = infoService.get(id);
			if (info != null) {
				info.setPreImageUrl(aossService.addImgParams(
						info.getPreImageUrl(),
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
				jo.put("info", info);
				List<RealEstateProject> relationREPs = repService
						.getREPByInfoId(id);
				jo.put("relationREPs", relationREPs);
			}
		} catch (Exception e) {
			LOG.error("获取资讯失败", e);
			jo.put("info", null);
			jo.put("relationREPs", null);
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/info/{infoId}/edit", method = { RequestMethod.GET })
	public String toUpdateInfo(HttpServletRequest request, Model model,
			@PathVariable String infoId) {
		model.addAttribute("infoId", infoId);
		return "/mi/zx/edit";
	}

	@RequestMapping(value = "/mi/info/{infoId}", method = { RequestMethod.POST })
	@ResponseBody
	public Object updateInfo(HttpServletRequest request, Information info,
			@PathVariable String infoId, String addREPs, String delREPs) {
		JSONObject jo = new JSONObject();
		try {
			Map<String, String> res = infoService.updateInfo(info, addREPs,
					delREPs);
			if (res.isEmpty()) {
				jo.put("success", true);
				jo.put("msg", "更新用户成功!");
			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}
		} catch (Exception e) {
			LOG.error("更新资讯失败", e);
			jo.put("success", false);
			jo.put("msg", "更新用户失败!");
		}
		return jo.toString();
	}

	@RequestMapping(value = "/mi/info/{infoId}/detail", method = { RequestMethod.GET })
	public String toInfoDetail(HttpServletRequest request, Model model,
			@PathVariable String infoId) {
		return "/mi/zx/detail";
	}

	@RequestMapping(value = "/mi/info/page/{curPage}", method = { RequestMethod.GET })
	@ResponseBody
	public Object miIndex(HttpServletRequest request,
			@PathVariable int curPage, String name, String type) {

		Object res = null;

		int page = curPage - 1 > 0 ? curPage - 1 : 0;

		Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
				: Integer.valueOf((String) request.getParameter("pagesize"));
		int rows = 0;
		try {
			rows = infoService.countInfoByParams(name, type);
			List<Information> items = infoService.findInfoByParams(name, type,
					page, pageSize);
			int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
					/ pageSize + 1);
			res = getJsonObject(rows, totalpage, curPage, pageSize, items,
					true, "");
		} catch (Exception e) {
			LOG.error("后台查询资讯出错", e);
			res = getJsonObject(rows, 0, curPage, pageSize, null, false, "");
		}
		return res;
	}

	@RequestMapping(value = "/mi/info/add", method = { RequestMethod.GET })
	public String toAddInfo(Model model, HttpServletRequest request) {
		return "mi/zx/add";
	}

	@RequestMapping(value = "/mi/info/add", method = { RequestMethod.POST })
	@ResponseBody
	public Object addInfo(HttpServletRequest request, Information info,
			String repIds) {

		JSONObject jo = new JSONObject();
		try {

			Map<String, String> res = infoService.addInfo(info, repIds);

			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "资讯保存成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "资讯保存失败!");
		}

		return jo.toString();
	}

	@RequestMapping(value = "/mi/info/batchDel", method = { RequestMethod.POST })
	@ResponseBody
	public Object batchDelInfo(HttpServletRequest request, String infoIds) {

		JSONObject jo = new JSONObject();
		try {

			Map<String, String> res = infoService.batchDel(infoIds);

			if (StringUtils.isEmpty(res.get("msg"))) {
				jo.put("success", true);
				jo.put("msg", "资讯删除成功!");

			} else {
				jo.put("success", false);
				jo.put("msg", res.get("msg"));
				jo.put("field", res.get("field"));
			}

		} catch (Exception e) {
			jo.put("success", false);
			jo.put("msg", "资讯删除失败!");
		}

		return jo.toString();
	}

	@RequestMapping(value = "/mi/info/uploadImg", method = { RequestMethod.POST })
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
			LOG.error("上传资讯缩略图出错！", e);
			jo.put("success", false);
			jo.put("msg", "保存图片失败");
		}
		return jo.toString();
	}

	public Object getJsonObject(int rows, int totalpage, int curPage,
			int pageSize, List<Information> items, boolean rescode, String msg) {
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

	@RequestMapping(value = "/ueditor/controller", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void ueditorController(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletRequestBindingException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "text/html");
		String rootPath = request.getSession().getServletContext()
				.getRealPath("/");
		response.getWriter().write(new ActionEnter(request, rootPath).exec());
	}

	@RequestMapping(value = "/ueditor/uploadImage", method = {
			RequestMethod.GET, RequestMethod.POST })
	public void ueditorUploadImage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("upfile") MultipartFile upfile) throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "text/html");
		State state;
		try {
			List<String> allowSuffix = new ArrayList<String>();
			allowSuffix.add(".jpg");
			allowSuffix.add(".jpeg");
			allowSuffix.add(".png");
			allowSuffix.add(".gif");
			String fileName = upfile.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			if (!allowSuffix.contains(suffix)) {
				state = new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			} else if (upfile.getSize() > 25 * 1024 * 1024) {
				state = new BaseState(false, AppInfo.MAX_SIZE);
			} else {
				String path = aossService.saveFileToServer(upfile);
				path = aossService.addImgParams(path,
						Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_INFO_IMG);
				state = new BaseState(true);
				state.putInfo("url", path);
				state.putInfo("type", suffix);
				state.putInfo("original", fileName);
				state.putInfo("size", upfile.getSize());
				state.putInfo("title", fileName);
			}
		} catch (Exception e) {
			state = new BaseState(false, AppInfo.FAILED_CREATE_FILE);
		}
		response.getWriter().write(state.toJSONString());
	}

}
