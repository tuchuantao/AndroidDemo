package com.kevin.demo.module.lottie

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityLottieBinding

/**
 * Create by Kevin-Tu on 2020/7/14.
 */
class LottieActivity: BaseActivity<ActivityLottieBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_lottie
    }

    override fun initView(savedInstanceState: Bundle?) {

    }
}