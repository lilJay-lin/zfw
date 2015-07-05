package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.HouseType;
import com.mimi.zfw.mybatis.pojo.HouseTypeExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HouseTypeMapper extends IBaseDao<HouseType, HouseTypeExample, String> {
    int countByExample(HouseTypeExample example);

    int deleteByExample(HouseTypeExample example);

    int deleteByPrimaryKey(String id);

    int insert(HouseType record);

    int insertSelective(HouseType record);

    List<HouseType> selectByExample(HouseTypeExample example);

    HouseType selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") HouseType record, @Param("example") HouseTypeExample example);

    int updateByExample(@Param("record") HouseType record, @Param("example") HouseTypeExample example);

    int updateByPrimaryKeySelective(HouseType record);

    int updateByPrimaryKey(HouseType record);
}