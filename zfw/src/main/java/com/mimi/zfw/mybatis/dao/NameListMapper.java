package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.NameList;
import com.mimi.zfw.mybatis.pojo.NameListExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NameListMapper extends IBaseDao<NameList, NameListExample, String> {
    int countByExample(NameListExample example);

    int deleteByExample(NameListExample example);

    int deleteByPrimaryKey(String id);

    int insert(NameList record);

    int insertSelective(NameList record);

    List<NameList> selectByExample(NameListExample example);

    NameList selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NameList record, @Param("example") NameListExample example);

    int updateByExample(@Param("record") NameList record, @Param("example") NameListExample example);

    int updateByPrimaryKeySelective(NameList record);

    int updateByPrimaryKey(NameList record);
}