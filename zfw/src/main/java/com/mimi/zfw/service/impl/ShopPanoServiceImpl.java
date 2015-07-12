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
import com.mimi.zfw.mybatis.dao.ShopPanoMapper;
import com.mimi.zfw.mybatis.pojo.ShopPano;
import com.mimi.zfw.mybatis.pojo.ShopPanoExample;
import com.mimi.zfw.mybatis.pojo.ShopPano;
import com.mimi.zfw.mybatis.pojo.ShopPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IShopPanoService;
import com.mimi.zfw.service.IUserService;

@Service
public class ShopPanoServiceImpl extends
	BaseService<ShopPano, ShopPanoExample, String> implements
	IShopPanoService {

    @Resource
    private ShopPanoMapper spm;
    @Resource
    private IUserService userService;

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

    @Override
    public List<ShopPano> findShopPanoByShopId(String shopId, Integer curPage,
	    Integer pageSize) {
	if (StringUtils.isBlank(shopId)) {
	    return null;
	}
	ShopPanoExample example = new ShopPanoExample();
	example.or().andShopIdEqualTo(shopId).andDelFlagEqualTo(false);
	example.setLimitStart(curPage * pageSize);
	example.setLimitSize(pageSize);
	List<ShopPano> shopPanos = spm.selectByExample(example);

	return shopPanos;
    }

    @Override
    public int countShopPanoByShopId(String shopId) {
	if (StringUtils.isBlank(shopId)) {
	    return 0;
	}
	ShopPanoExample example = new ShopPanoExample();
	example.or().andShopIdEqualTo(shopId).andDelFlagEqualTo(false);
	List<ShopPano> shopPanos = spm.selectByExample(example);

	return shopPanos.size();
    }

    @Override
    public int updateBatchShopPano(String shopPanoIds, ShopPano shopPano) {

	if (shopPano == null) {
	    return 0;
	}

	String[] ids = shopPanoIds.split(Constants.MI_IDS_SPLIT_STRING);
	List<String> list = new ArrayList<String>();
	for (String id : ids) {
	    list.add(id);
	}

	ShopPanoExample example = new ShopPanoExample();
	example.or().andIdIn(list).andDelFlagEqualTo(false);
	shopPano.setLastEditor(userService.getCurUserId());

	int i = spm.updateByExampleSelective(shopPano, example);

	return i;
    }

    @Override
    public List<ShopPano> findShopPanoByExample(ShopPanoExample example,
	    Integer curPage, Integer pageSize) {

	if (example == null) {
	    example = new ShopPanoExample();
	    example.or().andDelFlagEqualTo(false);
	}

	example.setLimitStart(curPage * pageSize);
	example.setLimitSize(pageSize);

	List<ShopPano> list = spm.selectByExample(example);

	return list;
    }

    @Override
    public int countShopPanoByExample(ShopPanoExample example) {

	if (example == null) {
	    example = new ShopPanoExample();
	    example.or().andDelFlagEqualTo(false);
	}
	List<ShopPano> list = spm.selectByExample(example);

	return list == null ? 0 : list.size();
    }

    @Override
    public Map<String, String> addShopPano(ShopPano shopPano) {
	// TODO Auto-generated method stub
	Map<String, String> resMap = new HashMap<String, String>();
	if (shopPano == null) {
	    resMap.put("msg", "商铺全景内容不能为空");
	    return resMap;
	}
	resMap = checkShopPano(shopPano);
	if (!resMap.isEmpty()) {
	    return resMap;
	}
	String curId = userService.getCurUserId();
	shopPano.setCreater(curId);
	shopPano.setLastEditor(curId);
	shopPano.setDelFlag(false);
	shopPano.setId(UUID.randomUUID().toString());
	spm.insertSelective(shopPano);

	return resMap;
    }

    @Override
    public Map<String, String> updateShopPano(ShopPano shopPano) {
	Map<String, String> resMap = new HashMap<String, String>();
	if (shopPano == null) {
	    resMap.put("msg", "商铺全景内容不能为空");
	    return resMap;
	}
	resMap = checkShopPano(shopPano);
	if (!resMap.isEmpty()) {
	    return resMap;
	}

	shopPano.setLastEditor(userService.getCurUserId());
	spm.updateByPrimaryKeySelective(shopPano);

	return resMap;
    }

    @Override
    public Map<String, String> checkShopPano(ShopPano shopPano) {
	Map<String, String> resMap = new HashMap<String, String>();

	if (StringUtils.isBlank(shopPano.getName())) {
	    resMap.put("field", "name");
	    resMap.put("msg", "商铺名称不能为空");
	}
	if (StringUtils.isBlank(shopPano.getPreImageUrl())) {
	    resMap.put("msg", "商铺图片不能为空");
	}

	return resMap;
    }

}
