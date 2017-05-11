package com.app.qunadai.bean;

/**
 * 注册成功
 * Created by wayne on 2017/5/11.
 */

public class RegBean {

    /**
     * msg : 操作成功
     * code : 000
     * detail : 操作成功
     * content : {"userId":"1ea9849f-5437-4e15-b989-10078ce0e489"}
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
        /**
         * userId : 1ea9849f-5437-4e15-b989-10078ce0e489
         */

        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
