package com.mycompany.myarchitecture;

/**
 * Created by lenovo on 2016/8/20.
 * 回调接口
 */
public interface CallBackListener<T>
{
    void onSuccess(T msg); //成功的回调

    void onFailed(String errorMsg); //失败的回调
}
