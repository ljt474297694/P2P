package com.atguigu.p2p.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.atguigu.p2p.R;
import com.atguigu.p2p.utils.UiUtils;


/**
 * Created by 李金桐 on 2017/3/13.
 * QQ: 474297694
 * 功能: xxxx
 */

public class MyProgressBar extends View {

    private int viewHeight;
    private int viewWidth;
    private int progressWidth = UiUtils.dp2px(5);
    private Paint paint;
    private int circleColor = Color.GRAY;
    private int arcColor = Color.RED;
    private int sweepArc = 120;

    public MyProgressBar(Context context) {
        this(context, null);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.progress);
        array.recycle();
    }

    private void init() {
        paint = new Paint();

        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.STROKE);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = viewWidth / 2;
        int cy = viewHeight / 2;
        int radius = viewWidth / 2 - progressWidth / 2;
        //背景圆
        paint.setStrokeWidth(progressWidth);
        paint.setColor(circleColor);
        canvas.drawCircle(cx, cy, radius, paint);

        //前面的弧
        paint.setStrokeWidth(progressWidth);
        paint.setColor(arcColor);
        RectF rectf = new RectF(progressWidth / 2, progressWidth / 2, viewWidth - progressWidth / 2, viewHeight - progressWidth / 2);
        canvas.drawArc(rectf, 0, sweepArc, false, paint);

        //文字
        String text = sweepArc * 100 / 360 + "%";
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);

        float tx = viewWidth / 2 - bounds.width() / 2;
        float ty = viewHeight /2 + bounds.height() / 2;
        paint.setStrokeWidth(0);
        paint.setTextSize(UiUtils.dp2px(18));
        paint.setColor(Color.BLUE);
        canvas.drawText(text, tx, ty, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = getMeasuredHeight();
        viewWidth = getMeasuredWidth();
    }
}
