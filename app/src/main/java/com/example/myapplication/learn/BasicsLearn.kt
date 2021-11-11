package com.example.myapplication.learn


//TODO 编译时常量
const val a3: Int = 1;

//TODO 函数
//默认都是 public
public fun method0(age: Int, name: String): Int {
    println("年纪：$age，名字：$name")
    return age
}

//TODO 函数默认参数
fun method1(age: Int = 27, name: String): Int {
    println("年纪：$age，名字：$name")
    return age
}

//TODO Nothing类型
fun method2() {
    //这个东西不是TODO注释 它会终止程序
    TODO("这句话不删除会报错")
}

//TODO 反引号函数名
fun `登录`() {
    println("登录")
}

//由于 is 在KT中是关键词 所以不能直接用 is 做函数名
fun `is`() = println("is")


fun basicsLearn() {

    //TODO Unit 无返回类型

    //TODO 变量声明
    //可读写变量
    var a: Int = 1
    //只读变量
    val a1: Int = 1

    //TODO 类型推断
    val a2 = 1 //类型推断为 Int

    //TODO range表达式
    val a4 = 20
    if (a4 in 60..100) {
        println("及格")
    } else if (a4 in 40..60) {
        println("不及格")
    } else {
        println("不及格 且 成绩很差")
    }

    //TODO when表达式
    val a5 = 1
    when (a5) {
        1 -> println("xxxx")
        2 -> println("xxxx")
        3 -> println("xxxx")
        in 0 until 10 -> println("xxxx")
        else -> println("xxxxx")
    }

    //TODO String模板
    val garden = "黄石公园"
    println("去$garden,看风景")

    //TODO 函数
    method0(name = "zl", age = 27)

    //TODO 函数默认参数 和 具名
    // 具名 method(name="zl") 此处name就是具名
    method1(name = "zl")

    //TODO Nothing类型
    //method2()

    //TODO 反引号函数名
    登录()
    `is`()

    //TODO 匿名函数
    val count = "zl".count {
        it == 'z'
    }

    //TODO 函数类型和隐式返回
}