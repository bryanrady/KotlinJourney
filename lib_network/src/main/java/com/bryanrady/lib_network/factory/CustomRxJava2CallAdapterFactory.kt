package com.bryanrady.lib_network.factory

import com.bryanrady.lib_network.errorhandler.ErrorHandlerFlowable
import com.bryanrady.lib_network.errorhandler.ErrorHandlerObservable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/1 20:32
 *    desc   :
 */
class CustomRxJava2CallAdapterFactory private constructor(
    private val mFunction: Function<in Any, out Any>? = null,
    private val mFactory: CallAdapter.Factory = RxJava2CallAdapterFactory.create()
) : CallAdapter.Factory() {

    companion object {

        fun create(function: Function<in Any, out Any>?): CustomRxJava2CallAdapterFactory {
            return CustomRxJava2CallAdapterFactory(function)
        }

    }

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val callAdapter = mFactory.get(returnType, annotations, retrofit)
        val rawType = getRawType(returnType)
        if (callAdapter != null) {
            if (rawType == Observable::class.java) {
                return CallAdapterFactory.create(
                    callAdapter as CallAdapter<Observable<*>, Observable<*>>,
                    Function<Observable<*>, Observable<*>> { t ->
                        val observable = t.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                        if (mFunction != null) {
                            observable.map(mFunction).onErrorResumeNext(ErrorHandlerObservable())
                        } else {
                            observable.onErrorResumeNext(ErrorHandlerObservable())
                        }
                    });
            } else if (rawType == Flowable::class.java) {
                return CallAdapterFactory.create(
                    callAdapter as CallAdapter<Flowable<*>, Flowable<*>>,
                    Function<Flowable<*>, Flowable<*>> { t ->
                        val f = t.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                        if (mFunction != null) {
                            f.map(mFunction).onErrorResumeNext(ErrorHandlerFlowable())
                        } else {
                            f.onErrorResumeNext(ErrorHandlerFlowable())
                        }
                    });
            }
        }
        return callAdapter
    }
}