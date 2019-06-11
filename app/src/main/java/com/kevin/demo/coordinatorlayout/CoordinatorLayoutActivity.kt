package com.kevin.demo.coordinatorlayout

import android.os.Bundle
import android.view.LayoutInflater
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityCoordinatorLayoutBinding
import com.kevin.demo.util.ScreenUtil

/**
 * Create by Kevin-Tu on 2019/5/30.
 */
class CoordinatorLayoutActivity : BaseActivity() {

    private lateinit var binding: ActivityCoordinatorLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinatorLayoutBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        title = "CoordinatorLayoutActivity"

        val maxHeight = (ScreenUtil.getScreenWidth(this) * 1.01).toInt()
        val layoutParams = binding.headLayout.layoutParams
        layoutParams.height = maxHeight
        binding.headLayout.layoutParams = layoutParams
    }
}