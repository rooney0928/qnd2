package com.app.qunadai.third.eventbus;

/**
 * Created by wayne on 2017/7/7.
 */

public class EventRefresh {
    private String type;

    public EventRefresh() {
    }

    public EventRefresh(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
