package com.example.myapplication.learn

/**
 * TODO 接口
 */
interface USB {
    var deviceInfo: String
    fun insert()
}

class Mouse : USB {
    override var deviceInfo: String = ""
        get() = field.takeIf { it.isNotEmpty() } ?: "logic Mouse v2.21"

    override fun insert() {
        println("insert：deviceInfo：$deviceInfo")
    }
}

/**
 * TODO 接口的默认实现
 */
interface USB2 {
    //1.接口 var 也是不能给接口的成员赋值的 （但是有其他方法）
    //2.任何类 接口 等等 val 代表只读的，是不可以在后面动态赋值（但是有其他方法）
    //val deviceInfo: String = ""
    val deviceInfo: String
        get() = (1..100).shuffled().last().toString()
}

fun interfaceLearn() {
    //TODO 接口
    //1.接口里面所有成员 和 接口本身 都是 public open 的，所以不需要open
    //2.接口不能有主构造，反正就是没有构造
    //3.实现类不仅仅需要重写接口的函数，也要重新 接口的成员
    //4.接口实现代码区域，全部都要增加 override 关键字来修饰
    val mouse = Mouse()
    mouse.insert()
    mouse.deviceInfo = "logic Mouse v3.0"
    mouse.insert()
}