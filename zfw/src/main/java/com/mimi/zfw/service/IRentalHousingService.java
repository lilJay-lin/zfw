package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.RentalHousing;
import com.mimi.zfw.mybatis.pojo.RentalHousingExample;

public interface IRentalHousingService extends
		IBaseService<RentalHousing, RentalHousingExample, String> {

	List<RentalHousing> findRentalHousingsByParams(String residenceCommunityId,
			String keyWord, String region, String rental, Integer roomNum,
			String grossFloorArea, String orderBy, Integer targetPage,
			Integer pageSize);

	int countRentalHousingByParams(String residenceCommunityId, String keyWord,
			String region, String rental, Integer roomNum,
			String grossFloorArea, String orderBy);
}
