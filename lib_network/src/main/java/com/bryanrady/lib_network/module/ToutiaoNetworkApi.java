
package com.bryanrady.lib_network.module;

import com.bryanrady.lib_network.bean.BaseResponseBean;
import com.bryanrady.lib_network.errorhandler.ExceptionHandle;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.Interceptor;

/**
 * author : bryanrady
 * e-mail : bryanrady@163.com
 * date   : 2021/7/18 16:26
 * desc   :
 */
public class ToutiaoNetworkApi extends NetworkApi {

    private static volatile ToutiaoNetworkApi sInstance = null;

    private static ToutiaoNetworkApi getApi(){
        if (sInstance == null){
            synchronized (NetworkApi.class){
                if (sInstance == null){
                    sInstance = new ToutiaoNetworkApi();
                }
            }
        }
        return sInstance;
    }

    private ToutiaoNetworkApi() {
        super();
    }

    /**
     * 方法泛型 这个方法放在子类 是为了让调用者更简单的调用
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T getApiService(Class<T> service){
        return getApi().getRetrofit(service).create(service);
    }

    @Override
    public String getTest() {
        return "http://v.juhe.cn/toutiao/";
    }

    @Override
    public String getFormal() {
        return "http://v.juhe.cn/toutiao/";
    }

    @Override
    protected List<Interceptor> getInterceptorList() {
        return null;
    }

    @Override
    protected <T> Function<T, T> getAppErrorHandler() {
        return new Function<T, T>() {
            @Override
            public T apply(@NonNull T responseBean) throws Exception {
                if (responseBean instanceof BaseResponseBean && ((BaseResponseBean)responseBean).getError_code() != 0) {
                    ExceptionHandle.ServerException serverException = new ExceptionHandle.ServerException();
                    serverException.code = ((BaseResponseBean)responseBean).getError_code();
                    serverException.message = ((BaseResponseBean)responseBean).getReason() != null
                            ? ((BaseResponseBean)responseBean).getReason() : "";
                    throw serverException;
                }
                return responseBean;
            }
        };
    }

}
