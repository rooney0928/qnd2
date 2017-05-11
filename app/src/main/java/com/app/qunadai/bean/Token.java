package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/11.
 */

public class Token {

    /**
     * msg : 创建成功
     * code : 000
     * detail : Token was created.
     * content : {"access_token":"690f059a-88a4-4a21-817c-282cdf4eeb8f","uid":"7d933cdb-39e3-42e5-a028-bedf9d1579ac","userStatus":"NORMAL","mobileNumber":"13162695551"}
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
         * access_token : 690f059a-88a4-4a21-817c-282cdf4eeb8f
         * uid : 7d933cdb-39e3-42e5-a028-bedf9d1579ac
         * userStatus : NORMAL
         * mobileNumber : 13162695551
         */

        private String access_token;
        private String uid;
        private String userStatus;
        private String mobileNumber;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(String userStatus) {
            this.userStatus = userStatus;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }
    }
}
