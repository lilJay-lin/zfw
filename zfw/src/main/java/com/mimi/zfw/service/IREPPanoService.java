package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.REPPano;
import com.mimi.zfw.mybatis.pojo.REPPanoExample;

public interface IREPPanoService extends
		IBaseService<REPPano, REPPanoExample, String> {

	public List<REPPano> getPanosByHTId(String id);

	public List<REPPano> getPanosByParams(String id, int targetPage,
			int pageSize);

}
