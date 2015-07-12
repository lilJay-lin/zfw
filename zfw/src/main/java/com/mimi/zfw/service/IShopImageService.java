package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.Role;
import com.mimi.zfw.mybatis.pojo.RoleExample;
import com.mimi.zfw.mybatis.pojo.Shop;
import com.mimi.zfw.mybatis.pojo.ShopImage;
import com.mimi.zfw.mybatis.pojo.ShopImageExample;

public interface IShopImageService extends
	IBaseService<ShopImage, ShopImageExample, String> {

    List<ShopImage> getImagesByParams(String id, int targetPage, int pageSize);
    
    public List<ShopImage> findShopImageByShopId(String shopId, Integer curPage,
	    Integer pageSize);

    public int countShopImageByShopId(String shopId);
    
    public int updateBatchShopImage(String shopImageIds,ShopImage shopImage);

    public List<ShopImage> findShopImageByExample(ShopImageExample example, Integer curPage,
	    Integer pageSize);

    public int countShopImageByExample(ShopImageExample example);

	
    public Map<String, String> addShopImage(ShopImage shopImage);

    public Map<String,String> updateShopImage(ShopImage shopImage);
    
    public Map<String, String> checkShopImage(ShopImage shopImage);
    
}
