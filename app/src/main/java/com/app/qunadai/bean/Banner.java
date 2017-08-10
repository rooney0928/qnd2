package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/8/8.
 */

public class Banner {

    /**
     * id : fb3d1944-df03-4dc1-80cf-5b42a16e66f7
     * createdTime : 1501230140000
     * updatedTime : 1501834783000
     * name : 发薪贷活动A
     * startTime : 1501084800000
     * endTime : 1504108800000
     * position : 1
     * bannerPic : e739dbd1-435c-438b-9cab-d11879503339
     * bannerUrl : https://hft.qunadai.com/html/index/details.html?pid=c1af68f4-adc7-431d-8faf-3916778ce11f
     * bannerType : APPLICATION
     * bannerMode : INTERNAL
     * browseAmount : 14
     * status : NORMAL
     * targetInfo : {"id":"c1af68f4-adc7-431d-8faf-3916778ce11f","type":"product"}
     */

    private String id;
    private long createdTime;
    private long updatedTime;
    private String name;
    private long startTime;
    private long endTime;
    private int position;
    private String bannerPic;
    private String bannerUrl;
    private String bannerType;
    private String bannerMode;
    private int browseAmount;
    private String status;
    private TargetInfoBean targetInfo;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getBannerPic() {
        return bannerPic;
    }

    public void setBannerPic(String bannerPic) {
        this.bannerPic = bannerPic;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public String getBannerMode() {
        return bannerMode;
    }

    public void setBannerMode(String bannerMode) {
        this.bannerMode = bannerMode;
    }

    public int getBrowseAmount() {
        return browseAmount;
    }

    public void setBrowseAmount(int browseAmount) {
        this.browseAmount = browseAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TargetInfoBean getTargetInfo() {
        return targetInfo;
    }

    public void setTargetInfo(TargetInfoBean targetInfo) {
        this.targetInfo = targetInfo;
    }

    public static class TargetInfoBean {
        /**
         * id : c1af68f4-adc7-431d-8faf-3916778ce11f
         * type : product
         */

        private String id;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
