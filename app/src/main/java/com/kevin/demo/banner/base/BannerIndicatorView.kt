package com.kevin.demo.banner.base

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import com.kevin.demo.R
import com.kevin.demo.databinding.BannerIndicatorBinding

/**
 * Create by Kevin-Tu on 2019/3/12.
 */
class BannerIndicatorView : LinearLayout, IndicatorViewInterface {

    private var mIndicatorViews: ArrayList<ImageView> = ArrayList()
    private var lastPosition = 0
    private var selectResource = R.drawable.home_indicator_view_selected
    private var unselectResource = R.drawable.home_indicator_view_normal

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initResource(context, attrs)
        gravity=Gravity.CENTER_VERTICAL
    }

    private fun initResource(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            var ta: TypedArray? = null
            try {
                ta = context.obtainStyledAttributes(attrs, R.styleable.BannerIndicatorView)

                try {
                    selectResource = ta.getResourceId(
                        R.styleable.BannerIndicatorView_selectDrawable,
                        R.drawable.home_indicator_view_selected
                    )
                } catch (e: Exception) {
                    Log.e("BannerIndicatorView", "one e: $e")
                }

                try {
                    unselectResource = ta.getResourceId(
                        R.styleable.BannerIndicatorView_unselectDrawable,
                        R.drawable.home_indicator_view_normal
                    )
                } catch (e: Exception) {
                    Log.e("BannerIndicatorView", "two e: $e")
                }
            } finally {
                ta?.recycle()

            }
        }
    }

    override fun initPageCount(pageCount: Int) {
        if (pageCount <= 0) return
        removeAllViews()
        mIndicatorViews.clear()
        for (i in 0 until pageCount) {
            var binding = BannerIndicatorBinding.inflate(LayoutInflater.from(context), this, false)
            binding.indicator.setImageResource(unselectResource)
            mIndicatorViews.add(binding.indicator)
            addView(binding.root)
        }
        lastPosition = 0
        mIndicatorViews[0].setImageResource(selectResource)
    }

    override fun onPageSelected(position: Int) {
        if (lastPosition != position) {
            val lastLayout = mIndicatorViews[lastPosition % mIndicatorViews.size].layoutParams
            lastLayout.height = context.resources.getDimensionPixelOffset(R.dimen.banner_indicator_radius)
            lastLayout.width = context.resources.getDimensionPixelOffset(R.dimen.banner_indicator_radius)
            mIndicatorViews[lastPosition % mIndicatorViews.size].layoutParams = lastLayout

            val layout = mIndicatorViews[position % mIndicatorViews.size].layoutParams
            layout.height = context.resources.getDimensionPixelOffset(R.dimen.banner_indicator_radius_select)
            layout.width = context.resources.getDimensionPixelOffset(R.dimen.banner_indicator_radius_select)
            mIndicatorViews[position % mIndicatorViews.size].layoutParams = layout

            mIndicatorViews[lastPosition % mIndicatorViews.size].setImageResource(unselectResource)
            mIndicatorViews[position % mIndicatorViews.size].setImageResource(selectResource)
            lastPosition = position
        }
    }

}