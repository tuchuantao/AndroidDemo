package com.kevin.demo.module.touch

import android.content.Context
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
        Log.d(this::class.java.simpleName, "dispatchTouchEvent() event=" + event.action)
        return super.dispatchTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        Log.d(this::class.java.simpleName, "onInterceptTouchEvent() event=" + event.action)
        if (event.action == MotionEvent.ACTION_MOVE) { // 触发子View ACTION_CANCEL事件
            return true
        }
        return super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.d(this::class.java.simpleName, "onTouchEvent() event=" + event.action)
//        return super.onTouchEvent(event)
        return true
    }
}