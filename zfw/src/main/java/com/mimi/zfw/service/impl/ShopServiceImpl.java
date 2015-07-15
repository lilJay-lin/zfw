package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.ShopImageMapper;
import com.mimi.zfw.mybatis.dao.ShopMapper;
import com.mimi.zfw.mybatis.dao.ShopPanoMapper;
import com.mimi.zfw.mybatis.pojo.Shop;
import com.mimi.zfw.mybatis.pojo.ShopExample;
import com.mimi.zfw.mybatis.pojo.ShopImage;
import com.mimi.zfw.mybatis.pojo.ShopImageExample;
import com.mimi.zfw.mybatis.pojo.ShopPano;
import com.mimi.zfw.mybatis.pojo.ShopPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IShopService;
import com.mimi.zfw.service.IUserService;

@Service
public class ShopServiceImpl extends BaseService<Shop, ShopExample, String>
	implements IShopService {

    @Resource
    private ShopMapper sm;

    @Resource
    private ShopImageMapper sim;

    @Resource
    private ShopPanoMapper spm;

    @Resource
    private IUserService userService;

    @Resource
    @Override
    public void setBaseDao(IBaseDao<Shop, ShopExample, String> baseDao) {
	this.baseDao = baseDao;
	this.sm = (ShopMapper) baseDao;
    }

    @Override
    public void initShop() {
	ShopExample se = new ShopExample();
	se.or().andDelFlagEqualTo(false);
	int count = sm.countByExample(se);
	if (count < 1) {
	    initTestData();
	}
    }

    private void initTestData() {
	String[] imgUrl = Constants.ALIYUN_OSS_TEST_IMG_URLS;
	String[] preImgUrl = Constants.ALIYUN_OSS_TEST_IMG_URLS;
	for (int i = 0; i < 50; i++) {
	    Shop shop = new Shop();
	    shop.setId(UUID.randomUUID().toString());
	    shop.setName("商铺名称" + RandomUtils.nextInt(100));
	    double regionTemp = Math.random();
	    String region;
	    if (regionTemp > 0.8) {
		region = Constants.REGION_BL;
	    } else if (regionTemp > 0.6) {
		region = Constants.REGION_CC;
	    } else if (regionTemp > 0.4) {
		region = Constants.REGION_CD;
	    } else if (regionTemp > 0.2) {
		region = Constants.REGION_CZ;
	    } else {
		region = Constants.REGION_GY;
	    }
	    shop.setRegion(region);
	    shop.setPhoneNum("12332112310");
	    shop.setRental(RandomUtils.nextInt(10000) + 1000);
	    shop.setTotalPrice(RandomUtils.nextInt(100) + 20);
	    shop.setGrossFloorArea((float) (Math.random() * 1000 + 50));

	    double decorationTemp = Math.random();
	    String decorationStatus;
	    if (decorationTemp > 0.8) {
		decorationStatus = Constants.DECORATION_STATUS_HH;
	    } else if (decorationTemp > 0.6) {
		decorationStatus = Constants.DECORATION_STATUS_J;
	    } else if (decorationTemp > 0.4) {
		decorationStatus = Constants.DECORATION_STATUS_JD;
	    } else {
		decorationStatus = Constants.DECORATION_STATUS_MP;
	    }
	    shop.setDecorationStatus(decorationStatus);
	    shop.setAddress("地址阿里山的房间卡死的解放路卡死地方");
	    shop.setIntroduction("介绍阿里斯顿房间爱死了的阿斯兰的上的");

	    double rosTemp = Math.random();
	    String rentOrSale;
	    if (rosTemp > 0.6) {
		rentOrSale = Constants.ROS_RENT_ONLY;
		shop.setTotalPrice(null);
	    } else if (rosTemp > 0.3) {
		rentOrSale = Constants.ROS_SALE_ONLY;
		shop.setRental(null);
	    } else {
		rentOrSale = Constants.ROS_RENT_AND_SALE;
	    }
	    shop.setRentOrSale(rentOrSale);
	    shop.setPropertyFee(RandomUtils.nextInt(20)
		    + RandomUtils.nextFloat());

	    double typeTemp = Math.random();
	    String type;
	    if (typeTemp > 0.8) {
		type = Constants.SHOP_TYPE_BH;
	    } else if (typeTemp > 0.6) {
		type = Constants.SHOP_TYPE_LJ;
	    } else if (typeTemp > 0.4) {
		type = Constants.SHOP_TYPE_SYJ;
	    } else if (typeTemp > 0.2) {
		type = Constants.SHOP_TYPE_XZL;
	    } else {
		type = Constants.SHOP_TYPE_ZZDS;
	    }
	    shop.setType(type);
	    shop.setOutOfDate(RandomUtils.nextBoolean());
	    shop.setDescription("描述啊楼上的房间啊拉伸到房间拉克丝放大镜");
	    shop.setTags("标签1,标签2,标签3");
	    shop.setPriority(RandomUtils.nextInt(100));
	    shop.setPreImageUrl(Constants.ALIYUN_OSS_TEST_IMG_URLS[3]);

	    sm.insertSelective(shop);

	    for (int ki = 0; ki < imgUrl.length; ki++) {
		ShopImage si = new ShopImage();
		si.setId(UUID.randomUUID().toString());
		si.setShopId(shop.getId());
		si.setContentUrl(imgUrl[ki]);
		si.setName("蝴蝶");
		si.setDescription("描述爱丽丝疯狂就阿斯兰的房间啦拉伸到卡机上了k");

		ShopPano sp = new ShopPano();
		sp.setId(UUID.randomUUID().toString());
		sp.setShopId(shop.getId());
		sp.setContentUrl("http://www.baidu.com");
		sp.setPreImageUrl(preImgUrl[ki]);
		sp.setName("哇哇");
		sp.setDescription("水电费水电费阿斯蒂芬 ");

		sim.insertSelective(si);
		spm.insertSelective(sp);
	    }
	}
    }

    @Override
    public List<Shop> findShopsByParams(String keyWord, String region,
	    String grossFloorArea, String type, String rentOrSale,
	    String rental, String totalPrice, String orderBy,
	    Integer targetPage, Integer pageSize) {
	ShopExample se = bindShopParams(keyWord, region, grossFloorArea, type,
		rentOrSale, rental, totalPrice);
	String orderByClause = "";
	if (StringUtils.isNotBlank(orderBy)) {
	    if ("priceFromLow".equals(orderBy)) {
		if (Constants.ROS_RENT_ONLY.equals(rentOrSale)) {
		    orderByClause = "rental";
		} else {
		    orderByClause = "total_price";
		}
		orderByClause += " asc,";
	    } else if ("priceFromHigh".equals(orderBy)) {
		if (Constants.ROS_RENT_ONLY.equals(rentOrSale)) {
		    orderByClause = "rental";
		} else {
		    orderByClause = "total_price";
		}
		orderByClause += " desc,";
	    } else if ("onUpdateFromNear".equals(orderBy)) {
		orderByClause = "update_date desc,";
	    } else if ("onUpdateFromFar".equals(orderBy)) {
		orderByClause = "update_date asc,";
	    }
	}
	orderByClause += "priority desc";
	se.setOrderByClause(orderByClause);
	se.setLimitStart(targetPage * pageSize);
	se.setLimitSize(pageSize);
	return sm.selectByExample(se);
    }

    @Override
    public int countShopByParams(String keyWord, String region,
	    String grossFloorArea, String type, String rentOrSale,
	    String rental, String totalPrice) {
	ShopExample se = bindShopParams(keyWord, region, grossFloorArea, type,
		rentOrSale, rental, totalPrice);
	return sm.countByExample(se);
    }

    private ShopExample bindShopParams(String keyWord, String region,
	    String grossFloorArea, String type, String rentOrSale,
	    String rental, String totalPrice) {
	ShopExample se = new ShopExample();
	ShopExample.Criteria cri = se.createCriteria();
	cri.andDelFlagEqualTo(false);
	ShopExample.Criteria cri2 = null;
	if (StringUtils.isNotBlank(keyWord)) {
	    cri.andNameLike("%" + keyWord + "%");
	    cri2 = se.createCriteria();
	    cri2.andAddressLike("%" + keyWord + "%").andDelFlagEqualTo(false);
	}
	if (StringUtils.isNotBlank(region)) {
	    cri.andRegionEqualTo(region);
	    if (cri2 != null) {
		cri2.andRegionEqualTo(region);
	    }
	}
	if (StringUtils.isNotBlank(grossFloorArea)) {
	    String[] values = grossFloorArea.split(":");
	    if (values.length > 1 && StringUtils.isNumeric(values[0])
		    && StringUtils.isNumeric(values[1])) {
		cri.andGrossFloorAreaBetween(Float.valueOf(values[0]),
			Float.valueOf(values[1]));
		if (cri2 != null) {
		    cri2.andGrossFloorAreaBetween(Float.valueOf(values[0]),
			    Float.valueOf(values[1]));
		}
	    }
	}
	if (StringUtils.isNotBlank(type)) {
	    cri.andTypeEqualTo(type);
	    if (cri2 != null) {
		cri2.andTypeEqualTo(type);
	    }
	}
	if (StringUtils.isNotBlank(rentOrSale)) {
	    if (Constants.ROS_RENT_AND_SALE.equals(rentOrSale)) {
		cri.andRentOrSaleEqualTo(rentOrSale);
	    } else {
		List<String> rentOrSales = new ArrayList<String>();
		rentOrSales.add(rentOrSale);
		rentOrSales.add(Constants.ROS_RENT_AND_SALE);
		cri.andRentOrSaleIn(rentOrSales);
	    }
	    if (cri2 != null) {
		if (Constants.ROS_RENT_AND_SALE.equals(rentOrSale)) {
		    cri2.andRentOrSaleEqualTo(rentOrSale);
		} else {
		    List<String> rentOrSales = new ArrayList<String>();
		    rentOrSales.add(rentOrSale);
		    rentOrSales.add(Constants.ROS_RENT_AND_SALE);
		    cri2.andRentOrSaleIn(rentOrSales);
		}
	    }
	}
	if (StringUtils.isNotBlank(rental)
		&& Constants.ROS_RENT_ONLY.equals(rentOrSale)) {
	    String[] values = rental.split(":");
	    if (values.length > 1 && StringUtils.isNumeric(values[0])
		    && StringUtils.isNumeric(values[1])) {
		cri.andRentalBetween(Integer.valueOf(values[0]),
			Integer.valueOf(values[1]));
		if (cri2 != null) {
		    cri2.andRentalBetween(Integer.valueOf(values[0]),
			    Integer.valueOf(values[1]));
		}
	    }
	}
	if (StringUtils.isNotBlank(totalPrice)
		&& Constants.ROS_SALE_ONLY.equals(rentOrSale)) {
	    String[] values = totalPrice.split(":");
	    if (values.length > 1 && StringUtils.isNumeric(values[0])
		    && StringUtils.isNumeric(values[1])) {
		cri.andTotalPriceBetween(Integer.valueOf(values[0]),
			Integer.valueOf(values[1]));
		if (cri2 != null) {
		    cri2.andTotalPriceBetween(Integer.valueOf(values[0]),
			    Integer.valueOf(values[1]));
		}
	    }
	}
	if (cri2 != null) {
	    se.or(cri2);
	}
	return se;
    }

    @Override
    public List<Shop> getByUserId(String userId, Integer targetPage,
	    Integer pageSize) {
	if (StringUtils.isBlank(userId)) {
	    return null;
	}
	ShopExample se = new ShopExample();
	se.or().andCreaterEqualTo(userId).andDelFlagEqualTo(false);
	if (targetPage != null && pageSize != null) {
	    se.setLimitStart(targetPage * pageSize);
	    se.setLimitSize(pageSize);
	}
	return sm.selectByExample(se);
    }

    @Override
    public int countByUserId(String userId) {
	if (StringUtils.isBlank(userId)) {
	    return 0;
	}
	ShopExample se = new ShopExample();
	se.or().andCreaterEqualTo(userId).andDelFlagEqualTo(false);
	return sm.countByExample(se);
    }

    @Override
    public String saveCascading(Shop shop, String imgUrls) {

	String userId = userService.getCurUserId();
	if (StringUtils.isBlank(userId)) {
	    return "请先登录";
	}
	if (StringUtils.isBlank(shop.getName())) {
	    return "标题不能为空";
	}
	if (StringUtils.isBlank(shop.getPhoneNum())) {
	    return "电话不能为空";
	}
	if (shop.getTotalPrice() == null && shop.getRental() == null) {
	    return "租金和售价不能同时为空";
	}

	// 创建商铺
	shop.setId(UUID.randomUUID().toString());
	shop.setCreater(userId);
	shop.setLastEditor(userId);
	sm.insertSelective(shop);

	long timeMillis = System.currentTimeMillis();
	// 创建商铺图片
	if (StringUtils.isNotBlank(imgUrls)) {
	    String[] urls = imgUrls.split(Constants.IMAGE_URLS_SPLIT_STRING);
	    for (int i = 0; i < urls.length; i++) {
		if (StringUtils.isNotBlank(urls[i])) {
		    ShopImage si = new ShopImage();
		    si.setId(UUID.randomUUID().toString());
		    si.setContentUrl(urls[i]);
		    si.setCreater(userId);
		    si.setLastEditor(shop.getLastEditor());
		    si.setShopId(shop.getId());
		    si.setName(shop.getName());
		    timeMillis = timeMillis + 1000;
		    Date nowDate = new Date(timeMillis);
		    si.setCreateDate(nowDate);
		    si.setUpdateDate(nowDate);
		    sim.insertSelective(si);
		}
	    }
	}
	return null;
    }

    @Override
    public String updateCascading(Shop shop, String imgUrls) {
	String userId = userService.getCurUserId();
	if (StringUtils.isBlank(userId)) {
	    return "请先登录";
	}
	if (StringUtils.isBlank(shop.getName())) {
	    return "标题不能为空";
	}
	if (StringUtils.isBlank(shop.getPhoneNum())) {
	    return "电话不能为空";
	}
	if (shop.getTotalPrice() == null && shop.getRental() == null) {
	    return "租金和售价不能同时为空";
	}

	// 更新商铺
	shop.setLastEditor(userId);
	sm.updateByPrimaryKeySelective(shop);

	// 更新商铺图片
	ShopImageExample ie = new ShopImageExample();
	ie.or().andShopIdEqualTo(shop.getId()).andDelFlagEqualTo(false);
	List<ShopImage> oldImgs = sim.selectByExample(ie);
	for (int j = 0; j < oldImgs.size(); j++) {
	    oldImgs.get(j).setDelFlag(true);
	}

	long timeMillis = System.currentTimeMillis();
	// 添加新图片
	if (StringUtils.isNotBlank(imgUrls)) {
	    String[] urls = imgUrls.split(Constants.IMAGE_URLS_SPLIT_STRING);
	    for (int i = 0; i < urls.length; i++) {
		if (StringUtils.isNotBlank(urls[i])) {
		    boolean exist = false;
		    for (int j = 0; j < oldImgs.size(); j++) {
			if (urls[i].equals(oldImgs.get(j).getContentUrl())) {
			    exist = true;
			    oldImgs.get(j).setDelFlag(false);
			    break;
			}
		    }
		    if (!exist) {
			ShopImage si = new ShopImage();
			si.setId(UUID.randomUUID().toString());
			si.setContentUrl(urls[i]);
			si.setCreater(shop.getLastEditor());
			si.setLastEditor(shop.getLastEditor());
			si.setShopId(shop.getId());
			si.setName(shop.getName());
			timeMillis = timeMillis + 1000;
			Date nowDate = new Date(timeMillis);
			si.setCreateDate(nowDate);
			si.setUpdateDate(nowDate);
			sim.insertSelective(si);
		    }
		}
	    }
	}
	// 删除图片
	List<String> delIds = new ArrayList<String>();
	for (int j = 0; j < oldImgs.size(); j++) {
	    if (oldImgs.get(j).getDelFlag()) {
		delIds.add(oldImgs.get(j).getId());
	    }
	}
	if (!delIds.isEmpty()) {
	    ShopImageExample ie2 = new ShopImageExample();
	    ie2.or().andIdIn(delIds).andDelFlagEqualTo(false);
	    ShopImage si = new ShopImage();
	    si.setDelFlag(true);
	    si.setLastEditor(shop.getLastEditor());
	    sim.updateByExampleSelective(si, ie2);
	}
	return null;
    }

    @Override
    public String refreshUserShop(String id) {
	String userId = userService.getCurUserId();
	if (StringUtils.isBlank(userId)) {
	    return "请先登录";
	}
	ShopExample se = new ShopExample();
	se.or().andIdEqualTo(id).andCreaterEqualTo(userId)
		.andDelFlagEqualTo(false);
	if (sm.countByExample(se) == 0) {
	    return "要删除的数据不存在";
	}
	Shop shop = new Shop();
	shop.setId(id);
	shop.setLastEditor(userId);
	shop.setUpdateDate(new Date(System.currentTimeMillis()));
	sm.updateByPrimaryKeySelective(shop);
	return null;
    }

    @Override
    public String deleteUserShopByFlag(String id) {
	String userId = userService.getCurUserId();
	if (StringUtils.isBlank(userId)) {
	    return "请先登录";
	}
	ShopExample se = new ShopExample();
	se.or().andIdEqualTo(id).andCreaterEqualTo(userId)
		.andDelFlagEqualTo(false);
	if (sm.countByExample(se) == 0) {
	    return "要删除的数据不存在";
	}

	// 删除商铺
	Shop shop = new Shop();
	shop.setId(id);
	shop.setDelFlag(true);
	shop.setLastEditor(userId);
	sm.updateByPrimaryKeySelective(shop);

	// 删除图片
	ShopImage si = new ShopImage();
	si.setDelFlag(true);
	si.setLastEditor(shop.getLastEditor());
	ShopImageExample ie = new ShopImageExample();
	ie.or().andShopIdEqualTo(id).andDelFlagEqualTo(false);
	sim.updateByExampleSelective(si, ie);
	return null;
    }

    @Override
    public Map<String, String> addShop(Shop shop) {
	
	Map<String, String> resMap = new HashMap<String, String>();
	if (shop == null) {
	    resMap.put("msg", "商铺内容不能为空");
	    return resMap;
	}
	String curUserId = userService.getCurUserId();
	if (StringUtils.isBlank(curUserId)) {
	    resMap.put("msg", "请先登录");
	    return resMap;
	}
	resMap = checkShop(shop);
	if (!resMap.isEmpty()) {
	    return resMap;
	}
	shop.setId(UUID.randomUUID().toString());
	shop.setCreater(curUserId);
	shop.setLastEditor(curUserId);
	sm.insertSelective(shop);

	return resMap;
    }

    private Map<String, String> checkShop(Shop shop) {
	Map<String, String> resMap = new HashMap<String, String>();
	return resMap;
    }

    @Override
    public Map<String, String> updateShop(Shop shop) {
	
	
	Map<String, String> resMap = new HashMap<String, String>();
	if (shop == null) {
	    resMap.put("msg", "商铺内容不能为空");
	    return resMap;
	}
	String curUserId = userService.getCurUserId();
	if (StringUtils.isBlank(curUserId)) {
	    resMap.put("msg", "请先登录");
	    return resMap;
	}
	resMap = checkShop(shop);
	if (!resMap.isEmpty()) {
	    return resMap;
	}
	shop.setLastEditor(curUserId);
	sm.updateByPrimaryKeySelective(shop);

	return resMap;
    }

    @Override
    public int updateBatchShop(String shopids, Shop shop) {
	
	if (shop == null) {
	    return 0;
	}

	String[] ids = shopids.split(Constants.MI_IDS_SPLIT_STRING);
	List<String> list = new ArrayList<String>();
	for (String id : ids) {
	    list.add(id);
	}
	String curUserId = userService.getCurUserId();
	ShopExample example = new ShopExample();
	example.or().andIdIn(list).andDelFlagEqualTo(false);
	
	shop.setLastEditor(curUserId);
	
	int row = sm.updateByExampleSelective(shop, example);
	
	Boolean delFlag = shop.getDelFlag();
	
	if(delFlag!=null && delFlag == true){
	    ShopImageExample sie = new ShopImageExample();
	    sie.or().andShopIdIn(list).andDelFlagEqualTo(false);
	    ShopImage si = new ShopImage();
	    si.setDelFlag(true);
	    si.setLastEditor(userService.getCurUserId());
	    sim.updateByExampleSelective(si, sie);
	    

	    ShopPanoExample spe = new ShopPanoExample();
	    spe.or().andShopIdIn(list).andDelFlagEqualTo(false);
	    ShopPano sp = new ShopPano();
	    sp.setDelFlag(true);
	    sp.setLastEditor(userService.getCurUserId());
	    spm.updateByExampleSelective(sp, spe);
	}
	return row;
    }
}
