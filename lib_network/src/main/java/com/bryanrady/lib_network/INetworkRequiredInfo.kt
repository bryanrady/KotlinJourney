package com.bryanrady.lib_network

import android.content.Context

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/1 20:13
 *    desc   :
 */
interface INetworkRequiredInfo {

    fun getAppVersionCode(): String
    fun getAppVersionName(): String
    fun isDebug(): Boolean
    fun getApplicationContext(): Context

}