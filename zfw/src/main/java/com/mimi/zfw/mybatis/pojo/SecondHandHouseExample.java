package com.mimi.zfw.mybatis.pojo;

import com.mimi.zfw.plugin.BaseExample;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SecondHandHouseExample extends BaseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitSize;

    public SecondHandHouseExample() {
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

        public Criteria andResidenceCommunityIdIsNull() {
            addCriterion("residence_community_id is null");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdIsNotNull() {
            addCriterion("residence_community_id is not null");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdEqualTo(String value) {
            addCriterion("residence_community_id =", value, "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdNotEqualTo(String value) {
            addCriterion("residence_community_id <>", value, "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdGreaterThan(String value) {
            addCriterion("residence_community_id >", value, "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdGreaterThanOrEqualTo(String value) {
            addCriterion("residence_community_id >=", value, "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdLessThan(String value) {
            addCriterion("residence_community_id <", value, "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdLessThanOrEqualTo(String value) {
            addCriterion("residence_community_id <=", value, "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdLike(String value) {
            addCriterion("residence_community_id like", value, "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdNotLike(String value) {
            addCriterion("residence_community_id not like", value, "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdIn(List<String> values) {
            addCriterion("residence_community_id in", values, "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdNotIn(List<String> values) {
            addCriterion("residence_community_id not in", values, "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdBetween(String value1, String value2) {
            addCriterion("residence_community_id between", value1, value2, "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityIdNotBetween(String value1, String value2) {
            addCriterion("residence_community_id not between", value1, value2, "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameIsNull() {
            addCriterion("residence_community_name is null");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameIsNotNull() {
            addCriterion("residence_community_name is not null");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameEqualTo(String value) {
            addCriterion("residence_community_name =", value, "residenceCommunityName");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameNotEqualTo(String value) {
            addCriterion("residence_community_name <>", value, "residenceCommunityName");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameGreaterThan(String value) {
            addCriterion("residence_community_name >", value, "residenceCommunityName");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameGreaterThanOrEqualTo(String value) {
            addCriterion("residence_community_name >=", value, "residenceCommunityName");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameLessThan(String value) {
            addCriterion("residence_community_name <", value, "residenceCommunityName");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameLessThanOrEqualTo(String value) {
            addCriterion("residence_community_name <=", value, "residenceCommunityName");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameLike(String value) {
            addCriterion("residence_community_name like", value, "residenceCommunityName");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameNotLike(String value) {
            addCriterion("residence_community_name not like", value, "residenceCommunityName");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameIn(List<String> values) {
            addCriterion("residence_community_name in", values, "residenceCommunityName");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameNotIn(List<String> values) {
            addCriterion("residence_community_name not in", values, "residenceCommunityName");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameBetween(String value1, String value2) {
            addCriterion("residence_community_name between", value1, value2, "residenceCommunityName");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameNotBetween(String value1, String value2) {
            addCriterion("residence_community_name not between", value1, value2, "residenceCommunityName");
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

        public Criteria andPhoneNumIsNull() {
            addCriterion("phone_num is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNotNull() {
            addCriterion("phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumEqualTo(String value) {
            addCriterion("phone_num =", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotEqualTo(String value) {
            addCriterion("phone_num <>", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThan(String value) {
            addCriterion("phone_num >", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("phone_num >=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThan(String value) {
            addCriterion("phone_num <", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThanOrEqualTo(String value) {
            addCriterion("phone_num <=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLike(String value) {
            addCriterion("phone_num like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotLike(String value) {
            addCriterion("phone_num not like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIn(List<String> values) {
            addCriterion("phone_num in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotIn(List<String> values) {
            addCriterion("phone_num not in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumBetween(String value1, String value2) {
            addCriterion("phone_num between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotBetween(String value1, String value2) {
            addCriterion("phone_num not between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNull() {
            addCriterion("total_price is null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNotNull() {
            addCriterion("total_price is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceEqualTo(Integer value) {
            addCriterion("total_price =", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotEqualTo(Integer value) {
            addCriterion("total_price <>", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThan(Integer value) {
            addCriterion("total_price >", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_price >=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThan(Integer value) {
            addCriterion("total_price <", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThanOrEqualTo(Integer value) {
            addCriterion("total_price <=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIn(List<Integer> values) {
            addCriterion("total_price in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotIn(List<Integer> values) {
            addCriterion("total_price not in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBetween(Integer value1, Integer value2) {
            addCriterion("total_price between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("total_price not between", value1, value2, "totalPrice");
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

        public Criteria andForwardIsNull() {
            addCriterion("forward is null");
            return (Criteria) this;
        }

        public Criteria andForwardIsNotNull() {
            addCriterion("forward is not null");
            return (Criteria) this;
        }

        public Criteria andForwardEqualTo(String value) {
            addCriterion("forward =", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardNotEqualTo(String value) {
            addCriterion("forward <>", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardGreaterThan(String value) {
            addCriterion("forward >", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardGreaterThanOrEqualTo(String value) {
            addCriterion("forward >=", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardLessThan(String value) {
            addCriterion("forward <", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardLessThanOrEqualTo(String value) {
            addCriterion("forward <=", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardLike(String value) {
            addCriterion("forward like", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardNotLike(String value) {
            addCriterion("forward not like", value, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardIn(List<String> values) {
            addCriterion("forward in", values, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardNotIn(List<String> values) {
            addCriterion("forward not in", values, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardBetween(String value1, String value2) {
            addCriterion("forward between", value1, value2, "forward");
            return (Criteria) this;
        }

        public Criteria andForwardNotBetween(String value1, String value2) {
            addCriterion("forward not between", value1, value2, "forward");
            return (Criteria) this;
        }

        public Criteria andCurFloorIsNull() {
            addCriterion("cur_floor is null");
            return (Criteria) this;
        }

        public Criteria andCurFloorIsNotNull() {
            addCriterion("cur_floor is not null");
            return (Criteria) this;
        }

        public Criteria andCurFloorEqualTo(Integer value) {
            addCriterion("cur_floor =", value, "curFloor");
            return (Criteria) this;
        }

        public Criteria andCurFloorNotEqualTo(Integer value) {
            addCriterion("cur_floor <>", value, "curFloor");
            return (Criteria) this;
        }

        public Criteria andCurFloorGreaterThan(Integer value) {
            addCriterion("cur_floor >", value, "curFloor");
            return (Criteria) this;
        }

        public Criteria andCurFloorGreaterThanOrEqualTo(Integer value) {
            addCriterion("cur_floor >=", value, "curFloor");
            return (Criteria) this;
        }

        public Criteria andCurFloorLessThan(Integer value) {
            addCriterion("cur_floor <", value, "curFloor");
            return (Criteria) this;
        }

        public Criteria andCurFloorLessThanOrEqualTo(Integer value) {
            addCriterion("cur_floor <=", value, "curFloor");
            return (Criteria) this;
        }

        public Criteria andCurFloorIn(List<Integer> values) {
            addCriterion("cur_floor in", values, "curFloor");
            return (Criteria) this;
        }

        public Criteria andCurFloorNotIn(List<Integer> values) {
            addCriterion("cur_floor not in", values, "curFloor");
            return (Criteria) this;
        }

        public Criteria andCurFloorBetween(Integer value1, Integer value2) {
            addCriterion("cur_floor between", value1, value2, "curFloor");
            return (Criteria) this;
        }

        public Criteria andCurFloorNotBetween(Integer value1, Integer value2) {
            addCriterion("cur_floor not between", value1, value2, "curFloor");
            return (Criteria) this;
        }

        public Criteria andTotalFloorIsNull() {
            addCriterion("total_floor is null");
            return (Criteria) this;
        }

        public Criteria andTotalFloorIsNotNull() {
            addCriterion("total_floor is not null");
            return (Criteria) this;
        }

        public Criteria andTotalFloorEqualTo(Integer value) {
            addCriterion("total_floor =", value, "totalFloor");
            return (Criteria) this;
        }

        public Criteria andTotalFloorNotEqualTo(Integer value) {
            addCriterion("total_floor <>", value, "totalFloor");
            return (Criteria) this;
        }

        public Criteria andTotalFloorGreaterThan(Integer value) {
            addCriterion("total_floor >", value, "totalFloor");
            return (Criteria) this;
        }

        public Criteria andTotalFloorGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_floor >=", value, "totalFloor");
            return (Criteria) this;
        }

        public Criteria andTotalFloorLessThan(Integer value) {
            addCriterion("total_floor <", value, "totalFloor");
            return (Criteria) this;
        }

        public Criteria andTotalFloorLessThanOrEqualTo(Integer value) {
            addCriterion("total_floor <=", value, "totalFloor");
            return (Criteria) this;
        }

        public Criteria andTotalFloorIn(List<Integer> values) {
            addCriterion("total_floor in", values, "totalFloor");
            return (Criteria) this;
        }

        public Criteria andTotalFloorNotIn(List<Integer> values) {
            addCriterion("total_floor not in", values, "totalFloor");
            return (Criteria) this;
        }

        public Criteria andTotalFloorBetween(Integer value1, Integer value2) {
            addCriterion("total_floor between", value1, value2, "totalFloor");
            return (Criteria) this;
        }

        public Criteria andTotalFloorNotBetween(Integer value1, Integer value2) {
            addCriterion("total_floor not between", value1, value2, "totalFloor");
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

        public Criteria andOutOfDateIsNull() {
            addCriterion("out_of_date is null");
            return (Criteria) this;
        }

        public Criteria andOutOfDateIsNotNull() {
            addCriterion("out_of_date is not null");
            return (Criteria) this;
        }

        public Criteria andOutOfDateEqualTo(Boolean value) {
            addCriterion("out_of_date =", value, "outOfDate");
            return (Criteria) this;
        }

        public Criteria andOutOfDateNotEqualTo(Boolean value) {
            addCriterion("out_of_date <>", value, "outOfDate");
            return (Criteria) this;
        }

        public Criteria andOutOfDateGreaterThan(Boolean value) {
            addCriterion("out_of_date >", value, "outOfDate");
            return (Criteria) this;
        }

        public Criteria andOutOfDateGreaterThanOrEqualTo(Boolean value) {
            addCriterion("out_of_date >=", value, "outOfDate");
            return (Criteria) this;
        }

        public Criteria andOutOfDateLessThan(Boolean value) {
            addCriterion("out_of_date <", value, "outOfDate");
            return (Criteria) this;
        }

        public Criteria andOutOfDateLessThanOrEqualTo(Boolean value) {
            addCriterion("out_of_date <=", value, "outOfDate");
            return (Criteria) this;
        }

        public Criteria andOutOfDateIn(List<Boolean> values) {
            addCriterion("out_of_date in", values, "outOfDate");
            return (Criteria) this;
        }

        public Criteria andOutOfDateNotIn(List<Boolean> values) {
            addCriterion("out_of_date not in", values, "outOfDate");
            return (Criteria) this;
        }

        public Criteria andOutOfDateBetween(Boolean value1, Boolean value2) {
            addCriterion("out_of_date between", value1, value2, "outOfDate");
            return (Criteria) this;
        }

        public Criteria andOutOfDateNotBetween(Boolean value1, Boolean value2) {
            addCriterion("out_of_date not between", value1, value2, "outOfDate");
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

        public Criteria andResidenceCommunityIdLikeInsensitive(String value) {
            addCriterion("upper(residence_community_id) like", value.toUpperCase(), "residenceCommunityId");
            return (Criteria) this;
        }

        public Criteria andResidenceCommunityNameLikeInsensitive(String value) {
            addCriterion("upper(residence_community_name) like", value.toUpperCase(), "residenceCommunityName");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andRegionLikeInsensitive(String value) {
            addCriterion("upper(region) like", value.toUpperCase(), "region");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLikeInsensitive(String value) {
            addCriterion("upper(phone_num) like", value.toUpperCase(), "phoneNum");
            return (Criteria) this;
        }

        public Criteria andForwardLikeInsensitive(String value) {
            addCriterion("upper(forward) like", value.toUpperCase(), "forward");
            return (Criteria) this;
        }

        public Criteria andDecorationStatusLikeInsensitive(String value) {
            addCriterion("upper(decoration_status) like", value.toUpperCase(), "decorationStatus");
            return (Criteria) this;
        }

        public Criteria andAddressLikeInsensitive(String value) {
            addCriterion("upper(address) like", value.toUpperCase(), "address");
            return (Criteria) this;
        }

        public Criteria andIntroductionLikeInsensitive(String value) {
            addCriterion("upper(introduction) like", value.toUpperCase(), "introduction");
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