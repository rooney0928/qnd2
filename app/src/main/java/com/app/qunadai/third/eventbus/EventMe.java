package com.app.qunadai.third.eventbus;

/**
 * Created by wayne on 2017/5/15.
 */

public class EventMe {
    private String nickname;

    public EventMe(String nickname) {
        this.nickname = nickname;
    }
    public EventMe() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
