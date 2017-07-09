package com.app.qunadai.bean.bbs;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wayne on 2017/7/4.
 */

public class Post implements Serializable{


    /**
     * id : bb81e256-5332-4e85-8b38-4b4bd980b557
     * createdTime : 1499413092000
     * updatedTime : 1499413767000
     * title : 爸爸
     你懂的
     * content : 我不懂
     * userId : ae6b0df0-1db0-49f9-b1f9-d3af053f65f6
     * userNick : 呵呵哦哦来了
     * userAvatar : 04d5fa81-3b1e-4635-8c51-01ce3f904001
     * thumbUpAmount : 1
     * browseAmount : 3
     * articleType : POSTING
     * pictures : []
     * praisedByCurrentUser : false
     */

    private String id;
    private long createdTime;
    private long updatedTime;
    private String title;
    private String content;
    private String userId;
    private String userNick;
    private String userAvatar;
    private int thumbUpAmount;
    private int browseAmount;
    private String articleType;
    private boolean praisedByCurrentUser;
    private List<String> pictures;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getThumbUpAmount() {
        return thumbUpAmount;
    }

    public void setThumbUpAmount(int thumbUpAmount) {
        this.thumbUpAmount = thumbUpAmount;
    }

    public int getBrowseAmount() {
        return browseAmount;
    }

    public void setBrowseAmount(int browseAmount) {
        this.browseAmount = browseAmount;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public boolean isPraisedByCurrentUser() {
        return praisedByCurrentUser;
    }

    public void setPraisedByCurrentUser(boolean praisedByCurrentUser) {
        this.praisedByCurrentUser = praisedByCurrentUser;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }
}
