package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.WarehouseImageMapper;
import com.mimi.zfw.mybatis.dao.WarehouseMapper;
import com.mimi.zfw.mybatis.dao.WarehousePanoMapper;
import com.mimi.zfw.mybatis.pojo.Warehouse;
import com.mimi.zfw.mybatis.pojo.WarehouseExample;
import com.mimi.zfw.mybatis.pojo.WarehouseImage;
import com.mimi.zfw.mybatis.pojo.WarehouseImageExample;
import com.mimi.zfw.mybatis.pojo.WarehousePano;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.service.IWarehouseService;

@Service
public class WarehouseServiceImpl extends
		BaseService<Warehouse, WarehouseExample, String> implements
		IWarehouseService {

	@Resource
	private WarehouseMapper wm;

	@Resource
	private WarehouseImageMapper wim;

	@Resource
	private WarehousePanoMapper wpm;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<Warehouse, WarehouseExample, String> baseDao) {
		this.baseDao = baseDao;
		this.wm = (WarehouseMapper) baseDao;
	}

	@Override
	public void initWarehouse() {
		WarehouseExample warehousee = new WarehouseExample();
		warehousee.or().andDelFlagEqualTo(false);
		int count = wm.countByExample(warehousee);
		if (count < 1) {
			initTestData();
		}		
	}
	
	private void initTestData(){
		String[] imgUrl = {
				"https://farm3.staticflickr.com/2567/5697107145_3c27ff3cd1_b.jpg",
				"https://farm2.staticflickr.com/1043/5186867718_06b2e9e551_b.jpg",
				"https://farm6.staticflickr.com/5023/5578283926_822e5e5791_b.jpg",
				"https://farm7.staticflickr.com/6175/6176698785_7dee72237e_b.jpg",
				"https://farm2.staticflickr.com/1043/5186867718_06b2e9e551_b.jpg" };
		String[] preImgUrl = {
				"https://farm3.staticflickr.com/2567/5697107145_3c27ff3cd1_m.jpg",
				"https://farm2.staticflickr.com/1043/5186867718_06b2e9e551_m.jpg",
				"https://farm6.staticflickr.com/5023/5578283926_822e5e5791_m.jpg",
				"https://farm7.staticflickr.com/6175/6176698785_7dee72237e_m.jpg",
				"https://farm2.staticflickr.com/1043/5186867718_06b2e9e551_m.jpg" };
		for (int i = 0; i < 50; i++) {
			Warehouse warehouse = new Warehouse();
			warehouse.setId(UUID.randomUUID().toString());
			warehouse.setName("厂房仓库名称" + RandomUtils.nextInt(100));
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
			warehouse.setRegion(region);
			warehouse.setPhoneNum("12332112310");
			warehouse.setRental(RandomUtils.nextInt(20000) + 1000);
			warehouse.setTotalPrice(RandomUtils.nextInt(500) + 20);
			warehouse.setGrossFloorArea((float) (Math.random() * 10000 + 50));

			warehouse.setAddress("地址阿里山的房间卡死的解放路卡死地方");
			warehouse.setIntroduction("介绍阿里斯顿房间爱死了的阿斯兰的上的");

			double rosTemp = Math.random();
			String rentOrSale;
			if (rosTemp > 0.6) {
				rentOrSale = Constants.ROS_RENT_ONLY;
				warehouse.setTotalPrice(null);
			} else if (rosTemp > 0.3) {
				rentOrSale = Constants.ROS_SALE_ONLY;
				warehouse.setRental(null);
			} else {
				rentOrSale = Constants.ROS_RENT_AND_SALE;
			}
			warehouse.setRentOrSale(rentOrSale);

			double typeTemp = Math.random();
			String type;
			if (typeTemp > 0.5) {
				type = Constants.WAREHOUSE_TYPE_CF;
			} else {
				type = Constants.WAREHOUSE_TYPE_CK;
			}
			warehouse.setType(type);
			warehouse.setOutOfDate(RandomUtils.nextBoolean());
			warehouse.setDescription("描述啊楼上的房间啊拉伸到房间拉克丝放大镜");
			warehouse.setTags("标签1,标签2,标签3");
			warehouse.setPriority(RandomUtils.nextInt(100));
			warehouse.setPreImageUrl("http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg");

			wm.insertSelective(warehouse);

			for (int ki = 0; ki < imgUrl.length; ki++) {
				WarehouseImage wi = new WarehouseImage();
				wi.setId(UUID.randomUUID().toString());
				wi.setWarehouseId(warehouse.getId());
				wi.setContentUrl(imgUrl[ki]);
				wi.setName("蝴蝶");
				wi.setDescription("描述爱丽丝疯狂就阿斯兰的房间啦拉伸到卡机上了k");

				WarehousePano wp = new WarehousePano();
				wp.setId(UUID.randomUUID().toString());
				wp.setWarehouseId(warehouse.getId());
				wp.setContentUrl("http://www.baidu.com");
				wp.setPreImageUrl(preImgUrl[ki]);
				wp.setName("哇哇");
				wp.setDescription("水电费水电费阿斯蒂芬 ");

				wim.insertSelective(wi);
				wpm.insertSelective(wp);
			}
		}
	}

	@Override
	public List<Warehouse> findWarehousesByParams(String keyWord, String region,
			String grossFloorArea, String type, String rentOrSale,
			String rental, String totalPrice, String orderBy,
			Integer targetPage, Integer pageSize) {
		WarehouseExample warehousee = bindWarehouseParams(keyWord, region, grossFloorArea, type,
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
		warehousee.setOrderByClause(orderByClause);
		warehousee.setLimitStart(targetPage * pageSize);
		warehousee.setLimitSize(pageSize);
		return wm.selectByExample(warehousee);
	}

	@Override
	public int countWarehouseByParams(String keyWord, String region,
			String grossFloorArea, String type, String rentOrSale,
			String rental, String totalPrice) {
		WarehouseExample warehousee = bindWarehouseParams(keyWord, region, grossFloorArea, type,
				rentOrSale, rental, totalPrice);
		return wm.countByExample(warehousee);
	}
	private WarehouseExample bindWarehouseParams(String keyWord, String region,
			String grossFloorArea, String type, String rentOrSale,
			String rental, String totalPrice) {
		WarehouseExample warehousee = new WarehouseExample();
		WarehouseExample.Criteria cri = warehousee.createCriteria();
		cri.andDelFlagEqualTo(false);
		bindWarehouseParams(cri, region, grossFloorArea, type, rentOrSale, rental, totalPrice);
		if (StringUtils.isNotBlank(keyWord)) {
			cri.andNameLike("%" + keyWord + "%");
			
			WarehouseExample.Criteria cri2 = warehousee.createCriteria();
			cri2.andAddressLike("%" + keyWord + "%").andDelFlagEqualTo(false);
			bindWarehouseParams(cri2, region, grossFloorArea, type, rentOrSale, rental, totalPrice);
			warehousee.or(cri2);
		}
		return warehousee;
	}
	
	private WarehouseExample.Criteria bindWarehouseParams(WarehouseExample.Criteria cri,String region,
			String grossFloorArea, String type, String rentOrSale,
			String rental, String totalPrice) {
		if (StringUtils.isNotBlank(region)) {
			cri.andRegionEqualTo(region);
		}
		if (StringUtils.isNotBlank(grossFloorArea)) {
			String[] values = grossFloorArea.split(":");
			if (values.length > 1 && StringUtils.isNumeric(values[0])
					&& StringUtils.isNumeric(values[1])) {
				cri.andGrossFloorAreaBetween(Float.valueOf(values[0]),
						Float.valueOf(values[1]));
			}
		}
		if (StringUtils.isNotBlank(type)) {
			cri.andTypeEqualTo(type);
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
		}
		if (StringUtils.isNotBlank(rental)
				&& Constants.ROS_RENT_ONLY.equals(rentOrSale)) {
			String[] values = rental.split(":");
			if (values.length > 1 && StringUtils.isNumeric(values[0])
					&& StringUtils.isNumeric(values[1])) {
				cri.andRentalBetween(Integer.valueOf(values[0]),
						Integer.valueOf(values[1]));
			}
		}
		if (StringUtils.isNotBlank(totalPrice)
				&& Constants.ROS_SALE_ONLY.equals(rentOrSale)) {
			String[] values = totalPrice.split(":");
			if (values.length > 1 && StringUtils.isNumeric(values[0])
					&& StringUtils.isNumeric(values[1])) {
				cri.andTotalPriceBetween(Integer.valueOf(values[0]),
						Integer.valueOf(values[1]));
			}
		}
		return cri;
	}

	@Override
	public List<Warehouse> getByUserId(String userId, Integer targetPage,
			Integer pageSize) {
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		WarehouseExample warehousee = new WarehouseExample();
		warehousee.or().andCreaterEqualTo(userId).andDelFlagEqualTo(false);
		if (targetPage != null && pageSize != null) {
			warehousee.setLimitStart(targetPage * pageSize);
			warehousee.setLimitSize(pageSize);
		}
		return wm.selectByExample(warehousee);
	}

	@Override
	public int countByUserId(String userId) {
		if (StringUtils.isBlank(userId)) {
			return 0;
		}
		WarehouseExample warehousee = new WarehouseExample();
		warehousee.or().andCreaterEqualTo(userId).andDelFlagEqualTo(false);
		return wm.countByExample(warehousee);
	}

	@Override
	public String saveCascading(Warehouse warehouse, String imgUrls) {

		String userId = userService.getCurUserId();
		if (StringUtils.isBlank(userId)) {
			return "请先登录";
		}
		if(StringUtils.isBlank(warehouse.getName())){
			return "标题不能为空";
		}
		if(StringUtils.isBlank(warehouse.getPhoneNum())){
			return "电话不能为空";
		}
		if(warehouse.getTotalPrice()==null && warehouse.getRental()==null){
			return "租金和售价不能同时为空";
		}

		// 创建商铺
		warehouse.setId(UUID.randomUUID().toString());
		warehouse.setCreater(userId);
		warehouse.setLastEditor(userId);
		wm.insertSelective(warehouse);

		long timeMillis = System.currentTimeMillis();
		// 创建商铺图片
		if (StringUtils.isNotBlank(imgUrls)) {
			String[] urls = imgUrls.split(Constants.IMAGE_URLS_SPLIT_STRING);
			for (int i = 0; i < urls.length; i++) {
				if (StringUtils.isNotBlank(urls[i])) {
					WarehouseImage wi = new WarehouseImage();
					wi.setId(UUID.randomUUID().toString());
					wi.setContentUrl(urls[i]);
					wi.setCreater(userId);
					wi.setLastEditor(warehouse.getLastEditor());
					wi.setWarehouseId(warehouse.getId());
					wi.setName(warehouse.getName());
					Date nowDate = new Date(++timeMillis);
					wi.setCreateDate(nowDate);
					wi.setUpdateDate(nowDate);
					wim.insertSelective(wi);
				}
			}
		}
		return null;
	}

	@Override
	public String updateCascading(Warehouse warehouse, String imgUrls) {
		String userId = userService.getCurUserId();
		if (StringUtils.isBlank(userId)) {
			return "请先登录";
		}
		if(StringUtils.isBlank(warehouse.getName())){
			return "标题不能为空";
		}
		if(StringUtils.isBlank(warehouse.getPhoneNum())){
			return "电话不能为空";
		}
		if(warehouse.getTotalPrice()==null && warehouse.getRental()==null){
			return "租金和售价不能同时为空";
		}
		
		// 更新商铺
		warehouse.setLastEditor(userId);
		wm.updateByPrimaryKeySelective(warehouse);

		// 更新商铺图片
		WarehouseImageExample ie = new WarehouseImageExample();
		ie.or().andWarehouseIdEqualTo(warehouse.getId()).andDelFlagEqualTo(false);
		List<WarehouseImage> oldImgs = wim.selectByExample(ie);
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
						WarehouseImage wi = new WarehouseImage();
						wi.setId(UUID.randomUUID().toString());
						wi.setContentUrl(urls[i]);
						wi.setCreater(warehouse.getLastEditor());
						wi.setLastEditor(warehouse.getLastEditor());
						wi.setWarehouseId(warehouse.getId());
						wi.setName(warehouse.getName());
						Date nowDate = new Date(++timeMillis);
						wi.setCreateDate(nowDate);
						wi.setUpdateDate(nowDate);
						wim.insertSelective(wi);
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
			WarehouseImageExample ie2 = new WarehouseImageExample();
			ie2.or().andIdIn(delIds).andDelFlagEqualTo(false);
			WarehouseImage wi = new WarehouseImage();
			wi.setDelFlag(true);
			wi.setLastEditor(warehouse.getLastEditor());
			wim.updateByExampleSelective(wi, ie2);
		}
		return null;
	}

	@Override
	public String refreshUserWarehouse(String id) {
		String userId = userService.getCurUserId();
		if (StringUtils.isBlank(userId)) {
			return "请先登录";
		}
		WarehouseExample warehousee = new WarehouseExample();
		warehousee.or().andIdEqualTo(id).andCreaterEqualTo(userId)
				.andDelFlagEqualTo(false);
		if (wm.countByExample(warehousee) == 0) {
			return "要删除的数据不存在";
		}
		Warehouse warehouse = new Warehouse();
		warehouse.setId(id);
		warehouse.setLastEditor(userId);
		warehouse.setUpdateDate(new Date(System.currentTimeMillis()));
		wm.updateByPrimaryKeySelective(warehouse);
		return null;
	}

	@Override
	public String deleteUserWarehouseByFlag(String id) {
		String userId = userService.getCurUserId();
		if (StringUtils.isBlank(userId)) {
			return "请先登录";
		}
		WarehouseExample warehousee = new WarehouseExample();
		warehousee.or().andIdEqualTo(id).andCreaterEqualTo(userId)
				.andDelFlagEqualTo(false);
		if (wm.countByExample(warehousee) == 0) {
			return "要删除的数据不存在";
		}

		// 删除商铺
		Warehouse warehouse = new Warehouse();
		warehouse.setId(id);
		warehouse.setDelFlag(true);
		warehouse.setLastEditor(userId);
		wm.updateByPrimaryKeySelective(warehouse);

		// 删除图片
		WarehouseImage wi = new WarehouseImage();
		wi.setDelFlag(true);
		wi.setLastEditor(warehouse.getLastEditor());
		WarehouseImageExample ie = new WarehouseImageExample();
		ie.or().andWarehouseIdEqualTo(id).andDelFlagEqualTo(false);
		wim.updateByExampleSelective(wi, ie);
		return null;
	}

}
