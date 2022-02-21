package com.kevin.javademo;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by tuchuantao on 2022/2/21
 * Desc: 函数式接口
 */
public class Function {

  @RequiresApi(api = Build.VERSION_CODES.N)
  public static void main(String[] args) {

    // 函数型接口：Function<T, R>
    java.util.function.Function<String, Integer> fun = Integer::parseInt;
    Integer value = fun.apply("123");
    System.out.println(value);

    // 消费型接口：Consumer<T>
    Consumer consumer = System.out::println;
    consumer.accept("hello function");

    // 供给型接口：Supplier<T>
    Supplier<String> supplier = () -> "Hello World !!";
    System.out.println(supplier.get());
  }
}
