package com.app.qunadai.bean;

/**
 * Created by wayne on 2017/5/10.
 */

public class LoanDetail {

    /**
     * id : 2a028d3f-ae87-4472-b8dd-ebb34bc77b17
     * createdTime : 1492660800000
     * updatedTime : 1494469964000
     * provider : {"id":"c44862f6-bcf4-4d3b-b76c-0fb4d7322ab4","createdTime":1491019200000,"updatedTime":1491019200000,"name":"魔法现金","lppStatus":"ENABLED"}
     * productNo : 5
     * name : 魔法现金
     * describe : 小额借贷就来魔法现金，3分钟审核，急速放款！
     * type : H5
     * icon : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=766471795,842660247&fm=117&gp=0.jpg
     * amount : 300-5000
     * minAmount : 300
     * maxAmount : 5000
     * term : 7,14天
     * minTerm : 0
     * maxTerm : 1
     * termUnit : 月
     * applicationConditions : 1.年满18周岁
     2.有稳定使用的手机号码和稳定的收入即可申请。不看征信。
     * applicationMaterials : 身份信息、本人银行卡，正确填写个人基本信息
     * url : http://aldb.me/ilZ6u
     * num : 7994
     * rate : 0.7%-5%
     * loanTime : 3分钟
     * pStatus : ENABLED
     * showIndexStatus : SHOW
     * minRate : 0.007
     * maxRate : 0.05
     * rateStatus : MONTH
     * sucRate : 30%
     */

    private String id;
    private long createdTime;
    private long updatedTime;
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
    private String loanTime;
    private String pStatus;
    private String showIndexStatus;
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
         * id : c44862f6-bcf4-4d3b-b76c-0fb4d7322ab4
         * createdTime : 1491019200000
         * updatedTime : 1491019200000
         * name : 魔法现金
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
