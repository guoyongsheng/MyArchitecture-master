package com.mycompany.myarchitecture.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by lenovo on 2016/8/19.
 * <p/>
 * 基类的activity
 */
public abstract class BaseActivity extends FragmentActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initVariables();
        initView(savedInstanceState);
        initPresenter();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    /**
     * 初始化变量
     * 这个变量可能是上个界面传过来的
     */
    public abstract void initVariables();


    /**
     *
     * @param savedInstanceState
     */
    public abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化presenter
     */
    public abstract void initPresenter();
}
