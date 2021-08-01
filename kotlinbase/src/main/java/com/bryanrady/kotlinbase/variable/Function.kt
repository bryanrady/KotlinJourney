package com.bryanrady.kotlinbase.variable

/**
 * 函数
 */
class Function{

    /**
     * 函数的声明方式：
     *
     *      fun + funName(): 函数返回类型
     *
     *
     */

    /**
     * java中函数没有返回值的函数默认关键字是void，Kotlin中默认的是Unit，并且Unit可以省略
     * 函数的返回类型写在函数后面
     */
    fun voidFun(): Unit{

    }

    /**
     * 函数的返回类型为String
     */
    fun returnFun(): String{
        return "bryanrady"
    }

    //函数参数也可以有可空的控制，根据前面说的空安全设计，在传递时需要注意：

    var name:String = "wangqingbin"
    var couldNullName: String? = null

    fun testParam(){
        //不可空类型变量 传递给函数的 不可空参数
        voidFunParamNotNull(name)

        //不可空类型变量 传递给函数的 可空参数
        voidFunParamCouldNull(name)

        //可空类型变量  传递给函数的 不可空参数
    //    voidFunParamNotNull(couldNullName)
        //编译器会报错，提示你将一个可空类型的变量 传递给了一个 不可空类型的参数，这也是 Kotlin 的空安全设计

        voidFunParamCouldNull(couldNullName)
    }


    /**
     * 带参数的函数  不可空类型参数
     */
    fun voidFunParamNotNull(param: String){
        println("函数参数为 $param")
    }

    /**
     * 带参数的函数，加一个 ？ 表示 param 这个参数可以为空   可空类型参数
     */
    fun voidFunParamCouldNull(param: String?){

    }



    fun returnName():String = "wangqingbin"



    fun getUserName(): String{
        return User().name
    }


}