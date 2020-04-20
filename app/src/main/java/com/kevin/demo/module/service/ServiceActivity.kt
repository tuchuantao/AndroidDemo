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

    override fun initBinding(): ActivityServiceBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_service, null, false)
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

}