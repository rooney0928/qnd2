package com.app.qunadai.bean.v5;

/**
 * Created by wayne on 2017/9/5.
 */

public class RoomBean {

    /**
     * id : 7dc073be-7e13-4a66-9940-e0c28c6dd0c9
     * createdTime : 1504594097000
     * updatedTime : 1504594097000
     * floorId : ece9a1c9-5f4d-495a-b4f2-584ecd45c56a
     * contentMappingId : 950ab76a-88b8-4e49-9c6d-711ba7f6b56e
     * contentName : 小花钱包
     * contentImg : 87aab827-ea33-4188-b1de-af553df6dabd
     */

    private String id;
    private long createdTime;
    private long updatedTime;
    private String floorId;
    private String contentMappingId;
    private String contentName;
    private String contentImg;
    private String bannerMode;
    private String contentUrl;

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getBannerMode() {
        return bannerMode;
    }

    public void setBannerMode(String bannerMode) {
        this.bannerMode = bannerMode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getContentMappingId() {
        return contentMappingId;
    }

    public void setContentMappingId(String contentMappingId) {
        this.contentMappingId = contentMappingId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentImg() {
        return contentImg;
    }

    public void setContentImg(String contentImg) {
        this.contentImg = contentImg;
    }
}
