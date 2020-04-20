package com.kevin.demo.module.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.kevin.demo.util.LogUtil

/**
 * Create by Kevin-Tu on 2020/4/20.
 */
class TestService2: Service() {

    override fun onCreate() {
        super.onCreate()
        LogUtil.d(this, "onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogUtil.d(this, "onStartCommand()")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        LogUtil.d(this, "onStart()")
    }

    override fun onBind(intent: Intent?): IBinder? {
        LogUtil.d(this, "onBind()")
        return null
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        LogUtil.d(this, "onRebind()")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        LogUtil.d(this, "onUnbind()")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(this, "onDestroy()")
    }
}