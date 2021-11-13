package com.example.myapplication.learn.singleton

/**
 * Created by zl on 2021/11/13.
 *
 */

/**
 * TODO 单例
 */
fun singletonLearn() {

    Singleton1.show()
    Singleton2.getInstance().show()
    Singleton3.getInstance().show()
    Singleton4.instance.show()

    SingletonJava1.singleTon1.show()
    SingletonJava2.getInstance().show()
    SingletonJava3.getInstance().show()
    SingletonJava4.getInstance().show()

}