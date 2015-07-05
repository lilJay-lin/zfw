package com.mimi.zfw.plugin;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IBaseDao<M extends IBaseModel<PK>, E extends BaseExample, PK extends Serializable> {

    int countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(PK id);

    int insert(M record);

    int insertSelective(M record);

    List<M> selectByExample(E example);

    M selectByPrimaryKey(PK id);

    int updateByExampleSelective(@Param("record") M record,
	    @Param("example") E example);

    int updateByExample(@Param("record") M record, @Param("example") E example);

    int updateByPrimaryKeySelective(M record);

    int updateByPrimaryKey(M record);
}
