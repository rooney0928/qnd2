package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/14.
 */

public class BankcardBean {

    /**
     * msg : 操作成功
     * code : 000
     * detail : 操作成功
     * content : {"requirement":{"id":"6bebd0a4-a6e1-4cd0-bf31-4432147d554e","createdTime":1491975235958,"updatedTime":1491975235958,"user":null,"name":"彼得潘","bankCardNumber":"123123123123123","mobileNumber":"13162695559","idNumber":"310100000000000000"}}
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
         * requirement : {"id":"6bebd0a4-a6e1-4cd0-bf31-4432147d554e","createdTime":1491975235958,"updatedTime":1491975235958,"user":null,"name":"彼得潘","bankCardNumber":"123123123123123","mobileNumber":"13162695559","idNumber":"310100000000000000"}
         */

        private RequirementBean requirement;

        public RequirementBean getRequirement() {
            return requirement;
        }

        public void setRequirement(RequirementBean requirement) {
            this.requirement = requirement;
        }

        public static class RequirementBean {
            /**
             * id : 6bebd0a4-a6e1-4cd0-bf31-4432147d554e
             * createdTime : 1491975235958
             * updatedTime : 1491975235958
             * user : null
             * name : 彼得潘
             * bankCardNumber : 123123123123123
             * mobileNumber : 13162695559
             * idNumber : 310100000000000000
             */

            private String id;
            private long createdTime;
            private long updatedTime;
            private Object user;
            private String name;
            private String bankCardNumber;
            private String mobileNumber;
            private String idNumber;

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

            public Object getUser() {
                return user;
            }

            public void setUser(Object user) {
                this.user = user;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBankCardNumber() {
                return bankCardNumber;
            }

            public void setBankCardNumber(String bankCardNumber) {
                this.bankCardNumber = bankCardNumber;
            }

            public String getMobileNumber() {
                return mobileNumber;
            }

            public void setMobileNumber(String mobileNumber) {
                this.mobileNumber = mobileNumber;
            }

            public String getIdNumber() {
                return idNumber;
            }

            public void setIdNumber(String idNumber) {
                this.idNumber = idNumber;
            }
        }
    }
}
