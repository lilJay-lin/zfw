package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.SHHPano;
import com.mimi.zfw.mybatis.pojo.SHHPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SHHPanoMapper extends IBaseDao<SHHPano, SHHPanoExample, String> {
    int countByExample(SHHPanoExample example);

    int deleteByExample(SHHPanoExample example);

    int deleteByPrimaryKey(String id);

    int insert(SHHPano record);

    int insertSelective(SHHPano record);

    List<SHHPano> selectByExample(SHHPanoExample example);

    SHHPano selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SHHPano record, @Param("example") SHHPanoExample example);

    int updateByExample(@Param("record") SHHPano record, @Param("example") SHHPanoExample example);

    int updateByPrimaryKeySelective(SHHPano record);

    int updateByPrimaryKey(SHHPano record);
}