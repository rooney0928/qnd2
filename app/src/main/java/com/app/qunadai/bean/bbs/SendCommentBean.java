package com.app.qunadai.bean.bbs;

/**
 * Created by wayne on 2017/7/7.
 */

public class SendCommentBean {


    /**
     * code : 000
     * content : {"comment":{"article":{"articleType":"POSTING","browseAmount":0,"content":"好文4","createdTime":1498706199000,"id":"24a62c93-01e3-421e-9677-2ea374ee3aed","thumbUpAmount":0,"title":"测试4","updatedTime":1498706646000,"userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2"},"content":"我是水军1","createdTime":1498727884073,"id":"130acac9-7436-4d68-8683-2d5c106ce2ce","thumbUpAmount":0,"updatedTime":1498727884073,"userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2"}}
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
         * comment : {"article":{"articleType":"POSTING","browseAmount":0,"content":"好文4","createdTime":1498706199000,"id":"24a62c93-01e3-421e-9677-2ea374ee3aed","thumbUpAmount":0,"title":"测试4","updatedTime":1498706646000,"userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2"},"content":"我是水军1","createdTime":1498727884073,"id":"130acac9-7436-4d68-8683-2d5c106ce2ce","thumbUpAmount":0,"updatedTime":1498727884073,"userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2"}
         */

        private CommentBean comment;

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public static class CommentBean {
            /**
             * article : {"articleType":"POSTING","browseAmount":0,"content":"好文4","createdTime":1498706199000,"id":"24a62c93-01e3-421e-9677-2ea374ee3aed","thumbUpAmount":0,"title":"测试4","updatedTime":1498706646000,"userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2"}
             * content : 我是水军1
             * createdTime : 1498727884073
             * id : 130acac9-7436-4d68-8683-2d5c106ce2ce
             * thumbUpAmount : 0
             * updatedTime : 1498727884073
             * userId : 3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2
             */

            private Post article;
            private String content;
            private long createdTime;
            private String id;
            private int thumbUpAmount;
            private long updatedTime;
            private String userId;

            public Post getArticle() {
                return article;
            }

            public void setArticle(Post article) {
                this.article = article;
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

            public int getThumbUpAmount() {
                return thumbUpAmount;
            }

            public void setThumbUpAmount(int thumbUpAmount) {
                this.thumbUpAmount = thumbUpAmount;
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
}
