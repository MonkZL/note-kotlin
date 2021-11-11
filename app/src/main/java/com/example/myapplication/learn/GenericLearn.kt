package com.example.myapplication.learn

/**
 * TODO 定义泛型类
 */
class G1<T>(private val obj: T) {
    fun show() = println("万能输出器:$obj")
}

data class Student1(val name: String, val age: Int)
data class Student2(val name: String, val age: Int)

fun genericLearn() {

    //TODO 定义泛型类
    G1(Student1(name = "zl",age = 27)).show()
    G1(Student2(name = "yz",age = 26)).show()
}