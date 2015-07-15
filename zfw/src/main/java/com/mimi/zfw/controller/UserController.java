package com.mimi.zfw.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IRoleService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.RSAUtil;
import com.mimi.zfw.web.captcha.GeetestLib;
import com.mimi.zfw.web.shiro.exception.IncorrectCaptchaException;

@Controller
public class UserController {
    private static final Logger LOG = LoggerFactory
	    .getLogger(UserController.class);

    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    @Resource
    private GeetestLib geetest;
    @Resource
    private CCPRestSmsSDK ytxAPI;
    @Resource
    private IAliyunOSSService aossService;

    @RequestMapping(value = "/user", method = { RequestMethod.GET })
    public String user(HttpServletRequest request) {
	addHeadImgUrl(request);
	return "ui/user/index";
    }

    @RequestMapping(value = "/user/index", method = { RequestMethod.GET })
    public String index(HttpServletRequest request) {
	addHeadImgUrl(request);
	return "ui/user/index";
    }

    private void addHeadImgUrl(HttpServletRequest request) {
	User user = userService.getCurUser();
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
	request.setAttribute("headImgUrl", hiu);
    }

    @RequestMapping(value = "/user/agreement", method = { RequestMethod.GET })
    public String agreement() {
	return "ui/user/agreement";
    }

    @RequiresPermissions("user:self")
    @RequestMapping(value = "/user/resetHeadImgUrl", method = { RequestMethod.POST })
    public @ResponseBody Object resetHeadImgUrl(String headImgUrl) {
	String error = userService.updateCurUserHeadImgUrl(headImgUrl);
	JSONObject jo = new JSONObject();
	if (StringUtils.isBlank(error)) {
	    jo.put("success", true);
	} else {
	    jo.put("success", false);
	    jo.put("msg", error);
	}
	return jo.toString();
    }

    @RequiresPermissions("user:self")
    @RequestMapping(value = "/user/resetPwd", method = { RequestMethod.GET })
    public String toResetPwd(HttpServletRequest request, Model model) {
	setRSAParams(model);
	setGeetestId(model);
	return "ui/user/resetPwd";
    }

    @RequiresPermissions("user:self")
    @RequestMapping(value = "/user/resetPwd", method = { RequestMethod.POST })
    public String resetPwd(HttpServletRequest request,
	    @ModelAttribute(Constants.COMMAND) User command, Model model) {

	request.setAttribute("phoneNum", request.getParameter("phoneNum"));

	String checkResult = checkPhoneNumAndPhoneCaptcha(request);
	if (StringUtils.isBlank(checkResult)) {
	    String password = "";
	    try {
		password = RSAUtil.getResult(
			request.getParameter("publicExponent"),
			request.getParameter("modulus"), command.getPassword());
	    } catch (Exception e) {
		LOG.error("密码解析出错！", e);
		request.setAttribute("error", "密码解析出错，请稍后重试");
		return "ui/user/resetPwd";
	    }
	    command.setPassword(password);
	    checkResult = userService.updatePassword(command);
	    if (StringUtils.isBlank(checkResult)) {
		addHeadImgUrl(request);
		return "ui/user/index";
	    }
	}
	request.setAttribute("error", checkResult);
	setGeetestId(model);
	setRSAParams(model);
	return "ui/user/resetPwd";
    }

    @RequiresPermissions("user:self")
    @RequestMapping(value = "/user/detail", method = { RequestMethod.GET })
    public String detail(HttpServletRequest request) {
	addHeadImgUrl(request);
	return "ui/user/detail";
    }

    @RequestMapping(value = "/user/login", method = { RequestMethod.GET })
    public String toLogin(HttpServletRequest request, Model model) {
	if (userService.isLogined() || userService.isRememberMe()) {
	    addHeadImgUrl(request);
	    return "ui/user/index";
	}
	setRSAParams(model);
	setGeetestId(model);
	return "ui/user/login";
    }

    @RequestMapping(value = "/user/login", method = { RequestMethod.POST })
    public String login(HttpServletRequest request, Model model) {
	if (userService.isLogined() || userService.isRememberMe()) {
	    addHeadImgUrl(request);
	    return "ui/user/index";
	}
	loginAction(request, model);
	setRSAParams(model);
	setGeetestId(model);
	return "ui/user/login";
    }

    @RequestMapping(value = "/user/captchaLogin", method = { RequestMethod.GET })
    public String toCaptchaLogin(HttpServletRequest request, Model model) {
	if (userService.isLogined() || userService.isRememberMe()) {
	    addHeadImgUrl(request);
	    return "ui/user/index";
	}
	setRSAParams(model);
	setGeetestId(model);
	model.addAttribute("loginType", Constants.LOGIN_TYPE_CAPTCHA);
	return "ui/user/login";
    }

    @RequestMapping(value = "/user/captchaLogin", method = { RequestMethod.POST })
    public String captchaLogin(HttpServletRequest request, Model model) {
	if (userService.isLogined() || userService.isRememberMe()) {
	    addHeadImgUrl(request);
	    return "ui/user/index";
	}
	String phoneNum = request.getParameter("phoneNum");
	request.setAttribute("phoneNum", phoneNum);
	String checkResult = checkPhoneNumAndPhoneCaptcha(request);
	if ("".equals(checkResult)) {
	    try {
		userService.login(phoneNum);
		addHeadImgUrl(request);
		return "ui/user/index";
	    } catch (Exception e) {
		checkResult = getErrorFromLoginExceptionName(e.getClass()
			.getName());
	    }
	}
	request.setAttribute("error", checkResult);
	setGeetestId(model);
	setRSAParams(model);
	model.addAttribute("loginType", Constants.LOGIN_TYPE_CAPTCHA);
	return "ui/user/login";
    }

    private void setRSAParams(Model model) {
	RSAPublicKey rpu = RSAUtil.getCurrentPublicKey();
	model.addAttribute("publicExponent",
		rpu.getPublicExponent().toString(16));
	model.addAttribute("modulus", rpu.getModulus().toString(16));
    }

    private void setGeetestId(Model model) {
	model.addAttribute("geetestId", Constants.GEETEST_ID);
    }

    private void loginAction(HttpServletRequest request, Model model) {
	String exceptionClassName = (String) request
		.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	String error = getErrorFromLoginExceptionName(exceptionClassName);
	model.addAttribute("error", error);
    }

    private String getErrorFromLoginExceptionName(String exceptionClassName) {
	String error = null;
	if (IncorrectCaptchaException.class.getName()
		.equals(exceptionClassName)) {
	    error = "验证码错误";
	} else if (UnknownAccountException.class.getName().equals(
		exceptionClassName)) {
	    error = "用户名/密码错误";
	} else if (IncorrectCredentialsException.class.getName().equals(
		exceptionClassName)) {
	    error = "用户名/密码错误";
	} else if (ExcessiveAttemptsException.class.getName().equals(
		exceptionClassName)) {
	    error = "账号已锁定";
	} else if (LockedAccountException.class.getName().equals(
		exceptionClassName)) {
	    error = "账号已冻结";
	} else if (exceptionClassName != null) {
	    error = "登录失败，请稍后尝试";
	}
	return error;
    }

    @RequestMapping(value = "/user/register", method = { RequestMethod.GET })
    public String toRegister(Model model) {
	if (!model.containsAttribute(Constants.COMMAND)) {
	    model.addAttribute(Constants.COMMAND, new User());
	    setRSAParams(model);
	    setGeetestId(model);
	}
	return "ui/user/register";
    }

    @RequestMapping(value = "/user/register", method = { RequestMethod.POST })
    public String register(HttpServletRequest request,
	    HttpServletResponse response,
	    @ModelAttribute(Constants.COMMAND) User command, Model model) {

	request.setAttribute("phoneNum", request.getParameter("phoneNum"));
	String checkResult = checkPhoneNumAndPhoneCaptcha(request);
	if ("".equals(checkResult)) {
	    String password = "";
	    try {
		password = RSAUtil.getResult(
			request.getParameter("publicExponent"),
			request.getParameter("modulus"), command.getPassword());
	    } catch (Exception e) {
		LOG.error("密码解析出错！", e);
		request.setAttribute("error", "密码解析出错，请稍后重试");
		return "ui/user/register";
	    }
	    command.setPassword(password);
	    command = userService.saveOriginUser(command);

	    try {
		userService.login(command.getPhoneNum(), password);
		addHeadImgUrl(request);
		return "ui/user/index";
	    } catch (Exception e) {
		LOG.error("密码登录出错！", e);
		checkResult = getErrorFromLoginExceptionName(e.getClass()
			.getName());
	    }
	}
	request.setAttribute("error", checkResult);
	setGeetestId(model);
	setRSAParams(model);
	return "ui/user/register";
    }

    private String checkPhoneNumAndPhoneCaptcha(HttpServletRequest request) {
	String result = "";
	String accessPhoneNum = (String) request.getSession().getAttribute(
		Constants.ACCESS_PHONE_NUM);
	String accessPhoneCaptcha = (String) request.getSession().getAttribute(
		Constants.ACCESS_PHONE_CAPTCHA);
	String phoneNum = (String) request.getParameter("phoneNum");
	String captcha = (String) request.getParameter("captcha");
	if (StringUtils.isBlank(accessPhoneNum)
		|| StringUtils.isBlank(accessPhoneCaptcha)) {
	    result = "未发送验证码";
	} else if (StringUtils.isBlank(phoneNum)) {
	    result = "电话号码为空";
	} else if (StringUtils.isBlank(captcha)) {
	    result = "验证码为空";
	} else if (!accessPhoneNum.equals(phoneNum)) {
	    result = "手机不一致";
	} else if (!accessPhoneCaptcha.equals(captcha)) {
	    result = "验证码错误";
	}
	return result;
    }

    @RequestMapping(value = "/public/json/user/checkLoginNameValidAndExisted", method = RequestMethod.GET)
    public @ResponseBody Object loginNameValid(String name) {
	JSONObject jo = new JSONObject();
	jo.put("value", name);
	boolean nameValid = false;
	boolean nameExisted = false;
	try {
	    if (userService.checkPhoneNumFormat(name)) {
		nameValid = true;
		User user = userService.findByPhoneNum(name);
		if (user != null) {
		    nameExisted = true;
		}
	    } else if (userService.checkNameFormat(name)) {
		nameValid = true;
		User user = userService.findByName(name);
		if (user != null) {
		    nameExisted = true;
		}
	    } else if (userService.checkEamilFormat(name)) {
		nameValid = true;
		User user = userService.findByEmail(name);
		if (user != null) {
		    nameExisted = true;
		}
	    }
	    jo.put("success", true);
	} catch (Exception e) {
	    LOG.error("校验登录名出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "校验出错！");
	}
	jo.put("valid", nameValid);
	jo.put("existed", nameExisted);
	return jo.toString();
    }

    @RequestMapping(value = "/public/json/user/checkPhoneNumValidAndExisted", method = RequestMethod.GET)
    public @ResponseBody Object phoneNumValid(String name) {
	JSONObject jo = new JSONObject();
	jo.put("value", name);
	boolean nameValid = false;
	boolean nameExisted = false;
	try {
	    if (userService.checkPhoneNumFormat(name)) {
		nameValid = true;
		User user = userService.findByPhoneNum(name);
		if (user != null) {
		    nameExisted = true;
		}
	    }
	    jo.put("success", true);
	} catch (Exception e) {
	    LOG.error("校验电话号码出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "校验出错！");

	}
	jo.put("valid", nameValid);
	jo.put("existed", nameExisted);
	return jo.toString();
    }

    @RequestMapping(value = "public/json/user/getPhoneCaptcha", method = RequestMethod.GET)
    public @ResponseBody Object getPhoneCaptcha(HttpServletRequest request,
	    String phoneNum) {
	JSONObject jo = new JSONObject();
	boolean result = geetest.validateRequest(request);
	if (result) {
	    int rNum = (int) (Math.random() * 999999);
	    String rNumStr = String.valueOf(rNum);
	    while (rNumStr.length() < 6) {
		rNumStr = "0" + rNumStr;
	    }
	    HashMap<String, Object> receiveMap = ytxAPI.sendTemplateSMS(
		    phoneNum, "1", new String[] { rNumStr, "44" });
	    if ("000000".equals(receiveMap.get("statusCode"))) {
		request.getSession().setAttribute(Constants.ACCESS_PHONE_NUM,
			phoneNum);
		request.getSession().setAttribute(
			Constants.ACCESS_PHONE_CAPTCHA, rNumStr);
	    } else {
		result = false;
		jo.put("msg", "发送验证码出错，请稍后重试");
	    }

	    // request.getSession().setAttribute(Constants.ACCESS_PHONE_NUM,
	    // phoneNum);
	    // request.getSession().setAttribute(Constants.ACCESS_PHONE_CAPTCHA,
	    // "414141");
	} else {
	    jo.put("msg", Constants.SMOOTH_CAPTCHA_ERROR);
	}
	jo.put("success", result);
	return jo.toString();
    }

    @RequestMapping(value = "/user/success", method = { RequestMethod.GET })
    public String success() {
	return "user/success";
    }

    @RequestMapping(value = "/mi/user", method = { RequestMethod.GET })
    public String indexMI() {
	return "mi/user/index";
    }

    @RequestMapping(value = "/mi/user/login", method = { RequestMethod.GET })
    public String toMILogin(HttpServletRequest request, Model model) {
	if (userService.isLogined()) {
	    return  "mi/index";
	}
	setRSAParams(model);
	setGeetestId(model);
	return "mi/user/login";
    }

    @RequestMapping(value = "/mi/user/login", method = { RequestMethod.POST })
    public String MILogin(HttpServletRequest request, Model model) {
	loginAction(request, model);
	setRSAParams(model);
	setGeetestId(model);
	return "mi/user/login";
    }

    @RequiresPermissions("user:self")
    @RequestMapping(value = "/user/uploadHeadImg", method = {
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
	    LOG.error("保存用户头像出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "保存图片失败");
	}
	return jo.toString();
    }
    @RequestMapping(value = "/mi/user/uploadHeadImg", method = {RequestMethod.POST })
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
	    LOG.error("保存用户头像出错！", e);
	    jo.put("success", false);
	    jo.put("msg", "保存图片失败");
	}
	return jo.toString();
    }

    @RequestMapping(value = "/mi/users", method = { RequestMethod.GET })
    public String miUser(HttpServletRequest request) {
	addHeadImgUrl(request);
	return "mi/user/index";
    }

    @RequestMapping(value = "/mi/users/page/{curPage}", method = { RequestMethod.GET })
    @ResponseBody
    public Object miIndex(HttpServletRequest request, @PathVariable int curPage) {

	Object res = null;

	int page = curPage - 1 > 0 ? curPage - 1 : 0;

	String name = request.getParameter("name") == null ? null
		: (String) request.getParameter("name");
	if(!StringUtils.isBlank(name)){
	    try {
		name = URLDecoder.decode(name,"utf-8");
	    } catch (UnsupportedEncodingException e) {
		
		JSONObject jo = new JSONObject();
		jo.put("success", false);
		jo.put("msg", "查询条件解码出错");
		LOG.error("查询角色分页，查询条件解码出错！",e);
		
		return jo.toString();
	    }
	}
	
	Integer pageSize = request.getParameter("pagesize") == null ? Constants.DEFAULT_PAGE_SIZE
		: Integer.valueOf((String) request.getParameter("pagesize"));
	int rows = 0;
	try {
	    rows = userService.countUserByParams(name);
	    List<User> items = userService.findUserByParams(name, page,
		    pageSize);
	    int totalpage = rows % pageSize == 0 ? rows / pageSize : (rows
		    / pageSize + 1);
	    res = getJsonObject(rows, totalpage, curPage, pageSize, items,
		    true, "");

	} catch (Exception e) {
	    
	    res = getJsonObject(rows, 0, curPage, pageSize, null, false, "");
	    LOG.error("查询用户分页信息出错！",e);
	}
	return res;
    }

    @RequestMapping(value = "/mi/user/{id}", method = { RequestMethod.GET })
    @ResponseBody
    public Object getUser(@PathVariable String id, HttpServletRequest request) {

	JSONObject jo = new JSONObject();

	try {
	    User user = (User) userService.get(id);
	    if (user != null) {
		jo.put("user", user);
		List<Role> relationroles = roleService.getRolesByUserId(user
			.getId());
		jo.put("relationroles", relationroles);
	    }
	} catch (Exception e) {
	    
	    jo.put("user", null);
	    jo.put("relationroles", null);
	    LOG.error("查询用户详细信息出错！",e);
	}

	return jo.toString();
    }

    @RequestMapping(value = "/mi/user/add", method = { RequestMethod.GET })
    public String toAddUser(Model model, HttpServletRequest request) {

	// return new ModelAndView("mi/users/add","user",new User());

	addHeadImgUrl(request);
	
	setRSAParams(model);

	return "mi/user/add";
    }

    @RequestMapping(value = "/mi/user", method = { RequestMethod.POST })
    @ResponseBody
    public Object addUser(HttpServletRequest request, User user, String roles,
	    String publicExponent, String modulus) {

	JSONObject jo = new JSONObject();

	if (user == null) {
	    jo.put("success", false);
	    jo.put("msg", "新增用户信息不能为空!");
	} else {
	    try {
		String password = RSAUtil.getResult(publicExponent, modulus,
			user.getPassword());
		user.setPassword(password);
	    } catch (Exception e) {
		jo.put("success", false);
		jo.put("msg", "密码解析出错，请稍后重试!");
		LOG.error("新增用户密码解析出错！",e);
	    }

	    try {
		user.setId(UUID.randomUUID().toString());

		Map<String, String> res = userService.addUser(user, roles);

		if (StringUtils.isEmpty(res.get("msg"))) {
		    jo.put("success", true);
		    jo.put("msg", "新增用户保存成功!");

		} else {
		    jo.put("success", false);
		    jo.put("msg", res.get("msg"));
		    jo.put("field", res.get("field"));
		}

	    } catch (Exception e) {
		jo.put("success", false);
		jo.put("msg", "新增用户保存失败!");
		LOG.error("新增用户保存失败！",e);
	    }

	}

	return jo.toString();
    }

    @RequestMapping(value = "/mi/user/{userid}/edit", method = { RequestMethod.GET })
    public String toUpdateUser(HttpServletRequest request,Model model, @PathVariable String userid) {
	addHeadImgUrl(request);
	model.addAttribute("userid", userid);
	setRSAParams(model);

	return "/mi/user/edit";
    }
    
    @RequestMapping(value = "/mi/user/{userid}/person", method = { RequestMethod.GET })
    public String toEidtLoginUser(HttpServletRequest request,Model model, @PathVariable String userid) {
	addHeadImgUrl(request);
	model.addAttribute("userid", userid);
	setRSAParams(model);

	return "/mi/user/person";
    }
    
    @RequestMapping(value = "/mi/user/{userid}/detail", method = { RequestMethod.GET })
    public String toViewUser(Model model, @PathVariable String userid) {

	model.addAttribute("userid", userid);
	setRSAParams(model);

	return "/mi/user/detail";
    }

    @RequestMapping(value = "/mi/user/{userid}", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateUser(HttpServletRequest request, User user,
	    @PathVariable String userid, String addroles, String delroles,
	    String publicExponent, String modulus) {

	JSONObject jo = new JSONObject();
	try {

	    try {
		String password = RSAUtil.getResult(publicExponent, modulus,
			user.getPassword());
		user.setPassword(password);
	    } catch (Exception e) {
		jo.put("success", false);
		jo.put("msg", "密码解析出错，请稍后重试!");
		LOG.error("更新用户信息，密码解析出错！",e);
	    }
	    
//	    MultipartFile file = request.getParameter("file")==null?null:(MultipartFile)request.getAttribute("addroles");

	    Map<String, String> res = userService.updateUser(user, addroles,
		    delroles);

	    if (StringUtils.isEmpty(res.get("msg"))) {
		if(StringUtils.equals(user.getId(), userService.getCurUserId())){
		    request.getSession().setAttribute("miCurrentHeadImgUrl", user.getHeadImgUrl());
		    request.getSession().setAttribute("miCurrentUserId", user.getId());
		}
		jo.put("success", true);
		jo.put("msg", "更新用户成功!");

	    } else {
		jo.put("success", false);
		jo.put("msg", res.get("msg"));
		jo.put("field", res.get("field"));
	    }

	} catch (Exception e) {
	    jo.put("success", false);
	    jo.put("msg", "更新用户失败!");
	    LOG.error("更新用户信息失败！",e);
	}

	return jo.toString();
    }

    @RequestMapping(value = "/mi/users", method = { RequestMethod.POST })
    @ResponseBody
    public Object updateBatchUser(HttpServletRequest request, User user,
	    String userids) {
	JSONObject jo = new JSONObject();
	boolean delflag = user.getDelFlag() == null ?false:user.getDelFlag();
	String success = delflag==true?"用户删除成功":"用户更新成功";
	String error = delflag==true?"用户删除失败":"用户更新失败";
	
	try {
	    userService.updateBatchUser(userids, user);
	    
	    jo.put("success", true);
	    jo.put("msg", success);
	} catch (Exception e) {
	    jo.put("success", false);
	    jo.put("msg",error);
	    LOG.error(error,e);
	}

	return jo.toString();
    }

    public Object getJsonObject(int rows, int totalpage, int curPage,
	    int pageSize, List<User> items, boolean rescode, String msg) {
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
