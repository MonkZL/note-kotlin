package com.example.myapplication.learn.annotation

/**
 *  TODO JvmOverloads 由于 Java 不支持默认参数 加上 JvmOverloads 编译器会重载一个只有 name 参数的方法
 */

@JvmOverloads
fun show(name: String, age: Int = 27, sex: Char = 'M') {
    println("name:$name,age:$age,sex:$sex")
}

fun main() {

}