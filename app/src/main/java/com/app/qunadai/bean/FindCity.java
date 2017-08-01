package com.app.qunadai.bean;

import java.util.List;

/**
 * Created by wayne on 2017/8/1.
 */

public class FindCity {

    /**
     * msg : 成功
     * code : 000
     * detail : 成功
     * content : {"districtList":[{"id":"150900","name":"乌兰察布市","shortName":"乌兰察布","levelType":2,"cityCode":"0474","zipCode":"012000","mergeName":"中国,内蒙古自治区,乌兰察布市","parentId":150000,"lng":"113.114543","lat":"41.034126","pingyin":"Ulanqab","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0},{"id":"650100","name":"乌鲁木齐市","shortName":"乌鲁木齐","levelType":2,"cityCode":"0991","zipCode":"830002","mergeName":"中国,新疆维吾尔自治区,乌鲁木齐市","parentId":650000,"lng":"87.617733","lat":"43.792818","pingyin":"Urumqi","memo":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"delFlag":0,"version":0}]}
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

    }
}
