package com.kevin.demo.module.affinity

import android.content.Intent
import android.os.Bundle
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityLaunchModeBinding

/**
 * adb shell dumpsys activity activities
 * adb shell dumpsys activity activities | sed -En -e '/Running activities/,/Run #0/p'
 *
 * Created by tuchuantao on 2021/7/29
 * Desc:
 */
open class BaseLaunchModeActivity : BaseActivity<ActivityLaunchModeBinding>() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_launch_mode
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.btnStandard.setOnClickListener {
            var intent = Intent(this, StandardActivity::class.java)
            startActivity(intent)
        }
        binding.btnStandardWithAffinity.setOnClickListener {
            var intent = Intent(this, StandardAffinityActivity::class.java)
            startActivity(intent)
        }
        binding.btnSingleTop.setOnClickListener {
            var intent = Intent(this, SingleTopActivity::class.java)
            startActivity(intent)
        }
        binding.btnSingleTask.setOnClickListener {
            var intent = Intent(this, SingleTaskActivity::class.java)
            startActivity(intent)
        }
        binding.btnSingleInstance.setOnClickListener {
            var intent = Intent(this, SingleInstanceActivity::class.java)
            startActivity(intent)
        }
        binding.btnSingleInstanceWithAffinity.setOnClickListener {
            var intent = Intent(this, SingleInstanceAffinityActivity::class.java)
            startActivity(intent)
        }
    }
}