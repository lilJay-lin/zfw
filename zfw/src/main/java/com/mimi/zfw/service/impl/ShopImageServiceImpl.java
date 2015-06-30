package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.ShopImageMapper;
import com.mimi.zfw.mybatis.pojo.ShopImage;
import com.mimi.zfw.mybatis.pojo.ShopImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IShopImageService;

@Service
public class ShopImageServiceImpl extends
		BaseService<ShopImage, ShopImageExample, String>
		implements IShopImageService {

	@Resource
	private ShopImageMapper sim;

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<ShopImage, ShopImageExample, String> baseDao) {
		this.baseDao = baseDao;
		this.sim = (ShopImageMapper) baseDao;
	}

	@Override
	public List<ShopImage> getImagesByParams(String id, int targetPage,
			int pageSize) {
		ShopImageExample ie = new ShopImageExample();
		ie.or().andShopIdEqualTo(id).andDelFlagEqualTo(false);
		ie.setLimitStart(targetPage*pageSize);
		ie.setLimitSize(pageSize);
		return sim.selectByExample(ie);
	}

}
