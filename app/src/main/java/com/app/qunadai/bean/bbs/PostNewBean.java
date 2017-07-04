package com.app.qunadai.bean.bbs;

import java.util.List;

/**
 * Created by wayne on 2017/6/30.
 */

public class PostNewBean {

    /**
     * code : 000
     * content : {"article":{"articleType":"POSTING","browseAmount":0,"content":"好文2","createdTime":1498706199446,"id":"24a62c93-01e3-421e-9677-2ea374ee3aed","pictures":["e6762425-0678-42be-a6e5-a4cd26031e7b","8918f7ab-6880-41b4-8972-9498a4706562"],"thumbUpAmount":0,"title":"测试2","updatedTime":1498706199446,"userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2"}}
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
         * article : {"articleType":"POSTING","browseAmount":0,"content":"好文2","createdTime":1498706199446,"id":"24a62c93-01e3-421e-9677-2ea374ee3aed","pictures":["e6762425-0678-42be-a6e5-a4cd26031e7b","8918f7ab-6880-41b4-8972-9498a4706562"],"thumbUpAmount":0,"title":"测试2","updatedTime":1498706199446,"userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2"}
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
             * articleType : POSTING
             * browseAmount : 0
             * content : 好文2
             * createdTime : 1498706199446
             * id : 24a62c93-01e3-421e-9677-2ea374ee3aed
             * pictures : ["e6762425-0678-42be-a6e5-a4cd26031e7b","8918f7ab-6880-41b4-8972-9498a4706562"]
             * thumbUpAmount : 0
             * title : 测试2
             * updatedTime : 1498706199446
             * userId : 3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2
             */

            private String articleType;
            private int browseAmount;
            private String content;
            private long createdTime;
            private String id;
            private int thumbUpAmount;
            private String title;
            private long updatedTime;
            private String userId;
            private List<String> pictures;

            public String getArticleType() {
                return articleType;
            }

            public void setArticleType(String articleType) {
                this.articleType = articleType;
            }

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

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public List<String> getPictures() {
                return pictures;
            }

            public void setPictures(List<String> pictures) {
                this.pictures = pictures;
            }
        }
    }
}
