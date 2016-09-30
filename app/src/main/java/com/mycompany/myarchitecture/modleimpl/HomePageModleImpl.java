package com.mycompany.myarchitecture.modleimpl;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;
import com.mycompany.myarchitecture.CallBackListener;
import com.mycompany.myarchitecture.GsonRequest;
import com.mycompany.myarchitecture.RetrofitCallBack;
import com.mycompany.myarchitecture.base.BaseModle;
import com.mycompany.myarchitecture.config.Config;
import com.mycompany.myarchitecture.entity.ImageInfo;
import com.mycompany.myarchitecture.entity.ResponseInfo;
import com.mycompany.myarchitecture.entity.UserEntity;
import com.mycompany.myarchitecture.entity.UserInfo;
import com.mycompany.myarchitecture.imodle.HomePageModle;
import com.mycompany.myarchitecture.util.VolleyUtils;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by lenovo on 2016/8/19.
 * 网络请求的实现
 */
public class HomePageModleImpl extends BaseModle implements HomePageModle
{

    private HomePageModle.ILogin iLogin;

    public HomePageModleImpl()
    {
        super();
        iLogin = retrofit.create(HomePageModle.ILogin.class);
    }

    /**
     * volley 获取网络请求的数据
     *
     * @param context  上下文对象
     * @param page     当前页
     * @param size     每次获取的条数
     * @param listener
     */
    public void getHomePageDataByVolley(Context context, int page, int size, CallBackListener<List<ImageInfo>> listener)
    {
        String url = Config.BASE_URL;
        Map<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(page));
        map.put("size", String.valueOf(size));
        Type type = new TypeToken<ResponseInfo<ImageInfo>>()
        {
        }.getType();

        GsonRequest<ResponseInfo<ImageInfo>> gsonRequest = new GsonRequest<>(Request.Method.POST, url, type, new Response.Listener<ResponseInfo<ImageInfo>>()
        {
            @Override
            public void onResponse(ResponseInfo<ImageInfo> response)
            {

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });

        VolleyUtils.getInstance(context.getApplicationContext()).addToRequestQueue(gsonRequest, context);
    }

    @Override
    public void login(Map<String, String> map, CallBackListener<UserInfo> callBackListener)
    {
        Call<UserEntity> call = iLogin.login(map);
        call.enqueue(new RetrofitCallBack<UserEntity>(callBackListener));
    }

}
