package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.OBPano;
import com.mimi.zfw.mybatis.pojo.OBPanoExample;

public interface IOBPanoService extends
		IBaseService<OBPano, OBPanoExample, String> {

	List<OBPano> getPanosByOBId(String id);
}
