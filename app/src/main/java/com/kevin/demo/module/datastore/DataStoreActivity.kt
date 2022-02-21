package com.kevin.demo.module.datastore

import android.os.Bundle
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityEmptyBinding

/**
 * Created by tuchuantao on 2021/12/20
 * Desc:
 */
class DataStoreActivity : BaseActivity<ActivityEmptyBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_empty
    }

    override fun initView(savedInstanceState: Bundle?) {
    }
}