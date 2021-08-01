package com.bryanrady.lib_network.module;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bryanrady.lib_network.R;
import com.bryanrady.lib_network.environment.EnvironmentActivity;
import com.bryanrady.lib_network.observer.BaseObserver;

import java.util.HashMap;
import java.util.List;

/**
 * author : bryanrady
 * e-mail : bryanrady@163.com
 * date   : 2021/7/18 14:40
 * desc   :
 */
public class TestNetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_network);
    }

    public void request(View view) {
        testRetrofit();
    }

    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    @SuppressLint("CheckResult")
    private void testRetrofit(){
        HashMap<String, Object> param = new HashMap<>();
        param.put("key", "4f71ad38a3a1b48ded68d993fe0d0f71");
        param.put("type", "tiyu");
        param.put("page", 1);
        param.put("page_size", 10);
        param.put("is_filter", 1);

//        ToutiaoNetworkApi.getApiService(ToutiApi.class)
//                .getNewsList(param)
//                .enqueue(new Callback<BaseResponseBean<List<TiyuNewsBean>>>() {
//                    @Override
//                    public void onResponse(Call<BaseResponseBean<List<TiyuNewsBean>>> call, Response<BaseResponseBean<List<TiyuNewsBean>>> response) {
//                        Log.e("wangqingbin", "当前是否是主线程 == " + isMainThread());
//                        Log.d("wangqingbin","onResponse : " + response.toString());
//                    }
//
//                    @Override
//                    public void onFailure(Call<BaseResponseBean<List<TiyuNewsBean>>> call, Throwable t) {
//                        Log.e("wangqingbin", "当前线程 == " + Thread.currentThread().getName());
//                        Log.d("wangqingbin","onFailure : " + t.getMessage());
//                    }
//                });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ToutiaoNetworkApi.getApiService(ToutiApi.class)
//                        .getNewsList2(param)
//                        .subscribe(new BaseObserver<List<TiyuNewsBean>>() {
//                            @Override
//                            protected void onSuccess(List<TiyuNewsBean> data) {
//                                Log.e("wangqingbin", "响应成功");
//                                Log.e("wangqingbin", "当前线程 == " + Thread.currentThread().getName());
//                            }
//
//                            @Override
//                            protected void onFail(String errMsg) {
//                                Log.e("wangqingbin", "当前线程 == " + Thread.currentThread().getName());
//                                Log.e("wangqingbin", "errMsg == " + errMsg);
//                            }
//                        });
//            }
//        }).start();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ToutiaoNetworkApi.getApiService(ToutiApi.class)
//                        .getNewsList2(param)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new BaseObserver<List<TiyuNewsBean>>() {
//                            @Override
//                            protected void onSuccess(List<TiyuNewsBean> data) {
//                                Log.e("wangqingbin", "响应成功");
//                                Log.e("wangqingbin", "当前线程 == " + Thread.currentThread().getName());
//                            }
//
//                            @Override
//                            protected void onFail(String errMsg) {
//                                Log.e("wangqingbin", "errMsg == " + errMsg);
//                            }
//                        });
//
//            }
//        }).start();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ToutiaoNetworkApi.getApiService(ToutiApi.class)
//                        .getNewsList2(param)
//                        .compose(NetworkApi.applySchedulers(new BaseObserver<List<TiyuNewsBean>>() {
//                            @Override
//                            protected void onSuccess(List<TiyuNewsBean> data) {
//                                Log.e("wangqingbin", "当前线程 == " + Thread.currentThread().getName());
//                                Log.e("wangqingbin", "响应成功");
//                            }
//
//                            @Override
//                            protected void onFail(String errMsg) {
//                                Log.e("wangqingbin", "errMsg == " + errMsg);
//                            }
//                        }));
//            }
//        }).start();


        ToutiaoNetworkApi.getApiService(ToutiApi.class)
                .getNewsList2(param)
                .subscribe(new BaseObserver<List<TiyuNewsBean>>() {
                    @Override
                    protected void onSuccess(List<TiyuNewsBean> data) {
                        Log.e("wangqingbin", "当前线程 == " + Thread.currentThread().getName());
                        Log.e("wangqingbin", "响应成功");
                    }

                    @Override
                    protected void onFail(String errMsg) {
                        Log.e("wangqingbin", "当前线程 == " + Thread.currentThread().getName());
                        Log.e("wangqingbin", "errMsg == " + errMsg);
                    }
                });

    }

    public void switchTest(View view) {
    }

    private int clickCount = 0;

    public void switchEnvironment(View view) {
        if (++clickCount == 5){
            Intent intent = new Intent(this, EnvironmentActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
