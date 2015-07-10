package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.HTImageMapper;
import com.mimi.zfw.mybatis.dao.HTPanoMapper;
import com.mimi.zfw.mybatis.dao.HTRingMapper;
import com.mimi.zfw.mybatis.dao.HouseTypeMapper;
import com.mimi.zfw.mybatis.dao.REPAvgPriceHistoryMapper;
import com.mimi.zfw.mybatis.dao.REPImageMapper;
import com.mimi.zfw.mybatis.dao.REPPanoMapper;
import com.mimi.zfw.mybatis.dao.REPVideoMapper;
import com.mimi.zfw.mybatis.dao.RealEstateProjectMapper;
import com.mimi.zfw.mybatis.dao.RelationREPAndInformationMapper;
import com.mimi.zfw.mybatis.dao.RelationUserAndREPMapper;
import com.mimi.zfw.mybatis.pojo.HTImage;
import com.mimi.zfw.mybatis.pojo.HTPano;
import com.mimi.zfw.mybatis.pojo.HTRing;
import com.mimi.zfw.mybatis.pojo.HouseType;
import com.mimi.zfw.mybatis.pojo.REPAvgPriceHistory;
import com.mimi.zfw.mybatis.pojo.REPImage;
import com.mimi.zfw.mybatis.pojo.REPPano;
import com.mimi.zfw.mybatis.pojo.REPVideo;
import com.mimi.zfw.mybatis.pojo.RealEstateProject;
import com.mimi.zfw.mybatis.pojo.RealEstateProjectExample;
import com.mimi.zfw.mybatis.pojo.RelationUserAndREP;
import com.mimi.zfw.mybatis.pojo.RealEstateProjectExample.Criteria;
import com.mimi.zfw.mybatis.pojo.RelationREPAndInformation;
import com.mimi.zfw.mybatis.pojo.RelationREPAndInformationExample;
import com.mimi.zfw.mybatis.pojo.RelationUserAndREPExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IRealEstateProjectService;
import com.mimi.zfw.service.IUserService;

@Service
public class RealEstateProjectServiceImpl extends
		BaseService<RealEstateProject, RealEstateProjectExample, String>
		implements IRealEstateProjectService {

	@Resource
	private RealEstateProjectMapper repm;
	
	@Resource
	private REPImageMapper repim;
	
	@Resource
	private REPPanoMapper reppm;
	
	@Resource
	private REPVideoMapper repvm;

	@Resource
	private HouseTypeMapper htm;
	
	@Resource
	private HTImageMapper htim;
	
	@Resource
	private HTPanoMapper htpm;
	
	@Resource
	private HTRingMapper htrm;
	
	@Resource
	private RelationREPAndInformationMapper rrim;
	
	@Resource
	private RelationUserAndREPMapper rurm;
	
	@Resource
	private REPAvgPriceHistoryMapper raphm;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<RealEstateProject, RealEstateProjectExample, String> baseDao) {
		this.baseDao = baseDao;
		this.repm = (RealEstateProjectMapper) baseDao;
	}

	@Override
	public void initRealEstateProject() {
		RealEstateProjectExample repe = new RealEstateProjectExample();
		repe.or().andDelFlagEqualTo(false);
		int count = repm.countByExample(repe);
		if (count < 1) {
			initTestData();
		}
	}

	private void initTestData() {
		String[] addressList = {
				"地址1阿斯兰的房间爱斯兰的房间爱死斯兰的房间爱死斯兰的房间爱死斯兰的房间爱死斯兰的房间爱死斯兰的房间爱死死了看房",
				"地址1阿斯兰的房间爱死了看房", "地址1阿斯兰的房间爱死了看房", "地址1阿斯兰的房间爱死了看房",
				"地址1阿斯兰的房间爱死了看房", "地址1阿斯兰s水电费啊啊啊的房间爱死了看房",
				"地址1阿斯兰的房间斯兰的房间斯兰的房间爱死了看房", "地址1阿斯兰的房间爱死了看房", "地址1阿斯兰的房间爱死了看房",
				"地址1阿斯兰的房间爱死了看房" };
		String[] nameList = { "敏捷城", "海逸他他1其1其他他1其他半岛", "星湖名郡",
				"其他其他1他1其他1其11", "其他1", "其他1", "其他1", "其他1", "其他1", "其他1" };
		int[] averagePriceList = { 4600, 1234, 5123, 2414, 5123, 2414, 5123,
				2414, 5123, 2414 };
		String[] buildingTypeList = {
				Constants.BUILDING_TYPE_BL + "," + Constants.BUILDING_TYPE_DPBS,
				Constants.BUILDING_TYPE_BL + "," + Constants.BUILDING_TYPE_DPBS,
				Constants.BUILDING_TYPE_BL + "," + Constants.BUILDING_TYPE_DPBS,
				Constants.BUILDING_TYPE_BL + "," + Constants.BUILDING_TYPE_DPBS,
				Constants.BUILDING_TYPE_BL + "," + Constants.BUILDING_TYPE_DPBS,
				Constants.BUILDING_TYPE_BL + "," + Constants.BUILDING_TYPE_DPBS,
				Constants.BUILDING_TYPE_BL + "," + Constants.BUILDING_TYPE_DPBS,
				Constants.BUILDING_TYPE_BL + "," + Constants.BUILDING_TYPE_DPBS,
				Constants.BUILDING_TYPE_BL + "," + Constants.BUILDING_TYPE_DPBS,
				Constants.BUILDING_TYPE_BL + "," + Constants.BUILDING_TYPE_DPBS };
		String[] decorationStatusList = { Constants.DECORATION_STATUS_HH,
				Constants.DECORATION_STATUS_JD, Constants.DECORATION_STATUS_MP,
				Constants.DECORATION_STATUS_JD, Constants.DECORATION_STATUS_MP,
				Constants.DECORATION_STATUS_HH, Constants.DECORATION_STATUS_JD,
				Constants.DECORATION_STATUS_MP, Constants.DECORATION_STATUS_JD,
				Constants.DECORATION_STATUS_MP };
		String[] developerList = { "开发商甲阿里山空房里山空房间爱蓝山啡", "开发商甲阿里山空房间爱蓝山咖啡",
				"开发商甲阿里山空房间爱蓝山咖啡", "开发商甲阿里山空房间爱蓝山咖啡", "开发商甲阿里山空房间爱蓝山咖啡",
				"开发商甲阿里山空房间爱蓝山咖啡", "开发商甲阿里山空房间爱蓝山咖啡", "开发商甲阿里山空房间爱蓝山咖啡",
				"开发商甲阿里山空房间爱蓝山咖啡", "开发商甲阿里山空房间爱蓝山咖啡" };
		float[] floorAreaRatioList = { 1.2f, 2.0f, 1.5f, 1.2f, 2.0f, 1.5f,
				1.2f, 2.0f, 1.5f, 2.5f };
		float[] greenRateList = { 0.2f, 0.3f, 0.5f, 0.2f, 0.3f, 0.5f, 0.2f,
				0.3f, 0.5f, 0.5f };
		int[] householdNumList = { 152, 125, 513, 2156, 1234, 23, 152, 125,
				513, 2156, 1234, 23 };
		String[] introductionList = { "水电费了卡接收到阿什利打飞机阿斯顿浪费空间阿什利打飞机阿什利打飞机",
				"水电费了卡接收到阿什利打飞机阿斯顿浪费空间阿什利打飞机阿什利打飞机",
				"水电费了卡接收到阿什利打飞机阿斯顿浪费空间阿什利打飞机阿什利打飞机",
				"水电费了卡接收到阿什利打飞机阿斯顿浪费空间阿什利打飞机阿什利打飞机",
				"水电费了卡接收到阿什利打飞机阿斯顿浪费空间阿什利打飞机阿什利打飞机",
				"水电费了卡接收到阿什利打飞机阿斯顿浪费空间阿什利打飞机阿什利打飞机",
				"水电费了卡接收到阿什利打飞机阿斯顿浪费空间阿什利打飞机阿什利打飞机",
				"水电费了卡接收到阿什利打飞机阿斯顿浪费空间阿什利打飞机阿什利打飞机",
				"水电费了卡接收到阿什利打飞机阿斯顿浪费空间阿什利打飞机阿什利打飞机",
				"水电费了卡接收到阿什利打飞机阿斯顿浪费空间阿什利打飞机阿什利打飞机" };
		// String[] onSaleDateList = { "预计2015年6月14日1-3、5#开盘",
		// "预计2015年6月14日1-3、5#开盘", "预计2015年6月14日1-3、5#开盘",
		// "预计2015年6月14日1-3、5#开盘", "预计2015年6月14日1-3、5#开盘",
		// "预计2015年6月14日1-3、5#开盘", "预计2015年6月14日1-3、5#开盘",
		// "预计2015年6月14日1-3、5#开盘", "预计2015年6月14日1-3、5#开盘",
		// "预计2015年6月14日1-3、5#开盘" };
		// String[] onReadyDateList = { "预计2015年下半年一期9栋楼交房",
		// "预计2015年下半年一期9栋楼交房",
		// "预计2015年下半年一期9栋楼交房", "预计2015年下半年一期9栋楼交房", "预计2015年下半年一期9栋楼交房",
		// "预计2015年下半年一期9栋楼交房", "预计2015年下半年一期9栋楼交房", "预计2015年下半年一期9栋楼交房",
		// "预计2015年下半年一期9栋楼交房", "预计2015年下半年一期9栋楼交房" };
		int[] parkingSpaceNumList = { 123, 125, 521, 21, 5124, 123, 125, 521,
				21, 5124 };
//		String[] preImageUrlList = {
//				"http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg",
//				"http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg",
//				"http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg",
//				"http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg",
//				"http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg",
//				"http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg",
//				"http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg",
//				"http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg",
//				"http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg",
//				"http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg" };
		String[] preImageUrlList = Constants.ALIYUN_OSS_TEST_IMG_URLS;
		String[] preSalePermitList = { "京房售证字(2014)188号", "京房售证字(2014)188号",
				"京房售证字(2014)188号", "京房售证字(2014)188号", "京房售证字(2014)188号",
				"京房售证字(2014)188号", "京房售证字(2014证字(2014)188号", "京房售证字(2014)188号",
				"京房售证字(2014)188号", "京房售证字(2014)188号" };
		int[] priorityList = { 1, 21, 52, 123, 512, 125, 51, 12, 45, 61 };
		String[] propertyCompanyList = { "北京首开鸿城实业有限公司", "北京首开鸿城实业有限公司",
				"北京首开鸿城实业有限公司", "北京首开鸿城实鸿城实业有公司", "北京首开鸿城实业有限公司",
				"北京首开鸿城实业有限公司", "北京首开鸿城实业有限公司", "北京首开鸿城实业有限公司", "北京首开鸿城实业有限公司",
				"北京首开鸿城实业有限公司" };
		float[] propertyFeeList = { 1.2f, 2.0f, 1.5f, 1.2f, 2.0f, 1.5f, 1.2f,
				2.0f, 1.5f, 2.5f };
//		String[] imgUrl = {"https://farm3.staticflickr.com/2567/5697107145_3c27ff3cd1_b.jpg","https://farm2.staticflickr.com/1043/5186867718_06b2e9e551_b.jpg","https://farm6.staticflickr.com/5023/5578283926_822e5e5791_b.jpg","https://farm7.staticflickr.com/6175/6176698785_7dee72237e_b.jpg","https://farm2.staticflickr.com/1043/5186867718_06b2e9e551_b.jpg"};
//		String[] preImgUrl = {"https://farm3.staticflickr.com/2567/5697107145_3c27ff3cd1_m.jpg","https://farm2.staticflickr.com/1043/5186867718_06b2e9e551_m.jpg","https://farm6.staticflickr.com/5023/5578283926_822e5e5791_m.jpg","https://farm7.staticflickr.com/6175/6176698785_7dee72237e_m.jpg","https://farm2.staticflickr.com/1043/5186867718_06b2e9e551_m.jpg"};
		String[] imgUrl = Constants.ALIYUN_OSS_TEST_IMG_URLS;
		String[] preImgUrl = Constants.ALIYUN_OSS_TEST_IMG_URLS;
//		Date nowDate = new Date(System.currentTimeMillis());

		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < nameList.length; i++) {
				//楼盘
				RealEstateProject rep = new RealEstateProject();
				rep.setId(UUID.randomUUID().toString());
//				rep.setCreateDate(nowDate);

				if (i == 3) {
					repm.insertSelective(rep);
					continue;

				}
				rep.setName(nameList[i]);
				rep.setAddress(addressList[i]);
				rep.setAveragePrice(averagePriceList[i]);
				rep.setBuildingType(buildingTypeList[i]);
				rep.setDecorationStatus(decorationStatusList[i]);
				rep.setDeveloper(developerList[i]);
				rep.setFloorAreaRatio(floorAreaRatioList[i]);
				rep.setGreenRate(greenRateList[i]);
				rep.setHouseholdNum(householdNumList[i]);
				rep.setIntroduction(introductionList[i]);

				float lat = (float) (Math.random() * 0.05);
				float lon = (float) (Math.random() * 0.1);
				lat += 23.05;
				lon += 112.42;
				rep.setLatitude(lat);
				rep.setLongitude(lon);

				rep.setMaxRoomGrossFloorArea((int) (Math.random() * 100)+100);
				rep.setMinRoomGrossFloorArea((int) (rep
						.getMaxRoomGrossFloorArea() - Math.random() * 100));

				Calendar cal = Calendar.getInstance();
				cal.set(2015, (int) (Math.random() * 11),
						(int) (Math.random() * 25));
				rep.setOnSaleDate(cal.getTime());

				cal.set(2016, (int) (Math.random() * 11),
						(int) (Math.random() * 25));
				rep.setOnReadyDate(cal.getTime());
				rep.setParkingSpaceNum(parkingSpaceNumList[i]);
				rep.setPreImageUrl(preImageUrlList[i]);
				rep.setPreSalePermit(preSalePermitList[i]);
				rep.setPriority(priorityList[i]);
				rep.setPropertyCompany(propertyCompanyList[i]);
				rep.setPropertyFee(propertyFeeList[i]);
				rep.setPropertyType(Constants.PROPERTY_TYPE_ZZXSPF);
				rep.setPropertyYears(70);

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
				rep.setRegion(region);

				double ssTemp = Math.random();
				String saleStatus = "";
				if (ssTemp > 0.6) {
					saleStatus = Constants.SALE_STATUS_DS;
				} else if (ssTemp > 0.3) {
					saleStatus = Constants.SALE_STATUS_SW;
				} else {
					saleStatus = Constants.SALE_STATUS_ZS;
				}
				rep.setSaleStatus(saleStatus);
				rep.setSurrounding("对伐啦斯蒂芬路径拉伸到房间拉伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯克丝阿里斯顿福建省");
				rep.setTags("标签1,标签2,标签3");
				rep.setTel("12312312312");
				rep.setTraffic("公交：425路、927路、38路、268路、831路、834路、56路、126路、66路、76A路、864路、223路 地铁：2号线黄边站");
				rep.setOneRoomNum((int) (Math.random() * 10));
				rep.setTwoRoomNum((int) (Math.random() * 10));
				rep.setThreeRoomNum((int) (Math.random() * 10));
				rep.setFourRoomNum((int) (Math.random() * 10));
				rep.setFiveRoomNum((int) (Math.random() * 10));
				rep.setOverFiveRoomNum((int) (Math.random() * 10));
				repm.insertSelective(rep);

				//户型
				int tempRGFA = rep.getMaxRoomGrossFloorArea()
						- rep.getMinRoomGrossFloorArea();
				for (int k = 0; k < 10; k++) {
					HouseType ht = new HouseType();
					ht.setId(UUID.randomUUID().toString());
//					ht.setCreateDate(nowDate);
					ht.setRealEstateProjectId(rep.getId());
					ht.setRealEstateProjectName(rep.getName());

					ht.setDescription("描述阿里山的房间了看阿什利打飞机阿什利打飞机拉氏底鳉蓝山咖啡的加了少看点卡拉斯");

					ht.setAveragePrice(rep.getAveragePrice());
					ht.setGrossFloorArea((float) (tempRGFA * Math.random() + rep
							.getMinRoomGrossFloorArea()));
					ht.setHallNum((int) (Math.random() * 2 + 1));
					ht.setInsideArea((float) (ht.getGrossFloorArea() * (0.7 + Math
							.random() * 0.2)));
					ht.setKitchenNum((int) (Math.random() * 2));
					ht.setName("户型阿斯顿");
					ht.setPreImageUrl("http://img2.imgtn.bdimg.com/it/u=4157877028,1827198230&fm=21&gp=0.jpg");
					ht.setPriority((int) (Math.random() * 50));
					ht.setRegion(region);
					ht.setRoomNum((int) (Math.random() * 5 + 1));
					ht.setSaleStatus(saleStatus);
					ht.setTags("标签1,标签2,标签3");
					ht.setToiletNum((int) (Math.random() + 1));

					
					for(int ki=0;ki<imgUrl.length;ki++){
						HTImage hi = new HTImage();
						hi.setId(UUID.randomUUID().toString());
//						hi.setCreateDate(nowDate);
						hi.setHouseTypeId(ht.getId());
						hi.setContentUrl(imgUrl[ki]);
						hi.setName("蝴蝶");
						hi.setDescription("描述爱丽丝疯狂就阿斯兰的房间啦拉伸到卡机上了k");
						
						HTPano hp = new HTPano();
						hp.setId(UUID.randomUUID().toString());
//						hp.setCreateDate(nowDate);
						hp.setHouseTypeId(ht.getId());
						hp.setContentUrl("http://www.baidu.com");
						hp.setPreImageUrl(preImgUrl[ki]);
						hp.setName("哇哇");
						hp.setDescription("水电费水电费阿斯蒂芬 ");
						
						HTRing hr = new HTRing();
						hr.setId(UUID.randomUUID().toString());
//						hr.setCreateDate(nowDate);
						hr.setHouseTypeId(ht.getId());
						hr.setContentUrl("http://www.baidu.com");
						hr.setPreImageUrl(preImgUrl[ki]);
						hr.setName("嘻嘻");
						hr.setDescription("是打发第三方");
						
						htim.insertSelective(hi);
						htpm.insertSelective(hp);
						htrm.insertSelective(hr);
					}
					
					htm.insertSelective(ht);
				}
				
				for(int v=0;v<imgUrl.length;v++){
					REPVideo rv = new REPVideo();
					rv.setId(UUID.randomUUID().toString());
					rv.setRealEstateProjectId(rep.getId());
//					rv.setCreateDate(nowDate);
					rv.setContentUrl("http://www.baidu.com");
					rv.setPreImageUrl(preImgUrl[v]);
					
					repvm.insertSelective(rv);
					
					REPPano rp = new REPPano();
					rp.setId(UUID.randomUUID().toString());
					rp.setRealEstateProjectId(rep.getId());
//					rp.setCreateDate(nowDate);
					rp.setContentUrl("http://www.baidu.com");
					rp.setPreImageUrl(preImgUrl[v]);
					
					reppm.insertSelective(rp);

					for(int vv=0;vv<(int)(Math.random()*3);vv++){
						REPImage ri = new REPImage();
						ri.setId(UUID.randomUUID().toString());
						ri.setRealEstateProjectId(rep.getId());
//						ri.setCreateDate(nowDate);
						ri.setContentUrl(imgUrl[v]);
						ri.setType(Constants.REP_IMAGE_TYPE_JTT);
						repim.insertSelective(ri);
					}

					for(int vv=0;vv<(int)(Math.random()*3);vv++){
						REPImage ri = new REPImage();
						ri.setId(UUID.randomUUID().toString());
						ri.setRealEstateProjectId(rep.getId());
//						ri.setCreateDate(nowDate);
						ri.setContentUrl(imgUrl[v]);
						ri.setType(Constants.REP_IMAGE_TYPE_PTT);
						repim.insertSelective(ri);
					}

					for(int vv=0;vv<(int)(Math.random()*3);vv++){
						REPImage ri = new REPImage();
						ri.setId(UUID.randomUUID().toString());
						ri.setRealEstateProjectId(rep.getId());
//						ri.setCreateDate(nowDate);
						ri.setContentUrl(imgUrl[v]);
						ri.setType(Constants.REP_IMAGE_TYPE_SJT);
						repim.insertSelective(ri);
					}

					for(int vv=0;vv<(int)(Math.random()*3);vv++){
						REPImage ri = new REPImage();
						ri.setId(UUID.randomUUID().toString());
						ri.setRealEstateProjectId(rep.getId());
//						ri.setCreateDate(nowDate);
						ri.setContentUrl(imgUrl[v]);
						ri.setType(Constants.REP_IMAGE_TYPE_XGT);
						repim.insertSelective(ri);
					}
				}
			}
		}
	}

	@Override
	public List<RealEstateProject> findRealEstateProjectByParams(
			String keyWord, String region, String averagePrice,
			Integer roomNum, String grossFloorArea, String saleStatus,
			String bound, String orderBy, int targetPage, int pageSize) {
		RealEstateProjectExample repe = bindRealEstateProjectParams(keyWord,
				region, averagePrice, roomNum, grossFloorArea, saleStatus,
				bound);
		String orderByClause = "";
		if (StringUtils.isNotBlank(orderBy)) {
			if ("priceFromLow".equals(orderBy)) {
				orderByClause = "average_price asc,";
			} else if ("priceFromHigh".equals(orderBy)) {
				orderByClause = "average_price desc,";
			} else if ("onSaleFromNear".equals(orderBy)) {
				orderByClause = "on_sale_date desc,";
			} else if ("onSaleFromFar".equals(orderBy)) {
				orderByClause = "on_sale_date asc,";
			}
		}
		orderByClause += "priority desc";
		repe.setOrderByClause(orderByClause);
		repe.setLimitStart(targetPage * pageSize);
		repe.setLimitSize(pageSize);
		return repm.selectByExample(repe);
	}

	@Override
	public int countRealEstateProjectByParams(String keyWord, String region,
			String averagePrice, Integer roomNum, String grossFloorArea,
			String saleStatus, String bound) {
		RealEstateProjectExample repe = bindRealEstateProjectParams(keyWord,
				region, averagePrice, roomNum, grossFloorArea, saleStatus,
				bound);
		return repm.countByExample(repe);
	}

	private RealEstateProjectExample bindRealEstateProjectParams(
			String keyWord, String region, String averagePrice,
			Integer roomNum, String grossFloorArea, String saleStatus,
			String bound) {
		RealEstateProjectExample repe = new RealEstateProjectExample();
		Criteria cri = repe.createCriteria();
		cri.andDelFlagEqualTo(false);
		Criteria cri2 = null;
		if (StringUtils.isNotBlank(keyWord)) {
			cri.andNameLike("%" + keyWord + "%");
			cri2 = repe.createCriteria();
			cri2.andAddressLike("%" + keyWord + "%").andDelFlagEqualTo(false);
		}
		if (StringUtils.isNotBlank(region)) {
			cri.andRegionEqualTo(region);
			if (cri2 != null) {
				cri2.andRegionEqualTo(region);
			}
		}
		if (StringUtils.isNotBlank(averagePrice)) {
			String[] values = averagePrice.split(":");
			if (values.length > 1 && StringUtils.isNumeric(values[0])
					&& StringUtils.isNumeric(values[1])) {
				cri.andAveragePriceBetween(Integer.valueOf(values[0]),
						Integer.valueOf(values[1]));
				if (cri2 != null) {
					cri2.andAveragePriceBetween(Integer.valueOf(values[0]),
							Integer.valueOf(values[1]));
				}
			}
		}
		if (roomNum != null) {
			if (roomNum == 1) {
				cri.andOneRoomNumGreaterThan(0);
				if (cri2 != null) {
					cri2.andOneRoomNumGreaterThan(0);
				}
			} else if (roomNum == 2) {
				cri.andTwoRoomNumGreaterThan(0);
				if (cri2 != null) {
					cri2.andTwoRoomNumGreaterThan(0);
				}
			} else if (roomNum == 3) {
				cri.andThreeRoomNumGreaterThan(0);
				if (cri2 != null) {
					cri2.andThreeRoomNumGreaterThan(0);
				}
			} else if (roomNum == 4) {
				cri.andFourRoomNumGreaterThan(0);
				if (cri2 != null) {
					cri2.andFourRoomNumGreaterThan(0);
				}
			} else if (roomNum == 5) {
				cri.andFiveRoomNumGreaterThan(0);
				if (cri2 != null) {
					cri2.andFiveRoomNumGreaterThan(0);
				}
			} else if (roomNum == 6) {
				cri.andOverFiveRoomNumGreaterThan(0);
				if (cri2 != null) {
					cri2.andOverFiveRoomNumGreaterThan(0);
				}
			}
		}
		if (StringUtils.isNotBlank(grossFloorArea)) {
			String[] values = grossFloorArea.split(":");
			if (values.length > 1 && StringUtils.isNumeric(values[0])
					&& StringUtils.isNumeric(values[1])) {
				cri.andMinRoomGrossFloorAreaLessThanOrEqualTo(
						Integer.valueOf(values[1]))
						.andMaxRoomGrossFloorAreaGreaterThanOrEqualTo(
								Integer.valueOf(values[0]));
				if (cri2 != null) {
					cri2.andMinRoomGrossFloorAreaLessThanOrEqualTo(
							Integer.valueOf(values[1]))
							.andMaxRoomGrossFloorAreaGreaterThanOrEqualTo(
									Integer.valueOf(values[0]));
				}
			}
		}
		if (StringUtils.isNotBlank(saleStatus)) {
			cri.andSaleStatusEqualTo(saleStatus);
			if (cri2 != null) {
				cri2.andSaleStatusEqualTo(saleStatus);
			}
		}
		if (StringUtils.isNotBlank(bound)) {
			String[] values = bound.split(":");
			if (values.length > 3) {
				cri.andLongitudeGreaterThan(Float.valueOf(values[0]));
				cri.andLatitudeGreaterThan(Float.valueOf(values[1]));
				cri.andLongitudeLessThanOrEqualTo(Float.valueOf(values[2]));
				cri.andLatitudeLessThanOrEqualTo(Float.valueOf(values[3]));
				if (cri2 != null) {
					cri2.andLongitudeGreaterThan(Float.valueOf(values[0]));
					cri2.andLatitudeGreaterThan(Float.valueOf(values[1]));
					cri2.andLongitudeLessThanOrEqualTo(Float.valueOf(values[2]));
					cri2.andLatitudeLessThanOrEqualTo(Float.valueOf(values[3]));
				}
			}
		}
		if (cri2 != null) {
			repe.or(cri2);
		}
		return repe;
	}

	@Override
	public List<RealEstateProject> getREPByInfoId(String id) {
		if(StringUtils.isBlank(id)){
			return null;
		}
		RelationREPAndInformationExample rrie = new RelationREPAndInformationExample();
		rrie.or().andInformationIdEqualTo(id).andDelFlagEqualTo(false);
		List<RelationREPAndInformation> relations = rrim.selectByExample(rrie);
		if(relations!=null && !relations.isEmpty()){
			List<String> repIds = new ArrayList<String>();
			for(int i=0;i<relations.size();i++){
				repIds.add(relations.get(i).getRealEstateProjectId());
			}
			if(!repIds.isEmpty()){
				RealEstateProjectExample repe = new RealEstateProjectExample();
				repe.or().andIdIn(repIds).andDelFlagEqualTo(false);
				List<RealEstateProject> list = repm.selectByExample(repe);
				return list;
			}
		}
		return null;
	}

	@Override
	public Map<String, String> addREP(RealEstateProject rep, String userIds,
			String infoIds) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (rep == null) {
			resMap.put("msg", "楼盘内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(rep);
		if(!resMap.isEmpty()){
			return resMap;
		}
		rep.setId(UUID.randomUUID().toString());
		rep.setCreater(curUserId);
		rep.setLastEditor(curUserId);
		repm.insertSelective(rep);
		addREPAvgPriceHistory(rep);
		if (StringUtils.isNotBlank(infoIds)) {
			String[] ids = infoIds.split(Constants.MI_IDS_SPLIT_STRING);
			for (String infoId : ids) {
				if (StringUtils.isNotBlank(infoId)) {
					RelationREPAndInformation rri = new RelationREPAndInformation();
					rri.setId(UUID.randomUUID().toString());
					rri.setInformationId(infoId);
					rri.setRealEstateProjectId(rep.getId());
					rri.setCreater(curUserId);
					rri.setLastEditor(curUserId);
					rrim.insertSelective(rri);
				}
			}
		}
		if (StringUtils.isNotBlank(userIds)) {
			String[] ids = userIds.split(Constants.MI_IDS_SPLIT_STRING);
			for (String userId : ids) {
				if (StringUtils.isNotBlank(userId)) {
					RelationUserAndREP rur = new RelationUserAndREP();
					rur.setId(UUID.randomUUID().toString());
					rur.setUserId(userId);
					rur.setRealEstateProjectId(rep.getId());
					rur.setCreater(curUserId);
					rur.setLastEditor(curUserId);
					rurm.insertSelective(rur);
				}
			}
		}
		return resMap;
	}

	@Override
	public Map<String, String> updateREP(RealEstateProject rep,
			String addUserRelations, String delUserRelations,
			String addInfoRelations, String delInfoRelations) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (rep == null) {
			resMap.put("msg", "楼盘内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(rep);
		if(!resMap.isEmpty()){
			return resMap;
		}
		rep.setLastEditor(curUserId);
		repm.updateByPrimaryKeySelective(rep);
		addREPAvgPriceHistory(rep);
		if (StringUtils.isNotBlank(addInfoRelations)) {
			String[] ids = addInfoRelations.split(Constants.MI_IDS_SPLIT_STRING);
			for (String infoId : ids) {
				if (StringUtils.isNotBlank(infoId)) {
					RelationREPAndInformation rri = new RelationREPAndInformation();
					rri.setId(UUID.randomUUID().toString());
					rri.setInformationId(infoId);
					rri.setRealEstateProjectId(rep.getId());
					rri.setCreater(curUserId);
					rri.setLastEditor(curUserId);
					rrim.insertSelective(rri);
				}
			}
		}
		if (StringUtils.isNotBlank(addUserRelations)) {
			String[] ids = addUserRelations.split(Constants.MI_IDS_SPLIT_STRING);
			for (String userId : ids) {
				if (StringUtils.isNotBlank(userId)) {
					RelationUserAndREP rur = new RelationUserAndREP();
					rur.setId(UUID.randomUUID().toString());
					rur.setUserId(userId);
					rur.setRealEstateProjectId(rep.getId());
					rur.setCreater(curUserId);
					rur.setLastEditor(curUserId);
					rurm.insertSelective(rur);
				}
			}
		}
		if (StringUtils.isNotBlank(delInfoRelations)) {
			String[] ids = delInfoRelations.split(Constants.MI_IDS_SPLIT_STRING);
			List<String> idList = new ArrayList<String>();
			for (String infoId : ids) {
				if (StringUtils.isNotBlank(infoId)) {
					idList.add(infoId);
				}
			}
			if(!idList.isEmpty()){
				RelationREPAndInformationExample rrie = new RelationREPAndInformationExample();
				rrie.or().andInformationIdIn(idList).andRealEstateProjectIdEqualTo(rep.getId()).andDelFlagEqualTo(false);
				RelationREPAndInformation rri = new RelationREPAndInformation();
				rri.setLastEditor(curUserId);
				rri.setDelFlag(true);
				rrim.updateByExampleSelective(rri, rrie);
			}
		}
		if (StringUtils.isNotBlank(delUserRelations)) {
			String[] ids = delUserRelations.split(Constants.MI_IDS_SPLIT_STRING);
			List<String> idList = new ArrayList<String>();
			for (String userId : ids) {
				if (StringUtils.isNotBlank(userId)) {
					idList.add(userId);
				}
			}
			if(!idList.isEmpty()){
				RelationUserAndREPExample rure = new RelationUserAndREPExample();
				rure.or().andUserIdIn(idList).andRealEstateProjectIdEqualTo(rep.getId()).andDelFlagEqualTo(false);
				RelationUserAndREP rur = new RelationUserAndREP();
				rur.setLastEditor(curUserId);
				rur.setDelFlag(true);
				rurm.updateByExampleSelective(rur, rure);
			}
		}
		return resMap;
	}
	
	private void addREPAvgPriceHistory(RealEstateProject rep){
		if(rep!=null && rep.getAveragePrice()!=null){
			REPAvgPriceHistory raph = new REPAvgPriceHistory();
			raph.setId(UUID.randomUUID().toString());
			raph.setCreater(rep.getLastEditor());
			raph.setLastEditor(rep.getLastEditor());
			raph.setRealEstateProjectId(rep.getId());
			raph.setValue(rep.getAveragePrice());
			raphm.insertSelective(raph);
		}
	}
	
	private Map<String, String> checkInfo(RealEstateProject rep) {
		Map<String, String> resMap = new HashMap<String, String>();
		return resMap;
	}
}
