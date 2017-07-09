package com.app.qunadai.bean.bbs;

import java.util.List;

/**
 * Created by wayne on 2017/7/8.
 */

public class StrategyBean {

    /**
     * code : 000
     * content : {"article":{"content":[{"articleType":"STRATEGY","browseAmount":12,"content":"这是攻略1","createdTime":1498702682000,"id":"e731632f-03d8-4168-b132-abb45383f027","pictures":{},"thumbUpAmount":0,"title":"攻略1","updatedTime":1498793578000}],"first":true,"firstPage":false,"last":true,"lastPage":false,"nextPage":false,"number":0,"numberOfElements":1,"previousPage":false,"size":10,"sort":1,"totalElements":1,"totalPages":1}}
     * detail : 操作成功
     * msg : 操作成功
     */

    private String code;
    private ContentBean content;
    private String detail;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
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

    public static class ContentBean {
        /**
         * article : {"content":[{"articleType":"STRATEGY","browseAmount":12,"content":"这是攻略1","createdTime":1498702682000,"id":"e731632f-03d8-4168-b132-abb45383f027","pictures":{},"thumbUpAmount":0,"title":"攻略1","updatedTime":1498793578000}],"first":true,"firstPage":false,"last":true,"lastPage":false,"nextPage":false,"number":0,"numberOfElements":1,"previousPage":false,"size":10,"sort":1,"totalElements":1,"totalPages":1}
         */

        private ArticleBean article;

        public ArticleBean getArticle() {
            return article;
        }

        public void setArticle(ArticleBean article) {
            this.article = article;
        }

        public static class ArticleBean {
            /**
             * content : [{"articleType":"STRATEGY","browseAmount":12,"content":"这是攻略1","createdTime":1498702682000,"id":"e731632f-03d8-4168-b132-abb45383f027","pictures":{},"thumbUpAmount":0,"title":"攻略1","updatedTime":1498793578000}]
             * first : true
             * firstPage : false
             * last : true
             * lastPage : false
             * nextPage : false
             * number : 0
             * numberOfElements : 1
             * previousPage : false
             * size : 10
             * sort : 1
             * totalElements : 1
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
            private List<Post> content;

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

            public List<Post> getContent() {
                return content;
            }

            public void setContent(List<Post> content) {
                this.content = content;
            }

        }
    }
}
