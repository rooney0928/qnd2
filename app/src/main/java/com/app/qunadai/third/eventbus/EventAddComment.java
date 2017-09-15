package com.app.qunadai.third.eventbus;

import com.app.qunadai.bean.v5.ProComment;

/**
 * Created by wayne on 2017/9/14.
 */

public class EventAddComment {
    private ProComment proComment;

    public EventAddComment() {
    }
    public EventAddComment(ProComment proComment) {
        this.proComment = proComment;
    }

    public ProComment getProComment() {
        return proComment;
    }

    public void setProComment(ProComment proComment) {
        this.proComment = proComment;
    }
}
