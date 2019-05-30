package com.kevin.demo.util

import android.content.Context
import android.view.WindowManager

/**
 * Create by Kevin-Tu on 2019/5/30.
 */
object ScreenUtil {

    /**
     * 获取屏幕的宽度
     *
     * @param context
     * @return
     */
    fun getScreenWidth(context: Context): Int {
        val manager = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        return display.width
    }

    /**
     * 获取屏幕的高度
     *
     * @param context
     * @return
     */
    fun getScreenHeight(context: Context): Int {
        val manager = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = manager.defaultDisplay
        return display.height
    }
}