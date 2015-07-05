package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.ShopPanoMapper;
import com.mimi.zfw.mybatis.pojo.ShopPano;
import com.mimi.zfw.mybatis.pojo.ShopPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IShopPanoService;

@Service
public class ShopPanoServiceImpl extends
		BaseService<ShopPano, ShopPanoExample, String> implements
		IShopPanoService {

	@Resource
	private ShopPanoMapper spm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<ShopPano, ShopPanoExample, String> baseDao) {
		this.baseDao = baseDao;
		this.spm = (ShopPanoMapper) baseDao;
	}

	@Override
	public List<ShopPano> getPanosByShopId(String id) {
		ShopPanoExample pe = new ShopPanoExample();
		pe.or().andShopIdEqualTo(id).andDelFlagEqualTo(false);
		return spm.selectByExample(pe);
	}

}
