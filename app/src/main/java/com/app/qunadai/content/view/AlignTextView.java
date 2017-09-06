package com.app.qunadai.content.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.TextView;

/**
 * Created by wayne on 2017/9/6.
 */

public class AlignTextView extends TextView {
    public AlignTextView(Context context) {
        super(context);
        init();

    }

    public AlignTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public AlignTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
//                initTextInfo();
                return true;
            }
        });
    }

}
