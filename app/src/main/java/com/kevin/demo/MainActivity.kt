package com.kevin.demo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.kevin.demo.module.banner.BannerActivity
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.module.coordinatorlayout.CoordinatorLayoutActivity
import com.kevin.demo.databinding.ActivityMainBinding
import com.kevin.demo.module.affinity.ActAffinityActivity
import com.kevin.demo.module.aidl.AidlActivity
import com.kevin.demo.module.constraintlayout.ConstraintLayoutActivity
import com.kevin.demo.module.customview.CustomActivity
import com.kevin.demo.module.databinding.DataBindingActivity
import com.kevin.demo.module.fadingedge.FadingEdgeActivity
import com.kevin.demo.module.lottie.LottieActivity
import com.kevin.demo.module.notification.NotificationActivity
import com.kevin.demo.module.service.ServiceActivity
import com.kevin.demo.module.surfaceview.SurfaceViewActivity
import com.kevin.demo.module.tablayout.TabLayoutActivity
import com.kevin.demo.module.tint.DrawableTintActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
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

        binding.btnFadingEdge.setOnClickListener {
            var intent = Intent(this, FadingEdgeActivity::class.java)
            startActivity(intent)
        }

        binding.btnDataBinding.setOnClickListener {
            var intent = Intent(this, DataBindingActivity::class.java)
            startActivity(intent)
        }

        binding.btnShowDialog.setOnClickListener {
            var dialog = AlertDialog.Builder(this)
                .setTitle("Alert Dialog")
                .create()
            dialog.show()
        }

        binding.btnLottie.setOnClickListener {
            var intent = Intent(this, LottieActivity::class.java)
            startActivity(intent)
        }

        binding.btnCustomView.setOnClickListener {
            var intent = Intent(this, CustomActivity::class.java)
            startActivity(intent)
        }

        binding.btnConstraintLayout.setOnClickListener {
            var intent = Intent(this, ConstraintLayoutActivity::class.java)
            startActivity(intent)
        }

        /*binding.btnOkio.setOnClickListener {
            var intent = Intent(this, )
        }*/

        //WorkHelper.startWorker(this)
    }

    /**
     * 通过更改scaledDensity来更改文字的大小，只对SP有用
     *
     * 0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
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
