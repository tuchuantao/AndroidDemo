package com.kevin.javademo;

/**
 * Created by tuchuantao on 2021/9/27
 * Desc:
 * https://blog.csdn.net/yemao_guyue/article/details/78592988
 */
public class ClassDemo {

    static class Human {
        public void print() {
            System.out.println("11");
        }
    }

    static class Man extends Human {
        public void print() {
            System.out.println("22");
        }
    }

    static class Women extends Human {
        public void print() {
            System.out.println("33");
        }
    }

    public void method(Human human) {
        System.out.println("human");
    }

    public void method(Man man) {
        System.out.println("man");
    }

    public void method(Women women) {
        System.out.println("women");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Women();
        man.print();
        woman.print();
        System.out.println(man instanceof Man);
        System.out.println(man instanceof Human);
        ClassDemo demo = new ClassDemo();
        demo.method(man);
        demo.method(woman);
        demo.method((Man) man);
    }
}
