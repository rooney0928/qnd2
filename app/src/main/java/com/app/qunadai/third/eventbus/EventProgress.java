package com.app.qunadai.third.eventbus;

/**
 * Created by wayne on 2017/7/11.
 */

public class EventProgress {
    private boolean show;

    public EventProgress(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
