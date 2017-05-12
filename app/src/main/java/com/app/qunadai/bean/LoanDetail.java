package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/10.
 */

public class LoanDetail {


    /**
     * id : 4b6786dd-a779-4ae3-8ab7-23b4c57c27c8
     * createdTime : 1494312301000
     * updatedTime : 1494411071000
     * provider : {"id":"025339b0-3cf3-43a3-97b2-47de96357d05","createdTime":1491019200000,"updatedTime":1494214374000,"name":"平安i贷","lppStatus":"ENABLED"}
     * name : 平安i贷
     * describe : 1天极速放款!月收入3000即可申请.帮您轻松应急!
     * type : H5
     * icon : f20d8780-88f3-42d7-b19e-4ef657ff066a
     * minAmount : 2000
     * maxAmount : 30000
     * minTerm : 12
     * maxTerm : 36
     * applicationConditions : 18-55周岁月收入：3000元/月或以上，现居住地址居住时间：最低6个月，在申请地居住或工作，客户职业不属于限制性行业。身份证、户口本、工作证明、有房产提供房产、银行卡
     * url : https://www.10100000.com/m/iloan/apply1.html?utm_source=wxhaihxxdy--m&utm_medium=cps&utm_campaign=m0018--iln&utm_content=m-LQ018&WT.mc_id=CXX-WXHAIHXXDY-LQ018-CSM-M0018ILN&
     * num : 5987
     * rate : 0.1%-0.1%
     * loanTime : 3分钟
     * pStatus : ENABLED
     * minRate : 0.002
     * maxRate : 0.002
     * rateStatus : MONTH
     * sucRate : 12%
     */

    private String id;
    private long createdTime;
    private long updatedTime;
    private ProviderBean provider;
    private String name;
    private String describe;
    private String type;
    private String icon;
    private int minAmount;
    private int maxAmount;
    private int minTerm;
    private int maxTerm;
    private String applicationConditions;
    private String url;
    private int num;
    private String rate;
    private String loanTime;
    private String pStatus;
    private double minRate;
    private double maxRate;
    private String rateStatus;
    private String sucRate;

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

    public ProviderBean getProvider() {
        return provider;
    }

    public void setProvider(ProviderBean provider) {
        this.provider = provider;
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

    public String getApplicationConditions() {
        return applicationConditions;
    }

    public void setApplicationConditions(String applicationConditions) {
        this.applicationConditions = applicationConditions;
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

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public String getPStatus() {
        return pStatus;
    }

    public void setPStatus(String pStatus) {
        this.pStatus = pStatus;
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

    public String getSucRate() {
        return sucRate;
    }

    public void setSucRate(String sucRate) {
        this.sucRate = sucRate;
    }

    public static class ProviderBean {
        /**
         * id : 025339b0-3cf3-43a3-97b2-47de96357d05
         * createdTime : 1491019200000
         * updatedTime : 1494214374000
         * name : 平安i贷
         * lppStatus : ENABLED
         */

        private String id;
        private long createdTime;
        private long updatedTime;
        private String name;
        private String lppStatus;

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

        public String getLppStatus() {
            return lppStatus;
        }

        public void setLppStatus(String lppStatus) {
            this.lppStatus = lppStatus;
        }
    }
}
