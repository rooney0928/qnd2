package com.app.qunadai.third.eventbus;

/**
 * Created by wayne on 2017/9/30.
 */

public class EventOffline {
    private String type;

    public EventOffline() {
    }

    public EventOffline(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
