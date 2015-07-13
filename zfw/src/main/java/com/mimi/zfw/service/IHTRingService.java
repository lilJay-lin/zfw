package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.HTRing;
import com.mimi.zfw.mybatis.pojo.HTRingExample;

public interface IHTRingService extends
		IBaseService<HTRing, HTRingExample, String> {
	public List<HTRing> getRingsByHTId(String id);

	public List<HTRing> getRingsByParams(String id, int targetPage, int pageSize);

	public List<HTRing> findByParams(String htId, String name,
			Integer targetPage, Integer pageSize);

	public int countByParams(String htId, String name);

	public Map<String, String> add(HTRing ring);

	public Map<String, String> modify(HTRing ring);

	public Map<String, String> batchDel(String imageIds);
}
