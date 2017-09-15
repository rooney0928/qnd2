package com.app.qunadai.bean.v5;

import java.util.List;

/**
 * Created by wayne on 2017/9/15.
 */

public class ExploreBean {

    private List<UserBrowsingHistoryListBean> userBrowsingHistoryList;

    public List<UserBrowsingHistoryListBean> getUserBrowsingHistoryList() {
        return userBrowsingHistoryList;
    }

    public void setUserBrowsingHistoryList(List<UserBrowsingHistoryListBean> userBrowsingHistoryList) {
        this.userBrowsingHistoryList = userBrowsingHistoryList;
    }

    public static class UserBrowsingHistoryListBean {
        /**
         * productVO : {"id":"0f2b4ed2-97ca-4bb8-aa16-95df0327e2dd","status":"NORMAL","productNo":"25","name":"U族大学贷2","describe":"是一款最最划算的大学生贷款软件平台，如果您短暂缺乏资金就可以使用这款软禁进行无息贷款","icon":"10aecbcf-278c-4452-9e65-72b138c86fe7","amount":"5000-10000","minAmount":0,"maxAmount":10000,"term":"10-40天","minTerm":10,"maxTerm":100,"termUnit":"天","applicationConditions":"1.满18周岁姓名\n2.身份证号验证即可申请","applicationMaterials":"身份证，联系人信息","url":"https://m.u-zu.com/?channel=u-qnd-llcs","rate":"0.732%-5.121%","loanTime":4,"pStatus":"ENABLED","minRate":0.732,"maxRate":5.121,"rateStatus":"MONTH","loanTimeUnit":"MINUTE"}
         * browsingTime : 1504862093000
         */

        private ProductVOBean productVO;
        private long browsingTime;

        public ProductVOBean getProductVO() {
            return productVO;
        }

        public void setProductVO(ProductVOBean productVO) {
            this.productVO = productVO;
        }

        public long getBrowsingTime() {
            return browsingTime;
        }

        public void setBrowsingTime(long browsingTime) {
            this.browsingTime = browsingTime;
        }

        public static class ProductVOBean {
            /**
             * id : 0f2b4ed2-97ca-4bb8-aa16-95df0327e2dd
             * status : NORMAL
             * productNo : 25
             * name : U族大学贷2
             * describe : 是一款最最划算的大学生贷款软件平台，如果您短暂缺乏资金就可以使用这款软禁进行无息贷款
             * icon : 10aecbcf-278c-4452-9e65-72b138c86fe7
             * amount : 5000-10000
             * minAmount : 0
             * maxAmount : 10000
             * term : 10-40天
             * minTerm : 10
             * maxTerm : 100
             * termUnit : 天
             * applicationConditions : 1.满18周岁姓名
             2.身份证号验证即可申请
             * applicationMaterials : 身份证，联系人信息
             * url : https://m.u-zu.com/?channel=u-qnd-llcs
             * rate : 0.732%-5.121%
             * loanTime : 4
             * pStatus : ENABLED
             * minRate : 0.732
             * maxRate : 5.121
             * rateStatus : MONTH
             * loanTimeUnit : MINUTE
             */

            private String id;
            private String status;
            private String productNo;
            private String name;
            private String describe;
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
            private String rate;
            private int loanTime;
            private String pStatus;
            private double minRate;
            private double maxRate;
            private String rateStatus;
            private String loanTimeUnit;

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

            public String getLoanTimeUnit() {
                return loanTimeUnit;
            }

            public void setLoanTimeUnit(String loanTimeUnit) {
                this.loanTimeUnit = loanTimeUnit;
            }
        }
    }
}
