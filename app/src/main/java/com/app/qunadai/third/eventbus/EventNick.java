package com.app.qunadai.third.eventbus;

/**
 * Created by wayne on 2017/5/15.
 */

public class EventNick {
    private String nickname;

    public EventNick(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
