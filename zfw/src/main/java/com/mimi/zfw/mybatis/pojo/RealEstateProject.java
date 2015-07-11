package com.mimi.zfw.mybatis.pojo;

import com.mimi.zfw.plugin.IBaseModel;
import java.util.Date;

public class RealEstateProject implements IBaseModel<String> {
    private String id;

    private String name;

    private String address;

    private String tel;

    private Date onSaleDate;

    private Date onReadyDate;

    private String propertyType;

    private String buildingType;

    private String decorationStatus;

    private Integer householdNum;

    private Float floorAreaRatio;

    private Float greenRate;

    private Integer parkingSpaceNum;

    private Integer propertyYears;

    private String developer;

    private String preSalePermit;

    private String propertyCompany;

    private Float propertyFee;

    private String introduction;

    private String surrounding;

    private String traffic;

    private String region;

    private Float longitude;

    private Float latitude;

    private Integer averagePrice;

    private String tags;

    private Integer priority;

    private String preImageUrl;

    private Integer oneRoomNum;

    private Integer twoRoomNum;

    private Integer threeRoomNum;

    private Integer fourRoomNum;

    private Integer fiveRoomNum;

    private Integer overFiveRoomNum;

    private Integer minRoomGrossFloorArea;

    private Integer maxRoomGrossFloorArea;

    private String saleStatus;

    private String creater;

    private String lastEditor;

    private Date createDate;

    private Date updateDate;

    private Boolean delFlag;

    private String description;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Date getOnSaleDate() {
        return onSaleDate;
    }

    public void setOnSaleDate(Date onSaleDate) {
        this.onSaleDate = onSaleDate;
    }

    public Date getOnReadyDate() {
        return onReadyDate;
    }

    public void setOnReadyDate(Date onReadyDate) {
        this.onReadyDate = onReadyDate;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType == null ? null : propertyType.trim();
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType == null ? null : buildingType.trim();
    }

    public String getDecorationStatus() {
        return decorationStatus;
    }

    public void setDecorationStatus(String decorationStatus) {
        this.decorationStatus = decorationStatus == null ? null : decorationStatus.trim();
    }

    public Integer getHouseholdNum() {
        return householdNum;
    }

    public void setHouseholdNum(Integer householdNum) {
        this.householdNum = householdNum;
    }

    public Float getFloorAreaRatio() {
        return floorAreaRatio;
    }

    public void setFloorAreaRatio(Float floorAreaRatio) {
        this.floorAreaRatio = floorAreaRatio;
    }

    public Float getGreenRate() {
        return greenRate;
    }

    public void setGreenRate(Float greenRate) {
        this.greenRate = greenRate;
    }

    public Integer getParkingSpaceNum() {
        return parkingSpaceNum;
    }

    public void setParkingSpaceNum(Integer parkingSpaceNum) {
        this.parkingSpaceNum = parkingSpaceNum;
    }

    public Integer getPropertyYears() {
        return propertyYears;
    }

    public void setPropertyYears(Integer propertyYears) {
        this.propertyYears = propertyYears;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer == null ? null : developer.trim();
    }

    public String getPreSalePermit() {
        return preSalePermit;
    }

    public void setPreSalePermit(String preSalePermit) {
        this.preSalePermit = preSalePermit == null ? null : preSalePermit.trim();
    }

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public void setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany == null ? null : propertyCompany.trim();
    }

    public Float getPropertyFee() {
        return propertyFee;
    }

    public void setPropertyFee(Float propertyFee) {
        this.propertyFee = propertyFee;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getSurrounding() {
        return surrounding;
    }

    public void setSurrounding(String surrounding) {
        this.surrounding = surrounding == null ? null : surrounding.trim();
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic == null ? null : traffic.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Integer getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Integer averagePrice) {
        this.averagePrice = averagePrice;
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

    public Integer getOneRoomNum() {
        return oneRoomNum;
    }

    public void setOneRoomNum(Integer oneRoomNum) {
        this.oneRoomNum = oneRoomNum;
    }

    public Integer getTwoRoomNum() {
        return twoRoomNum;
    }

    public void setTwoRoomNum(Integer twoRoomNum) {
        this.twoRoomNum = twoRoomNum;
    }

    public Integer getThreeRoomNum() {
        return threeRoomNum;
    }

    public void setThreeRoomNum(Integer threeRoomNum) {
        this.threeRoomNum = threeRoomNum;
    }

    public Integer getFourRoomNum() {
        return fourRoomNum;
    }

    public void setFourRoomNum(Integer fourRoomNum) {
        this.fourRoomNum = fourRoomNum;
    }

    public Integer getFiveRoomNum() {
        return fiveRoomNum;
    }

    public void setFiveRoomNum(Integer fiveRoomNum) {
        this.fiveRoomNum = fiveRoomNum;
    }

    public Integer getOverFiveRoomNum() {
        return overFiveRoomNum;
    }

    public void setOverFiveRoomNum(Integer overFiveRoomNum) {
        this.overFiveRoomNum = overFiveRoomNum;
    }

    public Integer getMinRoomGrossFloorArea() {
        return minRoomGrossFloorArea;
    }

    public void setMinRoomGrossFloorArea(Integer minRoomGrossFloorArea) {
        this.minRoomGrossFloorArea = minRoomGrossFloorArea;
    }

    public Integer getMaxRoomGrossFloorArea() {
        return maxRoomGrossFloorArea;
    }

    public void setMaxRoomGrossFloorArea(Integer maxRoomGrossFloorArea) {
        this.maxRoomGrossFloorArea = maxRoomGrossFloorArea;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus == null ? null : saleStatus.trim();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}