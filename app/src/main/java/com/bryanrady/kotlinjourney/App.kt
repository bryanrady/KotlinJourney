package com.bryanrady.kotlinjourney

import android.app.Application
import android.content.Context
import com.bryanrady.kotlinjourney.http.NetworkRequiredImpl
import com.bryanrady.lib_network.NetworkApi

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/1 20:17
 *    desc   :
 */
class App : Application() {

    companion object {

        private lateinit var mContext: Context

        fun getApplication(): Context {
            return mContext
        }

    }

    override fun onCreate() {
        super.onCreate()
        mContext = this

        NetworkApi.init(NetworkRequiredImpl())
    }

}