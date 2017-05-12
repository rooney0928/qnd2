package com.app.qunadai.bean;

import java.util.List;

/**
 * Created by wayne on 2017/5/12.
 */

public class ProductsBean {

    /**
     * msg : 成功
     * code : 000
     * detail : 成功
     * content : {"pages":{"content":[{"id":"214ba091-d0e2-4a17-8fb4-7187b84c85d3","createdTime":1493006400000,"updatedTime":1494572591000,"provider":{"id":"9e5e6648-5b09-4f05-a155-2cf0307037f7","createdTime":1491019200000,"updatedTime":1493949350000,"name":"读秒","lppStatus":"ENABLED"},"productNo":"515","name":"读秒","describe":"\u201c快贷\u201d是挖财2015年推出的手机借钱app，分成\u201c极速借款\u201d（最高3000元）和\u201c大额借款\u201d（最高20万元）两种模式。","type":"H5","amount":"1000-50000","minAmount":1000,"maxAmount":50000,"term":"1-24期","minTerm":1,"maxTerm":24,"termUnit":"月","applicationConditions":"1.22-55周岁工作稳定且有信用卡的全职工薪人士\n2.芝麻分600以上","applicationMaterials":"身份证、手机号、信用卡","url":"https://loan-m.jimu.com/loanv2/apply/index?from=jmhz152","num":3379,"rate":"0.7%-5%","loanTime":"3分钟","pStatus":"ENABLED","showIndexStatus":"HIDE","minRate":0.007,"maxRate":0.05,"rateStatus":"MONTH","sucRate":"8%","pOrder":5},{"id":"3aff4bcb-a002-454c-b6d8-ae6b1ea36ec3","createdTime":1493179200000,"updatedTime":1494469714000,"provider":{"id":"67740bfc-f89f-4891-a76f-53b6a5c46994","createdTime":1491019200000,"updatedTime":1491019200000,"name":"随手借","lppStatus":"ENABLED"},"name":"随手借","describe":"申请人提交借款申请后，最快可实时获得借款，最慢在5分钟内放款。","type":"H5","amount":"500-10000","minAmount":500,"maxAmount":10000,"term":"3-12月","minTerm":3,"maxTerm":12,"termUnit":"月","applicationConditions":"1.满足18-40岁的中国公民且社保正常\n2.即可进行借款申请","applicationMaterials":"身份证，联系人，工作资料","url":"http://appweb01.lend51.com/JieWebApp/huodong/zhouzhuan?ChannelID=20170225zzbz120","num":9831,"rate":"0.7%-5%","loanTime":"3分钟","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":0.007,"maxRate":0.05,"sucRate":"10%"},{"id":"950ab76a-88b8-4e49-9c6d-711ba7f6b56e","createdTime":1492660800000,"updatedTime":1492660800000,"provider":{"id":"eca183b1-e401-44c6-9ff5-e453d5261bd0","createdTime":1491019200000,"updatedTime":1493949359000,"name":"小花钱包","lppStatus":"ENABLED"},"name":"小花钱包","describe":"轻松提现 现金借款 费率低-借款费率低至每日0.05% 放款快-最快实时到账 超灵活-按日计息,随借随还 ","type":"H5","amount":"1000-20000","minAmount":1000,"maxAmount":20000,"term":"1-12期","minTerm":1,"maxTerm":12,"termUnit":"月","applicationConditions":"1.20-40周岁仅需身份证，刷脸即可贷款","applicationMaterials":"身份证，实名手机号","url":"https://www.xhqb.com/m/bfpp.html?appChannel=qund01","num":3063,"rate":"0.7-5%","loanTime":"3分钟","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":0.007,"maxRate":0.05,"sucRate":"8%"}],"last":true,"totalPages":1,"totalElements":3,"sort":[{"direction":"DESC","property":"pOrder","ignoreCase":false,"nullHandling":"NATIVE","ascending":false}],"first":true,"numberOfElements":3,"size":10,"number":0}}
     */

    private String msg;
    private String code;
    private String detail;
    private ContentBeanX content;

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

    public ContentBeanX getContent() {
        return content;
    }

    public void setContent(ContentBeanX content) {
        this.content = content;
    }

    public static class ContentBeanX {
        /**
         * pages : {"content":[{"id":"214ba091-d0e2-4a17-8fb4-7187b84c85d3","createdTime":1493006400000,"updatedTime":1494572591000,"provider":{"id":"9e5e6648-5b09-4f05-a155-2cf0307037f7","createdTime":1491019200000,"updatedTime":1493949350000,"name":"读秒","lppStatus":"ENABLED"},"productNo":"515","name":"读秒","describe":"\u201c快贷\u201d是挖财2015年推出的手机借钱app，分成\u201c极速借款\u201d（最高3000元）和\u201c大额借款\u201d（最高20万元）两种模式。","type":"H5","amount":"1000-50000","minAmount":1000,"maxAmount":50000,"term":"1-24期","minTerm":1,"maxTerm":24,"termUnit":"月","applicationConditions":"1.22-55周岁工作稳定且有信用卡的全职工薪人士\n2.芝麻分600以上","applicationMaterials":"身份证、手机号、信用卡","url":"https://loan-m.jimu.com/loanv2/apply/index?from=jmhz152","num":3379,"rate":"0.7%-5%","loanTime":"3分钟","pStatus":"ENABLED","showIndexStatus":"HIDE","minRate":0.007,"maxRate":0.05,"rateStatus":"MONTH","sucRate":"8%","pOrder":5},{"id":"3aff4bcb-a002-454c-b6d8-ae6b1ea36ec3","createdTime":1493179200000,"updatedTime":1494469714000,"provider":{"id":"67740bfc-f89f-4891-a76f-53b6a5c46994","createdTime":1491019200000,"updatedTime":1491019200000,"name":"随手借","lppStatus":"ENABLED"},"name":"随手借","describe":"申请人提交借款申请后，最快可实时获得借款，最慢在5分钟内放款。","type":"H5","amount":"500-10000","minAmount":500,"maxAmount":10000,"term":"3-12月","minTerm":3,"maxTerm":12,"termUnit":"月","applicationConditions":"1.满足18-40岁的中国公民且社保正常\n2.即可进行借款申请","applicationMaterials":"身份证，联系人，工作资料","url":"http://appweb01.lend51.com/JieWebApp/huodong/zhouzhuan?ChannelID=20170225zzbz120","num":9831,"rate":"0.7%-5%","loanTime":"3分钟","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":0.007,"maxRate":0.05,"sucRate":"10%"},{"id":"950ab76a-88b8-4e49-9c6d-711ba7f6b56e","createdTime":1492660800000,"updatedTime":1492660800000,"provider":{"id":"eca183b1-e401-44c6-9ff5-e453d5261bd0","createdTime":1491019200000,"updatedTime":1493949359000,"name":"小花钱包","lppStatus":"ENABLED"},"name":"小花钱包","describe":"轻松提现 现金借款 费率低-借款费率低至每日0.05% 放款快-最快实时到账 超灵活-按日计息,随借随还 ","type":"H5","amount":"1000-20000","minAmount":1000,"maxAmount":20000,"term":"1-12期","minTerm":1,"maxTerm":12,"termUnit":"月","applicationConditions":"1.20-40周岁仅需身份证，刷脸即可贷款","applicationMaterials":"身份证，实名手机号","url":"https://www.xhqb.com/m/bfpp.html?appChannel=qund01","num":3063,"rate":"0.7-5%","loanTime":"3分钟","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":0.007,"maxRate":0.05,"sucRate":"8%"}],"last":true,"totalPages":1,"totalElements":3,"sort":[{"direction":"DESC","property":"pOrder","ignoreCase":false,"nullHandling":"NATIVE","ascending":false}],"first":true,"numberOfElements":3,"size":10,"number":0}
         */

        private PagesBean pages;

        public PagesBean getPages() {
            return pages;
        }

        public void setPages(PagesBean pages) {
            this.pages = pages;
        }

        public static class PagesBean {
            /**
             * content : [{"id":"214ba091-d0e2-4a17-8fb4-7187b84c85d3","createdTime":1493006400000,"updatedTime":1494572591000,"provider":{"id":"9e5e6648-5b09-4f05-a155-2cf0307037f7","createdTime":1491019200000,"updatedTime":1493949350000,"name":"读秒","lppStatus":"ENABLED"},"productNo":"515","name":"读秒","describe":"\u201c快贷\u201d是挖财2015年推出的手机借钱app，分成\u201c极速借款\u201d（最高3000元）和\u201c大额借款\u201d（最高20万元）两种模式。","type":"H5","amount":"1000-50000","minAmount":1000,"maxAmount":50000,"term":"1-24期","minTerm":1,"maxTerm":24,"termUnit":"月","applicationConditions":"1.22-55周岁工作稳定且有信用卡的全职工薪人士\n2.芝麻分600以上","applicationMaterials":"身份证、手机号、信用卡","url":"https://loan-m.jimu.com/loanv2/apply/index?from=jmhz152","num":3379,"rate":"0.7%-5%","loanTime":"3分钟","pStatus":"ENABLED","showIndexStatus":"HIDE","minRate":0.007,"maxRate":0.05,"rateStatus":"MONTH","sucRate":"8%","pOrder":5},{"id":"3aff4bcb-a002-454c-b6d8-ae6b1ea36ec3","createdTime":1493179200000,"updatedTime":1494469714000,"provider":{"id":"67740bfc-f89f-4891-a76f-53b6a5c46994","createdTime":1491019200000,"updatedTime":1491019200000,"name":"随手借","lppStatus":"ENABLED"},"name":"随手借","describe":"申请人提交借款申请后，最快可实时获得借款，最慢在5分钟内放款。","type":"H5","amount":"500-10000","minAmount":500,"maxAmount":10000,"term":"3-12月","minTerm":3,"maxTerm":12,"termUnit":"月","applicationConditions":"1.满足18-40岁的中国公民且社保正常\n2.即可进行借款申请","applicationMaterials":"身份证，联系人，工作资料","url":"http://appweb01.lend51.com/JieWebApp/huodong/zhouzhuan?ChannelID=20170225zzbz120","num":9831,"rate":"0.7%-5%","loanTime":"3分钟","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":0.007,"maxRate":0.05,"sucRate":"10%"},{"id":"950ab76a-88b8-4e49-9c6d-711ba7f6b56e","createdTime":1492660800000,"updatedTime":1492660800000,"provider":{"id":"eca183b1-e401-44c6-9ff5-e453d5261bd0","createdTime":1491019200000,"updatedTime":1493949359000,"name":"小花钱包","lppStatus":"ENABLED"},"name":"小花钱包","describe":"轻松提现 现金借款 费率低-借款费率低至每日0.05% 放款快-最快实时到账 超灵活-按日计息,随借随还 ","type":"H5","amount":"1000-20000","minAmount":1000,"maxAmount":20000,"term":"1-12期","minTerm":1,"maxTerm":12,"termUnit":"月","applicationConditions":"1.20-40周岁仅需身份证，刷脸即可贷款","applicationMaterials":"身份证，实名手机号","url":"https://www.xhqb.com/m/bfpp.html?appChannel=qund01","num":3063,"rate":"0.7-5%","loanTime":"3分钟","pStatus":"ENABLED","showIndexStatus":"SHOW","minRate":0.007,"maxRate":0.05,"sucRate":"8%"}]
             * last : true
             * totalPages : 1
             * totalElements : 3
             * sort : [{"direction":"DESC","property":"pOrder","ignoreCase":false,"nullHandling":"NATIVE","ascending":false}]
             * first : true
             * numberOfElements : 3
             * size : 10
             * number : 0
             */

            private boolean last;
            private int totalPages;
            private int totalElements;
            private boolean first;
            private int numberOfElements;
            private int size;
            private int number;
            private List<LoanDetail> content;
            private List<SortBean> sort;

            public boolean isLast() {
                return last;
            }

            public void setLast(boolean last) {
                this.last = last;
            }

            public int getTotalPages() {
                return totalPages;
            }

            public void setTotalPages(int totalPages) {
                this.totalPages = totalPages;
            }

            public int getTotalElements() {
                return totalElements;
            }

            public void setTotalElements(int totalElements) {
                this.totalElements = totalElements;
            }

            public boolean isFirst() {
                return first;
            }

            public void setFirst(boolean first) {
                this.first = first;
            }

            public int getNumberOfElements() {
                return numberOfElements;
            }

            public void setNumberOfElements(int numberOfElements) {
                this.numberOfElements = numberOfElements;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public List<LoanDetail> getContent() {
                return content;
            }

            public void setContent(List<LoanDetail> content) {
                this.content = content;
            }

            public List<SortBean> getSort() {
                return sort;
            }

            public void setSort(List<SortBean> sort) {
                this.sort = sort;
            }


            public static class SortBean {
                /**
                 * direction : DESC
                 * property : pOrder
                 * ignoreCase : false
                 * nullHandling : NATIVE
                 * ascending : false
                 */

                private String direction;
                private String property;
                private boolean ignoreCase;
                private String nullHandling;
                private boolean ascending;

                public String getDirection() {
                    return direction;
                }

                public void setDirection(String direction) {
                    this.direction = direction;
                }

                public String getProperty() {
                    return property;
                }

                public void setProperty(String property) {
                    this.property = property;
                }

                public boolean isIgnoreCase() {
                    return ignoreCase;
                }

                public void setIgnoreCase(boolean ignoreCase) {
                    this.ignoreCase = ignoreCase;
                }

                public String getNullHandling() {
                    return nullHandling;
                }

                public void setNullHandling(String nullHandling) {
                    this.nullHandling = nullHandling;
                }

                public boolean isAscending() {
                    return ascending;
                }

                public void setAscending(boolean ascending) {
                    this.ascending = ascending;
                }
            }
        }
    }
}
