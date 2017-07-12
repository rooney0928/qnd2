package com.app.qunadai.third.eventbus;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wayne on 2017/7/12.
 */

public class EventAlbum implements Serializable {
    private List<String> pics;
    private int position;

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
