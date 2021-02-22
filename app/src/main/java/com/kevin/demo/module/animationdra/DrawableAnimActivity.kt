package com.kevin.demo.module.animationdra

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityDrawableAnimBinding

/**
 * Created by tuchuantao on 2020/11/13
 */
class DrawableAnimActivity : BaseActivity<ActivityDrawableAnimBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_drawable_anim
    }

    override fun initView(savedInstanceState: Bundle?) {
//        binding.img.setImageResource(R.drawable.a_animation_list)
//        var animationDrawable = binding.img.getDrawable() as AnimationDrawable
//        animationDrawable.start()
//
//        binding.img2.setImageResource(R.drawable.b_animation_list)
//        var animationDrawable2 = binding.img2.getDrawable() as AnimationDrawable
//
//        animationDrawable.start()
//        animationDrawable2.start()
    }
}