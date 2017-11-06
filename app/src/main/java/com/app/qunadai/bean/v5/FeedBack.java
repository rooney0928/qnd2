package com.app.qunadai.bean.v5;

/**
 * Created by wayne on 2017/11/6.
 */

public class FeedBack {

    /**
     * id : be8bce5a-afb1-4b66-9ab5-2fd1dc5ee981
     * createdTime : 1509963634919
     * updatedTime : 1509963634919
     * userId : ae6b0df0-1db0-49f9-b1f9-d3af053f65f6
     * message : 我胡梅尔斯就算是饿死也不会吃拜仁一口饭
     */

    private String id;
    private long createdTime;
    private long updatedTime;
    private String userId;
    private String message;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
