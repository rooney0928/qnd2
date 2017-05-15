package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/15.
 */

public class AvatarBean {


    /**
     * msg : 操作成功
     * code : 000
     * detail : 操作成功
     * content : {"user":{"id":"1ea9849f-5437-4e15-b989-10078ce0e489","createdTime":1494511923000,"updatedTime":1494829339247,"avatar":"c1f30498-0a23-4d5c-b007-bd97438b2737","nick":"15000983438","account":{"id":"a3b22e70-6105-496e-8a77-3db1d23ae4de","createdTime":1494511923000,"updatedTime":1494518563000,"mobileNumber":"15000983438","password":"273a0c7bd3c679ba9a6f5d99078e36e85d02b952","verificationCode":"606535"},"status":"NORMAL","lastSMS":1494518563000}}
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
         * user : {"id":"1ea9849f-5437-4e15-b989-10078ce0e489","createdTime":1494511923000,"updatedTime":1494829339247,"avatar":"c1f30498-0a23-4d5c-b007-bd97438b2737","nick":"15000983438","account":{"id":"a3b22e70-6105-496e-8a77-3db1d23ae4de","createdTime":1494511923000,"updatedTime":1494518563000,"mobileNumber":"15000983438","password":"273a0c7bd3c679ba9a6f5d99078e36e85d02b952","verificationCode":"606535"},"status":"NORMAL","lastSMS":1494518563000}
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
             * id : 1ea9849f-5437-4e15-b989-10078ce0e489
             * createdTime : 1494511923000
             * updatedTime : 1494829339247
             * avatar : c1f30498-0a23-4d5c-b007-bd97438b2737
             * nick : 15000983438
             * account : {"id":"a3b22e70-6105-496e-8a77-3db1d23ae4de","createdTime":1494511923000,"updatedTime":1494518563000,"mobileNumber":"15000983438","password":"273a0c7bd3c679ba9a6f5d99078e36e85d02b952","verificationCode":"606535"}
             * status : NORMAL
             * lastSMS : 1494518563000
             */

            private String id;
            private long createdTime;
            private long updatedTime;
            private String avatar;
            private String nick;
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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
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
                 * id : a3b22e70-6105-496e-8a77-3db1d23ae4de
                 * createdTime : 1494511923000
                 * updatedTime : 1494518563000
                 * mobileNumber : 15000983438
                 * password : 273a0c7bd3c679ba9a6f5d99078e36e85d02b952
                 * verificationCode : 606535
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
