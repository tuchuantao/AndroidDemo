package com.kevin.demo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.kevin.demo.module.banner.BannerActivity
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.module.coordinatorlayout.CoordinatorLayoutActivity
import com.kevin.demo.databinding.ActivityMainBinding
import com.kevin.demo.module.affinity.ActAffinityActivity
import com.kevin.demo.module.aidl.AidlActivity
import com.kevin.demo.module.notification.NotificationActivity
import com.kevin.demo.module.service.ServiceActivity
import com.kevin.demo.module.surfaceview.SurfaceViewActivity
import com.kevin.demo.module.tablayout.TabLayoutActivity
import com.kevin.demo.module.tint.DrawableTintActivity
import com.kevin.demo.work.WorkHelper

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initBinding(): ActivityMainBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_main, null, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        updateFontScale(1.3f)
        super.onCreate(savedInstanceState)
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

        binding.btnAidl.setOnClickListener {
            var intent = Intent(this, AidlActivity::class.java)
            startActivity(intent)
        }

        binding.btnNotification.setOnClickListener {
            var intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

        binding.btnTabLayout.setOnClickListener {
            var intent = Intent(this, TabLayoutActivity::class.java)
            startActivity(intent)
        }

        binding.btnDrawableTint.setOnClickListener {
            var intent = Intent(this, DrawableTintActivity::class.java)
            startActivity(intent)
        }

        binding.btnService.setOnClickListener {
            var intent = Intent(this, ServiceActivity::class.java)
            startActivity(intent)
        }

        binding.btnTaskAffinity.setOnClickListener {
            var intent = Intent(this, ActAffinityActivity::class.java)
            startActivity(intent)
        }

        //WorkHelper.startWorker(this)
    }

    /**
     * 通过更改scaledDensity来更改文字的大小，只对SP有用
     */
    private fun updateFontScale(scale: Float = 1f) {
        var configuration = resources.configuration
        configuration.fontScale = scale

        var metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            baseContext.resources.displayMetrics.scaledDensity = metrics.scaledDensity
            baseContext.createConfigurationContext(configuration)
        } else {
            baseContext.resources.updateConfiguration(configuration, metrics)
        }
    }
}
