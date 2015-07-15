package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.SHHPano;
import com.mimi.zfw.mybatis.pojo.SHHPanoExample;

public interface ISHHPanoService extends
		IBaseService<SHHPano, SHHPanoExample, String> {

	List<SHHPano> getPanosBySHHId(String id);

	public List<SHHPano> findByParams(String shhId, String name,
			Integer targetPage, Integer pageSize);

	public int countByParams(String shhId, String name);

	public Map<String, String> add(SHHPano pano);

	public Map<String, String> modify(SHHPano pano);

	public Map<String, String> batchDel(String panoIds);

}
