package com.bryanrady.lib_network.observer;

import com.bryanrady.lib_network.bean.BaseResponseBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author : bryanrady
 * e-mail : bryanrady@163.com
 * date   : 2021/7/18 18:30
 * desc   :
 */
public abstract class BaseObserver<T> implements Observer<BaseResponseBean<T>> {

    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        this.mDisposable = d;
    }

    @Override
    public void onNext(BaseResponseBean<T> value) {
        if (value != null) {
            BaseResponseBean.ResultBean<T> result = value.getResult();
            if (result != null) {
                T data = result.getData();
                onSuccess(data);
                return;
            }
        }
        throw new NullPointerException("service return data is error");
    }

    @Override
    public void onError(Throwable e) {
        //// TODO: 2021/7/18 异常处理
        onFail(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    /**
     * 解除RxJava订阅
     */
    private void unSubscribe(){
        if (mDisposable != null && !mDisposable.isDisposed()){
            mDisposable.dispose();
        }
    }

    protected abstract void onSuccess(T data);

    protected abstract void onFail(String errMsg);

}
