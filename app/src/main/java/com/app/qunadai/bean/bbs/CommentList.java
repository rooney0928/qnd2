package com.app.qunadai.bean.bbs;

import java.util.List;

/**
 * Created by wayne on 2017/7/7.
 */

public class CommentList {


    /**
     * code : 000
     * content : {"commentList":{"content":[{"createdTime":1498808526000,"id":"1e44b1b9-7ab5-426a-a50a-234f186afa2c","praisedByCurrentUser":false,"thumbUpAmount":0,"updatedTime":1498808526000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"},{"createdTime":1498727700000,"id":"93742657-8945-44c1-a2bb-8cc1217f4bf2","praisedByCurrentUser":false,"thumbUpAmount":0,"updatedTime":1498727700000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"},{"createdTime":1498717108000,"id":"5238129a-9315-4739-9174-d10d69ebb4b7","praisedByCurrentUser":false,"thumbUpAmount":0,"updatedTime":1498719453000,"userAvatar":"undefined","userId":"a302b103-a067-4339-bb2c-659af0ef55b1","userNick":"13000111222"},{"createdTime":1498717001000,"id":"34e685f6-6465-4d63-b05f-01db19be1d64","praisedByCurrentUser":false,"thumbUpAmount":0,"updatedTime":1498822362000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"}],"first":true,"firstPage":false,"last":true,"lastPage":false,"nextPage":false,"number":0,"numberOfElements":4,"previousPage":false,"size":10,"sort":1,"totalElements":4,"totalPages":1}}
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
         * commentList : {"content":[{"createdTime":1498808526000,"id":"1e44b1b9-7ab5-426a-a50a-234f186afa2c","praisedByCurrentUser":false,"thumbUpAmount":0,"updatedTime":1498808526000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"},{"createdTime":1498727700000,"id":"93742657-8945-44c1-a2bb-8cc1217f4bf2","praisedByCurrentUser":false,"thumbUpAmount":0,"updatedTime":1498727700000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"},{"createdTime":1498717108000,"id":"5238129a-9315-4739-9174-d10d69ebb4b7","praisedByCurrentUser":false,"thumbUpAmount":0,"updatedTime":1498719453000,"userAvatar":"undefined","userId":"a302b103-a067-4339-bb2c-659af0ef55b1","userNick":"13000111222"},{"createdTime":1498717001000,"id":"34e685f6-6465-4d63-b05f-01db19be1d64","praisedByCurrentUser":false,"thumbUpAmount":0,"updatedTime":1498822362000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"}],"first":true,"firstPage":false,"last":true,"lastPage":false,"nextPage":false,"number":0,"numberOfElements":4,"previousPage":false,"size":10,"sort":1,"totalElements":4,"totalPages":1}
         */

        private CommentListBean commentList;

        public CommentListBean getCommentList() {
            return commentList;
        }

        public void setCommentList(CommentListBean commentList) {
            this.commentList = commentList;
        }

        public static class CommentListBean {
            /**
             * content : [{"createdTime":1498808526000,"id":"1e44b1b9-7ab5-426a-a50a-234f186afa2c","praisedByCurrentUser":false,"thumbUpAmount":0,"updatedTime":1498808526000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"},{"createdTime":1498727700000,"id":"93742657-8945-44c1-a2bb-8cc1217f4bf2","praisedByCurrentUser":false,"thumbUpAmount":0,"updatedTime":1498727700000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"},{"createdTime":1498717108000,"id":"5238129a-9315-4739-9174-d10d69ebb4b7","praisedByCurrentUser":false,"thumbUpAmount":0,"updatedTime":1498719453000,"userAvatar":"undefined","userId":"a302b103-a067-4339-bb2c-659af0ef55b1","userNick":"13000111222"},{"createdTime":1498717001000,"id":"34e685f6-6465-4d63-b05f-01db19be1d64","praisedByCurrentUser":false,"thumbUpAmount":0,"updatedTime":1498822362000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"}]
             * first : true
             * firstPage : false
             * last : true
             * lastPage : false
             * nextPage : false
             * number : 0
             * numberOfElements : 4
             * previousPage : false
             * size : 10
             * sort : 1
             * totalElements : 4
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
            private List<Comment> content;

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

            public List<Comment> getContent() {
                return content;
            }

            public void setContent(List<Comment> content) {
                this.content = content;
            }

        }
    }
}
