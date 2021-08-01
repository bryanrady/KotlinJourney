package com.bryanrady.lib_network.environment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bryanrady.lib_network.R;
import com.bryanrady.lib_network.utils.SPUtils;

/**
 * author : bryanrady
 * e-mail : bryanrady@163.com
 * date   : 2021/7/25 16:18
 * desc   : 环境配置界面  这个界面由app的任何设置界面来跳转，可以做成和开发者选项一样的点击版本号几下来触发
 */
public class EnvironmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_environment);
    }

    public void switchFormal(View view) {
        SPUtils.putBoolean(getApplicationContext(), "isFormal", true );
        Toast.makeText(this, "您已经切换为正式环境，再您退出环境切换页面后，app将会切换网络环境！", Toast.LENGTH_SHORT).show();
    }

    public void switchTest(View view) {
        SPUtils.putBoolean(getApplicationContext(), "isFormal", false);
        Toast.makeText(this, "您已经切换为测试环境，再您退出环境切换页面后，app将会切换网络环境！", Toast.LENGTH_SHORT).show();
    }

}
