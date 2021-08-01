package com.bryanrady.kotlinbase.variable


/**
 * 变量的声明与赋值
 */
class Property {

    /**
     * 变量的声明方式：
     *
     *     访问修饰符 + (标识符 const/abstract等等) + 声明符(var/val) + (变量:) + 变量类型 = 初始值
     *
     *     Kotlin 里面的类、属性、函数 默认就是public的，所以public关键字是多余的
     *
     */

    //var age: Int
    //Property must be initialized or abstract  属性必须初始化或者是抽象属性
    //Java中的属性是不可以声明为抽象类型的

    //标准写法
    var age: Int = 0

    //也可以这样写，编译器会进行类型推断
    var level = 0

    //abstract var name: String = "age"
    /**
     * Abstract property 'name' int non-abstract class 'KotlinVariable'
     * 抽象属性“name” 在非抽象类 “KotlinVariable” 中
     *  (1)如果一个类中包含了抽象属性，那么这个类也一定是抽象的，必须给类也加上 abstract 关键字
     *  (2)抽象属性不可以直接进行初始化
     *      Property with initializer cannot be abstract
     *      具有初始值设定项的属性不能是抽象的
     */


    //var name: String = null
    //kotlin 里面的属性是不可以初始化为null的，Kotlin 的空安全设计，简单来说就是通过 IDE 的提示来避免调用 null 对象，从而避免 NullPointerException。

    //我们可以通过 ？ 修饰符，设置这个变量为 可空变量。
    private var list: List<String>? = null    //可空类型的变量

    fun printListSize(){
//        println(list.size)
        //这里我们直接调用list.size编译器会报错，Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type View?
        //「可能为空」的变量，Kotlin 不允许用

//        if (list != null){
//            println(list.size)
//        }
        //这样写会报错，Smart cast to 'View' is impossible, because 'view' is a mutable property that could have been changed by this time
        //这个报错的意思是即使你检查了非空也不能保证下面调用的时候就是非空，因为在多线程情况下，其他线程可能把它再改成空的。

        //Kotlin里面的空安全检查是非常实用的，这样再也不用担心以后会写出 NullPointException 的代码了，哈哈

        //那么 Kotlin 里是这么解决这个问题的呢？

        println("list集合的大小为 ${list?.size}")
        //这个写法同样会对变量做一次非空确认之后再调用方法，这是 Kotlin 的写法，并且它可以做到线程安全，因此这种写法叫做「safe call」。
    }


//    很多人在上手的时候都被变量声明搞懵，原因就是 Kotlin 的空安全设计所导致的这些报错：
//
//    变量需要手动初始化，如果不初始化的话会报错；
//    变量默认非空，所以初始化赋值 null 的话报错，之后再次赋值为 null 也会报错；
//    变量用 ? 设置为可空的时候，使用的时候因为「可能为空」又报错。

    //lateinit 变量延迟初始化，有些变量没办法第一时间初始化，只能在特定时间内进行初始化，可以使用lateinit关键字

    lateinit var firstName: String

    fun intData(){
        firstName = "bryanrady"
    }

    //val 和 var
    //val 是 Kotlin 在 Java 的「变量」类型之外，又增加的一种变量类型：只读变量。它只能赋值一次，不能修改。而 var 是一种可读可写变量。

    //val 和 Java 中的 final 类似：不过又有些不一样

    var num = 12
    val str = "wangqingbin"

    fun reAssignment(){
        num = 13
        //str = "bryanray"  val变量不可以重新赋值
    }




}