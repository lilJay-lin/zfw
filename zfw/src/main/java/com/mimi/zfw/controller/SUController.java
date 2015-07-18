package com.mimi.zfw.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.zfw.service.INameListService;

@Controller
public class SUController {
	@Resource
	private INameListService nlService;

    @RequiresPermissions("su:view")
	@RequestMapping(value = "/mi/su", method = { RequestMethod.GET })
	public String su(HttpServletRequest request) {
		return "mi/su/index";
	}

    @RequiresPermissions("su:update")
	@RequestMapping(value = "/mi/su/edit", method = { RequestMethod.POST })
	public @ResponseBody
	Object edit(HttpServletRequest request, Boolean showSignUpForm,
			String singUpFormTitle) {
		JSONObject jo = new JSONObject();
		if (showSignUpForm != null) {
			nlService.setShowSignUpForm(showSignUpForm);
			jo.put("showSignUpForm", showSignUpForm);
		}
		if (StringUtils.isNotBlank(singUpFormTitle)) {
			nlService.setSingUpFormTitle(singUpFormTitle);
			jo.put("singUpFormTitle", singUpFormTitle);
		}
		jo.put("success", true);
		return jo.toString();
	}

    @RequiresPermissions("su:view")
	@RequestMapping(value = "/mi/su/detail", method = { RequestMethod.GET })
	public @ResponseBody
	Object detail(HttpServletRequest request, Boolean showSignUpForm,
			String singUpFormTitle) {
		JSONObject jo = new JSONObject();
		jo.put("showSignUpForm", nlService.isShowSignUpForm());
		jo.put("singUpFormTitle", nlService.getSingUpFormTitle());
		jo.put("success", true);
		return jo.toString();
	}
}
