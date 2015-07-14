package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.RHPano;
import com.mimi.zfw.mybatis.pojo.RHPanoExample;

public interface IRHPanoService extends
		IBaseService<RHPano, RHPanoExample, String> {

	List<RHPano> getPanosByRHId(String id);

	public List<RHPano> findByParams(String rhId, String name,
			Integer targetPage, Integer pageSize);

	public int countByParams(String rhId, String name);

	public Map<String, String> add(RHPano pano);

	public Map<String, String> modify(RHPano pano);

	public Map<String, String> batchDel(String panoIds);
}
