package com.example.myapplication.learn

import java.io.File

//数组
fun arrLearn() {
    /**
     * TODO 数组操作
     */
    val intArray1 = intArrayOf(1, 2, 3, 4, 5)
    println(intArray1[0])
    //println(intArray1[5])//会崩溃
    println(intArray1.elementAtOrElse(5) { 0 })//越界 就取默认值
    println(intArray1.elementAtOrNull(5) ?: "越界")//越界 就取null

    /**
     * TODO 集合转数组
     */
    val intArray2 = listOf(1, 2, 3, 4).toIntArray()

    /**
     * TODO 对象数组
     */
    val objArr = arrayOf(File("a"), File("b"), File("c"))
}