package com.kevin.demo.module.inflater

import android.os.Bundle
import android.view.LayoutInflater
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityLayoutInflaterBinding

/**
 * Created by tuchuantao on 2021/8/2
 * Desc:
 */
class LayoutInflaterActivity : BaseActivity<ActivityLayoutInflaterBinding>() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_layout_inflater
    }

    override fun initView(savedInstanceState: Bundle?) {
        var contentLayout = LayoutInflater.from(this).inflate(R.layout.view_button, binding.contentLayout, true)
        log("inflate result View1=$contentLayout")
//        LayoutInflater.from(this).inflate(R.layout.view_button, binding.contentLayout, true)

        var btnView = LayoutInflater.from(this).inflate(R.layout.view_button, null)
        binding.contentLayout.addView(btnView)
        log("inflate result View2=$btnView")
//        var btnView = LayoutInflater.from(this).inflate(R.layout.view_button, binding.contentLayout, false)
//        binding.contentLayout.addView(btnView)
    }
}