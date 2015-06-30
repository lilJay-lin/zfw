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
		String[] preImgUrl = {
				"https://farm3.staticflickr.com/2567/5697107145_3c27ff3cd1_m.jpg",
				"https://farm2.staticflickr.com/1043/5186867718_06b2e9e551_m.jpg",
				"https://farm6.staticflickr.com/5023/5578283926_822e5e5791_m.jpg",
				"https://farm7.staticflickr.com/6175/6176698785_7dee72237e_m.jpg",
				"https://farm2.staticflickr.com/1043/5186867718_06b2e9e551_m.jpg" };
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
		ad.setPreImageUrl("http://img2.imgtn.bdimg.com/it/u=944210427,3514583163&fm=15&gp=0.jpg");
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
