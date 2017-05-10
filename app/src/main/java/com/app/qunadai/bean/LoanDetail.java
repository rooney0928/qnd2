package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/10.
 */

public class LoanDetail {

    /**
     * id : 214ba091-d0e2-4a17-8fb4-7187b84c85d3
     * createdTime : 1492660800000
     * updatedTime : 1493708442000
     * provider : {"id":"9e5e6648-5b09-4f05-a155-2cf0307037f7","createdTime":1491019200000,"updatedTime":1493949350000,"name":"读秒","lppStatus":"ENABLED"}
     * name : 读秒
     * describe : “快贷”是挖财2015年推出的手机借钱app，分成“极速借款”（最高3000元）和“大额借款”（最高20万元）两种模式。
     * type : H5
     * amount : 1000-50000
     * minAmount : 1000
     * maxAmount : 50000
     * term : 1-24期
     * minTerm : 1
     * maxTerm : 24
     * termUnit : 月
     * applicationConditions : 1.22-55周岁工作稳定且有信用卡的全职工薪人士
     2.芝麻分600以上
     * applicationMaterials : 身份证、手机号、信用卡
     * url : https://loan-m.jimu.com/loanv2/apply/index?from=jmhz152
     * num : 3379
     * rate : 0.7%-5%
     * loanTime : 3分钟
     * pStatus : ENABLED
     * showIndexStatus : SHOW
     * minRate : 0.007
     * maxRate : 0.05
     * sucRate : 8%
     */

    private String id;
    private long createdTime;
    private long updatedTime;
    private ProviderBean provider;
    private String name;
    private String describe;
    private String type;
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
    private String loanTime;
    private String pStatus;
    private String showIndexStatus;
    private double minRate;
    private double maxRate;
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

    public String getSucRate() {
        return sucRate;
    }

    public void setSucRate(String sucRate) {
        this.sucRate = sucRate;
    }

    public static class ProviderBean {
        /**
         * id : 9e5e6648-5b09-4f05-a155-2cf0307037f7
         * createdTime : 1491019200000
         * updatedTime : 1493949350000
         * name : 读秒
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
