package com.kevin.demo.module.surfaceview

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivitySurfaveViewBinding

/**
 * Create by Kevin-Tu on 2019/7/22.
 */
class SurfaceViewActivity : BaseActivity() {

    private lateinit var binding: ActivitySurfaveViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_surfave_view, null, false)

        initView()
    }

    private fun initView() {

    }
}