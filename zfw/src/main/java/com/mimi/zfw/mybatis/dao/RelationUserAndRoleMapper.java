package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.RelationUserAndRole;
import com.mimi.zfw.mybatis.pojo.RelationUserAndRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelationUserAndRoleMapper {
    int countByExample(RelationUserAndRoleExample example);

    int deleteByExample(RelationUserAndRoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(RelationUserAndRole record);

    int insertSelective(RelationUserAndRole record);

    List<RelationUserAndRole> selectByExample(RelationUserAndRoleExample example);

    RelationUserAndRole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RelationUserAndRole record, @Param("example") RelationUserAndRoleExample example);

    int updateByExample(@Param("record") RelationUserAndRole record, @Param("example") RelationUserAndRoleExample example);

    int updateByPrimaryKeySelective(RelationUserAndRole record);

    int updateByPrimaryKey(RelationUserAndRole record);
}