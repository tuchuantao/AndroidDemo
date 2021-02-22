package com.kevin.demo.module.customview

import android.os.Bundle
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityCustomViewBinding

class CustomActivity : BaseActivity<ActivityCustomViewBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_custom_view
    }

    override fun initView(savedInstanceState: Bundle?) {
    }
}