package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.HTPano;
import com.mimi.zfw.mybatis.pojo.HTPanoExample;

public interface IHTPanoService extends
		IBaseService<HTPano, HTPanoExample, String> {

	public List<HTPano> getPanosByHTId(String id);

	public List<HTPano> getPanosByParams(String id, int targetPage, int pageSize);

}
