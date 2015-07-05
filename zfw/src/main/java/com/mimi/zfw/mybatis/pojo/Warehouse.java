package com.mimi.zfw.mybatis.pojo;

import com.mimi.zfw.plugin.IBaseModel;
import java.util.Date;

public class Warehouse implements IBaseModel<String> {
    private String id;

    private String name;

    private String region;

    private String phoneNum;

    private Integer rental;

    private Integer totalPrice;

    private Float grossFloorArea;

    private String address;

    private String introduction;

    private String rentOrSale;

    private String type;

    private Boolean outOfDate;

    private String description;

    private String tags;

    private Integer priority;

    private String preImageUrl;

    private String creater;

    private String lastEditor;

    private Date createDate;

    private Date updateDate;

    private Boolean delFlag;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public Integer getRental() {
        return rental;
    }

    public void setRental(Integer rental) {
        this.rental = rental;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Float getGrossFloorArea() {
        return grossFloorArea;
    }

    public void setGrossFloorArea(Float grossFloorArea) {
        this.grossFloorArea = grossFloorArea;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getRentOrSale() {
        return rentOrSale;
    }

    public void setRentOrSale(String rentOrSale) {
        this.rentOrSale = rentOrSale == null ? null : rentOrSale.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Boolean getOutOfDate() {
        return outOfDate;
    }

    public void setOutOfDate(Boolean outOfDate) {
        this.outOfDate = outOfDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getPreImageUrl() {
        return preImageUrl;
    }

    public void setPreImageUrl(String preImageUrl) {
        this.preImageUrl = preImageUrl == null ? null : preImageUrl.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getLastEditor() {
        return lastEditor;
    }

    public void setLastEditor(String lastEditor) {
        this.lastEditor = lastEditor == null ? null : lastEditor.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}