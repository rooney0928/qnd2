package com.app.qunadai.bean.bbs;

/**
 * Created by wayne on 2017/7/7.
 */

public class Comment {


    /**
     * id : a45d5879-5af4-45b3-886d-2b1839373f90
     * createdTime : 1499410337000
     * updatedTime : 1499410337000
     * content : 4444444
     * userId : ae6b0df0-1db0-49f9-b1f9-d3af053f65f6
     * thumbUpAmount : 0
     * userNick : 呵呵哦哦来了
     * userAvatar : 04d5fa81-3b1e-4635-8c51-01ce3f904001
     * praisedByCurrentUser : false
     */

    private String id;
    private long createdTime;
    private long updatedTime;
    private String content;
    private String userId;
    private int thumbUpAmount;
    private String userNick;
    private String userAvatar;
    private boolean praisedByCurrentUser;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getThumbUpAmount() {
        return thumbUpAmount;
    }

    public void setThumbUpAmount(int thumbUpAmount) {
        this.thumbUpAmount = thumbUpAmount;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public boolean isPraisedByCurrentUser() {
        return praisedByCurrentUser;
    }

    public void setPraisedByCurrentUser(boolean praisedByCurrentUser) {
        this.praisedByCurrentUser = praisedByCurrentUser;
    }
}
