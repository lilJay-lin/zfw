package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.REPVideo;
import com.mimi.zfw.mybatis.pojo.REPVideoExample;

public interface IREPVideoService extends
		IBaseService<REPVideo, REPVideoExample, String> {

	public List<REPVideo> getVideosByHTId(String id);

	public List<REPVideo> getVideosByParams(String id, int targetPage,
			int pageSize);

	public List<REPVideo> findByParams(String repId, String name,
			Integer targetPage, Integer pageSize);

	public int countByParams(String repId, String name);

	public Map<String, String> add(REPVideo video);

	public Map<String, String> modify(REPVideo video);

	public Map<String, String> batchDel(String videoIds);

}
