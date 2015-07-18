package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.RentalHousing;
import com.mimi.zfw.mybatis.pojo.RentalHousingExample;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;

public interface IRentalHousingService extends
		IBaseService<RentalHousing, RentalHousingExample, String> {

	List<RentalHousing> findRentalHousingsByParams(String residenceCommunityId,
			String keyWord, String region, String rental, Integer roomNum,
			String grossFloorArea, Boolean outOfDate, String orderBy, Integer targetPage,
			Integer pageSize);

	int countRentalHousingByParams(String residenceCommunityId, String keyWord,
			String region, String rental, Integer roomNum, String grossFloorArea, Boolean outOfDate);

	List<RentalHousing> getByUserId(String userId, Integer targetPage,
			Integer pageSize);

	int countByUserId(String userId);

	String saveCascading(RentalHousing rh, String imgUrls);

	String deleteUserRHByFlag(String id);

	String refreshUserRH(String id);

	String updateCascading(RentalHousing rh, String imgUrls);

	public List<RentalHousing> findByParams(String name, String rcId,
			Integer targetPage, Integer pageSize);

	public int countByParams(String name, String rcId);

	public Map<String, String> addRH(RentalHousing rh);

	public Map<String, String> updateRH(RentalHousing rh);

	public Map<String, String> batchDel(String rcId, String rhIds);
	
	public void refreshByRC(ResidenceCommunity rc);

	String cleanDeadRH();
}
