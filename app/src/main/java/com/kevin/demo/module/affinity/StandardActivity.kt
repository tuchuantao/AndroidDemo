package com.kevin.demo.module.affinity

import android.os.Bundle


/**
 * Create by Kevin-Tu on 2020/5/29.
 */
class StandardActivity: BaseLaunchModeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 必须使用startActivityForResult，否则获取不到
        log("callingPackage=$callingPackage   callingActivity=$callingActivity")
    }
}