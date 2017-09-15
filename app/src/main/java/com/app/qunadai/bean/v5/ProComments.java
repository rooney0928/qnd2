package com.app.qunadai.bean.v5;

import java.util.List;

/**
 * Created by wayne on 2017/9/14.
 */

public class ProComments {

    /**
     * comments : {"content":[{"useravatar":"测试内容x1v8","usernick":"测试内容7osc","content":"lalala","createdTime":1505354935000,"id":"f372d8d3-0329-45fa-9309-7f64230a4ef4","productId":"0f2b4ed2-97ca-4bb8-aa16-95df0327e2dd","stars":4,"updatedTime":1505354935000,"userId":"1df346bd-9f3c-4c9f-ae31-95e11fe6126e"},{"useravatar":"测试内容x1v8","usernick":"测试内容7osc","content":"hahaha","createdTime":1505353487000,"id":"bed6ac20-510f-408b-89de-cf8cf5b6c001","productId":"0f2b4ed2-97ca-4bb8-aa16-95df0327e2dd","stars":5,"updatedTime":1505353487000,"userId":"1df346bd-9f3c-4c9f-ae31-95e11fe6126e"}],"first":true,"firstPage":false,"last":true,"lastPage":false,"nextPage":false,"number":0,"numberOfElements":2,"previousPage":false,"size":10,"sort":1,"totalElements":2,"totalPages":1}
     */

    private CommentsBean comments;

    public CommentsBean getComments() {
        return comments;
    }

    public void setComments(CommentsBean comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * content : [{"useravatar":"测试内容x1v8","usernick":"测试内容7osc","content":"lalala","createdTime":1505354935000,"id":"f372d8d3-0329-45fa-9309-7f64230a4ef4","productId":"0f2b4ed2-97ca-4bb8-aa16-95df0327e2dd","stars":4,"updatedTime":1505354935000,"userId":"1df346bd-9f3c-4c9f-ae31-95e11fe6126e"},{"useravatar":"测试内容x1v8","usernick":"测试内容7osc","content":"hahaha","createdTime":1505353487000,"id":"bed6ac20-510f-408b-89de-cf8cf5b6c001","productId":"0f2b4ed2-97ca-4bb8-aa16-95df0327e2dd","stars":5,"updatedTime":1505353487000,"userId":"1df346bd-9f3c-4c9f-ae31-95e11fe6126e"}]
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
        private List<ProComment> content;

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

        public List<ProComment> getContent() {
            return content;
        }

        public void setContent(List<ProComment> content) {
            this.content = content;
        }

    }
}
