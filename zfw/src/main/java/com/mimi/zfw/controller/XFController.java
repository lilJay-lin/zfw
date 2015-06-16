package com.mimi.zfw.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.RealEstateProject;

@Controller
public class XFController {

	@RequestMapping(value = "/xf", method = { RequestMethod.GET })
	public String xf(HttpServletRequest request) {
		return "ui/xf/index";
	}

	@RequestMapping(value = "/xf/map", method = { RequestMethod.GET })
	public String map(HttpServletRequest request) {
		return "ui/xf/map";
	}

	@RequestMapping(value = "/xf/{id}/detail", method = { RequestMethod.GET })
	public String toDetail(Model model, @PathVariable String id) {
		RealEstateProject rep = new RealEstateProject();
		return "ui/xf/detail";
	}

	@RequestMapping(value = "/hx/{realEstateProjectId}/{id}/detail", method = { RequestMethod.GET })
	public String toHXDetail(Model model, @PathVariable String realEstateProjectId, @PathVariable String id) {
		RealEstateProject rep = new RealEstateProject();
		return "ui/xf/hxDetail";
	}


}
