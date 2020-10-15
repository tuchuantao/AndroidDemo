package com.kevin.demo

import android.app.Application
import androidx.multidex.MultiDex
import com.squareup.leakcanary.LeakCanary

/**
 * Create by Kevin-Tu on 2020/4/1.
 */
class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

    }
}