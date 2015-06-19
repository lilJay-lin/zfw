package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.Advertisement;
import com.mimi.zfw.mybatis.pojo.AdvertisementExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvertisementMapper extends IBaseDao<Advertisement, AdvertisementExample, String> {
    int countByExample(AdvertisementExample example);

    int deleteByExample(AdvertisementExample example);

    int deleteByPrimaryKey(String id);

    int insert(Advertisement record);

    int insertSelective(Advertisement record);

    List<Advertisement> selectByExample(AdvertisementExample example);

    Advertisement selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Advertisement record, @Param("example") AdvertisementExample example);

    int updateByExample(@Param("record") Advertisement record, @Param("example") AdvertisementExample example);

    int updateByPrimaryKeySelective(Advertisement record);

    int updateByPrimaryKey(Advertisement record);
}