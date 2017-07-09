package com.app.qunadai.bean.bbs;

import java.util.List;

/**
 * Created by wayne on 2017/7/9.
 */

public class TalentBean {

    /**
     * code : 000
     * content : {"article":{"content":[{"pictures":["246e6e95-7bdb-4c06-9189-1cb464c66296","c0afdd72-0b2d-4694-b44b-3a996a1f6598"],"articleType":"POSTING","browseAmount":0,"content":"更改好文2","createdTime":1498702732000,"id":"558632b2-5226-4207-ae01-594607251abb","thumbUpAmount":0,"title":"更改测试2","updatedTime":1498703376000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"},{"pictures":["246e6e95-7bdb-4c06-9189-1cb464c66296","c0afdd72-0b2d-4694-b44b-3a996a1f6598"],"articleType":"POSTING","browseAmount":0,"content":"好文1","createdTime":1498702682000,"id":"e731632f-03d8-4168-b132-abb45383f027","thumbUpAmount":0,"title":"测试1","updatedTime":1498702682000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"}],"first":true,"firstPage":false,"last":true,"lastPage":false,"nextPage":false,"number":0,"numberOfElements":2,"previousPage":false,"size":10,"sort":1,"totalElements":2,"totalPages":1}}
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
         * article : {"content":[{"pictures":["246e6e95-7bdb-4c06-9189-1cb464c66296","c0afdd72-0b2d-4694-b44b-3a996a1f6598"],"articleType":"POSTING","browseAmount":0,"content":"更改好文2","createdTime":1498702732000,"id":"558632b2-5226-4207-ae01-594607251abb","thumbUpAmount":0,"title":"更改测试2","updatedTime":1498703376000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"},{"pictures":["246e6e95-7bdb-4c06-9189-1cb464c66296","c0afdd72-0b2d-4694-b44b-3a996a1f6598"],"articleType":"POSTING","browseAmount":0,"content":"好文1","createdTime":1498702682000,"id":"e731632f-03d8-4168-b132-abb45383f027","thumbUpAmount":0,"title":"测试1","updatedTime":1498702682000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"}],"first":true,"firstPage":false,"last":true,"lastPage":false,"nextPage":false,"number":0,"numberOfElements":2,"previousPage":false,"size":10,"sort":1,"totalElements":2,"totalPages":1}
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
             * content : [{"pictures":["246e6e95-7bdb-4c06-9189-1cb464c66296","c0afdd72-0b2d-4694-b44b-3a996a1f6598"],"articleType":"POSTING","browseAmount":0,"content":"更改好文2","createdTime":1498702732000,"id":"558632b2-5226-4207-ae01-594607251abb","thumbUpAmount":0,"title":"更改测试2","updatedTime":1498703376000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"},{"pictures":["246e6e95-7bdb-4c06-9189-1cb464c66296","c0afdd72-0b2d-4694-b44b-3a996a1f6598"],"articleType":"POSTING","browseAmount":0,"content":"好文1","createdTime":1498702682000,"id":"e731632f-03d8-4168-b132-abb45383f027","thumbUpAmount":0,"title":"测试1","updatedTime":1498702682000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"}]
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
