package com.kevin.demo

import android.app.Application
import android.util.Log
import androidx.multidex.MultiDex
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVHandler
import com.tencent.mmkv.MMKVLogLevel
import com.tencent.mmkv.MMKVRecoverStrategic

/**
 * Create by Kevin-Tu on 2020/4/1.
 */
class DemoApplication : Application() {

    companion object {
        var sInstance: DemoApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        MultiDex.install(this)

        // MMKV初始化
        // 1、自定义根目录
//        val dir = filesDir.absolutePath + "/mmkv_2"
//        val rootDir = MMKV.initialize(dir)
        // 2、使用默认目录: context.getFilesDir().getAbsolutePath() + "/mmkv"
        MMKV.initialize(this)
        MMKV.registerHandler(object : MMKVHandler {
            override fun onMMKVCRCCheckFail(mmapID: String?): MMKVRecoverStrategic {
                return MMKVRecoverStrategic.OnErrorDiscard
            }

            override fun onMMKVFileLengthError(mmapID: String?): MMKVRecoverStrategic {
                return MMKVRecoverStrategic.OnErrorDiscard
            }

            override fun wantLogRedirecting(): Boolean {
                return true
            }

            override fun mmkvLog(
                level: MMKVLogLevel,
                file: String,
                line: Int,
                function: String,
                message: String
            ) {
                Log.v("MMKVHandler", "MMKVHandler mmkvLog() level=$level file=$file line=$line function=$function message=$message")
            }

        })
    }
}