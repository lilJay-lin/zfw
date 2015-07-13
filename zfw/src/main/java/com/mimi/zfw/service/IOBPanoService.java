package com.mimi.zfw.service;

import java.util.List;
import java.util.Map;

import com.mimi.zfw.mybatis.pojo.OBPano;
import com.mimi.zfw.mybatis.pojo.OBPanoExample;
import com.mimi.zfw.mybatis.pojo.OBPano;

public interface IOBPanoService extends
	IBaseService<OBPano, OBPanoExample, String> {

    List<OBPano> getPanosByOBId(String id);

    public int updateBatchOBPano(String oBPanoIds, OBPano oBPano);

    public List<OBPano> findOBPanoByExample(OBPanoExample example,
	    Integer curPage, Integer pageSize);

    public int countOBPanoByExample(OBPanoExample example);

    public Map<String, String> addOBPano(OBPano oBPano);

    public Map<String, String> updateOBPano(OBPano oBPano);

    public Map<String, String> checkOBPano(OBPano oBPano);

}
