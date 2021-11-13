package com.example.myapplication.learn.singleton

/**
 * Created by zl on 2021/11/13.
 *
 */

/**
 * 懒汉式
 */
class Singleton2 private constructor(){

    companion object {
        private var singleton: Singleton2? = null
            get() {
                if (field == null) {
                    field = Singleton2()
                }
                return field
            }

        fun getInstance() = singleton!!
    }


    fun show() = println("kotlin : 懒汉式")
}