package com.app.qunadai.bean.bbs;

import com.app.qunadai.bean.City;

import java.util.List;

/**
 * Created by wayne on 2017/7/28.
 */

public class HotCity {

    /**
     * msg : 成功
     * code : 000
     * detail : 成功
     * content : {"districtList":[{"id":"110100","name":"北京市","shortName":"北京","levelType":2,"cityCode":"010","zipCode":"100000","mergeName":"中国,北京,北京市","parentId":110000,"lng":"116.405285","lat":"39.904989","pingyin":"Beijing","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0},{"id":"310100","name":"上海市","shortName":"上海","levelType":2,"cityCode":"021","zipCode":"200000","mergeName":"中国,上海,上海市","parentId":310000,"lng":"121.472644","lat":"31.231706","pingyin":"Shanghai","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0},{"id":"440100","name":"广州市","shortName":"广州","levelType":2,"cityCode":"020","zipCode":"510032","mergeName":"中国,广东省,广州市","parentId":440000,"lng":"113.280637","lat":"23.125178","pingyin":"Guangzhou","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0},{"id":"440300","name":"深圳市","shortName":"深圳","levelType":2,"cityCode":"0755","zipCode":"518035","mergeName":"中国,广东省,深圳市","parentId":440000,"lng":"114.085947","lat":"22.547","pingyin":"Shenzhen","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0},{"id":"510100","name":"成都市","shortName":"成都","levelType":2,"cityCode":"028","zipCode":"610015","mergeName":"中国,四川省,成都市","parentId":510000,"lng":"104.065735","lat":"30.659462","pingyin":"Chengdu","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0},{"id":"330100","name":"杭州市","shortName":"杭州","levelType":2,"cityCode":"0571","zipCode":"310026","mergeName":"中国,浙江省,杭州市","parentId":330000,"lng":"120.153576","lat":"30.287459","pingyin":"Hangzhou","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0},{"id":"320100","name":"南京市","shortName":"南京","levelType":2,"cityCode":"025","zipCode":"210008","mergeName":"中国,江苏省,南京市","parentId":320000,"lng":"118.767413","lat":"32.041544","pingyin":"Nanjing","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0},{"id":"420100","name":"武汉市","shortName":"武汉","levelType":2,"cityCode":"","zipCode":"430014","mergeName":"中国,湖北省,武汉市","parentId":420000,"lng":"114.298572","lat":"30.584355","pingyin":"Wuhan","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0},{"id":"500100","name":"重庆市","shortName":"重庆","levelType":2,"cityCode":"023","zipCode":"400000","mergeName":"中国,重庆,重庆市","parentId":500000,"lng":"106.504962","lat":"29.533155","pingyin":"Chongqing","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0},{"id":"370200","name":"青岛市","shortName":"青岛","levelType":2,"cityCode":"0532","zipCode":"266001","mergeName":"中国,山东省,青岛市","parentId":370000,"lng":"120.369557","lat":"36.094406","pingyin":"Qingdao","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0},{"id":"120100","name":"天津市","shortName":"天津","levelType":2,"cityCode":"022","zipCode":"300000","mergeName":"中国,天津,天津市","parentId":120000,"lng":"117.190182","lat":"39.125596","pingyin":"Tianjin","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0},{"id":"330300","name":"温州市","shortName":"温州","levelType":2,"cityCode":"0577","zipCode":"325000","mergeName":"中国,浙江省,温州市","parentId":330000,"lng":"120.672111","lat":"28.000575","pingyin":"Wenzhou","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0}]}
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
        private List<City> districtList;

        public List<City> getDistrictList() {
            return districtList;
        }

        public void setDistrictList(List<City> districtList) {
            this.districtList = districtList;
        }

    }
}
