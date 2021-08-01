package com.bryanrady.kotlinjourney.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bryanrady.kotlinjourney.mvp.presenter.BasePresenter
import com.bryanrady.kotlinjourney.mvp.view.IBaseView
import com.bryanrady.kotlinjourney.utils.ReflectUtils

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/1 18:26
 *    desc   :
 */
abstract class BaseActivity<V : IBaseView, P : BasePresenter<V>> : AppCompatActivity(), IBaseView {

    protected var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        if (needPresenter()) {
            mPresenter = ReflectUtils.getT(this, 0) as P
            mPresenter!!.attach(this as V);
        }

    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()

    protected abstract fun initData()

    protected abstract fun needPresenter(): Boolean

    override fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}