package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/12.
 */

public class PersonBean {

    /**
     * msg : 操作成功
     * code : 000
     * detail : 操作成功
     * content : {"personalValue":{"id":"8b813475-7711-4884-ae46-8e805d2f591e","createdTime":1493101490000,"updatedTime":1493110834000,"user":{"id":"f82538b9-af3d-4b7e-a6bd-15c3bddbaf6f","updatedTime":1493191640000,"avatar":"72bd005f-f1d4-4a58-afe0-557c21367119","nick":"哒哒哒","account":{"id":"f10bfc78-d0cb-49ed-bb2b-6526da780ca0","createdTime":1493101454000,"updatedTime":1493101490000,"mobileNumber":"17301746633","password":"7c4a8d09ca3762af61e59520943dc26494f8941b","verificationCode":"096095"},"status":"NORMAL"},"tips":"快去贷款吧!","valuation":2000,"balance":10000,"status":"CREATED","bankStatus":"CREATED","realInfoStatus":"SUCCESS","ebankStatus":"CREATED","zmxyStatus":"CREATED","operatorStatus":"CREATED","alipayStatus":"CREATED","emailStatus":"CREATED","fundStatus":"CREATED","zxStatus":"CREATED","taobaoStatus":"CREATED"}}
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
         * personalValue : {"id":"8b813475-7711-4884-ae46-8e805d2f591e","createdTime":1493101490000,"updatedTime":1493110834000,"user":{"id":"f82538b9-af3d-4b7e-a6bd-15c3bddbaf6f","updatedTime":1493191640000,"avatar":"72bd005f-f1d4-4a58-afe0-557c21367119","nick":"哒哒哒","account":{"id":"f10bfc78-d0cb-49ed-bb2b-6526da780ca0","createdTime":1493101454000,"updatedTime":1493101490000,"mobileNumber":"17301746633","password":"7c4a8d09ca3762af61e59520943dc26494f8941b","verificationCode":"096095"},"status":"NORMAL"},"tips":"快去贷款吧!","valuation":2000,"balance":10000,"status":"CREATED","bankStatus":"CREATED","realInfoStatus":"SUCCESS","ebankStatus":"CREATED","zmxyStatus":"CREATED","operatorStatus":"CREATED","alipayStatus":"CREATED","emailStatus":"CREATED","fundStatus":"CREATED","zxStatus":"CREATED","taobaoStatus":"CREATED"}
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
             * id : 8b813475-7711-4884-ae46-8e805d2f591e
             * createdTime : 1493101490000
             * updatedTime : 1493110834000
             * user : {"id":"f82538b9-af3d-4b7e-a6bd-15c3bddbaf6f","updatedTime":1493191640000,"avatar":"72bd005f-f1d4-4a58-afe0-557c21367119","nick":"哒哒哒","account":{"id":"f10bfc78-d0cb-49ed-bb2b-6526da780ca0","createdTime":1493101454000,"updatedTime":1493101490000,"mobileNumber":"17301746633","password":"7c4a8d09ca3762af61e59520943dc26494f8941b","verificationCode":"096095"},"status":"NORMAL"}
             * tips : 快去贷款吧!
             * valuation : 2000
             * balance : 10000
             * status : CREATED
             * bankStatus : CREATED
             * realInfoStatus : SUCCESS
             * ebankStatus : CREATED
             * zmxyStatus : CREATED
             * operatorStatus : CREATED
             * alipayStatus : CREATED
             * emailStatus : CREATED
             * fundStatus : CREATED
             * zxStatus : CREATED
             * taobaoStatus : CREATED
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
                 * id : f82538b9-af3d-4b7e-a6bd-15c3bddbaf6f
                 * updatedTime : 1493191640000
                 * avatar : 72bd005f-f1d4-4a58-afe0-557c21367119
                 * nick : 哒哒哒
                 * account : {"id":"f10bfc78-d0cb-49ed-bb2b-6526da780ca0","createdTime":1493101454000,"updatedTime":1493101490000,"mobileNumber":"17301746633","password":"7c4a8d09ca3762af61e59520943dc26494f8941b","verificationCode":"096095"}
                 * status : NORMAL
                 */

                private String id;
                private long updatedTime;
                private String avatar;
                private String nick;
                private AccountBean account;
                private String status;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
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

                public static class AccountBean {
                    /**
                     * id : f10bfc78-d0cb-49ed-bb2b-6526da780ca0
                     * createdTime : 1493101454000
                     * updatedTime : 1493101490000
                     * mobileNumber : 17301746633
                     * password : 7c4a8d09ca3762af61e59520943dc26494f8941b
                     * verificationCode : 096095
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
