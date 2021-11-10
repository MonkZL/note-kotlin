package com.example.myapplication.learn


//TODO 定义类和关键字
class KtBase {
    var name = "zl"
    /*  var name = "zl" //转换成java是下面这样的

        @NotNull
        private String name = "zl";

        public void setName(@NotNull String name){
            this.name = name;
        }

        @NotNull
        public String getName(){
            return this.name;
        }

     */

    var age = 18
        //设置年龄的范围
        set(value) {
            field = value.takeIf { it in 0..100 } ?: field
        }

    override fun toString(): String {
        return "KtBase(name='$name', age=$age)"
    }

}

//TODO 计算属性和防范竞态条件
class KtBase1 {
    val number: Int = 0

    /*  val number = 0 //转换成java是下面这样的 val不可修改所有没有set函数

        @NotNull
        private int number = 0;

        @NotNull
        public int getNumber(){
            return this.number;
        }

     */

    val number1: Int
        get() = (1..1000).shuffled().first()
    /*  val number1: Int //转换成java是下面这样的

        //val不可修改所有没有set函数
        //也没有 private int number1 因为number1对应field都没用到
        @NotNull
        public int getNumber1(){
            return (1..1000).shuffled().first();//返回java的随机值
        }

     */

    var info: String? = null

    //防范竞态条件 当你调用成员，这个成员，可能为null，可能为空值，就必须采用 防范竞态条件，这个是KT编程的规范
    fun getShowInfo(): String {
        return info?.let {
            return if (it.isEmpty()) {
                "空值，请检查代码"
            } else {
                "info的结果是：$it"
            }
        } ?: "null，请检查代码"
    }
}

//TODO 主构造函数 _name零时的参数 无法直接调用
class KtBase2(_name: String, _age: Int, _sex: Char, _info: String) {

    var name = _name
        private set //私有化设置
    var age = _age
    var sex = _sex
    var info = _info

}

//TODO 主构造函数 一步到位
class KtBase3(var name: String, var age: Int, var sex: Char, var info: String) {
}

//TODO 次构造函数
class KtBase4(_name: String = "yz") {

    var name = _name
    var sex: Char = '\u0000'
    var age: Int = 0
    var info: String = ""

    //次构造函数，必须调用主构造函数，否则不通过
    constructor(_name: String, _sex: Char) : this(_name) {
        this.sex = _sex
    }

    //次构造函数，必须调用主构造函数，否则不通过
    constructor(_name: String, _sex: Char, _age: Int) : this(_name, _sex) {
        this.age = _age
    }

    //次构造函数，必须调用主构造函数，否则不通过
    constructor(_name: String, _sex: Char, _age: Int, _info: String) : this(_name, _sex, _age) {
        this.info = _info
    }

    override fun toString(): String {
        return "KtBase4(name='$name', sex=$sex, age=$age, info='$info')"
    }


}

//TODO 构造函数中的默认参数
class KtBase5(_name: String = "zl") {

    var name = _name
    var sex: Char = '\u0000'
    var age: Int = 0
    var info: String = ""

    //次构造函数，必须调用主构造函数，否则不通过
    constructor(_name: String = "zl", _sex: Char = 'M') : this(_name) {
        this.sex = _sex
    }

    //次构造函数，必须调用主构造函数，否则不通过
    constructor(_name: String = "zl", _sex: Char = 'M', _age: Int = 27) : this(_name, _sex) {
        this.age = _age
    }

    //次构造函数，必须调用主构造函数，否则不通过
    constructor(
        _name: String = "zl",
        _sex: Char = 'M',
        _age: Int = 27,
        _info: String = "programing"
    ) : this(_name, _sex, _age) {
        this.info = _info
    }

    override fun toString(): String {
        return "KtBase5(name='$name', sex=$sex, age=$age, info='$info')"
    }
}

//TODO 初始化代码块
class KtBase6(_name: String, _age: Int, _sex: Char) {

    /*
       初始化代码块 init代码块
       _name _age _sex 零时类型只有init代码块才能使用
     */
    init {
        require(_name.isNotBlank()) { "名字输入错误" }
        require(_age > 0) { "年龄输入错误" }
        require(_sex.let { it == 'M' || it == 'W' }) { "性别输入错误" }
        println("_name : $_name, _age : $_age, _sex : $_sex")
    }

}

//TODO 构造初始化顺序
//第1步生成 val sex
class KtBase7(_name: String, val _sex: Char) {

    //第2步生成 val name
    //其实2 3 是并行的 只是看顺序而已
    val name = _name

    //第3步
    init {
        println("init代码块打印:_name:$_name，name:$name")
    }

    //这个就是在第3步之后执行
    val testValue = ""

    constructor(name: String, sex: Char, age: Int) : this(name, sex) {
        //第4步
        println("次构造 3个参数的，打印:name:$name，sex:$sex，age:$age")
    }

}

// TODO 延迟初始化lateinit
class KtBase8 {
    //lateinit val AAA 必须使用var修饰
    lateinit var responseResultInfo: String

    fun request() {
        responseResultInfo = "服务器加载成功"
    }

    override fun toString(): String {
        return if (::responseResultInfo.isInitialized) {
            "KtBase8(responseResultInfo='$responseResultInfo')"
        } else {
            "未初始化"
        }
    }

}

// TODO 惰性初始化by lazy
class KtBase9 {
    //饿汉式
    val databaseData1: String = readSQLServerDatabaseAction()
    //懒汉式
    val databaseData2: String by lazy(::readSQLServerDatabaseAction)
    private fun readSQLServerDatabaseAction(): String {
        Thread.sleep(1000)
        println("加载数据中。。。。。。。")
        println("加载数据中。。。。。。。")
        println("加载数据中。。。。。。。")
        println("加载数据中。。。。。。。")
        println("加载数据中。。。。。。。")
        println("加载数据中。。。。。。。")
        return "database data load success ok."
    }
}

fun classLearn() {
    /**
     * TODO 定义类和关键字
     */
    val ktBase = KtBase()
    println(ktBase)
    ktBase.name = "yz"//此处其实是调用了 ktBase.setName("yz")
    println(ktBase.name)//此处其实是调用了 ktBase.getName()
    ktBase.age = 101
    println(ktBase)
    ktBase.age = 99
    println(ktBase)

    /**
     * TODO 计算属性和防范竞态条件
     */
    println(KtBase1().number)
    //KtBase1().number = 1//报错 val只读
    println(KtBase1().number1)
    println(KtBase1().number1)
    println(KtBase1().getShowInfo())

    /**
     * TODO 主构造函数
     */
    val ktBase2 = KtBase2(_name = "zl", _age = 27, _sex = 'M', _info = "programing")
    val ktBase3 = KtBase3(name = "zl", age = 27, sex = 'M', info = "programing")

    /**
     * TODO 次构造函数
     */
    println(KtBase4())
    println(KtBase4(_name = "zl"))
    println(KtBase4(_name = "zl", _sex = 'M'))
    println(KtBase4(_name = "zl", _sex = 'M', _age = 27))
    println(KtBase4(_name = "zl", _sex = 'M', _age = 27, _info = "programing"))

    /**
     * TODO 构造函数中的默认参数
     */
    println(KtBase5()) // 当所有构造函数的参数都有默认值得时候 默认调用主构造函数
    println(KtBase5(_name = "yz", _sex = 'W'))//就近匹配原则

    /**
     * TODO 初始化代码块
     */
    KtBase6(_name = "zl", _age = 27, _sex = 'M')

    /**
     * TODO 构造初始化顺序
     */
    KtBase7(name = "zl", sex = 'M', age = 27)

    /**
     * TODO 延迟初始化lateinit
     */
    println(KtBase8().toString())//报错 responseResultInfo使用前要加载
    val ktBase8 = KtBase8()
    ktBase8.request()
    println(ktBase8.toString())

    /**
     * TODO 惰性初始化by lazy
     */
    val ktBase9 = KtBase9()
    //饿汉式
    println(ktBase9.databaseData1)
    //懒汉式
    println(ktBase9.databaseData2)
}