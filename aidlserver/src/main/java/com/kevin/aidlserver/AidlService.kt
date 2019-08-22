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
            this.user = user
            android.util.Log.v("kevin", "iBinder inUserInfo() user.id: ${user.id} user.name: ${user.name}")
        }

        override fun getUserInfo(): User? {
            return user
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        android.util.Log.v("kevin", "AidlService onBind()")
        return iBinder
    }
}