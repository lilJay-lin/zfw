package com.mimi.zfw.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.AdvertisementMapper;
import com.mimi.zfw.mybatis.pojo.Advertisement;
import com.mimi.zfw.mybatis.pojo.AdvertisementExample;
import com.mimi.zfw.mybatis.pojo.Information;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IAdvertisementService;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.FormatUtil;

@Service
public class AdvertisementServiceImpl extends
		BaseService<Advertisement, AdvertisementExample, String> implements
		IAdvertisementService {

	@Resource
	private AdvertisementMapper am;

	@Resource
	private IUserService userService;

	@Resource
	@Override
	public void setBaseDao(
			IBaseDao<Advertisement, AdvertisementExample, String> baseDao) {
		this.baseDao = baseDao;
		this.am = (AdvertisementMapper) baseDao;
	}

	@Override
	public void initAdvertisement() {
		AdvertisementExample ae = new AdvertisementExample();
		ae.or().andDelFlagEqualTo(false);
		int count = am.countByExample(ae);
		if (count < 1) {
			initTestData();
		}
	}

	private void initTestData() {
		Date nowDate = new Date(System.currentTimeMillis());
		String[] preImgUrl = Constants.ALIYUN_OSS_TEST_IMG_URLS;
		for (int i = 0; i < 5; i++) {
			Advertisement ad = new Advertisement();
			ad.setId(UUID.randomUUID().toString());
			ad.setCreateDate(nowDate);
			ad.setContentUrl("http://www.baidu.com");
			ad.setLocation(Constants.AD_LOCATION_HOME_TOP);
			ad.setName("广告");
			ad.setPreImageUrl(preImgUrl[i]);
			am.insertSelective(ad);
		}
		for (int i = 0; i < 5; i++) {
			Advertisement ad = new Advertisement();
			ad.setId(UUID.randomUUID().toString());
			ad.setCreateDate(nowDate);
			ad.setContentUrl("http://www.baidu.com");
			ad.setLocation(Constants.AD_LOCATION_INFO_TOP);
			ad.setName("广告");
			if (i % 2 == 0) {
				ad.setActive(false);
			}
			ad.setPreImageUrl(preImgUrl[i]);
			am.insertSelective(ad);
		}
		for (int i = 0; i < 4; i++) {
			Advertisement ad = new Advertisement();
			ad.setId(UUID.randomUUID().toString());
			ad.setCreateDate(nowDate);
			ad.setContentUrl("http://www.baidu.com");
			ad.setLocation(Constants.AD_LOCATION_HOME_MIDDLE_FOUR);
			ad.setName("广告");
			ad.setPreImageUrl(preImgUrl[i]);
			ad.setSummary("提要");
			am.insertSelective(ad);
		}
		Advertisement ad = new Advertisement();
		ad.setId(UUID.randomUUID().toString());
		ad.setCreateDate(nowDate);
		ad.setContentUrl("http://www.baidu.com");
		ad.setLocation(Constants.AD_LOCATION_HOME_MIDDLE_ONE);
		ad.setName("广告");
		ad.setPreImageUrl(Constants.ALIYUN_OSS_TEST_IMG_URLS[5]);
		ad.setSummary("提要");
		am.insertSelective(ad);
	}

	@Override
	public List<Advertisement> getActiveByLocation(String location) {
		AdvertisementExample ae = new AdvertisementExample();
		ae.or().andLocationEqualTo(location).andActiveEqualTo(true)
				.andDelFlagEqualTo(false);
		ae.setOrderByClause("priority desc,update_date desc");
		return am.selectByExample(ae);
	}

	@Override
	public List<Advertisement> findByParams(String name, String location,
			Integer targetPage, Integer pageSize) {
		AdvertisementExample ae = bindAdvertisementParams(name, location);
		if (targetPage != null && pageSize != null) {
			ae.setLimitStart(targetPage * pageSize);
			ae.setLimitSize(pageSize);
		}
		ae.setOrderByClause("update_date desc,priority desc");
		return am.selectByExample(ae);
	}

	private AdvertisementExample bindAdvertisementParams(String name,
			String location) {
		AdvertisementExample ae = new AdvertisementExample();
		AdvertisementExample.Criteria cri = ae.createCriteria();
		cri.andDelFlagEqualTo(false);
		if (StringUtils.isNotBlank(name)) {
			cri.andNameLike("%" + name + "%");
		}
		if (StringUtils.isNotBlank(location)) {
			cri.andLocationEqualTo(location);
		}
		return ae;
	}

	@Override
	public int countByParams(String name, String location) {
		AdvertisementExample ae = bindAdvertisementParams(name, location);
		return am.countByExample(ae);
	}

	@Override
	public Map<String, String> modify(Advertisement ad) {
		Map<String, String> resMap = new HashMap<String, String>();
		String curUserId = userService.getCurUserId();
		if (StringUtils.isBlank(curUserId)) {
			resMap.put("msg", "请先登录");
			return resMap;
		}
		resMap = checkInfo(ad);
		if (!resMap.isEmpty()) {
			return resMap;
		}
		ad.setLastEditor(curUserId);
		am.updateByPrimaryKeySelective(ad);
		return resMap;
	}

	private Map<String, String> checkInfo(Advertisement ad) {
		Map<String, String> resMap = new HashMap<String, String>();
		
		if (ad == null) {
			resMap.put("msg", "广告内容不能为空");
			return resMap;
		}
		
		String name = ad.getName();
		String errStr = FormatUtil.checkFormate(name, true, FormatUtil.MAX_LENGTH_COMMON_SHORT_L3, "标题");
		if(StringUtils.isNotBlank(errStr)){
			resMap.put("field","name");
			resMap.put("msg", errStr);
			return resMap;
		}
		
		String contentUrl = ad.getContentUrl();
		errStr = FormatUtil.checkFormate(contentUrl, true, FormatUtil.MAX_LENGTH_COMMON_NORMAL_L2, "内容路径");
		if(StringUtils.isNotBlank(errStr)){
			resMap.put("field","contentUrl");
			resMap.put("msg", errStr);
			return resMap;
		}
		
		String summary = ad.getSummary();
		errStr = FormatUtil.checkFormate(summary, true, FormatUtil.MAX_LENGTH_COMMON_NORMAL_L2, "提要");
		if(StringUtils.isNotBlank(errStr)){
			resMap.put("field","summary");
			resMap.put("msg", errStr);
			return resMap;
		}
		
		String description = ad.getDescription();
		errStr = FormatUtil.checkFormate(description,false,FormatUtil.MAX_LENGTH_COMMON_NORMAL_L2, "描述");
		if(StringUtils.isNotBlank(errStr)){
			resMap.put("field","description");
			resMap.put("msg", errStr);
			return resMap;
		}
		
		Integer priority = ad.getPriority();
		errStr = FormatUtil.checkFormat(priority, FormatUtil.REGEX_COMMON_PRIORITY, false, "优先级");
		if(StringUtils.isNotBlank(errStr)){
			resMap.put("field","priority");
			resMap.put("msg", errStr);
			return resMap;
		}
		
		//数字为null设置为0
		defaultNumberParams(ad);
		
		return resMap;
	}
	private void defaultNumberParams(Advertisement ad){
	    Integer i = new Integer(0);
	    if(ad.getPriority() == null ){
		ad.setPriority(i);
	    }
	}
}
