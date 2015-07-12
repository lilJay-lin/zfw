package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.HTPano;
import com.mimi.zfw.mybatis.pojo.HTPanoExample;

public interface IHTPanoService extends
		IBaseService<HTPano, HTPanoExample, String> {

	public List<HTPano> getPanosByHTId(String id);

	public List<HTPano> getPanosByParams(String id, int targetPage, int pageSize);

	public List<HTPano> findByParams(String htId, String name,
			Integer targetPage, Integer pageSize);

	public int countByParams(String htId, String name);

	public Map<String, String> add(HTPano pano);

	public Map<String, String> modify(HTPano pano);

	public Map<String, String> batchDel(String panoIds);
	
}
