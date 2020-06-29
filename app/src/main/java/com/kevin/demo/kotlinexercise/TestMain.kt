package com.kevin.demo.kotlinexercise

/**
 * Create by Kevin-Tu on 2020/6/29.
 */
class TestMain(name: String) {

    /**
     * init反编译后，在构造函数中执行
     *
     * public TestMain() {
         String var1 = TestMain.class.getSimpleName() + " init() ";
         boolean var2 = false;
         System.out.println(var1);
      }
     */
    init {
        println("${TestMain::class.java.simpleName} init() ")
    }

    constructor(): this("") {
        println("TestMain second constructor() ")
    }

    constructor(name: String, age: Int) : this(name) {
        println("TestMain second constructor() ")
    }

    constructor(name: String, age: Int, sex: Int) : this(name, age) {
        println("TestMain second constructor() ")
    }
}

/**
 * Kotlin main
 */
fun main() {
    println("Hello World !!!")

    decompile()
}

fun decompile() {
    // 编译后的JAVA代码
    // Object[] arr = new Object[]{1, 2, 3, 4, "5", '6'};
    var arr = arrayOf(1, 2, 3, 4, "5", '6')
    arr.forEach {
        println(it)
    }

    // 编译后的JAVA代码
    // Integer[] arr2 = new Integer[]{1, 2, 3, 4, 5, 6};
    var arr2 = arrayOf(1, 2, 3, 4, 5, 6)
    arr2.forEach {
        println(it)
    }


    var name: String?
    name = "name"
    name?.let {
        println(it)
    }

    testNull(null)
}

fun testNull(name : String?) {
    name?.let {
        println(it)
    }
}