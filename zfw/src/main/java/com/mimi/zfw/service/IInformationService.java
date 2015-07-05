package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.Information;
import com.mimi.zfw.mybatis.pojo.InformationExample;

public interface IInformationService extends
		IBaseService<Information, InformationExample, String> {
	public void initInformation();

	public List<Information> findByParams(String type, Integer targetPage,
			Integer pageSize);
	
	public int countByParams(String type);

	public List<Information> findByREPId(String id, Integer targetPage,
			Integer pageSize);

	public int countByREPId(String id);
}
