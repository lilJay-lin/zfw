package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.RelationUserAndREP;
import com.mimi.zfw.mybatis.pojo.RelationUserAndREPExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelationUserAndREPMapper extends IBaseDao<RelationUserAndREP, RelationUserAndREPExample, String> {
    int countByExample(RelationUserAndREPExample example);

    int deleteByExample(RelationUserAndREPExample example);

    int deleteByPrimaryKey(String id);

    int insert(RelationUserAndREP record);

    int insertSelective(RelationUserAndREP record);

    List<RelationUserAndREP> selectByExample(RelationUserAndREPExample example);

    RelationUserAndREP selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RelationUserAndREP record, @Param("example") RelationUserAndREPExample example);

    int updateByExample(@Param("record") RelationUserAndREP record, @Param("example") RelationUserAndREPExample example);

    int updateByPrimaryKeySelective(RelationUserAndREP record);

    int updateByPrimaryKey(RelationUserAndREP record);
}