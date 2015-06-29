package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.RCImage;
import com.mimi.zfw.mybatis.pojo.RCImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RCImageMapper extends IBaseDao<RCImage, RCImageExample, String> {
    int countByExample(RCImageExample example);

    int deleteByExample(RCImageExample example);

    int deleteByPrimaryKey(String id);

    int insert(RCImage record);

    int insertSelective(RCImage record);

    List<RCImage> selectByExample(RCImageExample example);

    RCImage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RCImage record, @Param("example") RCImageExample example);

    int updateByExample(@Param("record") RCImage record, @Param("example") RCImageExample example);

    int updateByPrimaryKeySelective(RCImage record);

    int updateByPrimaryKey(RCImage record);
}