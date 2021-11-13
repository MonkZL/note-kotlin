package com.example.myapplication.learn.annotation

/**
 *  TODO JvmStatic
 */
class JvmStaticAnnotation {

    companion object {
        const val TARGET = "黄石公园"
        @JvmStatic
        fun showAction(name: String) = println("$name 要去 $TARGET 玩")
    }

}