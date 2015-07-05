package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.SHHImage;
import com.mimi.zfw.mybatis.pojo.SHHImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SHHImageMapper extends IBaseDao<SHHImage, SHHImageExample, String> {
    int countByExample(SHHImageExample example);

    int deleteByExample(SHHImageExample example);

    int deleteByPrimaryKey(String id);

    int insert(SHHImage record);

    int insertSelective(SHHImage record);

    List<SHHImage> selectByExample(SHHImageExample example);

    SHHImage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SHHImage record, @Param("example") SHHImageExample example);

    int updateByExample(@Param("record") SHHImage record, @Param("example") SHHImageExample example);

    int updateByPrimaryKeySelective(SHHImage record);

    int updateByPrimaryKey(SHHImage record);
}