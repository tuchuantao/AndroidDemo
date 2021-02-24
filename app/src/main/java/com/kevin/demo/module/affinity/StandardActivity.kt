package com.kevin.demo.module.affinity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.kevin.demo.R
import com.kevin.demo.base.BaseActivity
import com.kevin.demo.databinding.ActivityWithBtnBinding

/**
 * Create by Kevin-Tu on 2020/5/29.
 */
class StandardActivity: BaseActivity<ActivityWithBtnBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_with_btn
    }

    override fun initView(savedInstanceState: Bundle?) {
        android.util.Log.v("kevin",  "${this::class.java.simpleName}  taskId=${taskId}")
        var input = intent.getStringExtra("name")
        Toast.makeText(this, "input=$input", Toast.LENGTH_LONG).show()
        title = "${this::class.java.simpleName}  taskId=${taskId}"
        binding.btnOne.setOnClickListener {
//            var intent = Intent(this, ActAffinityActivity::class.java)
//            startActivity(intent)

            // Test Activity result
            var intent = Intent()
            intent.putExtra("result", "Hello world !!  Result")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        binding.btnTwo.setOnClickListener {
            var intent = Intent(this, SingleTopActivity::class.java)
            startActivity(intent)
        }
    }
}