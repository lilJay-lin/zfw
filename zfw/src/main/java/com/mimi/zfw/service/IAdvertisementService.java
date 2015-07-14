package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.Advertisement;
import com.mimi.zfw.mybatis.pojo.AdvertisementExample;

public interface IAdvertisementService extends
		IBaseService<Advertisement, AdvertisementExample, String> {
	public void initAdvertisement();

	public List<Advertisement> getActiveByLocation(String location);

	public List<Advertisement> findByParams(String name, String location,
			Integer targetPage, Integer pageSize);

	public int countByParams(String name, String location);

	public Map<String, String> modify(Advertisement ad);
}
