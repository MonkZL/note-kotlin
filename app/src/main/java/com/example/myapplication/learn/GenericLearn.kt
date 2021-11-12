package com.example.myapplication.learn

/**
 * TODO 定义泛型类
 */
class G1<T>(private val obj: T) {
    fun show() = println("万能输出器:$obj")
    fun getObj() = obj.takeIf { it is Int }
}

data class Student1(val name: String, val age: Int)
data class Student2(val name: String, val age: Int)

/**
 * TODO 泛型函数
 */
fun <T> show(value: T? = null) = value.also {
    if (it == null) {
        println("大哥你返回的是null")
    } else {
        println(it)
    }
}

/**
 * TODO 泛型变换
 */
inline fun <I, O> map(value: I, isMap: Boolean = true, mapAction: (I) -> O) =
    if (isMap) mapAction(value) else null

/**
 * TODO 泛型类型约束
 */
fun <T : Any> getObj(value: T) = value

/**
 * TODO vararg关键字(动态参数)
 */
class G2<T>(vararg objects: T, var isMap: Boolean = true) {
    // out 开启 T 只读模式
    private val objectsLocal: Array<out T> = objects
    fun showObj(index: Int): T? = objectsLocal[index].takeIf { isMap }
    fun <O> mapObj(index: Int, mapAction: (T?) -> O): O? =
        mapAction(objectsLocal[index].takeIf { isMap })
}

/**
 * TODO 运算符重载
 */
class G3(private vararg var values: Int = intArrayOf(1, 2, 3), var id: Int = 1) {
    //全写
    //operator fun get(index: Int): Int {
    //return values[index]
    //}
    //简写
    operator fun get(index: Int) = values[index]
    operator fun compareTo(paramsG3: G3) = let {
        println("this.id : ${this.id} , nextG3.id : ${paramsG3.id}")
        this.values.size - paramsG3.values.size
    }

    operator fun rangeTo(paramsG3: G3) = let {
        println("this.id : ${this.id} , nextG3.id : ${paramsG3.id}")
        if (this > paramsG3) {
            arrayOf(this, paramsG3)
        } else {
            arrayOf(paramsG3, this)
        }
    }

    //这里的 this 是 in 后面那个对象
    operator fun contains(paramsG3: G3) = let {
        println("this.id : ${this.id} , nextG3.id : ${paramsG3.id}")
        paramsG3.values.none { nextIt ->
            nextIt !in it.values
        }
    }

    operator fun invoke(value: String) = println("value : $value")
}

/**
 * TODO 协变 out 只能读取，不能修改
 *      应用场景：当我们对整个类里面的泛型，只能读取，不能修改时，使用 out
 *      逆变 in  只能修改，不能让外界获取
 *      应用场景：当我们对整个类里面的泛型，只能修改，不能让外界获取时，使用 in
 */
interface Producer<out T> {
    fun make(): T
    //fun testFun(t: T) // 报错，使用 out 只能读取，不能修改
}

interface Consumer<in T> {
    fun buy(product: T)
    //fun testFun(): T // 报错，使用 in 只能修改，不能让外界获取
}

open class Product(val name: String)
class Paper(name: String = "眉山宣纸") : Product(name)

class PaperProducer : Producer<Paper> {
    override fun make(): Paper {
        val paper = Paper()
        println("生成${paper.name}成功")
        return paper
    }
}

class ConsumerImp : Consumer<Product> {
    override fun buy(product: Product) = println("消费${product.name}成功")
}

/**
 * TODO in out 的实践
 *  类型投影
 */
class InOutTest<T>(private var t: T) {
    fun getValue(): T = t
    fun setValue(newT: T) {
        t = newT
    }
}

fun testInOutTest1(inOutTest1: InOutTest<out Any>) {
    inOutTest1.getValue()
    //inOutTest1.setValue("yz") //TODO 加上了 out 编译器就不允许 修改泛型数据了
}

fun testInOutTest2(inOutTest2: InOutTest<in String>) {
    val value = inOutTest2.getValue() //TODO 加上了 in 此时value的类型就不再是 String 而是 Any?
}

/**
 * TODO in out 的实践
 *  星投影
 *  MutableList<*>和MutableList<Any?>进行一个对比：
 *      MutableList<Any?>：表示你可以往这个集合里面放入任意类型的元素
 *      MutableList<*>: 表示这个集合中只会放入某个特定类型的元素，但是我们此时此刻并不清楚这个特定类型是什么，类似于Java中的?，如Class<?>
 *      可以使用MutableList<Any?>()来创建一个对象，但是不能够使用MutableList<*>()来创建一个对象，后者只能出现在引用声明处
 */
fun starTest() {

    MutableList<Any?>(5) {
        if (it % 2 == 0) 1
        else "1"
    }
    //可以使用MutableList<Any?>()来创建一个对象，但是不能够使用MutableList<*>()来创建一个对象，后者只能出现在引用声明处
    //MutableList<*>(5) {
    //        if (it % 2 == 0) 1
    //        else "1"
    //    }
    val star: InOutTest<*> = InOutTest<String>("")
    val value = star.getValue() //TODO 此时value的类型不再是 String 而是Any?
    // star.setValue(newT) //TODO 此时 setValue 的参数只能是 Nothing 类型 由于不存在 Nothing 类型的实例 所以导致改方法不能使用

    //TODO 星投影 使用场景
    // 对类型参数并不care，像这里的printFirst()方法，我们仅仅想打印第一个元素，至于你到底是什么类型，是不关心的
    fun printFirst(list: List<*>) {
        if (list.isNotEmpty()) {
            println(list.first())
        }
    }
}

/**
 * TODO reified关键字
 */
inline fun <reified T> randomOrDefault(defaultValue: T): T {
    val objList = listOf(1, "zfc", true, 'c')
    val objRandom = objList.random()
    //最简单的判断方法
    //return objRandom.let {
    //        if (it is T) {
    //            it
    //        } else {
    //            defaultValue
    //        }
    //    }
    return objRandom.takeIf { objRandom is T } as T? ?: defaultValue
}



fun genericLearn() {

    //TODO 定义泛型类
    G1(Student1(name = "zl", age = 27)).show()
    G1(Student2(name = "yz", age = 26)).show()

    //TODO TK 中 null 也能调用 apply 方法
    G1("zl").getObj().apply {
        if (this == null) {
            println("大哥你返回的是null")
        } else {
            println(this)
        }
    }
    val g1: G1<Int>? = null
    //g1.getObj() //这时候是无法调用 getObj 方法的
    g1?.getObj() //加上空判断符就能调用

    G1(1).getObj().apply {
        if (this == null) {
            println("大哥你返回的是null")
        } else {
            println(this)
        }
    }

    //TODO 泛型函数
    show(1)
    show<Nothing>(null)

    //TODO 泛型变换
    println(map("1x2x3x4x5x6x7x8x9") {
        it.replace(Regex("[\\D]"), "").toIntOrNull() ?: 0
    })
    println(map("xx") {
        it.replace(Regex("[\\D]"), "").toIntOrNull() ?: 0
    })

    //TODO 泛型类型约束

    //TODO vararg关键字(动态参数)
    val g2 = G2(1, true, "3", 'D', isMap = true)
    println(g2.showObj(3))
    g2.mapObj(3) {
        println("mapObj : $it")
        "$it"
    }

    //TODO 运算符重载
    println("G3 : ${G3(1, 2, 3, 4, 5)[4]}")

    println(G3(1, 2, 3, id = 0) >= G3(1, 2, 3, 4, id = 1))

    println(G3(id = 0)..G3(id = 10))

    println(G3(values = intArrayOf(1, 2, 3), id = 0) in G3(values = intArrayOf(1, 2, 3, 4), id = 1))

    G3()("123")

    val a = G1(1)
    val b = G1(1)
    val c = G1(1)
    val d = G1(1)
    println(a in listOf(a, b, c, d))

    //TODO 默认情况下 泛型的 子类对象 是不可以赋值给泛型的 父类对象的
    //     out情况下 泛型的 子类对象 是可以赋值给泛型的   父类对象的
    //     相当于java中的 List<? extends Father> list = new ArrayList<Child>();
    val p1: Producer<Product> = PaperProducer()
    //TODO 默认情况下 泛型的 父类对象 是不可以赋值给泛型的 子类对象的
    //     in情况下  泛型的 父类对象 是可以赋值给泛型的   子类对象的
    //     相当于java中的 List<? super Child> list = new ArrayList<Father>();
    val c1: Consumer<Paper> = ConsumerImp()

    //TODO in out 的实践
    val inOutTest1: InOutTest<String> = InOutTest("zl")
    testInOutTest1(inOutTest1) // 此时 就需要将 testInOutTest1 的参数加上 out

    //TODO reified关键字
    println(randomOrDefault("xx"))
}