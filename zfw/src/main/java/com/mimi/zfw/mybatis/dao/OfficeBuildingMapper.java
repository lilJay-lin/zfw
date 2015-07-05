package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.OfficeBuilding;
import com.mimi.zfw.mybatis.pojo.OfficeBuildingExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OfficeBuildingMapper extends IBaseDao<OfficeBuilding, OfficeBuildingExample, String> {
    int countByExample(OfficeBuildingExample example);

    int deleteByExample(OfficeBuildingExample example);

    int deleteByPrimaryKey(String id);

    int insert(OfficeBuilding record);

    int insertSelective(OfficeBuilding record);

    List<OfficeBuilding> selectByExample(OfficeBuildingExample example);

    OfficeBuilding selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OfficeBuilding record, @Param("example") OfficeBuildingExample example);

    int updateByExample(@Param("record") OfficeBuilding record, @Param("example") OfficeBuildingExample example);

    int updateByPrimaryKeySelective(OfficeBuilding record);

    int updateByPrimaryKey(OfficeBuilding record);
}