package com.bryanrady.lib_network.errorhandler

import io.reactivex.Flowable
import io.reactivex.functions.Function

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/4 22:51
 *    desc   :
 */
class ErrorHandlerFlowable<T> : Function<Throwable, Flowable<T>> {

    @Throws(Exception::class)
    override fun apply(throwable: Throwable): Flowable<T> {
        throwable.printStackTrace()
        return Flowable.error(ExceptionHandle.handleException(throwable))
    }
}