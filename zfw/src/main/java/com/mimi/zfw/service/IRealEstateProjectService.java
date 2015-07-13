package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

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

	public List<RealEstateProject> getREPByInfoId(String id);

	public Map<String, String> addREP(RealEstateProject rep, String userIds,
			String infoIds);

	public Map<String, String> updateREP(RealEstateProject rep,
			String addUserRelations, String delUserRelations,
			String addInfoRelations, String delInfoRelations);

	public Map<String, String> batchDel(String repIds);

	public List<RealEstateProject> findByParams(String name, boolean ru,
			Integer targetPage, Integer pageSize);

	public int countByParams(String name, boolean ru);

}
