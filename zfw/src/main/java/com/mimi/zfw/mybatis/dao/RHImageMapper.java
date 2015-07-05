package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.RHImage;
import com.mimi.zfw.mybatis.pojo.RHImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RHImageMapper extends IBaseDao<RHImage, RHImageExample, String> {
    int countByExample(RHImageExample example);

    int deleteByExample(RHImageExample example);

    int deleteByPrimaryKey(String id);

    int insert(RHImage record);

    int insertSelective(RHImage record);

    List<RHImage> selectByExample(RHImageExample example);

    RHImage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RHImage record, @Param("example") RHImageExample example);

    int updateByExample(@Param("record") RHImage record, @Param("example") RHImageExample example);

    int updateByPrimaryKeySelective(RHImage record);

    int updateByPrimaryKey(RHImage record);
}