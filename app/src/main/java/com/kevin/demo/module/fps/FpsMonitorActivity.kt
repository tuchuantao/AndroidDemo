package com.kevin.demo.module.fps

import android.os.Bundle
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityFpsBinding

/**
 * Created by tuchuantao on 2021/7/27
 * Desc:
 */
class FpsMonitorActivity : BaseActivity<ActivityFpsBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_fps;
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.btnStart.setOnClickListener {
            FpsMonitor.startMonitor {
                binding.count.text = "FPS: $it"
            }
        }

        binding.btnStop.setOnClickListener {
            FpsMonitor.stopMonitor()
        }
    }
}