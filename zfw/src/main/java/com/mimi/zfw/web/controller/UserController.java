package com.mimi.zfw.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.YinXiangMa.YinXiangMa;
import com.mimi.zfw.Constants;
import com.mimi.zfw.model.UserModel;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.MD5Util;
import com.mimi.zfw.web.support.editor.DateEditor;

@Controller
public class UserController extends BaseController {

	@Autowired
	@Qualifier("IUserService")
	private IUserService userService;

//	@RequestMapping(value = "/user/login", method = { RequestMethod.GET,
//			RequestMethod.POST })
//	public String login(HttpServletRequest request, Model model,
//			@ModelAttribute("command") UserQueryModel command) {
//
//		// 执行过滤
//		if (userService.countAll() == 0) {
//			UserModel um = new UserModel();
//			um.setEmail("admin@qq.com");
//			um.setUsername("admin");
//			um.setPassword(MD5Util.MD5("admin"));
//			um.setRegisterDate(new Date(System.currentTimeMillis()));
//			userService.save(um);
//		}
//		setCommonData(model);
//		model.addAttribute(Constants.COMMAND, command);
//		command.setPassword(MD5Util.MD5(command.getPassword()));
//		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
//		CommonPageObject<UserModel> pages = userService.strictQuery(pn,
//				Constants.DEFAULT_PAGE_SIZE, command);
//		if (pages.getItems().size() > 0) {
//			request.getSession().setAttribute(Constants.LOGIN_USER_NAME,
//					pages.getItems().get(0).getUsername());
//			request.getSession().setAttribute(Constants.LOGIN_USER_ID,
//					pages.getItems().get(0).getId());
//			return "backstage/index";
//		}
//		if ("true".equals(request.getParameter("submitAction"))) {
//			request.getSession().setAttribute(Constants.ERROR_MESSAGE, "用户或密码错误，请重新输入！");
//		} else {
//			request.getSession().setAttribute(Constants.ERROR_MESSAGE, null);
//		}
//		request.getSession().setAttribute(Constants.LOGIN_USER_NAME, null);
//		return "login";
//	}
	@RequestMapping(value = "/user", method = { RequestMethod.GET })
	public String index() {
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
	
	

//	@RequestMapping(value = "/user/login", method = { RequestMethod.GET })
//	public String toLogin(Model model) {
//		if (!model.containsAttribute(Constants.COMMAND)) {
//			model.addAttribute(Constants.COMMAND, new UserQueryModel());
//		}
//		return "ui/user/login";
//	}

	@RequestMapping(value = "/user/login", method = { RequestMethod.POST,RequestMethod.GET })
	public String login(HttpServletRequest request,Model model){
//			@ModelAttribute(Constants.COMMAND) UserQueryModel command) {
//		CommonPageObject<UserModel> pages = userService.query(0, Constants.DEFAULT_PAGE_SIZE, command);
//		if(pages.getItemTotalNum()>0){
//			request.getSession().setAttribute(Constants.LOGIN_USER_NAME,
//					pages.getItems().get(0).getUserName());
//			request.getSession().setAttribute(Constants.LOGIN_USER_ID,
//					pages.getItems().get(0).getId());
//			return "ui/user/index";
//		}
//		request.setAttribute(Constants.ERROR_MESSAGE, "用户或密码错误，请重新输入！");
////		request.getSession().setAttribute(Constants.ERROR_MESSAGE, "用户或密码错误，请重新输入！");
//		request.getSession().setAttribute(Constants.LOGIN_USER_NAME, null);
//		request.getSession().setAttribute(Constants.LOGIN_USER_ID, null);
//		return "ui/user/login";
		

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
	

//	@RequestMapping(value = "/user/logout", method = { RequestMethod.POST,RequestMethod.GET })
//	public String logout(HttpServletRequest request,Model model){
////			@ModelAttribute(Constants.COMMAND) UserQueryModel command) {
////		CommonPageObject<UserModel> pages = userService.query(0, Constants.DEFAULT_PAGE_SIZE, command);
////		if(pages.getItemTotalNum()>0){
////			request.getSession().setAttribute(Constants.LOGIN_USER_NAME,
////					pages.getItems().get(0).getUserName());
////			request.getSession().setAttribute(Constants.LOGIN_USER_ID,
////					pages.getItems().get(0).getId());
////			return "ui/user/index";
////		}
////		request.setAttribute(Constants.ERROR_MESSAGE, "用户或密码错误，请重新输入！");
//////		request.getSession().setAttribute(Constants.ERROR_MESSAGE, "用户或密码错误，请重新输入！");
////		request.getSession().setAttribute(Constants.LOGIN_USER_NAME, null);
////		request.getSession().setAttribute(Constants.LOGIN_USER_ID, null);
////		return "ui/user/login";
//		
//
//        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
//        String error = null;
//        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
//            error = "用户名/密码错误";
//        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
//            error = "用户名/密码错误";
//        } else if(exceptionClassName != null) {
//            error = "其他错误：" + exceptionClassName;
//        }
//        model.addAttribute("error", error);
//        return "ui/user/login";
//	}
	
	

	@RequestMapping(value = "/user/register", method = { RequestMethod.GET })
	public String toRegister(Model model) {
		if (!model.containsAttribute(Constants.COMMAND)) {
			model.addAttribute(Constants.COMMAND, new UserModel());
		}
		return "ui/user/register";
	}

	@RequestMapping(value = "/user/register", method = { RequestMethod.POST })
	public String register(HttpServletRequest request,
			@ModelAttribute(Constants.COMMAND) UserModel command) {
		Date nowDate = new Date(System.currentTimeMillis());
		command.setRegisterDate(nowDate);
		String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
		SimpleHash sh = new SimpleHash("md5", command.getPassword(), command.getUserName() + salt, 2);
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
			model.addAttribute(Constants.COMMAND, new UserModel());
		}
		setCommonData(model);
		return "user/changePWD";
	}

	@RequestMapping(value = "/user/changePWD", method = { RequestMethod.POST })
	public String changePWD(HttpServletRequest request, Model model,
			@ModelAttribute("command") @Valid UserModel command,
			BindingResult result) {
		command = userService.get(command.getId());
		if (StringUtils.equals(
				MD5Util.MD5(request.getParameter("oldPassword")),
				command.getPassword())) {
			if (StringUtils.equals(request.getParameter("newPassword"),
					request.getParameter("newPasswordRP"))) {
				command.setPassword(MD5Util.MD5(request
						.getParameter("newPassword")));
				userService.update(command);
				return "redirect:/user/success";
			}
		}
		return "user/changePWD";
	}

	@RequestMapping(value = "/user/success", method = { RequestMethod.GET })
	public String success() {
		return "user/success";
	}

//	@RequestMapping(value = "/user/login", method = { RequestMethod.GET,
//			RequestMethod.POST })
//	public String loginWithVerification(HttpServletRequest request,HttpServletResponse response, Model model,
//			@ModelAttribute("command") UserQueryModel command){
//		  
//			if (verificationCodePassWithYXM(request)) { 
//            	// 执行过滤
//         		if (userService.countAll() == 0) {
//         			UserModel um = new UserModel();
//         			um.setEmail("admin@qq.com");
//         			um.setUserName("admin");
//         			um.setPassword(MD5Util.MD5("admin"));
//         			um.setRegisterDate(new Date(System.currentTimeMillis()));
//         			userService.save(um);
//         		}
//         		setCommonData(model);
//         		model.addAttribute(Constants.COMMAND, command);
//         		command.setPassword(MD5Util.MD5(command.getPassword()));
//         		int pn = ServletRequestUtils.getIntParameter(request, "pn", 1);
//         		CommonPageObject<UserModel> pages = userService.strictQuery(pn,
//         				Constants.DEFAULT_PAGE_SIZE, command);
//         		if (pages.getItems().size() > 0) {
//         			request.getSession().setAttribute(Constants.LOGIN_USER_NAME,
//         					pages.getItems().get(0).getUserName());
//         			request.getSession().setAttribute(Constants.LOGIN_USER_ID,
//         					pages.getItems().get(0).getId());
//         			return "backstage/index";
//         		}
//         		if ("true".equals(request.getParameter("submitAction"))) {
//         			request.getSession().setAttribute(Constants.ERROR_MESSAGE, "用户或密码错误，请重新输入！");
//         		} else {
//         			request.getSession().setAttribute(Constants.ERROR_MESSAGE, null);
//         		}
//         		request.getSession().setAttribute(Constants.LOGIN_USER_NAME, null);
//         		return "login";
//            } else {  
//                request.getSession().setAttribute(Constants.ERROR_MESSAGE, "验证码错误");  
//                request.getSession().setAttribute(Constants.LOGIN_USER_NAME, null);  
//         		return "login";
//            }
//	}
	
	private boolean verificationCodePassWithYXM(HttpServletRequest request){
		String result;
		try {
			result = YinXiangMa.Check_Answer("8d5d90f3b002f137e92e5ea31dffde6c", request.getParameter("YinXiangMa_challenge"), request.getParameter("YXM_level"), request.getParameter("YXM_input_result"));

			if (result=="true"){
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
//	private boolean verificationCodePass(HttpServletRequest request){
//		String validateCode = ServletRequestUtils.getStringParameter(request, Constants.VALIDATE_CODE_KEY,"").trim();  
//
//        String validateCodeInSession = (String) request.getSession()  
//                .getAttribute(Constants.VALIDATE_CODE_KEY); 
//        
//        request.getSession().removeAttribute(Constants.VALIDATE_CODE_KEY); 
//        return validateCodeInSession != null && validateCode != null  
//                && validateCode.equalsIgnoreCase(validateCodeInSession);
//	}
	
	

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

}
