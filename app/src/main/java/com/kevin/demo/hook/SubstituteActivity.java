package com.kevin.demo.hook;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.kevin.demo.R;

/**
 * Created by tuchuantao on 2022/4/8
 * Desc:
 */
public class SubstituteActivity extends Activity {

  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_substitue);
  }
}
