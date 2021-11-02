package com.kevin.demo

import android.app.Application
import androidx.multidex.MultiDex

/**
 * Create by Kevin-Tu on 2020/4/1.
 */
class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }
}