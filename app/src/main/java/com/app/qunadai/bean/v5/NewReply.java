package com.app.qunadai.bean.v5;

/**
 * Created by wayne on 2017/10/12.
 */

public class NewReply {

    /**
     * newComment : {"commentId":"7c78b0be-98bf-49f3-9feb-4515ca26ec2f","content":"支持","createdTime":1506396067144,"id":"a7910308-9a2c-4cff-a383-3004165cb550","replyNumber":0,"updatedTime":1506396067144,"userId":"1df346bd-9f3c-4c9f-ae31-95e11fe6126d"}
     */

    private NewCommentBean newComment;

    public NewCommentBean getNewComment() {
        return newComment;
    }

    public void setNewComment(NewCommentBean newComment) {
        this.newComment = newComment;
    }

    public static class NewCommentBean {
        /**
         * commentId : 7c78b0be-98bf-49f3-9feb-4515ca26ec2f
         * content : 支持
         * createdTime : 1506396067144
         * id : a7910308-9a2c-4cff-a383-3004165cb550
         * replyNumber : 0
         * updatedTime : 1506396067144
         * userId : 1df346bd-9f3c-4c9f-ae31-95e11fe6126d
         */

        private String commentId;
        private String content;
        private long createdTime;
        private String id;
        private int replyNumber;
        private long updatedTime;
        private String userId;

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
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

        public int getReplyNumber() {
            return replyNumber;
        }

        public void setReplyNumber(int replyNumber) {
            this.replyNumber = replyNumber;
        }

        public long getUpdatedTime() {
            return updatedTime;
        }

        public void setUpdatedTime(long updatedTime) {
            this.updatedTime = updatedTime;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
