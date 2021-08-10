package com.kevin.demo.module.aidl

import android.os.Bundle
import android.util.Log
import com.kevin.aidlserver.IMyAidlInterface
import com.kevin.aidlserver.User
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityAidlBinding

/**
 * Create by Kevin-Tu on 2019/7/23.
 */
class AidlActivity: BaseActivity<ActivityAidlBinding>() {

    private var aidlBinder: IMyAidlInterface? = null
    private val connection = object : ServiceConnectionLis {
        override fun onServiceConnected(aidlService: IMyAidlInterface) {
            aidlBinder = aidlService
        }

        override fun onServiceDisconnected() {
            aidlBinder = null
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_aidl
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.btnIpc.setOnClickListener {
            var result = aidlBinder?.add(7, 9)
            binding.result.text = "7 + 9 = $result"
        }

        binding.btnBind.setOnClickListener {
            if (aidlBinder == null) {
                AidlServiceWrapper.bindServiceIfNeed(this, connection)
            }
        }

        binding.btnSend.setOnClickListener {
            aidlBinder?.let {
                var user = User(12345, "KevinTu")
                it.inUserInfo(user)
                Log.d("kevin", "AidlActivity send user={$user} name=${user.name}")
            }
        }

        binding.btnGet.setOnClickListener {
            aidlBinder?.let {
                var user = it.userInfo
                if (user == null) {
                    binding.resultUser.text = "刚传递的User信息为：null"
                } else {
                    Log.d("kevin", "AidlActivity get user={$user}")
                    binding.resultUser.text = "刚传递的User信息为：id=${user.id} name=${user.name}"
                }
            }
        }

        binding.btnOut.setOnClickListener {
            aidlBinder?.let {
                var user = User(12345, "KevinTu")
                it.outUserInfo(user)
                Log.d("kevin", "AidlActivity outUserInfo() user={$user}  userName={${user.name}}")
            }
        }

        binding.btnInout.setOnClickListener {
            aidlBinder?.let {
                var user = User(12345, "KevinTu")
                it.inOutUserInfo(user)
                Log.d("kevin", "AidlActivity inOutUserInfo() user={$user}  userName={${user.name}}")
            }
        }

        binding.btnNoOneway.setOnClickListener {
            aidlBinder?.let {
                var user = User(12345, "KevinTu")
                Log.d("kevin", "AidlActivity testNoOneway() start")
                it.testNoOneway(user)
                Log.d("kevin", "AidlActivity testNoOneway() end")
            }
        }

        binding.btnOneway.setOnClickListener {
            aidlBinder?.let {
                var user = User(12345, "KevinTu")
                Log.d("kevin", "AidlActivity testOneway() start")
                it.testOneway(user)
                Log.d("kevin", "AidlActivity testOneway() end")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        aidlBinder?.let {
            AidlServiceWrapper.unbindService(this)
        }
    }
}