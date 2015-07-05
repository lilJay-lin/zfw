package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.RHPano;
import com.mimi.zfw.mybatis.pojo.RHPanoExample;

public interface IRHPanoService extends
		IBaseService<RHPano, RHPanoExample, String> {

	List<RHPano> getPanosByRHId(String id);
}
