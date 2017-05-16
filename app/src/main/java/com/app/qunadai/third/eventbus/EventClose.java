package com.app.qunadai.third.eventbus;

/**
 * Created by wayne on 2017/5/14.
 */

public class EventClose {
    private String page;

    public EventClose(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
