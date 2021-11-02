package com.kevin.demo.module.screenshoot

import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityScreenShootBinding
import java.util.*

/**
 * Created by tuchuantao on 2021/11/2
 * Desc: 截取ScrollView与RecyclerView的长图
 */
class ScreenShootActivity : BaseActivity<ActivityScreenShootBinding>(), View.OnClickListener {

    private val path: String = Environment.getExternalStorageDirectory().path

    override fun getLayoutResId(): Int {
        return R.layout.activity_screen_shoot
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.btnScreen.setOnClickListener(this)
        binding.btnRecyclerview.setOnClickListener(this)
        binding.btnScrollview.setOnClickListener(this)

        val list: ArrayList<DataEntity> = ArrayList()
        for (i in 0..19) {
            val dataEntity = DataEntity()
            dataEntity.title = "第" + i + "项"
            list.add(dataEntity)
        }
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        binding.rvList.layoutManager = manager
        binding.rvList.adapter = RecyclerAdapter(this, list)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_screen -> {
                binding.tvTip.text = "我是Activity的截图"
                binding.ivScreen.setImageBitmap(
                    ScreenShoot.screenShotWholeScreen(this)
                )
            }
            R.id.btn_scrollview -> {
                binding.tvTip.text = "我是scrollview的截图"
                binding.ivScreen.setImageBitmap(
                    ScreenShoot.getScrollViewBitmap(
                        binding.svMain, "$path/scrollview.jpg"
                    )
                )
            }
            R.id.btn_recyclerview -> {
                binding.tvTip.text = "我是recyclerview的截图"
                binding.ivScreen.setImageBitmap(
                    ScreenShoot.getRecyclerviewBitmap(
                        binding.rvList, "$path/recyclerview.jpg"
                    )
                )
            }
        }
    }

}