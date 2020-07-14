package com.kevin.demo.kotlinexercise.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Create by Kevin-Tu on 2020/7/1.
 */

//https://www.jianshu.com/p/e1061501bdc4
fun main() {

    threadPool()


    // 阻塞主线程以保证JVM存活
    Thread.sleep(200000L)
}

fun threadPool() {
    println("Main: ThreadName=${Thread.currentThread().name}  threadId=${Thread.currentThread().id}")

    repeat(100) {
        GlobalScope.launch(Dispatchers.IO) {
            //println("Sub ${it}: ThreadName=${Thread.currentThread().name}  threadId=${Thread.currentThread().id}")
            println("Sub: ThreadName=${Thread.currentThread().name}  threadId=${Thread.currentThread().id}")
        }
    }
}

fun execute() {
    GlobalScope.launch(Dispatchers.Main) {

    }
}