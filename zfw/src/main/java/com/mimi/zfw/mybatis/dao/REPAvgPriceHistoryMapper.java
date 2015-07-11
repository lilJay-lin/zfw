package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.REPAvgPriceHistory;
import com.mimi.zfw.mybatis.pojo.REPAvgPriceHistoryExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface REPAvgPriceHistoryMapper extends IBaseDao<REPAvgPriceHistory, REPAvgPriceHistoryExample, String> {
    int countByExample(REPAvgPriceHistoryExample example);

    int deleteByExample(REPAvgPriceHistoryExample example);

    int deleteByPrimaryKey(String id);

    int insert(REPAvgPriceHistory record);

    int insertSelective(REPAvgPriceHistory record);

    List<REPAvgPriceHistory> selectByExample(REPAvgPriceHistoryExample example);

    REPAvgPriceHistory selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") REPAvgPriceHistory record, @Param("example") REPAvgPriceHistoryExample example);

    int updateByExample(@Param("record") REPAvgPriceHistory record, @Param("example") REPAvgPriceHistoryExample example);

    int updateByPrimaryKeySelective(REPAvgPriceHistory record);

    int updateByPrimaryKey(REPAvgPriceHistory record);
}