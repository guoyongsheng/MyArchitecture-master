package com.mycompany.myarchitecture.base;

import android.app.Application;
import android.content.Context;


public class BaseApplication extends Application
{
    private static Context context;
    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this;
    }


    public static Context getIntance()
    {
        return context;
    }


    @Override
    public void onTrimMemory(int level)
    {
        super.onTrimMemory(level);

        switch (level)
        {
        /**
         * 所有的UI界面不可见时 就相当于是按下HOME键
         * 可以清空图片缓存 实现内存优化
         */
        case TRIM_MEMORY_UI_HIDDEN:

            break;

        default:
            break;
        }
    }
}
