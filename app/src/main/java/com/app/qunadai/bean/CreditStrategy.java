package com.app.qunadai.bean;

import java.util.List;

/**
 * Created by wayne on 2017/7/25.
 */

public class CreditStrategy {

    /**
     * code : 000
     * content : {"CreditCardStrategy":{"content":[{"browseAmount":0,"content":"哈哈哈哈哈哈","createdTime":1500533482000,"id":"65593725-4e85-4e1e-bb9f-136d9db17566","status":"NORMAL","thumbDownAmount":0,"thumbUpAmount":0,"title":"测试攻略2.0","updatedTime":1500544303000},{"browseAmount":0,"content":"hhhh","createdTime":1500544286000,"id":"f0aeaee0-c181-4c26-bb8f-d846388c51cd","status":"NORMAL","thumbDownAmount":0,"thumbUpAmount":0,"title":"测试攻略1.0","updatedTime":1500544286000}],"first":true,"firstPage":false,"last":true,"lastPage":false,"nextPage":false,"number":0,"numberOfElements":2,"previousPage":false,"size":10,"sort":1,"totalElements":2,"totalPages":1}}
     * detail : 操作成功
     * msg : 操作成功
     */

    private String code;
    private ContentBeanX content;
    private String detail;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ContentBeanX getContent() {
        return content;
    }

    public void setContent(ContentBeanX content) {
        this.content = content;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class ContentBeanX {

        /**
         * CreditCardStrategy : {"content":[{"browseAmount":0,"content":"哈哈哈哈哈哈","createdTime":1500533482000,"id":"65593725-4e85-4e1e-bb9f-136d9db17566","status":"NORMAL","thumbDownAmount":0,"thumbUpAmount":0,"title":"测试攻略2.0","updatedTime":1500544303000},{"browseAmount":0,"content":"hhhh","createdTime":1500544286000,"id":"f0aeaee0-c181-4c26-bb8f-d846388c51cd","status":"NORMAL","thumbDownAmount":0,"thumbUpAmount":0,"title":"测试攻略1.0","updatedTime":1500544286000}],"first":true,"firstPage":false,"last":true,"lastPage":false,"nextPage":false,"number":0,"numberOfElements":2,"previousPage":false,"size":10,"sort":1,"totalElements":2,"totalPages":1}
         */

        private CreditCardStrategyBean CreditCardStrategy;

        public CreditCardStrategyBean getCreditCardStrategy() {
            return CreditCardStrategy;
        }

        public void setCreditCardStrategy(CreditCardStrategyBean CreditCardStrategy) {
            this.CreditCardStrategy = CreditCardStrategy;
        }

        public static class CreditCardStrategyBean {
            /**
             * content : [{"browseAmount":0,"content":"哈哈哈哈哈哈","createdTime":1500533482000,"id":"65593725-4e85-4e1e-bb9f-136d9db17566","status":"NORMAL","thumbDownAmount":0,"thumbUpAmount":0,"title":"测试攻略2.0","updatedTime":1500544303000},{"browseAmount":0,"content":"hhhh","createdTime":1500544286000,"id":"f0aeaee0-c181-4c26-bb8f-d846388c51cd","status":"NORMAL","thumbDownAmount":0,"thumbUpAmount":0,"title":"测试攻略1.0","updatedTime":1500544286000}]
             * first : true
             * firstPage : false
             * last : true
             * lastPage : false
             * nextPage : false
             * number : 0
             * numberOfElements : 2
             * previousPage : false
             * size : 10
             * sort : 1
             * totalElements : 2
             * totalPages : 1
             */

            private boolean first;
            private boolean firstPage;
            private boolean last;
            private boolean lastPage;
            private boolean nextPage;
            private int number;
            private int numberOfElements;
            private boolean previousPage;
            private int size;
            private int sort;
            private int totalElements;
            private int totalPages;
            private List<ContentBean> content;

            public boolean isFirst() {
                return first;
            }

            public void setFirst(boolean first) {
                this.first = first;
            }

            public boolean isFirstPage() {
                return firstPage;
            }

            public void setFirstPage(boolean firstPage) {
                this.firstPage = firstPage;
            }

            public boolean isLast() {
                return last;
            }

            public void setLast(boolean last) {
                this.last = last;
            }

            public boolean isLastPage() {
                return lastPage;
            }

            public void setLastPage(boolean lastPage) {
                this.lastPage = lastPage;
            }

            public boolean isNextPage() {
                return nextPage;
            }

            public void setNextPage(boolean nextPage) {
                this.nextPage = nextPage;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getNumberOfElements() {
                return numberOfElements;
            }

            public void setNumberOfElements(int numberOfElements) {
                this.numberOfElements = numberOfElements;
            }

            public boolean isPreviousPage() {
                return previousPage;
            }

            public void setPreviousPage(boolean previousPage) {
                this.previousPage = previousPage;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getTotalElements() {
                return totalElements;
            }

            public void setTotalElements(int totalElements) {
                this.totalElements = totalElements;
            }

            public int getTotalPages() {
                return totalPages;
            }

            public void setTotalPages(int totalPages) {
                this.totalPages = totalPages;
            }

            public List<ContentBean> getContent() {
                return content;
            }

            public void setContent(List<ContentBean> content) {
                this.content = content;
            }

            public static class ContentBean {
                /**
                 * browseAmount : 0
                 * content : 哈哈哈哈哈哈
                 * createdTime : 1500533482000
                 * id : 65593725-4e85-4e1e-bb9f-136d9db17566
                 * status : NORMAL
                 * thumbDownAmount : 0
                 * thumbUpAmount : 0
                 * title : 测试攻略2.0
                 * updatedTime : 1500544303000
                 */

                private int browseAmount;
                private String content;
                private long createdTime;
                private String id;
                private String status;
                private int thumbDownAmount;
                private int thumbUpAmount;
                private String title;
                private long updatedTime;

                public int getBrowseAmount() {
                    return browseAmount;
                }

                public void setBrowseAmount(int browseAmount) {
                    this.browseAmount = browseAmount;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public long getCreatedTime() {
                    return createdTime;
                }

                public void setCreatedTime(long createdTime) {
                    this.createdTime = createdTime;
                }

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

                public int getThumbDownAmount() {
                    return thumbDownAmount;
                }

                public void setThumbDownAmount(int thumbDownAmount) {
                    this.thumbDownAmount = thumbDownAmount;
                }

                public int getThumbUpAmount() {
                    return thumbUpAmount;
                }

                public void setThumbUpAmount(int thumbUpAmount) {
                    this.thumbUpAmount = thumbUpAmount;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public long getUpdatedTime() {
                    return updatedTime;
                }

                public void setUpdatedTime(long updatedTime) {
                    this.updatedTime = updatedTime;
                }
            }
        }
    }
}
