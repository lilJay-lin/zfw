package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.WarehousePano;
import com.mimi.zfw.mybatis.pojo.WarehousePanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WarehousePanoMapper extends IBaseDao<WarehousePano, WarehousePanoExample, String> {
    int countByExample(WarehousePanoExample example);

    int deleteByExample(WarehousePanoExample example);

    int deleteByPrimaryKey(String id);

    int insert(WarehousePano record);

    int insertSelective(WarehousePano record);

    List<WarehousePano> selectByExample(WarehousePanoExample example);

    WarehousePano selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WarehousePano record, @Param("example") WarehousePanoExample example);

    int updateByExample(@Param("record") WarehousePano record, @Param("example") WarehousePanoExample example);

    int updateByPrimaryKeySelective(WarehousePano record);

    int updateByPrimaryKey(WarehousePano record);
}