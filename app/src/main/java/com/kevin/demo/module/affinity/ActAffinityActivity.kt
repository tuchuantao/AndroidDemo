package com.kevin.demo.module.affinity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityWithBtnBinding

/**
 *1、singleInstance 会新建一个task 栈，该栈有且仅有这个一个activity任务
 *
 *      （1）         （2）                                  （1）
 * 2、主任务栈  -> singleInstance / affinity (新的任务栈)  -> 主任务栈
 *    此时back键，不会回到新的任务栈中的任务，会在主任务栈中寻找上一个任务Activity
 *
 *
 *      （2）             （2）                                     （3）
 * 3、主任务栈 -> singleInstance / affinity (新的任务栈) ->  singleInstance / affinity (新的任务栈)
 *    此时back键，会(3) ->  back(2)  -> (1)
 *
 * Create by Kevin-Tu on 2020/5/29.
 */
class ActAffinityActivity : BaseActivity<ActivityWithBtnBinding>() {


    override fun initBinding(): ActivityWithBtnBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_with_btn, null, false)
    }

    override fun initView(savedInstanceState: Bundle?) {
        android.util.Log.v("kevin",  "${this::class.java.simpleName}  taskId=${taskId}")
        title = "${this::class.java.simpleName}  taskId=${taskId}"
        binding.btnOne.setOnClickListener {
            var intent = Intent(this, SingleInstanceActivity::class.java)
            startActivity(intent)
        }

        binding.btnTwo.setOnClickListener {
            var intent = Intent(this, SingleInstanceTwoActivity::class.java)
            startActivity(intent)
        }
    }
    /**

     */
}