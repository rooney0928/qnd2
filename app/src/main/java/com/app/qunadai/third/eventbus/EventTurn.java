package com.app.qunadai.third.eventbus;

/**
 * Created by wayne on 2017/5/25.
 */

public class EventTurn {
    private int page;
    private String type;

    public EventTurn(int page) {
        this.page = page;
    }

    public EventTurn(int page, String type) {
        this.page = page;
        this.type = type;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
