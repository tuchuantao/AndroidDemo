package com.kevin.demo.module.touch

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.LinearLayout

/**
 * Created by tuchuantao on 2021/8/3
 * Desc:
 */
class TouchChildLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        Log.d(this::class.java.simpleName, "dispatchTouchEvent() event=" + event.action  + " x=" + event.x + " y=" + event.y)
        if (event.action == MotionEvent.ACTION_MOVE) {
            if (event.x > 200) {
                Log.d(this::class.java.simpleName, "dispatchTouchEvent() event.x > 200")
                return true
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        Log.d(this::class.java.simpleName, "onInterceptTouchEvent() event=" + event.action)
//        if (event.action == MotionEvent.ACTION_MOVE) { // 触发子View ACTION_CANCEL事件
//            return true
//        }
        return super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d(this::class.java.simpleName, "onTouchEvent() event=" + event.action)
//        return super.onTouchEvent(event)
        return true
    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        Log.d(this::class.java.simpleName, "onMeasure()")
//    }
//
//    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
//        super.onLayout(changed, l, t, r, b)
//        Log.d(this::class.java.simpleName, "onLayout()")
//    }
//
//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//        Log.d(this::class.java.simpleName, "onDraw()")
//    }
//
//    override fun draw(canvas: Canvas?) {
//        super.draw(canvas)
//        Log.d(this::class.java.simpleName, "draw()")
//    }
}