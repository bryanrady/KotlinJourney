package com.bryanrady.lib_network.errorhandler

import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/4 22:51
 *    desc   :
 */
class ErrorHandlerObservable<T> : Function<Throwable, Observable<T>> {

    @Throws(Exception::class)
    override fun apply(throwable: Throwable): Observable<T> {
        throwable.printStackTrace()
        return Observable.error(ExceptionHandle.handleException(throwable))
    }
}