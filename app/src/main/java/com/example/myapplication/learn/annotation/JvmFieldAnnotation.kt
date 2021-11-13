package com.example.myapplication.learn.annotation

/**
 * TODO JvmField
 *  加上 JvmField 在 Java 端调用的时候可以直接 new JvmFieldAnnotation().names
 */
class JvmFieldAnnotation {
    @field:JvmField
    val names = listOf("zl", "yz", "zxx")
}