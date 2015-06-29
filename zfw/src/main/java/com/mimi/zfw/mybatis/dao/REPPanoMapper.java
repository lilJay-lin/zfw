package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.REPPano;
import com.mimi.zfw.mybatis.pojo.REPPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface REPPanoMapper extends IBaseDao<REPPano, REPPanoExample, String> {
    int countByExample(REPPanoExample example);

    int deleteByExample(REPPanoExample example);

    int deleteByPrimaryKey(String id);

    int insert(REPPano record);

    int insertSelective(REPPano record);

    List<REPPano> selectByExample(REPPanoExample example);

    REPPano selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") REPPano record, @Param("example") REPPanoExample example);

    int updateByExample(@Param("record") REPPano record, @Param("example") REPPanoExample example);

    int updateByPrimaryKeySelective(REPPano record);

    int updateByPrimaryKey(REPPano record);
}