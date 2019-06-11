package com.kevin.demo.banner.base

import androidx.viewpager.widget.PagerAdapter

/**
 * Create by Kevin-Tu on 2019/3/11.
 */
abstract class BannerBasePagerAdapter<T>: PagerAdapter() {

    abstract fun setData(dataList: ArrayList<T>)
}