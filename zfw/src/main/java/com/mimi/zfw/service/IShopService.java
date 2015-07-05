package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.Shop;
import com.mimi.zfw.mybatis.pojo.ShopExample;

public interface IShopService extends IBaseService<Shop, ShopExample, String> {
	public void initShop();

	public List<Shop> findShopsByParams(String keyWord, String region,
			String grossFloorArea, String type, String rentOrSale,
			String rental, String totalPrice, String orderBy,
			Integer targetPage, Integer pageSize);

	public int countShopByParams(String keyWord, String region,
			String grossFloorArea, String type, String rentOrSale,
			String rental, String totalPrice);

	public List<Shop> getByUserId(String userId, Integer targetPage,
			Integer pageSize);

	public int countByUserId(String userId);

	public String saveCascading(Shop shop, String imgUrls);

	public String updateCascading(Shop shop, String imgUrls);

	public String refreshUserShop(String id);

	public String deleteUserShopByFlag(String id);

}
