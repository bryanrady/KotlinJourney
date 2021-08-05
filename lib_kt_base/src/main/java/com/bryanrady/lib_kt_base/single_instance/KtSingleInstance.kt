package com.bryanrady.lib_kt_base.single_instance

import kotlin.reflect.KType

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/5 22:50
 *    desc   :
 */

fun main(args: Array<String>) {

}

//饿汉式单例模式
object KtSingleInstance {

}

//懒汉式单例模式
class KtSingleInstance2 private constructor(){

    companion object {

        private val instance: KtSingleInstance2? = null

        fun get() {
            if (instance == null) {
                KtSingleInstance2()
            }
        }
    }
}

//线程安全懒汉式单例模式
class KtSingleInstance3 private constructor() {

    companion object {

        private val instance: KtSingleInstance3? = null

        @Synchronized
        fun get() {
            if (instance == null) {
                KtSingleInstance3()
            }
        }

    }

}

//双重检查模式单例
class KtSingleInstance4 private constructor() {

    companion object {

        private val instance: KtSingleInstance4 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            KtSingleInstance4()
        }

    }

}

//内部类单例
class KtSingleInstance5 private constructor() {

    companion object {
        val instance = SingleHolder.holder
    }

    object SingleHolder {
        val holder = KtSingleInstance5()
    }
}