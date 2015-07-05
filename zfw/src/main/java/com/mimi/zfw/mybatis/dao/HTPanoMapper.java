package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.HTPano;
import com.mimi.zfw.mybatis.pojo.HTPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HTPanoMapper extends IBaseDao<HTPano, HTPanoExample, String> {
    int countByExample(HTPanoExample example);

    int deleteByExample(HTPanoExample example);

    int deleteByPrimaryKey(String id);

    int insert(HTPano record);

    int insertSelective(HTPano record);

    List<HTPano> selectByExample(HTPanoExample example);

    HTPano selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") HTPano record, @Param("example") HTPanoExample example);

    int updateByExample(@Param("record") HTPano record, @Param("example") HTPanoExample example);

    int updateByPrimaryKeySelective(HTPano record);

    int updateByPrimaryKey(HTPano record);
}