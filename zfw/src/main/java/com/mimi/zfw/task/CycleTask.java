package com.mimi.zfw.task;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mimi.zfw.service.ISHHFloorPriceLinearFunctionService;
  
@Component  
public class CycleTask {  
	private static final Logger LOG = LoggerFactory.getLogger(CycleTask.class);  
    @Resource
    private ISHHFloorPriceLinearFunctionService shhfplfService;
	
//    @Scheduled(cron="0 * * * * ? ") //每天凌晨2点执行	
    @Scheduled(cron="0 0 2 * * ? ") //每天凌晨2点执行
    public void computeFunction(){  
    	try{
    		LOG.info("------开始计算二手房评估线性回归方程------");
        	String errorStr = shhfplfService.resetFunction();
        	if(StringUtils.isNotBlank(errorStr)){
        		LOG.info("二手房评估线性回归方程计算请求中止-"+errorStr);
        	}else{
        		LOG.info("------二手房评估线性回归方程计算完成------");
        		shhfplfService.setLastSuccess(true);
        		shhfplfService.setLastEnd(new Date(System.currentTimeMillis()));
        	}
    	}catch(Exception e){
    		LOG.error("------二手房评估线性回归方程计算失败------",e);
    		shhfplfService.setLastSuccess(false);
    		shhfplfService.setLastEnd(new Date(System.currentTimeMillis()));
    	}
    }  

//    @Scheduled(cron="0/10 * * * * ? ") //间隔5秒执行  
//    public void taskCycle2(){  
//    	try{
//    		for(int i=5;;i--){
//    			int k  = 10/i;
//    		}
//    	}catch(Exception e){
//    		LOG.error("出错", e);
//    	}
//    }  
}  