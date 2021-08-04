package com.bryanrady.lib_network.interceptor

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/4 22:56
 *    desc   :
 */
class CommonResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        Log.d("bryanrady", "requestTime: " + (System.currentTimeMillis() - requestTime))
        return response
    }

}