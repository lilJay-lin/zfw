package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.ShopImage;
import com.mimi.zfw.mybatis.pojo.ShopImageExample;

public interface IShopImageService extends
		IBaseService<ShopImage, ShopImageExample, String> {

	List<ShopImage> getImagesByParams(String id, int targetPage, int pageSize);
}
