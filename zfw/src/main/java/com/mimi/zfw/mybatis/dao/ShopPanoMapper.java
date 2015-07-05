package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.ShopPano;
import com.mimi.zfw.mybatis.pojo.ShopPanoExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopPanoMapper extends IBaseDao<ShopPano, ShopPanoExample, String> {
    int countByExample(ShopPanoExample example);

    int deleteByExample(ShopPanoExample example);

    int deleteByPrimaryKey(String id);

    int insert(ShopPano record);

    int insertSelective(ShopPano record);

    List<ShopPano> selectByExample(ShopPanoExample example);

    ShopPano selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShopPano record, @Param("example") ShopPanoExample example);

    int updateByExample(@Param("record") ShopPano record, @Param("example") ShopPanoExample example);

    int updateByPrimaryKeySelective(ShopPano record);

    int updateByPrimaryKey(ShopPano record);
}