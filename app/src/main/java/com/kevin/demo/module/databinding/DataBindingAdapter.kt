package com.kevin.demo.module.databinding

import android.text.TextUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * Create by Kevin-Tu on 2020/6/29.
 */
object DataBindingAdapter {

    @BindingAdapter("sex")
    @JvmStatic
    fun setSex(view: TextView, sex: String) {
        if (TextUtils.equals(sex, "男")) {
            view.text = "性别: 男"
        } else {
            view.text = "美丽的女士"
        }
    }

}