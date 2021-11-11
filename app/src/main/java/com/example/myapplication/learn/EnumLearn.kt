package com.example.myapplication.learn

/**
 * TODO 枚举
 */
enum class Week {
    Mon,
    Tus,
    Wen,
    Thu,
    Fri,
    Sat,
    Sun
}

/**
 * TODO 枚举特殊用法
 */
class LimbsInfo(private var limbsInfo: String, private var length: Int) {
    fun show() {
        println("${limbsInfo}的长度是$length")
    }

    fun update(limbsInfo: LimbsInfo) {
        this.limbsInfo = limbsInfo.limbsInfo
        length = limbsInfo.length
    }
}

//枚举的主构造必须和所有枚举值，保持一致的效果
enum class Limbs(val limbsInfo: LimbsInfo) {
    LEFT_HAND(LimbsInfo("左手", 88)),
    RIGHT_HAND(LimbsInfo("右手", 88)),
    LEFT_FOOT(LimbsInfo("左脚", 100)),
    RIGHT_FOOT(LimbsInfo("右脚", 100));
}

/**
 * TODO 代数数据类型
 */
enum class Exam {
    Fraction1,//分数差
    Fraction2,//分数及格
    Fraction3,//分数良好
    Fraction4;//分数优秀

    //需求：得到优秀的还在名字
    var studentName: String? = null
    //我们用枚举类，要做到此需求，非常麻烦
    //由此需求：引出 TODO 密封类
}

class Teacher(private val exam: Exam) {
    fun show() {
        println(
            when (exam) {
                Exam.Fraction1 -> "该学生分数差"
                Exam.Fraction2 -> "该学生分数及格"
                Exam.Fraction3 -> "该学生分数良好"
                Exam.Fraction4 -> "该学生分数优秀"
                //TODO 由于是代数数据类型 类型已经固定只有多少种 如果类型全部填入了就不用写else
                else -> "永远不会进入的else"
            }
        )
    }
}

/**
 * TODO 密封类
 */
sealed class Exams() {
    //object： Fraction1 Fraction2 Fraction3 和 Exams 保持一致所以不用class 且只会有一种情况所以就用单例就行了 直接写object
    object Fraction1 : Exams()//分数差
    object Fraction2 : Exams()//分数及格
    object Fraction3 : Exams()//分数良好

    //class：由于需要 studentName 和 Exams 不一样 且可能会有很多个 Fraction4 所以需要使用 class 做扩展
    class Fraction4(val studentName: String) : Exams()//分数优秀
}

class Teacher1(private val exams: Exams) {
    fun show() {
        println(
            when (exams) {
                is Exams.Fraction1 -> "该学生分数差"
                is Exams.Fraction2 -> "该学生分数及格"
                is Exams.Fraction3 -> "该学生分数良好"
                is Exams.Fraction4 -> "该学生分数优秀,名字是：${exams.studentName}，${exams.studentName.let { if (it == "zl") "性别：男" else "性别：女" }}"
            }
        )
    }
}

fun enumLearn() {
    //TODO 枚举
    println(Week.Mon)

    //TODO 枚举的值 等价于 枚举本身
    println(Week.Mon is Week)

    //TODO 枚举特殊用法
    Limbs.LEFT_FOOT.limbsInfo.show()
    Limbs.LEFT_FOOT.limbsInfo.update(LimbsInfo("左脚", 110))
    Limbs.LEFT_FOOT.limbsInfo.show()

    //TODO 代数数据类型
    Teacher(Exam.Fraction3).show()

    //TODO 密封类
    Teacher1(Exams.Fraction1).show()
    Teacher1(Exams.Fraction4("zl")).show()
    println(Exams.Fraction1 === Exams.Fraction1)//object修饰 是单例 肯定返回true
    println(Exams.Fraction4("zl") === Exams.Fraction4("zl"))//class修饰 每次创建都是新的对象 肯定是false
}