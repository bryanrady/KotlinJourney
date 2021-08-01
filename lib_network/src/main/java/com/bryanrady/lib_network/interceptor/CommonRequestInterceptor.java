package com.bryanrady.lib_network.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author : bryanrady
 * e-mail : bryanrady@163.com
 * date   : 2021/7/18 16:31
 * desc   : 公共的请求拦截器 添加请求头 请求参数变换封装 url鉴权等等 具体更具需求定义来
 */
public class CommonRequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
    //    builder.addHeader("", "");
        return chain.proceed(builder.build());
    }
}
