package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.HouseType;
import com.mimi.zfw.mybatis.pojo.HouseTypeExample;

public interface IHouseTypeService extends
		IBaseService<HouseType, HouseTypeExample, String> {

	public List<HouseType> findHouseTypeByParams(String keyWord, String region,
			String averagePrice, Integer roomNum, String grossFloorArea,
			String saleStatus, String orderBy, Integer targetPage,
			Integer pageSize);

	public int countHouseTypeByParams(String keyWord, String region,
			String averagePrice, Integer roomNum, String grossFloorArea,
			String saleStatus);

	public List<HouseType> getHouseTypeByREPId(String id);

	public List<HouseType> findByParams(String name, String repId,
			Integer targetPage, Integer pageSize);

	public int countByParams(String name, String repId);

	public Map<String, String> addHT(HouseType ht);

	public Map<String, String> updateHT(HouseType ht);

	public Map<String, String> batchDel(String htIds);
}
