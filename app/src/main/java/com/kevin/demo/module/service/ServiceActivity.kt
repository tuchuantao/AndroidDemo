package com.kevin.demo.module.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityServiceBinding
import com.kevin.demo.util.LogUtil
import java.lang.Exception

/**
 * Create by Kevin-Tu on 2020/4/20.
 */
class ServiceActivity: BaseActivity<ActivityServiceBinding>() {

    var connection: ServiceConnection? = null

    override fun getLayoutResId(): Int {
        return R.layout.activity_service
    }

    override fun initView(savedInstanceState: Bundle?) {
        connection = object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName) {
                LogUtil.d(ServiceActivity::class.java, "onServiceDisconnected() name: $name")
            }

            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                LogUtil.d(ServiceActivity::class.java, "onServiceConnected() name: $name  service:$service")
            }
        }

        binding.btnStart.setOnClickListener {
            var intent = getServiceIntent()
            startService(intent)
        }

        binding.btnStop.setOnClickListener {
            var intent = getServiceIntent()
            stopService(intent)
        }

        binding.btnBind.setOnClickListener {
            var intent = getServiceIntent()
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
            //bindService(intent, connection, Context.BIND_NOT_FOREGROUND)
        }

        binding.btnUnbind.setOnClickListener {
            try {
                unbindService(connection)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.btnBroadcast.setOnClickListener {
            var intent = Intent("com.kevin.receiver.TEST_SERVICE")
            sendBroadcast(intent)
        }
    }

    private fun getServiceIntent() : Intent {
        var intent = Intent("com.kevin.service.TEST_SERVICE")
        intent.component = ComponentName(packageName, TestService::class.java.name)
        return intent
    }

    /**
     * Service销毁思考：

    bindService(intent, connection, Context.BIND_NOT_FOREGROUND)
    startService() -> bindService()
    2020-10-15 11:34:59.497 14522-14522/com.kevin.demo D/TestService: onCreate()
    2020-10-15 11:34:59.497 14522-14522/com.kevin.demo D/TestService: onStartCommand()
    2020-10-15 11:35:07.962 14522-14522/com.kevin.demo D/TestService: onBind()

    -> stopService()
    2020-10-15 11:35:16.884 14522-14522/com.kevin.demo D/TestService: onUnbind()
    2020-10-15 11:35:16.885 14522-14522/com.kevin.demo D/TestService: onDestroy()

    bindService(intent, connection, Context.BIND_AUTO_CREATE)
    startService() -> bindService()
    2020-10-15 11:34:59.497 14522-14522/com.kevin.demo D/TestService: onCreate()
    2020-10-15 11:34:59.497 14522-14522/com.kevin.demo D/TestService: onStartCommand()
    2020-10-15 11:35:07.962 14522-14522/com.kevin.demo D/TestService: onBind()

    -> stopService()
    nothing
    -> unBindService()
    2020-10-15 11:35:16.884 14522-14522/com.kevin.demo D/TestService: onUnbind()
    2020-10-15 11:35:16.885 14522-14522/com.kevin.demo D/TestService: onDestroy()

    Service是否销毁（执行onDestroy()生命周期方法）取决于：
    1、startServcie()后是否调用stopService()
    2、bindService时，如果flags传入的是Context.BIND_AUTO_CREATE，也得需要调用unbindService()进行解绑，
     */
}