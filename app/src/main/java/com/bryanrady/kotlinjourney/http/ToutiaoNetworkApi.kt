package com.bryanrady.kotlinjourney.http

import com.bryanrady.lib_network.NetworkApi
import com.bryanrady.lib_network.bean.BaseResponse
import com.bryanrady.lib_network.errorhandler.ExceptionHandle
import io.reactivex.functions.Function
import okhttp3.Interceptor

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/4 23:47
 *    desc   :
 */
class ToutiaoNetworkApi private constructor() : NetworkApi() {

    /**
     * 双重检查模式单例
     */
    companion object {

        private val instance: ToutiaoNetworkApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ToutiaoNetworkApi()
        }

        fun <T> getApiService(service: Class<T>): T {
            return instance.getRetrofit(service)!!.create(service)
        }
    }

    override fun getInterceptorList(): ArrayList<Interceptor>? {
        return null
    }

    override fun <T> getAppErrorHandler(): Function<T, T>? {
        return Function { responseBean ->
            if (responseBean is BaseResponse<*> && (responseBean as BaseResponse<*>).getError_code() != 0) {
                val serverException =
                    ExceptionHandle.ServerException()
                val response = responseBean as BaseResponse<*>
                serverException.code = response.getError_code()
                serverException.message = response.getReason()
                throw serverException
            }
            responseBean
        }
    }

}