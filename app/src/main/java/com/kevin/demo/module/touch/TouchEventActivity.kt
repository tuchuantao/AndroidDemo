package com.kevin.demo.module.touch

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityTouchEventBinding

/**
 * Created by tuchuantao on 2021/8/3
 * Desc:
 */
class TouchEventActivity : BaseActivity<ActivityTouchEventBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_touch_event
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        Log.v(this::class.java.simpleName, "dispatchTouchEvent() event=" + event.action)
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.v(this::class.java.simpleName, "onTouchEvent() event=" + event.action)
        return super.onTouchEvent(event)
    }
}