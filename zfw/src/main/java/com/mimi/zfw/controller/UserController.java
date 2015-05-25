package com.mimi.zfw.controller;

import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.User;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.RSAUtil;
import com.mimi.zfw.web.shiro.exception.IncorrectCaptchaException;

@Controller
public class UserController {


	@Resource
	private IUserService userService;
	@RequestMapping(value = "/user", method = { RequestMethod.GET })
	public String index() {
//	    return "list";
		return "ui/user/index";
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
		return "ui/user/login";
	}

	@RequestMapping(value = "/mi/user/login", method = { RequestMethod.GET })
    	public String toMILogin(HttpServletRequest request,Model model){
	    	setRSAParams(model);
        	return "mi/user/login";
	}
	
	@RequestMapping(value = "/mi/user/login", method = { RequestMethod.POST })
	public String MILogin(HttpServletRequest request,Model model){
		loginAction(request, model);
	    	setRSAParams(model);
		return "mi/user/login";
	}
	
	@RequestMapping(value = "/user/login", method = { RequestMethod.POST })
	public String login(HttpServletRequest request,Model model){
		loginAction(request, model);
	    	setRSAParams(model);
		return "ui/user/login";
	}
	
	private void setRSAParams(Model model){
		RSAPublicKey rpu = RSAUtil.getCurrentPublicKey();
//		request.setAttribute("modulus", rpu.getModulus().toString(16));
//		request.setAttribute("publicExponent", rpu.getPublicExponent().toString(16));
		model.addAttribute("publicExponent",rpu.getPublicExponent().toString(16) );
		model.addAttribute("modulus", rpu.getModulus().toString(16));
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
                    error = "其他错误：" + exceptionClassName;
                }
                model.addAttribute("error", error);
	}
	
	

	@RequestMapping(value = "/user/register", method = { RequestMethod.GET })
	public String toRegister(Model model) {
		if (!model.containsAttribute(Constants.COMMAND)) {
			model.addAttribute(Constants.COMMAND, new User());
		    	setRSAParams(model);
		}
		return "ui/user/register";
	}

	@RequestMapping(value = "/user/register", method = { RequestMethod.POST })
	public String register(HttpServletRequest request,
			@ModelAttribute(Constants.COMMAND) User command) {
		Date nowDate = new Date(System.currentTimeMillis());
		command.setId(UUID.randomUUID().toString());
		command.setCreateDate(nowDate);
		String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
		SimpleHash sh = new SimpleHash("md5", command.getPassword(), command.getId() + salt, 2);
//		SimpleHash sh = new SimpleHash("md5", command.getPassword());
		command.setPassword(sh.toString());
		command.setSalt(salt);
//		MessageDigest.getInstance("md5").update(command.getPassword().getBytes())
//		System.out.println(sh.toString()+"_"+MD5Util.MD5(MD5Util.MD5(command.getPassword()+command.getUserName()+salt)));
		userService.save(command);
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

	@RequestMapping(value = "/user/success", method = { RequestMethod.GET })
	public String success() {
		return "user/success";
	}

}
