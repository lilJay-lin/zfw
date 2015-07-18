package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.OfficeBuilding;
import com.mimi.zfw.mybatis.pojo.OfficeBuildingExample;

public interface IOfficeBuildingService extends
		IBaseService<OfficeBuilding, OfficeBuildingExample, String> {
	public void initOfficeBuilding();

	public List<OfficeBuilding> findOfficeBuildingsByParams(String keyWord,
			String region, String grossFloorArea, String type,
			String rentOrSale, String rental, String totalPrice,
			Boolean outOfDate, String orderBy, Integer targetPage, Integer pageSize);

	public int countOfficeBuildingByParams(String keyWord, String region,
			String grossFloorArea, String type, String rentOrSale,
			String rental, String totalPrice, Boolean outOfDate);

	public int countByUserId(String userId);

	public String saveCascading(OfficeBuilding ob, String imgUrls);

	public String deleteUserOBByFlag(String id);

	public String refreshUserOB(String id);

	public String updateCascading(OfficeBuilding ob, String imgUrls);

	public List<OfficeBuilding> getByUserId(String userId, Integer targetPage,
			Integer pageSize);
	
	public Map<String, String> addOfficeBuilding(OfficeBuilding officeBuilding);

	public Map<String,String> updateOfficeBuilding(OfficeBuilding officeBuilding);
	    
	public int updateBatchOfficeBuilding(String officeBuildingIds,OfficeBuilding officeBuilding);
	
	public Map<String,String> checkOfficeBuilding(OfficeBuilding officeBuilding);

	public String cleanDeadOB();

}
