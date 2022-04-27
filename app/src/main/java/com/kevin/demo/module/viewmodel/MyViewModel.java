package com.kevin.demo.module.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kevin.aidlserver.User;

import java.util.List;

/**
 * Created by tuchuantao on 2022/4/26
 * Desc:
 */
public class MyViewModel extends ViewModel {
  private MutableLiveData<List<User>> users;

  public LiveData<List<User>> getUsers() {
    if (users == null) {
      users = new MutableLiveData<>();
      loadUsers();
    }
    return users;
  }

  private void loadUsers() {
    // Do an asynchronous operation to fetch users.
  }
}
