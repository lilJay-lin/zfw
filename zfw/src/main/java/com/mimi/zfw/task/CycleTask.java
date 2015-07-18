package com.mimi.zfw.task;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mimi.zfw.service.IOfficeBuildingService;
import com.mimi.zfw.service.IRentalHousingService;
import com.mimi.zfw.service.ISHHFloorPriceLinearFunctionService;
import com.mimi.zfw.service.ISecondHandHouseService;
import com.mimi.zfw.service.IShopService;
import com.mimi.zfw.service.IWarehouseService;
import com.mimi.zfw.util.RSAUtil;

@Component
public class CycleTask {
	private static final Logger LOG = LoggerFactory.getLogger(CycleTask.class);
	@Resource
	private ISHHFloorPriceLinearFunctionService shhfplfService;

	@Resource
	private ISecondHandHouseService shhService;

	@Resource
	private IRentalHousingService rhService;

	@Resource
	private IShopService shopService;

	@Resource
	private IOfficeBuildingService obService;

	@Resource
	private IWarehouseService warehouseService;

	// @Scheduled(cron="0 * * * * ? ") //每天凌晨2点执行
	@Scheduled(cron = "0 0 2 * * ? ")
	// 每天凌晨2点执行
	public void computeFunction() {
		try {
			LOG.info("------开始计算二手房评估线性回归方程------");
			String errorStr = shhfplfService.resetFunction();
			if (StringUtils.isNotBlank(errorStr)) {
				LOG.info("二手房评估线性回归方程计算请求中止-" + errorStr);
			} else {
				LOG.info("------二手房评估线性回归方程计算完成------");
				shhfplfService.setLastSuccess(true);
				shhfplfService.setLastEnd(new Date(System.currentTimeMillis()));
			}
		} catch (Exception e) {
			LOG.error("------二手房评估线性回归方程计算失败------", e);
			shhfplfService.setLastSuccess(false);
			shhfplfService.setLastEnd(new Date(System.currentTimeMillis()));
		}
	}

	@Scheduled(cron = "0 0 3 * * ? ")
	// 每天凌晨2点执行
	public void cleanDeadInfo() {
		try {
			LOG.info("------开始清理过期二手房------");
			String errorStr = shhService.cleanDeadSHH();
			if (StringUtils.isNotBlank(errorStr)) {
				LOG.info("过期二手房清理请求中止-" + errorStr);
			} else {
				LOG.info("------过期二手房清理完成------");
			}
		} catch (Exception e) {
			LOG.error("------过期二手房清理失败------", e);
		}

		try {
			LOG.info("------开始清理过期租房------");
			String errorStr = rhService.cleanDeadRH();
			if (StringUtils.isNotBlank(errorStr)) {
				LOG.info("过期租房清理请求中止-" + errorStr);
			} else {
				LOG.info("------过期租房清理完成------");
			}
		} catch (Exception e) {
			LOG.error("------过期租房清理失败------", e);
		}

		try {
			LOG.info("------开始清理过期商铺------");
			String errorStr = shopService.cleanDeadShop();
			if (StringUtils.isNotBlank(errorStr)) {
				LOG.info("过期商铺清理请求中止-" + errorStr);
			} else {
				LOG.info("------过期商铺清理完成------");
			}
		} catch (Exception e) {
			LOG.error("------过期商铺清理失败------", e);
		}

		try {
			LOG.info("------开始清理过期写字楼------");
			String errorStr = obService.cleanDeadOB();
			if (StringUtils.isNotBlank(errorStr)) {
				LOG.info("过期写字楼清理请求中止-" + errorStr);
			} else {
				LOG.info("------过期写字楼清理完成------");
			}
		} catch (Exception e) {
			LOG.error("------过期写字楼清理失败------", e);
		}

		try {
			LOG.info("------开始清理过期厂房仓库------");
			String errorStr = warehouseService.cleanDeadWarehouse();
			if (StringUtils.isNotBlank(errorStr)) {
				LOG.info("过期厂房仓库清理请求中止-" + errorStr);
			} else {
				LOG.info("------过期厂房仓库清理完成------");
			}
		} catch (Exception e) {
			LOG.error("------过期厂房仓库清理失败------", e);
		}
	}

	@Scheduled(cron = "0 0 2 * * ? ")
	// 每天凌晨2点执行
	public void resetRSA() {
		try {
			LOG.info("开始生产密匙");
			RSAUtil.generateKeyPair();
			LOG.info("生产密匙完成");
		} catch (Exception e) {
			LOG.error("生成密钥出错！", e);
		}
	}

	// @Scheduled(cron="0/10 * * * * ? ") //间隔5秒执行
	// public void taskCycle2(){
	// try{
	// for(int i=5;;i--){
	// int k = 10/i;
	// }
	// }catch(Exception e){
	// LOG.error("出错", e);
	// }
	// }
}