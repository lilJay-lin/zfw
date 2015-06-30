package com.mimi.zfw.service;

import com.mimi.zfw.mybatis.pojo.OfficeBuilding;
import com.mimi.zfw.mybatis.pojo.OfficeBuildingExample;

public interface IOfficeBuildingService extends IBaseService<OfficeBuilding, OfficeBuildingExample, String> {
	public void initOfficeBuilding();
}
