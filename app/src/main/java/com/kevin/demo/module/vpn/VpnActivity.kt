package com.kevin.demo.module.vpn

import android.content.Intent
import android.net.VpnService
import android.os.Bundle
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityVpnBinding

/**
 * Created by tuchuantao on 2021/8/23
 * Desc:
 */
class VpnActivity : BaseActivity<ActivityVpnBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_vpn
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.btnStart.setOnClickListener {
            var intent = prepare()
            if (intent == null) {

            } else {

            }
        }

        binding.btnStop.setOnClickListener {

        }
    }

    fun prepare(): Intent? {
        return VpnService.prepare(this)
    }
}