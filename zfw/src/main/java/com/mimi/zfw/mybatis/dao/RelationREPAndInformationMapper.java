package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.RelationREPAndInformation;
import com.mimi.zfw.mybatis.pojo.RelationREPAndInformationExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelationREPAndInformationMapper extends IBaseDao<RelationREPAndInformation, RelationREPAndInformationExample, String> {
    int countByExample(RelationREPAndInformationExample example);

    int deleteByExample(RelationREPAndInformationExample example);

    int deleteByPrimaryKey(String id);

    int insert(RelationREPAndInformation record);

    int insertSelective(RelationREPAndInformation record);

    List<RelationREPAndInformation> selectByExample(RelationREPAndInformationExample example);

    RelationREPAndInformation selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RelationREPAndInformation record, @Param("example") RelationREPAndInformationExample example);

    int updateByExample(@Param("record") RelationREPAndInformation record, @Param("example") RelationREPAndInformationExample example);

    int updateByPrimaryKeySelective(RelationREPAndInformation record);

    int updateByPrimaryKey(RelationREPAndInformation record);
}