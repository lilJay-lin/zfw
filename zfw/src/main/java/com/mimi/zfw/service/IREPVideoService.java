package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.REPVideo;
import com.mimi.zfw.mybatis.pojo.REPVideoExample;

public interface IREPVideoService extends
		IBaseService<REPVideo, REPVideoExample, String> {

	public List<REPVideo> getVideosByHTId(String id);

	public List<REPVideo> getVideosByParams(String id, int targetPage,
			int pageSize);

}
