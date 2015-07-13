package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.OBImage;
import com.mimi.zfw.mybatis.pojo.OBImageExample;

public interface IOBImageService extends
	IBaseService<OBImage, OBImageExample, String> {

    List<OBImage> getImagesByParams(String id, int targetPage, int pageSize);

    public int updateBatchOBImage(String oBImageIds, OBImage oBImage);

    public List<OBImage> findOBImageByExample(OBImageExample example,
	    Integer curPage, Integer pageSize);

    public int countOBImageByExample(OBImageExample example);

    public Map<String, String> addOBImage(OBImage oBImage);

    public Map<String, String> updateOBImage(OBImage oBImage);

    public Map<String, String> checkOBImage(OBImage oBImage);

}
