package com.example.myapplication.learn.singleton;

/**
 * Created by zl on 2021/11/13.
 */

/**
 * 懒汉式
 */
public class SingletonJava3 {

    private static SingletonJava3 singleton3 = null;

    private SingletonJava3(){}

    public static synchronized SingletonJava3 getInstance() {
        if (singleton3 == null) {
            singleton3 = new SingletonJava3();
        }
        return singleton3;
    }

    public void show() {
        System.out.println("Java : 懒汉式 安全");
    }
}
