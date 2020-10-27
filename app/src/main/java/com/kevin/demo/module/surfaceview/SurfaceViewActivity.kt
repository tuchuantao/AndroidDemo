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
class SurfaceViewActivity : BaseActivity<ActivitySurfaveViewBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_surfave_view
    }

    override fun initView(savedInstanceState: Bundle?) {
        title = "Surface View"
    }
}