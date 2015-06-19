package com.mimi.zfw.mybatis.pojo;

import com.mimi.zfw.plugin.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class HouseTypeExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public HouseTypeExample() {
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

        public Criteria andRealEstateProjectIdIsNull() {
            addCriterion("real_estate_project_id is null");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdIsNotNull() {
            addCriterion("real_estate_project_id is not null");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdEqualTo(String value) {
            addCriterion("real_estate_project_id =", value, "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdNotEqualTo(String value) {
            addCriterion("real_estate_project_id <>", value, "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdGreaterThan(String value) {
            addCriterion("real_estate_project_id >", value, "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("real_estate_project_id >=", value, "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdLessThan(String value) {
            addCriterion("real_estate_project_id <", value, "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdLessThanOrEqualTo(String value) {
            addCriterion("real_estate_project_id <=", value, "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdLike(String value) {
            addCriterion("real_estate_project_id like", value, "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdNotLike(String value) {
            addCriterion("real_estate_project_id not like", value, "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdIn(List<String> values) {
            addCriterion("real_estate_project_id in", values, "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdNotIn(List<String> values) {
            addCriterion("real_estate_project_id not in", values, "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdBetween(String value1, String value2) {
            addCriterion("real_estate_project_id between", value1, value2, "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectIdNotBetween(String value1, String value2) {
            addCriterion("real_estate_project_id not between", value1, value2, "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameIsNull() {
            addCriterion("real_estate_project_name is null");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameIsNotNull() {
            addCriterion("real_estate_project_name is not null");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameEqualTo(String value) {
            addCriterion("real_estate_project_name =", value, "realEstateProjectName");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameNotEqualTo(String value) {
            addCriterion("real_estate_project_name <>", value, "realEstateProjectName");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameGreaterThan(String value) {
            addCriterion("real_estate_project_name >", value, "realEstateProjectName");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameGreaterThanOrEqualTo(String value) {
            addCriterion("real_estate_project_name >=", value, "realEstateProjectName");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameLessThan(String value) {
            addCriterion("real_estate_project_name <", value, "realEstateProjectName");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameLessThanOrEqualTo(String value) {
            addCriterion("real_estate_project_name <=", value, "realEstateProjectName");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameLike(String value) {
            addCriterion("real_estate_project_name like", value, "realEstateProjectName");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameNotLike(String value) {
            addCriterion("real_estate_project_name not like", value, "realEstateProjectName");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameIn(List<String> values) {
            addCriterion("real_estate_project_name in", values, "realEstateProjectName");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameNotIn(List<String> values) {
            addCriterion("real_estate_project_name not in", values, "realEstateProjectName");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameBetween(String value1, String value2) {
            addCriterion("real_estate_project_name between", value1, value2, "realEstateProjectName");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameNotBetween(String value1, String value2) {
            addCriterion("real_estate_project_name not between", value1, value2, "realEstateProjectName");
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

        public Criteria andInsideAreaIsNull() {
            addCriterion("inside_area is null");
            return (Criteria) this;
        }

        public Criteria andInsideAreaIsNotNull() {
            addCriterion("inside_area is not null");
            return (Criteria) this;
        }

        public Criteria andInsideAreaEqualTo(Float value) {
            addCriterion("inside_area =", value, "insideArea");
            return (Criteria) this;
        }

        public Criteria andInsideAreaNotEqualTo(Float value) {
            addCriterion("inside_area <>", value, "insideArea");
            return (Criteria) this;
        }

        public Criteria andInsideAreaGreaterThan(Float value) {
            addCriterion("inside_area >", value, "insideArea");
            return (Criteria) this;
        }

        public Criteria andInsideAreaGreaterThanOrEqualTo(Float value) {
            addCriterion("inside_area >=", value, "insideArea");
            return (Criteria) this;
        }

        public Criteria andInsideAreaLessThan(Float value) {
            addCriterion("inside_area <", value, "insideArea");
            return (Criteria) this;
        }

        public Criteria andInsideAreaLessThanOrEqualTo(Float value) {
            addCriterion("inside_area <=", value, "insideArea");
            return (Criteria) this;
        }

        public Criteria andInsideAreaIn(List<Float> values) {
            addCriterion("inside_area in", values, "insideArea");
            return (Criteria) this;
        }

        public Criteria andInsideAreaNotIn(List<Float> values) {
            addCriterion("inside_area not in", values, "insideArea");
            return (Criteria) this;
        }

        public Criteria andInsideAreaBetween(Float value1, Float value2) {
            addCriterion("inside_area between", value1, value2, "insideArea");
            return (Criteria) this;
        }

        public Criteria andInsideAreaNotBetween(Float value1, Float value2) {
            addCriterion("inside_area not between", value1, value2, "insideArea");
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

        public Criteria andGrossFloorAreaIsNull() {
            addCriterion("gross_floor_area is null");
            return (Criteria) this;
        }

        public Criteria andGrossFloorAreaIsNotNull() {
            addCriterion("gross_floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andGrossFloorAreaEqualTo(Float value) {
            addCriterion("gross_floor_area =", value, "grossFloorArea");
            return (Criteria) this;
        }

        public Criteria andGrossFloorAreaNotEqualTo(Float value) {
            addCriterion("gross_floor_area <>", value, "grossFloorArea");
            return (Criteria) this;
        }

        public Criteria andGrossFloorAreaGreaterThan(Float value) {
            addCriterion("gross_floor_area >", value, "grossFloorArea");
            return (Criteria) this;
        }

        public Criteria andGrossFloorAreaGreaterThanOrEqualTo(Float value) {
            addCriterion("gross_floor_area >=", value, "grossFloorArea");
            return (Criteria) this;
        }

        public Criteria andGrossFloorAreaLessThan(Float value) {
            addCriterion("gross_floor_area <", value, "grossFloorArea");
            return (Criteria) this;
        }

        public Criteria andGrossFloorAreaLessThanOrEqualTo(Float value) {
            addCriterion("gross_floor_area <=", value, "grossFloorArea");
            return (Criteria) this;
        }

        public Criteria andGrossFloorAreaIn(List<Float> values) {
            addCriterion("gross_floor_area in", values, "grossFloorArea");
            return (Criteria) this;
        }

        public Criteria andGrossFloorAreaNotIn(List<Float> values) {
            addCriterion("gross_floor_area not in", values, "grossFloorArea");
            return (Criteria) this;
        }

        public Criteria andGrossFloorAreaBetween(Float value1, Float value2) {
            addCriterion("gross_floor_area between", value1, value2, "grossFloorArea");
            return (Criteria) this;
        }

        public Criteria andGrossFloorAreaNotBetween(Float value1, Float value2) {
            addCriterion("gross_floor_area not between", value1, value2, "grossFloorArea");
            return (Criteria) this;
        }

        public Criteria andRoomNumIsNull() {
            addCriterion("room_num is null");
            return (Criteria) this;
        }

        public Criteria andRoomNumIsNotNull() {
            addCriterion("room_num is not null");
            return (Criteria) this;
        }

        public Criteria andRoomNumEqualTo(Integer value) {
            addCriterion("room_num =", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumNotEqualTo(Integer value) {
            addCriterion("room_num <>", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumGreaterThan(Integer value) {
            addCriterion("room_num >", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_num >=", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumLessThan(Integer value) {
            addCriterion("room_num <", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumLessThanOrEqualTo(Integer value) {
            addCriterion("room_num <=", value, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumIn(List<Integer> values) {
            addCriterion("room_num in", values, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumNotIn(List<Integer> values) {
            addCriterion("room_num not in", values, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumBetween(Integer value1, Integer value2) {
            addCriterion("room_num between", value1, value2, "roomNum");
            return (Criteria) this;
        }

        public Criteria andRoomNumNotBetween(Integer value1, Integer value2) {
            addCriterion("room_num not between", value1, value2, "roomNum");
            return (Criteria) this;
        }

        public Criteria andHallNumIsNull() {
            addCriterion("hall_num is null");
            return (Criteria) this;
        }

        public Criteria andHallNumIsNotNull() {
            addCriterion("hall_num is not null");
            return (Criteria) this;
        }

        public Criteria andHallNumEqualTo(Integer value) {
            addCriterion("hall_num =", value, "hallNum");
            return (Criteria) this;
        }

        public Criteria andHallNumNotEqualTo(Integer value) {
            addCriterion("hall_num <>", value, "hallNum");
            return (Criteria) this;
        }

        public Criteria andHallNumGreaterThan(Integer value) {
            addCriterion("hall_num >", value, "hallNum");
            return (Criteria) this;
        }

        public Criteria andHallNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("hall_num >=", value, "hallNum");
            return (Criteria) this;
        }

        public Criteria andHallNumLessThan(Integer value) {
            addCriterion("hall_num <", value, "hallNum");
            return (Criteria) this;
        }

        public Criteria andHallNumLessThanOrEqualTo(Integer value) {
            addCriterion("hall_num <=", value, "hallNum");
            return (Criteria) this;
        }

        public Criteria andHallNumIn(List<Integer> values) {
            addCriterion("hall_num in", values, "hallNum");
            return (Criteria) this;
        }

        public Criteria andHallNumNotIn(List<Integer> values) {
            addCriterion("hall_num not in", values, "hallNum");
            return (Criteria) this;
        }

        public Criteria andHallNumBetween(Integer value1, Integer value2) {
            addCriterion("hall_num between", value1, value2, "hallNum");
            return (Criteria) this;
        }

        public Criteria andHallNumNotBetween(Integer value1, Integer value2) {
            addCriterion("hall_num not between", value1, value2, "hallNum");
            return (Criteria) this;
        }

        public Criteria andKitchenNumIsNull() {
            addCriterion("kitchen_num is null");
            return (Criteria) this;
        }

        public Criteria andKitchenNumIsNotNull() {
            addCriterion("kitchen_num is not null");
            return (Criteria) this;
        }

        public Criteria andKitchenNumEqualTo(Integer value) {
            addCriterion("kitchen_num =", value, "kitchenNum");
            return (Criteria) this;
        }

        public Criteria andKitchenNumNotEqualTo(Integer value) {
            addCriterion("kitchen_num <>", value, "kitchenNum");
            return (Criteria) this;
        }

        public Criteria andKitchenNumGreaterThan(Integer value) {
            addCriterion("kitchen_num >", value, "kitchenNum");
            return (Criteria) this;
        }

        public Criteria andKitchenNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("kitchen_num >=", value, "kitchenNum");
            return (Criteria) this;
        }

        public Criteria andKitchenNumLessThan(Integer value) {
            addCriterion("kitchen_num <", value, "kitchenNum");
            return (Criteria) this;
        }

        public Criteria andKitchenNumLessThanOrEqualTo(Integer value) {
            addCriterion("kitchen_num <=", value, "kitchenNum");
            return (Criteria) this;
        }

        public Criteria andKitchenNumIn(List<Integer> values) {
            addCriterion("kitchen_num in", values, "kitchenNum");
            return (Criteria) this;
        }

        public Criteria andKitchenNumNotIn(List<Integer> values) {
            addCriterion("kitchen_num not in", values, "kitchenNum");
            return (Criteria) this;
        }

        public Criteria andKitchenNumBetween(Integer value1, Integer value2) {
            addCriterion("kitchen_num between", value1, value2, "kitchenNum");
            return (Criteria) this;
        }

        public Criteria andKitchenNumNotBetween(Integer value1, Integer value2) {
            addCriterion("kitchen_num not between", value1, value2, "kitchenNum");
            return (Criteria) this;
        }

        public Criteria andToiletNumIsNull() {
            addCriterion("toilet_num is null");
            return (Criteria) this;
        }

        public Criteria andToiletNumIsNotNull() {
            addCriterion("toilet_num is not null");
            return (Criteria) this;
        }

        public Criteria andToiletNumEqualTo(Integer value) {
            addCriterion("toilet_num =", value, "toiletNum");
            return (Criteria) this;
        }

        public Criteria andToiletNumNotEqualTo(Integer value) {
            addCriterion("toilet_num <>", value, "toiletNum");
            return (Criteria) this;
        }

        public Criteria andToiletNumGreaterThan(Integer value) {
            addCriterion("toilet_num >", value, "toiletNum");
            return (Criteria) this;
        }

        public Criteria andToiletNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("toilet_num >=", value, "toiletNum");
            return (Criteria) this;
        }

        public Criteria andToiletNumLessThan(Integer value) {
            addCriterion("toilet_num <", value, "toiletNum");
            return (Criteria) this;
        }

        public Criteria andToiletNumLessThanOrEqualTo(Integer value) {
            addCriterion("toilet_num <=", value, "toiletNum");
            return (Criteria) this;
        }

        public Criteria andToiletNumIn(List<Integer> values) {
            addCriterion("toilet_num in", values, "toiletNum");
            return (Criteria) this;
        }

        public Criteria andToiletNumNotIn(List<Integer> values) {
            addCriterion("toilet_num not in", values, "toiletNum");
            return (Criteria) this;
        }

        public Criteria andToiletNumBetween(Integer value1, Integer value2) {
            addCriterion("toilet_num between", value1, value2, "toiletNum");
            return (Criteria) this;
        }

        public Criteria andToiletNumNotBetween(Integer value1, Integer value2) {
            addCriterion("toilet_num not between", value1, value2, "toiletNum");
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

        public Criteria andRealEstateProjectIdLikeInsensitive(String value) {
            addCriterion("upper(real_estate_project_id) like", value.toUpperCase(), "realEstateProjectId");
            return (Criteria) this;
        }

        public Criteria andRealEstateProjectNameLikeInsensitive(String value) {
            addCriterion("upper(real_estate_project_name) like", value.toUpperCase(), "realEstateProjectName");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andSaleStatusLikeInsensitive(String value) {
            addCriterion("upper(sale_status) like", value.toUpperCase(), "saleStatus");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(description) like", value.toUpperCase(), "description");
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

        public Criteria andRegionLikeInsensitive(String value) {
            addCriterion("upper(region) like", value.toUpperCase(), "region");
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