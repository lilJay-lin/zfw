package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.RHPano;
import com.mimi.zfw.mybatis.pojo.RHPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RHPanoMapper extends IBaseDao<RHPano, RHPanoExample, String> {
    int countByExample(RHPanoExample example);

    int deleteByExample(RHPanoExample example);

    int deleteByPrimaryKey(String id);

    int insert(RHPano record);

    int insertSelective(RHPano record);

    List<RHPano> selectByExample(RHPanoExample example);

    RHPano selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RHPano record, @Param("example") RHPanoExample example);

    int updateByExample(@Param("record") RHPano record, @Param("example") RHPanoExample example);

    int updateByPrimaryKeySelective(RHPano record);

    int updateByPrimaryKey(RHPano record);
}