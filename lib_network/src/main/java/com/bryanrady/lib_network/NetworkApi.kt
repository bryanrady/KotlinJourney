package com.bryanrady.lib_network

import com.bryanrady.lib_network.factory.CustomRxJava2CallAdapterFactory
import com.bryanrady.lib_network.interceptor.CommonRequestInterceptor
import com.bryanrady.lib_network.interceptor.CommonResponseInterceptor
import io.reactivex.functions.Function
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *    author : bryanrady
 *    e-mail : bryanrady@163.com
 *    date   : 2021/8/1 19:48
 *    desc   :
 */
abstract class NetworkApi {

    /**
     * 伴生对象
     */
    companion object {

        private var TIME_OUT: Long = 30

        private var retrofitHashMap: MutableMap<String, Retrofit> = HashMap<String, Retrofit>()

        private lateinit var mNetworkRequiredInfo: INetworkRequiredInfo

        fun init(networkRequiredInfo : INetworkRequiredInfo) {
            this.mNetworkRequiredInfo = networkRequiredInfo
        }

    }

    private val mBaseUrl: String = "http://v.juhe.cn/toutiao/"

    private var mOkHttpClient: OkHttpClient? = null

    protected fun getRetrofit(service: Class<*>): Retrofit? {
        val retrofitKey = mBaseUrl + service.name
        if (retrofitHashMap[retrofitKey] != null) {
            return retrofitHashMap[retrofitKey]
        }
        val builder = Retrofit.Builder()
        builder.baseUrl(mBaseUrl)
        builder.addConverterFactory(GsonConverterFactory.create())
    //    builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        builder.addCallAdapterFactory(CustomRxJava2CallAdapterFactory.create(getAppErrorHandler()))
        builder.client(getOkHttpClient()!!)

        val retrofit = builder.build()

        retrofitHashMap[retrofitKey] = retrofit

        return retrofit
    }

    private fun getOkHttpClient(): OkHttpClient? {
        if (mOkHttpClient == null){
            val builder = OkHttpClient.Builder()
            builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            builder.readTimeout(TIME_OUT, TimeUnit.SECONDS)
            if (getInterceptorList() != null) {
                for (interceptor in getInterceptorList()!!) {
                    builder.addInterceptor(interceptor)
                }
            }
            //// TODO: 2021/7/18  不用域名的请求头 可能不一样，所以这里的设置还需要职责划分的更明显才行
            builder.addInterceptor(CommonRequestInterceptor())
            builder.addInterceptor(CommonResponseInterceptor())

            if (mNetworkRequiredInfo.isDebug()) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(loggingInterceptor)
            }
            mOkHttpClient = builder.build()
        }
        return mOkHttpClient
    }

    protected abstract fun getInterceptorList(): ArrayList<Interceptor>?

    protected abstract fun <T> getAppErrorHandler(): Function<T, T>?

//    public static  <T> Function<T, T> getAppErrorHandler2(){
//        return new Function<T, T>() {
//            @Override
//            public T apply(@NonNull T responseBean) throws Exception {
//                if (responseBean instanceof BaseResponseBean && ((BaseResponseBean)responseBean).getError_code() != 0) {
//                    ExceptionHandle.ServerException serverException = new ExceptionHandle.ServerException();
//                    serverException.code = ((BaseResponseBean)responseBean).getError_code();
//                    serverException.message = ((BaseResponseBean)responseBean).getReason() != null
//                    ? ((BaseResponseBean)responseBean).getReason() : "";
//                    throw serverException;
//                }
//                return responseBean;
//            }
//        };
//    }

}