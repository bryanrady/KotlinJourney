package com.bryanrady.lib_network.errorhandler

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/1 22:12
 *    desc   :
 */
class ErrorHandler<T> : Function<Throwable, Observable<T>> {

    @Throws(Exception::class)
    override fun apply(throwable: Throwable): Observable<T> {
        throwable.printStackTrace()
        return Observable.error(ExceptionHandle.handleException(throwable))
    }
}

class ErrorHandlerFlowable<T> : Function<Throwable, Flowable<T>> {

    @Throws(Exception::class)
    override fun apply(throwable: Throwable): Flowable<T> {
        throwable.printStackTrace()
        return Flowable.error(ExceptionHandle.handleException(throwable))
    }
}