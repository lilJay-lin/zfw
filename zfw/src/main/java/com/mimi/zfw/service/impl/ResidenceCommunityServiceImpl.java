package com.mimi.zfw.service.impl;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.RCImageMapper;
import com.mimi.zfw.mybatis.dao.RHImageMapper;
import com.mimi.zfw.mybatis.dao.RHPanoMapper;
import com.mimi.zfw.mybatis.dao.RentalHousingMapper;
import com.mimi.zfw.mybatis.dao.ResidenceCommunityMapper;
import com.mimi.zfw.mybatis.dao.SHHImageMapper;
import com.mimi.zfw.mybatis.dao.SHHPanoMapper;
import com.mimi.zfw.mybatis.dao.SecondHandHouseMapper;
import com.mimi.zfw.mybatis.pojo.RCImage;
import com.mimi.zfw.mybatis.pojo.RHImage;
import com.mimi.zfw.mybatis.pojo.RHPano;
import com.mimi.zfw.mybatis.pojo.RentalHousing;
import com.mimi.zfw.mybatis.pojo.RentalHousingExample;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunityExample;
import com.mimi.zfw.mybatis.pojo.SHHImage;
import com.mimi.zfw.mybatis.pojo.SHHPano;
import com.mimi.zfw.mybatis.pojo.SecondHandHouse;
import com.mimi.zfw.mybatis.pojo.SecondHandHouseExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IResidenceCommunityService;

@Service
public class ResidenceCommunityServiceImpl extends
		BaseService<ResidenceCommunity, ResidenceCommunityExample, String>
		implements IResidenceCommunityService {

	@Resource
	private ResidenceCommunityMapper rcm;

	@Resource
	private RCImageMapper rcim;

	@Resource
	private SecondHandHouseMapper shhm;

	@Resource
	private SHHImageMapper shhim;

	@Resource
	private SHHPanoMapper shhpm;

	@Resource
	private RentalHousingMapper rhm;

	@Resource
	private RHImageMapper rhim;

	@Resource
	private RHPanoMapper rhpm;

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<ResidenceCommunity, ResidenceCommunityExample, String> baseDao) {
		this.baseDao = baseDao;
		this.rcm = (ResidenceCommunityMapper) baseDao;
	}

	@Override
	public void initResidenceCommunicity() {
		ResidenceCommunityExample rce = new ResidenceCommunityExample();
		rce.or().andDelFlagEqualTo(false);
		int count = rcm.countByExample(rce);
		if (count < 1) {
			initTestData();
		}
	}

	private void initTestData() {
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

		String[] names = { "百花园", "林隐天下", "比湖广场", "星湖名郡", "泰湖新城", "明珠花园",
				"伴月花园", "恒裕海湾", "荷花苑", "龙景花园" };
		String[] addressList = {
				"地址1阿斯兰的房间爱斯兰的房间爱死斯兰的房间爱死斯兰的房间爱死斯兰的房间爱死斯兰的房间爱死斯兰的房间爱死死了看房",
				"地址1阿斯兰的房间爱死了看房", "地址1阿斯兰的房间爱死了看房", "地址1阿斯兰的房间爱死了看房",
				"地址1阿斯兰的房间爱死了看房", "地址1阿斯兰s水电费啊啊啊的房间爱死了看房",
				"地址1阿斯兰的房间斯兰的房间斯兰的房间爱死了看房", "地址1阿斯兰的房间爱死了看房", "地址1阿斯兰的房间爱死了看房",
				"地址1阿斯兰的房间爱死了看房" };
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
		int[] householdNumList = { 152, 125, 513, 2156, 1234, 23, 152, 125,
				513, 2156, 1234, 23 };
		float[] floorAreaRatioList = { 1.2f, 2.0f, 1.5f, 1.2f, 2.0f, 1.5f,
				1.2f, 2.0f, 1.5f, 2.5f };
		float[] greenRateList = { 0.2f, 0.3f, 0.5f, 0.2f, 0.3f, 0.5f, 0.2f,
				0.3f, 0.5f, 0.5f };
		int[] parkingSpaceNumList = { 123, 125, 521, 21, 5124, 123, 125, 521,
				21, 5124 };
		float[] propertyFeeList = { 1.2f, 2.0f, 1.5f, 1.2f, 2.0f, 1.5f, 1.2f,
				2.0f, 1.5f, 2.5f };
		int[] averagePriceList = { 4600, 1234, 5123, 2414, 5123, 2414, 5123,
				2414, 5123, 2414 };
		for (int z = 0; z < 5; z++) {
			for (int i = 0; i < 10; i++) {
				// 小区
				ResidenceCommunity rc = new ResidenceCommunity();

				rc.setId(UUID.randomUUID().toString());
				rc.setName(names[i] + "_" + z);
				rc.setAddress(addressList[i]);
				rc.setOnSaleDate(new Date(
						(long) (System.currentTimeMillis() - Math.random() * 10
								* 365 * 24 * 60 * 60 * 1000)));
				rc.setPropertyType(Constants.PROPERTY_TYPE_ZZXSPF);
				rc.setBuildingType(buildingTypeList[i]);
				rc.setHouseholdNum(householdNumList[i]);
				rc.setFloorAreaRatio(floorAreaRatioList[i]);
				rc.setGreenRate(greenRateList[i]);
				rc.setParkingSpaceNum(parkingSpaceNumList[i]);
				rc.setPropertyYears(70);
				rc.setPropertyCompany("某某物业公司");
				rc.setPropertyFee(propertyFeeList[i]);
				rc.setIntroduction("介绍伐啦斯蒂芬路径拉伸到房间拉伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯克丝阿里斯顿福建省");
				rc.setSurrounding("介绍伐啦斯蒂芬路径拉伸到房间拉伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯克丝阿里斯顿福建省");
				rc.setTraffic("介绍伐啦斯蒂芬路径拉伸到房间拉伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯伸到房间拉克丝阿里斯克丝阿里斯顿福建省");

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
				rc.setRegion(region);

				float lat = (float) (Math.random() * 0.05);
				float lon = (float) (Math.random() * 0.1);
				lat += 23.05;
				lon += 112.42;
				rc.setLongitude(lon);
				rc.setLatitude(lat);

				rc.setShhAveragePrice(averagePriceList[i]);
				rc.setShhNum((int) (Math.random() * 50));
				rc.setShhOneRoomNum((int) (Math.random() * 10));
				rc.setShhTwoRoomNum((int) (Math.random() * 10));
				rc.setShhThreeRoomNum((int) (Math.random() * 10));
				rc.setShhFourRoomNum((int) (Math.random() * 10));
				rc.setShhFiveRoomNum((int) (Math.random() * 10));
				rc.setShhOverFiveRoomNum((int) (Math.random() * 10));
				rc.setShhMaxRoomGrossFloorArea((int) (Math.random() * 100) + 100);
				rc.setShhMinRoomGrossFloorArea((int) (rc
						.getShhMaxRoomGrossFloorArea() - Math.random() * 100));
				rc.setShhMinTotalPrice((int) (Math.random() * 50) + 20);
				rc.setShhMaxTotalPrice(rc.getShhMinTotalPrice()
						+ (int) (Math.random() * 50));

				rc.setRhAveragePrice(averagePriceList[i]);
				rc.setRhNum((int) (Math.random() * 50));
				rc.setRhOneRoomNum((int) (Math.random() * 10));
				rc.setRhTwoRoomNum((int) (Math.random() * 10));
				rc.setRhThreeRoomNum((int) (Math.random() * 10));
				rc.setRhFourRoomNum((int) (Math.random() * 10));
				rc.setRhFiveRoomNum((int) (Math.random() * 10));
				rc.setRhOverFiveRoomNum((int) (Math.random() * 10));
				rc.setRhMaxRoomGrossFloorArea((int) (Math.random() * 100) + 100);
				rc.setRhMinRoomGrossFloorArea((int) (rc
						.getRhMaxRoomGrossFloorArea() - Math.random() * 100));
				rc.setRhEntireRentNum((int) (Math.random() * 20));
				rc.setRhFlatShareNum((int) (Math.random() * 20));
				rc.setRhMinRental((int) (Math.random() * 1000 + 300));
				rc.setRhMaxRental(rc.getRhMinRental()
						+ (int) (Math.random() * 1000));

				rc.setTags("标签1,标签2,标签3");
				rc.setPriority((int) (Math.random() * 100));
				rc.setPreImageUrl("http://i3.sinaimg.cn/hs/2010/0901/S18375T1283345502659.jpg");

				for (int v = 0; v < imgUrl.length; v++) {
					RCImage ri = new RCImage();
					ri.setId(UUID.randomUUID().toString());
					ri.setResidenceCommunityId(rc.getId());
					ri.setContentUrl(imgUrl[v]);
					rcim.insertSelective(ri);
				}
				rcm.insertSelective(rc);

				// 二手房
				int tempRGFA = rc.getShhMaxRoomGrossFloorArea()
						- rc.getShhMinRoomGrossFloorArea();
				for (int k = 0; k < 10; k++) {
					SecondHandHouse shh = new SecondHandHouse();
					shh.setId(UUID.randomUUID().toString());
					shh.setResidenceCommunityId(rc.getId());
					shh.setResidenceCommunityName(rc.getName());
					
					Calendar cal = Calendar.getInstance();
					int tempDay = RandomUtils.nextInt(300);
					cal.add(Calendar.DAY_OF_YEAR, -tempDay);
					shh.setCreateDate(cal.getTime());
					shh.setUpdateDate(cal.getTime());
					shh.setOutOfDate(tempDay>=90);

					shh.setName("户型名_" + z + "_" + i + "_" + k);
					shh.setRegion(region);
					shh.setPhoneNum("1" + (int) (Math.random() * 99999)
							+ (int) (Math.random() * 99999));
					shh.setTotalPrice((int) (30 + Math.random() * 100));
					shh.setGrossFloorArea((float) (tempRGFA * Math.random() + rc
							.getShhMinRoomGrossFloorArea()));
					shh.setRoomNum((int) (Math.random() * 5 + 1));
					shh.setHallNum((int) (Math.random() * 2 + 1));
					shh.setToiletNum((int) (Math.random() + 1));

					double forwardTemp = Math.random();
					String forward;
					if (forwardTemp > 0.8) {
						forward = Constants.HOUSE_FORWARD_E;
					} else if (forwardTemp > 0.6) {
						forward = Constants.HOUSE_FORWARD_SN;
					} else if (forwardTemp > 0.4) {
						forward = Constants.HOUSE_FORWARD_EW;
					} else {
						forward = Constants.HOUSE_FORWARD_ES;
					}
					shh.setForward(forward);
					shh.setCurFloor((int) (Math.random() * 20));
					shh.setTotalFloor((int) (shh.getCurFloor() + Math.random() * 30));

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
					shh.setDecorationStatus(decorationStatus);
					shh.setAddress("建设三路与工农南路交汇处南侧（口岸大楼北侧） ");
					shh.setIntroduction("肇庆市位于广东省中西部，西江干流中下游，东部和东南部与佛山市、江门市接壤，西南与云浮市相连，西及西北与广西壮族自治区梧州市和贺州市交界，北部和东北部与清远市相邻。");
					shh.setDescription("描述阿里山的房间了看阿什利打飞机阿什利打飞机拉氏底鳉蓝山咖啡的加了少看点卡拉斯");
					shh.setTags("标签1,标签2,标签3");
					shh.setPriority((int) (Math.random() * 50));
					shh.setPreImageUrl("http://img2.imgtn.bdimg.com/it/u=4157877028,1827198230&fm=21&gp=0.jpg");

					for (int ki = 0; ki < imgUrl.length; ki++) {
						SHHImage si = new SHHImage();
						si.setId(UUID.randomUUID().toString());
						si.setSecondHandHouseId(shh.getId());
						si.setContentUrl(imgUrl[ki]);
						si.setName("蝴蝶");
						si.setDescription("描述爱丽丝疯狂就阿斯兰的房间啦拉伸到卡机上了k");

						SHHPano sp = new SHHPano();
						sp.setId(UUID.randomUUID().toString());
						sp.setSecondHandHouseId(shh.getId());
						sp.setContentUrl("http://www.baidu.com");
						sp.setPreImageUrl(preImgUrl[ki]);
						sp.setName("哇哇");
						sp.setDescription("水电费水电费阿斯蒂芬 ");

						shhim.insertSelective(si);
						shhpm.insertSelective(sp);
					}

					shhm.insertSelective(shh);
				}

				// 租房
				for (int k = 0; k < 10; k++) {
					RentalHousing rh = new RentalHousing();
					rh.setId(UUID.randomUUID().toString());
					rh.setResidenceCommunityId(rc.getId());
					rh.setResidenceCommunityName(rc.getName());

					Calendar cal = Calendar.getInstance();
					int tempDay = RandomUtils.nextInt(300);
					cal.add(Calendar.DAY_OF_YEAR, -tempDay);
					rh.setCreateDate(cal.getTime());
					rh.setUpdateDate(cal.getTime());
					rh.setOutOfDate(tempDay>=90);
					
					rh.setName("户型名_" + z + "_" + i + "_" + k);
					rh.setRegion(region);
					rh.setPhoneNum("1" + (int) (Math.random() * 99999)
							+ (int) (Math.random() * 99999));
					rh.setRental((int) (300 + Math.random() * 1000));
					rh.setGrossFloorArea((float) (tempRGFA * Math.random() + rc
							.getShhMinRoomGrossFloorArea()));
					rh.setRoomNum((int) (Math.random() * 5 + 1));
					rh.setHallNum((int) (Math.random() * 2 + 1));
					rh.setToiletNum((int) (Math.random() + 1));

					double forwardTemp = Math.random();
					String forward;
					if (forwardTemp > 0.8) {
						forward = Constants.HOUSE_FORWARD_E;
					} else if (forwardTemp > 0.6) {
						forward = Constants.HOUSE_FORWARD_SN;
					} else if (forwardTemp > 0.4) {
						forward = Constants.HOUSE_FORWARD_EW;
					} else {
						forward = Constants.HOUSE_FORWARD_ES;
					}
					rh.setForward(forward);
					rh.setCurFloor((int) (Math.random() * 20));
					rh.setTotalFloor((int) (rh.getCurFloor() + Math.random() * 30));

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
					rh.setDecorationStatus(decorationStatus);
					rh.setAddress("建设三路与工农南路交汇处南侧（口岸大楼北侧） ");
					rh.setIntroduction("肇庆市位于广东省中西部，西江干流中下游，东部和东南部与佛山市、江门市接壤，西南与云浮市相连，西及西北与广西壮族自治区梧州市和贺州市交界，北部和东北部与清远市相邻。");

					rh.setFacilityAirConditioner(RandomUtils.nextBoolean());
					rh.setFacilityBed(RandomUtils.nextBoolean());
					rh.setFacilityBroadband(RandomUtils.nextBoolean());
					rh.setFacilityHeater(RandomUtils.nextBoolean());
					rh.setFacilityHeating(RandomUtils.nextBoolean());
					rh.setFacilityRefrigerator(RandomUtils.nextBoolean());
					rh.setFacilityTv(RandomUtils.nextBoolean());
					rh.setFacilityWasher(RandomUtils.nextBoolean());

					rh.setLeaseWay(RandomUtils.nextBoolean() ? Constants.LEASE_WAY_SHARE
							: Constants.LEASE_WAY_WHOLE);

					rh.setDescription("描述阿里山的房间了看阿什利打飞机阿什利打飞机拉氏底鳉蓝山咖啡的加了少看点卡拉斯");
					rh.setTags("标签1,标签2,标签3");
					rh.setPriority((int) (Math.random() * 50));
					rh.setPreImageUrl("http://img2.imgtn.bdimg.com/it/u=4157877028,1827198230&fm=21&gp=0.jpg");

					for (int ki = 0; ki < imgUrl.length; ki++) {
						RHImage ri = new RHImage();
						ri.setId(UUID.randomUUID().toString());
						ri.setRentalHousingId(rh.getId());
						ri.setContentUrl(imgUrl[ki]);
						ri.setName("蝴蝶");
						ri.setDescription("描述爱丽丝疯狂就阿斯兰的房间啦拉伸到卡机上了k");

						RHPano rp = new RHPano();
						rp.setId(UUID.randomUUID().toString());
						rp.setRentalHousingId(rh.getId());
						rp.setContentUrl("http://www.baidu.com");
						rp.setPreImageUrl(preImgUrl[ki]);
						rp.setName("哇哇");
						rp.setDescription("水电费水电费阿斯蒂芬 ");

						rhim.insertSelective(ri);
						rhpm.insertSelective(rp);
					}

					rhm.insertSelective(rh);
				}

			}
		}
	}

	@Override
	public List<ResidenceCommunity> findResidenceCommunitiesByParams(
			String bound, String region, String shhTotalPrice,
			Integer shhRoomNum, String shhRoomGrossFloorArea, String rhRental,
			Integer rhRoomNum, String rhRoomGrossFloorArea) {
		ResidenceCommunityExample rce = new ResidenceCommunityExample();
		ResidenceCommunityExample.Criteria cri = rce.createCriteria();
		if (StringUtils.isNotBlank(bound)) {
			String[] values = bound.split(":");
			if (values.length > 3) {
				cri.andLongitudeGreaterThan(Float.valueOf(values[0]));
				cri.andLatitudeGreaterThan(Float.valueOf(values[1]));
				cri.andLongitudeLessThanOrEqualTo(Float.valueOf(values[2]));
				cri.andLatitudeLessThanOrEqualTo(Float.valueOf(values[3]));
			}
		}
		if (StringUtils.isNotBlank(region)) {
			cri.andRegionEqualTo(region);
		}
		if (StringUtils.isNotBlank(shhTotalPrice)) {
			String[] values = shhTotalPrice.split(":");
			if (values.length > 1 && StringUtils.isNumeric(values[0])
					&& StringUtils.isNumeric(values[1])) {
				cri.andShhMinTotalPriceLessThanOrEqualTo(
						Integer.valueOf(values[1]))
						.andShhMaxTotalPriceGreaterThanOrEqualTo(
								Integer.valueOf(values[0]));
			}
		}
		if (shhRoomNum != null) {
			if (shhRoomNum == 1) {
				cri.andShhOneRoomNumGreaterThan(0);
			} else if (shhRoomNum == 2) {
				cri.andShhTwoRoomNumGreaterThan(0);
			} else if (shhRoomNum == 3) {
				cri.andShhThreeRoomNumGreaterThan(0);
			} else if (shhRoomNum == 4) {
				cri.andShhFourRoomNumGreaterThan(0);
			} else if (shhRoomNum == 5) {
				cri.andShhFiveRoomNumGreaterThan(0);
			} else if (shhRoomNum == 6) {
				cri.andShhOverFiveRoomNumGreaterThan(0);
			}
		}
		if (StringUtils.isNotBlank(shhRoomGrossFloorArea)) {
			String[] values = shhRoomGrossFloorArea.split(":");
			if (values.length > 1 && StringUtils.isNumeric(values[0])
					&& StringUtils.isNumeric(values[1])) {
				cri.andShhMinRoomGrossFloorAreaLessThanOrEqualTo(
						Integer.valueOf(values[1]))
						.andShhMaxRoomGrossFloorAreaGreaterThanOrEqualTo(
								Integer.valueOf(values[0]));
			}
		}
		if (StringUtils.isNotBlank(rhRental)) {
			String[] values = rhRental.split(":");
			if (values.length > 1 && StringUtils.isNumeric(values[0])
					&& StringUtils.isNumeric(values[1])) {
				cri.andRhMinRentalLessThanOrEqualTo(Integer.valueOf(values[1]))
						.andRhMaxRentalGreaterThanOrEqualTo(
								Integer.valueOf(values[0]));
			}
		}
		if (rhRoomNum != null) {
			if (rhRoomNum == 1) {
				cri.andRhOneRoomNumGreaterThan(0);
			} else if (rhRoomNum == 2) {
				cri.andRhTwoRoomNumGreaterThan(0);
			} else if (rhRoomNum == 3) {
				cri.andRhThreeRoomNumGreaterThan(0);
			} else if (rhRoomNum == 4) {
				cri.andRhFourRoomNumGreaterThan(0);
			} else if (rhRoomNum == 5) {
				cri.andRhFiveRoomNumGreaterThan(0);
			} else if (rhRoomNum == 6) {
				cri.andRhOverFiveRoomNumGreaterThan(0);
			}
		}
		if (StringUtils.isNotBlank(rhRoomGrossFloorArea)) {
			String[] values = rhRoomGrossFloorArea.split(":");
			if (values.length > 1 && StringUtils.isNumeric(values[0])
					&& StringUtils.isNumeric(values[1])) {
				cri.andRhMinRoomGrossFloorAreaLessThanOrEqualTo(
						Integer.valueOf(values[1]))
						.andRhMaxRoomGrossFloorAreaGreaterThanOrEqualTo(
								Integer.valueOf(values[0]));
			}
		}
		rce.setOrderByClause("priority desc");
		return rcm.selectByExample(rce);
	}

	@Override
	public List<ResidenceCommunity> findByName(String name) {
		ResidenceCommunityExample rce = new ResidenceCommunityExample();
		rce.or().andNameLike("%"+name+"%").andDelFlagEqualTo(false);
		return rcm.selectByExample(rce);
	}

	@Override
	public ResidenceCommunity refreshResidenceCommunity(String id,boolean onShh,boolean onRh){
		ResidenceCommunity rc = rcm.selectByPrimaryKey(id);
		if(rc==null){
			return null;
		}
		if(onShh){
			SecondHandHouseExample shhe = new SecondHandHouseExample();
			shhe.or().andResidenceCommunityIdEqualTo(id).andDelFlagEqualTo(false);
			List<SecondHandHouse> shhList = shhm.selectByExample(shhe);
			int shhAveragePrice = 0;
			int shhNum = 0;
			int shhOneRoomNum = 0;
			int shhTwoRoomNum = 0;
			int shhThreeRoomNum = 0;
			int shhFourRoomNum = 0;
			int shhFiveRoomNum = 0;
			int shhOverFiveRoomNum = 0;
			int shhMaxRoomGrossFloorArea = 0;
			int shhMinRoomGrossFloorArea = 0;
			int shhMaxTotalPrice = 0;
			int shhMinTotalPrice = 0;
			if(shhList!=null && !shhList.isEmpty()){
				shhNum = shhList.size();
				int tempShhTotalPrice = 0;
				int tempShhTotalArea = 0;
				for(int i=0;i<shhList.size();i++){
					SecondHandHouse shh = shhList.get(i);
					if(shh.getRoomNum()!=null){
						switch(shh.getRoomNum()){
							case 0:break;
							case 1:shhOneRoomNum++;break;
							case 2:shhTwoRoomNum++;break;
							case 3:shhThreeRoomNum++;break;
							case 4:shhFourRoomNum++;break;
							case 5:shhFiveRoomNum++;break;
							default:shhOverFiveRoomNum++;
						}
					}
					if(shh.getGrossFloorArea()!=null){
						if(shh.getGrossFloorArea()>shhMaxRoomGrossFloorArea){
							shhMaxRoomGrossFloorArea = (int) shh.getGrossFloorArea().floatValue();
						}
						if(shh.getGrossFloorArea()<shhMinRoomGrossFloorArea){
							shhMinRoomGrossFloorArea = (int) shh.getGrossFloorArea().floatValue();
						}
					}
					if(shh.getTotalPrice()!=null){
						if(shh.getTotalPrice()>shhMaxTotalPrice){
							shhMaxTotalPrice = shh.getTotalPrice();
						}
						if(shh.getTotalPrice()<shhMinTotalPrice){
							shhMinTotalPrice = shh.getTotalPrice();
						}
					}
					if(shh.getGrossFloorArea()!=null && shh.getTotalPrice()!=null){
						tempShhTotalArea+=shh.getGrossFloorArea();
						tempShhTotalPrice+=shh.getTotalPrice();
					}
				}
				if(tempShhTotalArea!=0){
					shhAveragePrice = tempShhTotalPrice*10000/tempShhTotalArea;
				}
			}
			rc.setShhAveragePrice(shhAveragePrice);
			rc.setShhNum(shhNum);
			rc.setShhOneRoomNum(shhOneRoomNum);
			rc.setShhTwoRoomNum(shhTwoRoomNum);
			rc.setShhThreeRoomNum(shhThreeRoomNum);
			rc.setShhFourRoomNum(shhFourRoomNum);
			rc.setShhFiveRoomNum(shhFiveRoomNum);
			rc.setShhOverFiveRoomNum(shhOverFiveRoomNum);
			rc.setShhMaxRoomGrossFloorArea(shhMaxRoomGrossFloorArea);
			rc.setShhMinRoomGrossFloorArea(shhMinRoomGrossFloorArea);
			rc.setShhMinTotalPrice(shhMinTotalPrice);
			rc.setShhMaxTotalPrice(shhMaxTotalPrice);
		}
		if(onRh){
			RentalHousingExample rhe = new RentalHousingExample();
			rhe.or().andResidenceCommunityIdEqualTo(id).andDelFlagEqualTo(false);
			List<RentalHousing> rhList = rhm.selectByExample(rhe);
			int rhAveragePrice = 0;
			int rhNum = 0;
			int rhOneRoomNum = 0;
			int rhTwoRoomNum = 0;
			int rhThreeRoomNum = 0;
			int rhFourRoomNum = 0;
			int rhFiveRoomNum = 0;
			int rhOverFiveRoomNum = 0;
			int rhMaxRoomGrossFloorArea = 0;
			int rhMinRoomGrossFloorArea = 0;
			int rhMaxRental = 0;
			int rhMinRental = 0;
			int rhEntireRentNum = 0;
			int rhFlatShareNum = 0;
			if(rhList!=null && !rhList.isEmpty()){
				rhNum = rhList.size();
				int tempRhTotalPrice = 0;
				int tempRhNum = 0;
				for(int i=0;i<rhList.size();i++){
					RentalHousing rh = rhList.get(i);
					if(rh.getRoomNum()!=null){
						switch(rh.getRoomNum()){
							case 0:break;
							case 1:rhOneRoomNum++;break;
							case 2:rhTwoRoomNum++;break;
							case 3:rhThreeRoomNum++;break;
							case 4:rhFourRoomNum++;break;
							case 5:rhFiveRoomNum++;break;
							default:rhOverFiveRoomNum++;
						}
					}
					if(rh.getGrossFloorArea()!=null){
						if(rh.getGrossFloorArea()>rhMaxRoomGrossFloorArea){
							rhMaxRoomGrossFloorArea = (int) rh.getGrossFloorArea().floatValue();
						}
						if(rh.getGrossFloorArea()<rhMinRoomGrossFloorArea){
							rhMinRoomGrossFloorArea = (int) rh.getGrossFloorArea().floatValue();
						}
					}
					if(rh.getRental()!=null){
						if(rh.getRental()>rhMaxRental){
							rhMaxRental = rh.getRental();
						}
						if(rh.getRental()<rhMinRental){
							rhMinRental = rh.getRental();
						}
						tempRhTotalPrice+=rh.getRental();
						tempRhNum++;
					}
				}
				if(tempRhNum!=0){
					rhAveragePrice = tempRhTotalPrice/tempRhNum;
				}
			}
			rc.setRhAveragePrice(rhAveragePrice);
			rc.setRhNum(rhNum);
			rc.setRhOneRoomNum(rhOneRoomNum);
			rc.setRhTwoRoomNum(rhTwoRoomNum);
			rc.setRhThreeRoomNum(rhThreeRoomNum);
			rc.setRhFourRoomNum(rhFourRoomNum);
			rc.setRhFiveRoomNum(rhFiveRoomNum);
			rc.setRhOverFiveRoomNum(rhOverFiveRoomNum);
			rc.setRhMaxRoomGrossFloorArea(rhMaxRoomGrossFloorArea);
			rc.setRhMinRoomGrossFloorArea(rhMinRoomGrossFloorArea);
			rc.setRhEntireRentNum(rhEntireRentNum);
			rc.setRhFlatShareNum(rhFlatShareNum);
			rc.setRhMinRental(rhMinRental);
			rc.setRhMaxRental(rhMaxRental);
		}
		rcm.updateByPrimaryKeySelective(rc);
		return rc;
	}

	@Override
	public ResidenceCommunity getByName(String name) {
		ResidenceCommunityExample rce = new ResidenceCommunityExample();
		rce.or().andNameEqualTo(name).andDelFlagEqualTo(false);
		List<ResidenceCommunity> list = rcm.selectByExample(rce);
		if(list!=null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
