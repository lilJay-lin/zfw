package com.mimi.zfw.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.RSAUtil;
import com.mimi.zfw.web.captcha.GeetestLib;
import com.mimi.zfw.web.shiro.exception.IncorrectCaptchaException;

@Controller
public class UserController {

    @Resource
    private IUserService userService;
    @Resource
    private GeetestLib geetest;
    @Resource
    private CCPRestSmsSDK ytxAPI;

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
    public @ResponseBody
    Object resetHeadImgUrl(String headImgUrl) {
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
	// model.addAttribute("halfPhoneNum", getHalfPhoneNum((String)
	// request.getAttribute("phoneNum")));
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
		request.setAttribute("error", "密码解析出错，请稍后重试");
		return "ui/user/resetPwd";
	    }
	    command.setPassword(password);
	    checkResult = userService.updatePassword(command);
	    if (StringUtils.isBlank(checkResult)) {
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

    @RequiresPermissions("user:view")
    @RequestMapping(value = "/user/view", method = { RequestMethod.GET })
    public String view() {
	return "ui/user/index";
    }

    @RequiresPermissions("user:add")
    @RequestMapping(value = "/user/add", method = { RequestMethod.GET })
    public String add() {
	return "ui/user/index";
    }

    @RequiresPermissions("user:del")
    @RequestMapping(value = "/user/del", method = { RequestMethod.GET })
    public String del() {
	return "ui/user/index";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/user/update", method = { RequestMethod.GET })
    public String update() {
	return "ui/user/index";
    }

    @RequestMapping(value = "/user/login", method = { RequestMethod.GET })
    public String toLogin(HttpServletRequest request, Model model) {
	if (userService.isLogined()) {
	    return "ui/user/index";
	}
	setRSAParams(model);
	setGeetestId(model);
	return "ui/user/login";
    }

    @RequestMapping(value = "/user/login", method = { RequestMethod.POST })
    public String login(HttpServletRequest request, Model model) {
	if (userService.isLogined()) {
	    return "ui/user/index";
	}
	loginAction(request, model);
	setRSAParams(model);
	setGeetestId(model);
	return "ui/user/login";
    }

    @RequestMapping(value = "/user/captchaLogin", method = { RequestMethod.GET })
    public String toCaptchaLogin(HttpServletRequest request, Model model) {
	if (userService.isLogined()) {
	    return "ui/user/index";
	}
	setRSAParams(model);
	setGeetestId(model);
	model.addAttribute("loginType", Constants.LOGIN_TYPE_CAPTCHA);
	return "ui/user/login";
    }

    @RequestMapping(value = "/user/captchaLogin", method = { RequestMethod.POST })
    public String captchaLogin(HttpServletRequest request, Model model) {
	if (userService.isLogined()) {
	    return "ui/user/index";
	}
	String phoneNum = request.getParameter("phoneNum");
	request.setAttribute("phoneNum", phoneNum);
	String checkResult = checkPhoneNumAndPhoneCaptcha(request);
	if ("".equals(checkResult)) {
	    try {
		userService.login(phoneNum);
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
	// request.setAttribute("modulus", rpu.getModulus().toString(16));
	// request.setAttribute("publicExponent",
	// rpu.getPublicExponent().toString(16));
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
	    // error = "其他错误：" + exceptionClassName;
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
	// boolean gtResult = geetest.validateRequest(request);
	// if (!gtResult) {
	// request.setAttribute("error", Constants.SMOOTH_CAPTCHA_ERROR);
	// setGeetestId(model);
	// setRSAParams(model);
	// return "ui/user/register";
	// }

	String checkResult = checkPhoneNumAndPhoneCaptcha(request);
	if ("".equals(checkResult)) {
	    String password = "";
	    try {
		password = RSAUtil.getResult(
			request.getParameter("publicExponent"),
			request.getParameter("modulus"), command.getPassword());
	    } catch (Exception e) {
		request.setAttribute("error", "密码解析出错，请稍后重试");
		return "ui/user/register";
	    }
	    command.setPassword(password);
	    command = userService.saveOriginUser(command);

	    try {
		userService.login(command.getPhoneNum(), password);
		return "ui/user/index";
	    } catch (Exception e) {
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
    public @ResponseBody
    Object loginNameValid(String name) {
	// JSONArray joarr = new JSONArray();
	// // JSONArray.fromObject(params);
	// for(int i=0;i<1;i++){
	// JSONObject tjo = new JSONObject();
	// tjo.put("address",
	// "\u5e7f\u4e1c\u7701\u8087\u5e86\u5e02\u7aef\u5dde\u533a\u666f\u5fb7\u8def18\u53f7");
	// tjo.put("city", "\u8087\u5e86\u5e02");
	// tjo.put("content_id", 1);
	// tjo.put("title", "\u661f\u6e56\u540d\u4ed5\u4f1a");
	// tjo.put("location", "[112.495924,23.068761]");
	// joarr.add(tjo);
	// }
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
	    jo.put("success", false);
	    jo.put("msg", "校验出错！");
	}
	jo.put("valid", nameValid);
	jo.put("existed", nameExisted);
	return jo.toString();
    }

    @RequestMapping(value = "/public/json/user/checkPhoneNumValidAndExisted", method = RequestMethod.GET)
    public @ResponseBody
    Object phoneNumValid(String name) {
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
	    jo.put("success", false);
	    jo.put("msg", "校验出错！");

	}
	jo.put("valid", nameValid);
	jo.put("existed", nameExisted);
	return jo.toString();
    }

    @RequestMapping(value = "public/json/user/getPhoneCaptcha", method = RequestMethod.GET)
    public @ResponseBody
    Object getPhoneCaptcha(HttpServletRequest request, String phoneNum) {
	JSONObject jo = new JSONObject();
	boolean result = geetest.validateRequest(request);
	if (result) {
	    // int rNum = (int) (Math.random() * 999999);
	    // String rNumStr = String.valueOf(rNum);
	    // while (rNumStr.length() < 6) {
	    // rNumStr = "0" + rNumStr;
	    // }
	    // HashMap<String, Object> receiveMap = ytxAPI.sendTemplateSMS(
	    // phoneNum, "1", new String[] { rNumStr, "44" });
	    // if ("000000".equals(receiveMap.get("statusCode"))) {
	    // request.getSession().setAttribute(Constants.ACCESS_PHONE_NUM,
	    // phoneNum);
	    // request.getSession().setAttribute(
	    // Constants.ACCESS_PHONE_CAPTCHA, rNumStr);
	    // } else {
	    // result = false;
	    // jo.put("msg", "发送验证码出错，请稍后重试");
	    // }

	    request.getSession().setAttribute(Constants.ACCESS_PHONE_NUM,
		    phoneNum);
	    request.getSession().setAttribute(Constants.ACCESS_PHONE_CAPTCHA,
		    "414141");
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
    public @ResponseBody
    Object upload(HttpServletRequest request,
	    @RequestParam("theFile") MultipartFile theFile) {

	JSONObject jo = new JSONObject();
	try {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.getTimeInMillis();
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH) + 1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    int hour = cal.get(Calendar.HOUR_OF_DAY);
	    String path = "/assets/upload/" + year + "/" + month + "/" + day
		    + "/" + hour + "/";
	    path = request.getContextPath()
		    + path
		    + saveFileToServer(theFile, request.getSession()
			    .getServletContext().getRealPath("/")
			    + path);
	    jo.put("imgPath", path);
	    jo.put("success", true);
	} catch (IOException e) {
	    jo.put("success", false);
	    jo.put("msg", "保存图片失败");
	}
	return jo.toString();
    }

    public String saveFileToServer(MultipartFile multifile, String path)
	    throws IOException {
	// 创建目录
	File dir = new File(path);
	if (!dir.exists()) {
	    dir.mkdirs();
	}
	String fileName = multifile.getOriginalFilename();
	fileName = UUID.randomUUID().toString()
		+ fileName.substring(fileName.lastIndexOf("."));
	// String fileName = UUID.randomUUID().toString();
	// 读取文件流并保持在指定路径
	InputStream inputStream = multifile.getInputStream();
	OutputStream outputStream = new FileOutputStream(path + fileName);
	byte[] buffer = multifile.getBytes();
	int bytesum = 0;
	int byteread = 0;
	while ((byteread = inputStream.read(buffer)) != -1) {
	    bytesum += byteread;
	    outputStream.write(buffer, 0, byteread);
	    outputStream.flush();
	}
	outputStream.close();
	inputStream.close();

	return fileName;
    }

    public static void main(String args[]) {
	for (int i = 0; i < 10; i++) {
	    int rNum = (int) (Math.random() * 999999);
	    String rNumStr = String.valueOf(rNum);
	    while (rNumStr.length() < 6) {
		rNumStr = "0" + rNumStr;
	    }
	    System.out.println(rNumStr);
	}
    }

}
