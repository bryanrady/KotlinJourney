package com.bryanrady.lib_kt_base.base

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/5 22:00
 *    desc   :
 */

data class Person(var name: String? =null, var age: Int = 0)

data class Rectangle(private val width: Int, private val height: Int) {

    //是不是正方形字段
    val isSquare: Boolean
        get() {
            return width == height
        }
}

/**
 * 枚举
 */
enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE
}

/**
 * 带属性的枚举
 */
enum class Color2(val r: Int, val g: Int, val b: Int) {
    RED(255,0,0),
    GREEN(0,255,0),
    BLUE(0,0,255);

    fun rgb() = (r * 256 + g) * 256 + b
}

//顶层属性  在类中直接使用，不需要依托任何类
val TEST_CODE = 10000
//const 关键字相当于 java 的 static final 并且const 关键字只能声明在顶层或者object类中
const val TEST_CODE2 = 10000

//顶层函数
fun qujian(c: Char) = when(c) {
    in '0'..'9' -> "$c 是数字"
    in 'a'..'z' -> "$c 是小写字母"
    in 'A'..'Z' -> "$c 是大写字母"
    else -> "$c 其他字符"
}

//扩展函数 扩展函数不可重写
fun String.lastChar(): Char {
    return this[length - 1]
}

//中缀调用
infix fun String.toTask(other: String) = Pair(this, other)

//可变参数
fun testVararg(vararg a: Int){

}

fun main(args: Array<String>) {

    /**
     * Person 类前面加 data 修饰符，打印的时候就会打印出自定义toString的效果，不加修饰符会打印对象的内存地址
     * 加 data:  Person(name=wangqingbin, age=27)
     * 不加data： com.bryanrady.lib_kt_base.day1.Person@12a3a380
     */
    val person = Person("wangqingbin", 27)
    println(person)

    /**
     * 使用 listOf 生成一个集合，而且这个集合只能获取，不能添加  如果想添加 可以用 mutableListOf 多变集合 或者 arrayListOf
     * 这里集合中的第一个元素，只需要传一个参数就行，因为在类中已经把年龄初始化为0了
     */
    val personList = listOf<Person>(Person("lucy"), Person("jack", 18),
        Person(name = "Tom", age = 20))
    for (p in personList) {
        println(p)
    }

    personList.forEach {
        println(it)
    }

//    for(i in 1..100) {
//        println(i)
//    }

//    for(i in 100 downTo 1) {
//        println(i)
//    }

    for(i in 10 downTo 1 step 3) {
        println(i)
    }

    val maxAgePerson = personList.maxBy { it:Person ->
        it.age
    }
    println("max age is ${maxAgePerson!!.name}")

    val rectangle = Rectangle(50,60)
    println(rectangle.isSquare)

    println(qujian('B'))
    println(qujian('7'))
    println(qujian('f'))
    println(qujian('王'))

    //字符串也能in
    println("Kotlin" in "Java".."Android")
    println("Kotlin" in "Kot".."Kotling")
    //in 检查集合
    println("Kotlin" in listOf("Kot", "Kotling"))

    //扩展函数
    println("Java".lastChar())

    //利用Pair()初始换一个map
    val map = mapOf<Int, String>(Pair(1, "A"), Pair(2, "B"))
    //利用to函数初始换一个map to函数实际上返回一个Pair对象, Pair不是属于map结构内部的运算符
    val map1 = mapOf(1.to("A"), 2.to("B"))
    //利用to函数中缀调用初始换一个map 中缀调用(infix 关键字)  中缀调用 to 这个特殊函数 infix修饰的函数 表示我们可以中缀调用
    //public infix fun <A, B> A.to(that: B): Pair<A, B> = Pair(this, that)
    val map2 = mapOf(1 to "A", 2 to "B")
    //infix fun String.to(other: String) = Pair(this, other)
    val map3 = mapOf("1".toTask("A"), "2".toTask("B"))
    map3.forEach{
        println("key 为 ${it.key}, value 为 ${it.value}")
    }

    //可变参数
    testVararg(1,5,6,712)

}



