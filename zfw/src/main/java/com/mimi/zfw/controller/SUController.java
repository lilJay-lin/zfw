package com.mimi.zfw.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mimi.zfw.service.INameListService;

@Controller
public class SUController {
	private static final Logger LOG = LoggerFactory
			.getLogger(SUController.class);

	@Resource
	private INameListService nlService;

	@RequestMapping(value = "/mi/su", method = { RequestMethod.GET })
	public String su(HttpServletRequest request) {
		return "mi/su/index";
	}

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
