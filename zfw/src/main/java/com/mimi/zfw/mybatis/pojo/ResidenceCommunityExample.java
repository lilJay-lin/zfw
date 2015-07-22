package com.mimi.zfw.mybatis.pojo;

import com.mimi.zfw.plugin.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ResidenceCommunityExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public ResidenceCommunityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitSize(Integer limitSize) {
        this.limitSize=limitSize;
    }

    public Integer getLimitSize() {
        return limitSize;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andOnSaleDateIsNull() {
            addCriterion("on_sale_date is null");
            return (Criteria) this;
        }

        public Criteria andOnSaleDateIsNotNull() {
            addCriterion("on_sale_date is not null");
            return (Criteria) this;
        }

        public Criteria andOnSaleDateEqualTo(Date value) {
            addCriterionForJDBCDate("on_sale_date =", value, "onSaleDate");
            return (Criteria) this;
        }

        public Criteria andOnSaleDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("on_sale_date <>", value, "onSaleDate");
            return (Criteria) this;
        }

        public Criteria andOnSaleDateGreaterThan(Date value) {
            addCriterionForJDBCDate("on_sale_date >", value, "onSaleDate");
            return (Criteria) this;
        }

        public Criteria andOnSaleDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("on_sale_date >=", value, "onSaleDate");
            return (Criteria) this;
        }

        public Criteria andOnSaleDateLessThan(Date value) {
            addCriterionForJDBCDate("on_sale_date <", value, "onSaleDate");
            return (Criteria) this;
        }

        public Criteria andOnSaleDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("on_sale_date <=", value, "onSaleDate");
            return (Criteria) this;
        }

        public Criteria andOnSaleDateIn(List<Date> values) {
            addCriterionForJDBCDate("on_sale_date in", values, "onSaleDate");
            return (Criteria) this;
        }

        public Criteria andOnSaleDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("on_sale_date not in", values, "onSaleDate");
            return (Criteria) this;
        }

        public Criteria andOnSaleDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("on_sale_date between", value1, value2, "onSaleDate");
            return (Criteria) this;
        }

        public Criteria andOnSaleDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("on_sale_date not between", value1, value2, "onSaleDate");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIsNull() {
            addCriterion("property_type is null");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIsNotNull() {
            addCriterion("property_type is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeEqualTo(String value) {
            addCriterion("property_type =", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotEqualTo(String value) {
            addCriterion("property_type <>", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeGreaterThan(String value) {
            addCriterion("property_type >", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("property_type >=", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeLessThan(String value) {
            addCriterion("property_type <", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeLessThanOrEqualTo(String value) {
            addCriterion("property_type <=", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeLike(String value) {
            addCriterion("property_type like", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotLike(String value) {
            addCriterion("property_type not like", value, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeIn(List<String> values) {
            addCriterion("property_type in", values, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotIn(List<String> values) {
            addCriterion("property_type not in", values, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeBetween(String value1, String value2) {
            addCriterion("property_type between", value1, value2, "propertyType");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeNotBetween(String value1, String value2) {
            addCriterion("property_type not between", value1, value2, "propertyType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeIsNull() {
            addCriterion("building_type is null");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeIsNotNull() {
            addCriterion("building_type is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeEqualTo(String value) {
            addCriterion("building_type =", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeNotEqualTo(String value) {
            addCriterion("building_type <>", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeGreaterThan(String value) {
            addCriterion("building_type >", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeGreaterThanOrEqualTo(String value) {
            addCriterion("building_type >=", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeLessThan(String value) {
            addCriterion("building_type <", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeLessThanOrEqualTo(String value) {
            addCriterion("building_type <=", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeLike(String value) {
            addCriterion("building_type like", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeNotLike(String value) {
            addCriterion("building_type not like", value, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeIn(List<String> values) {
            addCriterion("building_type in", values, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeNotIn(List<String> values) {
            addCriterion("building_type not in", values, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeBetween(String value1, String value2) {
            addCriterion("building_type between", value1, value2, "buildingType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeNotBetween(String value1, String value2) {
            addCriterion("building_type not between", value1, value2, "buildingType");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumIsNull() {
            addCriterion("household_num is null");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumIsNotNull() {
            addCriterion("household_num is not null");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumEqualTo(Integer value) {
            addCriterion("household_num =", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumNotEqualTo(Integer value) {
            addCriterion("household_num <>", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumGreaterThan(Integer value) {
            addCriterion("household_num >", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("household_num >=", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumLessThan(Integer value) {
            addCriterion("household_num <", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumLessThanOrEqualTo(Integer value) {
            addCriterion("household_num <=", value, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumIn(List<Integer> values) {
            addCriterion("household_num in", values, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumNotIn(List<Integer> values) {
            addCriterion("household_num not in", values, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumBetween(Integer value1, Integer value2) {
            addCriterion("household_num between", value1, value2, "householdNum");
            return (Criteria) this;
        }

        public Criteria andHouseholdNumNotBetween(Integer value1, Integer value2) {
            addCriterion("household_num not between", value1, value2, "householdNum");
            return (Criteria) this;
        }

        public Criteria andFloorAreaRatioIsNull() {
            addCriterion("floor_area_ratio is null");
            return (Criteria) this;
        }

        public Criteria andFloorAreaRatioIsNotNull() {
            addCriterion("floor_area_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andFloorAreaRatioEqualTo(Float value) {
            addCriterion("floor_area_ratio =", value, "floorAreaRatio");
            return (Criteria) this;
        }

        public Criteria andFloorAreaRatioNotEqualTo(Float value) {
            addCriterion("floor_area_ratio <>", value, "floorAreaRatio");
            return (Criteria) this;
        }

        public Criteria andFloorAreaRatioGreaterThan(Float value) {
            addCriterion("floor_area_ratio >", value, "floorAreaRatio");
            return (Criteria) this;
        }

        public Criteria andFloorAreaRatioGreaterThanOrEqualTo(Float value) {
            addCriterion("floor_area_ratio >=", value, "floorAreaRatio");
            return (Criteria) this;
        }

        public Criteria andFloorAreaRatioLessThan(Float value) {
            addCriterion("floor_area_ratio <", value, "floorAreaRatio");
            return (Criteria) this;
        }

        public Criteria andFloorAreaRatioLessThanOrEqualTo(Float value) {
            addCriterion("floor_area_ratio <=", value, "floorAreaRatio");
            return (Criteria) this;
        }

        public Criteria andFloorAreaRatioIn(List<Float> values) {
            addCriterion("floor_area_ratio in", values, "floorAreaRatio");
            return (Criteria) this;
        }

        public Criteria andFloorAreaRatioNotIn(List<Float> values) {
            addCriterion("floor_area_ratio not in", values, "floorAreaRatio");
            return (Criteria) this;
        }

        public Criteria andFloorAreaRatioBetween(Float value1, Float value2) {
            addCriterion("floor_area_ratio between", value1, value2, "floorAreaRatio");
            return (Criteria) this;
        }

        public Criteria andFloorAreaRatioNotBetween(Float value1, Float value2) {
            addCriterion("floor_area_ratio not between", value1, value2, "floorAreaRatio");
            return (Criteria) this;
        }

        public Criteria andGreenRateIsNull() {
            addCriterion("green_rate is null");
            return (Criteria) this;
        }

        public Criteria andGreenRateIsNotNull() {
            addCriterion("green_rate is not null");
            return (Criteria) this;
        }

        public Criteria andGreenRateEqualTo(Float value) {
            addCriterion("green_rate =", value, "greenRate");
            return (Criteria) this;
        }

        public Criteria andGreenRateNotEqualTo(Float value) {
            addCriterion("green_rate <>", value, "greenRate");
            return (Criteria) this;
        }

        public Criteria andGreenRateGreaterThan(Float value) {
            addCriterion("green_rate >", value, "greenRate");
            return (Criteria) this;
        }

        public Criteria andGreenRateGreaterThanOrEqualTo(Float value) {
            addCriterion("green_rate >=", value, "greenRate");
            return (Criteria) this;
        }

        public Criteria andGreenRateLessThan(Float value) {
            addCriterion("green_rate <", value, "greenRate");
            return (Criteria) this;
        }

        public Criteria andGreenRateLessThanOrEqualTo(Float value) {
            addCriterion("green_rate <=", value, "greenRate");
            return (Criteria) this;
        }

        public Criteria andGreenRateIn(List<Float> values) {
            addCriterion("green_rate in", values, "greenRate");
            return (Criteria) this;
        }

        public Criteria andGreenRateNotIn(List<Float> values) {
            addCriterion("green_rate not in", values, "greenRate");
            return (Criteria) this;
        }

        public Criteria andGreenRateBetween(Float value1, Float value2) {
            addCriterion("green_rate between", value1, value2, "greenRate");
            return (Criteria) this;
        }

        public Criteria andGreenRateNotBetween(Float value1, Float value2) {
            addCriterion("green_rate not between", value1, value2, "greenRate");
            return (Criteria) this;
        }

        public Criteria andParkingSpaceNumIsNull() {
            addCriterion("parking_space_num is null");
            return (Criteria) this;
        }

        public Criteria andParkingSpaceNumIsNotNull() {
            addCriterion("parking_space_num is not null");
            return (Criteria) this;
        }

        public Criteria andParkingSpaceNumEqualTo(Integer value) {
            addCriterion("parking_space_num =", value, "parkingSpaceNum");
            return (Criteria) this;
        }

        public Criteria andParkingSpaceNumNotEqualTo(Integer value) {
            addCriterion("parking_space_num <>", value, "parkingSpaceNum");
            return (Criteria) this;
        }

        public Criteria andParkingSpaceNumGreaterThan(Integer value) {
            addCriterion("parking_space_num >", value, "parkingSpaceNum");
            return (Criteria) this;
        }

        public Criteria andParkingSpaceNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("parking_space_num >=", value, "parkingSpaceNum");
            return (Criteria) this;
        }

        public Criteria andParkingSpaceNumLessThan(Integer value) {
            addCriterion("parking_space_num <", value, "parkingSpaceNum");
            return (Criteria) this;
        }

        public Criteria andParkingSpaceNumLessThanOrEqualTo(Integer value) {
            addCriterion("parking_space_num <=", value, "parkingSpaceNum");
            return (Criteria) this;
        }

        public Criteria andParkingSpaceNumIn(List<Integer> values) {
            addCriterion("parking_space_num in", values, "parkingSpaceNum");
            return (Criteria) this;
        }

        public Criteria andParkingSpaceNumNotIn(List<Integer> values) {
            addCriterion("parking_space_num not in", values, "parkingSpaceNum");
            return (Criteria) this;
        }

        public Criteria andParkingSpaceNumBetween(Integer value1, Integer value2) {
            addCriterion("parking_space_num between", value1, value2, "parkingSpaceNum");
            return (Criteria) this;
        }

        public Criteria andParkingSpaceNumNotBetween(Integer value1, Integer value2) {
            addCriterion("parking_space_num not between", value1, value2, "parkingSpaceNum");
            return (Criteria) this;
        }

        public Criteria andPropertyYearsIsNull() {
            addCriterion("property_years is null");
            return (Criteria) this;
        }

        public Criteria andPropertyYearsIsNotNull() {
            addCriterion("property_years is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyYearsEqualTo(Integer value) {
            addCriterion("property_years =", value, "propertyYears");
            return (Criteria) this;
        }

        public Criteria andPropertyYearsNotEqualTo(Integer value) {
            addCriterion("property_years <>", value, "propertyYears");
            return (Criteria) this;
        }

        public Criteria andPropertyYearsGreaterThan(Integer value) {
            addCriterion("property_years >", value, "propertyYears");
            return (Criteria) this;
        }

        public Criteria andPropertyYearsGreaterThanOrEqualTo(Integer value) {
            addCriterion("property_years >=", value, "propertyYears");
            return (Criteria) this;
        }

        public Criteria andPropertyYearsLessThan(Integer value) {
            addCriterion("property_years <", value, "propertyYears");
            return (Criteria) this;
        }

        public Criteria andPropertyYearsLessThanOrEqualTo(Integer value) {
            addCriterion("property_years <=", value, "propertyYears");
            return (Criteria) this;
        }

        public Criteria andPropertyYearsIn(List<Integer> values) {
            addCriterion("property_years in", values, "propertyYears");
            return (Criteria) this;
        }

        public Criteria andPropertyYearsNotIn(List<Integer> values) {
            addCriterion("property_years not in", values, "propertyYears");
            return (Criteria) this;
        }

        public Criteria andPropertyYearsBetween(Integer value1, Integer value2) {
            addCriterion("property_years between", value1, value2, "propertyYears");
            return (Criteria) this;
        }

        public Criteria andPropertyYearsNotBetween(Integer value1, Integer value2) {
            addCriterion("property_years not between", value1, value2, "propertyYears");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyIsNull() {
            addCriterion("property_company is null");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyIsNotNull() {
            addCriterion("property_company is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyEqualTo(String value) {
            addCriterion("property_company =", value, "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNotEqualTo(String value) {
            addCriterion("property_company <>", value, "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyGreaterThan(String value) {
            addCriterion("property_company >", value, "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("property_company >=", value, "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyLessThan(String value) {
            addCriterion("property_company <", value, "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyLessThanOrEqualTo(String value) {
            addCriterion("property_company <=", value, "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyLike(String value) {
            addCriterion("property_company like", value, "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNotLike(String value) {
            addCriterion("property_company not like", value, "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyIn(List<String> values) {
            addCriterion("property_company in", values, "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNotIn(List<String> values) {
            addCriterion("property_company not in", values, "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyBetween(String value1, String value2) {
            addCriterion("property_company between", value1, value2, "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyNotBetween(String value1, String value2) {
            addCriterion("property_company not between", value1, value2, "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeIsNull() {
            addCriterion("property_fee is null");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeIsNotNull() {
            addCriterion("property_fee is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeEqualTo(Float value) {
            addCriterion("property_fee =", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeNotEqualTo(Float value) {
            addCriterion("property_fee <>", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeGreaterThan(Float value) {
            addCriterion("property_fee >", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeGreaterThanOrEqualTo(Float value) {
            addCriterion("property_fee >=", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeLessThan(Float value) {
            addCriterion("property_fee <", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeLessThanOrEqualTo(Float value) {
            addCriterion("property_fee <=", value, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeIn(List<Float> values) {
            addCriterion("property_fee in", values, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeNotIn(List<Float> values) {
            addCriterion("property_fee not in", values, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeBetween(Float value1, Float value2) {
            addCriterion("property_fee between", value1, value2, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andPropertyFeeNotBetween(Float value1, Float value2) {
            addCriterion("property_fee not between", value1, value2, "propertyFee");
            return (Criteria) this;
        }

        public Criteria andIntroductionIsNull() {
            addCriterion("introduction is null");
            return (Criteria) this;
        }

        public Criteria andIntroductionIsNotNull() {
            addCriterion("introduction is not null");
            return (Criteria) this;
        }

        public Criteria andIntroductionEqualTo(String value) {
            addCriterion("introduction =", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotEqualTo(String value) {
            addCriterion("introduction <>", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionGreaterThan(String value) {
            addCriterion("introduction >", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("introduction >=", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLessThan(String value) {
            addCriterion("introduction <", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLessThanOrEqualTo(String value) {
            addCriterion("introduction <=", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLike(String value) {
            addCriterion("introduction like", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotLike(String value) {
            addCriterion("introduction not like", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionIn(List<String> values) {
            addCriterion("introduction in", values, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotIn(List<String> values) {
            addCriterion("introduction not in", values, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionBetween(String value1, String value2) {
            addCriterion("introduction between", value1, value2, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotBetween(String value1, String value2) {
            addCriterion("introduction not between", value1, value2, "introduction");
            return (Criteria) this;
        }

        public Criteria andSurroundingIsNull() {
            addCriterion("surrounding is null");
            return (Criteria) this;
        }

        public Criteria andSurroundingIsNotNull() {
            addCriterion("surrounding is not null");
            return (Criteria) this;
        }

        public Criteria andSurroundingEqualTo(String value) {
            addCriterion("surrounding =", value, "surrounding");
            return (Criteria) this;
        }

        public Criteria andSurroundingNotEqualTo(String value) {
            addCriterion("surrounding <>", value, "surrounding");
            return (Criteria) this;
        }

        public Criteria andSurroundingGreaterThan(String value) {
            addCriterion("surrounding >", value, "surrounding");
            return (Criteria) this;
        }

        public Criteria andSurroundingGreaterThanOrEqualTo(String value) {
            addCriterion("surrounding >=", value, "surrounding");
            return (Criteria) this;
        }

        public Criteria andSurroundingLessThan(String value) {
            addCriterion("surrounding <", value, "surrounding");
            return (Criteria) this;
        }

        public Criteria andSurroundingLessThanOrEqualTo(String value) {
            addCriterion("surrounding <=", value, "surrounding");
            return (Criteria) this;
        }

        public Criteria andSurroundingLike(String value) {
            addCriterion("surrounding like", value, "surrounding");
            return (Criteria) this;
        }

        public Criteria andSurroundingNotLike(String value) {
            addCriterion("surrounding not like", value, "surrounding");
            return (Criteria) this;
        }

        public Criteria andSurroundingIn(List<String> values) {
            addCriterion("surrounding in", values, "surrounding");
            return (Criteria) this;
        }

        public Criteria andSurroundingNotIn(List<String> values) {
            addCriterion("surrounding not in", values, "surrounding");
            return (Criteria) this;
        }

        public Criteria andSurroundingBetween(String value1, String value2) {
            addCriterion("surrounding between", value1, value2, "surrounding");
            return (Criteria) this;
        }

        public Criteria andSurroundingNotBetween(String value1, String value2) {
            addCriterion("surrounding not between", value1, value2, "surrounding");
            return (Criteria) this;
        }

        public Criteria andTrafficIsNull() {
            addCriterion("traffic is null");
            return (Criteria) this;
        }

        public Criteria andTrafficIsNotNull() {
            addCriterion("traffic is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficEqualTo(String value) {
            addCriterion("traffic =", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficNotEqualTo(String value) {
            addCriterion("traffic <>", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficGreaterThan(String value) {
            addCriterion("traffic >", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficGreaterThanOrEqualTo(String value) {
            addCriterion("traffic >=", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficLessThan(String value) {
            addCriterion("traffic <", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficLessThanOrEqualTo(String value) {
            addCriterion("traffic <=", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficLike(String value) {
            addCriterion("traffic like", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficNotLike(String value) {
            addCriterion("traffic not like", value, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficIn(List<String> values) {
            addCriterion("traffic in", values, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficNotIn(List<String> values) {
            addCriterion("traffic not in", values, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficBetween(String value1, String value2) {
            addCriterion("traffic between", value1, value2, "traffic");
            return (Criteria) this;
        }

        public Criteria andTrafficNotBetween(String value1, String value2) {
            addCriterion("traffic not between", value1, value2, "traffic");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNull() {
            addCriterion("longitude is null");
            return (Criteria) this;
        }

        public Criteria andLongitudeIsNotNull() {
            addCriterion("longitude is not null");
            return (Criteria) this;
        }

        public Criteria andLongitudeEqualTo(Float value) {
            addCriterion("longitude =", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotEqualTo(Float value) {
            addCriterion("longitude <>", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThan(Float value) {
            addCriterion("longitude >", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeGreaterThanOrEqualTo(Float value) {
            addCriterion("longitude >=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThan(Float value) {
            addCriterion("longitude <", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeLessThanOrEqualTo(Float value) {
            addCriterion("longitude <=", value, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeIn(List<Float> values) {
            addCriterion("longitude in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotIn(List<Float> values) {
            addCriterion("longitude not in", values, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeBetween(Float value1, Float value2) {
            addCriterion("longitude between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLongitudeNotBetween(Float value1, Float value2) {
            addCriterion("longitude not between", value1, value2, "longitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNull() {
            addCriterion("latitude is null");
            return (Criteria) this;
        }

        public Criteria andLatitudeIsNotNull() {
            addCriterion("latitude is not null");
            return (Criteria) this;
        }

        public Criteria andLatitudeEqualTo(Float value) {
            addCriterion("latitude =", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotEqualTo(Float value) {
            addCriterion("latitude <>", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThan(Float value) {
            addCriterion("latitude >", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeGreaterThanOrEqualTo(Float value) {
            addCriterion("latitude >=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThan(Float value) {
            addCriterion("latitude <", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeLessThanOrEqualTo(Float value) {
            addCriterion("latitude <=", value, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeIn(List<Float> values) {
            addCriterion("latitude in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotIn(List<Float> values) {
            addCriterion("latitude not in", values, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeBetween(Float value1, Float value2) {
            addCriterion("latitude between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andLatitudeNotBetween(Float value1, Float value2) {
            addCriterion("latitude not between", value1, value2, "latitude");
            return (Criteria) this;
        }

        public Criteria andShhAveragePriceIsNull() {
            addCriterion("shh_average_price is null");
            return (Criteria) this;
        }

        public Criteria andShhAveragePriceIsNotNull() {
            addCriterion("shh_average_price is not null");
            return (Criteria) this;
        }

        public Criteria andShhAveragePriceEqualTo(Integer value) {
            addCriterion("shh_average_price =", value, "shhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andShhAveragePriceNotEqualTo(Integer value) {
            addCriterion("shh_average_price <>", value, "shhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andShhAveragePriceGreaterThan(Integer value) {
            addCriterion("shh_average_price >", value, "shhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andShhAveragePriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("shh_average_price >=", value, "shhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andShhAveragePriceLessThan(Integer value) {
            addCriterion("shh_average_price <", value, "shhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andShhAveragePriceLessThanOrEqualTo(Integer value) {
            addCriterion("shh_average_price <=", value, "shhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andShhAveragePriceIn(List<Integer> values) {
            addCriterion("shh_average_price in", values, "shhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andShhAveragePriceNotIn(List<Integer> values) {
            addCriterion("shh_average_price not in", values, "shhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andShhAveragePriceBetween(Integer value1, Integer value2) {
            addCriterion("shh_average_price between", value1, value2, "shhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andShhAveragePriceNotBetween(Integer value1, Integer value2) {
            addCriterion("shh_average_price not between", value1, value2, "shhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andShhNumIsNull() {
            addCriterion("shh_num is null");
            return (Criteria) this;
        }

        public Criteria andShhNumIsNotNull() {
            addCriterion("shh_num is not null");
            return (Criteria) this;
        }

        public Criteria andShhNumEqualTo(Integer value) {
            addCriterion("shh_num =", value, "shhNum");
            return (Criteria) this;
        }

        public Criteria andShhNumNotEqualTo(Integer value) {
            addCriterion("shh_num <>", value, "shhNum");
            return (Criteria) this;
        }

        public Criteria andShhNumGreaterThan(Integer value) {
            addCriterion("shh_num >", value, "shhNum");
            return (Criteria) this;
        }

        public Criteria andShhNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("shh_num >=", value, "shhNum");
            return (Criteria) this;
        }

        public Criteria andShhNumLessThan(Integer value) {
            addCriterion("shh_num <", value, "shhNum");
            return (Criteria) this;
        }

        public Criteria andShhNumLessThanOrEqualTo(Integer value) {
            addCriterion("shh_num <=", value, "shhNum");
            return (Criteria) this;
        }

        public Criteria andShhNumIn(List<Integer> values) {
            addCriterion("shh_num in", values, "shhNum");
            return (Criteria) this;
        }

        public Criteria andShhNumNotIn(List<Integer> values) {
            addCriterion("shh_num not in", values, "shhNum");
            return (Criteria) this;
        }

        public Criteria andShhNumBetween(Integer value1, Integer value2) {
            addCriterion("shh_num between", value1, value2, "shhNum");
            return (Criteria) this;
        }

        public Criteria andShhNumNotBetween(Integer value1, Integer value2) {
            addCriterion("shh_num not between", value1, value2, "shhNum");
            return (Criteria) this;
        }

        public Criteria andShhOneRoomNumIsNull() {
            addCriterion("shh_one_room_num is null");
            return (Criteria) this;
        }

        public Criteria andShhOneRoomNumIsNotNull() {
            addCriterion("shh_one_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andShhOneRoomNumEqualTo(Integer value) {
            addCriterion("shh_one_room_num =", value, "shhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOneRoomNumNotEqualTo(Integer value) {
            addCriterion("shh_one_room_num <>", value, "shhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOneRoomNumGreaterThan(Integer value) {
            addCriterion("shh_one_room_num >", value, "shhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOneRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("shh_one_room_num >=", value, "shhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOneRoomNumLessThan(Integer value) {
            addCriterion("shh_one_room_num <", value, "shhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOneRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("shh_one_room_num <=", value, "shhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOneRoomNumIn(List<Integer> values) {
            addCriterion("shh_one_room_num in", values, "shhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOneRoomNumNotIn(List<Integer> values) {
            addCriterion("shh_one_room_num not in", values, "shhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOneRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("shh_one_room_num between", value1, value2, "shhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOneRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("shh_one_room_num not between", value1, value2, "shhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhTwoRoomNumIsNull() {
            addCriterion("shh_two_room_num is null");
            return (Criteria) this;
        }

        public Criteria andShhTwoRoomNumIsNotNull() {
            addCriterion("shh_two_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andShhTwoRoomNumEqualTo(Integer value) {
            addCriterion("shh_two_room_num =", value, "shhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhTwoRoomNumNotEqualTo(Integer value) {
            addCriterion("shh_two_room_num <>", value, "shhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhTwoRoomNumGreaterThan(Integer value) {
            addCriterion("shh_two_room_num >", value, "shhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhTwoRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("shh_two_room_num >=", value, "shhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhTwoRoomNumLessThan(Integer value) {
            addCriterion("shh_two_room_num <", value, "shhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhTwoRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("shh_two_room_num <=", value, "shhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhTwoRoomNumIn(List<Integer> values) {
            addCriterion("shh_two_room_num in", values, "shhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhTwoRoomNumNotIn(List<Integer> values) {
            addCriterion("shh_two_room_num not in", values, "shhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhTwoRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("shh_two_room_num between", value1, value2, "shhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhTwoRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("shh_two_room_num not between", value1, value2, "shhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhThreeRoomNumIsNull() {
            addCriterion("shh_three_room_num is null");
            return (Criteria) this;
        }

        public Criteria andShhThreeRoomNumIsNotNull() {
            addCriterion("shh_three_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andShhThreeRoomNumEqualTo(Integer value) {
            addCriterion("shh_three_room_num =", value, "shhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhThreeRoomNumNotEqualTo(Integer value) {
            addCriterion("shh_three_room_num <>", value, "shhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhThreeRoomNumGreaterThan(Integer value) {
            addCriterion("shh_three_room_num >", value, "shhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhThreeRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("shh_three_room_num >=", value, "shhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhThreeRoomNumLessThan(Integer value) {
            addCriterion("shh_three_room_num <", value, "shhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhThreeRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("shh_three_room_num <=", value, "shhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhThreeRoomNumIn(List<Integer> values) {
            addCriterion("shh_three_room_num in", values, "shhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhThreeRoomNumNotIn(List<Integer> values) {
            addCriterion("shh_three_room_num not in", values, "shhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhThreeRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("shh_three_room_num between", value1, value2, "shhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhThreeRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("shh_three_room_num not between", value1, value2, "shhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFourRoomNumIsNull() {
            addCriterion("shh_four_room_num is null");
            return (Criteria) this;
        }

        public Criteria andShhFourRoomNumIsNotNull() {
            addCriterion("shh_four_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andShhFourRoomNumEqualTo(Integer value) {
            addCriterion("shh_four_room_num =", value, "shhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFourRoomNumNotEqualTo(Integer value) {
            addCriterion("shh_four_room_num <>", value, "shhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFourRoomNumGreaterThan(Integer value) {
            addCriterion("shh_four_room_num >", value, "shhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFourRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("shh_four_room_num >=", value, "shhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFourRoomNumLessThan(Integer value) {
            addCriterion("shh_four_room_num <", value, "shhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFourRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("shh_four_room_num <=", value, "shhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFourRoomNumIn(List<Integer> values) {
            addCriterion("shh_four_room_num in", values, "shhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFourRoomNumNotIn(List<Integer> values) {
            addCriterion("shh_four_room_num not in", values, "shhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFourRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("shh_four_room_num between", value1, value2, "shhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFourRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("shh_four_room_num not between", value1, value2, "shhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFiveRoomNumIsNull() {
            addCriterion("shh_five_room_num is null");
            return (Criteria) this;
        }

        public Criteria andShhFiveRoomNumIsNotNull() {
            addCriterion("shh_five_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andShhFiveRoomNumEqualTo(Integer value) {
            addCriterion("shh_five_room_num =", value, "shhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFiveRoomNumNotEqualTo(Integer value) {
            addCriterion("shh_five_room_num <>", value, "shhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFiveRoomNumGreaterThan(Integer value) {
            addCriterion("shh_five_room_num >", value, "shhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFiveRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("shh_five_room_num >=", value, "shhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFiveRoomNumLessThan(Integer value) {
            addCriterion("shh_five_room_num <", value, "shhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFiveRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("shh_five_room_num <=", value, "shhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFiveRoomNumIn(List<Integer> values) {
            addCriterion("shh_five_room_num in", values, "shhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFiveRoomNumNotIn(List<Integer> values) {
            addCriterion("shh_five_room_num not in", values, "shhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFiveRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("shh_five_room_num between", value1, value2, "shhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhFiveRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("shh_five_room_num not between", value1, value2, "shhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOverFiveRoomNumIsNull() {
            addCriterion("shh_over_five_room_num is null");
            return (Criteria) this;
        }

        public Criteria andShhOverFiveRoomNumIsNotNull() {
            addCriterion("shh_over_five_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andShhOverFiveRoomNumEqualTo(Integer value) {
            addCriterion("shh_over_five_room_num =", value, "shhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOverFiveRoomNumNotEqualTo(Integer value) {
            addCriterion("shh_over_five_room_num <>", value, "shhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOverFiveRoomNumGreaterThan(Integer value) {
            addCriterion("shh_over_five_room_num >", value, "shhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOverFiveRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("shh_over_five_room_num >=", value, "shhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOverFiveRoomNumLessThan(Integer value) {
            addCriterion("shh_over_five_room_num <", value, "shhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOverFiveRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("shh_over_five_room_num <=", value, "shhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOverFiveRoomNumIn(List<Integer> values) {
            addCriterion("shh_over_five_room_num in", values, "shhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOverFiveRoomNumNotIn(List<Integer> values) {
            addCriterion("shh_over_five_room_num not in", values, "shhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOverFiveRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("shh_over_five_room_num between", value1, value2, "shhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhOverFiveRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("shh_over_five_room_num not between", value1, value2, "shhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andShhMinRoomGrossFloorAreaIsNull() {
            addCriterion("shh_min_room_gross_floor_area is null");
            return (Criteria) this;
        }

        public Criteria andShhMinRoomGrossFloorAreaIsNotNull() {
            addCriterion("shh_min_room_gross_floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andShhMinRoomGrossFloorAreaEqualTo(Integer value) {
            addCriterion("shh_min_room_gross_floor_area =", value, "shhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMinRoomGrossFloorAreaNotEqualTo(Integer value) {
            addCriterion("shh_min_room_gross_floor_area <>", value, "shhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMinRoomGrossFloorAreaGreaterThan(Integer value) {
            addCriterion("shh_min_room_gross_floor_area >", value, "shhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMinRoomGrossFloorAreaGreaterThanOrEqualTo(Integer value) {
            addCriterion("shh_min_room_gross_floor_area >=", value, "shhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMinRoomGrossFloorAreaLessThan(Integer value) {
            addCriterion("shh_min_room_gross_floor_area <", value, "shhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMinRoomGrossFloorAreaLessThanOrEqualTo(Integer value) {
            addCriterion("shh_min_room_gross_floor_area <=", value, "shhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMinRoomGrossFloorAreaIn(List<Integer> values) {
            addCriterion("shh_min_room_gross_floor_area in", values, "shhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMinRoomGrossFloorAreaNotIn(List<Integer> values) {
            addCriterion("shh_min_room_gross_floor_area not in", values, "shhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMinRoomGrossFloorAreaBetween(Integer value1, Integer value2) {
            addCriterion("shh_min_room_gross_floor_area between", value1, value2, "shhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMinRoomGrossFloorAreaNotBetween(Integer value1, Integer value2) {
            addCriterion("shh_min_room_gross_floor_area not between", value1, value2, "shhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMaxRoomGrossFloorAreaIsNull() {
            addCriterion("shh_max_room_gross_floor_area is null");
            return (Criteria) this;
        }

        public Criteria andShhMaxRoomGrossFloorAreaIsNotNull() {
            addCriterion("shh_max_room_gross_floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andShhMaxRoomGrossFloorAreaEqualTo(Integer value) {
            addCriterion("shh_max_room_gross_floor_area =", value, "shhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMaxRoomGrossFloorAreaNotEqualTo(Integer value) {
            addCriterion("shh_max_room_gross_floor_area <>", value, "shhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMaxRoomGrossFloorAreaGreaterThan(Integer value) {
            addCriterion("shh_max_room_gross_floor_area >", value, "shhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMaxRoomGrossFloorAreaGreaterThanOrEqualTo(Integer value) {
            addCriterion("shh_max_room_gross_floor_area >=", value, "shhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMaxRoomGrossFloorAreaLessThan(Integer value) {
            addCriterion("shh_max_room_gross_floor_area <", value, "shhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMaxRoomGrossFloorAreaLessThanOrEqualTo(Integer value) {
            addCriterion("shh_max_room_gross_floor_area <=", value, "shhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMaxRoomGrossFloorAreaIn(List<Integer> values) {
            addCriterion("shh_max_room_gross_floor_area in", values, "shhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMaxRoomGrossFloorAreaNotIn(List<Integer> values) {
            addCriterion("shh_max_room_gross_floor_area not in", values, "shhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMaxRoomGrossFloorAreaBetween(Integer value1, Integer value2) {
            addCriterion("shh_max_room_gross_floor_area between", value1, value2, "shhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMaxRoomGrossFloorAreaNotBetween(Integer value1, Integer value2) {
            addCriterion("shh_max_room_gross_floor_area not between", value1, value2, "shhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andShhMinTotalPriceIsNull() {
            addCriterion("shh_min_total_price is null");
            return (Criteria) this;
        }

        public Criteria andShhMinTotalPriceIsNotNull() {
            addCriterion("shh_min_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andShhMinTotalPriceEqualTo(Integer value) {
            addCriterion("shh_min_total_price =", value, "shhMinTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMinTotalPriceNotEqualTo(Integer value) {
            addCriterion("shh_min_total_price <>", value, "shhMinTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMinTotalPriceGreaterThan(Integer value) {
            addCriterion("shh_min_total_price >", value, "shhMinTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMinTotalPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("shh_min_total_price >=", value, "shhMinTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMinTotalPriceLessThan(Integer value) {
            addCriterion("shh_min_total_price <", value, "shhMinTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMinTotalPriceLessThanOrEqualTo(Integer value) {
            addCriterion("shh_min_total_price <=", value, "shhMinTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMinTotalPriceIn(List<Integer> values) {
            addCriterion("shh_min_total_price in", values, "shhMinTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMinTotalPriceNotIn(List<Integer> values) {
            addCriterion("shh_min_total_price not in", values, "shhMinTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMinTotalPriceBetween(Integer value1, Integer value2) {
            addCriterion("shh_min_total_price between", value1, value2, "shhMinTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMinTotalPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("shh_min_total_price not between", value1, value2, "shhMinTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMaxTotalPriceIsNull() {
            addCriterion("shh_max_total_price is null");
            return (Criteria) this;
        }

        public Criteria andShhMaxTotalPriceIsNotNull() {
            addCriterion("shh_max_total_price is not null");
            return (Criteria) this;
        }

        public Criteria andShhMaxTotalPriceEqualTo(Integer value) {
            addCriterion("shh_max_total_price =", value, "shhMaxTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMaxTotalPriceNotEqualTo(Integer value) {
            addCriterion("shh_max_total_price <>", value, "shhMaxTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMaxTotalPriceGreaterThan(Integer value) {
            addCriterion("shh_max_total_price >", value, "shhMaxTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMaxTotalPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("shh_max_total_price >=", value, "shhMaxTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMaxTotalPriceLessThan(Integer value) {
            addCriterion("shh_max_total_price <", value, "shhMaxTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMaxTotalPriceLessThanOrEqualTo(Integer value) {
            addCriterion("shh_max_total_price <=", value, "shhMaxTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMaxTotalPriceIn(List<Integer> values) {
            addCriterion("shh_max_total_price in", values, "shhMaxTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMaxTotalPriceNotIn(List<Integer> values) {
            addCriterion("shh_max_total_price not in", values, "shhMaxTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMaxTotalPriceBetween(Integer value1, Integer value2) {
            addCriterion("shh_max_total_price between", value1, value2, "shhMaxTotalPrice");
            return (Criteria) this;
        }

        public Criteria andShhMaxTotalPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("shh_max_total_price not between", value1, value2, "shhMaxTotalPrice");
            return (Criteria) this;
        }

        public Criteria andRhAveragePriceIsNull() {
            addCriterion("rh_average_price is null");
            return (Criteria) this;
        }

        public Criteria andRhAveragePriceIsNotNull() {
            addCriterion("rh_average_price is not null");
            return (Criteria) this;
        }

        public Criteria andRhAveragePriceEqualTo(Integer value) {
            addCriterion("rh_average_price =", value, "rhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andRhAveragePriceNotEqualTo(Integer value) {
            addCriterion("rh_average_price <>", value, "rhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andRhAveragePriceGreaterThan(Integer value) {
            addCriterion("rh_average_price >", value, "rhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andRhAveragePriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_average_price >=", value, "rhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andRhAveragePriceLessThan(Integer value) {
            addCriterion("rh_average_price <", value, "rhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andRhAveragePriceLessThanOrEqualTo(Integer value) {
            addCriterion("rh_average_price <=", value, "rhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andRhAveragePriceIn(List<Integer> values) {
            addCriterion("rh_average_price in", values, "rhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andRhAveragePriceNotIn(List<Integer> values) {
            addCriterion("rh_average_price not in", values, "rhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andRhAveragePriceBetween(Integer value1, Integer value2) {
            addCriterion("rh_average_price between", value1, value2, "rhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andRhAveragePriceNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_average_price not between", value1, value2, "rhAveragePrice");
            return (Criteria) this;
        }

        public Criteria andRhNumIsNull() {
            addCriterion("rh_num is null");
            return (Criteria) this;
        }

        public Criteria andRhNumIsNotNull() {
            addCriterion("rh_num is not null");
            return (Criteria) this;
        }

        public Criteria andRhNumEqualTo(Integer value) {
            addCriterion("rh_num =", value, "rhNum");
            return (Criteria) this;
        }

        public Criteria andRhNumNotEqualTo(Integer value) {
            addCriterion("rh_num <>", value, "rhNum");
            return (Criteria) this;
        }

        public Criteria andRhNumGreaterThan(Integer value) {
            addCriterion("rh_num >", value, "rhNum");
            return (Criteria) this;
        }

        public Criteria andRhNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_num >=", value, "rhNum");
            return (Criteria) this;
        }

        public Criteria andRhNumLessThan(Integer value) {
            addCriterion("rh_num <", value, "rhNum");
            return (Criteria) this;
        }

        public Criteria andRhNumLessThanOrEqualTo(Integer value) {
            addCriterion("rh_num <=", value, "rhNum");
            return (Criteria) this;
        }

        public Criteria andRhNumIn(List<Integer> values) {
            addCriterion("rh_num in", values, "rhNum");
            return (Criteria) this;
        }

        public Criteria andRhNumNotIn(List<Integer> values) {
            addCriterion("rh_num not in", values, "rhNum");
            return (Criteria) this;
        }

        public Criteria andRhNumBetween(Integer value1, Integer value2) {
            addCriterion("rh_num between", value1, value2, "rhNum");
            return (Criteria) this;
        }

        public Criteria andRhNumNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_num not between", value1, value2, "rhNum");
            return (Criteria) this;
        }

        public Criteria andRhOneRoomNumIsNull() {
            addCriterion("rh_one_room_num is null");
            return (Criteria) this;
        }

        public Criteria andRhOneRoomNumIsNotNull() {
            addCriterion("rh_one_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andRhOneRoomNumEqualTo(Integer value) {
            addCriterion("rh_one_room_num =", value, "rhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOneRoomNumNotEqualTo(Integer value) {
            addCriterion("rh_one_room_num <>", value, "rhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOneRoomNumGreaterThan(Integer value) {
            addCriterion("rh_one_room_num >", value, "rhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOneRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_one_room_num >=", value, "rhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOneRoomNumLessThan(Integer value) {
            addCriterion("rh_one_room_num <", value, "rhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOneRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("rh_one_room_num <=", value, "rhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOneRoomNumIn(List<Integer> values) {
            addCriterion("rh_one_room_num in", values, "rhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOneRoomNumNotIn(List<Integer> values) {
            addCriterion("rh_one_room_num not in", values, "rhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOneRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("rh_one_room_num between", value1, value2, "rhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOneRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_one_room_num not between", value1, value2, "rhOneRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhTwoRoomNumIsNull() {
            addCriterion("rh_two_room_num is null");
            return (Criteria) this;
        }

        public Criteria andRhTwoRoomNumIsNotNull() {
            addCriterion("rh_two_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andRhTwoRoomNumEqualTo(Integer value) {
            addCriterion("rh_two_room_num =", value, "rhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhTwoRoomNumNotEqualTo(Integer value) {
            addCriterion("rh_two_room_num <>", value, "rhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhTwoRoomNumGreaterThan(Integer value) {
            addCriterion("rh_two_room_num >", value, "rhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhTwoRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_two_room_num >=", value, "rhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhTwoRoomNumLessThan(Integer value) {
            addCriterion("rh_two_room_num <", value, "rhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhTwoRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("rh_two_room_num <=", value, "rhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhTwoRoomNumIn(List<Integer> values) {
            addCriterion("rh_two_room_num in", values, "rhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhTwoRoomNumNotIn(List<Integer> values) {
            addCriterion("rh_two_room_num not in", values, "rhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhTwoRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("rh_two_room_num between", value1, value2, "rhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhTwoRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_two_room_num not between", value1, value2, "rhTwoRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhThreeRoomNumIsNull() {
            addCriterion("rh_three_room_num is null");
            return (Criteria) this;
        }

        public Criteria andRhThreeRoomNumIsNotNull() {
            addCriterion("rh_three_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andRhThreeRoomNumEqualTo(Integer value) {
            addCriterion("rh_three_room_num =", value, "rhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhThreeRoomNumNotEqualTo(Integer value) {
            addCriterion("rh_three_room_num <>", value, "rhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhThreeRoomNumGreaterThan(Integer value) {
            addCriterion("rh_three_room_num >", value, "rhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhThreeRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_three_room_num >=", value, "rhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhThreeRoomNumLessThan(Integer value) {
            addCriterion("rh_three_room_num <", value, "rhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhThreeRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("rh_three_room_num <=", value, "rhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhThreeRoomNumIn(List<Integer> values) {
            addCriterion("rh_three_room_num in", values, "rhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhThreeRoomNumNotIn(List<Integer> values) {
            addCriterion("rh_three_room_num not in", values, "rhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhThreeRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("rh_three_room_num between", value1, value2, "rhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhThreeRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_three_room_num not between", value1, value2, "rhThreeRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFourRoomNumIsNull() {
            addCriterion("rh_four_room_num is null");
            return (Criteria) this;
        }

        public Criteria andRhFourRoomNumIsNotNull() {
            addCriterion("rh_four_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andRhFourRoomNumEqualTo(Integer value) {
            addCriterion("rh_four_room_num =", value, "rhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFourRoomNumNotEqualTo(Integer value) {
            addCriterion("rh_four_room_num <>", value, "rhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFourRoomNumGreaterThan(Integer value) {
            addCriterion("rh_four_room_num >", value, "rhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFourRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_four_room_num >=", value, "rhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFourRoomNumLessThan(Integer value) {
            addCriterion("rh_four_room_num <", value, "rhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFourRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("rh_four_room_num <=", value, "rhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFourRoomNumIn(List<Integer> values) {
            addCriterion("rh_four_room_num in", values, "rhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFourRoomNumNotIn(List<Integer> values) {
            addCriterion("rh_four_room_num not in", values, "rhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFourRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("rh_four_room_num between", value1, value2, "rhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFourRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_four_room_num not between", value1, value2, "rhFourRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFiveRoomNumIsNull() {
            addCriterion("rh_five_room_num is null");
            return (Criteria) this;
        }

        public Criteria andRhFiveRoomNumIsNotNull() {
            addCriterion("rh_five_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andRhFiveRoomNumEqualTo(Integer value) {
            addCriterion("rh_five_room_num =", value, "rhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFiveRoomNumNotEqualTo(Integer value) {
            addCriterion("rh_five_room_num <>", value, "rhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFiveRoomNumGreaterThan(Integer value) {
            addCriterion("rh_five_room_num >", value, "rhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFiveRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_five_room_num >=", value, "rhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFiveRoomNumLessThan(Integer value) {
            addCriterion("rh_five_room_num <", value, "rhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFiveRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("rh_five_room_num <=", value, "rhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFiveRoomNumIn(List<Integer> values) {
            addCriterion("rh_five_room_num in", values, "rhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFiveRoomNumNotIn(List<Integer> values) {
            addCriterion("rh_five_room_num not in", values, "rhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFiveRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("rh_five_room_num between", value1, value2, "rhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhFiveRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_five_room_num not between", value1, value2, "rhFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOverFiveRoomNumIsNull() {
            addCriterion("rh_over_five_room_num is null");
            return (Criteria) this;
        }

        public Criteria andRhOverFiveRoomNumIsNotNull() {
            addCriterion("rh_over_five_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andRhOverFiveRoomNumEqualTo(Integer value) {
            addCriterion("rh_over_five_room_num =", value, "rhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOverFiveRoomNumNotEqualTo(Integer value) {
            addCriterion("rh_over_five_room_num <>", value, "rhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOverFiveRoomNumGreaterThan(Integer value) {
            addCriterion("rh_over_five_room_num >", value, "rhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOverFiveRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_over_five_room_num >=", value, "rhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOverFiveRoomNumLessThan(Integer value) {
            addCriterion("rh_over_five_room_num <", value, "rhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOverFiveRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("rh_over_five_room_num <=", value, "rhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOverFiveRoomNumIn(List<Integer> values) {
            addCriterion("rh_over_five_room_num in", values, "rhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOverFiveRoomNumNotIn(List<Integer> values) {
            addCriterion("rh_over_five_room_num not in", values, "rhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOverFiveRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("rh_over_five_room_num between", value1, value2, "rhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhOverFiveRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_over_five_room_num not between", value1, value2, "rhOverFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andRhMinRoomGrossFloorAreaIsNull() {
            addCriterion("rh_min_room_gross_floor_area is null");
            return (Criteria) this;
        }

        public Criteria andRhMinRoomGrossFloorAreaIsNotNull() {
            addCriterion("rh_min_room_gross_floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andRhMinRoomGrossFloorAreaEqualTo(Integer value) {
            addCriterion("rh_min_room_gross_floor_area =", value, "rhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMinRoomGrossFloorAreaNotEqualTo(Integer value) {
            addCriterion("rh_min_room_gross_floor_area <>", value, "rhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMinRoomGrossFloorAreaGreaterThan(Integer value) {
            addCriterion("rh_min_room_gross_floor_area >", value, "rhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMinRoomGrossFloorAreaGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_min_room_gross_floor_area >=", value, "rhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMinRoomGrossFloorAreaLessThan(Integer value) {
            addCriterion("rh_min_room_gross_floor_area <", value, "rhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMinRoomGrossFloorAreaLessThanOrEqualTo(Integer value) {
            addCriterion("rh_min_room_gross_floor_area <=", value, "rhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMinRoomGrossFloorAreaIn(List<Integer> values) {
            addCriterion("rh_min_room_gross_floor_area in", values, "rhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMinRoomGrossFloorAreaNotIn(List<Integer> values) {
            addCriterion("rh_min_room_gross_floor_area not in", values, "rhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMinRoomGrossFloorAreaBetween(Integer value1, Integer value2) {
            addCriterion("rh_min_room_gross_floor_area between", value1, value2, "rhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMinRoomGrossFloorAreaNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_min_room_gross_floor_area not between", value1, value2, "rhMinRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMaxRoomGrossFloorAreaIsNull() {
            addCriterion("rh_max_room_gross_floor_area is null");
            return (Criteria) this;
        }

        public Criteria andRhMaxRoomGrossFloorAreaIsNotNull() {
            addCriterion("rh_max_room_gross_floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andRhMaxRoomGrossFloorAreaEqualTo(Integer value) {
            addCriterion("rh_max_room_gross_floor_area =", value, "rhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMaxRoomGrossFloorAreaNotEqualTo(Integer value) {
            addCriterion("rh_max_room_gross_floor_area <>", value, "rhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMaxRoomGrossFloorAreaGreaterThan(Integer value) {
            addCriterion("rh_max_room_gross_floor_area >", value, "rhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMaxRoomGrossFloorAreaGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_max_room_gross_floor_area >=", value, "rhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMaxRoomGrossFloorAreaLessThan(Integer value) {
            addCriterion("rh_max_room_gross_floor_area <", value, "rhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMaxRoomGrossFloorAreaLessThanOrEqualTo(Integer value) {
            addCriterion("rh_max_room_gross_floor_area <=", value, "rhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMaxRoomGrossFloorAreaIn(List<Integer> values) {
            addCriterion("rh_max_room_gross_floor_area in", values, "rhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMaxRoomGrossFloorAreaNotIn(List<Integer> values) {
            addCriterion("rh_max_room_gross_floor_area not in", values, "rhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMaxRoomGrossFloorAreaBetween(Integer value1, Integer value2) {
            addCriterion("rh_max_room_gross_floor_area between", value1, value2, "rhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMaxRoomGrossFloorAreaNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_max_room_gross_floor_area not between", value1, value2, "rhMaxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRhMinRentalIsNull() {
            addCriterion("rh_min_rental is null");
            return (Criteria) this;
        }

        public Criteria andRhMinRentalIsNotNull() {
            addCriterion("rh_min_rental is not null");
            return (Criteria) this;
        }

        public Criteria andRhMinRentalEqualTo(Integer value) {
            addCriterion("rh_min_rental =", value, "rhMinRental");
            return (Criteria) this;
        }

        public Criteria andRhMinRentalNotEqualTo(Integer value) {
            addCriterion("rh_min_rental <>", value, "rhMinRental");
            return (Criteria) this;
        }

        public Criteria andRhMinRentalGreaterThan(Integer value) {
            addCriterion("rh_min_rental >", value, "rhMinRental");
            return (Criteria) this;
        }

        public Criteria andRhMinRentalGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_min_rental >=", value, "rhMinRental");
            return (Criteria) this;
        }

        public Criteria andRhMinRentalLessThan(Integer value) {
            addCriterion("rh_min_rental <", value, "rhMinRental");
            return (Criteria) this;
        }

        public Criteria andRhMinRentalLessThanOrEqualTo(Integer value) {
            addCriterion("rh_min_rental <=", value, "rhMinRental");
            return (Criteria) this;
        }

        public Criteria andRhMinRentalIn(List<Integer> values) {
            addCriterion("rh_min_rental in", values, "rhMinRental");
            return (Criteria) this;
        }

        public Criteria andRhMinRentalNotIn(List<Integer> values) {
            addCriterion("rh_min_rental not in", values, "rhMinRental");
            return (Criteria) this;
        }

        public Criteria andRhMinRentalBetween(Integer value1, Integer value2) {
            addCriterion("rh_min_rental between", value1, value2, "rhMinRental");
            return (Criteria) this;
        }

        public Criteria andRhMinRentalNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_min_rental not between", value1, value2, "rhMinRental");
            return (Criteria) this;
        }

        public Criteria andRhMaxRentalIsNull() {
            addCriterion("rh_max_rental is null");
            return (Criteria) this;
        }

        public Criteria andRhMaxRentalIsNotNull() {
            addCriterion("rh_max_rental is not null");
            return (Criteria) this;
        }

        public Criteria andRhMaxRentalEqualTo(Integer value) {
            addCriterion("rh_max_rental =", value, "rhMaxRental");
            return (Criteria) this;
        }

        public Criteria andRhMaxRentalNotEqualTo(Integer value) {
            addCriterion("rh_max_rental <>", value, "rhMaxRental");
            return (Criteria) this;
        }

        public Criteria andRhMaxRentalGreaterThan(Integer value) {
            addCriterion("rh_max_rental >", value, "rhMaxRental");
            return (Criteria) this;
        }

        public Criteria andRhMaxRentalGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_max_rental >=", value, "rhMaxRental");
            return (Criteria) this;
        }

        public Criteria andRhMaxRentalLessThan(Integer value) {
            addCriterion("rh_max_rental <", value, "rhMaxRental");
            return (Criteria) this;
        }

        public Criteria andRhMaxRentalLessThanOrEqualTo(Integer value) {
            addCriterion("rh_max_rental <=", value, "rhMaxRental");
            return (Criteria) this;
        }

        public Criteria andRhMaxRentalIn(List<Integer> values) {
            addCriterion("rh_max_rental in", values, "rhMaxRental");
            return (Criteria) this;
        }

        public Criteria andRhMaxRentalNotIn(List<Integer> values) {
            addCriterion("rh_max_rental not in", values, "rhMaxRental");
            return (Criteria) this;
        }

        public Criteria andRhMaxRentalBetween(Integer value1, Integer value2) {
            addCriterion("rh_max_rental between", value1, value2, "rhMaxRental");
            return (Criteria) this;
        }

        public Criteria andRhMaxRentalNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_max_rental not between", value1, value2, "rhMaxRental");
            return (Criteria) this;
        }

        public Criteria andRhEntireRentNumIsNull() {
            addCriterion("rh_entire_rent_num is null");
            return (Criteria) this;
        }

        public Criteria andRhEntireRentNumIsNotNull() {
            addCriterion("rh_entire_rent_num is not null");
            return (Criteria) this;
        }

        public Criteria andRhEntireRentNumEqualTo(Integer value) {
            addCriterion("rh_entire_rent_num =", value, "rhEntireRentNum");
            return (Criteria) this;
        }

        public Criteria andRhEntireRentNumNotEqualTo(Integer value) {
            addCriterion("rh_entire_rent_num <>", value, "rhEntireRentNum");
            return (Criteria) this;
        }

        public Criteria andRhEntireRentNumGreaterThan(Integer value) {
            addCriterion("rh_entire_rent_num >", value, "rhEntireRentNum");
            return (Criteria) this;
        }

        public Criteria andRhEntireRentNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_entire_rent_num >=", value, "rhEntireRentNum");
            return (Criteria) this;
        }

        public Criteria andRhEntireRentNumLessThan(Integer value) {
            addCriterion("rh_entire_rent_num <", value, "rhEntireRentNum");
            return (Criteria) this;
        }

        public Criteria andRhEntireRentNumLessThanOrEqualTo(Integer value) {
            addCriterion("rh_entire_rent_num <=", value, "rhEntireRentNum");
            return (Criteria) this;
        }

        public Criteria andRhEntireRentNumIn(List<Integer> values) {
            addCriterion("rh_entire_rent_num in", values, "rhEntireRentNum");
            return (Criteria) this;
        }

        public Criteria andRhEntireRentNumNotIn(List<Integer> values) {
            addCriterion("rh_entire_rent_num not in", values, "rhEntireRentNum");
            return (Criteria) this;
        }

        public Criteria andRhEntireRentNumBetween(Integer value1, Integer value2) {
            addCriterion("rh_entire_rent_num between", value1, value2, "rhEntireRentNum");
            return (Criteria) this;
        }

        public Criteria andRhEntireRentNumNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_entire_rent_num not between", value1, value2, "rhEntireRentNum");
            return (Criteria) this;
        }

        public Criteria andRhFlatShareNumIsNull() {
            addCriterion("rh_flat_share_num is null");
            return (Criteria) this;
        }

        public Criteria andRhFlatShareNumIsNotNull() {
            addCriterion("rh_flat_share_num is not null");
            return (Criteria) this;
        }

        public Criteria andRhFlatShareNumEqualTo(Integer value) {
            addCriterion("rh_flat_share_num =", value, "rhFlatShareNum");
            return (Criteria) this;
        }

        public Criteria andRhFlatShareNumNotEqualTo(Integer value) {
            addCriterion("rh_flat_share_num <>", value, "rhFlatShareNum");
            return (Criteria) this;
        }

        public Criteria andRhFlatShareNumGreaterThan(Integer value) {
            addCriterion("rh_flat_share_num >", value, "rhFlatShareNum");
            return (Criteria) this;
        }

        public Criteria andRhFlatShareNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("rh_flat_share_num >=", value, "rhFlatShareNum");
            return (Criteria) this;
        }

        public Criteria andRhFlatShareNumLessThan(Integer value) {
            addCriterion("rh_flat_share_num <", value, "rhFlatShareNum");
            return (Criteria) this;
        }

        public Criteria andRhFlatShareNumLessThanOrEqualTo(Integer value) {
            addCriterion("rh_flat_share_num <=", value, "rhFlatShareNum");
            return (Criteria) this;
        }

        public Criteria andRhFlatShareNumIn(List<Integer> values) {
            addCriterion("rh_flat_share_num in", values, "rhFlatShareNum");
            return (Criteria) this;
        }

        public Criteria andRhFlatShareNumNotIn(List<Integer> values) {
            addCriterion("rh_flat_share_num not in", values, "rhFlatShareNum");
            return (Criteria) this;
        }

        public Criteria andRhFlatShareNumBetween(Integer value1, Integer value2) {
            addCriterion("rh_flat_share_num between", value1, value2, "rhFlatShareNum");
            return (Criteria) this;
        }

        public Criteria andRhFlatShareNumNotBetween(Integer value1, Integer value2) {
            addCriterion("rh_flat_share_num not between", value1, value2, "rhFlatShareNum");
            return (Criteria) this;
        }

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Integer value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Integer> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Integer> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Integer value1, Integer value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("priority not between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andActiveIsNull() {
            addCriterion("active is null");
            return (Criteria) this;
        }

        public Criteria andActiveIsNotNull() {
            addCriterion("active is not null");
            return (Criteria) this;
        }

        public Criteria andActiveEqualTo(Boolean value) {
            addCriterion("active =", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualTo(Boolean value) {
            addCriterion("active <>", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThan(Boolean value) {
            addCriterion("active >", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("active >=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThan(Boolean value) {
            addCriterion("active <", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualTo(Boolean value) {
            addCriterion("active <=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveIn(List<Boolean> values) {
            addCriterion("active in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotIn(List<Boolean> values) {
            addCriterion("active not in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveBetween(Boolean value1, Boolean value2) {
            addCriterion("active between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("active not between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlIsNull() {
            addCriterion("pre_image_url is null");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlIsNotNull() {
            addCriterion("pre_image_url is not null");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlEqualTo(String value) {
            addCriterion("pre_image_url =", value, "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlNotEqualTo(String value) {
            addCriterion("pre_image_url <>", value, "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlGreaterThan(String value) {
            addCriterion("pre_image_url >", value, "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pre_image_url >=", value, "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlLessThan(String value) {
            addCriterion("pre_image_url <", value, "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlLessThanOrEqualTo(String value) {
            addCriterion("pre_image_url <=", value, "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlLike(String value) {
            addCriterion("pre_image_url like", value, "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlNotLike(String value) {
            addCriterion("pre_image_url not like", value, "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlIn(List<String> values) {
            addCriterion("pre_image_url in", values, "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlNotIn(List<String> values) {
            addCriterion("pre_image_url not in", values, "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlBetween(String value1, String value2) {
            addCriterion("pre_image_url between", value1, value2, "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlNotBetween(String value1, String value2) {
            addCriterion("pre_image_url not between", value1, value2, "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("creater is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("creater is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("creater =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("creater <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("creater >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("creater >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("creater <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("creater <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("creater like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("creater not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("creater in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("creater not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("creater between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("creater not between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andLastEditorIsNull() {
            addCriterion("last_editor is null");
            return (Criteria) this;
        }

        public Criteria andLastEditorIsNotNull() {
            addCriterion("last_editor is not null");
            return (Criteria) this;
        }

        public Criteria andLastEditorEqualTo(String value) {
            addCriterion("last_editor =", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorNotEqualTo(String value) {
            addCriterion("last_editor <>", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorGreaterThan(String value) {
            addCriterion("last_editor >", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorGreaterThanOrEqualTo(String value) {
            addCriterion("last_editor >=", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorLessThan(String value) {
            addCriterion("last_editor <", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorLessThanOrEqualTo(String value) {
            addCriterion("last_editor <=", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorLike(String value) {
            addCriterion("last_editor like", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorNotLike(String value) {
            addCriterion("last_editor not like", value, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorIn(List<String> values) {
            addCriterion("last_editor in", values, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorNotIn(List<String> values) {
            addCriterion("last_editor not in", values, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorBetween(String value1, String value2) {
            addCriterion("last_editor between", value1, value2, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andLastEditorNotBetween(String value1, String value2) {
            addCriterion("last_editor not between", value1, value2, "lastEditor");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(Boolean value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(Boolean value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(Boolean value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(Boolean value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(Boolean value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<Boolean> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<Boolean> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(Boolean value1, Boolean value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(id) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andAddressLikeInsensitive(String value) {
            addCriterion("upper(address) like", value.toUpperCase(), "address");
            return (Criteria) this;
        }

        public Criteria andPropertyTypeLikeInsensitive(String value) {
            addCriterion("upper(property_type) like", value.toUpperCase(), "propertyType");
            return (Criteria) this;
        }

        public Criteria andBuildingTypeLikeInsensitive(String value) {
            addCriterion("upper(building_type) like", value.toUpperCase(), "buildingType");
            return (Criteria) this;
        }

        public Criteria andPropertyCompanyLikeInsensitive(String value) {
            addCriterion("upper(property_company) like", value.toUpperCase(), "propertyCompany");
            return (Criteria) this;
        }

        public Criteria andIntroductionLikeInsensitive(String value) {
            addCriterion("upper(introduction) like", value.toUpperCase(), "introduction");
            return (Criteria) this;
        }

        public Criteria andSurroundingLikeInsensitive(String value) {
            addCriterion("upper(surrounding) like", value.toUpperCase(), "surrounding");
            return (Criteria) this;
        }

        public Criteria andTrafficLikeInsensitive(String value) {
            addCriterion("upper(traffic) like", value.toUpperCase(), "traffic");
            return (Criteria) this;
        }

        public Criteria andRegionLikeInsensitive(String value) {
            addCriterion("upper(region) like", value.toUpperCase(), "region");
            return (Criteria) this;
        }

        public Criteria andTagsLikeInsensitive(String value) {
            addCriterion("upper(tags) like", value.toUpperCase(), "tags");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(description) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }

        public Criteria andPreImageUrlLikeInsensitive(String value) {
            addCriterion("upper(pre_image_url) like", value.toUpperCase(), "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andCreaterLikeInsensitive(String value) {
            addCriterion("upper(creater) like", value.toUpperCase(), "creater");
            return (Criteria) this;
        }

        public Criteria andLastEditorLikeInsensitive(String value) {
            addCriterion("upper(last_editor) like", value.toUpperCase(), "lastEditor");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}