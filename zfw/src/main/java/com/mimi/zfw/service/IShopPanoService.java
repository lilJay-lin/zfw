package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.ShopPano;
import com.mimi.zfw.mybatis.pojo.ShopPanoExample;

public interface IShopPanoService extends
		IBaseService<ShopPano, ShopPanoExample, String> {

	List<ShopPano> getPanosByShopId(String id);
}
