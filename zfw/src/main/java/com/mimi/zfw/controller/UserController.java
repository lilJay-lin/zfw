package com.mimi.zfw.controller;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.FileUtil;
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

//			request.getSession().setAttribute(Constants.ACCESS_PHONE_NUM,
//					phoneNum);
//			request.getSession().setAttribute(Constants.ACCESS_PHONE_CAPTCHA,
//					"414141");
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
					+ FileUtil.saveFileToServer(theFile, request.getSession()
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


	@RequestMapping(value = "/mi/users", method = { RequestMethod.GET })
	    public String miUser(HttpServletRequest request) {
			addHeadImgUrl(request);
			return "mi/user/index";
	    }
	    
	    @RequestMapping(value = "/mi/users/page/{curPage}", method = { RequestMethod.GET })
	    @ResponseBody
	    public Object miIndex(HttpServletRequest request,@PathVariable int curPage) {
		
			Object res = null;
			
			int page= curPage-1>0?curPage-1:0;
			
			String name =request.getParameter("name")==null?null:(String)request.getParameter("name");
			
			Integer pageSize = request.getParameter("pagesize")==null?Constants.DEFAULT_PAGE_SIZE:Integer.valueOf((String)request.getParameter("pagesize"));
			int rows =0;
			try {
				rows = userService.countUserByParams(name);
				List<User> items = userService.findUserByParams(name, page, pageSize);
				int totalpage = rows%pageSize ==0?rows/pageSize:(rows/pageSize + 1);
				res = getJsonObject(rows, totalpage, curPage,pageSize,items,"1", "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				res = getJsonObject(rows,0, curPage,pageSize, null,"0", "");
			}
			return res;
	    } 
	    
	    
	    @SuppressWarnings("unchecked")
			public Object getJsonObject(int rows,int totalpage,int curPage,int pageSize,List items,String rescode,String msg){
			JSONObject jo = new JSONObject();
			
			Map map = new HashedMap();
			map.put("totalrows", rows);
			map.put("curpage",curPage);
			map.put("totalpage",totalpage);
			map.put("pagesize",pageSize);
			
			jo.put("pageinfo", map);
			jo.put("items", items);
			
			jo.put("rescode", rescode);
			jo.put("msg", msg);
			
			
			return jo.toString();
	    }
}
