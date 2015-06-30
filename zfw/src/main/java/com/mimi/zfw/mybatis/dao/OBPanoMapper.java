package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.OBPano;
import com.mimi.zfw.mybatis.pojo.OBPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OBPanoMapper extends IBaseDao<OBPano, OBPanoExample, String> {
    int countByExample(OBPanoExample example);

    int deleteByExample(OBPanoExample example);

    int deleteByPrimaryKey(String id);

    int insert(OBPano record);

    int insertSelective(OBPano record);

    List<OBPano> selectByExample(OBPanoExample example);

    OBPano selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") OBPano record, @Param("example") OBPanoExample example);

    int updateByExample(@Param("record") OBPano record, @Param("example") OBPanoExample example);

    int updateByPrimaryKeySelective(OBPano record);

    int updateByPrimaryKey(OBPano record);
}