package com.mimi.zfw.mybatis.dao;

import com.mimi.zfw.mybatis.pojo.RentalHousing;
import com.mimi.zfw.mybatis.pojo.RentalHousingExample;
import com.mimi.zfw.plugin.IBaseDao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RentalHousingMapper extends IBaseDao<RentalHousing, RentalHousingExample, String> {
    int countByExample(RentalHousingExample example);

    int deleteByExample(RentalHousingExample example);

    int deleteByPrimaryKey(String id);

    int insert(RentalHousing record);

    int insertSelective(RentalHousing record);

    List<RentalHousing> selectByExample(RentalHousingExample example);

    RentalHousing selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RentalHousing record, @Param("example") RentalHousingExample example);

    int updateByExample(@Param("record") RentalHousing record, @Param("example") RentalHousingExample example);

    int updateByPrimaryKeySelective(RentalHousing record);

    int updateByPrimaryKey(RentalHousing record);
}