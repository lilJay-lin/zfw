package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.OfficeBuilding;
import com.mimi.zfw.mybatis.pojo.OfficeBuildingExample;

public interface IOfficeBuildingService extends
		IBaseService<OfficeBuilding, OfficeBuildingExample, String> {
	public void initOfficeBuilding();

	public List<OfficeBuilding> findOfficeBuildingsByParams(String keyWord,
			String region, String grossFloorArea, String type,
			String rentOrSale, String rental, String totalPrice,
			String orderBy, Integer targetPage, Integer pageSize);

	public int countOfficeBuildingByParams(String keyWord, String region,
			String grossFloorArea, String type, String rentOrSale,
			String rental, String totalPrice);

	public int countByUserId(String userId);

	public String saveCascading(OfficeBuilding ob, String imgUrls);

	public String deleteUserOBByFlag(String id);

	public String refreshUserOB(String id);

	public String updateCascading(OfficeBuilding ob, String imgUrls);

	public List<OfficeBuilding> getByUserId(String userId, Integer targetPage,
			Integer pageSize);
}
