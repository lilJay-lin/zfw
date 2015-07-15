package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunityExample;

public interface IResidenceCommunityService extends
		IBaseService<ResidenceCommunity, ResidenceCommunityExample, String> {
	public void initResidenceCommunicity();

	public List<ResidenceCommunity> findResidenceCommunitiesByParams(
			String bound, String region, String shhTotalPrice,
			Integer shhRoomNum, String shhRoomGrossFloorArea, String rhRental,
			Integer rhRoomNum, String rhRoomGrossFloorArea);

	public List<ResidenceCommunity> findByName(String name);

	public ResidenceCommunity getByName(String name);

	public ResidenceCommunity refreshResidenceCommunity(String id,
			boolean onShh, boolean onRh);

	public List<ResidenceCommunity> findByParams(String name, Boolean active,
			Integer targetPage, Integer pageSize);

	public int countByParams(String name, Boolean active);

	public Map<String, String> addRC(ResidenceCommunity rc);

	public Map<String, String> updateRC(ResidenceCommunity rc);

	public Map<String, String> batchDel(String rcIds);
}
