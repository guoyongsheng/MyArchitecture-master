package com.mycompany.myarchitecture.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by lenovo on 2016/8/20.
 * 网络请求框架 volley的工具类
 *
 * 单例模式
 */
public class VolleyUtils
{
    private Context context; //上下文对象
    private volatile static VolleyUtils instance;
    private RequestQueue requestQueue; //请求队列

    private VolleyUtils(Context context)
    {
        if(context != null)
        {
            this.context = context.getApplicationContext();
        }
        requestQueue = getRequestQueue();
    }

    //获取对象实例
    public static VolleyUtils getInstance(Context context)
    {
        if(instance == null)
        {
            synchronized (VolleyUtils.class)
            {
                if(instance == null)
                {
                    instance = new VolleyUtils(context);
                }
            }
        }

        return instance;
    }


    //获取RequestQueue实例
    public RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
        {
            if(context == null)
            {
                return null;
            }
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }


    //添加请求到队列中
    public <T> void addToRequestQueue(Request<T> request, Object tag)
    {
        if(request == null || getRequestQueue() == null)
        {
            return;
        }
        request.setTag(tag);
        getRequestQueue().add(request);
    }

    //取消请求
    public void cancleRequest(Object tag)
    {
        if(getRequestQueue() == null)
        {
            return;
        }
        getRequestQueue().cancelAll(tag);
    }
}
