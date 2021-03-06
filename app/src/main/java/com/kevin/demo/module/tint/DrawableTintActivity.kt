package com.kevin.demo.module.tint

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityDrawableTintBinding

/**
 * Create by Kevin-Tu on 2020/4/15.
 */
class DrawableTintActivity: BaseActivity<ActivityDrawableTintBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_drawable_tint
    }

    override fun initView(savedInstanceState: Bundle?) {
        setDrawableTint(binding.img2, Color.BLUE)
        setDrawableTint(binding.img3, Color.RED)
    }

    private fun setDrawableTint(imgView: ImageView, color: Int) {
        // NOTE: 2020/4/15  Android5.0之后才开始支持tint
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val drawable = DrawableCompat.wrap(imgView.drawable)
            DrawableCompat.setTint(drawable, color)
        } else {
            imgView.setColorFilter(color)
        }
    }
}