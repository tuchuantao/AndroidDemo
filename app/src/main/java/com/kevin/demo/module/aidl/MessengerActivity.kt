package com.kevin.demo.module.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityMessengerBinding

/**
 * Created by tuchuantao on 2022/4/11
 * Desc:
 */
class MessengerActivity : BaseActivity<ActivityMessengerBinding>() {

  private lateinit var mServiceConnection : ServiceConnection
  private var mMessenger : Messenger? = null

  companion object {
    private const val SERVICE_NAME = "com.kevin.aidlserver.MessengerService"
  }

  override fun getLayoutResId(): Int {
    return R.layout.activity_messenger
  }

  override fun initView(savedInstanceState: Bundle?) {
    mServiceConnection = object : ServiceConnection {
      override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        mMessenger = Messenger(service)
      }

      override fun onServiceDisconnected(name: ComponentName?) {
        mMessenger = null;
      }
    }

    binding.btnBind.setOnClickListener {
      bindService()
    }

    binding.btnIpc.setOnClickListener {
//      var msg = Message.obtain();
//      msg.what = 123;
//      msg.obj = "这是一条夸进程的消息"
//      RuntimeException: Can't marshal non-Parcelable objects across processes.

      val msg = Message.obtain()
      val bundle = Bundle()
      bundle.putInt("id", 100)
      bundle.putString("content", "这是一条夸进程的消息")
      msg.data = bundle
      mMessenger?.send(msg)
    }
  }

  private fun bindService(): Boolean {
    val intent = Intent(SERVICE_NAME)
    val componentName = resolveService(intent)
    if (componentName != null) { // 判断Service是否存在
      intent.component = componentName
      bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
      return true
    }
    /*var intent = Intent();
    // Android 5.0开始，启动服务必须使用显示的，不能用隐式的
    intent.component = ComponentName("com.kevin.aidlserver", "com.kevin.aidlserver.AidlService")*/
    return false
  }

  private fun resolveService(intent: Intent): ComponentName? {
    try {
      val resolveInfo = packageManager.resolveService(intent, 0)
      if (resolveInfo != null) {
        val serviceName = resolveInfo.serviceInfo.name
        val packageName = resolveInfo.serviceInfo.packageName
        Log.d("kevin", "servicename: $serviceName, pkgname: $packageName")
        return ComponentName(packageName, serviceName)
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }

    return null
  }
}