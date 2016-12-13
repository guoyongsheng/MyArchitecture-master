package com.mycompany.myarchitecture.imodle;

import android.content.Context;

import com.mycompany.myarchitecture.CallBackListener;
import com.mycompany.myarchitecture.entity.ImageInfo;
import com.mycompany.myarchitecture.entity.UserEntity;
import com.mycompany.myarchitecture.entity.UserInfo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lenovo on 2016/8/19.
 * <p>
 * modle层 网络请求的接口
 */
public interface HomePageModle {
    /**
     * 获取主页数据
     *
     * @param context  上下文对象
     * @param page     当前页
     * @param size     每次获取的条数
     * @param listener dddd
     */
    void getHomePageDataByVolley(Context context, int page, int size, CallBackListener<List<ImageInfo>> listener);


    void login(Map<String, String> map, CallBackListener<UserInfo> callBackListener);


    interface ILogin {
        @FormUrlEncoded
        @POST("account/loginInfo.json")
        Call<UserEntity> login(@FieldMap Map<String, String> map);
    }
}
