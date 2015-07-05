package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.ShopImage;
import com.mimi.zfw.mybatis.pojo.ShopImageExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopImageMapper extends IBaseDao<ShopImage, ShopImageExample, String> {
    int countByExample(ShopImageExample example);

    int deleteByExample(ShopImageExample example);

    int deleteByPrimaryKey(String id);

    int insert(ShopImage record);

    int insertSelective(ShopImage record);

    List<ShopImage> selectByExample(ShopImageExample example);

    ShopImage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ShopImage record, @Param("example") ShopImageExample example);

    int updateByExample(@Param("record") ShopImage record, @Param("example") ShopImageExample example);

    int updateByPrimaryKeySelective(ShopImage record);

    int updateByPrimaryKey(ShopImage record);
}