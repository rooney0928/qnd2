package com.app.qunadai.third.eventbus;

import com.app.qunadai.bean.cpl.UserInfo;

/**
 * Created by wayne on 2017/11/8.
 */

public class EventCplInfo {
    private UserInfo userInfo;
    private int page;

    public EventCplInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public EventCplInfo(UserInfo userInfo, int page) {
        this.userInfo = userInfo;
        this.page = page;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
