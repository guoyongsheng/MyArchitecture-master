package com.mycompany.myarchitecture.base;

import android.util.Log;

import com.mycompany.myarchitecture.config.Config;
import com.mycompany.myarchitecture.imodle.IHttpService;
import com.mycompany.myarchitecture.util.NetworkUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2016/8/21.
 */
public class BaseModle {
    protected static Retrofit retrofit;
    protected IHttpService iHttpService;

    public BaseModle() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();

            iHttpService = retrofit.create(IHttpService.class);
        }
    }


    //获取OkHttpClient的实例
    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //公共参数
        builder.addInterceptor(new QueryParamterIntercepter());

        //缓存
        // File cacheFile = new File(BaseApplication.getIntance().getExternalCacheDir(), "cache_retrofit");
        //  Cache cache = new Cache(cacheFile, 50 * 1024 * 1024);
        // return builder.cache(cache).addInterceptor(new CacheInterceptor()).build();

        //日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);


        //return builder.addInterceptor(new LogInterceptor()).build();
        //return builder.build();


        return builder.build();

    }


    //公共参数
    private static class QueryParamterIntercepter implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request request;
            HttpUrl httpUrl = originalRequest.url().newBuilder()
                    .addQueryParameter("token", "123")
                    .build();
            request = originalRequest.newBuilder().url(httpUrl).build();
            return chain.proceed(request);
        }
    }


    //缓存
    private static class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isAvailable(BaseApplication.getIntance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            int maxAge = 10 * 60;
            response.newBuilder()
                    .header("Cache-Control", "public, max-age = " + maxAge)
                    .build();
            return response;
        }
    }


    //日志
    private static class LogInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.i("BaseModle 参数", "request = " + request.toString());
            Response response = chain.proceed(request);
            MediaType mediaType = response.body().contentType();
            Log.i("BaseModle mediaType", "mediaType = " + mediaType);
            String body = response.body().toString();
            Log.i("BaseModle body", " body = " + body);
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, body))
                    .build();
        }
    }


}
