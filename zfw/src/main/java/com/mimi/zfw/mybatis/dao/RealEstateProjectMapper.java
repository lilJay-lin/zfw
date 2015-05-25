package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.RealEstateProject;
import com.mimi.zfw.mybatis.pojo.RealEstateProjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RealEstateProjectMapper {
    int countByExample(RealEstateProjectExample example);

    int deleteByExample(RealEstateProjectExample example);

    int deleteByPrimaryKey(String id);

    int insert(RealEstateProject record);

    int insertSelective(RealEstateProject record);

    List<RealEstateProject> selectByExample(RealEstateProjectExample example);

    RealEstateProject selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RealEstateProject record, @Param("example") RealEstateProjectExample example);

    int updateByExample(@Param("record") RealEstateProject record, @Param("example") RealEstateProjectExample example);

    int updateByPrimaryKeySelective(RealEstateProject record);

    int updateByPrimaryKey(RealEstateProject record);
}