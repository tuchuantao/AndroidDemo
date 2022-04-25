package com.kevin.demo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.kevin.demo.util.ScreenUtil;

/**
 * PorterDuffXfermode
 *
 * https://www.jianshu.com/p/19997b0b5b24
 */
public class CustomView extends View {

    private int dp50;
    private int dp5;
    private Paint mPaint;
    private PorterDuffXfermode mPorterDuffXfermode;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dp50 = ScreenUtil.INSTANCE.dip2px(getContext(), 50);
        dp5 = ScreenUtil.INSTANCE.dip2px(getContext(), 5);


        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setFilterBitmap(false);
        mPaint.setColor(Color.RED);
        mPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 在布局发生变化时回调，重新获取宽高
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @SuppressLint("NewApi")
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // onResume()后需要重新绘制
        mPaint.setXfermode(null);
        mPaint.setColor(Color.RED);

        // 建立一个透明层来进行裁剪
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), mPaint);

        canvas.drawRect(150, 150, 400, 400, mPaint);

        mPaint.setColor(Color.YELLOW);
        mPaint.setXfermode(mPorterDuffXfermode);
//        canvas.drawRect(dp5, 0, dp5 * 3, getHeight(), mPaint);

        canvas.drawCircle(150, 150, 100, mPaint);

        canvas.drawRoundRect(300, 300, 500, 500, dp5, dp5, mPaint);

        canvas.drawRoundRect(300, 100, 500, 200, dp5, dp5, mPaint);

        canvas.restoreToCount(saveCount);
    }
}
