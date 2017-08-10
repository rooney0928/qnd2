package com.app.qunadai.bean;

import java.util.List;

/**
 * Created by wayne on 2017/7/25.
 */

public class CreditCard {

    /**
     * msg : 成功
     * code : 000
     * detail : 成功
     * content : {"banks":{"content":[{"id":"8ae81014-9d88-41e9-92ba-abd6f9fc9a2c","createdTime":1500968318000,"updatedTime":1500971525000,"status":"NORMAL","bName":"浦发银行","bLogo":"8c14ed2a-5896-4e26-86ce-d9c94eea158d","bUrl":"http://asdjfsdf","recommendSlogan":"收到了快放假","bOrder":4,"bLabel":"审批快,额度高","approveSpeed":"4-9天","avgAmount":"451万","applyBase":415},{"id":"9f336d3e-5faa-4c56-900f-61f16cafe63f","createdTime":1500968626000,"updatedTime":1500972094000,"status":"NORMAL","bName":"兴业银行","bLogo":"8209e5e2-0e64-435d-8f3f-b7299eb7bbcd","bUrl":"http://asdjfsdf","recommendSlogan":"打扫房间","bOrder":2,"bLabel":"审批快","approveSpeed":"2-9天","avgAmount":"545万","applyBase":564163}],"number":0,"size":500,"totalPages":1,"numberOfElements":2,"totalElements":2,"previousPage":false,"firstPage":false,"nextPage":false,"lastPage":false,"sort":null,"last":true,"first":true}}
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
         * banks : {"content":[{"id":"8ae81014-9d88-41e9-92ba-abd6f9fc9a2c","createdTime":1500968318000,"updatedTime":1500971525000,"status":"NORMAL","bName":"浦发银行","bLogo":"8c14ed2a-5896-4e26-86ce-d9c94eea158d","bUrl":"http://asdjfsdf","recommendSlogan":"收到了快放假","bOrder":4,"bLabel":"审批快,额度高","approveSpeed":"4-9天","avgAmount":"451万","applyBase":415},{"id":"9f336d3e-5faa-4c56-900f-61f16cafe63f","createdTime":1500968626000,"updatedTime":1500972094000,"status":"NORMAL","bName":"兴业银行","bLogo":"8209e5e2-0e64-435d-8f3f-b7299eb7bbcd","bUrl":"http://asdjfsdf","recommendSlogan":"打扫房间","bOrder":2,"bLabel":"审批快","approveSpeed":"2-9天","avgAmount":"545万","applyBase":564163}],"number":0,"size":500,"totalPages":1,"numberOfElements":2,"totalElements":2,"previousPage":false,"firstPage":false,"nextPage":false,"lastPage":false,"sort":null,"last":true,"first":true}
         */

        private BanksBean banks;

        public BanksBean getBanks() {
            return banks;
        }

        public void setBanks(BanksBean banks) {
            this.banks = banks;
        }

        public static class BanksBean {
            /**
             * content : [{"id":"8ae81014-9d88-41e9-92ba-abd6f9fc9a2c","createdTime":1500968318000,"updatedTime":1500971525000,"status":"NORMAL","bName":"浦发银行","bLogo":"8c14ed2a-5896-4e26-86ce-d9c94eea158d","bUrl":"http://asdjfsdf","recommendSlogan":"收到了快放假","bOrder":4,"bLabel":"审批快,额度高","approveSpeed":"4-9天","avgAmount":"451万","applyBase":415},{"id":"9f336d3e-5faa-4c56-900f-61f16cafe63f","createdTime":1500968626000,"updatedTime":1500972094000,"status":"NORMAL","bName":"兴业银行","bLogo":"8209e5e2-0e64-435d-8f3f-b7299eb7bbcd","bUrl":"http://asdjfsdf","recommendSlogan":"打扫房间","bOrder":2,"bLabel":"审批快","approveSpeed":"2-9天","avgAmount":"545万","applyBase":564163}]
             * number : 0
             * size : 500
             * totalPages : 1
             * numberOfElements : 2
             * totalElements : 2
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
            private List<ContentBean> content;

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

            public List<ContentBean> getContent() {
                return content;
            }

            public void setContent(List<ContentBean> content) {
                this.content = content;
            }

            public static class ContentBean {
                /**
                 * id : 8ae81014-9d88-41e9-92ba-abd6f9fc9a2c
                 * createdTime : 1500968318000
                 * updatedTime : 1500971525000
                 * status : NORMAL
                 * bName : 浦发银行
                 * bLogo : 8c14ed2a-5896-4e26-86ce-d9c94eea158d
                 * bUrl : http://asdjfsdf
                 * recommendSlogan : 收到了快放假
                 * bOrder : 4
                 * bLabel : 审批快,额度高
                 * approveSpeed : 4-9天
                 * avgAmount : 451万
                 * applyBase : 415
                 */

                private String id;
                private long createdTime;
                private long updatedTime;
                private String status;
                private String bName;
                private String bLogo;
                private String bUrl;
                private String recommendSlogan;
                private int bOrder;
                private String bLabel;
                private String approveSpeed;
                private String avgAmount;
                private int applyBase;

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

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public String getBName() {
                    return bName;
                }

                public void setBName(String bName) {
                    this.bName = bName;
                }

                public String getBLogo() {
                    return bLogo;
                }

                public void setBLogo(String bLogo) {
                    this.bLogo = bLogo;
                }

                public String getBUrl() {
                    return bUrl;
                }

                public void setBUrl(String bUrl) {
                    this.bUrl = bUrl;
                }

                public String getRecommendSlogan() {
                    return recommendSlogan;
                }

                public void setRecommendSlogan(String recommendSlogan) {
                    this.recommendSlogan = recommendSlogan;
                }

                public int getBOrder() {
                    return bOrder;
                }

                public void setBOrder(int bOrder) {
                    this.bOrder = bOrder;
                }

                public String getBLabel() {
                    return bLabel;
                }

                public void setBLabel(String bLabel) {
                    this.bLabel = bLabel;
                }

                public String getApproveSpeed() {
                    return approveSpeed;
                }

                public void setApproveSpeed(String approveSpeed) {
                    this.approveSpeed = approveSpeed;
                }

                public String getAvgAmount() {
                    return avgAmount;
                }

                public void setAvgAmount(String avgAmount) {
                    this.avgAmount = avgAmount;
                }

                public int getApplyBase() {
                    return applyBase;
                }

                public void setApplyBase(int applyBase) {
                    this.applyBase = applyBase;
                }
            }
        }
    }
}
