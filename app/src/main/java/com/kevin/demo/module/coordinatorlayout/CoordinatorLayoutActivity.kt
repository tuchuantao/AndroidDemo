package com.kevin.demo.module.coordinatorlayout

import android.os.Bundle
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityCoordinatorLayoutBinding
import com.kevin.demo.util.ScreenUtil

/**
 * Create by Kevin-Tu on 2019/5/30.
 */
class CoordinatorLayoutActivity : BaseActivity<ActivityCoordinatorLayoutBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_coordinator_layout
    }

    override fun initView(savedInstanceState: Bundle?) {
        title = "CoordinatorLayoutActivity"

        val maxHeight = (ScreenUtil.getScreenWidth(this) * 1.01).toInt()
        val layoutParams = binding.headLayout.layoutParams
        layoutParams.height = maxHeight
        binding.headLayout.layoutParams = layoutParams
    }
}