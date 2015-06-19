package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.REPVideo;
import com.mimi.zfw.mybatis.pojo.REPVideoExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface REPVideoMapper extends IBaseDao<REPVideo, REPVideoExample, String> {
    int countByExample(REPVideoExample example);

    int deleteByExample(REPVideoExample example);

    int deleteByPrimaryKey(String id);

    int insert(REPVideo record);

    int insertSelective(REPVideo record);

    List<REPVideo> selectByExample(REPVideoExample example);

    REPVideo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") REPVideo record, @Param("example") REPVideoExample example);

    int updateByExample(@Param("record") REPVideo record, @Param("example") REPVideoExample example);

    int updateByPrimaryKeySelective(REPVideo record);

    int updateByPrimaryKey(REPVideo record);
}