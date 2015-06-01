package com.mimi.zfw.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.service.IRoleService;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Resource
	private IRoleService roleService;
	
	@RequestMapping("")
    	public String toIndex(HttpServletRequest request,Model model){
	    	long b =  new Date().getTime();
		model.addAttribute("roles", roleService.listAll());
		long e = new Date().getTime();
		model.addAttribute("time", (e-b)*0.001);
		return "list";
	}

	@RequestMapping("batchSave")
    	public String batchSave(HttpServletRequest request,Model model){
	    	long b =  new Date().getTime();
	    	List<Role> roles = new ArrayList<Role>();
	    	for(int i=0;i<20;i++){
		    String id = UUID.randomUUID().toString();
		    String name = "name_"+i+id;
		    String description = "description_"+i;
	    	    Role role = new Role();
	    	    role.setDescription(description);
	    	    role.setId(id);
	    	    role.setName(name);
	    	    roles.add(role);
	    	}
	    	roleService.saveBatch(roles);
		model.addAttribute("roles", roleService.listAll());
		long e = new Date().getTime();
		model.addAttribute("time", (e-b)*0.001);
		return "list";
	}

	@RequestMapping("test")
    	public String toTest(HttpServletRequest request,Model model){
		return "index";
	}

}
