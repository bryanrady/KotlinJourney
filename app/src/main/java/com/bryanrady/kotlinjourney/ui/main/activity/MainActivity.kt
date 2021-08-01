package com.bryanrady.kotlinjourney.ui.main.activity

import com.bryanrady.kotlinjourney.R
import com.bryanrady.kotlinjourney.base.BaseActivity
import com.bryanrady.kotlinjourney.mvp.presenter.main.MainPresenter
import com.bryanrady.kotlinjourney.mvp.view.main.IMainView

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/1 19:15
 *    desc   :
 */
class MainActivity : BaseActivity<IMainView, MainPresenter>() {

    override fun getLayoutId(): Int  = R.layout.activity_main

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun needPresenter(): Boolean  = true
}