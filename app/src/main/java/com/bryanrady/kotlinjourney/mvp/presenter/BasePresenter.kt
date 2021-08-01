package com.bryanrady.kotlinjourney.mvp.presenter

import com.bryanrady.kotlinjourney.mvp.view.IBaseView
import java.lang.ref.WeakReference

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/1 18:08
 *    desc   :
 */
open class BasePresenter<V : IBaseView> {

    private var mRefView: WeakReference<V>? = null

    fun attach(view: V) {
        this.mRefView = WeakReference<V>(view)
    }

    open fun getView(): V? {
        //这里mView后面添加？表示只有mView不为null的时候才会执行get()
        return mRefView?.get()
    }

}