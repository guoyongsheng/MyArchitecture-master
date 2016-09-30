package com.mycompany.myarchitecture.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2016/8/20.
 * gson的工具类 单例模式
 *
 * 只有一个实例，私有的构造方法
 */
public class GsonUtils
{
    private volatile static GsonUtils instance;

    private GsonUtils()
    {

    }

   /* *//**
     * 懒汉模式 线程不安全
     * @return
     *//*
    public static GsonUtils getInstance()
    {
        if(instance == null)
        {
            instance = new GsonUtils();
        }

        return instance;
    }*/


   /* *//**
     * 懒汉式 同步锁
     * @return
     *//*
    public static GsonUtils getInstance()
    {
        synchronized (GsonUtils.class)
        {
            if(instance == null)
            {
                instance = new GsonUtils();
            }
        }

        return instance;
    }*/


    /**
     * 双重校验
     *
     */
    public static GsonUtils getInstance()
    {
        if(instance == null)
        {
            synchronized (GsonUtils.class)
            {
                if(instance == null)
                {
                    instance = new GsonUtils();
                }
            }
        }

        return instance;
    }

    //对象转字符串
    public String toJson(Object json)
    {
        Gson gson = new Gson();
        return gson.toJson(json);
    }

    //json转JavaBean
    public <T> T fromJson(String json, Class<T> t)
    {
        Gson gson = new Gson();
        return gson.fromJson(json, t);
    }

    //json转list
    public <T> T fromJson(String json, Type type)
    {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

}
