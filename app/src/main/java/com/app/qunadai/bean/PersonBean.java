package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/12.
 */

public class PersonBean {

    /**
     * msg : 操作成功
     * code : 000
     * detail : 操作成功
     * content : {"personalValue":{"id":"8f25c641-b847-4164-a81c-ca9d2061515e","createdTime":1493544112000,"updatedTime":1499407097000,"user":{"id":"ae6b0df0-1db0-49f9-b1f9-d3af053f65f6","createdTime":1493696630000,"updatedTime":1508920188000,"avatar":"6e60a833-a287-43e2-a246-3c72f9d49a74","nick":"汝等小儿，可敢杀我","account":{"id":"d3b76361-c2be-40b0-b28d-94ac68e6a228","createdTime":1493544026000,"updatedTime":1506752870000,"mobileNumber":"15000983434","password":"3e35dbffd6b900e0033313643d78d9010cf0a312","verificationCode":"9168"},"status":"NORMAL","lastSMS":1506752870000,"lastLoginTime":1508920188000,"imei":"867368021787513","userValidity":"VALID","browsedLatestProducts":false},"tips":"快去贷款吧!","valuation":17000,"balance":10000,"status":"CREATED","bankStatus":"SUCCESS","realInfoStatus":"SUCCESS","ebankStatus":"CREATED","zmxyStatus":"CREATED","operatorStatus":"CREATED","alipayStatus":"CREATED","emailStatus":"CREATED","fundStatus":"CREATED","zxStatus":"CREATED","taobaoStatus":"PROCESSING"}}
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
         * personalValue : {"id":"8f25c641-b847-4164-a81c-ca9d2061515e","createdTime":1493544112000,"updatedTime":1499407097000,"user":{"id":"ae6b0df0-1db0-49f9-b1f9-d3af053f65f6","createdTime":1493696630000,"updatedTime":1508920188000,"avatar":"6e60a833-a287-43e2-a246-3c72f9d49a74","nick":"汝等小儿，可敢杀我","account":{"id":"d3b76361-c2be-40b0-b28d-94ac68e6a228","createdTime":1493544026000,"updatedTime":1506752870000,"mobileNumber":"15000983434","password":"3e35dbffd6b900e0033313643d78d9010cf0a312","verificationCode":"9168"},"status":"NORMAL","lastSMS":1506752870000,"lastLoginTime":1508920188000,"imei":"867368021787513","userValidity":"VALID","browsedLatestProducts":false},"tips":"快去贷款吧!","valuation":17000,"balance":10000,"status":"CREATED","bankStatus":"SUCCESS","realInfoStatus":"SUCCESS","ebankStatus":"CREATED","zmxyStatus":"CREATED","operatorStatus":"CREATED","alipayStatus":"CREATED","emailStatus":"CREATED","fundStatus":"CREATED","zxStatus":"CREATED","taobaoStatus":"PROCESSING"}
         */

        private PersonalValueBean personalValue;

        public PersonalValueBean getPersonalValue() {
            return personalValue;
        }

        public void setPersonalValue(PersonalValueBean personalValue) {
            this.personalValue = personalValue;
        }

        public static class PersonalValueBean {
            /**
             * id : 8f25c641-b847-4164-a81c-ca9d2061515e
             * createdTime : 1493544112000
             * updatedTime : 1499407097000
             * user : {"id":"ae6b0df0-1db0-49f9-b1f9-d3af053f65f6","createdTime":1493696630000,"updatedTime":1508920188000,"avatar":"6e60a833-a287-43e2-a246-3c72f9d49a74","nick":"汝等小儿，可敢杀我","account":{"id":"d3b76361-c2be-40b0-b28d-94ac68e6a228","createdTime":1493544026000,"updatedTime":1506752870000,"mobileNumber":"15000983434","password":"3e35dbffd6b900e0033313643d78d9010cf0a312","verificationCode":"9168"},"status":"NORMAL","lastSMS":1506752870000,"lastLoginTime":1508920188000,"imei":"867368021787513","userValidity":"VALID","browsedLatestProducts":false}
             * tips : 快去贷款吧!
             * valuation : 17000
             * balance : 10000
             * status : CREATED
             * bankStatus : SUCCESS
             * realInfoStatus : SUCCESS
             * ebankStatus : CREATED
             * zmxyStatus : CREATED
             * operatorStatus : CREATED
             * alipayStatus : CREATED
             * emailStatus : CREATED
             * fundStatus : CREATED
             * zxStatus : CREATED
             * taobaoStatus : PROCESSING
             */

            private String id;
            private long createdTime;
            private long updatedTime;
            private UserBean user;
            private String tips;
            private int valuation;
            private int balance;
            private String status;
            private String bankStatus;
            private String realInfoStatus;
            private String ebankStatus;
            private String zmxyStatus;
            private String operatorStatus;
            private String alipayStatus;
            private String emailStatus;
            private String fundStatus;
            private String zxStatus;
            private String taobaoStatus;

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

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getTips() {
                return tips;
            }

            public void setTips(String tips) {
                this.tips = tips;
            }

            public int getValuation() {
                return valuation;
            }

            public void setValuation(int valuation) {
                this.valuation = valuation;
            }

            public int getBalance() {
                return balance;
            }

            public void setBalance(int balance) {
                this.balance = balance;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getBankStatus() {
                return bankStatus;
            }

            public void setBankStatus(String bankStatus) {
                this.bankStatus = bankStatus;
            }

            public String getRealInfoStatus() {
                return realInfoStatus;
            }

            public void setRealInfoStatus(String realInfoStatus) {
                this.realInfoStatus = realInfoStatus;
            }

            public String getEbankStatus() {
                return ebankStatus;
            }

            public void setEbankStatus(String ebankStatus) {
                this.ebankStatus = ebankStatus;
            }

            public String getZmxyStatus() {
                return zmxyStatus;
            }

            public void setZmxyStatus(String zmxyStatus) {
                this.zmxyStatus = zmxyStatus;
            }

            public String getOperatorStatus() {
                return operatorStatus;
            }

            public void setOperatorStatus(String operatorStatus) {
                this.operatorStatus = operatorStatus;
            }

            public String getAlipayStatus() {
                return alipayStatus;
            }

            public void setAlipayStatus(String alipayStatus) {
                this.alipayStatus = alipayStatus;
            }

            public String getEmailStatus() {
                return emailStatus;
            }

            public void setEmailStatus(String emailStatus) {
                this.emailStatus = emailStatus;
            }

            public String getFundStatus() {
                return fundStatus;
            }

            public void setFundStatus(String fundStatus) {
                this.fundStatus = fundStatus;
            }

            public String getZxStatus() {
                return zxStatus;
            }

            public void setZxStatus(String zxStatus) {
                this.zxStatus = zxStatus;
            }

            public String getTaobaoStatus() {
                return taobaoStatus;
            }

            public void setTaobaoStatus(String taobaoStatus) {
                this.taobaoStatus = taobaoStatus;
            }

            public static class UserBean {
                /**
                 * id : ae6b0df0-1db0-49f9-b1f9-d3af053f65f6
                 * createdTime : 1493696630000
                 * updatedTime : 1508920188000
                 * avatar : 6e60a833-a287-43e2-a246-3c72f9d49a74
                 * nick : 汝等小儿，可敢杀我
                 * account : {"id":"d3b76361-c2be-40b0-b28d-94ac68e6a228","createdTime":1493544026000,"updatedTime":1506752870000,"mobileNumber":"15000983434","password":"3e35dbffd6b900e0033313643d78d9010cf0a312","verificationCode":"9168"}
                 * status : NORMAL
                 * lastSMS : 1506752870000
                 * lastLoginTime : 1508920188000
                 * imei : 867368021787513
                 * userValidity : VALID
                 * browsedLatestProducts : false
                 */

                private String id;
                private long createdTime;
                private long updatedTime;
                private String avatar;
                private String nick;
                private AccountBean account;
                private String status;
                private long lastSMS;
                private long lastLoginTime;
                private String imei;
                private String userValidity;
                private boolean browsedLatestProducts;

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

                public long getLastLoginTime() {
                    return lastLoginTime;
                }

                public void setLastLoginTime(long lastLoginTime) {
                    this.lastLoginTime = lastLoginTime;
                }

                public String getImei() {
                    return imei;
                }

                public void setImei(String imei) {
                    this.imei = imei;
                }

                public String getUserValidity() {
                    return userValidity;
                }

                public void setUserValidity(String userValidity) {
                    this.userValidity = userValidity;
                }

                public boolean isBrowsedLatestProducts() {
                    return browsedLatestProducts;
                }

                public void setBrowsedLatestProducts(boolean browsedLatestProducts) {
                    this.browsedLatestProducts = browsedLatestProducts;
                }

                public static class AccountBean {
                    /**
                     * id : d3b76361-c2be-40b0-b28d-94ac68e6a228
                     * createdTime : 1493544026000
                     * updatedTime : 1506752870000
                     * mobileNumber : 15000983434
                     * password : 3e35dbffd6b900e0033313643d78d9010cf0a312
                     * verificationCode : 9168
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
}
