package com.mimi.zfw.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.dao.AdvertisementMapper;
import com.mimi.zfw.mybatis.pojo.Advertisement;
import com.mimi.zfw.mybatis.pojo.AdvertisementExample;
import com.mimi.zfw.plugin.IBaseDao;
import com.mimi.zfw.service.IAdvertisementService;

@Service
public class AdvertisementServiceImpl extends
		BaseService<Advertisement, AdvertisementExample, String> implements
		IAdvertisementService {

	@Resource
	private AdvertisementMapper am;

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
		ae.or().andLocationEqualTo(location).andActiveEqualTo(true).andDelFlagEqualTo(false);
		return am.selectByExample(ae);
	}

}
