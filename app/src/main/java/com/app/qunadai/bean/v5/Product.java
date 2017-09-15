package com.app.qunadai.bean.v5;

import java.io.Serializable;

/**
 * Created by wayne on 2017/9/8.
 */

public class Product implements Serializable{


    /**
     * id : 0f2b4ed2-97ca-4bb8-aa16-95df0327e2dd
     * status : NORMAL
     * provider : {"id":"4d9f4749-5e11-415c-9161-a76188df33e5","status":null,"name":"钱师爷","type":"理财","address":"上海市","code":null,"contactsPerson":"网二","contactsPhoneNumber":"18644112255","icon":null,"lppStatus":"ENABLED"}
     * productNo : 25
     * name : U族大学贷2
     * describe : 是一款最最划算的大学生贷款软件平台，如果您短暂缺乏资金就可以使用这款软禁进行无息贷款
     * type : H5
     * icon : 10aecbcf-278c-4452-9e65-72b138c86fe7
     * amount : 5000-10000
     * minAmount : 0
     * maxAmount : 10000
     * term : 10-40天
     * minTerm : 10
     * maxTerm : 100
     * termUnit : 月
     * applicationConditions : 1.满18周岁姓名
     2.身份证号验证即可申请
     * applicationMaterials : 身份证，联系人信息
     * url : https://m.u-zu.com/?channel=u-qnd-llcs
     * num : 486
     * rate : 0.73%-5%
     * loanTime : 4
     * loanTimeUnit : SECONDS
     * pStatus : ENABLED
     * showIndexStatus : SHOW
     * minRate : 0.73
     * maxRate : 5
     * rateStatus : MONTH
     * sucRate : 80
     * pOrder : 1
     * balanceRatio : 4
     * totalStarNumber : 21
     * totalCommentNumber : 6
     */

    private String id;
    private String status;
    private ProviderBean provider;
    private String productNo;
    private String name;
    private String describe;
    private String type;
    private String icon;
    private String amount;
    private int minAmount;
    private int maxAmount;
    private String term;
    private int minTerm;
    private int maxTerm;
    private String termUnit;
    private String applicationConditions;
    private String applicationMaterials;
    private String url;
    private int num;
    private String rate;
    private int loanTime;
    private String loanTimeUnit;
    private String pStatus;
    private String showIndexStatus;
    private double minRate;
    private double maxRate;
    private String rateStatus;
    private int sucRate;
    private int pOrder;
    private String balanceRatio;
    private int totalStarNumber;
    private int totalCommentNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProviderBean getProvider() {
        return provider;
    }

    public void setProvider(ProviderBean provider) {
        this.provider = provider;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getMinTerm() {
        return minTerm;
    }

    public void setMinTerm(int minTerm) {
        this.minTerm = minTerm;
    }

    public int getMaxTerm() {
        return maxTerm;
    }

    public void setMaxTerm(int maxTerm) {
        this.maxTerm = maxTerm;
    }

    public String getTermUnit() {
        return termUnit;
    }

    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit;
    }

    public String getApplicationConditions() {
        return applicationConditions;
    }

    public void setApplicationConditions(String applicationConditions) {
        this.applicationConditions = applicationConditions;
    }

    public String getApplicationMaterials() {
        return applicationMaterials;
    }

    public void setApplicationMaterials(String applicationMaterials) {
        this.applicationMaterials = applicationMaterials;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(int loanTime) {
        this.loanTime = loanTime;
    }

    public String getLoanTimeUnit() {
        return loanTimeUnit;
    }

    public void setLoanTimeUnit(String loanTimeUnit) {
        this.loanTimeUnit = loanTimeUnit;
    }

    public String getPStatus() {
        return pStatus;
    }

    public void setPStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public String getShowIndexStatus() {
        return showIndexStatus;
    }

    public void setShowIndexStatus(String showIndexStatus) {
        this.showIndexStatus = showIndexStatus;
    }

    public double getMinRate() {
        return minRate;
    }

    public void setMinRate(double minRate) {
        this.minRate = minRate;
    }

    public double getMaxRate() {
        return maxRate;
    }

    public void setMaxRate(double maxRate) {
        this.maxRate = maxRate;
    }

    public String getRateStatus() {
        return rateStatus;
    }

    public void setRateStatus(String rateStatus) {
        this.rateStatus = rateStatus;
    }

    public int getSucRate() {
        return sucRate;
    }

    public void setSucRate(int sucRate) {
        this.sucRate = sucRate;
    }

    public int getPOrder() {
        return pOrder;
    }

    public void setPOrder(int pOrder) {
        this.pOrder = pOrder;
    }

    public String getBalanceRatio() {
        return balanceRatio;
    }

    public void setBalanceRatio(String balanceRatio) {
        this.balanceRatio = balanceRatio;
    }

    public int getTotalStarNumber() {
        return totalStarNumber;
    }

    public void setTotalStarNumber(int totalStarNumber) {
        this.totalStarNumber = totalStarNumber;
    }

    public int getTotalCommentNumber() {
        return totalCommentNumber;
    }

    public void setTotalCommentNumber(int totalCommentNumber) {
        this.totalCommentNumber = totalCommentNumber;
    }

    public static class ProviderBean implements Serializable{
        /**
         * id : 4d9f4749-5e11-415c-9161-a76188df33e5
         * status : null
         * name : 钱师爷
         * type : 理财
         * address : 上海市
         * code : null
         * contactsPerson : 网二
         * contactsPhoneNumber : 18644112255
         * icon : null
         * lppStatus : ENABLED
         */

        private String id;
        private Object status;
        private String name;
        private String type;
        private String address;
        private Object code;
        private String contactsPerson;
        private String contactsPhoneNumber;
        private Object icon;
        private String lppStatus;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public String getContactsPerson() {
            return contactsPerson;
        }

        public void setContactsPerson(String contactsPerson) {
            this.contactsPerson = contactsPerson;
        }

        public String getContactsPhoneNumber() {
            return contactsPhoneNumber;
        }

        public void setContactsPhoneNumber(String contactsPhoneNumber) {
            this.contactsPhoneNumber = contactsPhoneNumber;
        }

        public Object getIcon() {
            return icon;
        }

        public void setIcon(Object icon) {
            this.icon = icon;
        }

        public String getLppStatus() {
            return lppStatus;
        }

        public void setLppStatus(String lppStatus) {
            this.lppStatus = lppStatus;
        }
    }
}
