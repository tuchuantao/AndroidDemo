package com.kevin.demo.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Create by Kevin-Tu on 2019/6/11.
 */
object ImageViewBindingAdapter {

    @BindingAdapter("bannerImgUrl")
    @JvmStatic
    fun setBannerImgUrl(imgView: ImageView, imgUrl: String) {
        Glide.with(imgView)
            .load(imgUrl)
            .apply(RequestOptions.fitCenterTransform())
            .into(imgView)
    }
}