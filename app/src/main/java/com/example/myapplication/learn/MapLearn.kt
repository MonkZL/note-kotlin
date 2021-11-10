package com.example.myapplication.learn

import android.os.Build
import androidx.annotation.RequiresApi

//map字典
@RequiresApi(Build.VERSION_CODES.N)
fun mapLearn() {
    /**
     * TODO 不可变字典操作
     */
    val map1 = mapOf("zl" to 1, "yz" to 2)
    val map2 = mapOf(Pair("zl", 1), Pair("yz", 2))
    println(map1.get("zl"))
    println(map1["zl"])//运算符重载

    /**
     * TODO 获取数据方法
     */
    //第1种
    println(map1["zxx"])//找不到不会报错 返回null
    //第2种
    println(map1.getOrDefault("zxx", -1))//找不到不会报错 返回默认值
    //第3种
    println(map1.getOrElse("zxx") { -1 })//找不到不会报错 返回默认值
    //第4种
    //println(map1.getValue("zxx"))//找不到会崩溃

    /**
     * TODO 遍历
     */
    //第1种方式
    map2.forEach() {
        println("${it.key} -- ${it.value}")
    }
    //第2种方式
    map2.forEach() { key: String, value: Int ->
        println("$key -- $value")
    }
    //第3种方式
    map2.forEach() { (key: String, value: Int) ->
        println("$key -- $value")
    }
    //第4种方式
    for (entry in map2) {
        println("${entry.key} -- ${entry.value}")
    }

    /**
     * TODO 可变字典操作
     */
    val map3 = mutableMapOf("zl" to 1, "yz" to 2, Pair("zdd", 3))
    println(map3)
    //设置值
    map3 += Pair("zxx", 4)
    println(map3)
    //删除值
    map3 -= "zxx"
    println(map3)
    //设置值
    map3["zxx"] = 4
    println(map3)
    //删除值
    map3.remove("zxx")
    println(map3)
    //设置值
    map3.put("zxx", 4)
    println(map3)
    //如果没有zbb就新增一个
    map3.getOrPut("zbb") { 6 }
    //若是有的话直接获取 不做修改
    map3.getOrPut("zbb") { 7 }
    println(map3)
}