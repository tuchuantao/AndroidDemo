package com.kevin.demo.module.affinity

import android.content.Intent
import android.os.Bundle
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityWithBtnBinding

/**
 * Create by Kevin-Tu on 2020/5/29.
 */
class StandardActivity: BaseActivity<ActivityWithBtnBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_with_btn
    }

    override fun initView(savedInstanceState: Bundle?) {
        android.util.Log.v("kevin",  "${this::class.java.simpleName}  taskId=${taskId}")
        title = "${this::class.java.simpleName}  taskId=${taskId}"
        binding.btnOne.setOnClickListener {
            var intent = Intent(this, ActAffinityActivity::class.java)
            startActivity(intent)
        }

        binding.btnTwo.setOnClickListener {
            var intent = Intent(this, SingleTopActivity::class.java)
            startActivity(intent)
        }
    }
}