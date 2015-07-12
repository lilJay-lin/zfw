package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.ShopPano;
import com.mimi.zfw.mybatis.pojo.ShopPanoExample;

public interface IShopPanoService extends
	IBaseService<ShopPano, ShopPanoExample, String> {

    List<ShopPano> getPanosByShopId(String id);

    public List<ShopPano> findShopPanoByShopId(String shopId,
	    Integer curPage, Integer pageSize);

    public int countShopPanoByShopId(String shopId);

    public int updateBatchShopPano(String shopPanoIds, ShopPano shopPano);

    public List<ShopPano> findShopPanoByExample(ShopPanoExample example,
	    Integer curPage, Integer pageSize);

    public int countShopPanoByExample(ShopPanoExample example);

    public Map<String, String> addShopPano(ShopPano shopPano);

    public Map<String, String> updateShopPano(ShopPano shopPano);

    public Map<String, String> checkShopPano(ShopPano shopPano);

}
