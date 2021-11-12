package com.example.myapplication.learn

import java.io.File
import java.nio.charset.Charset
import java.util.ArrayList

/**
 * TODO 扩展函数
 */
class E1(val name: String) {
    fun showName() = println(name)
}

fun E1.getNameLength() = name.length

/**
 * TODO 对库内容进行扩展
 */
fun String.addExt(ext: String) = "${this}${ext}"

/**
 * TODO 覆盖原有方法
 *  1.自己写的扩展函数是不能有两个，否则编译不通过
 *  2.KT内置的扩展函数，被我们重复定义，属于覆盖，优先使用我们的
 */
fun File.readLines(charset: Charset = Charsets.UTF_8): List<String> {
    println("我们自己重新的readLines")
    val result = ArrayList<String>()
    forEachLine(charset) { result.add(it); }
    return result
}

//fun String.addExt(ext: String) = "${this}${ext}" //TODO 自己写的扩展函数是不能有两个的

/**
 * TODO 泛型扩展
 */
fun <T> T.show() = println("show me")

fun commonFun() = println("commonFun")

/**
 * TODO 内置函数的实现原理
 *  I.() 是什么意思
 */
inline fun <I> I.myApply(lambda: I.() -> Unit): I {
    lambda(this)
    return this
}

//TODO inline 我们使用的事高阶函数，所以用到内联，做lambda的优化，提高性能
inline fun <I, O> I.myLet(lambda: (I) -> O): O = lambda(this)

/**
 * TODO 属性扩展
 *  为 String 扩展一个 myInfo 属性
 */
val String.myInfo: String
    get() = "AA"

fun extendFunLearn() {

    //TODO 扩展函数
    println(E1("zl").getNameLength())

    //TODO 对库内容进行扩展
    println("zl".addExt(" love yz"))

    //TODO 覆盖原有方法
    //File("").readLines()

    //TODO 泛型扩展
    1.show()
    "".show()
    true.show()
    'a'.show()
    commonFun().show()


    //TODO 内置函数的实现原理
    println("zl".myApply {
        println("myApply")
    })

    println("zl".myLet() {
        "$it love yz"
    })

    //TODO 属性扩展
    println("zl".myInfo)

}