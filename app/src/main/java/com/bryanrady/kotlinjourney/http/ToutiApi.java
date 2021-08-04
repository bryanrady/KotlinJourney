package com.bryanrady.kotlinjourney.http;

import com.bryanrady.lib_network.bean.BaseResponse;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * author : bryanrady
 * e-mail : bryanrady@163.com
 * date   : 2021/7/18 14:57
 * desc   :
 */
public interface ToutiApi {

    String TOUTIAO_NEWS_URL = "index";

    @GET(TOUTIAO_NEWS_URL)
    Observable<BaseResponse<List<TiyuNewsBean>>> getNewsList(@QueryMap HashMap<String, Object> param);

}
