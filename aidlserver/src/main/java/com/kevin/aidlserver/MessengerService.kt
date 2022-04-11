package com.kevin.aidlserver

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

/**
 * Created by tuchuantao on 2022/4/11
 * Desc:
 */
class MessengerService : Service() {

  val mHandler: Handler by lazy {
    object : Handler(Looper.getMainLooper()) {
      override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        Log.v("kevin", "MessengerService: handleMessage() id=${msg.data.getInt("id")}  content=${msg.data.getString("content")}")
      }
    }
  }

  override fun onBind(intent: Intent?): IBinder? {
    Log.v("kevin", "MessengerService: onBind()")
    return Messenger(mHandler).binder;
  }
}