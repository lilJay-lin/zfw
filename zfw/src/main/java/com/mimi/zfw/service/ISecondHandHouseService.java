package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.SecondHandHouse;
import com.mimi.zfw.mybatis.pojo.SecondHandHouseExample;

public interface ISecondHandHouseService extends
		IBaseService<SecondHandHouse, SecondHandHouseExample, String> {

	List<SecondHandHouse> findSecondHandHousesByParams(
			String residenceCommunityId, String keyWord, String region,
			String totalPrice, Integer roomNum, String grossFloorArea,
			String orderBy, Integer targetPage, Integer pageSize);

	int countSecondHandHouseByParams(String residenceCommunityId,
			String keyWord, String region, String totalPrice, Integer roomNum,
			String grossFloorArea, String orderBy);
}
