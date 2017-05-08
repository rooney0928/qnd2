package com.app.qunadai.content.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Administrator on 2017/3/30.
 */

public class ProductDetailBottomBarBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = "ProductDetailBottomBarBehavior";

    private ObjectAnimator showAnimator;
    private ObjectAnimator hideAnimator;

    private boolean isShow = true;

    private int bottomViewHeight;
    private int currentTranslationY;

    public ProductDetailBottomBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);

        if (dy > 0) {
            //hide();
            hide(child);
        } else if (dy < 0) {
            show(child);
        }
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }


    public void hide(final View view) {
        if (hideAnimator == null) {
            hideAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 0f, view.getHeight());
            hideAnimator.setInterpolator(new LinearInterpolator());

            hideAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isShow = false;
                }
            });

            hideAnimator.setDuration(200);
        }

        if (hideAnimator.isRunning() || !isShow) {
            return;
        }

        hideAnimator.setFloatValues(0, view.getHeight());
        if (showAnimator != null && showAnimator.isRunning()) {
            showAnimator.cancel();
            hideAnimator.setFloatValues(view.getTranslationY(), view.getHeight());
        }

        hideAnimator.start();
    }

    public void show(final View view) {
        if (showAnimator == null) {
            showAnimator = ObjectAnimator.ofFloat(view, "translationY", view.getHeight(), 0);
            showAnimator.setInterpolator(new LinearInterpolator());

            showAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    isShow = true;
                }
            });

            showAnimator.setDuration(200);
        }

        if (showAnimator.isRunning() || isShow) {
            return;
        }

        showAnimator.setFloatValues(view.getHeight(), 0);
        if (hideAnimator != null && hideAnimator.isRunning()) {
            hideAnimator.cancel();
            showAnimator.setFloatValues(view.getTranslationY(), 0);
        }
        showAnimator.start();
    }

}
