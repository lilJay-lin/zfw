package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.RentalHousingMapper;
import com.mimi.zfw.mybatis.pojo.RentalHousing;
import com.mimi.zfw.mybatis.pojo.RentalHousingExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IRentalHousingService;

@Service
public class RentalHousingServiceImpl extends
		BaseService<RentalHousing, RentalHousingExample, String> implements
		IRentalHousingService {

	@Resource
	private RentalHousingMapper rhm;

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<RentalHousing, RentalHousingExample, String> baseDao) {
		this.baseDao = baseDao;
		this.rhm = (RentalHousingMapper) baseDao;
	}

	@Override
	public List<RentalHousing> findRentalHousingsByParams(
			String residenceCommunityId, String keyWord, String region,
			String rental, Integer roomNum, String grossFloorArea,
			String orderBy, Integer targetPage, Integer pageSize) {
		RentalHousingExample rhe = bindRentalHousingParams(
				residenceCommunityId, keyWord, region, rental, roomNum,
				grossFloorArea);
		String orderByClause = "";
		if (StringUtils.isNotBlank(orderBy)) {
			if ("priceFromLow".equals(orderBy)) {
				orderByClause = "rental asc,";
			} else if ("priceFromHigh".equals(orderBy)) {
				orderByClause = "rental desc,";
			} else if ("onUpdateFromNear".equals(orderBy)) {
				orderByClause = "update_date desc,";
			} else if ("onUpdateFromFar".equals(orderBy)) {
				orderByClause = "update_date asc,";
			}
		}
		orderByClause += "priority desc";
		rhe.setOrderByClause(orderByClause);
		rhe.setLimitStart(targetPage * pageSize);
		rhe.setLimitSize(pageSize);
		return rhm.selectByExample(rhe);
	}

	@Override
	public int countRentalHousingByParams(String residenceCommunityId,
			String keyWord, String region, String rental, Integer roomNum,
			String grossFloorArea, String orderBy) {
		RentalHousingExample rhe = bindRentalHousingParams(
				residenceCommunityId, keyWord, region, rental, roomNum,
				grossFloorArea);
		return rhm.countByExample(rhe);
	}

	private RentalHousingExample bindRentalHousingParams(
			String residenceCommunityId, String keyWord, String region,
			String rental, Integer roomNum, String grossFloorArea) {
		RentalHousingExample rhe = new RentalHousingExample();
		RentalHousingExample.Criteria cri = rhe.createCriteria();
		cri.andDelFlagEqualTo(false);
		RentalHousingExample.Criteria cri2 = null;
		if (StringUtils.isNotBlank(keyWord)) {
			cri.andResidenceCommunityNameLike("%" + keyWord + "%");
			cri2 = rhe.createCriteria();
			cri2.andAddressLike("%" + keyWord + "%").andDelFlagEqualTo(false);
		}
		if (StringUtils.isNotBlank(residenceCommunityId)) {
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
		if (StringUtils.isNotBlank(rental)) {
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
			rhe.or(cri2);
		}
		return rhe;
	}

}
