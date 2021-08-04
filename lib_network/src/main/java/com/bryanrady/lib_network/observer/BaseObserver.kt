package com.bryanrady.lib_network.observer

import com.bryanrady.lib_network.bean.BaseResponse
import com.bryanrady.lib_network.bean.BaseResponse.ResultBean
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/4 23:02
 *    desc   :
 */
abstract class BaseObserver<T> : Observer<BaseResponse<T>> {

    private var mDisposable: Disposable? = null

    override fun onSubscribe(d: Disposable) {
        mDisposable = d
    }

    override fun onNext(t: BaseResponse<T>) {
        val result = t.getResult()
        if (result != null) {
            val data = result.getData()
            onSuccess(data!!)
            return
        }
        throw NullPointerException("service return data is error")
    }

    override fun onError(e: Throwable) {
        unSubscribe()
    }

    override fun onComplete() {
        unSubscribe()
    }

    /**
     * 解除RxJava订阅
     */
    private fun unSubscribe() {
        if (mDisposable != null && !mDisposable!!.isDisposed) {
            mDisposable!!.dispose()
        }
    }

    protected abstract fun onSuccess(data: T)

    protected abstract fun onFail(code: Int, errMsg: String?)

}