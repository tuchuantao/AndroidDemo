package com.kevin.demo.module.banner.base

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.kevin.demo.R
import com.kevin.demo.databinding.BannerLayoutBinding
import java.lang.Exception

/**
 * Create by Kevin-Tu on 2019/3/8.
 */
class BannerLayout : ConstraintLayout {

    companion object {
        private const val MIN_CYCLE_SIZE = 3 // 循环滑动的最小个数
    }

    private lateinit var binding: BannerLayoutBinding
    private lateinit var mHandler: Handler
    private var indicatorView: IndicatorViewInterface? = null
    private var pageMargin: Int = 0
    private var autoWheel: Boolean = false
    private var wheelDuration: Long = 3000
    private var cycleEnable: Boolean = false
    private var realCycleEnable: Boolean = cycleEnable
    private var pageSelectListener: ViewPager.OnPageChangeListener? = null

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        mHandler = Handler()
        binding = BannerLayoutBinding.inflate(LayoutInflater.from(context), this, true)

        val typeArray = context.theme.obtainStyledAttributes(attrs, R.styleable.BannerLayout, 0, 0)
        try {
            pageMargin = typeArray.getDimensionPixelSize(R.styleable.BannerLayout_pageMargin, 0)
            autoWheel = typeArray.getBoolean(R.styleable.BannerLayout_autoWheel, false)
            var duration = typeArray.getInt(R.styleable.BannerLayout_wheelDuration, 3000).toLong()
            if (duration > 1000) {
                wheelDuration = duration
            }
            cycleEnable = typeArray.getBoolean(R.styleable.BannerLayout_cycleEnable, false)
            realCycleEnable = cycleEnable
        } finally {
            typeArray.recycle()
        }

        if (pageMargin > 0) {
            //binding.viewPager.clipToPadding = false
            //binding.viewPager.setPadding(pageMargin, 0, pageMargin, 0)
            binding.viewPager.pageMargin = pageMargin
        }

        initPagerListener()
    }

    private fun initPagerListener() {
        binding.viewPager.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> { // 用户触摸时，停止轮播计时
                    if (autoWheel) {
                        mHandler.removeCallbacksAndMessages(null)
                    }
                }
                MotionEvent.ACTION_UP -> {
                    if (autoWheel) {
                        mHandler.postDelayed({ scrollToNextItem() }, wheelDuration)
                    }
                }
            }
            false
        }

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                if (autoWheel) {
                    when (state) {
                        ViewPager.SCROLL_STATE_IDLE -> mHandler.postDelayed(
                            { scrollToNextItem() },
                            wheelDuration
                        ) // 滚动结束后开始轮播计时
                        ViewPager.SCROLL_STATE_DRAGGING -> mHandler.removeCallbacksAndMessages(null) // 滚动时停止轮播计时
                    }
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (realCycleEnable && positionOffset == 0f && positionOffsetPixels == 0) {
                    if (position == 0) {
                        binding.viewPager.setCurrentItem(binding.viewPager.adapter!!.count - 2, false)
                    } else if (position == binding.viewPager.adapter!!.count - 1) {
                        binding.viewPager.setCurrentItem(1, false)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                if (realCycleEnable) {
                    var newPosition = position
                    if (position == binding.viewPager.adapter!!.count - 1) {
                        newPosition = 1
                    } else if (position == 0) {
                        newPosition = binding.viewPager.adapter!!.count - 2
                    }
                    updateSelectPosition(newPosition - 1)
                } else {
                    updateSelectPosition(position)
                }
            }
        })
    }

    private fun updateSelectPosition(position: Int) {
        updateIndicatorView(position)
        pageSelectListener?.let {
            pageSelectListener!!.onPageSelected(position)
        }
    }

    /**
     * 获取映射后的位置
     */
    private fun getRealPosition(position: Int): Int {
        if (!realCycleEnable) {
            return position
        }
        return when (position) {
            0 -> binding.viewPager.adapter!!.count - 2
            binding.viewPager.adapter!!.count - 1 -> 1
            else -> position
        }
    }

    fun setOnPageSelectListener(pageSelectListener: ViewPager.OnPageChangeListener) {
        this.pageSelectListener = pageSelectListener
    }

    /**
     * 设置自动轮播的间隔时间
     */
    fun setWheelDuration(duration: Long) {
        if (duration > 1000) {
            this.wheelDuration = duration
            if (autoWheel) {
                mHandler.removeCallbacksAndMessages(null)
                mHandler.postDelayed({ scrollToNextItem() }, wheelDuration)
            }
        }
    }

    /**
     * 设置ViewPager切换指示View
     */
    fun addIndicatorView(indicatorView: BannerIndicatorView, layoutParams: LayoutParams) {
        addView(indicatorView, layoutParams)
        this.indicatorView = indicatorView
    }

    /**
     * 更新指示器
     */
    private fun updateIndicatorView(selectedPosition: Int) {
        indicatorView?.onPageSelected(selectedPosition)
    }

    fun <T> setAdapter(adapter: BannerBasePagerAdapter<T>) {
        binding.viewPager.adapter = adapter
    }

    /**
     * 设置ViewPager的数据源
     * 如果需要支持循环切换，必须将最后一页View同时添加到第一页，将真实的第一页View同时添加到最后一页
     * Example:  真实数据：    1, 2, 3, 4
     *      支持循环的数据： 4, 1, 2, 3, 4, 1
     */
    fun <T> setData(dataList: ArrayList<T>) {
        binding.viewPager.offscreenPageLimit = dataList.size + 2
        if (binding.viewPager.adapter != null && dataList.isNotEmpty()) {
            mHandler.removeCallbacksAndMessages(null)
            try {
                // 初始化IndicatorView
                indicatorView?.initPageCount(dataList.size)
                updateIndicatorView(0)

                if (cycleEnable && dataList.size >= MIN_CYCLE_SIZE) {
                    realCycleEnable = true
                    var newList = ArrayList<T>()
                    newList.add(dataList[dataList.size - 1])
                    newList.addAll(dataList)
                    newList.add(dataList[0])
                    (binding.viewPager.adapter as BannerBasePagerAdapter<T>).setData(newList)
                } else {
                    realCycleEnable = false
                    (binding.viewPager.adapter as BannerBasePagerAdapter<T>).setData(dataList)
                }
                if (autoWheel) {
                    mHandler.postDelayed({ scrollToNextItem() }, wheelDuration)
                }
                binding.viewPager.adapter?.notifyDataSetChanged()
                if (realCycleEnable) {// 如果开启循环，真实的第一页其实是在第二页的位置
                    binding.viewPager.setCurrentItem(1, false)
                } else {
                    binding.viewPager.setCurrentItem(0, false)
                    pageSelectListener?.onPageSelected(0)
                }
            } catch (e: Exception) {
            }
        }
    }

    /**
     * 轮播，显示下一个item
     */
    @Synchronized
    private fun scrollToNextItem() {
        mHandler.removeCallbacksAndMessages(null)

        var newPosition: Int
        if (realCycleEnable) {
            newPosition = (binding.viewPager.currentItem + 1) % (binding.viewPager.adapter!!.count - 1)
            if (newPosition == 0) {
                newPosition = binding.viewPager.adapter!!.count - 1
            }
        } else {
            newPosition = (binding.viewPager.currentItem + 1) % binding.viewPager.adapter!!.count
        }
        binding.viewPager.setCurrentItem(newPosition, true)

        mHandler.postDelayed({ scrollToNextItem() }, wheelDuration)
    }

    fun setViewPagerHeight(height: Int) {
        binding.viewPager.layoutParams.height = height
    }

    fun setPageTransformer(reverseDrawingOrder: Boolean, transformer: ViewPager.PageTransformer) {
        binding.viewPager.setPageTransformer(reverseDrawingOrder, transformer)
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (autoWheel && binding.viewPager.adapter != null && binding.viewPager.adapter!!.count > 0) {
            if (visibility == View.VISIBLE) {
                mHandler.postDelayed({ scrollToNextItem() }, wheelDuration)
            } else {
                mHandler.removeCallbacksAndMessages(null)
            }
        }
    }

    fun getCurrentItem(): Int {
        return binding.viewPager.currentItem
    }
}