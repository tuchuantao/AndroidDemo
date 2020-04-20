package com.kevin.demo.module.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.kevin.aidlserver.IMyAidlInterface
import com.kevin.aidlserver.User
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityAidlBinding

/**
 * Create by Kevin-Tu on 2019/7/23.
 */
class AidlActivity: BaseActivity<ActivityAidlBinding>() {

    private var aidl: IMyAidlInterface? = null
    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            aidl = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            aidl = IMyAidlInterface.Stub.asInterface(service)
        }
    }

    override fun initBinding(): ActivityAidlBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_aidl, null, false)
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.btnIpc.setOnClickListener {
            var result = aidl?.add(7, 9)
            binding.result.text = "7 + 9 = $result"
        }

        binding.btnBind.setOnClickListener {
            if (aidl == null) {
                bindService()
            }
        }

        binding.btnSend.setOnClickListener {
            aidl?.let {
                it.inUserInfo(User(12345, "KevinTu"))
            }
        }

        binding.btnGet.setOnClickListener {
            aidl?.let {
                var user = it.userInfo
                if (user == null) {
                    binding.resultUser.text = "刚传递的User信息为：null"
                } else {
                    binding.resultUser.text = "刚传递的User信息为：id=${user.id} name=${user.name}"
                }
            }
        }
    }

    private fun bindService() {
        var intent = Intent("com.kevin.aidlserver.AidlService")
        var component = resolveService(this, intent)
        component?.let {
            intent.component = component

        }
        /*var intent = Intent();
        // Android 5.0开始，启动服务必须使用显示的，不能用隐式的
        intent.component = ComponentName("com.kevin.aidlserver", "com.kevin.aidlserver.AidlService")*/
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    private fun resolveService(context: Context, intent: Intent): ComponentName? {
        try {
            val pm = context.applicationContext.packageManager
            val resolveInfo = pm.resolveService(intent, 0)
            if (resolveInfo != null) {
                val serviceName = resolveInfo.serviceInfo.name
                val packageName = resolveInfo.serviceInfo.packageName
                Log.d("resolveService", "servicename: $serviceName, pkgname: $packageName")
                return ComponentName(packageName, serviceName)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        aidl?.let {
            unbindService(connection)
        }
    }
}