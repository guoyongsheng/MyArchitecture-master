package com.mycompany.myarchitecture.homepage;

import com.mycompany.myarchitecture.base.BasePresenter;
import com.mycompany.myarchitecture.base.BaseView;
import com.mycompany.myarchitecture.entity.ImageInfo;

import java.util.List;

/**
 * Created by lenovo on 2016/8/19.
 */
public interface HomePageContract
{

    interface View<HomePagePresenter> extends BaseView<HomePagePresenter>
    {
        void showHomePageData(List<ImageInfo> msg); //展示主页数据

        void showErrorMessage(String errorMsg); //显示错误信息


        //用retrofit框架实现登录
        void login(String name, String password);
    }

    //这个接口里面其实都是一些网络请求的接口
    interface Presenter extends BasePresenter
    {
        void loadHomePageData();  //加载主页数据

        //用retrofit框架实现登录
        void login(String name, String password);
    }
}
