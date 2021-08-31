package com.kevin.demo.module.touch

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatTextView

/**
 * Created by tuchuantao on 2021/8/3
 * Desc:
 */
class TouchTextView: AppCompatTextView {

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
//        parent?.requestDisallowInterceptTouchEvent(true)
        setOnTouchListener(object : OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                Log.d(this::class.java.simpleName, "onTouch() event=" + event.action)
                return false
            }
        })

        setOnClickListener {
            Log.d(this::class.java.simpleName, "onClick()")
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        Log.e(this::class.java.simpleName, "dispatchTouchEvent() event=" + event.action)
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.e(this::class.java.simpleName, "onTouchEvent() event=" + event.action)
//        return super.onTouchEvent(event)
        return true
    }

//    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        Log.d(this::class.java.simpleName, "onMeasure() text=$text")
//    }
//
//    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
//        super.onLayout(changed, l, t, r, b)
//        Log.d(this::class.java.simpleName, "onLayout() text=$text")
//    }
//
//    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
//        Log.d(this::class.java.simpleName, "onDraw() text=$text")
//    }
}