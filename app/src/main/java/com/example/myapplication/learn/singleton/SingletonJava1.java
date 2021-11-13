package com.example.myapplication.learn.singleton;

/**
 * Created by zl on 2021/11/13.
 */

/**
 * 饿汉式
 */
public class SingletonJava1 {
    public static SingletonJava1 singleTon1 = new SingletonJava1();

    private SingletonJava1(){}

    public void show() {
        System.out.println("Java : 饿汉式");
    }

}
