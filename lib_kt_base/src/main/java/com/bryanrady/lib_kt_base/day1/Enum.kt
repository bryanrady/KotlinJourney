package com.bryanrady.lib_kt_base.day1

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/5 22:44
 *    desc   :
 */

enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE
}

enum class Color2(val r: Int, val g: Int, val b: Int) {
    RED(255,0,0),
    GREEN(0,255,0),
    BLUE(0,0,255);

    fun rgb() = (r * 256 + g) * 256 + b
}