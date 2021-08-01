package com.bryanrady.lib_network.module;

import com.bryanrady.lib_network.bean.BaseResponseBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
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
    Call<BaseResponseBean<List<TiyuNewsBean>>> getNewsList(@QueryMap HashMap<String, Object> param);

    @GET(TOUTIAO_NEWS_URL)
    Observable<BaseResponseBean<List<TiyuNewsBean>>> getNewsList2(@QueryMap HashMap<String, Object> param);

}
