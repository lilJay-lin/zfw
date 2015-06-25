package com.mimi.zfw.service;

import java.util.List;

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

	public ResidenceCommunity refreshResidenceCommunity(String id, boolean onShh,
			boolean onRh);
}
