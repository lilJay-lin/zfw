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
import com.mimi.zfw.mybatis.dao.HTImageMapper;
import com.mimi.zfw.mybatis.dao.HTPanoMapper;
import com.mimi.zfw.mybatis.dao.HTRingMapper;
import com.mimi.zfw.mybatis.dao.HouseTypeMapper;
import com.mimi.zfw.mybatis.pojo.HTImage;
import com.mimi.zfw.mybatis.pojo.HTImageExample;
import com.mimi.zfw.mybatis.pojo.HTPano;
import com.mimi.zfw.mybatis.pojo.HTPanoExample;
import com.mimi.zfw.mybatis.pojo.HTRing;
import com.mimi.zfw.mybatis.pojo.HTRingExample;
import com.mimi.zfw.mybatis.pojo.HouseType;
import com.mimi.zfw.mybatis.pojo.HouseTypeExample;
import com.mimi.zfw.mybatis.pojo.RealEstateProject;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IHouseTypeService;
import com.mimi.zfw.service.IRealEstateProjectService;
import com.mimi.zfw.service.IUserService;

@Service
public class HouseTypeServiceImpl extends
		BaseService<HouseType, HouseTypeExample, String> implements
		IHouseTypeService {

	@Resource
	private HouseTypeMapper htm;

	@Resource
	private HTPanoMapper htpm;

	@Resource
	private HTRingMapper htrm;

	@Resource
	private HTImageMapper htim;

	@Resource
	private IUserService userService;

	@Resource
	private IRealEstateProjectService repService;

	@Resource
	@Override
	public void setBaseDao(IBaseDao<HouseType, HouseTypeExample, String> baseDao) {
		this.baseDao = baseDao;
		this.htm = (HouseTypeMapper) baseDao;
	}

	@Override
	public List<HouseType> findHouseTypeByParams(String keyWord, String region,
			String averagePrice, Integer roomNum, String grossFloorArea,
			String saleStatus, String orderBy, Integer targetPage,
			Integer pageSize) {
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
		if (targetPage != null && pageSize != null) {
			hte.setLimitStart(targetPage * pageSize);
			hte.setLimitSize(pageSize);
		}
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
			cri2.andRealEstateProjectNameLike("%" + keyWord + "%")
					.andDelFlagEqualTo(false);
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

	@Override
	public List<HouseType> getHouseTypeByREPId(String id) {
		HouseTypeExample hte = new HouseTypeExample();
		hte.or().andRealEstateProjectIdEqualTo(id).andDelFlagEqualTo(false);
		hte.setOrderByClause("update_date desc,priority desc");
		return htm.selectByExample(hte);
	}

	@Override
	public List<HouseType> findByParams(String name, String repId,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(repId)) {
			return null;
		}
		HouseTypeExample hte = bindHouseTypeParams(repId, name);
		if (targetPage != null && pageSize != null) {
			hte.setLimitStart(targetPage * pageSize);
			hte.setLimitSize(pageSize);
		}
		hte.setOrderByClause("update_date desc,priority desc");
		return htm.selectByExample(hte);
	}

	@Override
	public int countByParams(String name, String repId) {
		if (StringUtils.isBlank(repId)) {
			return 0;
		}
		HouseTypeExample hte = bindHouseTypeParams(repId, name);
		return htm.countByExample(hte);
	}

	private HouseTypeExample bindHouseTypeParams(String repId, String name) {
		HouseTypeExample hte = new HouseTypeExample();
		HouseTypeExample.Criteria cri = hte.createCriteria();
		cri.andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(repId)) {
			cri.andRealEstateProjectIdEqualTo(repId);
		}
		if (StringUtils.isNotBlank(name)) {
			cri.andNameLike("%" + name + "%");
		}
		return hte;
	}

	@Override
	public Map<String, String> addHT(HouseType ht) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (ht == null) {
			resMap.put("msg", "户型内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(ht);
		if(!resMap.isEmpty()){
			return resMap;
		}
		ht.setId(UUID.randomUUID().toString());
		ht.setCreater(curUserId);
		ht.setLastEditor(curUserId);
		htm.insertSelective(ht);
		repService.refreshRealEstateProject(ht.getRealEstateProjectId());
		return resMap;
	}

	@Override
	public Map<String, String> updateHT(HouseType ht) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (ht == null) {
			resMap.put("msg", "户型内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(ht);
		if(!resMap.isEmpty()){
			return resMap;
		}
		ht.setLastEditor(curUserId);
		htm.updateByPrimaryKeySelective(ht);
		repService.refreshRealEstateProject(ht.getRealEstateProjectId());
		return resMap;
	}


	@Override
	public Map<String, String> batchDel(String repId, String htIds) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isBlank(htIds)) {
			resMap.put("msg", "删除内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		String[] ids = htIds.split(Constants.MI_IDS_SPLIT_STRING);
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotBlank(ids[i])) {
				idList.add(ids[i]);
			}
		}
		if (!idList.isEmpty()) {
			HouseTypeExample hte = new HouseTypeExample();
			hte.or().andIdIn(idList).andDelFlagEqualTo(false);
			HouseType ht = new HouseType();
			ht.setDelFlag(true);
			ht.setLastEditor(curUserId);
			htm.updateByExampleSelective(ht, hte);
			
			HTPanoExample pe = new HTPanoExample();
			pe.or().andHouseTypeIdIn(idList).andDelFlagEqualTo(false);
			HTPano pano = new HTPano();
			pano.setDelFlag(true);
			pano.setLastEditor(curUserId);
			htpm.updateByExampleSelective(pano, pe);
			
			HTRingExample re = new HTRingExample();
			re.or().andHouseTypeIdIn(idList).andDelFlagEqualTo(false);
			HTRing ring = new HTRing();
			ring.setDelFlag(true);
			ring.setLastEditor(curUserId);
			htrm.updateByExampleSelective(ring, re);
			
			HTImageExample ie = new HTImageExample();
			ie.or().andHouseTypeIdIn(idList).andDelFlagEqualTo(false);
			HTImage image = new HTImage();
			image.setDelFlag(true);
			image.setLastEditor(curUserId);
			htim.updateByExampleSelective(image, ie);
			repService.refreshRealEstateProject(repId);
		}
		return resMap;
	}
	
	private Map<String, String> checkInfo(HouseType ht) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (ht == null) {
			resMap.put("msg", "户型内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(ht.getRealEstateProjectId())) {
			resMap.put("msg", "户型所属楼盘不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(ht.getPreImageUrl())) {
			resMap.put("msg", "户型缩略图不能为空");
			return resMap;
		}
		if (ht.getAveragePrice() == null) {
			resMap.put("msg", "户型均价不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(ht.getName())) {
			resMap.put("msg", "户型名称不能为空");
			return resMap;
		}
		return resMap;
	}

	@Override
	public void refreshByREP(RealEstateProject rep) {
		HouseTypeExample hte = new HouseTypeExample();
		hte.or().andRealEstateProjectIdEqualTo(rep.getId()).andDelFlagEqualTo(false);
		HouseType ht = new HouseType();
		ht.setRegion(rep.getRegion());
		ht.setRealEstateProjectName(rep.getName());
		htm.updateByExampleSelective(ht, hte);
	}

}
