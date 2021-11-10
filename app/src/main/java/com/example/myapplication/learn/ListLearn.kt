package com.example.myapplication.learn

import android.os.Build
import androidx.annotation.RequiresApi

//list集合
@RequiresApi(Build.VERSION_CODES.N)
fun listLearn() {
    /**
     * TODO 不可变list操作
     */
    val list = listOf(1, 2, 3, 4, 5)
    println(list.get(0))
    println(list[0])//运算符重载方式
    println(list.getOrElse(5) { "越界" })//越界 就取默认值
    println(list.getOrNull(5) ?: "越界")//越界 就取null

    /**
     * TODO 可变list操作
     */
    val mutableList = mutableListOf(1, 2, 3, 4, 5)
    println(mutableList.get(0))
    println(mutableList[0])//运算符重载方式
    println(mutableList.getOrElse(5) { "越界" })//越界 就取默认值
    println(mutableList.getOrNull(5) ?: "越界")//越界 就取null
    mutableList.add(6)//添加
    mutableList.remove(6)//移除
    mutableList.apply {
        removeAt(size - 1)
    }
    println(mutableList)

    /**
     * TODO 集合转换
     */
    list.toMutableList()//转换成可变集合
    mutableList.toList()//转换成不可变集合

    /**
     * TODO 集合添加移除数据的 运算符重载方式使用
     */
    val mutableList1 = mutableListOf("zl", "yz")
    println(mutableList1)
    mutableList1 += "zxx"//运算符重载
    println(mutableList1)
    mutableList1 -= "zxx"//运算符重载
    println(mutableList1)

    /**
     * TODO 集合的条件移除
     */
    mutableList1.removeIf { // 条件移除
        it == "zl"
    }
    println(mutableList1)

    /**
     * TODO 集合的遍历
     */
    val mutableList2 = mutableListOf("zl", "yz")
    println(mutableList2)

    //第1种方式
    for (s in mutableList2) {
        println("元素:$s")
    }

    //第2种方式
    mutableList2.forEach {
        println("元素:$it")
    }

    //第3种方式
    mutableList2.forEachIndexed { index: Int, s: String ->
        println("第${index}元素:$s")
    }

    /**
     * TODO 集合解构语法过滤元素
     */
    val list1 = listOf(1, 2, 3, 4)
    val (v1, v2, v3, v4) = list1
    println("$v1,$v2,$v3,$v4")
    val (_, n2, _, n3) = list1 // 过滤元素_下划线内部可以不接受赋值，节约性能
    println("$n2,$n3")
}