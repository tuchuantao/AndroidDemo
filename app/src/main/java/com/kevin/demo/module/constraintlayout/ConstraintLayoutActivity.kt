package com.kevin.demo.module.constraintlayout

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityConstraintLayoutBinding

class ConstraintLayoutActivity : BaseActivity<ActivityConstraintLayoutBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_constraint_layout
    }

    override fun initView(savedInstanceState: Bundle?) {
    }
}