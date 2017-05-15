package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/15.
 */

public class ApplyBean {

    /**
     * msg : 成功
     * code : 000
     * detail : 成功
     * content : {"loanOrderId":"c715dadd-1d9c-4896-8a97-1e9374f3156f"}
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
         * loanOrderId : c715dadd-1d9c-4896-8a97-1e9374f3156f
         */

        private String loanOrderId;

        public String getLoanOrderId() {
            return loanOrderId;
        }

        public void setLoanOrderId(String loanOrderId) {
            this.loanOrderId = loanOrderId;
        }
    }
}
