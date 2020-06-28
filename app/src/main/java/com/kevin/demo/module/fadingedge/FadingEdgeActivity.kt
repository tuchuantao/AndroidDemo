package com.kevin.demo.module.fadingedge

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityFadingEdgeBinding

/**
 * Create by Kevin-Tu on 2020/6/28.
 */
class FadingEdgeActivity : BaseActivity<ActivityFadingEdgeBinding>() {

    override fun initBinding(): ActivityFadingEdgeBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_fading_edge, null, false)
    }

    override fun initView(savedInstanceState: Bundle?) {
        var content = "这将是一段毫无意义的废话(请务必滑动它)：离开世间的肌肤撒旦家里看见老师的话上课讲话的方式地方开机速度飞快几节课深刻的发牢骚地方深刻的非健康的减肥来看还是打工哈撒收到了快放假啦健身房阿斯顿噶说的圣诞节格拉卡精神的速度来看宫颈癌是德国进口了了裸嫁时代公司的飞机快立刻就爱上对方卢萨卡减肥的拉开精神负担就爱上了；款到即发爱上了大家看法埃里克斯的叫法；所看见的风景卡上的空间里就是打发来得及看方法上看到了法律快速的反击开发建设的减肥肯定撒开放的开始发放的时刻地方撒开了解对方卡里SD卡里解放军快乐的时间流口水激昂的法律框架按理说看见对方空间按理说看见对方绿卡绿色科技发达了看见了啊；老师扩大开放理解；爱上了对方就哭了多久拉开手机的房间开了看见啦释放的空间理解立刻就爱上对方立刻骄傲；类似款发动机啊；离开教室的法律框架爱上发动机立刻就爱上了发的看见啦；时间法律框架爱上了看见对方亮机卡理解拉萨看见对方立刻骄傲十分的了解空间"
        binding.tv.text = content
        binding.tv.movementMethod = ScrollingMovementMethod()


        binding.tv2.text = content
    }
}