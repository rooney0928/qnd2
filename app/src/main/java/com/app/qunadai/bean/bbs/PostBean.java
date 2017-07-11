package com.app.qunadai.bean.bbs;

import java.util.List;

/**
 * Created by wayne on 2017/7/10.
 */

public class PostBean {

    /**
     * code : 000
     * content : {"article":{"articleType":"POSTING","browseAmount":11,"content":"更改好文2","createdTime":1498702732000,"id":"558632b2-5226-4207-ae01-594607251abb","pictures":["246e6e95-7bdb-4c06-9189-1cb464c66296","c0afdd72-0b2d-4694-b44b-3a996a1f6598"],"praisedByCurrentUser":true,"thumbUpAmount":1,"title":"更改测试2","updatedTime":1499049978000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"}}
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
         * article : {"articleType":"POSTING","browseAmount":11,"content":"更改好文2","createdTime":1498702732000,"id":"558632b2-5226-4207-ae01-594607251abb","pictures":["246e6e95-7bdb-4c06-9189-1cb464c66296","c0afdd72-0b2d-4694-b44b-3a996a1f6598"],"praisedByCurrentUser":true,"thumbUpAmount":1,"title":"更改测试2","updatedTime":1499049978000,"userAvatar":"916bbbd5-751d-4923-b581-9ba8e4037947","userId":"3ab7c4dd-2f2a-4667-aaf9-e44a2be778a2","userNick":"无情大毛腿"}
         */

        private Post article;

        public Post getArticle() {
            return article;
        }

        public void setArticle(Post article) {
            this.article = article;
        }

    }
}
