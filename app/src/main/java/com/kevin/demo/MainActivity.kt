package com.kevin.demo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.coordinatorlayout.CoordinatorLayoutActivity
import com.kevin.demo.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.btnCoordinatorLayout.setOnClickListener {
            var intent = Intent(this, CoordinatorLayoutActivity::class.java)
            startActivity(intent)
        }
    }
}
