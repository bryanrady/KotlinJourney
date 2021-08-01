package com.bryanrady.kotlinjourney.utils

import java.lang.reflect.ParameterizedType

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/1 18:35
 *    desc   :
 */
object ReflectUtils {

    fun <T> getT(o: Any?, index: Int): T {
        val genericSuperclass: ParameterizedType = o!!::class.java.genericSuperclass as ParameterizedType
        val clazz = genericSuperclass.actualTypeArguments[index] as Class<*>
        return clazz.newInstance() as T
    }

}