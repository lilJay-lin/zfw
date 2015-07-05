package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.Shop;
import com.mimi.zfw.mybatis.pojo.ShopExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopMapper extends IBaseDao<Shop, ShopExample, String> {
    int countByExample(ShopExample example);

    int deleteByExample(ShopExample example);

    int deleteByPrimaryKey(String id);

    int insert(Shop record);

    int insertSelective(Shop record);

    List<Shop> selectByExample(ShopExample example);

    Shop selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Shop record, @Param("example") ShopExample example);

    int updateByExample(@Param("record") Shop record, @Param("example") ShopExample example);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
}