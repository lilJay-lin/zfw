package com.mimi.zfw.controller;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mimi.zfw.Constants;
import com.mimi.zfw.pojo.User;
import com.mimi.zfw.service.IUserService;

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
	
	@RequestMapping(value = "/user/login", method = { RequestMethod.POST,RequestMethod.GET })
	public String login(HttpServletRequest request,Model model){
		

            String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
            String error = null;
            if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
                error = "用户名/密码错误";
            } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                error = "用户名/密码错误";
            } else if(exceptionClassName != null) {
                error = "其他错误：" + exceptionClassName;
            }
            model.addAttribute("error", error);
            return "ui/user/login";
	}
	
	

	@RequestMapping(value = "/user/register", method = { RequestMethod.GET })
	public String toRegister(Model model) {
		if (!model.containsAttribute(Constants.COMMAND)) {
			model.addAttribute(Constants.COMMAND, new User());
		}
		return "ui/user/register";
	}

	@RequestMapping(value = "/user/register", method = { RequestMethod.POST })
	public String register(HttpServletRequest request,
			@ModelAttribute(Constants.COMMAND) User command) {
		Date nowDate = new Date(System.currentTimeMillis());
		command.setId(UUID.randomUUID().toString());
		command.setCreatedate(nowDate);
		String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
		SimpleHash sh = new SimpleHash("md5", command.getPassword(), command.getName() + salt, 2);
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
