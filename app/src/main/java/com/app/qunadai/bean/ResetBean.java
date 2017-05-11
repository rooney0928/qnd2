package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/11.
 */

public class ResetBean {


    /**
     * msg : 操作成功
     * code : 000
     * detail : 操作成功
     * content : {"user":{"id":"8f00da3d-6548-42fb-9874-32382caa850b","createdTime":1492047201687,"updatedTime":1492047311884,"name":"13162695551","email":null,"avatar":null,"nick":null,"account":{"id":"b1c3cefe-546b-4238-8eda-67e74b99df09","createdTime":1492047201667,"updatedTime":1492047415310,"mobileNumber":"13162695551","password":"newsha1password","verificationCode":"601116"},"status":"NORMAL","lastSMS":1492047290061}}
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
         * user : {"id":"8f00da3d-6548-42fb-9874-32382caa850b","createdTime":1492047201687,"updatedTime":1492047311884,"name":"13162695551","email":null,"avatar":null,"nick":null,"account":{"id":"b1c3cefe-546b-4238-8eda-67e74b99df09","createdTime":1492047201667,"updatedTime":1492047415310,"mobileNumber":"13162695551","password":"newsha1password","verificationCode":"601116"},"status":"NORMAL","lastSMS":1492047290061}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * id : 8f00da3d-6548-42fb-9874-32382caa850b
             * createdTime : 1492047201687
             * updatedTime : 1492047311884
             * name : 13162695551
             * email : null
             * avatar : null
             * nick : null
             * account : {"id":"b1c3cefe-546b-4238-8eda-67e74b99df09","createdTime":1492047201667,"updatedTime":1492047415310,"mobileNumber":"13162695551","password":"newsha1password","verificationCode":"601116"}
             * status : NORMAL
             * lastSMS : 1492047290061
             */

            private String id;
            private long createdTime;
            private long updatedTime;
            private String name;
            private Object email;
            private Object avatar;
            private Object nick;
            private AccountBean account;
            private String status;
            private long lastSMS;

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

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public Object getAvatar() {
                return avatar;
            }

            public void setAvatar(Object avatar) {
                this.avatar = avatar;
            }

            public Object getNick() {
                return nick;
            }

            public void setNick(Object nick) {
                this.nick = nick;
            }

            public AccountBean getAccount() {
                return account;
            }

            public void setAccount(AccountBean account) {
                this.account = account;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public long getLastSMS() {
                return lastSMS;
            }

            public void setLastSMS(long lastSMS) {
                this.lastSMS = lastSMS;
            }

            public static class AccountBean {
                /**
                 * id : b1c3cefe-546b-4238-8eda-67e74b99df09
                 * createdTime : 1492047201667
                 * updatedTime : 1492047415310
                 * mobileNumber : 13162695551
                 * password : newsha1password
                 * verificationCode : 601116
                 */

                private String id;
                private long createdTime;
                private long updatedTime;
                private String mobileNumber;
                private String password;
                private String verificationCode;

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

                public String getMobileNumber() {
                    return mobileNumber;
                }

                public void setMobileNumber(String mobileNumber) {
                    this.mobileNumber = mobileNumber;
                }

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public String getVerificationCode() {
                    return verificationCode;
                }

                public void setVerificationCode(String verificationCode) {
                    this.verificationCode = verificationCode;
                }
            }
        }
    }
}
