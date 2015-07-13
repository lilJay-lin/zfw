package com.mimi.zfw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.ResidenceCommunityMapper;
import com.mimi.zfw.mybatis.dao.SHHImageMapper;
import com.mimi.zfw.mybatis.dao.SHHPanoMapper;
import com.mimi.zfw.mybatis.dao.SecondHandHouseMapper;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunityExample;
import com.mimi.zfw.mybatis.pojo.SHHImage;
import com.mimi.zfw.mybatis.pojo.SHHImageExample;
import com.mimi.zfw.mybatis.pojo.SHHPano;
import com.mimi.zfw.mybatis.pojo.SHHPanoExample;
import com.mimi.zfw.mybatis.pojo.SecondHandHouse;
import com.mimi.zfw.mybatis.pojo.SecondHandHouseExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IResidenceCommunityService;
import com.mimi.zfw.service.ISecondHandHouseService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.NumberUtil;

@Service
public class SecondHandHouseServiceImpl extends
		BaseService<SecondHandHouse, SecondHandHouseExample, String> implements
		ISecondHandHouseService {

	@Resource
	private SecondHandHouseMapper shhm;

	@Resource
	private SHHImageMapper shhim;

	@Resource
	private SHHPanoMapper shhpm;

	@Resource
	private ResidenceCommunityMapper rcm;
	
	@Resource
	private IUserService userService;

	@Resource
	private IResidenceCommunityService rcService;

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
			String grossFloorArea) {
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

	@Override
	public List<SecondHandHouse> getByUserId(String userId, Integer targetPage,
			Integer pageSize) {
		if(StringUtils.isBlank(userId)){
			return null;
		}
		SecondHandHouseExample shhe = new SecondHandHouseExample();
		shhe.or().andCreaterEqualTo(userId).andDelFlagEqualTo(false);
		if(targetPage!=null && pageSize!=null){
			shhe.setLimitStart(targetPage*pageSize);
			shhe.setLimitSize(pageSize);
		}
		return shhm.selectByExample(shhe);
	}

	@Override
	public int countByUserId(String userId) {
		if(StringUtils.isBlank(userId)){
			return 0;
		}
		SecondHandHouseExample shhe = new SecondHandHouseExample();
		shhe.or().andCreaterEqualTo(userId).andDelFlagEqualTo(false);
		return shhm.countByExample(shhe);
	}

	@Override
	public String saveCascading(SecondHandHouse shh, String imgUrls) {
		String userId = userService.getCurUserId();
		if(StringUtils.isBlank(userId)){
			return "请先登录";
		}
		if(StringUtils.isBlank(shh.getResidenceCommunityName())){
			return "小区名称不能为空";
		}
		if(shh.getTotalPrice()==null){
			return "价格不能为空";
		}
		if(shh.getRoomNum()==null){
			return "居室数不能为空";
		}
		if(shh.getGrossFloorArea()==null){
			return "面积不能为空";
		}
		if(shh.getGrossFloorArea()==0){
			return "面积不能为0";
		}
		shh.setCreater(userId);
		shh.setLastEditor(userId);
		
		//创建或更新小区
		ResidenceCommunity rc = null;
		if(StringUtils.isNotBlank(shh.getResidenceCommunityId())){
			ResidenceCommunityExample rce = new ResidenceCommunityExample();
			rce.or().andDelFlagEqualTo(false).andIdEqualTo(shh.getResidenceCommunityId());
			List<ResidenceCommunity> rcList = rcm.selectByExample(rce);
			if(rcList!=null && !rcList.isEmpty()){
				rc = rcList.get(0);
			}
		}
		if(rc==null && StringUtils.isNotBlank(shh.getResidenceCommunityName())){
			ResidenceCommunityExample rce = new ResidenceCommunityExample();
			rce.or().andDelFlagEqualTo(false).andNameEqualTo(shh.getResidenceCommunityName());
			List<ResidenceCommunity> rcList = rcm.selectByExample(rce);
			if(rcList!=null && !rcList.isEmpty()){
				rc = rcList.get(0);
			}
		}
		if(rc==null){
			rc = new ResidenceCommunity();
			rc.setName(shh.getResidenceCommunityName());
			rc.setAddress(shh.getAddress());
			rc.setCreater(shh.getLastEditor());
			rc.setLastEditor(shh.getLastEditor());
			rc.setActive(false);
		}
		
		int oldAveragePrice = NumberUtil.getIntOrZero(rc.getShhAveragePrice());
		int oldNum = NumberUtil.getIntOrZero(rc.getShhNum());
		rc.setShhAveragePrice((int) ((oldNum*oldAveragePrice+shh.getTotalPrice()*10000/shh.getGrossFloorArea())/(oldNum+1)));
		rc.setShhNum(oldNum+1);
		
		switch(shh.getRoomNum()){
			case 0:break;
			case 1:rc.setShhOneRoomNum(NumberUtil.getIntOrZero(rc.getShhOneRoomNum())+1);break;
			case 2:rc.setShhTwoRoomNum(NumberUtil.getIntOrZero(rc.getShhTwoRoomNum())+1);break;
			case 3:rc.setShhThreeRoomNum(NumberUtil.getIntOrZero(rc.getShhThreeRoomNum())+1);break;
			case 4:rc.setShhFourRoomNum(NumberUtil.getIntOrZero(rc.getShhFourRoomNum())+1);break;
			case 5:rc.setShhFiveRoomNum(NumberUtil.getIntOrZero(rc.getShhFiveRoomNum())+1);break;
			default:rc.setShhOverFiveRoomNum(NumberUtil.getIntOrZero(rc.getShhOverFiveRoomNum())+1);
		}
		
		if(rc.getShhMaxRoomGrossFloorArea()==null || rc.getShhMaxRoomGrossFloorArea()<shh.getGrossFloorArea()){
			rc.setShhMaxRoomGrossFloorArea((int)shh.getGrossFloorArea().floatValue());
		}
		if(rc.getShhMinRoomGrossFloorArea()==null || rc.getShhMinRoomGrossFloorArea()>shh.getGrossFloorArea()){
			rc.setShhMinRoomGrossFloorArea((int)shh.getGrossFloorArea().floatValue());
		}
		
		if(rc.getShhMaxTotalPrice()==null || rc.getShhMaxTotalPrice()<shh.getTotalPrice()){
			rc.setShhMaxTotalPrice(shh.getTotalPrice());
		}
		if(rc.getShhMinTotalPrice()==null || rc.getShhMinTotalPrice()>shh.getTotalPrice()){
			rc.setShhMinTotalPrice(shh.getTotalPrice());
		}
		if(StringUtils.isBlank(rc.getId())){
			rc.setId(UUID.randomUUID().toString());
			rcm.insertSelective(rc);
		}else{
			rcm.updateByPrimaryKeySelective(rc);
		}
		
		//创建二手房
		shh.setId(UUID.randomUUID().toString());
		shh.setResidenceCommunityId(rc.getId());
		shh.setRegion(rc.getRegion());
		shhm.insertSelective(shh);

		long timeMillis = System.currentTimeMillis();
		//创建二手房图片
		if(StringUtils.isNotBlank(imgUrls)){
			String[] urls = imgUrls.split(Constants.IMAGE_URLS_SPLIT_STRING);
			for(int i=0;i<urls.length;i++){
				if(StringUtils.isNotBlank(urls[i])){
					SHHImage si = new SHHImage();
					si.setId(UUID.randomUUID().toString());
					si.setContentUrl(urls[i]);
					si.setCreater(userId);
					si.setLastEditor(shh.getLastEditor());
					si.setSecondHandHouseId(shh.getId());
					si.setName(shh.getName());
					timeMillis = timeMillis + 1000;
					Date nowDate = new Date(timeMillis);
					si.setCreateDate(nowDate);
					si.setUpdateDate(nowDate);
					shhim.insertSelective(si);
				}
			}
		}
		return null;
	}

	@Override
	public String updateCascading(SecondHandHouse shh, String imgUrls) {
		String userId = userService.getCurUserId();
		if(StringUtils.isBlank(userId)){
			return "请先登录";
		}
		if(StringUtils.isBlank(shh.getResidenceCommunityName())){
			return "小区名称不能为空";
		}
		if(shh.getTotalPrice()==null){
			return "价格不能为空";
		}
		if(shh.getRoomNum()==null){
			return "居室数不能为空";
		}
		if(shh.getGrossFloorArea()==null){
			return "面积不能为空";
		}
		if(shh.getGrossFloorArea()==0){
			return "面积不能为0";
		}
		shh.setLastEditor(userId);
		
		boolean RCExisted = true;

		//创建小区
		ResidenceCommunity rc = null;
		if(StringUtils.isNotBlank(shh.getResidenceCommunityId())){
			ResidenceCommunityExample rce = new ResidenceCommunityExample();
			rce.or().andDelFlagEqualTo(false).andIdEqualTo(shh.getResidenceCommunityId());
			List<ResidenceCommunity> rcList = rcm.selectByExample(rce);
			if(rcList!=null && !rcList.isEmpty()){
				rc = rcList.get(0);
			}
		}
		if(rc==null && StringUtils.isNotBlank(shh.getResidenceCommunityName())){
			ResidenceCommunityExample rce = new ResidenceCommunityExample();
			rce.or().andDelFlagEqualTo(false).andNameEqualTo(shh.getResidenceCommunityName());
			List<ResidenceCommunity> rcList = rcm.selectByExample(rce);
			if(rcList!=null && !rcList.isEmpty()){
				rc = rcList.get(0);
			}
		}
		if(rc==null){
			RCExisted = false;
			rc = new ResidenceCommunity();
			rc.setName(shh.getResidenceCommunityName());
			rc.setAddress(shh.getAddress());
			rc.setCreater(shh.getLastEditor());
			rc.setLastEditor(shh.getLastEditor());
			rc.setActive(false);
			int oldAveragePrice = NumberUtil.getIntOrZero(rc.getShhAveragePrice());
			int oldNum = NumberUtil.getIntOrZero(rc.getShhNum());
			rc.setShhAveragePrice((int) ((oldNum*oldAveragePrice+shh.getTotalPrice()*10000/shh.getGrossFloorArea())/(oldNum+1)));
			rc.setShhNum(oldNum+1);
			switch(shh.getRoomNum()){
				case 0:break;
				case 1:rc.setShhOneRoomNum(NumberUtil.getIntOrZero(rc.getShhOneRoomNum())+1);break;
				case 2:rc.setShhTwoRoomNum(NumberUtil.getIntOrZero(rc.getShhTwoRoomNum())+1);break;
				case 3:rc.setShhThreeRoomNum(NumberUtil.getIntOrZero(rc.getShhThreeRoomNum())+1);break;
				case 4:rc.setShhFourRoomNum(NumberUtil.getIntOrZero(rc.getShhFourRoomNum())+1);break;
				case 5:rc.setShhFiveRoomNum(NumberUtil.getIntOrZero(rc.getShhFiveRoomNum())+1);break;
				default:rc.setShhOverFiveRoomNum(NumberUtil.getIntOrZero(rc.getShhOverFiveRoomNum())+1);
			}
			
			if(rc.getShhMaxRoomGrossFloorArea()==null || rc.getShhMaxRoomGrossFloorArea()<shh.getGrossFloorArea()){
				rc.setShhMaxRoomGrossFloorArea((int)shh.getGrossFloorArea().floatValue());
			}
			if(rc.getShhMinRoomGrossFloorArea()==null || rc.getShhMinRoomGrossFloorArea()>shh.getGrossFloorArea()){
				rc.setShhMinRoomGrossFloorArea((int)shh.getGrossFloorArea().floatValue());
			}
			
			if(rc.getShhMaxTotalPrice()==null || rc.getShhMaxTotalPrice()<shh.getTotalPrice()){
				rc.setShhMaxTotalPrice(shh.getTotalPrice());
			}
			if(rc.getShhMinTotalPrice()==null || rc.getShhMinTotalPrice()>shh.getTotalPrice()){
				rc.setShhMinTotalPrice(shh.getTotalPrice());
			}
			rc.setId(UUID.randomUUID().toString());
			rcm.insertSelective(rc);
			shh.setResidenceCommunityId(rc.getId());
		}

		//更新二手房
		shhm.updateByPrimaryKeySelective(shh);
		
		//更新二手房图片
		SHHImageExample ie = new SHHImageExample();
		ie.or().andSecondHandHouseIdEqualTo(shh.getId()).andDelFlagEqualTo(false);
		List<SHHImage> oldImgs = shhim.selectByExample(ie);
		for(int j=0;j<oldImgs.size();j++){
			oldImgs.get(j).setDelFlag(true);
		}
		
		long timeMillis = System.currentTimeMillis();
		//添加新图片
		if(StringUtils.isNotBlank(imgUrls)){
			String[] urls = imgUrls.split(Constants.IMAGE_URLS_SPLIT_STRING);
			for(int i=0;i<urls.length;i++){
				if(StringUtils.isNotBlank(urls[i])){
					boolean exist = false;
					for(int j=0;j<oldImgs.size();j++){
						if(urls[i].equals(oldImgs.get(j).getContentUrl())){
							exist = true;
							oldImgs.get(j).setDelFlag(false);
							break;
						}
					}
					if(!exist){
						SHHImage si = new SHHImage();
						si.setId(UUID.randomUUID().toString());
						si.setContentUrl(urls[i]);
						si.setCreater(shh.getLastEditor());
						si.setLastEditor(shh.getLastEditor());
						si.setSecondHandHouseId(shh.getId());
						si.setName(shh.getName());
						timeMillis = timeMillis + 1000;
						Date nowDate = new Date(timeMillis);
						si.setCreateDate(nowDate);
						si.setUpdateDate(nowDate);
						shhim.insertSelective(si);
					}
				}
			}
		}
		//删除图片
		List<String> delIds = new ArrayList<String>();
		for(int j=0;j<oldImgs.size();j++){
			if(oldImgs.get(j).getDelFlag()){
				delIds.add(oldImgs.get(j).getId());
//				shhim.updateByPrimaryKeySelective(oldImgs.get(j));
			}
		}
		if(!delIds.isEmpty()){
			SHHImageExample ie2 = new SHHImageExample();
			ie2.or().andIdIn(delIds).andDelFlagEqualTo(false);
			SHHImage si = new SHHImage();
			si.setDelFlag(true);
			si.setLastEditor(shh.getLastEditor());
			shhim.updateByExampleSelective(si, ie2);
		}
		
		//刷新小区
		if(RCExisted){
			rcService.refreshResidenceCommunity(rc.getId(), true, false);
		}
		return null;
	}
	

	@Override
	public String deleteUserSHHByFlag(String id) {
		String userId = userService.getCurUserId();
		if(StringUtils.isBlank(userId)){
			return "请先登录";
		}
		SecondHandHouseExample shhe = new SecondHandHouseExample();
		shhe.or().andIdEqualTo(id).andCreaterEqualTo(userId).andDelFlagEqualTo(false);
		if(shhm.countByExample(shhe)==0){
			return "要删除的数据不存在";
		}
		
		//删除二手房
		SecondHandHouse shh = new SecondHandHouse();
		shh.setId(id);
		shh.setDelFlag(true);
		shh.setLastEditor(userId);
		shhm.updateByPrimaryKeySelective(shh);
		
		//删除图片
		SHHImage si = new SHHImage();
		si.setDelFlag(true);
		si.setLastEditor(shh.getLastEditor());
		SHHImageExample ie = new SHHImageExample();
		ie.or().andSecondHandHouseIdEqualTo(id).andDelFlagEqualTo(false);
		shhim.updateByExampleSelective(si, ie);
		
		//刷新小区
		rcService.refreshResidenceCommunity(shh.getResidenceCommunityId(), true, false);
		return null;
	}

	@Override
	public String refreshUserSHH(String id) {
		String userId = userService.getCurUserId();
		if(StringUtils.isBlank(userId)){
			return "请先登录";
		}
		SecondHandHouseExample shhe = new SecondHandHouseExample();
		shhe.or().andIdEqualTo(id).andCreaterEqualTo(userId).andDelFlagEqualTo(false);
		if(shhm.countByExample(shhe)==0){
			return "要刷新的数据不存在";
		}
		SecondHandHouse shh = new SecondHandHouse();
		shh.setId(id);
		shh.setUpdateDate(new Date(System.currentTimeMillis()));
		shh.setLastEditor(userId);
		shhm.updateByPrimaryKeySelective(shh);
		return null;
	}
	
	@Override
	public List<SecondHandHouse> findByParams(String name, String rcId,
			Integer targetPage, Integer pageSize) {
		if (StringUtils.isBlank(rcId)) {
			return null;
		}
		SecondHandHouseExample shhe = bindSecondHandHouseParams(rcId, name);
		if (targetPage != null && pageSize != null) {
			shhe.setLimitStart(targetPage * pageSize);
			shhe.setLimitSize(pageSize);
		}
		return shhm.selectByExample(shhe);
	}

	@Override
	public int countByParams(String name, String rcId) {
		if (StringUtils.isBlank(rcId)) {
			return 0;
		}
		SecondHandHouseExample shhe = bindSecondHandHouseParams(rcId, name);
		return shhm.countByExample(shhe);
	}

	private SecondHandHouseExample bindSecondHandHouseParams(String rcId, String name) {
		SecondHandHouseExample shhe = new SecondHandHouseExample();
		SecondHandHouseExample.Criteria cri = shhe.createCriteria();
		cri.andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(rcId)) {
			cri.andResidenceCommunityIdEqualTo(rcId);
		}
		if (StringUtils.isNotBlank(name)) {
			cri.andNameLike("%" + name + "%");
		}
		return shhe;
	}

	@Override
	public Map<String, String> addSHH(SecondHandHouse shh) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (shh == null) {
			resMap.put("msg", "二手房内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(shh);
		if(!resMap.isEmpty()){
			return resMap;
		}
		shh.setId(UUID.randomUUID().toString());
		shh.setCreater(curUserId);
		shh.setLastEditor(curUserId);
		shhm.insertSelective(shh);
		return resMap;
	}

	@Override
	public Map<String, String> updateSHH(SecondHandHouse shh) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (shh == null) {
			resMap.put("msg", "二手房内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(shh);
		if(!resMap.isEmpty()){
			return resMap;
		}
		shh.setLastEditor(curUserId);
		shhm.updateByPrimaryKeySelective(shh);
		return resMap;
	}


	@Override
	public Map<String, String> batchDel(String shhIds) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (StringUtils.isBlank(shhIds)) {
			resMap.put("msg", "删除内容不能为空");
			return resMap;
		}
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		String[] ids = shhIds.split(Constants.MI_IDS_SPLIT_STRING);
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotBlank(ids[i])) {
				idList.add(ids[i]);
			}
		}
		if (!idList.isEmpty()) {
			SecondHandHouseExample shhe = new SecondHandHouseExample();
			shhe.or().andIdIn(idList).andDelFlagEqualTo(false);
			SecondHandHouse shh = new SecondHandHouse();
			shh.setDelFlag(true);
			shh.setLastEditor(curUserId);
			shhm.updateByExampleSelective(shh, shhe);
			
			SHHPanoExample pe = new SHHPanoExample();
			pe.or().andSecondHandHouseIdIn(idList).andDelFlagEqualTo(false);
			SHHPano pano = new SHHPano();
			pano.setDelFlag(true);
			pano.setLastEditor(curUserId);
			shhpm.updateByExampleSelective(pano, pe);
			
			SHHImageExample ie = new SHHImageExample();
			ie.or().andSecondHandHouseIdIn(idList).andDelFlagEqualTo(false);
			SHHImage image = new SHHImage();
			image.setDelFlag(true);
			image.setLastEditor(curUserId);
			shhim.updateByExampleSelective(image, ie);
		}
		return resMap;
	}
	
	private Map<String, String> checkInfo(SecondHandHouse shh) {
		Map<String, String> resMap = new HashMap<String, String>();
		if (shh == null) {
			resMap.put("msg", "二手房内容不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(shh.getResidenceCommunityId()) || StringUtils.isBlank(shh.getResidenceCommunityName())) {
			resMap.put("msg", "二手房所属小区不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(shh.getPreImageUrl())) {
			resMap.put("msg", "二手房缩略图不能为空");
			return resMap;
		}
		if (StringUtils.isBlank(shh.getName())) {
			resMap.put("msg", "二手房名称不能为空");
			return resMap;
		}
		return resMap;
	}
}
