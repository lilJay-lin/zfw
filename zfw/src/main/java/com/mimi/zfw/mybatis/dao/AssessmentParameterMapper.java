package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.AssessmentParameter;
import com.mimi.zfw.mybatis.pojo.AssessmentParameterExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssessmentParameterMapper extends IBaseDao<AssessmentParameter, AssessmentParameterExample, String> {
    int countByExample(AssessmentParameterExample example);

    int deleteByExample(AssessmentParameterExample example);

    int deleteByPrimaryKey(String id);

    int insert(AssessmentParameter record);

    int insertSelective(AssessmentParameter record);

    List<AssessmentParameter> selectByExample(AssessmentParameterExample example);

    AssessmentParameter selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AssessmentParameter record, @Param("example") AssessmentParameterExample example);

    int updateByExample(@Param("record") AssessmentParameter record, @Param("example") AssessmentParameterExample example);

    int updateByPrimaryKeySelective(AssessmentParameter record);

    int updateByPrimaryKey(AssessmentParameter record);
}