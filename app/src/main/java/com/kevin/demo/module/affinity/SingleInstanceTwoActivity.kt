package com.kevin.demo.module.affinity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityWithBtnBinding

/**
 * Create by Kevin-Tu on 2020/5/29.
 */
class SingleInstanceTwoActivity: BaseActivity<ActivityWithBtnBinding>() {


    override fun initBinding(): ActivityWithBtnBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_with_btn, null, false)
    }

    override fun initView(savedInstanceState: Bundle?) {
        android.util.Log.v("kevin",  "${this::class.java.simpleName}  taskId=${taskId}")
        title = "${this::class.java.simpleName}  taskId=${taskId}"
        binding.btnOne.setOnClickListener {
            var intent = Intent(this, StandardActivity::class.java)
            startActivity(intent)
        }

        binding.btnTwo.setOnClickListener {
            var intent = Intent(this, SingleInstanceActivity::class.java)
            startActivity(intent)
        }
    }
}