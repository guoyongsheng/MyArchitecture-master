package com.mycompany.myarchitecture;

import com.mycompany.myarchitecture.entity.ResponseInfo;

import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lenovo on 2016/8/21.
 */
public class RetrofitCallBack<T extends ResponseInfo> implements Callback<T>
{

    private CallBackListener callBackListener;

    public RetrofitCallBack(CallBackListener callBackListener)
    {
        this.callBackListener = callBackListener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response)
    {
        Set<String> set = call.request().url().queryParameterNames();
        boolean isError = response.body().isError();
        onSuccess(response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t)
    {
        onFailed(t.getMessage());
    }

    private void onSuccess(Response<T> response)
    {
        callBackListener.onSuccess(response.body().getResults());
    }

    private void onFailed(String errorMsg)
    {
        callBackListener.onFailed(errorMsg);
    }
}
