package com.example.myapplication.learn.singleton;

/**
 * Created by zl on 2021/11/13.
 */

/**
 * 懒汉式
 */
public class SingletonJava2 {

    private static SingletonJava2 singleton2 = null;

    private SingletonJava2(){}

    public static SingletonJava2 getInstance() {
        if (singleton2 == null) {
            singleton2 = new SingletonJava2();
        }
        return singleton2;
    }

    public void show() {
        System.out.println("Java : 懒汉式");
    }
}
