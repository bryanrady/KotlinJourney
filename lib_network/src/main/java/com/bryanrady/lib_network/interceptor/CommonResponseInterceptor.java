package com.bryanrady.lib_network.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * author : bryanrady
 * e-mail : bryanrady@163.com
 * date   : 2021/7/18 16:31
 * desc   :
 */
public class CommonResponseInterceptor implements Interceptor {

    private static final String TAG = CommonResponseInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        long requestTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        Log.d(TAG, "requestTime: " + (System.currentTimeMillis() - requestTime));
        return response;
    }
}
