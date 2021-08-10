package com.kevin.aidlserver

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Create by Kevin-Tu on 2019/7/23.
 */
class AidlService : Service() {

    private var iBinder = object : IMyAidlInterface.Stub() {

        private var user: User? = null

        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {
        }

        override fun add(value: Int, value2: Int): Int {
            return value + value2
        }

        override fun inUserInfo(user: User) {
            android.util.Log.v("kevin", "iBinder inUserInfo() user={$user} user.name: ${user.name}")
            this.user = user
            user.name = "inUserInfo"
        }

        override fun outUserInfo(user: User) {
            android.util.Log.v("kevin", "iBinder outUserInfo() user={$user} user.name: ${user.name}")
            user.name = "outUserInfo"
        }

        override fun inOutUserInfo(user: User) {
            android.util.Log.v("kevin", "iBinder inOutUserInfo() user={$user} user.name: ${user.name}")
            user.name = "inOutUserInfo"
        }

        override fun getUserInfo(): User? {
            return user
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        android.util.Log.v("kevin", "AidlService onBind() IBinder=$iBinder")
        return iBinder
    }
}