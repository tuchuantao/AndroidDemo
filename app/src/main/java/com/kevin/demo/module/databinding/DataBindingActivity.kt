package com.kevin.demo.module.databinding

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.data.Person
import com.kevin.demo.databinding.ActivityDataBindingBinding

/**
 * Create by Kevin-Tu on 2020/6/29.
 */
class DataBindingActivity : BaseActivity<ActivityDataBindingBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_data_binding
    }

    override fun initView(savedInstanceState: Bundle?) {
        var person = Person("张三", 28, "男")
        binding.person = person

        var person2 = Person("李四", 18, "女")
        binding.person2 = person2
    }
}