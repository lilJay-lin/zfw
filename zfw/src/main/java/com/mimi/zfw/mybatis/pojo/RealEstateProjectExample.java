package com.mimi.zfw.mybatis.pojo;

import com.mimi.zfw.plugin.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RealEstateProjectExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public RealEstateProjectExample() {
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

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
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

        public Criteria andOnReadyDateIsNull() {
            addCriterion("on_ready_date is null");
            return (Criteria) this;
        }

        public Criteria andOnReadyDateIsNotNull() {
            addCriterion("on_ready_date is not null");
            return (Criteria) this;
        }

        public Criteria andOnReadyDateEqualTo(Date value) {
            addCriterionForJDBCDate("on_ready_date =", value, "onReadyDate");
            return (Criteria) this;
        }

        public Criteria andOnReadyDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("on_ready_date <>", value, "onReadyDate");
            return (Criteria) this;
        }

        public Criteria andOnReadyDateGreaterThan(Date value) {
            addCriterionForJDBCDate("on_ready_date >", value, "onReadyDate");
            return (Criteria) this;
        }

        public Criteria andOnReadyDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("on_ready_date >=", value, "onReadyDate");
            return (Criteria) this;
        }

        public Criteria andOnReadyDateLessThan(Date value) {
            addCriterionForJDBCDate("on_ready_date <", value, "onReadyDate");
            return (Criteria) this;
        }

        public Criteria andOnReadyDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("on_ready_date <=", value, "onReadyDate");
            return (Criteria) this;
        }

        public Criteria andOnReadyDateIn(List<Date> values) {
            addCriterionForJDBCDate("on_ready_date in", values, "onReadyDate");
            return (Criteria) this;
        }

        public Criteria andOnReadyDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("on_ready_date not in", values, "onReadyDate");
            return (Criteria) this;
        }

        public Criteria andOnReadyDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("on_ready_date between", value1, value2, "onReadyDate");
            return (Criteria) this;
        }

        public Criteria andOnReadyDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("on_ready_date not between", value1, value2, "onReadyDate");
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

        public Criteria andDecorationStatusIsNull() {
            addCriterion("decoration_status is null");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusIsNotNull() {
            addCriterion("decoration_status is not null");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusEqualTo(String value) {
            addCriterion("decoration_status =", value, "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusNotEqualTo(String value) {
            addCriterion("decoration_status <>", value, "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusGreaterThan(String value) {
            addCriterion("decoration_status >", value, "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusGreaterThanOrEqualTo(String value) {
            addCriterion("decoration_status >=", value, "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusLessThan(String value) {
            addCriterion("decoration_status <", value, "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusLessThanOrEqualTo(String value) {
            addCriterion("decoration_status <=", value, "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusLike(String value) {
            addCriterion("decoration_status like", value, "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusNotLike(String value) {
            addCriterion("decoration_status not like", value, "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusIn(List<String> values) {
            addCriterion("decoration_status in", values, "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusNotIn(List<String> values) {
            addCriterion("decoration_status not in", values, "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusBetween(String value1, String value2) {
            addCriterion("decoration_status between", value1, value2, "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusNotBetween(String value1, String value2) {
            addCriterion("decoration_status not between", value1, value2, "decorationStatus");
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

        public Criteria andDeveloperIsNull() {
            addCriterion("developer is null");
            return (Criteria) this;
        }

        public Criteria andDeveloperIsNotNull() {
            addCriterion("developer is not null");
            return (Criteria) this;
        }

        public Criteria andDeveloperEqualTo(String value) {
            addCriterion("developer =", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperNotEqualTo(String value) {
            addCriterion("developer <>", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperGreaterThan(String value) {
            addCriterion("developer >", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperGreaterThanOrEqualTo(String value) {
            addCriterion("developer >=", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperLessThan(String value) {
            addCriterion("developer <", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperLessThanOrEqualTo(String value) {
            addCriterion("developer <=", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperLike(String value) {
            addCriterion("developer like", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperNotLike(String value) {
            addCriterion("developer not like", value, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperIn(List<String> values) {
            addCriterion("developer in", values, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperNotIn(List<String> values) {
            addCriterion("developer not in", values, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperBetween(String value1, String value2) {
            addCriterion("developer between", value1, value2, "developer");
            return (Criteria) this;
        }

        public Criteria andDeveloperNotBetween(String value1, String value2) {
            addCriterion("developer not between", value1, value2, "developer");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIsNull() {
            addCriterion("pre_sale_permit is null");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIsNotNull() {
            addCriterion("pre_sale_permit is not null");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitEqualTo(String value) {
            addCriterion("pre_sale_permit =", value, "preSalePermit");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitNotEqualTo(String value) {
            addCriterion("pre_sale_permit <>", value, "preSalePermit");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitGreaterThan(String value) {
            addCriterion("pre_sale_permit >", value, "preSalePermit");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitGreaterThanOrEqualTo(String value) {
            addCriterion("pre_sale_permit >=", value, "preSalePermit");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitLessThan(String value) {
            addCriterion("pre_sale_permit <", value, "preSalePermit");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitLessThanOrEqualTo(String value) {
            addCriterion("pre_sale_permit <=", value, "preSalePermit");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitLike(String value) {
            addCriterion("pre_sale_permit like", value, "preSalePermit");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitNotLike(String value) {
            addCriterion("pre_sale_permit not like", value, "preSalePermit");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitIn(List<String> values) {
            addCriterion("pre_sale_permit in", values, "preSalePermit");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitNotIn(List<String> values) {
            addCriterion("pre_sale_permit not in", values, "preSalePermit");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitBetween(String value1, String value2) {
            addCriterion("pre_sale_permit between", value1, value2, "preSalePermit");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitNotBetween(String value1, String value2) {
            addCriterion("pre_sale_permit not between", value1, value2, "preSalePermit");
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

        public Criteria andAveragePriceIsNull() {
            addCriterion("average_price is null");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIsNotNull() {
            addCriterion("average_price is not null");
            return (Criteria) this;
        }

        public Criteria andAveragePriceEqualTo(Integer value) {
            addCriterion("average_price =", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotEqualTo(Integer value) {
            addCriterion("average_price <>", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceGreaterThan(Integer value) {
            addCriterion("average_price >", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("average_price >=", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLessThan(Integer value) {
            addCriterion("average_price <", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceLessThanOrEqualTo(Integer value) {
            addCriterion("average_price <=", value, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceIn(List<Integer> values) {
            addCriterion("average_price in", values, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotIn(List<Integer> values) {
            addCriterion("average_price not in", values, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceBetween(Integer value1, Integer value2) {
            addCriterion("average_price between", value1, value2, "averagePrice");
            return (Criteria) this;
        }

        public Criteria andAveragePriceNotBetween(Integer value1, Integer value2) {
            addCriterion("average_price not between", value1, value2, "averagePrice");
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

        public Criteria andOneRoomNumIsNull() {
            addCriterion("one_room_num is null");
            return (Criteria) this;
        }

        public Criteria andOneRoomNumIsNotNull() {
            addCriterion("one_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andOneRoomNumEqualTo(Integer value) {
            addCriterion("one_room_num =", value, "oneRoomNum");
            return (Criteria) this;
        }

        public Criteria andOneRoomNumNotEqualTo(Integer value) {
            addCriterion("one_room_num <>", value, "oneRoomNum");
            return (Criteria) this;
        }

        public Criteria andOneRoomNumGreaterThan(Integer value) {
            addCriterion("one_room_num >", value, "oneRoomNum");
            return (Criteria) this;
        }

        public Criteria andOneRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("one_room_num >=", value, "oneRoomNum");
            return (Criteria) this;
        }

        public Criteria andOneRoomNumLessThan(Integer value) {
            addCriterion("one_room_num <", value, "oneRoomNum");
            return (Criteria) this;
        }

        public Criteria andOneRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("one_room_num <=", value, "oneRoomNum");
            return (Criteria) this;
        }

        public Criteria andOneRoomNumIn(List<Integer> values) {
            addCriterion("one_room_num in", values, "oneRoomNum");
            return (Criteria) this;
        }

        public Criteria andOneRoomNumNotIn(List<Integer> values) {
            addCriterion("one_room_num not in", values, "oneRoomNum");
            return (Criteria) this;
        }

        public Criteria andOneRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("one_room_num between", value1, value2, "oneRoomNum");
            return (Criteria) this;
        }

        public Criteria andOneRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("one_room_num not between", value1, value2, "oneRoomNum");
            return (Criteria) this;
        }

        public Criteria andTwoRoomNumIsNull() {
            addCriterion("two_room_num is null");
            return (Criteria) this;
        }

        public Criteria andTwoRoomNumIsNotNull() {
            addCriterion("two_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andTwoRoomNumEqualTo(Integer value) {
            addCriterion("two_room_num =", value, "twoRoomNum");
            return (Criteria) this;
        }

        public Criteria andTwoRoomNumNotEqualTo(Integer value) {
            addCriterion("two_room_num <>", value, "twoRoomNum");
            return (Criteria) this;
        }

        public Criteria andTwoRoomNumGreaterThan(Integer value) {
            addCriterion("two_room_num >", value, "twoRoomNum");
            return (Criteria) this;
        }

        public Criteria andTwoRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("two_room_num >=", value, "twoRoomNum");
            return (Criteria) this;
        }

        public Criteria andTwoRoomNumLessThan(Integer value) {
            addCriterion("two_room_num <", value, "twoRoomNum");
            return (Criteria) this;
        }

        public Criteria andTwoRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("two_room_num <=", value, "twoRoomNum");
            return (Criteria) this;
        }

        public Criteria andTwoRoomNumIn(List<Integer> values) {
            addCriterion("two_room_num in", values, "twoRoomNum");
            return (Criteria) this;
        }

        public Criteria andTwoRoomNumNotIn(List<Integer> values) {
            addCriterion("two_room_num not in", values, "twoRoomNum");
            return (Criteria) this;
        }

        public Criteria andTwoRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("two_room_num between", value1, value2, "twoRoomNum");
            return (Criteria) this;
        }

        public Criteria andTwoRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("two_room_num not between", value1, value2, "twoRoomNum");
            return (Criteria) this;
        }

        public Criteria andThreeRoomNumIsNull() {
            addCriterion("three_room_num is null");
            return (Criteria) this;
        }

        public Criteria andThreeRoomNumIsNotNull() {
            addCriterion("three_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andThreeRoomNumEqualTo(Integer value) {
            addCriterion("three_room_num =", value, "threeRoomNum");
            return (Criteria) this;
        }

        public Criteria andThreeRoomNumNotEqualTo(Integer value) {
            addCriterion("three_room_num <>", value, "threeRoomNum");
            return (Criteria) this;
        }

        public Criteria andThreeRoomNumGreaterThan(Integer value) {
            addCriterion("three_room_num >", value, "threeRoomNum");
            return (Criteria) this;
        }

        public Criteria andThreeRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("three_room_num >=", value, "threeRoomNum");
            return (Criteria) this;
        }

        public Criteria andThreeRoomNumLessThan(Integer value) {
            addCriterion("three_room_num <", value, "threeRoomNum");
            return (Criteria) this;
        }

        public Criteria andThreeRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("three_room_num <=", value, "threeRoomNum");
            return (Criteria) this;
        }

        public Criteria andThreeRoomNumIn(List<Integer> values) {
            addCriterion("three_room_num in", values, "threeRoomNum");
            return (Criteria) this;
        }

        public Criteria andThreeRoomNumNotIn(List<Integer> values) {
            addCriterion("three_room_num not in", values, "threeRoomNum");
            return (Criteria) this;
        }

        public Criteria andThreeRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("three_room_num between", value1, value2, "threeRoomNum");
            return (Criteria) this;
        }

        public Criteria andThreeRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("three_room_num not between", value1, value2, "threeRoomNum");
            return (Criteria) this;
        }

        public Criteria andFourRoomNumIsNull() {
            addCriterion("four_room_num is null");
            return (Criteria) this;
        }

        public Criteria andFourRoomNumIsNotNull() {
            addCriterion("four_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andFourRoomNumEqualTo(Integer value) {
            addCriterion("four_room_num =", value, "fourRoomNum");
            return (Criteria) this;
        }

        public Criteria andFourRoomNumNotEqualTo(Integer value) {
            addCriterion("four_room_num <>", value, "fourRoomNum");
            return (Criteria) this;
        }

        public Criteria andFourRoomNumGreaterThan(Integer value) {
            addCriterion("four_room_num >", value, "fourRoomNum");
            return (Criteria) this;
        }

        public Criteria andFourRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("four_room_num >=", value, "fourRoomNum");
            return (Criteria) this;
        }

        public Criteria andFourRoomNumLessThan(Integer value) {
            addCriterion("four_room_num <", value, "fourRoomNum");
            return (Criteria) this;
        }

        public Criteria andFourRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("four_room_num <=", value, "fourRoomNum");
            return (Criteria) this;
        }

        public Criteria andFourRoomNumIn(List<Integer> values) {
            addCriterion("four_room_num in", values, "fourRoomNum");
            return (Criteria) this;
        }

        public Criteria andFourRoomNumNotIn(List<Integer> values) {
            addCriterion("four_room_num not in", values, "fourRoomNum");
            return (Criteria) this;
        }

        public Criteria andFourRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("four_room_num between", value1, value2, "fourRoomNum");
            return (Criteria) this;
        }

        public Criteria andFourRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("four_room_num not between", value1, value2, "fourRoomNum");
            return (Criteria) this;
        }

        public Criteria andFiveRoomNumIsNull() {
            addCriterion("five_room_num is null");
            return (Criteria) this;
        }

        public Criteria andFiveRoomNumIsNotNull() {
            addCriterion("five_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andFiveRoomNumEqualTo(Integer value) {
            addCriterion("five_room_num =", value, "fiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andFiveRoomNumNotEqualTo(Integer value) {
            addCriterion("five_room_num <>", value, "fiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andFiveRoomNumGreaterThan(Integer value) {
            addCriterion("five_room_num >", value, "fiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andFiveRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("five_room_num >=", value, "fiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andFiveRoomNumLessThan(Integer value) {
            addCriterion("five_room_num <", value, "fiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andFiveRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("five_room_num <=", value, "fiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andFiveRoomNumIn(List<Integer> values) {
            addCriterion("five_room_num in", values, "fiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andFiveRoomNumNotIn(List<Integer> values) {
            addCriterion("five_room_num not in", values, "fiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andFiveRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("five_room_num between", value1, value2, "fiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andFiveRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("five_room_num not between", value1, value2, "fiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andOverFiveRoomNumIsNull() {
            addCriterion("over_five_room_num is null");
            return (Criteria) this;
        }

        public Criteria andOverFiveRoomNumIsNotNull() {
            addCriterion("over_five_room_num is not null");
            return (Criteria) this;
        }

        public Criteria andOverFiveRoomNumEqualTo(Integer value) {
            addCriterion("over_five_room_num =", value, "overFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andOverFiveRoomNumNotEqualTo(Integer value) {
            addCriterion("over_five_room_num <>", value, "overFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andOverFiveRoomNumGreaterThan(Integer value) {
            addCriterion("over_five_room_num >", value, "overFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andOverFiveRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("over_five_room_num >=", value, "overFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andOverFiveRoomNumLessThan(Integer value) {
            addCriterion("over_five_room_num <", value, "overFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andOverFiveRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("over_five_room_num <=", value, "overFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andOverFiveRoomNumIn(List<Integer> values) {
            addCriterion("over_five_room_num in", values, "overFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andOverFiveRoomNumNotIn(List<Integer> values) {
            addCriterion("over_five_room_num not in", values, "overFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andOverFiveRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("over_five_room_num between", value1, value2, "overFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andOverFiveRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("over_five_room_num not between", value1, value2, "overFiveRoomNum");
            return (Criteria) this;
        }

        public Criteria andMinRoomGrossFloorAreaIsNull() {
            addCriterion("min_room_gross_floor_area is null");
            return (Criteria) this;
        }

        public Criteria andMinRoomGrossFloorAreaIsNotNull() {
            addCriterion("min_room_gross_floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andMinRoomGrossFloorAreaEqualTo(Integer value) {
            addCriterion("min_room_gross_floor_area =", value, "minRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMinRoomGrossFloorAreaNotEqualTo(Integer value) {
            addCriterion("min_room_gross_floor_area <>", value, "minRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMinRoomGrossFloorAreaGreaterThan(Integer value) {
            addCriterion("min_room_gross_floor_area >", value, "minRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMinRoomGrossFloorAreaGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_room_gross_floor_area >=", value, "minRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMinRoomGrossFloorAreaLessThan(Integer value) {
            addCriterion("min_room_gross_floor_area <", value, "minRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMinRoomGrossFloorAreaLessThanOrEqualTo(Integer value) {
            addCriterion("min_room_gross_floor_area <=", value, "minRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMinRoomGrossFloorAreaIn(List<Integer> values) {
            addCriterion("min_room_gross_floor_area in", values, "minRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMinRoomGrossFloorAreaNotIn(List<Integer> values) {
            addCriterion("min_room_gross_floor_area not in", values, "minRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMinRoomGrossFloorAreaBetween(Integer value1, Integer value2) {
            addCriterion("min_room_gross_floor_area between", value1, value2, "minRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMinRoomGrossFloorAreaNotBetween(Integer value1, Integer value2) {
            addCriterion("min_room_gross_floor_area not between", value1, value2, "minRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMaxRoomGrossFloorAreaIsNull() {
            addCriterion("max_room_gross_floor_area is null");
            return (Criteria) this;
        }

        public Criteria andMaxRoomGrossFloorAreaIsNotNull() {
            addCriterion("max_room_gross_floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andMaxRoomGrossFloorAreaEqualTo(Integer value) {
            addCriterion("max_room_gross_floor_area =", value, "maxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMaxRoomGrossFloorAreaNotEqualTo(Integer value) {
            addCriterion("max_room_gross_floor_area <>", value, "maxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMaxRoomGrossFloorAreaGreaterThan(Integer value) {
            addCriterion("max_room_gross_floor_area >", value, "maxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMaxRoomGrossFloorAreaGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_room_gross_floor_area >=", value, "maxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMaxRoomGrossFloorAreaLessThan(Integer value) {
            addCriterion("max_room_gross_floor_area <", value, "maxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMaxRoomGrossFloorAreaLessThanOrEqualTo(Integer value) {
            addCriterion("max_room_gross_floor_area <=", value, "maxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMaxRoomGrossFloorAreaIn(List<Integer> values) {
            addCriterion("max_room_gross_floor_area in", values, "maxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMaxRoomGrossFloorAreaNotIn(List<Integer> values) {
            addCriterion("max_room_gross_floor_area not in", values, "maxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMaxRoomGrossFloorAreaBetween(Integer value1, Integer value2) {
            addCriterion("max_room_gross_floor_area between", value1, value2, "maxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andMaxRoomGrossFloorAreaNotBetween(Integer value1, Integer value2) {
            addCriterion("max_room_gross_floor_area not between", value1, value2, "maxRoomGrossFloorArea");
            return (Criteria) this;
        }

        public Criteria andSaleStatusIsNull() {
            addCriterion("sale_status is null");
            return (Criteria) this;
        }

        public Criteria andSaleStatusIsNotNull() {
            addCriterion("sale_status is not null");
            return (Criteria) this;
        }

        public Criteria andSaleStatusEqualTo(String value) {
            addCriterion("sale_status =", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotEqualTo(String value) {
            addCriterion("sale_status <>", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusGreaterThan(String value) {
            addCriterion("sale_status >", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusGreaterThanOrEqualTo(String value) {
            addCriterion("sale_status >=", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusLessThan(String value) {
            addCriterion("sale_status <", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusLessThanOrEqualTo(String value) {
            addCriterion("sale_status <=", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusLike(String value) {
            addCriterion("sale_status like", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotLike(String value) {
            addCriterion("sale_status not like", value, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusIn(List<String> values) {
            addCriterion("sale_status in", values, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotIn(List<String> values) {
            addCriterion("sale_status not in", values, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusBetween(String value1, String value2) {
            addCriterion("sale_status between", value1, value2, "saleStatus");
            return (Criteria) this;
        }

        public Criteria andSaleStatusNotBetween(String value1, String value2) {
            addCriterion("sale_status not between", value1, value2, "saleStatus");
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
            addCriterionForJDBCDate("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterionForJDBCDate("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterionForJDBCDate("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_date not between", value1, value2, "createDate");
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
            addCriterionForJDBCDate("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterionForJDBCDate("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterionForJDBCDate("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_date not between", value1, value2, "updateDate");
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

        public Criteria andTelLikeInsensitive(String value) {
            addCriterion("upper(tel) like", value.toUpperCase(), "tel");
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

        public Criteria andDecorationStatusLikeInsensitive(String value) {
            addCriterion("upper(decoration_status) like", value.toUpperCase(), "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andDeveloperLikeInsensitive(String value) {
            addCriterion("upper(developer) like", value.toUpperCase(), "developer");
            return (Criteria) this;
        }

        public Criteria andPreSalePermitLikeInsensitive(String value) {
            addCriterion("upper(pre_sale_permit) like", value.toUpperCase(), "preSalePermit");
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

        public Criteria andPreImageUrlLikeInsensitive(String value) {
            addCriterion("upper(pre_image_url) like", value.toUpperCase(), "preImageUrl");
            return (Criteria) this;
        }

        public Criteria andSaleStatusLikeInsensitive(String value) {
            addCriterion("upper(sale_status) like", value.toUpperCase(), "saleStatus");
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