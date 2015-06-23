package com.mimi.zfw.mybatis.pojo;

import com.mimi.zfw.plugin.IBaseModel;
import java.util.Date;

public class ResidenceCommunity implements IBaseModel<String> {
    private String id;

    private String name;

    private String address;

    private Date onSaleDate;

    private String propertyType;

    private String buildingType;

    private Integer householdNum;

    private Float floorAreaRatio;

    private Float greenRate;

    private Integer parkingSpaceNum;

    private Integer propertyYears;

    private String propertyCompany;

    private Float propertyFee;

    private String introduction;

    private String surrounding;

    private String traffic;

    private String region;

    private Float longitude;

    private Float latitude;

    private Integer shhAveragePrice;

    private Integer shhNum;

    private Integer shhOneRoomNum;

    private Integer shhTwoRoomNum;

    private Integer shhThreeRoomNum;

    private Integer shhFourRoomNum;

    private Integer shhFiveRoomNum;

    private Integer shhOverFiveRoomNum;

    private Integer shhMinRoomGrossFloorArea;

    private Integer shhMaxRoomGrossFloorArea;

    private Integer shhMinTotalPrice;

    private Integer shhMaxTotalPrice;

    private Integer rhAveragePrice;

    private Integer rhNum;

    private Integer rhOneRoomNum;

    private Integer rhTwoRoomNum;

    private Integer rhThreeRoomNum;

    private Integer rhFourRoomNum;

    private Integer rhFiveRoomNum;

    private Integer rhOverFiveRoomNum;

    private Integer rhMinRoomGrossFloorArea;

    private Integer rhMaxRoomGrossFloorArea;

    private Integer rhMinRental;

    private Integer rhMaxRental;

    private Integer rhEntireRentNum;

    private Integer rhFlatShareNum;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getOnSaleDate() {
        return onSaleDate;
    }

    public void setOnSaleDate(Date onSaleDate) {
        this.onSaleDate = onSaleDate;
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

    public Integer getShhAveragePrice() {
        return shhAveragePrice;
    }

    public void setShhAveragePrice(Integer shhAveragePrice) {
        this.shhAveragePrice = shhAveragePrice;
    }

    public Integer getShhNum() {
        return shhNum;
    }

    public void setShhNum(Integer shhNum) {
        this.shhNum = shhNum;
    }

    public Integer getShhOneRoomNum() {
        return shhOneRoomNum;
    }

    public void setShhOneRoomNum(Integer shhOneRoomNum) {
        this.shhOneRoomNum = shhOneRoomNum;
    }

    public Integer getShhTwoRoomNum() {
        return shhTwoRoomNum;
    }

    public void setShhTwoRoomNum(Integer shhTwoRoomNum) {
        this.shhTwoRoomNum = shhTwoRoomNum;
    }

    public Integer getShhThreeRoomNum() {
        return shhThreeRoomNum;
    }

    public void setShhThreeRoomNum(Integer shhThreeRoomNum) {
        this.shhThreeRoomNum = shhThreeRoomNum;
    }

    public Integer getShhFourRoomNum() {
        return shhFourRoomNum;
    }

    public void setShhFourRoomNum(Integer shhFourRoomNum) {
        this.shhFourRoomNum = shhFourRoomNum;
    }

    public Integer getShhFiveRoomNum() {
        return shhFiveRoomNum;
    }

    public void setShhFiveRoomNum(Integer shhFiveRoomNum) {
        this.shhFiveRoomNum = shhFiveRoomNum;
    }

    public Integer getShhOverFiveRoomNum() {
        return shhOverFiveRoomNum;
    }

    public void setShhOverFiveRoomNum(Integer shhOverFiveRoomNum) {
        this.shhOverFiveRoomNum = shhOverFiveRoomNum;
    }

    public Integer getShhMinRoomGrossFloorArea() {
        return shhMinRoomGrossFloorArea;
    }

    public void setShhMinRoomGrossFloorArea(Integer shhMinRoomGrossFloorArea) {
        this.shhMinRoomGrossFloorArea = shhMinRoomGrossFloorArea;
    }

    public Integer getShhMaxRoomGrossFloorArea() {
        return shhMaxRoomGrossFloorArea;
    }

    public void setShhMaxRoomGrossFloorArea(Integer shhMaxRoomGrossFloorArea) {
        this.shhMaxRoomGrossFloorArea = shhMaxRoomGrossFloorArea;
    }

    public Integer getShhMinTotalPrice() {
        return shhMinTotalPrice;
    }

    public void setShhMinTotalPrice(Integer shhMinTotalPrice) {
        this.shhMinTotalPrice = shhMinTotalPrice;
    }

    public Integer getShhMaxTotalPrice() {
        return shhMaxTotalPrice;
    }

    public void setShhMaxTotalPrice(Integer shhMaxTotalPrice) {
        this.shhMaxTotalPrice = shhMaxTotalPrice;
    }

    public Integer getRhAveragePrice() {
        return rhAveragePrice;
    }

    public void setRhAveragePrice(Integer rhAveragePrice) {
        this.rhAveragePrice = rhAveragePrice;
    }

    public Integer getRhNum() {
        return rhNum;
    }

    public void setRhNum(Integer rhNum) {
        this.rhNum = rhNum;
    }

    public Integer getRhOneRoomNum() {
        return rhOneRoomNum;
    }

    public void setRhOneRoomNum(Integer rhOneRoomNum) {
        this.rhOneRoomNum = rhOneRoomNum;
    }

    public Integer getRhTwoRoomNum() {
        return rhTwoRoomNum;
    }

    public void setRhTwoRoomNum(Integer rhTwoRoomNum) {
        this.rhTwoRoomNum = rhTwoRoomNum;
    }

    public Integer getRhThreeRoomNum() {
        return rhThreeRoomNum;
    }

    public void setRhThreeRoomNum(Integer rhThreeRoomNum) {
        this.rhThreeRoomNum = rhThreeRoomNum;
    }

    public Integer getRhFourRoomNum() {
        return rhFourRoomNum;
    }

    public void setRhFourRoomNum(Integer rhFourRoomNum) {
        this.rhFourRoomNum = rhFourRoomNum;
    }

    public Integer getRhFiveRoomNum() {
        return rhFiveRoomNum;
    }

    public void setRhFiveRoomNum(Integer rhFiveRoomNum) {
        this.rhFiveRoomNum = rhFiveRoomNum;
    }

    public Integer getRhOverFiveRoomNum() {
        return rhOverFiveRoomNum;
    }

    public void setRhOverFiveRoomNum(Integer rhOverFiveRoomNum) {
        this.rhOverFiveRoomNum = rhOverFiveRoomNum;
    }

    public Integer getRhMinRoomGrossFloorArea() {
        return rhMinRoomGrossFloorArea;
    }

    public void setRhMinRoomGrossFloorArea(Integer rhMinRoomGrossFloorArea) {
        this.rhMinRoomGrossFloorArea = rhMinRoomGrossFloorArea;
    }

    public Integer getRhMaxRoomGrossFloorArea() {
        return rhMaxRoomGrossFloorArea;
    }

    public void setRhMaxRoomGrossFloorArea(Integer rhMaxRoomGrossFloorArea) {
        this.rhMaxRoomGrossFloorArea = rhMaxRoomGrossFloorArea;
    }

    public Integer getRhMinRental() {
        return rhMinRental;
    }

    public void setRhMinRental(Integer rhMinRental) {
        this.rhMinRental = rhMinRental;
    }

    public Integer getRhMaxRental() {
        return rhMaxRental;
    }

    public void setRhMaxRental(Integer rhMaxRental) {
        this.rhMaxRental = rhMaxRental;
    }

    public Integer getRhEntireRentNum() {
        return rhEntireRentNum;
    }

    public void setRhEntireRentNum(Integer rhEntireRentNum) {
        this.rhEntireRentNum = rhEntireRentNum;
    }

    public Integer getRhFlatShareNum() {
        return rhFlatShareNum;
    }

    public void setRhFlatShareNum(Integer rhFlatShareNum) {
        this.rhFlatShareNum = rhFlatShareNum;
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