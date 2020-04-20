package com.kevin.demo.util

import android.util.Log

/**
 * Create by Kevin-Tu on 2020/4/20.
 */
object LogUtil {

    fun v(obj: Any, message : String) {
        Log.v(obj.javaClass.simpleName, message)
    }

    fun v(message : String) {
        Log.v("kevin", message)
    }

    fun d(obj: Any, message : String) {
        Log.d(obj.javaClass.simpleName, message)
    }

    fun d(message : String) {
        Log.d("kevin", message)
    }

    fun i(obj: Any, message : String) {
        Log.i(obj.javaClass.simpleName, message)
    }

    fun i(message : String) {
        Log.i("kevin", message)
    }

    fun w(obj: Any, message : String) {
        Log.w(obj.javaClass.simpleName, message)
    }

    fun w(message : String) {
        Log.w("kevin", message)
    }

    fun e(obj: Any, message : String) {
        Log.e(obj.javaClass.simpleName, message)
    }

    fun e(message : String) {
        Log.e("kevin", message)
    }
}