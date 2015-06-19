package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.HouseType;
import com.mimi.zfw.mybatis.pojo.HouseTypeExample;

public interface IHouseTypeService extends
		IBaseService<HouseType, HouseTypeExample, String> {

	public List<HouseType> findHouseTypeByParams(String keyWord, String region,
			String averagePrice, Integer roomNum, String grossFloorArea,
			String saleStatus, String orderBy, int targetPage, int pageSize);

	public int countHouseTypeByParams(String keyWord, String region,
			String averagePrice, Integer roomNum, String grossFloorArea,
			String saleStatus);
	
	public List<HouseType> getHouseTypeByREPId(String id);
}
