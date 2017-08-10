package com.app.qunadai.bean;

import java.util.List;

/**
 * Created by wayne on 2017/8/8.
 */

public class BannerBean {

    /**
     * msg : 操作成功
     * code : 000
     * detail : 操作成功
     * content : {"availableBanners":[{"id":"fb3d1944-df03-4dc1-80cf-5b42a16e66f7","createdTime":1501230140000,"updatedTime":1501834783000,"name":"发薪贷活动A","startTime":1501084800000,"endTime":1504108800000,"position":1,"bannerPic":"e739dbd1-435c-438b-9cab-d11879503339","bannerUrl":"https://hft.qunadai.com/html/index/details.html?pid=c1af68f4-adc7-431d-8faf-3916778ce11f","bannerType":"APPLICATION","bannerMode":"INTERNAL","browseAmount":14,"status":"NORMAL","targetInfo":{"id":"c1af68f4-adc7-431d-8faf-3916778ce11f","type":"product"}},{"id":"0cd2c8ff-4d01-486a-b9bb-39a0dbe57eed","createdTime":1501230244000,"updatedTime":1501731598000,"name":"发薪贷活动B","startTime":1501171200000,"endTime":1504108800000,"position":2,"bannerPic":"0417b92e-cace-4f53-9318-e8a8f7c99a90","bannerUrl":"https://www.baidu.com","bannerType":"APPLICATION","bannerMode":"EXTERNAL","browseAmount":13,"status":"NORMAL","targetInfo":{}}]}
     */

    private String msg;
    private String code;
    private String detail;
    private ContentBean content;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        private List<Banner> availableBanners;

        public List<Banner> getAvailableBanners() {
            return availableBanners;
        }

        public void setAvailableBanners(List<Banner> availableBanners) {
            this.availableBanners = availableBanners;
        }

    }
}
