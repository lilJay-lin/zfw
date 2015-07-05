package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.SHHFloorPriceLinearFunction;
import com.mimi.zfw.mybatis.pojo.SHHFloorPriceLinearFunctionExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SHHFloorPriceLinearFunctionMapper extends IBaseDao<SHHFloorPriceLinearFunction, SHHFloorPriceLinearFunctionExample, String> {
    int countByExample(SHHFloorPriceLinearFunctionExample example);

    int deleteByExample(SHHFloorPriceLinearFunctionExample example);

    int deleteByPrimaryKey(String id);

    int insert(SHHFloorPriceLinearFunction record);

    int insertSelective(SHHFloorPriceLinearFunction record);

    List<SHHFloorPriceLinearFunction> selectByExample(SHHFloorPriceLinearFunctionExample example);

    SHHFloorPriceLinearFunction selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SHHFloorPriceLinearFunction record, @Param("example") SHHFloorPriceLinearFunctionExample example);

    int updateByExample(@Param("record") SHHFloorPriceLinearFunction record, @Param("example") SHHFloorPriceLinearFunctionExample example);

    int updateByPrimaryKeySelective(SHHFloorPriceLinearFunction record);

    int updateByPrimaryKey(SHHFloorPriceLinearFunction record);
}