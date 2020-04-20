package com.kevin.demo.module.service

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.kevin.demo.util.LogUtil

/**
 * Create by Kevin-Tu on 2020/4/20.
 */
class TestService: Service() {

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

        // NOTE: 2020/4/20 Service 中可以start/bind一个service
/*        var intent = Intent(this, TestService2::class.java)
        startService(intent)

        var intent2 = Intent(this, TestService2::class.java)
        bindService(intent2, object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
                LogUtil.d(this, "onServiceDisconnected()")
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                LogUtil.d(this, "onServiceConnected()")
            }
        }, Context.BIND_AUTO_CREATE)*/
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
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(this, "onDestroy()")
    }
}