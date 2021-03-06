package com.app.qunadai.bean.v5;

import java.io.Serializable;

/**
 * Created by wayne on 2017/9/14.
 */

public class ProComment implements Serializable {


    /**
     * id : 055cde1a-5e7e-4702-b331-bcdc05d82f53
     * createdTime : 1505789101000
     * updatedTime : 1505789101000
     * productId : 3d77f0bc-5c91-43ae-b337-323a319f30dd
     * userId : cb6b6eaf-d023-4c32-8261-d9ace9ab433d
     * useravatar : 6e60a833-a287-43e2-a246-3c72f9d49a74
     * usernick : 17301746631
     * content : 讲真真的还不错呢
     * latestReply : {"id":"5e3be48f-a4f5-11e7-a4a7-00163e1a1ccc","createdTime":1505789101000,"updatedTime":1505789101000,"productId":"3d77f0bc-5c91-43ae-b337-323a319f30dd","commentId":"055cde1a-5e7e-4702-b331-bcdc05d82f53","userId":"ae6b0df0-1db0-49f9-b1f9-d3af053f65f6","useravatar":"6e60a833-a287-43e2-a246-3c72f9d49a74","usernick":"汝等小儿，可敢杀我","content":"哈哈一级回复","replyNumber":0}
     * replyNumber : 6
     */

    private String id;
    private long createdTime;
    private long updatedTime;
    private String productId;
    private String userId;
    private String useravatar;
    private String usernick;
    private String content;
    private Reply latestReply;
    private int replyNumber;


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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUseravatar() {
        return useravatar;
    }

    public void setUseravatar(String useravatar) {
        this.useravatar = useravatar;
    }

    public String getUsernick() {
        return usernick;
    }

    public void setUsernick(String usernick) {
        this.usernick = usernick;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Reply getLatestReply() {
        return latestReply;
    }

    public void setLatestReply(Reply latestReply) {
        this.latestReply = latestReply;
    }

    public int getReplyNumber() {
        return replyNumber;
    }

    public void setReplyNumber(int replyNumber) {
        this.replyNumber = replyNumber;
    }

    @Override
    public Object clone() {
        ProComment comment = null;
        try {

            comment = (ProComment) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return comment;
    }
}
