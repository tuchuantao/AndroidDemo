package com.kevin.demo.banner

import android.os.Bundle
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.kevin.demo.R
import com.kevin.demo.banner.base.BannerIndicatorView
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityBannerLayoutBinding

/**
 * Create by Kevin-Tu on 2019/6/11.
 */
class BannerActivity : BaseActivity() {

    private lateinit var binding: ActivityBannerLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBannerLayoutBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        title = "BannerLayout"

        var bannerData = ArrayList<String>()
        bannerData.add("https://kimg.cdn.video.9ddm.com/kv-cms-image/156005C5BAF40FF51A327F1C34F2975B_1560149471231.jpg")
        bannerData.add("https://kimg.cdn.video.9ddm.com/kv-cms-image/F3CCDD27D2000E3F9255A7E3E2C48800_1560138044169.jpg")
        bannerData.add("https://kimg.cdn.video.9ddm.com/kv-cms-image/9FA23FC504EE7793B9BFB0C5CD868075_1559809920015.jpg")
        bannerData.add("https://kimg.cdn.video.9ddm.com/kv-cms-image/30E62FDDC14C05988B44E7C02788E187_1560139095519.jpg")

        var adapter = PlaylistBannerPagerAdapter(this)

        val indicatorView =
            LayoutInflater.from(this).inflate(R.layout.view_banner_indicator, null) as BannerIndicatorView
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.bottomToBottom = R.id.view_pager
        layoutParams.startToStart = R.id.view_pager
        layoutParams.endToEnd = R.id.view_pager
        layoutParams.bottomMargin = 27

        binding.banner.addIndicatorView(indicatorView, layoutParams)
        binding.banner.setAdapter(adapter)
        binding.banner.setData(bannerData)
    }
}