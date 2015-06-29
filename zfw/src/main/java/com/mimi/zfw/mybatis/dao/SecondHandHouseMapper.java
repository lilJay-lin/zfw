package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.SecondHandHouse;
import com.mimi.zfw.mybatis.pojo.SecondHandHouseExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SecondHandHouseMapper extends IBaseDao<SecondHandHouse, SecondHandHouseExample, String> {
    int countByExample(SecondHandHouseExample example);

    int deleteByExample(SecondHandHouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(SecondHandHouse record);

    int insertSelective(SecondHandHouse record);

    List<SecondHandHouse> selectByExample(SecondHandHouseExample example);

    SecondHandHouse selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SecondHandHouse record, @Param("example") SecondHandHouseExample example);

    int updateByExample(@Param("record") SecondHandHouse record, @Param("example") SecondHandHouseExample example);

    int updateByPrimaryKeySelective(SecondHandHouse record);

    int updateByPrimaryKey(SecondHandHouse record);
}