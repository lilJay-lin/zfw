package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.REPImage;
import com.mimi.zfw.mybatis.pojo.REPImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface REPImageMapper extends IBaseDao<REPImage, REPImageExample, String> {
    int countByExample(REPImageExample example);

    int deleteByExample(REPImageExample example);

    int deleteByPrimaryKey(String id);

    int insert(REPImage record);

    int insertSelective(REPImage record);

    List<REPImage> selectByExample(REPImageExample example);

    REPImage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") REPImage record, @Param("example") REPImageExample example);

    int updateByExample(@Param("record") REPImage record, @Param("example") REPImageExample example);

    int updateByPrimaryKeySelective(REPImage record);

    int updateByPrimaryKey(REPImage record);
}