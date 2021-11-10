package com.example.myapplication.learn

//set集合
fun setLearn() {
    /**
     * TODO 不可变set操作
     */
    val set1 = setOf(1, 2, 3, 1)//不会出现重复元素
    println(set1)
    //set1[0] 没有这个功能
    println(set1.elementAt(0))
    println(set1.elementAt(1))
    println(set1.elementAt(2))
    //println(set1.elementAt(3))//越界会崩溃
    println(set1.elementAtOrElse(3) { "越界" })//越界 就取默认值
    println(set1.elementAtOrNull(3))//越界 就取null

    /**
     * TODO 可变set操作
     */
    val mutableSet1 = mutableSetOf(1, 2, 3, 1)
    println(mutableSet1.elementAt(0))
    println(mutableSet1.elementAt(1))
    println(mutableSet1.elementAt(2))
    //println(mutableSet1.elementAt(3))//越界会崩溃
    println(mutableSet1.elementAtOrElse(3) { "越界" })//越界 就取默认值
    println(mutableSet1.elementAtOrNull(3))//越界 就取null

    /**
     * TODO 集合添加移除数据的 运算符重载方式使用
     */
    println(mutableSet1)
    mutableSet1 += 4
    println(mutableSet1)
    mutableSet1 -= 4
    println(mutableSet1)

    /**
     * TODO 集合转换
     */
    set1.toMutableSet()//转换成可变集合
    mutableSet1.toSet()//转换成不可变集合
    val mutableList1 = mutableListOf(1, 2, 3, 4, 1, 2, 3, 4)
    println(mutableList1)
    println(mutableList1.toSet().toMutableList())//list转set自动去重
    val mutableList2 = mutableListOf(1, 2, 3, 4, 1, 2, 3, 4)
    println(mutableList2)
    println(mutableList2.distinct())//直接去重
}