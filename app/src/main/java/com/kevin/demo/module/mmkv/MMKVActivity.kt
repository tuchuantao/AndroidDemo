package com.kevin.demo.module.mmkv

import android.os.Bundle
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityEmptyBinding
import com.tencent.mmkv.MMKV
import java.util.*


/**
 * Created by tuchuantao on 2021/11/2
 * Desc: MMKV 是基于 mmap 内存映射的 key-value 组件，底层序列化/反序列化使用 protobuf 实现，性能高，稳定性强。
 * https://github.com/Tencent/MMKV
 */
class MMKVActivity: BaseActivity<ActivityEmptyBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_empty
    }

    override fun initView(savedInstanceState: Bundle?) {
        demo()
    }

    private fun demo() {
        val kv = MMKV.defaultMMKV()

        kv.encode("bool", true)
        println("bool: " + kv.decodeBool("bool"))

        kv.encode("int", Int.MIN_VALUE)
        println("int: " + kv.decodeInt("int"))

        kv.encode("long", Long.MAX_VALUE)
        println("long: " + kv.decodeLong("long"))

        kv.encode("float", -3.14f)
        println("float: " + kv.decodeFloat("float"))

        kv.encode("double", Double.MIN_VALUE)
        println("double: " + kv.decodeDouble("double"))

        kv.encode("string", "Hello from mmkv")
        println("string: " + kv.decodeString("string"))

        val bytes = byteArrayOf('m'.toByte(), 'm'.toByte(), 'k'.toByte(), 'v'.toByte())
        kv.encode("bytes", bytes)
        println("bytes: " + String(kv.decodeBytes("bytes")!!))


        var value = kv.decodeBool("bool")
        println("value: " + value)

        kv.removeValueForKey("bool")
        println("bool: " + kv.decodeBool("bool"))

        kv.removeValuesForKeys(arrayOf("int", "long"))
        System.out.println("allKeys: " + Arrays.toString(kv.allKeys()))

        val hasBool = kv.containsKey("bool")
    }
}