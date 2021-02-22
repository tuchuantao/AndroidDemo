package com.kevin.demo.module.lottie

import android.net.Uri
import android.os.Bundle
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityLottieBinding

/**
 * http://airbnb.io/lottie/#/android?id=sample-app
 *
 * Create by Kevin-Tu on 2020/7/14.
 */
class LottieActivity: BaseActivity<ActivityLottieBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_lottie
    }

    override fun initView(savedInstanceState: Bundle?) {
        // 属性
//        <resources>
//        <attr format="reference" name="lottieAnimationViewStyle"/>
//        <item name="lottie_layer_name" type="id"/>
//        <declare-styleable name="LottieAnimationView">
//        <attr format="string" name="lottie_fileName"/>
//        <attr format="reference" name="lottie_rawRes"/>
//        <attr format="string" name="lottie_url"/>
//        <attr format="reference" name="lottie_fallbackRes"/>
//        <attr format="boolean" name="lottie_autoPlay"/>
//        <attr format="boolean" name="lottie_loop"/>
//        <attr format="enum" name="lottie_repeatMode">
//        <enum name="restart" value="1"/>
//        <enum name="reverse" value="2"/>
//        </attr>
//        <attr format="integer" name="lottie_repeatCount"/>
//        <attr format="string" name="lottie_imageAssetsFolder"/>
//        <attr format="float" name="lottie_progress"/>
//        <attr format="boolean" name="lottie_enableMergePathsForKitKatAndAbove"/>
//        <attr format="color" name="lottie_colorFilter"/>
//        <attr format="float" name="lottie_scale"/>
//        <attr format="float" name="lottie_speed"/>
//        <attr format="boolean" name="lottie_cacheComposition"/>
//        <!-- These values must be kept in sync with the RenderMode enum -->
//        <attr format="enum" name="lottie_renderMode">
//        <enum name="automatic" value="0"/>
//        <enum name="hardware" value="1"/>
//        <enum name="software" value="2"/>
//        </attr>
//        </declare-styleable>
//        </resources>

//        val controllerLis = LoopCountModifyControllerLis(5)
//
//        val controller: DraweeController = Fresco.newDraweeControllerBuilder()
//            .setOldController(binding.webpImg.getController())
//            .setAutoPlayAnimations(true)
//            .setControllerListener(controllerLis)
//            .setUri(Uri.parse("asset:///loading_progress.webp"))
//            .build()
//        binding.webpImg.controller = controller
    }
}