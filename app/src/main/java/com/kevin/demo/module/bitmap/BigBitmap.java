package com.kevin.demo.module.bitmap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tuchuantao on 2022/4/18
 * Desc:
 */
public class BigBitmap extends ViewGroup implements View.OnTouchListener {

  // 手势检查器 （长图需要通过手势滑动、双击放大、拖动、缩小）
  private GestureDetector mGestureDetector;


  public BigBitmap(Context context) {
    super(context);
  }

  public BigBitmap(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public BigBitmap(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private void init() {
    setOnTouchListener(this::onTouch);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {

  }


  @Override
  public boolean onTouch(View v, MotionEvent event) {
    // onTouch() 执行在 onTouchEvent() 之前
    mGestureDetector.onTouchEvent(event);
    return false;
  }
}
