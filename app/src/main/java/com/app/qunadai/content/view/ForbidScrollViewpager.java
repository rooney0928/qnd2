/**
 *
 */
package com.app.qunadai.content.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @since 2015年11月5日
 */
public class ForbidScrollViewpager extends ViewPager {

    private boolean canScroll = false;

    /**
     * @param context
     * @param attrs
     */
    public ForbidScrollViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param context
     */
    public ForbidScrollViewpager(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }


    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        if (canScroll == false) {
            return false;
        } else {
            return super.onInterceptTouchEvent(arg0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // TODO Auto-generated method stub
        if (canScroll == false) {
            return false;
        } else {
            return super.onTouchEvent(arg0);
        }
    }
}
