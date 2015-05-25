package com.mimi.zfw.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import com.mimi.zfw.service.IPermissionService;
import com.mimi.zfw.service.IRoleService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.RSAUtil;

@Repository
public class InitData implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    @Resource
    private IPermissionService permissionService;
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
//		event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")
//		System.out.println("0000-"+event.getApplicationContext().getDisplayName());
		if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")){
			init();
		}
//		Root WebApplicationContext
//		WebApplicationContext for namespace 'SpringMVC-servlet'
	}
	
	private void init(){
		try {
			RSAUtil.generateKeyPair();
		} catch (Exception e) {
			e.printStackTrace();
		}
		permissionService.initPermission();
		roleService.initRole();
		userService.initUser();
	}


}
