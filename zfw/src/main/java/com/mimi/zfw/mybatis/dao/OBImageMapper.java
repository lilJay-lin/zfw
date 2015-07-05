package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.OBImage;
import com.mimi.zfw.mybatis.pojo.OBImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OBImageMapper extends IBaseDao<OBImage, OBImageExample, String> {
    int countByExample(OBImageExample example);

    int deleteByExample(OBImageExample example);

    int deleteByPrimaryKey(String id);

    int insert(OBImage record);

    int insertSelective(OBImage record);

    List<OBImage> selectByExample(OBImageExample example);

    OBImage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OBImage record, @Param("example") OBImageExample example);

    int updateByExample(@Param("record") OBImage record, @Param("example") OBImageExample example);

    int updateByPrimaryKeySelective(OBImage record);

    int updateByPrimaryKey(OBImage record);
}