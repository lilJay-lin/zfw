package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.REPPano;
import com.mimi.zfw.mybatis.pojo.REPPanoExample;

public interface IREPPanoService extends
		IBaseService<REPPano, REPPanoExample, String> {

	public List<REPPano> getPanosByHTId(String id);

	public List<REPPano> getPanosByParams(String id, int targetPage,
			int pageSize);

	public List<REPPano> findByParams(String repId, String name,
			Integer targetPage, Integer pageSize);

	public int countByParams(String repId, String name);

	public Map<String, String> add(REPPano pano);

	public Map<String, String> modify(REPPano pano);

	public Map<String, String> batchDel(String imageIds);

}
