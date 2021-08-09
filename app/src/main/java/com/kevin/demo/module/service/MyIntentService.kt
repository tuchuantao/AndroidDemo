package com.kevin.demo.module.service

import android.app.IntentService
import android.content.Intent
import android.os.IBinder

/**
 * Create by Kevin-Tu on 2019/7/24.
 */
class MyIntentService : IntentService("Intent Service") {

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onHandleIntent(intent: Intent?) {
    }
}