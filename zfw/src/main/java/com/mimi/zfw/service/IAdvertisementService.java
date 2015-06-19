package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.Advertisement;
import com.mimi.zfw.mybatis.pojo.AdvertisementExample;

public interface IAdvertisementService extends
		IBaseService<Advertisement, AdvertisementExample, String> {
	public void initAdvertisement();

	public List<Advertisement> getActiveByLocation(String location);
}
