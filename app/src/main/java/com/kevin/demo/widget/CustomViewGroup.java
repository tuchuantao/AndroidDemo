package com.kevin.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tuchuantao on 2022/4/24
 * Desc:
 */
public class CustomViewGroup extends ViewGroup {

  private boolean mIsMeasured = false;


  public CustomViewGroup(Context context) {
    super(context);
  }

  public CustomViewGroup(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  // NOTE: 定义控件下所有子控件所使用的layoutParams类 通过这种形式使你的控件可以按自己想要的方式和属性来操作它的子view
  @Override
  public LayoutParams generateLayoutParams(AttributeSet attrs) {
    return new MarginLayoutParams(getContext(), attrs);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int widthSize = MeasureSpec.getSize(widthMeasureSpec);
    int heightSize = MeasureSpec.getSize(heightMeasureSpec);

    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
    int heightMode = MeasureSpec.getMode(heightMeasureSpec);

    if (!mIsMeasured) {
      mIsMeasured = true; // onMeasure() 多次调用问题，首次会有问题
    } else {
      // 真正进行测量
    }

    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
       View child = getChildAt(i);
       // 先测子控件
       measureChildWithMargins(child, 0, widthMeasureSpec, heightMeasureSpec, 0);
       // MarginLayoutParams 由 generateLayoutParams() 指定
       MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
    }


    setMeasuredDimension(widthSize, heightSize);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {

  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }
}
