package com.bryanrady.kotlinjourney.http;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bryanrady.kotlinjourney.R;
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
        setContentView(R.layout.activity_main);
    }

    public void request(View view) {
        testRetrofit();
    }

    @SuppressLint("CheckResult")
    private void testRetrofit(){
        HashMap<String, Object> param = new HashMap<>();
        param.put("key", "4f71ad38a3a1b48ded68d993fe0d0f71");
        param.put("type", "tiyu");
        param.put("page", 1);
        param.put("page_size", 10);
        param.put("is_filter", 1);

        ToutiaoNetworkApi.Companion.getApiService(ToutiApi.class)
                .getNewsList(param)
                .subscribe(new BaseObserver<List<TiyuNewsBean>>() {

                    @Override
                    protected void onSuccess(List<TiyuNewsBean> data) {
                        Log.e("wangqingbin", "响应成功");
                    }

                    @Override
                    protected void onFail(int code, @org.jetbrains.annotations.Nullable String errMsg) {
                        Log.e("wangqingbin", "响应失败");
                    }

                });

    }

}
