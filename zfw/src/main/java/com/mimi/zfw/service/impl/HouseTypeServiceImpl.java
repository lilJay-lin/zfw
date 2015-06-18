package com.mimi.zfw.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.mybatis.dao.HouseTypeMapper;
import com.mimi.zfw.mybatis.pojo.HouseType;
import com.mimi.zfw.mybatis.pojo.HouseTypeExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IHouseTypeService;

@Service
public class HouseTypeServiceImpl extends
		BaseService<HouseType, HouseTypeExample, String> implements
		IHouseTypeService {

	@Resource
	private HouseTypeMapper htm;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<HouseType, HouseTypeExample, String> baseDao) {
		this.baseDao = baseDao;
		this.htm = (HouseTypeMapper) baseDao;
	}

	@Override
	public List<HouseType> findHouseTypeByParams(String keyWord, String region,
			String averagePrice, Integer roomNum, String grossFloorArea,
			String saleStatus, String orderBy, int targetPage, int pageSize) {
		HouseTypeExample hte = bindHouseTypeParams(keyWord, region,
				averagePrice, roomNum, grossFloorArea, saleStatus);
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
		hte.setOrderByClause(orderByClause);
		hte.setLimitStart(targetPage * pageSize);
		hte.setLimitSize(pageSize);
		return htm.selectByExample(hte);
	}

	@Override
	public int countHouseTypeByParams(String keyWord, String region,
			String averagePrice, Integer roomNum, String grossFloorArea,
			String saleStatus) {
		HouseTypeExample hte = bindHouseTypeParams(keyWord, region,
				averagePrice, roomNum, grossFloorArea, saleStatus);
		return htm.countByExample(hte);
	}

	private HouseTypeExample bindHouseTypeParams(String keyWord, String region,
			String averagePrice, Integer roomNum, String grossFloorArea,
			String saleStatus) {
		HouseTypeExample hte = new HouseTypeExample();
		com.mimi.zfw.mybatis.pojo.HouseTypeExample.Criteria cri = hte
				.createCriteria();
		com.mimi.zfw.mybatis.pojo.HouseTypeExample.Criteria cri2 = null;
		if (StringUtils.isNotBlank(keyWord)) {
			cri.andNameLike("%" + keyWord + "%").andDelFlagEqualTo(false);
			cri2 = hte.createCriteria();
			cri2.andRealEstateProjectNameLike("%" + keyWord + "%").andDelFlagEqualTo(false);
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
		if (StringUtils.isNotBlank(saleStatus)) {
			cri.andSaleStatusEqualTo(saleStatus);
			if (cri2 != null) {
				cri2.andSaleStatusEqualTo(saleStatus);
			}
		}
		if (cri2 != null) {
			hte.or(cri2);
		}
		return hte;
	}

}
