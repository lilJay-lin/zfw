package com.mimi.zfw.service;

import java.util.Date;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.SHHFloorPriceLinearFunction;
import com.mimi.zfw.mybatis.pojo.SHHFloorPriceLinearFunctionExample;

public interface ISHHFloorPriceLinearFunctionService
		extends
		IBaseService<SHHFloorPriceLinearFunction, SHHFloorPriceLinearFunctionExample, String> {
	public String resetFunction();

	public Map<String, Object> analyse(String residenceCommunityId,
			String residenceCommunityName, String forward, Integer curFloor,
			Integer plusValue);

	public Date getLastStart();

	public void setLastStart(Date lastStart);

	public Date getLastEnd();

	public void setLastEnd(Date lastEnd);

	public boolean isLastSuccess();

	public void setLastSuccess(boolean lastSuccess);

	public boolean isComputing();
}
