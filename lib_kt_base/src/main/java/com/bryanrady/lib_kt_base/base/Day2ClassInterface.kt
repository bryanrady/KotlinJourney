package com.bryanrady.lib_kt_base.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import java.lang.IllegalArgumentException
import com.bryanrady.lib_kt_base.base.lastChar as lastC

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/7 13:42
 *    desc   :
 */
interface IProduct {

    fun getName(): String

    /**
     * 和java接口不同的是 还有方法体
     */
    fun test() {
        println("IProduct 接口的 test 方法")
    }

}

interface IProduct2 {

    fun getName2(): String

    /**
     * 和java接口不同的是 kotlin的接口可以带方法体
     */
    fun test() {
        println("IProduct2 接口的 test 方法")
    }

}

class ProductImpl : IProduct {

    override fun getName(): String {
        return "1号产品"
    }

    /**
     * 接口中有默认实现的test()方法，所以这个可以不用重写
     */
    override fun test() {
        super.test()
    }

}

//实现的多接口有 相同的方法
class ProductImpl2 : IProduct, IProduct2 {

    override fun getName(): String {
        return "1号产品"
    }

    override fun getName2(): String {
        return "2号产品"
    }

    /**
     * 遇到两个接口有重名的 必须重新自己的
     * 可以通过<>选择实现哪个接口的方法
     */
    override fun test() {
        super<IProduct>.test()
    }
}

/**
 * 1. open/final/abstract 修饰符
 *      Java中的类和方法默认都是open的，也就是可以被继承和重写的
 *      Kotlin中的类和方法默认都是final的，不可被继承和重写
 *
 *      原则上，所有没有特别需要在子类中被重写的类和方法都应该是final的
 *
 *      final       不能被继承和重写       类中的成员和方法默认都是final的
 *      open        可以被继承和重写       如果想要一个类可以被继承或者方法被重写 需要添加open
 *      abstract    必须被重写            只能在抽象类中;抽象成员不能有实现
 *      override    重写父类或接口的成员    默认是open的，可以添加final
 *
 *   可见性修饰符
 *       public(默认)   所有地方可见
 *       internal      模块内可见
 *       protected     只有子类可见，同一个包中不可见，同一个包中不可见和java有点区别
 *       private       类中可见
 *
 *
 */
class RealButton {
    private var name: String? = null
    var name1: String? = null

    private fun test(){

    }

    protected fun test2(){

    }
}

//自己的扩展函数不能访问自己的private和protected成员和方法
fun RealButton.test1(){
    //不能访问
    //name
    //test()
    //test2()

    //可以访问
    //name1
}

interface Expr

class Num(val value: Int) : Expr
class Sum(val a: Expr, val b: Expr) : Expr
class Multi(val a: Expr, val b: Expr) : Expr

fun numOperator(e: Expr) : Int {
    return when (e) {
        is Num -> e.value
        is Sum -> numOperator(e.a) + numOperator(e.b)
        is Multi -> numOperator(e.a) * numOperator(e.b)
        else -> throw IllegalArgumentException("参数错误，未知的表达式")
    }
}

//Expr1使用密封sealed，when表达式涵盖了所有的可能 所以可以不再需要else分支
fun numOperator1(e: Expr1) : Int {
    return when (e) {
        is Expr1.Num -> e.value
        is Expr1.Sum -> numOperator1(e.a) + numOperator1(e.b)
        is Expr1.Multi -> numOperator1(e.a) * numOperator1(e.b)
        //    is Expr1.Subtract -> numOperator1(e.a) - numOperator1(e.b)
    }
}

//sealed 密封，只能作用于class
//sealed 对可能创建的子类作出严格限制，所有的子类必须嵌套在父类中
sealed class Expr1 {
    class Num(val value: Int) : Expr1()
    class Sum(val a: Expr1, val b: Expr1) : Expr1()
    class Multi(val a: Expr1, val b: Expr1) : Expr1()
    //如果添加了一个子类，numOperator1方法里面的when表达是就会报错，提醒我们潜在的bug
    //  class Subtract(val a: Expr1, val b: Expr1) : Expr1()
}

/**
 *  Kotlin的构造函数分为主构造器（primary constructor）和次级构造器（secondary constructor）。
 */
/**
 * 主构造函数    Primary Constructor
 *
 *  写法： class 类名 constructor(形参1, 形参2, 形参3){}
 *      关键字constructor：
 *          在Java中，构造方法名须和类名相同；而在Kotlin中，是通过constructor关键字来标明的，
 *          且对于Primary Constructor而言，它的位置是在类的首部（class header）而不是在类体中（class body）。
 *      关键字init：
 *          init{}它被称作是初始化代码块（Initializer Block），它的作用是为了Primary Constructor服务的，
 *          由于Primary Constructor是放置在类的首部，是不能包含任何初始化执行语句的，这是语法规定的，
 *          那么这个时候就有了init的用武之地，我们可以把初始化执行语句放置在此处，为属性进行赋值。
 */
class Animal01 constructor(name: String) {
    val name: String
    init {
        this.name = name
    }
}

//演变1：初始化语句不是必须放置在init块中，我们可以在定义属性时直接将主构造器中的形参赋值给它
class Animal011 constructor(name: String) {
    val name: String = name
}

//演变2：这种在构造器中声明形参，然后在属性定义进行赋值，这个过程实际上很繁琐，有没有更加简便的方法呢？
//当然有，我们可以直接在Primary Constructor中直接定义类的属性
class Animal012 constructor(val name: String) {
}

//演变3：当constructor关键字没有注解和可见性修饰符作用于它时，constructor关键字可以省略
//（当然，如果有这些修饰时，是不能够省略的，并且constructor关键字位于修饰符后面）。
class Animal013(val name: String) {
}

/**
 * 次构造函数    Secondary Constructor 没有主构造函数
 *  和Primary Constructor相比，很明显的一点，Secondary Constructor是定义在类体中。
 *  第二，Secondary Constructor可以有多个，而Primary Constructor只会有一个。
 */
open class Animal02 {
    val name: String
    var age: Int = 0

    constructor(name: String) {
        this.name = name
    }

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}

//加上@JvmOverloads注解可以只写一个构造函数了
open class Animal021 {
    val name: String
    var age: Int = 0

    @JvmOverloads
    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}

/**
 * 同时声明主构造函数和次构造函数
 */
open class Animal03(open val name: String) {

    var age: Int = 0

    constructor(name: String, age: Int) : this(name) {
        this.age = age
    }
}

//继承 而且这个参数name和age都是父类的
class Dog1(name: String, age: Int, var type: String) : Animal03(name, age) {

}

//继承 我们也可以用自己写的name 只需要重写属性即可，并且父类的属性要改成open的
class Dog2(override val name: String, age: Int, var type: String) : Animal03(name, age) {

}

//如果父类没有主构造函数 也可以不用写(), 然后必须要在类体中重写父类的构造函数
class Dog3 : Animal02 {

    constructor(name: String) : super(name) {
    }

    constructor(name: String, age: Int) : super(name, age) {
    }
}

class CustomView : View {

    constructor(context: Context) : this(context, null) {
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
    }

}

/**
 * 接口中声明属性
 */
interface IUser {
    //在接口中声明属性，实现类必须重写这个字段
    val username: String
}

class QqUser(override val username: String) : IUser {
}

class WechatUser(username: String) : IUser {
    override val username: String = username
}

fun main(args: Array<String>) {

    //可以直接调用任何类的顶层属性和顶层函数
    val i = TEST_CODE
    qujian('u')

    //导入day1包中的 lastChar 扩展函数 并且通过as关键字 自己改名为 lastC，这样就不能调用lastChar，因为已经改名了
    // import com.bryanrady.lib_kt_base.day1.lastChar as lastC
    "Android".lastC()

    val product = ProductImpl()
    println(product.getName())
    println(product.test())

    val product2 = ProductImpl2()
    println(product2.getName2())
    println(product2.test())

    println(numOperator(Sum(Num(4), Num(5))))
    println(numOperator(Multi(Num(4), Num(5))))

    println(Animal01("小兔子").name)
    println(Animal011("小羊").name)
    println(Animal012("小鸡").name)
    println(Animal013("小猪").name)

    println(Animal02("小猴子").name)
    println(Animal02("小猴子2", 3).name)

    println(Animal03("小猫咪1").name)
    println(Animal03("小猫咪2", 6).name)

    println(Dog1("小狗狗1",6,"哈士奇").name)
    println(Dog1("小狗狗2",6,"金毛").type)

    println(QqUser("qq昵称").username)
    println(WechatUser("微信昵称").username)
}