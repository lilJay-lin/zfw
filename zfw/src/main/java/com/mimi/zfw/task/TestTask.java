package com.mimi.zfw.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mimi.zfw.service.IRoleService;
  
@Component  
public class TestTask {  
	@Resource
	private IRoleService roleService;
	
    @Scheduled(cron="0/5 * * * * ? ") //间隔5秒执行  
    public void taskCycle(){  
	
        System.out.println("sfadfsdf"+roleService.listAll().size()+"_"+roleService.getK());  
    }  
}  