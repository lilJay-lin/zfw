package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.ShopImageMapper;
import com.mimi.zfw.mybatis.pojo.ShopImage;
import com.mimi.zfw.mybatis.pojo.ShopImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IShopImageService;
import com.mimi.zfw.service.IUserService;

@Service
public class ShopImageServiceImpl extends
	BaseService<ShopImage, ShopImageExample, String> implements
	IShopImageService {

    @Resource
    private ShopImageMapper sim;
    @Resource
    private IUserService userService ;
    @Resource
    @Override
    public void setBaseDao(IBaseDao<ShopImage, ShopImageExample, String> baseDao) {
	this.baseDao = baseDao;
	this.sim = (ShopImageMapper) baseDao;
    }

    @Override
    public List<ShopImage> getImagesByParams(String id, int targetPage,
	    int pageSize) {
	ShopImageExample ie = new ShopImageExample();
	ie.or().andShopIdEqualTo(id).andDelFlagEqualTo(false);
	ie.setLimitStart(targetPage * pageSize);
	ie.setLimitSize(pageSize);
	ie.setOrderByClause("update_date desc");
	return sim.selectByExample(ie);
    }

    @Override
    public List<ShopImage> findShopImageByShopId(String shopId,
	    Integer curPage, Integer pageSize) {
	if (StringUtils.isBlank(shopId)) {
	    return null;
	}
	ShopImageExample example = new ShopImageExample();
	example.or().andShopIdEqualTo(shopId).andDelFlagEqualTo(false);
	example.setLimitStart(curPage * pageSize);
	example.setLimitSize(pageSize);
	example.setOrderByClause("update_date desc");
	List<ShopImage> shopImages= sim.selectByExample(example);
	
	return shopImages;
    }

    @Override
    public int countShopImageByShopId(String shopId) {
	if (StringUtils.isBlank(shopId)) {
	    return 0;
	}
	ShopImageExample example = new ShopImageExample();
	example.or().andShopIdEqualTo(shopId).andDelFlagEqualTo(false);
	return sim.countByExample(example);
    }

    @Override
    public int updateBatchShopImage(String shopImageIds, ShopImage shopImage) {
	
	if (shopImage == null) {
	    return 0;
	}
	
	String[] ids = shopImageIds.split(Constants.MI_IDS_SPLIT_STRING);
	List<String> list = new ArrayList<String>();
	for (String id : ids) {
	    list.add(id);
	}
	
	ShopImageExample example = new ShopImageExample();
	example.or().andIdIn(list).andDelFlagEqualTo(false);
	shopImage.setLastEditor(userService.getCurUserId());
	
	int i = sim.updateByExampleSelective(shopImage, example);
	
	return i;
    }

    @Override
    public List<ShopImage> findShopImageByExample(ShopImageExample example,
	    Integer curPage, Integer pageSize) {
	
	
	if(example == null){
	    example = new ShopImageExample();
	    example.or().andDelFlagEqualTo(false);
	}

	example.setLimitStart(curPage* pageSize);
	example.setLimitSize(pageSize);

	example.setOrderByClause("update_date desc");
	List<ShopImage> list = sim.selectByExample(example);
	
	return list;
    }

    @Override
    public int countShopImageByExample(ShopImageExample example) {
	
	if(example == null){
	    example = new ShopImageExample();
	    example.or().andDelFlagEqualTo(false);
	}
	List<ShopImage> list = sim.selectByExample(example);

	return list==null?0: list.size();
    }

    @Override
    public Map<String, String> addShopImage(ShopImage shopImage) {
	// TODO Auto-generated method stub
	Map<String, String> resMap = new HashMap<String, String>();
	if (shopImage == null) {
	    resMap.put("msg", "商铺图片内容不能为空");
	    return resMap;
	}
	resMap = checkShopImage(shopImage);
	if (!resMap.isEmpty()) {
	    return resMap;
	}
	String curId = userService.getCurUserId();
	shopImage.setCreater(curId);
	shopImage.setLastEditor(curId);
	shopImage.setDelFlag(false);
	shopImage.setId(UUID.randomUUID().toString());
	sim.insertSelective(shopImage);

	return resMap;	
    }

    @Override
    public Map<String, String> updateShopImage(ShopImage shopImage) {
	Map<String, String> resMap = new HashMap<String, String>();
	if (shopImage == null) {
	    resMap.put("msg", "商铺图片内容不能为空");
	    return resMap;
	}
	resMap = checkShopImage(shopImage);
	if (!resMap.isEmpty()) {
	    return resMap;
	}
	
	shopImage.setLastEditor(userService.getCurUserId());
	sim.updateByPrimaryKeySelective(shopImage);

	return resMap;
    }

    @Override
    public Map<String, String> checkShopImage(ShopImage shopImage) {
	Map<String, String> resMap = new HashMap<String, String>();
	
	if(StringUtils.isBlank(shopImage.getName())){
	    resMap.put("field","name");
	    resMap.put("msg","商铺名称不能为空");
	}
	if(StringUtils.isBlank(shopImage.getContentUrl())){
	    resMap.put("msg","商铺图片不能为空");
	}
	
	return resMap;
    }


}
