package com.example.myapplication.learn


/**
 * TODO open
 * KT所有类默认 final 修饰不能继承
 * 使用 TODO open 去掉 final
 */
open class Person(val name: String) {

    private fun showName() = "名字：$name"

    /**
     * KT所有类方法默认 final 修饰不能继承
     * 使用 TODO open 去掉 final
     */
    open fun printlnName() = println(showName())
}

class Student(private val subName: String) : Person(subName) {

    private fun showName() = "子类的名字：$subName"

    override fun printlnName() = println(showName())

    fun nameLength() = subName.length
}

/**
 * TODO KT中所有类都隐式继承Any类 类似于 java Object 类
 *
 * Any类在KT中只实现标准 看不到实现 在各个平台中做各自的实现
 */
class K : Any()

/**
 * TODO 对象声明 相当于是个单例
 */
object K1 {
    /*  object 对象类背后做了什么事

        public static final K1 INSTANCE;

        private K1(){}

        public final void show(){
            String var1 = "K1 show 。。。";
            ...
            System.out.println(var1)
        }

        //这个区域是 object 不同点:

        static {
            K1 var0 = new K1();
            INSTANCE = var0;
            String var1 = "K1 init 。。。";
            ...
            System.out.println(var1)
        }
     */
    init {
        println("K1 init 。。。")
    }

    fun show() = println("K1 show 。。。")
}

/**
 * TODO 对象表达式
 */
open class K2 {
    open fun add(info: String) = println("K2 add:$info")
    open fun del(info: String) = println("K2 del:$info")
}

class K2Child : K2() {
    override fun add(info: String) {
        //super.add(info)
        println("具名 add:$info")
    }

    override fun del(info: String) {
        //super.del(info)
        println("具名 del:$info")
    }
}

interface KtRunnable {
    fun run()
}

/**
 * TODO 伴生对象
 * 由来：在KT中没有static静态，伴生很大程度上和Java的这种static静态 差不多
 * 无论 K3 构建多少次，派生对象只加载一次
 */
class K3 {
    companion object {
        const val info = "zl"
        fun showInfo() = println("显示 info:$info")
    }
}

/**
 * TODO 内部类
 */
class Body(_bodyInfo: String) {

    val bodyInfo = _bodyInfo

    //心脏
    inner class Heart {
        fun run() = println("心脏访问身体信息：$bodyInfo") // TODO 这样会报错 KT里面内部类不能直接访问外部类 想要访问需要在内部类前加上inner
    }

    //肾脏
    inner class Kidney {
        fun run() = println("肾脏访问身体信息：$bodyInfo")
    }

    //手
    inner class Hand {

        // TODO 如果外部类是 inner 修饰 那么 该类必须加上 inner
        //左手
        inner class LeftHand {
            fun run() = println("左手访问身体信息：$bodyInfo")
        }

        //右手
        inner class RightHand {
            fun run() = println("右手访问身体信息：$bodyInfo")
        }
    }
}

/**
 * TODO 嵌套类
 */
class Outer {
    val info: String = "OK"

    class Nested {
        fun run() = println("Nested run")
    }
}

/**
 * TODO 数据类
 */
//普通类
//生成java后有 set get
class ResponseResultBean1(var msg: String, var code: Int, var data: String)

//数据类
//生成java后有 set get 构造函数 解构操作 copy toString hashCode equals
data class ResponseResultBean2(var msg: String, var code: Int, var data: String)

/**
 * TODO 数据类的使用条件
 */
//条件1：服务器请求回来的响应的JavaBean 基本都能使用数据类
//条件2：数据类至少必须有一个参数的主构造函数
//条件3：数据类必须有参数，val val 的参数
//条件4：数据类不能使用 abstract，open，sealed，inner 等等修饰（数据类只做数据载入的事情）
//条件5：需求： 比较，copy，toString，解构，等等 这些丰富的功能时，也可以使用数据类

/**
 * TODO copy函数
 * 可以看出 data 定义的类 内部的操作都是 只管主构造内的参数
 */
data class K4(var name: String, var age: Int) {
    var coreInfo: String = ""

    init {
        println("主构造被调用")
    }

    constructor(name: String) : this(name, 99) {
        println("次构造被调用")
        coreInfo = "增加非常核心内容信息"
    }

    //TODO data默认的 toStrong 是没有 coreInfo 的，只有主构造的数据
    override fun toString(): String {
        return "K4(name='$name', age=$age, coreInfo='$coreInfo')"
    }
}

/**
 * TODO 解构声明
 */
data class K5(var name: String, var age: Int, var sex: Char) {
    private val info: String = "info"

    //TODO info不在主构造内部 只能手动加上解构 ，且只能为 component4 ,123都已默认生成
    operator fun component4() = info
}

/**
 * TODO 运算符重载
 */
data class K6(var num1: Int, var num2: Int) {
    operator fun plus(k61: K6): K6 {
        return apply {
            num1 += k61.num2
            num2 += k61.num2
        }
    }
}

fun classMoreLearn() {

    //TODO 多态
    val person: Person = Student("zl");
    person.printlnName()

    //TODO 类型转换
    //person.nameLength() //和java instanceOf 一样 没做判断在这里是无法调用子类的方法的
    if (person is Student) {//使用 TODO is 判断是否为子类 相当于 java instanceOf
        person.nameLength()
    }
    (person as Student).nameLength() // 强制使用 TODO as 转换 相当于 java (类型)

    //TODO 智能类型转换
    val person1: Person = Student("zl");
    (person1 as Student).nameLength()
    person1.nameLength()//上面转换过一次 就不用再次转换了

    //TODO 对象声明 相当于是个单例
    println(K1) //com.example.myapplication.learn.K1@2515911
    println(K1) //com.example.myapplication.learn.K1@2515911
    K1.show()//背后代码是 K1.INSTANCE.show()

    //TODO 对象表达式
    //1 匿名方式
    val k2 = object : K2() {
        override fun add(info: String) {
            //super.add(info)
            println("匿名 add:$info")
        }

        override fun del(info: String) {
            //super.del(info)
            println("匿名 del:$info")
        }
    }
    k2.add("zl")
    k2.del("zl")

    //2 具名方式
    val k3 = K2Child()
    k3.add("zl")
    k3.del("zl")

    //3 对java的接口 用 对象表达式
    val runnable1 = object : Runnable {
        override fun run() {
            println("Java Runnable object run Method")
        }
    }
    runnable1.run()

    //4 对java的接口 用 Java最简洁的方式
    val runnable2 = Runnable {
        println("run Method")
    }
    runnable2.run()

    //5 对kt的接口 只能用 对象表达式
    val runnable3 = object : KtRunnable {
        override fun run() {
            println("Kt Runnable object run Method")
        }
    }
    runnable3.run()

    //TODO 伴生对象
    println(K3.info)
    K3.showInfo()

    //TODO 内部类 由于使用了 inner 两个类有关联 所以内部类必须依赖外部类的实例
    Body(_bodyInfo = "健康").Hand().LeftHand()

    //TODO 嵌套类 由于未使用了 inner 两个类没关联 所以可直接通过外部类点内部类的形式
    Outer.Nested().run()

    //TODO 数据类
    println(ResponseResultBean1("loginSuccess", 200, "登录成功后的数据"))
    println(ResponseResultBean2("loginSuccess", 200, "登录成功后的数据"))

    //TODO == 值得比较 相当于java的equals ，=== 引用 对象 比较
    println(
        ResponseResultBean1("loginSuccess", 200, "登录成功后的数据") ==
                ResponseResultBean1("loginSuccess", 200, "登录成功后的数据")
    )//Any父类的 equals 实现的是 引用的比较

    println(
        ResponseResultBean2("loginSuccess", 200, "登录成功后的数据") ==
                ResponseResultBean2("loginSuccess", 200, "登录成功后的数据")
    )//Any父类的 equals 实现被重写 ，equals 会调用 子类的 equals函数

    //TODO copy函数
    val k4 = K4("zl")
    println(k4)
    val k4Copy = k4.copy()//只会 copy 主构造的数据
    println(k4Copy)

    //TODO 解构
    val (name, age, sex, info) = K5(name = "zl", age = 27, sex = 'M')
    println("name:$name, age:$age, sex:$sex, info:$info")

    //TODO 运算符重载
    //C++ +运算符重载就行了 -运算符重载就行了
    //KT  plus代表+运算符重载
    println(K6(1, 1) + K6(2, 2))
}