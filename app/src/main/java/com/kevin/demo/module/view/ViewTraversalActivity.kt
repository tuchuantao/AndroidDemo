package com.kevin.demo.module.view

import android.os.Bundle
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityViewTraversalBinding

/**
 * Created by tuchuantao on 2021/8/4
 * Desc:
 */
class ViewTraversalActivity : BaseActivity<ActivityViewTraversalBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_view_traversal
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.tx1.setOnClickListener {
            it.requestLayout()
//            it.invalidate()
        }

        binding.tx2.setOnClickListener {
            it.requestLayout()
//            it.invalidate()
        }

        binding.tx3.setOnClickListener {
            it.requestLayout()
//            it.invalidate()
        }

        binding.childLayout.setOnClickListener {
            it.requestLayout()
//            it.invalidate()
        }


        binding.reLayout.setOnClickListener {
            it.requestLayout()
//            it.invalidate()
        }

        binding.tx4.setOnClickListener {
            it.requestLayout()
//            it.invalidate()
        }

        binding.tx5.setOnClickListener {
            it.requestLayout()
//            it.invalidate()
        }
    }
}