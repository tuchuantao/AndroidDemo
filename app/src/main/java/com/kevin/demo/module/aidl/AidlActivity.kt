package com.kevin.demo.module.aidl

import android.os.Bundle
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

    private var aidlBinder: IMyAidlInterface? = null
    private val connection = object : ServiceConnectionLis {
        override fun onServiceConnected(aidlService: IMyAidlInterface) {
            aidlBinder = aidlService
        }

        override fun onServiceDisconnected() {
            aidlBinder = null
        }
    }

    override fun initBinding(): ActivityAidlBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_aidl, null, false)
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
                it.inUserInfo(User(12345, "KevinTu"))
            }
        }

        binding.btnGet.setOnClickListener {
            aidlBinder?.let {
                var user = it.userInfo
                if (user == null) {
                    binding.resultUser.text = "刚传递的User信息为：null"
                } else {
                    binding.resultUser.text = "刚传递的User信息为：id=${user.id} name=${user.name}"
                }
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