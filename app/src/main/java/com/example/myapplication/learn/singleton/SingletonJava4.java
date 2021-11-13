package com.example.myapplication.learn.singleton;

/**
 * Created by zl on 2021/11/13.
 */

/**
 * 懒汉式 双重校验安全
 */
public class SingletonJava4 {

    private static volatile SingletonJava4 singleton4 = null;

    private SingletonJava4() {
    }

    public static SingletonJava4 getInstance() {
        if (singleton4 == null) {
            synchronized (SingletonJava4.class) {
                if (singleton4 == null) {
                    singleton4 = new SingletonJava4();
                }
            }
        }
        return singleton4;
    }

    public void show() {
        System.out.println("Java : 懒汉式 双重校验安全");
    }
}
