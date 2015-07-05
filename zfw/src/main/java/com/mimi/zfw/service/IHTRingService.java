package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.HTRing;
import com.mimi.zfw.mybatis.pojo.HTRingExample;

public interface IHTRingService extends
		IBaseService<HTRing, HTRingExample, String> {
	public List<HTRing> getRingsByHTId(String id);

	public List<HTRing> getRingsByParams(String id, int targetPage, int pageSize);
}
