package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.HTRing;
import com.mimi.zfw.mybatis.pojo.HTRingExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HTRingMapper extends IBaseDao<HTRing, HTRingExample, String> {
    int countByExample(HTRingExample example);

    int deleteByExample(HTRingExample example);

    int deleteByPrimaryKey(String id);

    int insert(HTRing record);

    int insertSelective(HTRing record);

    List<HTRing> selectByExample(HTRingExample example);

    HTRing selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") HTRing record, @Param("example") HTRingExample example);

    int updateByExample(@Param("record") HTRing record, @Param("example") HTRingExample example);

    int updateByPrimaryKeySelective(HTRing record);

    int updateByPrimaryKey(HTRing record);
}