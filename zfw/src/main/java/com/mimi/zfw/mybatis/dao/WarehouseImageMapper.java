package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.WarehouseImage;
import com.mimi.zfw.mybatis.pojo.WarehouseImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WarehouseImageMapper extends IBaseDao<WarehouseImage, WarehouseImageExample, String> {
    int countByExample(WarehouseImageExample example);

    int deleteByExample(WarehouseImageExample example);

    int deleteByPrimaryKey(String id);

    int insert(WarehouseImage record);

    int insertSelective(WarehouseImage record);

    List<WarehouseImage> selectByExample(WarehouseImageExample example);

    WarehouseImage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") WarehouseImage record, @Param("example") WarehouseImageExample example);

    int updateByExample(@Param("record") WarehouseImage record, @Param("example") WarehouseImageExample example);

    int updateByPrimaryKeySelective(WarehouseImage record);

    int updateByPrimaryKey(WarehouseImage record);
}