package com.mimi.zfw.service;

import java.util.List;

import com.mimi.zfw.mybatis.pojo.SHHPano;
import com.mimi.zfw.mybatis.pojo.SHHPanoExample;

public interface ISHHPanoService extends
		IBaseService<SHHPano, SHHPanoExample, String> {

	List<SHHPano> getPanosBySHHId(String id);
}
