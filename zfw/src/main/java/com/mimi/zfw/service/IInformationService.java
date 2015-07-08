package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

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

	public int countInfoByParams(String name, String type);

	public List<Information> findInfoByParams(String name, String type,
			Integer targetPage, Integer pageSize);

	public Map<String, String> addInfo(Information info, String repIds);

	public Map<String, String> updateInfo(Information info, String addREPs,
			String delREPs);

	public Map<String, String> batchDel(String infoIds);
}
