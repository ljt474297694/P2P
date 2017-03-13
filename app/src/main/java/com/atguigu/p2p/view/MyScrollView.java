package com.atguigu.p2p.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;


/**
 * Created by 李金桐 on 2017/3/13.
 * QQ: 474297694
 * 功能: xxxx
 */

public class MyScrollView extends ScrollView {
    private int lastY;
    private View childView;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getChildCount() == 0) {
            return super.onTouchEvent(ev);
        }
        int eventY = (int)ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //保存第一次触摸到的点
                lastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isNeedMove()) {
                    int dy = eventY - lastY;
                    childView.layout(childView.getLeft(),childView.getTop()+dy/2
                                    ,childView.getRight(),childView.getBottom()+dy/2);
                }
                lastY = eventY;
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.onTouchEvent(ev);
    }

    private boolean isNeedMove() {
        int scrollViewY = getMeasuredHeight();
        int childViewY = childView.getMeasuredHeight();

        int dy = childViewY - scrollViewY;
        //拿到滑动的距离  往下滑动是变小 往上滑动是变大

        if (getScrollY() <= 0 || getScrollY() >= dy) {
            return true;
        }
        return false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //这个方法是在布局加载完成后调用的
        //判断
        if (getChildCount() > 0) {
            childView = getChildAt(0);
        }
    }
}
