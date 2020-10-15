package com.kevin.demo.util

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executors

/**
 * Created by tuchuantao on 2020/10/14
 */

private val executors = Executors.newFixedThreadPool(128)

fun <T> asyncSchedulers(): ObservableTransformer<T, T> {
    return ObservableTransformer {
        it.subscribeOn(Schedulers.from(executors))
            .observeOn(AndroidSchedulers.mainThread())
    }
}