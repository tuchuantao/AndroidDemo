package com.kevin.demo.module.touch

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.widget.LinearLayout

/**
 * Created by tuchuantao on 2021/8/3
 * Desc:
 */
class TouchFatherLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        Log.d(this::class.java.simpleName, "dispatchTouchEvent() event=" + event.action)
        return super.dispatchTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        Log.d(this::class.java.simpleName, "onInterceptTouchEvent() event=" + event.action)
//        if (event.action == ACTION_MOVE) {
//            return true
//        }
        return super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d(this::class.java.simpleName, "onTouchEvent() event=" + event.action)
        return super.onTouchEvent(event)
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
}