package com.kevin.demo.module.rx;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by tuchuantao on 2022/4/25
 * Desc:
 */
public class RxDemo {

  public static void main(String[] args) {
    Observable.create(new Observable.OnSubscribe<String>() {
      @Override
      public void call(Subscriber<? super String> subscriber) {
        for (int i = 0; i < 10; i++) {
          subscriber.onNext("这是一次响应事件 " + i);
        }
        subscriber.onCompleted();
      }
    }).subscribe(new Observer<String>() {
      @Override
      public void onCompleted() {

      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onNext(String s) {

      }
    });
  }
}
