package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.RHImageMapper;
import com.mimi.zfw.mybatis.dao.RentalHousingMapper;
import com.mimi.zfw.mybatis.dao.ResidenceCommunityMapper;
import com.mimi.zfw.mybatis.pojo.RHImage;
import com.mimi.zfw.mybatis.pojo.RHImageExample;
import com.mimi.zfw.mybatis.pojo.RentalHousing;
import com.mimi.zfw.mybatis.pojo.RentalHousingExample;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunityExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IRentalHousingService;
import com.mimi.zfw.service.IResidenceCommunityService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.NumberUtil;

@Service
public class RentalHousingServiceImpl extends
		BaseService<RentalHousing, RentalHousingExample, String> implements
		IRentalHousingService {

	@Resource
	private RentalHousingMapper rhm;

	@Resource
	private RHImageMapper rhim;

	@Resource
	private ResidenceCommunityMapper rcm;

	@Resource
	private IUserService userService;

	@Resource
	private IResidenceCommunityService rcService;

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

	@Override
	public List<RentalHousing> getByUserId(String userId, Integer targetPage,
			Integer pageSize) {
		if (StringUtils.isBlank(userId)) {
			return null;
		}
		RentalHousingExample rhe = new RentalHousingExample();
		rhe.or().andCreaterEqualTo(userId).andDelFlagEqualTo(false);
		if (targetPage != null && pageSize != null) {
			rhe.setLimitStart(targetPage * pageSize);
			rhe.setLimitSize(pageSize);
		}
		return rhm.selectByExample(rhe);
	}

	@Override
	public int countByUserId(String userId) {
		if (StringUtils.isBlank(userId)) {
			return 0;
		}
		RentalHousingExample rhe = new RentalHousingExample();
		rhe.or().andCreaterEqualTo(userId).andDelFlagEqualTo(false);
		return rhm.countByExample(rhe);
	}

	@Override
	public String saveCascading(RentalHousing rh, String imgUrls) {

		String userId = userService.getCurUserId();
		if (StringUtils.isBlank(userId)) {
			return "请先登录";
		}
		if (StringUtils.isBlank(rh.getResidenceCommunityName())) {
			return "小区名称不能为空";
		}
		if (rh.getRental() == null) {
			return "租金不能为空";
		}
		if (rh.getRoomNum() == null) {
			return "居室数不能为空";
		}
		if (rh.getGrossFloorArea() == null) {
			return "面积不能为空";
		}
		if (rh.getGrossFloorArea() == 0) {
			return "面积不能为0";
		}
		rh.setCreater(userId);
		rh.setLastEditor(userId);

		// 创建或更新小区
		ResidenceCommunity rc = null;
		if (StringUtils.isNotBlank(rh.getResidenceCommunityId())) {
			ResidenceCommunityExample rce = new ResidenceCommunityExample();
			rce.or().andDelFlagEqualTo(false)
					.andIdEqualTo(rh.getResidenceCommunityId());
			List<ResidenceCommunity> rcList = rcm.selectByExample(rce);
			if (rcList != null && !rcList.isEmpty()) {
				rc = rcList.get(0);
			}
		}
		if (rc == null
				&& StringUtils.isNotBlank(rh.getResidenceCommunityName())) {
			ResidenceCommunityExample rce = new ResidenceCommunityExample();
			rce.or().andDelFlagEqualTo(false)
					.andNameEqualTo(rh.getResidenceCommunityName());
			List<ResidenceCommunity> rcList = rcm.selectByExample(rce);
			if (rcList != null && !rcList.isEmpty()) {
				rc = rcList.get(0);
			}
		}
		if (rc == null) {
			rc = new ResidenceCommunity();
			rc.setName(rh.getResidenceCommunityName());
			rc.setAddress(rh.getAddress());
			rc.setCreater(rh.getLastEditor());
			rc.setLastEditor(rh.getLastEditor());
			rc.setActive(false);
		}

		int oldAveragePrice = NumberUtil.getIntOrZero(rc.getRhAveragePrice());
		int oldNum = NumberUtil.getIntOrZero(rc.getRhNum());
		rc.setRhAveragePrice((int) ((oldNum * oldAveragePrice + rh.getRental()) / (oldNum + 1)));
		rc.setRhNum(oldNum + 1);

		switch (rh.getRoomNum()) {
		case 0:
			break;
		case 1:
			rc.setRhOneRoomNum(NumberUtil.getIntOrZero(rc.getRhOneRoomNum()) + 1);
			break;
		case 2:
			rc.setRhTwoRoomNum(NumberUtil.getIntOrZero(rc.getRhTwoRoomNum()) + 1);
			break;
		case 3:
			rc.setRhThreeRoomNum(NumberUtil.getIntOrZero(rc.getRhThreeRoomNum()) + 1);
			break;
		case 4:
			rc.setRhFourRoomNum(NumberUtil.getIntOrZero(rc.getRhFourRoomNum()) + 1);
			break;
		case 5:
			rc.setRhFiveRoomNum(NumberUtil.getIntOrZero(rc.getRhFiveRoomNum()) + 1);
			break;
		default:
			rc.setRhOverFiveRoomNum(NumberUtil.getIntOrZero(rc
					.getRhOverFiveRoomNum()) + 1);
		}

		if (rc.getRhMaxRoomGrossFloorArea() == null
				|| rc.getRhMaxRoomGrossFloorArea() < rh.getGrossFloorArea()) {
			rc.setRhMaxRoomGrossFloorArea((int) rh.getGrossFloorArea()
					.floatValue());
		}
		if (rc.getRhMinRoomGrossFloorArea() == null
				|| rc.getRhMinRoomGrossFloorArea() > rh.getGrossFloorArea()) {
			rc.setRhMinRoomGrossFloorArea((int) rh.getGrossFloorArea()
					.floatValue());
		}

		if (rc.getRhMaxRental() == null || rc.getRhMaxRental() < rh.getRental()) {
			rc.setRhMaxRental(rh.getRental());
		}
		if (rc.getRhMinRental() == null || rc.getRhMinRental() > rh.getRental()) {
			rc.setRhMinRental(rh.getRental());
		}
		if (StringUtils.isBlank(rc.getId())) {
			rc.setId(UUID.randomUUID().toString());
			rcm.insertSelective(rc);
		} else {
			rcm.updateByPrimaryKeySelective(rc);
		}

		// 创建租房
		rh.setId(UUID.randomUUID().toString());
		rh.setResidenceCommunityId(rc.getId());
		rh.setRegion(rc.getRegion());
		rhm.insertSelective(rh);

		// 创建租房图片
		if (StringUtils.isNotBlank(imgUrls)) {
			String[] urls = imgUrls.split(Constants.IMAGE_URLS_SPLIT_STRING);
			for (int i = 0; i < urls.length; i++) {
				if (StringUtils.isNotBlank(urls[i])) {
					RHImage ri = new RHImage();
					ri.setId(UUID.randomUUID().toString());
					ri.setContentUrl(urls[i]);
					ri.setCreater(userId);
					ri.setLastEditor(rh.getLastEditor());
					ri.setRentalHousingId(rh.getId());
					ri.setName(rh.getName());
					rhim.insertSelective(ri);
				}
			}
		}
		return null;
	}

	@Override
	public String deleteUserRHByFlag(String id) {
		String userId = userService.getCurUserId();
		if (StringUtils.isBlank(userId)) {
			return "请先登录";
		}
		RentalHousingExample rhe = new RentalHousingExample();
		rhe.or().andIdEqualTo(id).andCreaterEqualTo(userId)
				.andDelFlagEqualTo(false);
		if (rhm.countByExample(rhe) == 0) {
			return "要删除的数据不存在";
		}

		// 删除二手房
		RentalHousing rh = new RentalHousing();
		rh.setId(id);
		rh.setDelFlag(true);
		rh.setLastEditor(userId);
		rhm.updateByPrimaryKeySelective(rh);

		// 删除图片
		RHImage ri = new RHImage();
		ri.setDelFlag(true);
		ri.setLastEditor(rh.getLastEditor());
		RHImageExample ie = new RHImageExample();
		ie.or().andRentalHousingIdEqualTo(id).andDelFlagEqualTo(false);
		rhim.updateByExampleSelective(ri, ie);

		// 刷新小区
		rcService.refreshResidenceCommunity(rh.getResidenceCommunityId(),
				false, true);
		return null;
	}

	@Override
	public String refreshUserRH(String id) {
		String userId = userService.getCurUserId();
		if (StringUtils.isBlank(userId)) {
			return "请先登录";
		}
		RentalHousingExample rhe = new RentalHousingExample();
		rhe.or().andIdEqualTo(id).andCreaterEqualTo(userId)
				.andDelFlagEqualTo(false);
		if (rhm.countByExample(rhe) == 0) {
			return "要删除的数据不存在";
		}
		RentalHousing rh = new RentalHousing();
		rh.setId(id);
		rh.setUpdateDate(new Date(System.currentTimeMillis()));
		rh.setLastEditor(userId);
		rhm.updateByPrimaryKeySelective(rh);
		return null;
	}

	@Override
	public String updateCascading(RentalHousing rh, String imgUrls) {
		String userId = userService.getCurUserId();
		if (StringUtils.isBlank(userId)) {
			return "请先登录";
		}
		if (StringUtils.isBlank(rh.getResidenceCommunityName())) {
			return "小区名称不能为空";
		}
		if (rh.getRental() == null) {
			return "租金不能为空";
		}
		if (rh.getRoomNum() == null) {
			return "居室数不能为空";
		}
		if (rh.getGrossFloorArea() == null) {
			return "面积不能为空";
		}
		if (rh.getGrossFloorArea() == 0) {
			return "面积不能为0";
		}
		rh.setLastEditor(userId);

		boolean RCExisted = true;

		// 创建小区
		ResidenceCommunity rc = null;
		if (StringUtils.isNotBlank(rh.getResidenceCommunityId())) {
			ResidenceCommunityExample rce = new ResidenceCommunityExample();
			rce.or().andDelFlagEqualTo(false)
					.andIdEqualTo(rh.getResidenceCommunityId());
			List<ResidenceCommunity> rcList = rcm.selectByExample(rce);
			if (rcList != null && !rcList.isEmpty()) {
				rc = rcList.get(0);
			}
		}
		if (rc == null
				&& StringUtils.isNotBlank(rh.getResidenceCommunityName())) {
			ResidenceCommunityExample rce = new ResidenceCommunityExample();
			rce.or().andDelFlagEqualTo(false)
					.andNameEqualTo(rh.getResidenceCommunityName());
			List<ResidenceCommunity> rcList = rcm.selectByExample(rce);
			if (rcList != null && !rcList.isEmpty()) {
				rc = rcList.get(0);
			}
		}
		if (rc == null) {
			RCExisted = false;
			rc = new ResidenceCommunity();
			rc.setName(rh.getResidenceCommunityName());
			rc.setAddress(rh.getAddress());
			rc.setCreater(rh.getLastEditor());
			rc.setLastEditor(rh.getLastEditor());
			rc.setActive(false);
			int oldAveragePrice = NumberUtil.getIntOrZero(rc
					.getRhAveragePrice());
			int oldNum = NumberUtil.getIntOrZero(rc.getRhNum());
			rc.setRhAveragePrice((int) ((oldNum * oldAveragePrice + rh
					.getRental()) / (oldNum + 1)));
			rc.setRhNum(oldNum + 1);
			switch (rh.getRoomNum()) {
			case 0:
				break;
			case 1:
				rc.setRhOneRoomNum(NumberUtil.getIntOrZero(rc.getRhOneRoomNum()) + 1);
				break;
			case 2:
				rc.setRhTwoRoomNum(NumberUtil.getIntOrZero(rc.getRhTwoRoomNum()) + 1);
				break;
			case 3:
				rc.setRhThreeRoomNum(NumberUtil.getIntOrZero(rc
						.getRhThreeRoomNum()) + 1);
				break;
			case 4:
				rc.setRhFourRoomNum(NumberUtil.getIntOrZero(rc
						.getRhFourRoomNum()) + 1);
				break;
			case 5:
				rc.setRhFiveRoomNum(NumberUtil.getIntOrZero(rc
						.getRhFiveRoomNum()) + 1);
				break;
			default:
				rc.setRhOverFiveRoomNum(NumberUtil.getIntOrZero(rc
						.getRhOverFiveRoomNum()) + 1);
			}

			if (rc.getRhMaxRoomGrossFloorArea() == null
					|| rc.getRhMaxRoomGrossFloorArea() < rh.getGrossFloorArea()) {
				rc.setRhMaxRoomGrossFloorArea((int) rh.getGrossFloorArea()
						.floatValue());
			}
			if (rc.getRhMinRoomGrossFloorArea() == null
					|| rc.getRhMinRoomGrossFloorArea() > rh.getGrossFloorArea()) {
				rc.setRhMinRoomGrossFloorArea((int) rh.getGrossFloorArea()
						.floatValue());
			}

			if (rc.getRhMaxRental() == null
					|| rc.getRhMaxRental() < rh.getRental()) {
				rc.setRhMaxRental(rh.getRental());
			}
			if (rc.getRhMinRental() == null
					|| rc.getRhMinRental() > rh.getRental()) {
				rc.setRhMinRental(rh.getRental());
			}
			rc.setId(UUID.randomUUID().toString());
			rcm.insertSelective(rc);
			rh.setResidenceCommunityId(rc.getId());
		}

		// 更新二手房
		rhm.updateByPrimaryKeySelective(rh);

		// 更新二手房图片
		RHImageExample ie = new RHImageExample();
		ie.or().andRentalHousingIdEqualTo(rh.getId()).andDelFlagEqualTo(false);
		List<RHImage> oldImgs = rhim.selectByExample(ie);
		for (int j = 0; j < oldImgs.size(); j++) {
			oldImgs.get(j).setDelFlag(true);
		}
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
						RHImage ri = new RHImage();
						ri.setId(UUID.randomUUID().toString());
						ri.setContentUrl(urls[i]);
						ri.setCreater(rh.getLastEditor());
						ri.setLastEditor(rh.getLastEditor());
						ri.setRentalHousingId(rh.getId());
						ri.setName(rh.getName());
						rhim.insertSelective(ri);
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
			RHImageExample ie2 = new RHImageExample();
			ie2.or().andIdIn(delIds).andDelFlagEqualTo(false);
			RHImage si = new RHImage();
			si.setDelFlag(true);
			si.setLastEditor(rh.getLastEditor());
			rhim.updateByExampleSelective(si, ie2);
		}

		// 刷新小区
		if (RCExisted) {
			rcService.refreshResidenceCommunity(rc.getId(), false, true);
		}
		return null;
	}

}
