package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/11.
 */

public class Message {


    /**
     * msg : 创建成功
     * code : 000
     * detail : CToken was created.
     * content : {"mobileNumber":"13162695559","userId":"e8131be4-5afd-4b91-b400-de8a170ff591","message":"短信已发送"}
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
         * mobileNumber : 13162695559
         * userId : e8131be4-5afd-4b91-b400-de8a170ff591
         * message : 短信已发送
         */

        private String mobileNumber;
        private String userId;
        private String message;

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
