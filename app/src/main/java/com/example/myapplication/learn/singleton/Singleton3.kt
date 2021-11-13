package com.example.myapplication.learn.singleton

/**
 * Created by zl on 2021/11/13.
 *
 */

/**
 * 懒汉式
 */
class Singleton3 private constructor(){

    companion object {
        private var singleton: Singleton3? = null
            get() {
                if (field == null) {
                    field = Singleton3()
                }
                return field
            }
        @Synchronized
        fun getInstance() = singleton!!
    }


    fun show() = println("kotlin : 懒汉式 安全")
}