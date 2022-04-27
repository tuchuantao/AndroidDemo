package com.kevin.demo.module.viewmodel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.kevin.aidlserver.User;

import java.util.List;

/**
 * Created by tuchuantao on 2022/4/26
 * Desc:
 */
public class ViewModelActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

//    setContentView();
    // NOTE: ViewModelProvider
    MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
    // NOTE: 通过 LiveData 回调到 UI 线程
    viewModel.getUsers().observe(this, new Observer<List<User>>() {
      @Override
      public void onChanged(List<User> users) {
        // NOTE: Update UI
      }
    });


    // NOTE: new ViewModelProvider(this)   ViewModelStoreOwner 
  }

  @Nullable
  @org.jetbrains.annotations.Nullable
  @Override
  public Object getLastNonConfigurationInstance() {
    return super.getLastNonConfigurationInstance();
  }

  // ComponentActivity  mViewModelStore
  // ViewModelStore

//  public Object onRetainNonConfigurationInstance() {
//    return super.onRetainNonConfigurationInstance();
//  }
}
