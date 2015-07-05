package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.RealEstateProject;
import com.mimi.zfw.mybatis.pojo.RealEstateProjectExample;

public interface IRealEstateProjectService extends
		IBaseService<RealEstateProject, RealEstateProjectExample, String> {
	public void initRealEstateProject();

	public List<RealEstateProject> findRealEstateProjectByParams(
			String keyWord, String region, String averagePrice,
			Integer roomNum, String grossFloorArea, String saleStatus,
			String bound, String orderBy, int targetPage, int pageSize);

	public int countRealEstateProjectByParams(String keyWord, String region,
			String averagePrice, Integer roomNum, String grossFloorArea,
			String saleStatus, String bound);

}
