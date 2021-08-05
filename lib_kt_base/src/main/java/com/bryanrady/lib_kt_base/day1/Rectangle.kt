package com.bryanrady.lib_kt_base.day1

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/5 22:39
 *    desc   : 自定义类的访问器
 */

class Rectangle(private val width: Int, private val height: Int) {

    //是不是正方形字段
    val isSquare: Boolean
        get() {
            return width == height
        }



}