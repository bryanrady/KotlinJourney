package com.bryanrady.lib_kt_base.day1

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/5 22:00
 *    desc   :
 */

data class Person(val name: String, var age: Int? = 0)

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

    val maxAgePerson = personList.maxBy { it:Person ->
            it.age ?: 0
    }
    println("max age is ${maxAgePerson!!.name}")

    val rectangle = Rectangle(50,60)
    println(rectangle.isSquare)

}

