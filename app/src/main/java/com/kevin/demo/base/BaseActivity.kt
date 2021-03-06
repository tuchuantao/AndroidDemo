package com.kevin.demo.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * Create by Kevin-Tu on 2019/5/30.
 */
open abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        option()

        binding = initBinding()
        if (binding != null) {
            setContentView(binding.root)
        }

        initView(savedInstanceState)
    }

    private fun initBinding(): T {
        return DataBindingUtil.inflate(LayoutInflater.from(this), getLayoutResId(), null, false)
    }

    abstract fun getLayoutResId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    open fun option() {
    }
}