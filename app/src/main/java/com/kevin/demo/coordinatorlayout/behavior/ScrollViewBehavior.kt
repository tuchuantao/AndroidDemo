package com.kevin.demo.coordinatorlayout.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView

/**
 * Create by Kevin-Tu on 2019/5/30.
 */
class ScrollViewBehavior: CoordinatorLayout.Behavior<NestedScrollView> {

    constructor()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: NestedScrollView, dependency: View): Boolean {
        return dependency is FrameLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: NestedScrollView, dependency: View): Boolean {
        //计算列表y坐标，最小为0
        var y = dependency.y + dependency.height
        if (y < 0f) {
            y = 0F
        }
        child.y = y
        return true
    }
}