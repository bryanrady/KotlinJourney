package com.bryanrady.lib_network.factory

import io.reactivex.functions.Function
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/1 20:24
 *    desc   :
 */
class CallAdapterFactory<R, W>(adapter: CallAdapter<R, W>, function: Function<W, W>) : CallAdapter<R, W> {

    private var mAdapter: CallAdapter<R, W>? = adapter
    private var mFunction: Function<W, W>? = function

    companion object {

        fun <R, W> create(adapter: CallAdapter<R, W>, function: Function<W, W>): CallAdapterFactory<R, W> {
            return CallAdapterFactory(adapter, function)
        }

    }

    override fun adapt(call: Call<R>): W {
        val adapt = mAdapter!!.adapt(call)
        return mFunction!!.apply(adapt)
    }

    override fun responseType(): Type = mAdapter!!.responseType()
}