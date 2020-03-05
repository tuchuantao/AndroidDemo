package com.kevin.demo.module.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityTabLayoutBinding

/**
 * https://juejin.im/post/5cf7a0f851882537b945c445
 *
 * Create by Kevin-Tu on 2020/2/28.
 */
class TabLayoutActivity : BaseActivity<ActivityTabLayoutBinding>() {

    override fun initBinding(): ActivityTabLayoutBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_tab_layout, null, false)
    }

    override fun initView(savedInstanceState: Bundle?) {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TAB1"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TAB2"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TAB3"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("TAB4"));


        binding.tabLayout2.addTab(binding.tabLayout2.newTab().setText("TAB1"));
        binding.tabLayout2.addTab(binding.tabLayout2.newTab().setText("TAB2"));
        binding.tabLayout2.addTab(binding.tabLayout2.newTab().setText("TAB3"));
        binding.tabLayout2.addTab(binding.tabLayout2.newTab().setText("TAB4"));
        binding.tabLayout2.addTab(binding.tabLayout2.newTab().setText("TAB5"));
        binding.tabLayout2.addTab(binding.tabLayout2.newTab().setText("TAB6"));
        binding.tabLayout2.addTab(binding.tabLayout2.newTab().setText("TAB7"));
        binding.tabLayout2.addTab(binding.tabLayout2.newTab().setText("TAB8"));

        binding.tabLayout2.addOnTabSelectedListener(object : BaseOnTabSelectedListener<TabLayout.Tab> {
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
            }
        })
    }
}