package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/7/11.
 */

public class NickBean {


    /**
     * code : 000
     * content : {"user":"保存成功"}
     * detail : 操作成功
     * msg : 操作成功
     */

    private String code;
    private ContentBean content;
    private String detail;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ContentBean {
        /**
         * user : 保存成功
         */

        private String user;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }
}
