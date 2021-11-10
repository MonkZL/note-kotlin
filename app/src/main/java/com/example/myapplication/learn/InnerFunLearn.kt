package com.example.myapplication.learn

fun innerFunLearn() {
    /**
     * TODO apply 函数
     * 1.持有this
     * 2.返回本身
     */
    val listOf = mutableListOf(1, 2, 3, 4, 5)
    listOf.apply {
        add(6)
        println(this)
    }

    /**
     * TODO let 函数
     * 1.持有it
     * 2.返回指定值
     */
    val result = listOf(1, 2, 3, 4, 5).let {
        it[0] + it[1]
    }
    println(result)
    println(getMethod("123"))
    println(getMethod1("123"))
    println(getMethod2("123"))

    /**
     * TODO run 内置函数
     * 1.持有本身
     * 2.返回指定值
     */
    val str = "zl"
    val num = str.run {
        length
    }
    println(num)
    println(str.run(::isLong).run(::showText).run(::mapText))
    str.run(::isLong).run(::showText).run(::mapText).run(::println)

    /**
     * TODO with 内置函数
     * 和run函数一样 只不过使用方式不一样
     */
    with(str, ::println)

    /**
     * TODO also 内置函数
     * 1.持有it
     * 2.返回本身
     */
    str.also {
        println(it.length)
    }

    /**
     * TODO takeIf 内置函数
     * 返回值为true 返回本身
     * 返回值为false 返回null 所以在kotlin里面一般会结合空合并使用 ?:
     */
    val name = "Root1"
    println(name.takeIf { it == "Root" } ?: "newRoot")

    /**
     * TODO takeUnless 内置函数
     * 返回值为true 返回null 所以在kotlin里面一般会结合空合并使用 ?:
     * 返回值为false 返回本身
     */
    println(name.takeUnless { it == "Root" } ?: "newRoot")
}

private fun isLong(str: String) = str.length > 5
private fun showText(isLong: Boolean) = if (isLong) "合格" else "不合格"
private fun mapText(getShow: String) = "[$getShow]"
private fun getMethod(value: String?): String {
    return if (value == null) "不能为空" else "欢迎$value"
}

private fun getMethod1(value: String?) = if (value == null) "不能为空" else "欢迎$value"
private fun getMethod2(value: String?) = value?.let { "欢迎$it" } ?: "不能为空"
