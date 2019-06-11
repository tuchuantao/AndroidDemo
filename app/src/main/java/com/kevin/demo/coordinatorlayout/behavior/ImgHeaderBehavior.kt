package com.kevin.demo.coordinatorlayout.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.kevin.demo.util.ScreenUtil

/**
 * Create by Kevin-Tu on 2019/5/30.
 */
class ImgHeaderBehavior: CoordinatorLayout.Behavior<FrameLayout> {

    private var lastHeight: Int = 0

    constructor()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FrameLayout,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes and ViewCompat.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: FrameLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
        if (canScroll(child, dy.toFloat())) {
            var finalHeight = child.height - dy
            val minHeight = getMinHeight(child.context)
            val maxHeight = getMaxHeight(child.context)
            if (finalHeight < minHeight) {
                finalHeight = minHeight
            } else if (finalHeight > maxHeight) {
                finalHeight = maxHeight
            }
            lastHeight = child.height
            changeHeight(child, finalHeight)

            // 让CoordinatorLayout消费滑动事件
            consumed[1] = dy
        }
    }

    private fun canScroll(child: View, scrollY: Float): Boolean {
        if (scrollY > 0) { // 上滑
            return child.height >= getMinHeight(child.context) && lastHeight != getMinHeight(child.context)
        } else { // 下滑
            return child.height < getMaxHeight(child.context) && lastHeight != getMaxHeight(child.context)
        }
    }

    private fun changeHeight(view: FrameLayout, height: Int) {
        val layoutParams: ViewGroup.LayoutParams = view.layoutParams as ViewGroup.LayoutParams
        layoutParams.height = height
        view.layoutParams = layoutParams
        view.requestLayout()
    }

    private fun getMinHeight(context: Context): Int {
        return ScreenUtil.getScreenWidth(context) / 16 * 9
    }

    private fun getMaxHeight(context: Context): Int {
        return (ScreenUtil.getScreenWidth(context) * 1.01).toInt()
    }
}