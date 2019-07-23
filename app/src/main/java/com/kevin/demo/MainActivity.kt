package com.kevin.demo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.kevin.demo.module.banner.BannerActivity
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.module.coordinatorlayout.CoordinatorLayoutActivity
import com.kevin.demo.databinding.ActivityMainBinding
import com.kevin.demo.module.surfaceview.SurfaceViewActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initBinding() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, null, false)
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.btnCoordinatorLayout.setOnClickListener {
            var intent = Intent(this, CoordinatorLayoutActivity::class.java)
            startActivity(intent)
        }

        binding.btnBannerLayout.setOnClickListener {
            var intent = Intent(this, BannerActivity::class.java)
            startActivity(intent)
        }

        binding.btnSurfaceView.setOnClickListener {
            var intent = Intent(this, SurfaceViewActivity::class.java)
            startActivity(intent)
        }
    }
}
