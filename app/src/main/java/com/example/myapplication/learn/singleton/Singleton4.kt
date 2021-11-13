package com.example.myapplication.learn.singleton

/**
 * Created by zl on 2021/11/13.
 *
 */

/**
 * 懒汉式 双重校验安全
 */
class Singleton4 private constructor() {

    companion object {
        val instance: Singleton4 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { Singleton4() }
    }

    fun show() = println("kotlin : 懒汉式 双重校验安全")
}