package com.mimi.zfw.listener;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.service.IAdvertisementService;
import com.mimi.zfw.service.IAssessmentItemService;
import com.mimi.zfw.service.IInformationService;
import com.mimi.zfw.service.IOfficeBuildingService;
import com.mimi.zfw.service.IPermissionService;
import com.mimi.zfw.service.IRealEstateProjectService;
import com.mimi.zfw.service.IResidenceCommunityService;
import com.mimi.zfw.service.IRoleService;
import com.mimi.zfw.service.ISHHFloorPriceLinearFunctionService;
import com.mimi.zfw.service.IShopService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.service.IWarehouseService;
import com.mimi.zfw.util.RSAUtil;

@Repository
public class InitData implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private IUserService userService;
    @Resource
    private IRoleService roleService;
    @Resource
    private IPermissionService permissionService;
    @Resource
    private IRealEstateProjectService repService;
    @Resource
    private IInformationService infoService;
    @Resource
    private IAdvertisementService adService;
    @Resource
    private IResidenceCommunityService rcService;
    @Resource
    private IAssessmentItemService aiServcie;
    @Resource
    private ISHHFloorPriceLinearFunctionService shhfplfService;
    @Resource
    private IShopService shopService;
    @Resource
    private IOfficeBuildingService obService;
    @Resource
    private IWarehouseService wService;

    @Resource
    private CCPRestSmsSDK ytxAPI;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
	// TODO Auto-generated method stub
	// event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")
	// System.out.println("0000-"+event.getApplicationContext().getDisplayName());
	if (event.getApplicationContext().getDisplayName()
		.equals("Root WebApplicationContext")) {
	    init();
	}
	// Root WebApplicationContext
	// WebApplicationContext for namespace 'SpringMVC-servlet'
    }

    private void init() {
	try {
	    RSAUtil.generateKeyPair();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	ytxAPI.init("sandboxapp.cloopen.com", "8883");
	ytxAPI.setAccount("8a48b5514d32a2a8014d95626ee147c3",
		"ce3126c41d6a4181b13fb9399db922b2");
	ytxAPI.setAppId("8a48b5514d32a2a8014d9562b2ca47c6");

//	permissionService.initPermission();
//	roleService.initRole();
//	userService.initUser();
//	repService.initRealEstateProject();
//	infoService.initInformation();
//	adService.initAdvertisement();
//	rcService.initResidenceCommunicity();
//	aiServcie.initAssessItem();
//	shopService.initShop();
//	obService.initOfficeBuilding();
	wService.initWarehouse();
//	List<ResidenceCommunity> list = rcService.listAll();
//	for(int i=0;i<list.size();i++){
//		rcService.refreshResidenceCommunity(list.get(i).getId(), true, true);
//	}
//	shhfplfService.resetFunction();
    }

}
