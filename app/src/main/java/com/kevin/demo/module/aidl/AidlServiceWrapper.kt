package com.kevin.demo.module.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import com.kevin.aidlserver.IMyAidlInterface
import java.util.ArrayList

/**
 * Create by Kevin-Tu on 2020/4/21.
 */
object AidlServiceWrapper {

    private const val SERVICE_NAME = "com.kevin.aidlserver.AidlService"

    private var mAppContext: Context? = null
    private val mListeners: ArrayList<ServiceConnectionLis> = ArrayList()
    private var mAidlService: IMyAidlInterface? = null
    private var mServiceConnection: ServiceConnection
    private var mDeathRecipient: IBinder.DeathRecipient

    init {
        mDeathRecipient = IBinder.DeathRecipient {
            if (mAidlService != null) { // 当前绑定由于异常断开，重新绑定
                mAidlService = null
                realBindService()
            }
        }

        mServiceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                mAidlService = IMyAidlInterface.Stub.asInterface(service)
                // 注册死亡接收器
                try {
                    // 0: 代表普通的RPC, FLAG_ONEWAY 表示一个单向RPC
                    service.linkToDeath(mDeathRecipient, 0)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                synchronized(mListeners) {
                    if (!mListeners.isEmpty()) {
                        val lisLength = mListeners.size
                        for (i in 0 until lisLength) {
                            mListeners[i].onServiceConnected(mAidlService!!)
                        }
                    }
                }
            }

            override fun onServiceDisconnected(name: ComponentName) {
                mAidlService = null

                synchronized(mListeners) {
                    if (!mListeners.isEmpty()) {
                        val lisLength = mListeners.size
                        for (i in 0 until lisLength) {
                            mListeners[i].onServiceDisconnected()
                        }
                        mListeners.clear()
                    }
                }
            }
        }
    }

    fun bindServiceIfNeed(context: Context, lis: ServiceConnectionLis) {
        if (mAppContext == null) {
            mAppContext = context.applicationContext
        }

        if (mAidlService != null) { // 之前已经绑定过，直接返回
            lis.onServiceConnected(mAidlService!!)
        } else {
            if (!realBindService()) { // 未发现需要bind的service
                return
            }
        }

        synchronized(mListeners) {
            if (!mListeners.contains(lis)) {
                mListeners.add(lis)
            }
        }
    }

    private fun realBindService(): Boolean {
        val intent = Intent(SERVICE_NAME)
        val componentName = resolveService(mAppContext!!, intent)
        if (componentName != null) { // 判断Service是否存在
            intent.component = componentName
            mAppContext?.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
            return true
        }
        /*var intent = Intent();
        // Android 5.0开始，启动服务必须使用显示的，不能用隐式的
        intent.component = ComponentName("com.kevin.aidlserver", "com.kevin.aidlserver.AidlService")*/
        return false
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

    fun removeServiceConnectLis(lis: ServiceConnectionLis) {
        synchronized(mListeners) {
            mListeners.remove(lis)
        }
    }

    fun unbindService(context: Context) {
        if (mAidlService != null) {
            context.applicationContext.unbindService(mServiceConnection)
        }
    }
}

interface ServiceConnectionLis {

    fun onServiceConnected(aidlService: IMyAidlInterface)

    fun onServiceDisconnected()
}