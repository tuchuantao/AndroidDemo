package com.kevin.demo.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner


/**
 * Create by Kevin-Tu on 2019/5/30.
 */
open abstract class BaseActivity<T: ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Lifecycle", this::class.java.simpleName + " onCreate()")
        option()

        binding = initBinding()
        if (binding != null) {
            setContentView(binding.root)
        }

        initView(savedInstanceState)

        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                Log.d("Lifecycle2", this::class.java.simpleName + "  onStateChanged() source= " + source + " event= " + event)
            }
        })
    }

    private fun initBinding(): T {
        return DataBindingUtil.inflate(LayoutInflater.from(this), getLayoutResId(), null, false)
    }

    abstract fun getLayoutResId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    open fun option() {
    }

    override fun onStart() {
        super.onStart()
        Log.d("Lifecycle", this::class.java.simpleName + " onStart()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Lifecycle", this::class.java.simpleName + " onRestart()")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("Lifecycle", this::class.java.simpleName + " onNewIntent()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Lifecycle", this::class.java.simpleName + " onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Lifecycle", this::class.java.simpleName + " onStop()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Lifecycle", this::class.java.simpleName + " onResume()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Lifecycle", this::class.java.simpleName + " onDestroy()")
    }

    fun log(msg: String) {
        Log.d(this::class.java.simpleName, msg)
    }
}