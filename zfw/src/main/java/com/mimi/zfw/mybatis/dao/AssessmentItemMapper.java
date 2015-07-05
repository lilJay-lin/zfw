package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.AssessmentItem;
import com.mimi.zfw.mybatis.pojo.AssessmentItemExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssessmentItemMapper extends IBaseDao<AssessmentItem, AssessmentItemExample, String> {
    int countByExample(AssessmentItemExample example);

    int deleteByExample(AssessmentItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(AssessmentItem record);

    int insertSelective(AssessmentItem record);

    List<AssessmentItem> selectByExample(AssessmentItemExample example);

    AssessmentItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AssessmentItem record, @Param("example") AssessmentItemExample example);

    int updateByExample(@Param("record") AssessmentItem record, @Param("example") AssessmentItemExample example);

    int updateByPrimaryKeySelective(AssessmentItem record);

    int updateByPrimaryKey(AssessmentItem record);
}