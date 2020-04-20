package com.kevin.demo.module.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kevin.demo.util.LogUtil

/**
 * Create by Kevin-Tu on 2020/4/20.
 */
class TestBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        LogUtil.d("TestBroadcastReceiver onReceive() ")

        var intent = Intent(context, TestService2::class.java)
        context.startService(intent)
    }
}