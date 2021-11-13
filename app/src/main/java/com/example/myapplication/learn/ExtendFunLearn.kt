package com.example.myapplication.learn

import com.example.myapplication.learn.ext.ExtendFunLearn
import com.example.myapplication.learn.ext.randomToGetFirst
/**
 * TODO 重命名扩展
 */
import com.example.myapplication.learn.ext.randomToGetFirstToPrintln as p
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

/**
 * TODO 可空类型扩展
 */
fun String?.printlnValue() {
    println(this)
}

/**
 * TODO infix == 中缀表达式 可以简化代码
 *  自定义 中缀表达式
 *  1. 对第一个参数 C1.gogogo 函数扩展
 *  2. 需要在 括号（c2:C2）参数里面，传递一个参数
 */
infix fun <C1, C2> C1.gogogo(c2: C2) {
    println("$this,$c2")
}


/**
 * TODO DSL领域专用语言（ Domain Specified language / DSL）
 *  applyContext 就是 DSL 编程范式，定义输入输出等规则：
 *  1.定义整个 lambda 规则标准，输入，必须是 Context 这个类，才有资格调用 applyContext，匿名函数里面持有 it 和 this
 *  2.定义整个 lambda 规则标准，输出，我们会始终返回 Context 本身，所有可以链式调用
 */
class Context {
    val info = "info"
    fun toast(str: String) = println("Toast : $str")
}

inline fun Context.applyContext(lambda: Context.(String) -> Unit): Context {
    lambda(info)
    return this
}

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

    //TODO 可空类型扩展
    val str: String? = null
    str.printlnValue()
    val str1 = "zl"
    str1.printlnValue()

    //TODO infix == 中缀表达式 可以简化代码
    println(mapOf("一" to 1))
    //TODO 自定义 中缀表达式
    1 gogogo "222"

    //TODO 扩展文件
    println(listOf(1, 2, 3, 4).randomToGetFirst())

    //TODO 重命名扩展
    listOf(1, 2, 3, 4).p()

    //TODO DSL
    val applyContext: Context = Context().applyContext {
        toast(info)
    }.applyContext {
        toast("DDD")
    }

    //TODO map操作 {返回类型：T String Int ... 是把每个元素（String）加入到新集合，最后返回新集合List<String>}
    val map = listOf(1, 2, 3, 4, 5).map {
        it * 10
    }
    println(map)

    //TODO flatMap操作 {返回类型：每一个元素 T 集合1 集合2 集合3 ... 是把每个元素（集合）加入到新集合，最后返回新集合 List<List<String>> 最终会处理简化为 List<String>}
    val flatMap = listOf(1, 2, 3, 4, 5).flatMap {
        listOf("参数$it", it.takeIf { it % 2 == 0 } ?: 0)
    }
    println(flatMap)

    //TODO filter操作
    val filter = listOf(1, 2, 3, 4, 5).filter {
        it % 2 == 0
    }
    println(filter)

    //TODO zip合并操作符
    val listOf1 = listOf(1, 2, 3)
    val listOf2 = listOf(4, 5, 6)
    val zip: List<Pair<Int, Int>> = listOf1.zip(listOf2)
    println(zip)
    println(zip.toMap())
    println(zip.toSet())
    println(zip.toMutableSet())
    println(zip.toList())
    println(zip.toMutableList())

    //TODO 和Java的互操作性和可控性
    val extendFunLearn = ExtendFunLearn()
    //可以看到此处的 info 类型是 String! 这是和 Java 交互返回的固定类型
    //以防为空 每次和 Java 交互的数据类型都要加上空 ?
    val info1 = extendFunLearn.info
    val info2: String? = extendFunLearn.info
    //println(info1.length) 不加 ? 的结果就是可能会导致 空指针
    println(info2?.length ?: "你是null")
}