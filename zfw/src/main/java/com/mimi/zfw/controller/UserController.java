package com.mimi.zfw.controller;

import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    	
    	
	@RequestMapping(value = "/user", method = { RequestMethod.GET })
	public String index() {
//	    return "list";
		return "ui/user/index";
	}

	@RequestMapping(value = "/mi/user", method = { RequestMethod.GET })
	public String indexMI() {
//	    return "list";
		return "mi/user/index";
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
	
//	@RequestMapping(value = "/user/login", method = { RequestMethod.POST,RequestMethod.GET })
//	public String login(HttpServletRequest request,Model model){
//		
//
//            String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
//            String error = null;
//            if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
//                error = "用户名/密码错误";
//            } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
//                error = "用户名/密码错误";
//            } else if(exceptionClassName != null) {
//                error = "其他错误：" + exceptionClassName;
//            }
//            model.addAttribute("error", error);
//            return "ui/user/login";
//	}

	
	@RequestMapping(value = "/user/login", method = { RequestMethod.GET })
	public String toLogin(HttpServletRequest request,Model model){
	    	setRSAParams(model);
	    	setGeetestId(model);
		return "ui/user/login";
	}

	@RequestMapping(value = "/mi/user/login", method = { RequestMethod.GET })
    	public String toMILogin(HttpServletRequest request,Model model){
	    	setRSAParams(model);
	    	setGeetestId(model);
        	return "mi/user/login";
	}
	
	@RequestMapping(value = "/mi/user/login", method = { RequestMethod.POST })
	public String MILogin(HttpServletRequest request,Model model){
		loginAction(request, model);
	    	setRSAParams(model);
	    	setGeetestId(model);
		return "mi/user/login";
	}
	
	@RequestMapping(value = "/user/login", method = { RequestMethod.POST })
	public String login(HttpServletRequest request,Model model){
		loginAction(request, model);
	    	setRSAParams(model);
	    	setGeetestId(model);
		return "ui/user/login";
	}
	
	private void setRSAParams(Model model){
		RSAPublicKey rpu = RSAUtil.getCurrentPublicKey();
//		request.setAttribute("modulus", rpu.getModulus().toString(16));
//		request.setAttribute("publicExponent", rpu.getPublicExponent().toString(16));
		model.addAttribute("publicExponent",rpu.getPublicExponent().toString(16) );
		model.addAttribute("modulus", rpu.getModulus().toString(16));
	}
	
	private void setGeetestId(Model model){
	    model.addAttribute("geetestId", Constants.GEETEST_ID);
	}
	
	private void loginAction(HttpServletRequest request,Model model){
		String exceptionClassName = (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
                String error = null;
                if(IncorrectCaptchaException.class.getName().equals(exceptionClassName)){
                	error = "验证码错误";
                }else if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
                    error = "用户名/密码错误";
                } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                    error = "用户名/密码错误";
                } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
                	error = "账号已锁定";
                } else if(exceptionClassName != null) {
//                    error = "其他错误：" + exceptionClassName;
                    error = "登录失败，请稍后尝试";
                }
                model.addAttribute("error", error);
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
	public String register(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute(Constants.COMMAND) User command){
//		Date nowDate = new Date(System.currentTimeMillis());
//		command.setId(UUID.randomUUID().toString());
//		command.setCreateDate(nowDate);
//		String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
//		SimpleHash sh = new SimpleHash("md5", command.getPassword(), command.getId() + salt, 2);
////		SimpleHash sh = new SimpleHash("md5", command.getPassword());
//		command.setPassword(sh.toString());
//		command.setSalt(salt);
////		MessageDigest.getInstance("md5").update(command.getPassword().getBytes())
////		System.out.println(sh.toString()+"_"+MD5Util.MD5(MD5Util.MD5(command.getPassword()+command.getUserName()+salt)));
//		userService.save(command);
	   String password = "";
	try {
	    password = RSAUtil.getResult(
	    	    request.getParameter("publicExponent"),
	    	    request.getParameter("modulus"),command.getPassword());
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	   command.setPassword(password);
	    boolean gtResult = false;
		if (geetest.resquestIsLegal(request)) {
		    gtResult = geetest.validateRequest(request);
		} else {
		    gtResult = false;
		}

		if (!gtResult) {
		    throw new IncorrectCaptchaException("验证码错误！");
		}
	    command = userService.saveOriginUser(command);
	    userService.login(command.getName(),password);
		return "ui/user/index";
	}
	
	private void setCommonData(Model model) {
		// 设置通用属性
	}

	@RequestMapping(value = "/user/changePWD", method = { RequestMethod.GET })
	public String toChangePWD(Model model) {

		if (!model.containsAttribute(Constants.COMMAND)) {
			model.addAttribute(Constants.COMMAND, new User());
		}
		setCommonData(model);
		return "user/changePWD";
	}

	@RequestMapping(value = "/user/changePWD", method = { RequestMethod.POST })
	public String changePWD(HttpServletRequest request, Model model,
			@ModelAttribute("command") @Valid User command,
			BindingResult result) {
//		command = userService.get(command.getId());
//		if (StringUtils.equals(
//				MD5Util.MD5(request.getParameter("oldPassword")),
//				command.getPassword())) {
//			if (StringUtils.equals(request.getParameter("newPassword"),
//					request.getParameter("newPasswordRP"))) {
//				command.setPassword(MD5Util.MD5(request
//						.getParameter("newPassword")));
//				userService.update(command);
//				return "redirect:/user/success";
//			}
//		}
		return "user/changePWD";
	}


	    @RequestMapping(value = "/public/json/user/checkLoginNameValid", method = RequestMethod.GET)
	    public @ResponseBody
	    Object loginNameValid(String name) {
//		JSONArray joarr = new JSONArray();
////		JSONArray.fromObject(params);
//		for(int i=0;i<1;i++){
//			JSONObject tjo = new JSONObject();
//			tjo.put("address", "\u5e7f\u4e1c\u7701\u8087\u5e86\u5e02\u7aef\u5dde\u533a\u666f\u5fb7\u8def18\u53f7");
//			tjo.put("city", "\u8087\u5e86\u5e02");
//			tjo.put("content_id", 1);
//			tjo.put("title", "\u661f\u6e56\u540d\u4ed5\u4f1a");
//			tjo.put("location", "[112.495924,23.068761]");
//			joarr.add(tjo);
//		}
//		boolean nameValid = userService.checkEamilFormat(name);
//		if(userService.checkEamilFormat(name)){
//		    User user = userService.findByEmail(name);
//		    if(user!=null){
//			nameValid = false;
//		    }
//		}
//		}else if(userService.checkNameFormat(name))
//		if()
//		|| userService.checkNameFormat(name) || userService.checkPhoneNumFormat(name);
//		String msg = "";
//		if(nameValid){
//		    User user = userService.findByUsername(name);
//		    if(user!=null){
//			nameValid = false;
//		    }
//		}else{
//		    msg = "登录名格式有误";
//		}
		JSONObject jo = new JSONObject();
		jo.put("success", true);
		jo.put("value", name);
//		if(!nameValid){
//		    jo.put("msg", "");
//		}
//		jo.put("contents", joarr);
		System.out.println(jo.toString());
		return jo.toString();
//		return '{"status":0,"total":1,"size":1,"contents":[{"address":"\u5e7f\u4e1c\u7701\u8087\u5e86\u5e02\u7aef\u5dde\u533a\u666f\u5fb7\u8def18\u53f7","city":"\u8087\u5e86\u5e02","content_id":1,"create_time":1421809324,"district":"\u7aef\u5dde\u533a","geotable_id":92345,"location":[112.495924,23.068761],"modify_time":1421891993,"on_sale":0,"price":9500,"province":"\u5e7f\u4e1c\u7701","tags":"\u697c\u76d8","title":"\u661f\u6e56\u540d\u4ed5\u4f1a","uid":627101714,"coord_type":3,"type":0,"distance":0,"weight":0}]}';
//		return APIHttpClient.httpClientPost(url);
	    }

	    @RequestMapping(value = "/public/json/user/checkPhoneNumValid", method = RequestMethod.GET)
	    public @ResponseBody
	    Object phoneNumValid(String name) {
		JSONObject jo = new JSONObject();
		jo.put("success", true);
		jo.put("value", name);
		System.out.println(jo.toString());
		return jo.toString();
	    }
	@RequestMapping(value = "/user/success", method = { RequestMethod.GET })
	public String success() {
		return "user/success";
	}

}
