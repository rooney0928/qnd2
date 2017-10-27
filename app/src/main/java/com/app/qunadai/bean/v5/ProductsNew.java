package com.app.qunadai.bean.v5;

import java.util.List;

/**
 * Created by wayne on 2017/10/26.
 */

public class ProductsNew {

    /**
     * productList : {"content":[{"id":"51d6d0ca-be40-435d-a4c3-585a819d0146","status":"NORMAL","provider":{"id":"025339b0-3cf3-43a3-97b2-47de96357d05","status":"NORMAL","name":"平安i贷","type":"sdf","address":"上海","code":"22","contactsPerson":"主","contactsPhoneNumber":"17301847533","icon":null,"lppStatus":"ENABLED"},"productNo":"1","name":"测试product","describe":"请问","type":"H5","amount":"5000","minAmount":1000,"maxAmount":5000,"term":"5","minTerm":5,"maxTerm":24,"termUnit":"月","applicationConditions":"去去去","url":"https://mmt.qunadai.com/#!/forum/creditCardStrategies","num":675,"rate":"1%-8%","loanTime":1,"loanTimeUnit":"MINUTE","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":1,"maxRate":8,"rateStatus":"MONTH","sucRate":92,"pOrder":0,"totalCommentNumber":35,"icon":"8e649b4f-d5a7-42f0-8345-b90ce5289f89","applicationMaterials":"身份证、工作证明、收入证明","balanceRatio":"1","balanceType":"CPA"},{"id":"06c79b21-1256-4dba-b545-d2d0ecf18c53","status":"NORMAL","provider":{"id":"139da164-ece3-4265-8e49-9d6432f8a0ba","status":"NORMAL","name":"U族大学贷","type":"金融","address":"上海市","code":"SHKS","contactsPerson":"张三","contactsPhoneNumber":"13155632145","icon":null,"lppStatus":"ENABLED"},"productNo":"55","name":"测试产品","describe":"手动阀","type":"H5","amount":"3500-7000","minAmount":5,"maxAmount":8,"term":"545","minTerm":2,"maxTerm":3,"termUnit":"月","applicationConditions":"阿斯蒂芬","url":"http://m.niwodai.com/index.do?http://m.niwodai.com/index.do?method=ac&artId=3800001383297513&nwd_ext_aid=3000001491455041&source_id=","num":3,"rate":"2%-4%","loanTime":323,"loanTimeUnit":"HOUR","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":2,"maxRate":4,"rateStatus":"MONTH","sucRate":3,"pOrder":1,"totalCommentNumber":29},{"id":"3bb01cd1-76c3-4e81-8826-8726fb07d9b7","status":"NORMAL","provider":{"id":"139da164-ece3-4265-8e49-9d6432f8a0ba","status":"NORMAL","name":"U族大学贷","type":"金融","address":"上海市","code":"SHKS","contactsPerson":"张三","contactsPhoneNumber":"13155632145","icon":null,"lppStatus":"ENABLED"},"productNo":"23121","name":"开始的减肥了","describe":"asdf","type":"H5","amount":"2000-5221","minAmount":632,"maxAmount":2121,"term":"3-12月","minTerm":1,"maxTerm":33,"termUnit":"月","applicationConditions":"asdkf","url":"https://m.u-zu.com/?channel=u-qnd-llcs","num":-1,"rate":"3%-323%","loanTime":2,"loanTimeUnit":"MINUTE","pStatus":"DISABLED","showIndexStatus":"SHOW","minRate":3,"maxRate":323,"rateStatus":"MONTH","sucRate":31,"pOrder":1,"totalCommentNumber":30},{"id":"3d77f0bc-5c91-43ae-b337-323a319f30dd","status":"NORMAL","provider":{"id":"c7ead9d0-0ebc-4f9a-be8f-9a746f2c941d","status":"NORMAL","name":"中腾信","type":null,"address":null,"code":null,"contactsPerson":null,"contactsPhoneNumber":null,"icon":null,"lppStatus":"ENABLED"},"productNo":"ZXT","name":"中腾信","describe":"有社保、3分钟最高借15万","type":"H5","icon":"8e649b4f-d5a7-42f0-8345-b90ce5289f89","amount":"10000-15000","minAmount":10000,"maxAmount":150000,"term":"12,24,36,48期","minTerm":12,"maxTerm":48,"termUnit":"天","applicationConditions":"1.月入2000及以上\n2.有社保\n3.有公积金","applicationMaterials":"身份证、工作证明、收入证明","url":"http://mjk.chinatopcredit.com/?channel=WAPYK5","num":5173,"rate":"0.3%-5%","loanTime":3,"loanTimeUnit":"HOUR","pStatus":"DISABLED","showIndexStatus":"SHOW","minRate":0.3,"maxRate":5,"rateStatus":"MONTH","sucRate":10,"pOrder":1,"totalCommentNumber":116},{"id":"5e4a7ee2-d82f-4af1-8cae-c1028ecbcbfd","status":"NORMAL","provider":{"id":"151d540a-2862-40be-aff7-8ed0e4ee78d0","status":"NORMAL","name":"捷信福贷","type":null,"address":null,"code":null,"contactsPerson":null,"contactsPhoneNumber":null,"icon":null,"lppStatus":"ENABLED"},"productNo":"1","name":"测试11","describe":"了的罚款规定","type":"H5","amount":"2000-30000","minAmount":652,"maxAmount":2323,"term":"1-24月","minTerm":1,"maxTerm":2,"termUnit":"月","applicationConditions":"是的减肥的","url":"https://promotion.crfchina.com/localMarket/index.html?c=&s=imm3&salesmanNo=JKTZNJ0144&agentNo=JKTZNJ0144_20170117SHGY001","num":54,"rate":"0.7%-1%","loanTime":1,"loanTimeUnit":"HOUR","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":0.7,"maxRate":1,"rateStatus":"MONTH","sucRate":22,"pOrder":1,"balanceRatio":"1","balanceType":"CPA","totalCommentNumber":46}],"number":0,"size":10,"totalPages":1,"numberOfElements":5,"totalElements":5,"previousPage":false,"firstPage":false,"nextPage":false,"lastPage":false,"sort":null,"last":true,"first":true}
     */

    private ProductListBean productList;

    public ProductListBean getProductList() {
        return productList;
    }

    public void setProductList(ProductListBean productList) {
        this.productList = productList;
    }

    public static class ProductListBean {
        /**
         * content : [{"id":"51d6d0ca-be40-435d-a4c3-585a819d0146","status":"NORMAL","provider":{"id":"025339b0-3cf3-43a3-97b2-47de96357d05","status":"NORMAL","name":"平安i贷","type":"sdf","address":"上海","code":"22","contactsPerson":"主","contactsPhoneNumber":"17301847533","icon":null,"lppStatus":"ENABLED"},"productNo":"1","name":"测试product","describe":"请问","type":"H5","amount":"5000","minAmount":1000,"maxAmount":5000,"term":"5","minTerm":5,"maxTerm":24,"termUnit":"月","applicationConditions":"去去去","url":"https://mmt.qunadai.com/#!/forum/creditCardStrategies","num":675,"rate":"1%-8%","loanTime":1,"loanTimeUnit":"MINUTE","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":1,"maxRate":8,"rateStatus":"MONTH","sucRate":92,"pOrder":0,"totalCommentNumber":35},{"id":"06c79b21-1256-4dba-b545-d2d0ecf18c53","status":"NORMAL","provider":{"id":"139da164-ece3-4265-8e49-9d6432f8a0ba","status":"NORMAL","name":"U族大学贷","type":"金融","address":"上海市","code":"SHKS","contactsPerson":"张三","contactsPhoneNumber":"13155632145","icon":null,"lppStatus":"ENABLED"},"productNo":"55","name":"测试产品","describe":"手动阀","type":"H5","amount":"3500-7000","minAmount":5,"maxAmount":8,"term":"545","minTerm":2,"maxTerm":3,"termUnit":"月","applicationConditions":"阿斯蒂芬","url":"http://m.niwodai.com/index.do?http://m.niwodai.com/index.do?method=ac&artId=3800001383297513&nwd_ext_aid=3000001491455041&source_id=","num":3,"rate":"2%-4%","loanTime":323,"loanTimeUnit":"HOUR","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":2,"maxRate":4,"rateStatus":"MONTH","sucRate":3,"pOrder":1,"totalCommentNumber":29},{"id":"3bb01cd1-76c3-4e81-8826-8726fb07d9b7","status":"NORMAL","provider":{"id":"139da164-ece3-4265-8e49-9d6432f8a0ba","status":"NORMAL","name":"U族大学贷","type":"金融","address":"上海市","code":"SHKS","contactsPerson":"张三","contactsPhoneNumber":"13155632145","icon":null,"lppStatus":"ENABLED"},"productNo":"23121","name":"开始的减肥了","describe":"asdf","type":"H5","amount":"2000-5221","minAmount":632,"maxAmount":2121,"term":"3-12月","minTerm":1,"maxTerm":33,"termUnit":"月","applicationConditions":"asdkf","url":"https://m.u-zu.com/?channel=u-qnd-llcs","num":-1,"rate":"3%-323%","loanTime":2,"loanTimeUnit":"MINUTE","pStatus":"DISABLED","showIndexStatus":"SHOW","minRate":3,"maxRate":323,"rateStatus":"MONTH","sucRate":31,"pOrder":1,"totalCommentNumber":30},{"id":"3d77f0bc-5c91-43ae-b337-323a319f30dd","status":"NORMAL","provider":{"id":"c7ead9d0-0ebc-4f9a-be8f-9a746f2c941d","status":"NORMAL","name":"中腾信","type":null,"address":null,"code":null,"contactsPerson":null,"contactsPhoneNumber":null,"icon":null,"lppStatus":"ENABLED"},"productNo":"ZXT","name":"中腾信","describe":"有社保、3分钟最高借15万","type":"H5","icon":"8e649b4f-d5a7-42f0-8345-b90ce5289f89","amount":"10000-15000","minAmount":10000,"maxAmount":150000,"term":"12,24,36,48期","minTerm":12,"maxTerm":48,"termUnit":"天","applicationConditions":"1.月入2000及以上\n2.有社保\n3.有公积金","applicationMaterials":"身份证、工作证明、收入证明","url":"http://mjk.chinatopcredit.com/?channel=WAPYK5","num":5173,"rate":"0.3%-5%","loanTime":3,"loanTimeUnit":"HOUR","pStatus":"DISABLED","showIndexStatus":"SHOW","minRate":0.3,"maxRate":5,"rateStatus":"MONTH","sucRate":10,"pOrder":1,"totalCommentNumber":116},{"id":"5e4a7ee2-d82f-4af1-8cae-c1028ecbcbfd","status":"NORMAL","provider":{"id":"151d540a-2862-40be-aff7-8ed0e4ee78d0","status":"NORMAL","name":"捷信福贷","type":null,"address":null,"code":null,"contactsPerson":null,"contactsPhoneNumber":null,"icon":null,"lppStatus":"ENABLED"},"productNo":"1","name":"测试11","describe":"了的罚款规定","type":"H5","amount":"2000-30000","minAmount":652,"maxAmount":2323,"term":"1-24月","minTerm":1,"maxTerm":2,"termUnit":"月","applicationConditions":"是的减肥的","url":"https://promotion.crfchina.com/localMarket/index.html?c=&s=imm3&salesmanNo=JKTZNJ0144&agentNo=JKTZNJ0144_20170117SHGY001","num":54,"rate":"0.7%-1%","loanTime":1,"loanTimeUnit":"HOUR","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":0.7,"maxRate":1,"rateStatus":"MONTH","sucRate":22,"pOrder":1,"balanceRatio":"1","balanceType":"CPA","totalCommentNumber":46}]
         * number : 0
         * size : 10
         * totalPages : 1
         * numberOfElements : 5
         * totalElements : 5
         * previousPage : false
         * firstPage : false
         * nextPage : false
         * lastPage : false
         * sort : null
         * last : true
         * first : true
         */

        private int number;
        private int size;
        private int totalPages;
        private int numberOfElements;
        private int totalElements;
        private boolean previousPage;
        private boolean firstPage;
        private boolean nextPage;
        private boolean lastPage;
        private Object sort;
        private boolean last;
        private boolean first;
        private List<Product> content;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public boolean isPreviousPage() {
            return previousPage;
        }

        public void setPreviousPage(boolean previousPage) {
            this.previousPage = previousPage;
        }

        public boolean isFirstPage() {
            return firstPage;
        }

        public void setFirstPage(boolean firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isNextPage() {
            return nextPage;
        }

        public void setNextPage(boolean nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isLastPage() {
            return lastPage;
        }

        public void setLastPage(boolean lastPage) {
            this.lastPage = lastPage;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public List<Product> getContent() {
            return content;
        }

        public void setContent(List<Product> content) {
            this.content = content;
        }

    }
}
