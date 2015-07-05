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
import com.mimi.zfw.mybatis.dao.OBImageMapper;
import com.mimi.zfw.mybatis.dao.OBPanoMapper;
import com.mimi.zfw.mybatis.dao.OfficeBuildingMapper;
import com.mimi.zfw.mybatis.pojo.OBImage;
import com.mimi.zfw.mybatis.pojo.OBImageExample;
import com.mimi.zfw.mybatis.pojo.OBPano;
import com.mimi.zfw.mybatis.pojo.OfficeBuilding;
import com.mimi.zfw.mybatis.pojo.OfficeBuildingExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IOfficeBuildingService;
import com.mimi.zfw.service.IUserService;

@Service
public class OfficeBuildingServiceImpl extends
		BaseService<OfficeBuilding, OfficeBuildingExample, String> implements
		IOfficeBuildingService {

	@Resource
	private OfficeBuildingMapper obm;
	@Resource
	private OBImageMapper obim;

	@Resource
	private OBPanoMapper obpm;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<OfficeBuilding, OfficeBuildingExample, String> baseDao) {
		this.baseDao = baseDao;
		this.obm = (OfficeBuildingMapper) baseDao;
	}

	@Override
	public void initOfficeBuilding() {
		OfficeBuildingExample obe = new OfficeBuildingExample();
		obe.or().andDelFlagEqualTo(false);
		int count = obm.countByExample(obe);
		if (count < 1) {
			initTestData();
		}
	}
	private void initTestData(){
		String[] imgUrl = Constants.ALIYUN_OSS_TEST_IMG_URLS;
		String[] preImgUrl = Constants.ALIYUN_OSS_TEST_IMG_URLS;
		for (int i = 0; i < 50; i++) {
			OfficeBuilding ob = new OfficeBuilding();
			ob.setId(UUID.randomUUID().toString());
			ob.setName("写字楼名称" + RandomUtils.nextInt(100));
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
			ob.setRegion(region);
			ob.setPhoneNum("12332112310");
			ob.setRental(RandomUtils.nextInt(10000) + 1000);
			ob.setTotalPrice(RandomUtils.nextInt(100) + 20);
			ob.setGrossFloorArea((float) (Math.random() * 1000 + 50));

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
			ob.setDecorationStatus(decorationStatus);
			ob.setAddress("地址阿里山的房间卡死的解放路卡死地方");
			ob.setIntroduction("介绍阿里斯顿房间爱死了的阿斯兰的上的");

			double rosTemp = Math.random();
			String rentOrSale;
			if (rosTemp > 0.6) {
				rentOrSale = Constants.ROS_RENT_ONLY;
				ob.setTotalPrice(null);
			} else if (rosTemp > 0.3) {
				rentOrSale = Constants.ROS_SALE_ONLY;
				ob.setRental(null);
			} else {
				rentOrSale = Constants.ROS_RENT_AND_SALE;
			}
			ob.setRentOrSale(rentOrSale);
			ob.setPropertyFee(RandomUtils.nextInt(20)
					+ RandomUtils.nextFloat());

			double typeTemp = Math.random();
			String type;
			if (typeTemp > 0.8) {
				type = Constants.OFFICE_BUILDING_TYPE_CHUN;
			} else if (typeTemp > 0.6) {
				type = Constants.OFFICE_BUILDING_TYPE_JD;
			} else if (typeTemp > 0.4) {
				type = Constants.OFFICE_BUILDING_TYPE_SYL;
			} else {
				type = Constants.OFFICE_BUILDING_TYPE_ZHT;
			}
			ob.setType(type);
			ob.setOutOfDate(RandomUtils.nextBoolean());
			ob.setDescription("描述啊楼上的房间啊拉伸到房间拉克丝放大镜");
			ob.setTags("标签1,标签2,标签3");
			ob.setPriority(RandomUtils.nextInt(100));
			ob.setPreImageUrl(Constants.ALIYUN_OSS_TEST_IMG_URLS[2]);

			obm.insertSelective(ob);

			for (int ki = 0; ki < imgUrl.length; ki++) {
				OBImage obi = new OBImage();
				obi.setId(UUID.randomUUID().toString());
				obi.setOfficeBuildingId(ob.getId());
				obi.setContentUrl(imgUrl[ki]);
				obi.setName("蝴蝶");
				obi.setDescription("描述爱丽丝疯狂就阿斯兰的房间啦拉伸到卡机上了k");

				OBPano obp = new OBPano();
				obp.setId(UUID.randomUUID().toString());
				obp.setOfficeBuildingId(ob.getId());
				obp.setContentUrl("http://www.baidu.com");
				obp.setPreImageUrl(preImgUrl[ki]);
				obp.setName("哇哇");
				obp.setDescription("水电费水电费阿斯蒂芬 ");

				obim.insertSelective(obi);
				obpm.insertSelective(obp);
			}
		}
	}

	@Override
	public List<OfficeBuilding> findOfficeBuildingsByParams(String keyWord, String region,
			String grossFloorArea, String type, String rentOrSale,
			String rental, String totalPrice, String orderBy,
			Integer targetPage, Integer pageSize) {
		OfficeBuildingExample obe = bindOfficeBuildingParams(keyWord, region, grossFloorArea, type,
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
		obe.setOrderByClause(orderByClause);
		obe.setLimitStart(targetPage * pageSize);
		obe.setLimitSize(pageSize);
		return obm.selectByExample(obe);
	}

	@Override
	public int countOfficeBuildingByParams(String keyWord, String region,
			String grossFloorArea, String type, String rentOrSale,
			String rental, String totalPrice) {
		OfficeBuildingExample obe = bindOfficeBuildingParams(keyWord, region, grossFloorArea, type,
				rentOrSale, rental, totalPrice);
		return obm.countByExample(obe);
	}
	private OfficeBuildingExample bindOfficeBuildingParams(String keyWord, String region,
			String grossFloorArea, String type, String rentOrSale,
			String rental, String totalPrice) {
		OfficeBuildingExample obe = new OfficeBuildingExample();
		OfficeBuildingExample.Criteria cri = obe.createCriteria();
		cri.andDelFlagEqualTo(false);
		bindOfficeBuildingParams(cri, region, grossFloorArea, type, rentOrSale, rental, totalPrice);
		if (StringUtils.isNotBlank(keyWord)) {
			cri.andNameLike("%" + keyWord + "%");
			
			OfficeBuildingExample.Criteria cri2 = obe.createCriteria();
			cri2.andAddressLike("%" + keyWord + "%").andDelFlagEqualTo(false);
			bindOfficeBuildingParams(cri2, region, grossFloorArea, type, rentOrSale, rental, totalPrice);
			obe.or(cri2);
		}
		return obe;
	}
	
	private OfficeBuildingExample.Criteria bindOfficeBuildingParams(OfficeBuildingExample.Criteria cri,String region,
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
//
//	private OfficeBuildingExample bindOfficeBuildingParams(String keyWord, String region,
//			String grossFloorArea, String type, String rentOrSale,
//			String rental, String totalPrice) {
//		OfficeBuildingExample obe = new OfficeBuildingExample();
//		OfficeBuildingExample.Criteria cri = obe.createCriteria();
//		cri.andDelFlagEqualTo(false);
//		OfficeBuildingExample.Criteria cri2 = null;
//		if (StringUtils.isNotBlank(keyWord)) {
//			cri.andNameLike("%" + keyWord + "%");
//			cri2 = obe.createCriteria();
//			cri2.andAddressLike("%" + keyWord + "%").andDelFlagEqualTo(false);
//		}
//		if (StringUtils.isNotBlank(region)) {
//			cri.andRegionEqualTo(region);
//			if (cri2 != null) {
//				cri2.andRegionEqualTo(region);
//			}
//		}
//		if (StringUtils.isNotBlank(grossFloorArea)) {
//			String[] values = grossFloorArea.split(":");
//			if (values.length > 1 && StringUtils.isNumeric(values[0])
//					&& StringUtils.isNumeric(values[1])) {
//				cri.andGrossFloorAreaBetween(Float.valueOf(values[0]),
//						Float.valueOf(values[1]));
//				if (cri2 != null) {
//					cri2.andGrossFloorAreaBetween(Float.valueOf(values[0]),
//							Float.valueOf(values[1]));
//				}
//			}
//		}
//		if (StringUtils.isNotBlank(type)) {
//			cri.andTypeEqualTo(type);
//			if (cri2 != null) {
//				cri2.andTypeEqualTo(type);
//			}
//		}
//		if (StringUtils.isNotBlank(rentOrSale)) {
//			if (Constants.ROS_RENT_AND_SALE.equals(rentOrSale)) {
//				cri.andRentOrSaleEqualTo(rentOrSale);
//			} else {
//				List<String> rentOrSales = new ArrayList<String>();
//				rentOrSales.add(rentOrSale);
//				rentOrSales.add(Constants.ROS_RENT_AND_SALE);
//				cri.andRentOrSaleIn(rentOrSales);
//			}
//			if (cri2 != null) {
//				if (Constants.ROS_RENT_AND_SALE.equals(rentOrSale)) {
//					cri2.andRentOrSaleEqualTo(rentOrSale);
//				} else {
//					List<String> rentOrSales = new ArrayList<String>();
//					rentOrSales.add(rentOrSale);
//					rentOrSales.add(Constants.ROS_RENT_AND_SALE);
//					cri2.andRentOrSaleIn(rentOrSales);
//				}
//			}
//		}
//		if (StringUtils.isNotBlank(rental)
//				&& Constants.ROS_RENT_ONLY.equals(rentOrSale)) {
//			String[] values = rental.split(":");
//			if (values.length > 1 && StringUtils.isNumeric(values[0])
//					&& StringUtils.isNumeric(values[1])) {
//				cri.andRentalBetween(Integer.valueOf(values[0]),
//						Integer.valueOf(values[1]));
//				if (cri2 != null) {
//					cri2.andRentalBetween(Integer.valueOf(values[0]),
//							Integer.valueOf(values[1]));
//				}
//			}
//		}
//		if (StringUtils.isNotBlank(totalPrice)
//				&& Constants.ROS_SALE_ONLY.equals(rentOrSale)) {
//			String[] values = totalPrice.split(":");
//			if (values.length > 1 && StringUtils.isNumeric(values[0])
//					&& StringUtils.isNumeric(values[1])) {
//				cri.andTotalPriceBetween(Integer.valueOf(values[0]),
//						Integer.valueOf(values[1]));
//				if (cri2 != null) {
//					cri2.andTotalPriceBetween(Integer.valueOf(values[0]),
//							Integer.valueOf(values[1]));
//				}
//			}
//		}
//		if (cri2 != null) {
//			obe.or(cri2);
//		}
//		return obe;
//	}

	@Override
	public List<OfficeBuilding> getByUserId(String userId, Integer targetPage,
			Integer pageSize) {
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		OfficeBuildingExample obe = new OfficeBuildingExample();
		obe.or().andCreaterEqualTo(userId).andDelFlagEqualTo(false);
		if (targetPage != null && pageSize != null) {
			obe.setLimitStart(targetPage * pageSize);
			obe.setLimitSize(pageSize);
		}
		return obm.selectByExample(obe);
	}

	@Override
	public int countByUserId(String userId) {
		if (StringUtils.isBlank(userId)) {
			return 0;
		}
		OfficeBuildingExample obe = new OfficeBuildingExample();
		obe.or().andCreaterEqualTo(userId).andDelFlagEqualTo(false);
		return obm.countByExample(obe);
	}

	@Override
	public String saveCascading(OfficeBuilding ob, String imgUrls) {

		String userId = userService.getCurUserId();
		if (StringUtils.isBlank(userId)) {
			return "请先登录";
		}
		if(StringUtils.isBlank(ob.getName())){
			return "标题不能为空";
		}
		if(StringUtils.isBlank(ob.getPhoneNum())){
			return "电话不能为空";
		}
		if(ob.getTotalPrice()==null && ob.getRental()==null){
			return "租金和售价不能同时为空";
		}

		// 创建商铺
		ob.setId(UUID.randomUUID().toString());
		ob.setCreater(userId);
		ob.setLastEditor(userId);
		obm.insertSelective(ob);

		long timeMillis = System.currentTimeMillis();
		// 创建商铺图片
		if (StringUtils.isNotBlank(imgUrls)) {
			String[] urls = imgUrls.split(Constants.IMAGE_URLS_SPLIT_STRING);
			for (int i = 0; i < urls.length; i++) {
				if (StringUtils.isNotBlank(urls[i])) {
					OBImage obi = new OBImage();
					obi.setId(UUID.randomUUID().toString());
					obi.setContentUrl(urls[i]);
					obi.setCreater(userId);
					obi.setLastEditor(ob.getLastEditor());
					obi.setOfficeBuildingId(ob.getId());
					obi.setName(ob.getName());
					timeMillis = timeMillis + 1000;
					Date nowDate = new Date(timeMillis);
					obi.setCreateDate(nowDate);
					obi.setUpdateDate(nowDate);
					obim.insertSelective(obi);
				}
			}
		}
		return null;
	}

	@Override
	public String updateCascading(OfficeBuilding ob, String imgUrls) {
		String userId = userService.getCurUserId();
		if (StringUtils.isBlank(userId)) {
			return "请先登录";
		}
		if(StringUtils.isBlank(ob.getName())){
			return "标题不能为空";
		}
		if(StringUtils.isBlank(ob.getPhoneNum())){
			return "电话不能为空";
		}
		if(ob.getTotalPrice()==null && ob.getRental()==null){
			return "租金和售价不能同时为空";
		}
		
		// 更新商铺
		ob.setLastEditor(userId);
		obm.updateByPrimaryKeySelective(ob);

		// 更新商铺图片
		OBImageExample ie = new OBImageExample();
		ie.or().andOfficeBuildingIdEqualTo(ob.getId()).andDelFlagEqualTo(false);
		List<OBImage> oldImgs = obim.selectByExample(ie);
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
						OBImage obi = new OBImage();
						obi.setId(UUID.randomUUID().toString());
						obi.setContentUrl(urls[i]);
						obi.setCreater(ob.getLastEditor());
						obi.setLastEditor(ob.getLastEditor());
						obi.setOfficeBuildingId(ob.getId());
						obi.setName(ob.getName());
						timeMillis = timeMillis + 1000;
						Date nowDate = new Date(timeMillis);
						obi.setCreateDate(nowDate);
						obi.setUpdateDate(nowDate);
						obim.insertSelective(obi);
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
			OBImageExample ie2 = new OBImageExample();
			ie2.or().andIdIn(delIds).andDelFlagEqualTo(false);
			OBImage obi = new OBImage();
			obi.setDelFlag(true);
			obi.setLastEditor(ob.getLastEditor());
			obim.updateByExampleSelective(obi, ie2);
		}
		return null;
	}

	@Override
	public String refreshUserOB(String id) {
		String userId = userService.getCurUserId();
		if (StringUtils.isBlank(userId)) {
			return "请先登录";
		}
		OfficeBuildingExample obe = new OfficeBuildingExample();
		obe.or().andIdEqualTo(id).andCreaterEqualTo(userId)
				.andDelFlagEqualTo(false);
		if (obm.countByExample(obe) == 0) {
			return "要删除的数据不存在";
		}
		OfficeBuilding ob = new OfficeBuilding();
		ob.setId(id);
		ob.setLastEditor(userId);
		ob.setUpdateDate(new Date(System.currentTimeMillis()));
		obm.updateByPrimaryKeySelective(ob);
		return null;
	}

	@Override
	public String deleteUserOBByFlag(String id) {
		String userId = userService.getCurUserId();
		if (StringUtils.isBlank(userId)) {
			return "请先登录";
		}
		OfficeBuildingExample obe = new OfficeBuildingExample();
		obe.or().andIdEqualTo(id).andCreaterEqualTo(userId)
				.andDelFlagEqualTo(false);
		if (obm.countByExample(obe) == 0) {
			return "要删除的数据不存在";
		}

		// 删除商铺
		OfficeBuilding ob = new OfficeBuilding();
		ob.setId(id);
		ob.setDelFlag(true);
		ob.setLastEditor(userId);
		obm.updateByPrimaryKeySelective(ob);

		// 删除图片
		OBImage obi = new OBImage();
		obi.setDelFlag(true);
		obi.setLastEditor(ob.getLastEditor());
		OBImageExample ie = new OBImageExample();
		ie.or().andOfficeBuildingIdEqualTo(id).andDelFlagEqualTo(false);
		obim.updateByExampleSelective(obi, ie);
		return null;
	}

}
