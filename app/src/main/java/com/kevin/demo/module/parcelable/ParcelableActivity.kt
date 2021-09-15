package com.kevin.demo.module.parcelable

import android.os.Bundle
import android.os.Parcel
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityParcelableBinding

/**
 * Created by tuchuantao on 2021/9/15
 * Desc:  验证：序列化 和 反序列化顺序和类型不同是，会出现乱码，不会崩溃
 */
class ParcelableActivity : BaseActivity<ActivityParcelableBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_parcelable
    }

    override fun initView(savedInstanceState: Bundle?) {
        var userInfo = UserInfo("张三", 18)
        var parcel: Parcel = Parcel.obtain()
        binding.btn1.setOnClickListener {
            userInfo.writeToParcel(parcel, 0)
        }

        binding.btn2.setOnClickListener {
            var user = UserInfo.CREATOR.createFromParcel(parcel)
            log(user.toString())
        }
    }
}