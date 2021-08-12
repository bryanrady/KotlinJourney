package com.bryanrady.lib_kt_base.base

import android.util.Log

//() -> Unit    https://www.cnblogs.com/Jetictors/p/8647888.html
// 表示无参数无返回值的Lambda表达式类型

//(T) -> Unit
// 表示接收一个T类型参数，无返回值的Lambda表达式类型

//(T) -> R
// 表示接收一个T类型参数，返回一个R类型值的Lambda表达式类型

//(T, P) -> R
// 表示接收一个T类型和P类型的参数，返回一个R类型值的Lambda表达式类型

//(T, (P,Q) -> S) -> R
// 表示接收一个T类型参数和一个接收P、Q类型两个参数并返回一个S类型的值的Lambda表达式类型参数，返回一个R类型值的Lambda表达式类型


//1. 无参数的情况 ：
//val/var 变量名 = { 操作的代码 }
// 源代码
fun test1(){
    println("无参数")
}
// lambda代码
val test11 = {
    println("无参数")
}
// 调用
//test11()  => 结果为：无参数

//2. 有参数的情况
//val/var 变量名: (参数类型，参数类型，...) -> 返回值类型 = {参数1，参数2，... -> 操作参数的代码 }
//等价于
//val/var 变量名 = { 参数1： 类型，参数2: 类型, ... -> 操作参数的代码 }  这种写法：即表达式的返回值类型会根据操作的代码自推导出来。
fun test2(a : Int , b : Int) : Int{
    return a + b
}

// lambda
val test21 : (Int , Int) -> Int = {a , b ->
    a + b
}
// 等价于  返回值类型自动推导
val test22 = {a : Int , b : Int ->
    a + b
}
// 调用
//test21(3,5) => 结果为：8

//3. lambda表达式作为函数中的参数的时候，这里举一个例子：
//fun test(a: Int, 参数名: (参数1： 类型，参数2: 类型, ... ) -> 表达式返回类型){
//    ...
//}
// 源代码
fun test3(a: Int , b: Int) : Int{
    return a + b
}

fun sum(num1: Int , num2: Int) : Int{
    return num1 + num2
}

// 调用
//test3(10,sum(3,5)) // 结果为：18

// lambda
fun test31(a: Int , action : (num1: Int , num2: Int) -> Int) : Int{
    return a + action.invoke(3,5)
}

// 调用
//test31(10,{ num1: Int, num2: Int ->  num1 + num2 })  // 结果为：18


fun test4(num: Int, bool: (Int) -> Boolean): Int {
    return if (bool(num)) {
        num
    } else {
        0
    }
}

//kotlin的闭包 函数包含函数
fun closure1(num: Int): () -> Int {
    var curr = 3
    return fun(): Int{
        println("num==$num")
        println("curr==$curr")
        return num + curr++
    }
}


fun main(args: Array<String>) {

    println(test4(4) {
        it < 5
    })

    val clo1 = closure1(3)
    println(clo1())
    println(clo1())
    println(clo1())

//    num==3
//    curr==3
//    6
//    num==3
//    curr==4
//    7
//    num==3
//    curr==5
//    8




}