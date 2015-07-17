package com.mimi.zfw.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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

import com.baidu.ueditor.um.Uploader;
import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.Advertisement;
import com.mimi.zfw.mybatis.pojo.Information;
import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.service.IAdvertisementService;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IInformationService;
import com.mimi.zfw.service.INameListService;
import com.mimi.zfw.service.IOfficeBuildingService;
import com.mimi.zfw.service.IRealEstateProjectService;
import com.mimi.zfw.service.IRentalHousingService;
import com.mimi.zfw.service.ISecondHandHouseService;
import com.mimi.zfw.service.IShopService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.service.IWarehouseService;

//import com.mimi.zfw.web.bind.annotation.CurrentUser;

@Controller("indexController")
public class IndexController {
	private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);  

	@Resource
	private IAdvertisementService adService;

	@Resource
	private IInformationService infoService;
	
	@Resource
	private INameListService nameListService;
	@Resource
	private IRealEstateProjectService repService;
	@Resource
	private ISecondHandHouseService shhService;
	@Resource
	private IRentalHousingService rhService;
	@Resource
	private IShopService spService;
	@Resource
	private IOfficeBuildingService obService;
	@Resource
	private IWarehouseService wService;
    @Resource
    private IUserService userService;
	@Resource
	private IAliyunOSSService aossService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String list(HttpServletRequest request ) {
    	homePageSet(request);
	return "ui/index";
    }

    @RequestMapping(value = "/index", method = { RequestMethod.GET })
    public String index(HttpServletRequest request)
	    throws ServletRequestBindingException {
    	homePageSet(request);
	return "ui/index";
    }
    private void homePageSet(HttpServletRequest request){
    	List<Advertisement> adts = adService.getActiveByLocation(Constants.AD_LOCATION_HOME_TOP);
    	List<Advertisement> adm4s = adService.getActiveByLocation(Constants.AD_LOCATION_HOME_MIDDLE_FOUR);
    	List<Advertisement> admos = adService.getActiveByLocation(Constants.AD_LOCATION_HOME_MIDDLE_ONE);
    	Advertisement admo = null;
    	if(admos!=null && !admos.isEmpty()){
    		admo = admos.get(0);
    		admo.setPreImageUrl(aossService.addImgParams(admo.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_AD));
    	}
    	List<Information> fczx = infoService.findByParams(Constants.INFORMATION_TYPE_FC, 0, 15);
    	List<Information> zhzx = infoService.findByParams(Constants.INFORMATION_TYPE_ZH, 0, 15);
    	

		if(adts!=null && !adts.isEmpty()){
			for(int i=0;i<adts.size();i++){
				Advertisement ad = adts.get(i);
				ad.setPreImageUrl(aossService.addImgParams(ad.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_AD));
			}
		}
		if(adm4s!=null && !adm4s.isEmpty()){
			for(int i=0;i<adm4s.size();i++){
				Advertisement ad = adm4s.get(i);
				ad.setPreImageUrl(aossService.addImgParams(ad.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_AD_SMALL));
			}
		}
		if(fczx!=null && !fczx.isEmpty()){
			for(int i=0;i<fczx.size();i++){
				Information info = fczx.get(i);
				info.setPreImageUrl(aossService.addImgParams(info.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
			}
		}
		if(zhzx!=null && !zhzx.isEmpty()){
			for(int i=0;i<zhzx.size();i++){
				Information info = zhzx.get(i);
				info.setPreImageUrl(aossService.addImgParams(info.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_NORMAL_PRE_IMG));
			}
		}
    	request.setAttribute("adts", adts);
    	request.setAttribute("adm4s", adm4s);
    	request.setAttribute("admo", admo);
    	request.setAttribute("fczx", fczx);
    	request.setAttribute("zhzx", zhzx);
    	request.setAttribute("showSignUp", nameListService.isShowSignUpForm());
    	request.setAttribute("signUpFormTitle", nameListService.getSingUpFormTitle());
    }

    @RequestMapping(value = "/nameList/json/signUp", method = RequestMethod.POST)
    public @ResponseBody
    Object ajaxSignUp(String name,String phoneNum) {
		JSONObject jo = new JSONObject();
		try{
			String errorStr = nameListService.signUp(name,phoneNum);
			if(StringUtils.isNotBlank(errorStr)){
				jo.put("success", false);
				jo.put("msg", errorStr);
			}else{
				jo.put("name", name);
				jo.put("phoneNum", phoneNum);
				jo.put("success", true);
			}
		}catch(Exception e){
			LOG.error("保存报名名单出错！",e);
			jo.put("success", false);
			jo.put("msg", "报名出错！");
		}
		return jo.toString();
    }

    @RequestMapping(value = "/{keyWord}-/search",method = RequestMethod.GET)
    public String search(HttpServletRequest request ,@PathVariable String keyWord) {
    	int xfNum = repService.countRealEstateProjectByParams(keyWord, null, null, null, null, null, null);
    	int esfNum = shhService.countSecondHandHouseByParams(null, keyWord, null, null, null, null);
    	int zfNum = rhService.countRentalHousingByParams(null, keyWord, null, null, null, null);
    	int spNum = spService.countShopByParams(keyWord, null, null, null, null, null, null);
    	int xzlNum = obService.countOfficeBuildingByParams(keyWord, null, null, null, null, null, null);
    	int cfckNum = wService.countWarehouseByParams(keyWord, null, null, null, null, null, null);
    	int totalNum = xfNum+esfNum+zfNum+spNum+xzlNum+cfckNum;
    	request.setAttribute("xfNum", xfNum);
    	request.setAttribute("esfNum", esfNum);
    	request.setAttribute("zfNum", zfNum);
    	request.setAttribute("spNum", spNum);
    	request.setAttribute("xzlNum", xzlNum);
    	request.setAttribute("cfckNum", cfckNum);
    	request.setAttribute("totalNum", totalNum);
    	return "ui/searchResult";
    }

    @RequestMapping(value = "/mi",method = RequestMethod.GET)
    public String indexMI(HttpServletRequest request, Model model) {
	User user = userService.getCurUser();
	if(user!=null){
	    String hiu = Constants.HEAD_IMG_DEFAULT_URL;
	    if (user != null && StringUtils.isNotBlank(user.getHeadImgUrl())) {
		hiu = user.getHeadImgUrl();
		hiu = aossService.addImgParams(hiu,
			Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_HEAD_IMG);
	    }
	    if (hiu.indexOf("http://") == -1
		    && hiu.indexOf(request.getContextPath()) == -1) {
		hiu = request.getContextPath() + hiu;
	    }
	    request.getSession().setAttribute("miCurrentHeadImgUrl", hiu);
	    request.getSession().setAttribute("miCurrentUserId", user.getId());
	}
	return "mi/index";
    }

    @RequestMapping(value = "/unauthorized")
    public String unauthorized() {
	return "index";
    }

    @RequestMapping(value = "/unauthenticated")
    public String unauthenticated() {
	return "index";
    }

    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest req, Model model) {
	String exceptionClassName = (String) req
		.getAttribute("shiroLoginFailure");
	String error = null;
	if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
	    error = "用户名/密码错误";
	} else if (IncorrectCredentialsException.class.getName().equals(
		exceptionClassName)) {
	    error = "用户名/密码错误";
	} else if (exceptionClassName != null) {
	    error = "其他错误：" + exceptionClassName;
	}
	model.addAttribute("error", error);
	return "login";
    }

    @RequestMapping(value = "/error_all", method = { RequestMethod.GET })
    public String errorAll(HttpServletRequest request)
	    throws ServletRequestBindingException {
	return "error_all";
    }

}
