package com.mimi.zfw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mimi.zfw.Constants;
import com.mimi.zfw.mybatis.pojo.HTImage;
import com.mimi.zfw.mybatis.pojo.HTPano;
import com.mimi.zfw.mybatis.pojo.HTRing;
import com.mimi.zfw.mybatis.pojo.HouseType;
import com.mimi.zfw.mybatis.pojo.RealEstateProject;
import com.mimi.zfw.service.IAliyunOSSService;
import com.mimi.zfw.service.IHTImageService;
import com.mimi.zfw.service.IHTPanoService;
import com.mimi.zfw.service.IHTRingService;
import com.mimi.zfw.service.IHouseTypeService;
import com.mimi.zfw.service.IRealEstateProjectService;

@Controller
public class HXController {

	@Resource
	private IHouseTypeService htService;
	
	@Resource
	private IRealEstateProjectService repService;

	@Resource
	private IHTImageService hiService;

	@Resource
	private IHTPanoService hpService;

	@Resource
	private IHTRingService hrService;
	@Resource
	private IAliyunOSSService aossService;

	@RequestMapping(value = "/hx/{id}/detail", method = { RequestMethod.GET })
	public String toHXDetail(HttpServletRequest request, @PathVariable String id) {
		
		int targetPage = 0;
		int pageSize = 2;
		List<HTImage> images = hiService.getImagesByParams(id, targetPage,
				pageSize);
		List<HTPano> panos = hpService.getPanosByHTId(id);
		List<HTRing> rings = hrService.getRingsByHTId(id);
		List<Map<String, String>> topImgs = new ArrayList<Map<String, String>>();
		for (int i = 0; i < images.size(); i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "image");
			map.put("imgUrl", images.get(i).getContentUrl());
			topImgs.add(map);
		}
		for (int i = 0; i < panos.size() && i<pageSize; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "pano");
			map.put("imgUrl", panos.get(i).getPreImageUrl());
			topImgs.add(map);
		}
		for (int i = 0; i < rings.size() && i<pageSize; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "ring");
			map.put("imgUrl", rings.get(i).getPreImageUrl());
			topImgs.add(map);
		}

		if(panos!=null && !panos.isEmpty()){
			for(int i=0;i<panos.size();i++){
				HTPano pano = panos.get(i);
				pano.setPreImageUrl(aossService.addImgParams(pano.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_BANNER));
			}
		}
		if(rings!=null && !rings.isEmpty()){
			for(int i=0;i<rings.size();i++){
				HTRing ring = rings.get(i);
				ring.setPreImageUrl(aossService.addImgParams(ring.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_BANNER));
			}
		}
		if(topImgs!=null && !topImgs.isEmpty()){
			for(int i=0;i<topImgs.size();i++){
				Map<String, String> map = topImgs.get(i);
				map.put("imgUrl",aossService.addImgParams(map.get("imgUrl"), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_BANNER));
			}
		}

		
		HouseType ht = htService.get(id);
		RealEstateProject rep = repService.get(ht.getRealEstateProjectId());
		
		
		request.setAttribute("panos", panos);
		request.setAttribute("rings", rings);
		request.setAttribute("hx", ht);
		request.setAttribute("rep", rep);
		request.setAttribute("topImgs", topImgs);
		return "ui/xf/hxDetail";
	}

	@RequestMapping(value = "/hx/{id}/photos", method = { RequestMethod.GET })
	public String toHXPhoto(HttpServletRequest request, @PathVariable String id) {
		List<HTImage> images = hiService.getImagesByHTId(id);
		List<HTPano> panos = hpService.getPanosByHTId(id);
		List<HTRing> rings = hrService.getRingsByHTId(id);
		List<Map<String,Object>> photos = new ArrayList<Map<String,Object>>();
		if(panos!=null && !panos.isEmpty()){
			Map<String,Object> listMap = new HashMap<String,Object>();
			listMap.put("name", Constants.PHOTO_DATA_TITLE_PANO);
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for(int i=0;i<panos.size();i++){
				HTPano pano = panos.get(i);
				Map<String,String> map = new HashMap<String,String>();
				map.put("name", pano.getName());
				map.put("contentUrl", aossService.addImgParams(pano.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_IMG));
				map.put("preImageUrl", aossService.addImgParams(pano.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				map.put("dataType", Constants.PHOTO_DATA_TYPE_PANO);
				list.add(map);
			}
			listMap.put("list", list);
			photos.add(listMap);
		}
		if(rings!=null && !rings.isEmpty()){
			Map<String,Object> listMap = new HashMap<String,Object>();
			listMap.put("name", Constants.PHOTO_DATA_TITLE_RING);
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for(int i=0;i<rings.size();i++){
				HTRing ring = rings.get(i);
				Map<String,String> map = new HashMap<String,String>();
				map.put("name", ring.getName());
				map.put("contentUrl", aossService.addImgParams(ring.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_IMG));
				map.put("preImageUrl", aossService.addImgParams(ring.getPreImageUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				map.put("dataType", Constants.PHOTO_DATA_TYPE_PANO);
				list.add(map);
			}
			listMap.put("list", list);
			photos.add(listMap);
		}

		if(images!=null && !images.isEmpty()){
			Map<String,Object> listMap = new HashMap<String,Object>();
			listMap.put("name", Constants.PHOTO_DATA_TITLE_IMAGE);
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for(int i=0;i<images.size();i++){
				HTImage image = images.get(i);
				Map<String,String> map = new HashMap<String,String>();
				map.put("name", image.getName());
				map.put("contentUrl", aossService.addImgParams(image.getContentUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_IMG));
				map.put("preImageUrl", aossService.addImgParams(image.getContentUrl(), Constants.ALIYUN_OSS_IMAGE_PARAMS_TYPE_PHOTO_SMALL_IMG));
				map.put("dataType", Constants.PHOTO_DATA_TYPE_IMAGE);
				list.add(map);
			}
			listMap.put("list", list);
			photos.add(listMap);
		}
		request.setAttribute("photos", photos);
		return "ui/photo/photoList";
	}

}
