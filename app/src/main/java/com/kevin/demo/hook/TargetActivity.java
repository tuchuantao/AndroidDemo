package com.kevin.demo.hook;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.kevin.demo.R;

/**
 * Created by tuchuantao on 2022/4/7
 * Desc: 没有在 Manifest 中配置的Activity
 */
public class TargetActivity extends Activity {

  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.v("kevin", "TargetActivity onCreate()");
    setContentView(R.layout.activity_target);
  }
}
