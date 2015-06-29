package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.HTImage;
import com.mimi.zfw.mybatis.pojo.HTImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HTImageMapper extends IBaseDao<HTImage, HTImageExample, String> {
    int countByExample(HTImageExample example);

    int deleteByExample(HTImageExample example);

    int deleteByPrimaryKey(String id);

    int insert(HTImage record);

    int insertSelective(HTImage record);

    List<HTImage> selectByExample(HTImageExample example);

    HTImage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") HTImage record, @Param("example") HTImageExample example);

    int updateByExample(@Param("record") HTImage record, @Param("example") HTImageExample example);

    int updateByPrimaryKeySelective(HTImage record);

    int updateByPrimaryKey(HTImage record);
}