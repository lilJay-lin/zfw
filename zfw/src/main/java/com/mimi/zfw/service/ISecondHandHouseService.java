package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

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
			String grossFloorArea);

	List<SecondHandHouse> getByUserId(String userId, Integer targetPage,
			Integer pageSize);

	int countByUserId(String userId);

	String saveCascading(SecondHandHouse shh, String imgUrls);

	String deleteUserSHHByFlag(String id);

	String refreshUserSHH(String id);

	String updateCascading(SecondHandHouse shh, String imgUrls);

	public List<SecondHandHouse> findByParams(String name, String rcId,
			Integer targetPage, Integer pageSize);

	public int countByParams(String name, String rcId);

	public Map<String, String> addSHH(SecondHandHouse shh);

	public Map<String, String> updateSHH(SecondHandHouse shh);

	public Map<String, String> batchDel(String shhIds);
}
