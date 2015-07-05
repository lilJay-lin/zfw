package com.mimi.zfw.service;

import java.util.Map;

import com.mimi.zfw.mybatis.pojo.SHHFloorPriceLinearFunction;
import com.mimi.zfw.mybatis.pojo.SHHFloorPriceLinearFunctionExample;

public interface ISHHFloorPriceLinearFunctionService
		extends
		IBaseService<SHHFloorPriceLinearFunction, SHHFloorPriceLinearFunctionExample, String> {
	public void resetFunction();

	public Map<String, Object> analyse(String residenceCommunityId,
			String residenceCommunityName, String forward, Integer curFloor,
			Integer plusValue);

}
