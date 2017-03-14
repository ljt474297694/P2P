package com.atguigu.p2p.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;


/**
 * Created by 李金桐 on 2017/3/13.
 * QQ: 474297694
 * 功能: xxxx
 */

public class MyScrollView extends ScrollView {

    private View childView;
    private Rect rect;
    //用于判断动画是否结束 如果动画进行中 不会相应滑动事件
    private boolean isAnimationEnd = true;
    private int lastX;
    private int lastY;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        rect = new Rect();
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        int eventY = (int) ev.getY();
//        int eventX = (int) ev.getX();
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                lastX = (int) ev.getX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                if (Math.abs(eventY - lastY) > Math.abs(eventX - lastX) && Math.abs(eventY - lastY) > UiUtils.dp2px(10)) {
//                    return true;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//
//
//                break;
//        }
//        return super.onInterceptTouchEvent(ev);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getChildCount() == 0 || !isAnimationEnd) {
            return super.onTouchEvent(ev);
        }
        int eventY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //保存第一次触摸到的点
                lastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isNeedMove()) {
                    if (lastY == 0) {
                        //如果等于0表示down事件丢失 从新赋值 防止滑动跳动
                        lastY = eventY;

                    }
                    if(rect.isEmpty()) {
                        rect.set(childView.getLeft(), childView.getTop(), childView.getRight(), childView.getBottom());
                    }
                    if(l!=null) {
                        l.onStartScroll();
                    }
                    int dy = eventY - lastY;
                    childView.layout(childView.getLeft(), childView.getTop() + dy / 2
                            , childView.getRight(), childView.getBottom() + dy / 2);
                }
                lastY = eventY;
                break;
            case MotionEvent.ACTION_UP:
                if (!rect.isEmpty()) {

                    int bottom = childView.getBottom() - rect.bottom;

                    TranslateAnimation animation = new TranslateAnimation(0, 0, 0, -bottom);
                    animation.setDuration(200);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isAnimationEnd = false;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //动画完成 可以下次拖动
                            isAnimationEnd = true;
                            //清除动画
                            childView.clearAnimation();
                            //回归原式位置
                            childView.layout(rect.left, rect.top, rect.right, rect.bottom);
                            //清空
                            lastY = 0; //赋0
                            lastX = 0;
                            if(l!=null) {
                                l.onEndScroll();
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    childView.startAnimation(animation);


                }

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
    public interface OnScrollListener{
        void onStartScroll();
        void onEndScroll();
    }
    private OnScrollListener l;

    public void setOnScrollListener(OnScrollListener l) {
        this.l = l;
    }
}
