/**
 * TODO JvmName 在编译环节 修改 生成 Java 文件的类名
 *  加上 JvmName Java端调用的时候就不是 文件名.show() 而是 JvmName 定义的文件名.show()
 */
@file:JvmName("JNA")

package com.example.myapplication.learn.annotation

fun show() = println("JvmNameAnnotation")
