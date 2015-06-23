package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.SecondHandHouseMapper;
import com.mimi.zfw.mybatis.pojo.SecondHandHouse;
import com.mimi.zfw.mybatis.pojo.SecondHandHouseExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.ISecondHandHouseService;

@Service
public class SecondHandHouseServiceImpl extends
		BaseService<SecondHandHouse, SecondHandHouseExample, String> implements
		ISecondHandHouseService {

	@Resource
	private SecondHandHouseMapper shhm;

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<SecondHandHouse, SecondHandHouseExample, String> baseDao) {
		this.baseDao = baseDao;
		this.shhm = (SecondHandHouseMapper) baseDao;
	}

	@Override
	public List<SecondHandHouse> findSecondHandHousesByParams(
			String residenceCommunityId, String keyWord, String region,
			String totalPrice, Integer roomNum, String grossFloorArea,
			String orderBy, Integer targetPage, Integer pageSize) {
		SecondHandHouseExample shhe = bindSecondHandHouseParams(
				residenceCommunityId, keyWord, region, totalPrice, roomNum,
				grossFloorArea);
		String orderByClause = "";
		if (StringUtils.isNotBlank(orderBy)) {
			if ("priceFromLow".equals(orderBy)) {
				orderByClause = "total_price asc,";
			} else if ("priceFromHigh".equals(orderBy)) {
				orderByClause = "total_price desc,";
			} else if ("onUpdateFromNear".equals(orderBy)) {
				orderByClause = "update_date desc,";
			} else if ("onUpdateFromFar".equals(orderBy)) {
				orderByClause = "update_date asc,";
			}
		}
		orderByClause += "priority desc";
		shhe.setOrderByClause(orderByClause);
		shhe.setLimitStart(targetPage * pageSize);
		shhe.setLimitSize(pageSize);
		return shhm.selectByExample(shhe);
	}

	@Override
	public int countSecondHandHouseByParams(String residenceCommunityId,
			String keyWord, String region, String totalPrice, Integer roomNum,
			String grossFloorArea, String orderBy) {
		SecondHandHouseExample shhe = bindSecondHandHouseParams(
				residenceCommunityId, keyWord, region, totalPrice, roomNum,
				grossFloorArea);
		return shhm.countByExample(shhe);
	}

	private SecondHandHouseExample bindSecondHandHouseParams(
			String residenceCommunityId, String keyWord, String region,
			String totalPrice, Integer roomNum, String grossFloorArea) {
		SecondHandHouseExample shhe = new SecondHandHouseExample();
		SecondHandHouseExample.Criteria cri = shhe.createCriteria();
		cri.andDelFlagEqualTo(false);
		SecondHandHouseExample.Criteria cri2 = null;
		if (StringUtils.isNotBlank(keyWord)) {
			cri.andResidenceCommunityNameLike("%" + keyWord + "%");
			cri2 = shhe.createCriteria();
			cri2.andAddressLike("%" + keyWord + "%").andDelFlagEqualTo(false);
		}
		if(StringUtils.isNotBlank(residenceCommunityId)){
			cri.andResidenceCommunityIdEqualTo(residenceCommunityId);
			if (cri2 != null) {
				cri2.andResidenceCommunityIdEqualTo(residenceCommunityId);
			}
		}
		if (StringUtils.isNotBlank(region)) {
			cri.andRegionEqualTo(region);
			if (cri2 != null) {
				cri2.andRegionEqualTo(region);
			}
		}
		if (StringUtils.isNotBlank(totalPrice)) {
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
		if (roomNum != null) {
			cri.andRoomNumEqualTo(roomNum);
			if (cri2 != null) {
				cri2.andRoomNumEqualTo(roomNum);
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
		if (cri2 != null) {
			shhe.or(cri2);
		}
		return shhe;
	}
}
