package com.mimi.zfw.mybatis.pojo;

import com.mimi.zfw.plugin.IBaseModel;
import java.util.Date;

public class RentalHousing implements IBaseModel<String> {
    private String id;

    private String residenceCommunityId;

    private String residenceCommunityName;

    private String name;

    private String region;

    private String phoneNum;

    private Integer rental;

    private Float grossFloorArea;

    private Integer roomNum;

    private Integer hallNum;

    private Integer toiletNum;

    private String forward;

    private Integer curFloor;

    private Integer totalFloor;

    private String decorationStatus;

    private String address;

    private String introduction;

    private Boolean facilityBed;

    private Boolean facilityRefrigerator;

    private Boolean facilityBroadband;

    private Boolean facilityAirConditioner;

    private Boolean facilityTv;

    private Boolean facilityHeating;

    private Boolean facilityWasher;

    private Boolean facilityHeater;

    private String leaseWay;

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

    public String getResidenceCommunityId() {
        return residenceCommunityId;
    }

    public void setResidenceCommunityId(String residenceCommunityId) {
        this.residenceCommunityId = residenceCommunityId == null ? null : residenceCommunityId.trim();
    }

    public String getResidenceCommunityName() {
        return residenceCommunityName;
    }

    public void setResidenceCommunityName(String residenceCommunityName) {
        this.residenceCommunityName = residenceCommunityName == null ? null : residenceCommunityName.trim();
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

    public Float getGrossFloorArea() {
        return grossFloorArea;
    }

    public void setGrossFloorArea(Float grossFloorArea) {
        this.grossFloorArea = grossFloorArea;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getHallNum() {
        return hallNum;
    }

    public void setHallNum(Integer hallNum) {
        this.hallNum = hallNum;
    }

    public Integer getToiletNum() {
        return toiletNum;
    }

    public void setToiletNum(Integer toiletNum) {
        this.toiletNum = toiletNum;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward == null ? null : forward.trim();
    }

    public Integer getCurFloor() {
        return curFloor;
    }

    public void setCurFloor(Integer curFloor) {
        this.curFloor = curFloor;
    }

    public Integer getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(Integer totalFloor) {
        this.totalFloor = totalFloor;
    }

    public String getDecorationStatus() {
        return decorationStatus;
    }

    public void setDecorationStatus(String decorationStatus) {
        this.decorationStatus = decorationStatus == null ? null : decorationStatus.trim();
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

    public Boolean getFacilityBed() {
        return facilityBed;
    }

    public void setFacilityBed(Boolean facilityBed) {
        this.facilityBed = facilityBed;
    }

    public Boolean getFacilityRefrigerator() {
        return facilityRefrigerator;
    }

    public void setFacilityRefrigerator(Boolean facilityRefrigerator) {
        this.facilityRefrigerator = facilityRefrigerator;
    }

    public Boolean getFacilityBroadband() {
        return facilityBroadband;
    }

    public void setFacilityBroadband(Boolean facilityBroadband) {
        this.facilityBroadband = facilityBroadband;
    }

    public Boolean getFacilityAirConditioner() {
        return facilityAirConditioner;
    }

    public void setFacilityAirConditioner(Boolean facilityAirConditioner) {
        this.facilityAirConditioner = facilityAirConditioner;
    }

    public Boolean getFacilityTv() {
        return facilityTv;
    }

    public void setFacilityTv(Boolean facilityTv) {
        this.facilityTv = facilityTv;
    }

    public Boolean getFacilityHeating() {
        return facilityHeating;
    }

    public void setFacilityHeating(Boolean facilityHeating) {
        this.facilityHeating = facilityHeating;
    }

    public Boolean getFacilityWasher() {
        return facilityWasher;
    }

    public void setFacilityWasher(Boolean facilityWasher) {
        this.facilityWasher = facilityWasher;
    }

    public Boolean getFacilityHeater() {
        return facilityHeater;
    }

    public void setFacilityHeater(Boolean facilityHeater) {
        this.facilityHeater = facilityHeater;
    }

    public String getLeaseWay() {
        return leaseWay;
    }

    public void setLeaseWay(String leaseWay) {
        this.leaseWay = leaseWay == null ? null : leaseWay.trim();
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