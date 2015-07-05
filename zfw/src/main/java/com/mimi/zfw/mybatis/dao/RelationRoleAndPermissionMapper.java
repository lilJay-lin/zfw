package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.RelationRoleAndPermission;
import com.mimi.zfw.mybatis.pojo.RelationRoleAndPermissionExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RelationRoleAndPermissionMapper extends IBaseDao<RelationRoleAndPermission, RelationRoleAndPermissionExample, String> {
    int countByExample(RelationRoleAndPermissionExample example);

    int deleteByExample(RelationRoleAndPermissionExample example);

    int deleteByPrimaryKey(String id);

    int insert(RelationRoleAndPermission record);

    int insertSelective(RelationRoleAndPermission record);

    List<RelationRoleAndPermission> selectByExample(RelationRoleAndPermissionExample example);

    RelationRoleAndPermission selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RelationRoleAndPermission record, @Param("example") RelationRoleAndPermissionExample example);

    int updateByExample(@Param("record") RelationRoleAndPermission record, @Param("example") RelationRoleAndPermissionExample example);

    int updateByPrimaryKeySelective(RelationRoleAndPermission record);

    int updateByPrimaryKey(RelationRoleAndPermission record);
}