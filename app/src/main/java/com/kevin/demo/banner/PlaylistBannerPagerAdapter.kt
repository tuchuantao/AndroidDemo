package com.kevin.demo.banner

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kevin.demo.banner.base.BannerBasePagerAdapter
import com.kevin.demo.databinding.ItemBannerBinding

class PlaylistBannerPagerAdapter(private val context: Context) : BannerBasePagerAdapter<String>() {

    private var viewList = ArrayList<View>()

    override fun setData(dataList: ArrayList<String>) {
        viewList.clear()
        dataList.forEachIndexed { index, it ->
            var binding = ItemBannerBinding.inflate(LayoutInflater.from(context))
            binding.imgUrl = it
            viewList.add(binding.root)
        }
        notifyDataSetChanged()
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return viewList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var childView: View? = null
        if (position < viewList.size) {
            childView = viewList[position]
            // 防止view被添加前存在于另一个父容器中
            if (childView.parent != null) {
                (childView.parent as ViewGroup).removeView(childView)
            }
            container.addView(childView)
        }
        return childView ?: Unit
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}