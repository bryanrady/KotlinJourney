package com.bryanrady.kotlinjourney.http

import android.content.Context
import com.bryanrady.kotlinjourney.App.Companion.getApplication
import com.bryanrady.kotlinjourney.BuildConfig
import com.bryanrady.lib_network.INetworkRequiredInfo

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/4 23:44
 *    desc   :
 */
class NetworkRequiredImpl : INetworkRequiredInfo {

    override fun getAppVersionCode(): String {
        return BuildConfig.VERSION_CODE.toString()
    }

    override fun getAppVersionName(): String {
        return BuildConfig.VERSION_NAME
    }

    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun getApplicationContext(): Context {
        return getApplication()
    }
}