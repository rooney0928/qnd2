package com.app.qunadai.content.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by wayne on 2017/9/7.
 */

public class FullRecyclerView extends RecyclerView {
    public FullRecyclerView(Context context) {
        super(context);
    }

    public FullRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canScrollVertically(int direction) {
        // check if scrolling up
        if (direction < 1) {
            boolean original = super.canScrollVertically(direction);
            return !original && getChildAt(0) != null && getChildAt(0).getTop() < 0 || original;
        }
        return super.canScrollVertically(direction);

    }

}
