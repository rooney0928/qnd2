package com.app.qunadai.bean.bbs;

/**
 * Created by wayne on 2017/7/10.
 */

public class PraiseBean {


    /**
     * code : 000
     * content : {"thumbUp":{"createdTime":1498822086000,"id":"17641563-cbfc-4b1c-8c71-ab650c323dfd","status":"DELETED","textId":"34e685f6-6465-4d63-b05f-01db19be1d64","thumbUpType":"COMMENT","updatedTime":1498822361414,"userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2"}}
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
         * thumbUp : {"createdTime":1498822086000,"id":"17641563-cbfc-4b1c-8c71-ab650c323dfd","status":"DELETED","textId":"34e685f6-6465-4d63-b05f-01db19be1d64","thumbUpType":"COMMENT","updatedTime":1498822361414,"userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2"}
         */

        private ThumbUpBean thumbUp;

        public ThumbUpBean getThumbUp() {
            return thumbUp;
        }

        public void setThumbUp(ThumbUpBean thumbUp) {
            this.thumbUp = thumbUp;
        }

        public static class ThumbUpBean {
            /**
             * createdTime : 1498822086000
             * id : 17641563-cbfc-4b1c-8c71-ab650c323dfd
             * status : DELETED
             * textId : 34e685f6-6465-4d63-b05f-01db19be1d64
             * thumbUpType : COMMENT
             * updatedTime : 1498822361414
             * userId : 3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2
             */

            private long createdTime;
            private String id;
            private String status;
            private String textId;
            private String thumbUpType;
            private long updatedTime;
            private String userId;

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

            public String getTextId() {
                return textId;
            }

            public void setTextId(String textId) {
                this.textId = textId;
            }

            public String getThumbUpType() {
                return thumbUpType;
            }

            public void setThumbUpType(String thumbUpType) {
                this.thumbUpType = thumbUpType;
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
