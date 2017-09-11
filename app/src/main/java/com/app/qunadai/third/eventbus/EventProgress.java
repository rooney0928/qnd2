package com.app.qunadai.third.eventbus;

/**
 * Created by wayne on 2017/7/11.
 */

public class EventProgress {
    private boolean show;
    private String type;

    public EventProgress(boolean show) {
        this.show = show;
    }

    public EventProgress(boolean show, String type) {
        this.show = show;
        this.type = type;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
