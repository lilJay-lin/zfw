package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.ResidenceCommunity;
import com.mimi.zfw.mybatis.pojo.ResidenceCommunityExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResidenceCommunityMapper extends IBaseDao<ResidenceCommunity, ResidenceCommunityExample, String> {
    int countByExample(ResidenceCommunityExample example);

    int deleteByExample(ResidenceCommunityExample example);

    int deleteByPrimaryKey(String id);

    int insert(ResidenceCommunity record);

    int insertSelective(ResidenceCommunity record);

    List<ResidenceCommunity> selectByExample(ResidenceCommunityExample example);

    ResidenceCommunity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ResidenceCommunity record, @Param("example") ResidenceCommunityExample example);

    int updateByExample(@Param("record") ResidenceCommunity record, @Param("example") ResidenceCommunityExample example);

    int updateByPrimaryKeySelective(ResidenceCommunity record);

    int updateByPrimaryKey(ResidenceCommunity record);
}