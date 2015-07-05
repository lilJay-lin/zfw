package com.mimi.zfw.mybatis.pojo;

import com.mimi.zfw.plugin.IBaseModel;
import java.util.Date;

public class SHHFloorPriceLinearFunction implements IBaseModel<String> {
    private String id;

    private String residenceCommunityId;

    private Double paramA;

    private Double paramB;

    private String forward;

    private String description;

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

    public Double getParamA() {
        return paramA;
    }

    public void setParamA(Double paramA) {
        this.paramA = paramA;
    }

    public Double getParamB() {
        return paramB;
    }

    public void setParamB(Double paramB) {
        this.paramB = paramB;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward == null ? null : forward.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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