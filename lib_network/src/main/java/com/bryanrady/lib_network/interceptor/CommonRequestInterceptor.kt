package com.bryanrady.lib_network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/4 22:55
 *    desc   : 公共的请求拦截器 添加请求头 请求参数变换封装 url鉴权等等 具体更具需求定义来
 */
class CommonRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        //    builder.addHeader("", "");
        //    builder.addHeader("", "");
        return chain.proceed(builder.build())
    }

}